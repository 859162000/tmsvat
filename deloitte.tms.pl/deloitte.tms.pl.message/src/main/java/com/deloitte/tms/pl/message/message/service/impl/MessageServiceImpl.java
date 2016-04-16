package com.deloitte.tms.pl.message.message.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.message.message.dao.MessageDao;
import com.deloitte.tms.pl.message.message.model.Message;
import com.deloitte.tms.pl.message.message.model.RecipientType;
import com.deloitte.tms.pl.message.message.model.SendInfo;
import com.deloitte.tms.pl.message.message.model.SendStatus;
import com.deloitte.tms.pl.message.message.model.SendType;
import com.deloitte.tms.pl.message.message.service.ISendMessageCallback;
import com.deloitte.tms.pl.message.message.service.MessageService;
import com.deloitte.tms.pl.message.message.service.MessageUserProvider;
import com.deloitte.tms.pl.message.utils.Email;
import com.deloitte.tms.pl.security.model.SecurityUser;

@Component("messageService")
public class MessageServiceImpl implements MessageService{

    private final static Logger log = Logger.getLogger(MessageServiceImpl.class);

    private final static String MESSAGE_SENDER = "";

    private final static DefaultSendMessageCallback defaultCallback = new DefaultSendMessageCallback();

    @Resource
    private MessageDao messageDao;

    /**
     * 发送消息(待发送)
     * 
     * @param message
     */
    public void execSendMessage(Message message) {
        checkMessage(message);
        messageDao.saveMessage(message);

    }

    /**
     * 标记消息已读
     * 
     * @param id
     */
    public void execSetMessageIsRead(Long id, Boolean isRead) {
        messageDao.messageIsRead(id, isRead);
    }

    /**
     * 标记消息已关闭
     * 
     * @param id
     */
    public void execSetMessageIsClose(List<Long> ids) {
        messageDao.messageIsClose(ids);
    }

    public void execStartSendMessageTask() {
        List<Message> list = messageDao.findUnSendMessages();
        for (Message message : list) {
            try {
                execDoSendMessage(message);
            } catch (Exception e) {
                // 消息发送失败
                log.error("消息发送失败:messageId=" + message.getId(), e);
                message.setSend(true);
            }
        }
    }

    /**
     * 开始发送消息(批处理调用)
     * 
     * @param message
     */
    public void execDoSendMessage(Message message) {
    	MessageUserProvider userProvider=null;
        execDoSendMessage(message, defaultCallback,userProvider);
    }

    /**
     * 开始发送消息(批处理调用)
     * 
     * @param message
     */
    protected void execDoSendMessage(Message message, ISendMessageCallback callback,MessageUserProvider userProvider) {
        if (message.isSend()) {
            return;
        }

        if (callback == null) {
            callback = MessageServiceImpl.defaultCallback;
        }
        message.setSendDate(new Date());
        List<SecurityUser> userList = userProvider.getUserList();

        if (userList.isEmpty()) {
            throw new BusinessException("找不到指定的消息接收人");
        }

        SendInfo sendInfo = null;
        for (SecurityUser user : userList) {
            sendInfo = new SendInfo();
            sendInfo.setSendDate(new Date());
            sendInfo.setMessageId(message.getId());
            sendInfo.setSender(message.getSender());
            sendInfo.setRecipient(user.getUsername());
            executeSendMessage(message, sendInfo, user, callback);
        }

        message.setSend(true);

        messageDao.updateMessage(message);
    }

    /**
     * 执行消息发送保存记录
     * 
     * @param sendInfo
     */
    protected void executeSendMessage(Message message, SendInfo sendInfo, SecurityUser user, ISendMessageCallback callback) {
        callback.beforeSend(message, sendInfo, user);
        try {
            if (message.getSendType() == SendType.SYSTEM) {
                sendSystemMessage(message, sendInfo, user);
            } else if (message.getSendType() == SendType.EMAIL) {
                sendEmailMessage(message, sendInfo, user);
            }
        } catch (Exception e) {
            log.error("消息发送失败:messageId=" + sendInfo.getId() + ",username=" + user.getUsername(), e);
            sendInfo.setSendStatus(SendStatus.EXCEPTION);
            callback.onError(message, sendInfo, user);
        }
        sendInfo.setIsSend(true);
        messageDao.saveSendInfo(sendInfo);
        callback.onSuccess(message, sendInfo, user);
    }

    /**
     * 发送系统消息
     * 
     * @param message
     * @param sendInfo
     */
    protected void sendSystemMessage(Message message, SendInfo sendInfo, SecurityUser user) {
        // 系统消息只在数据库中插入记录不做处理
    }

    /**
     * 发送邮件消息
     * 
     * @param message
     * @param sendInfo
     */
    protected void sendEmailMessage(Message message, SendInfo sendInfo, SecurityUser user) {
        Email.make(user.getEmail(), message.getTitle(), message.getContent()).send();
    }

    /**
     * 检查消息关键字段
     * 
     * @param message
     */
    protected static void checkMessage(Message message) {
        AssertHelper.notEmpty_assert(message.getBussinessId(), "bussinessId不能为空");
        AssertHelper.notEmpty_assert(message.getBussinessType(), "bussinessType不能为空");
        AssertHelper.notEmpty_assert(message.getRecipientType(), "接收人类型不能为空");
        if (message.getRecipientType() == RecipientType.ROLE || message.getRecipientType() == RecipientType.OWNER) {
            AssertHelper.notEmpty_assert(message.getRecipients(), "接收人不能为空");
        }
        AssertHelper.notEmpty_assert(message.getSendType(), "标题不能为空");
        AssertHelper.notEmpty_assert(message.getTitle(), "标题不能为空");
        AssertHelper.notEmpty_assert(message.getContent(), "消息内容不能为空");
        if (AssertHelper.empty(message.getSender())) {
        	message.setSender("SYSTEM");
        }
    }

}
