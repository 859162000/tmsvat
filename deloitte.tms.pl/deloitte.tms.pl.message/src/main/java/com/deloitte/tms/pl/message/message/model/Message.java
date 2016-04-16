package com.deloitte.tms.pl.message.message.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = Message.TABLE)
public class Message extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -552718759897925867L;

    public static final String TABLE = TablePreDef.MESSAGE + "Message";
    public static final String SEQ = TABLE;

    @Transient
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Id
    @GenericGenerator(name = SEQ + "_GENERATOR", strategy = Ling2IdGenerator.STRATEGY_NAME, parameters = { @Parameter(name = "pkColumnValue", value = SEQ) })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ + "_GENERATOR")
    @Column(name = "id", length = 16)
    private Long id;

    @OneToMany(mappedBy = "message", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<SendInfo> sendList;

    @Column(name = "bussiness_Type", length = 100)
    private String bussinessType;

    @Column(name = "bussiness_Id", length = 100)
    private String bussinessId;

    @Column(name = "recipient_Type")
    @Enumerated(EnumType.STRING)
    private RecipientType recipientType;

    @Column(name = "recipients", length = 2000)
    private String recipients;

    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "sendDate")
    private Date sendDate;

    @Column(name = "keepDate")
    private Date keepDate;

    @Column(name = "send_Type")
    @Enumerated(EnumType.STRING)
    private SendType sendType;

    @Column(name = "is_Send")
    private boolean isSend = false;

    @Column(name = "send_Order")
    @Enumerated(EnumType.STRING)
    private Order sendOrder = Order.NORMAL;

    @Column(name = "sender", length = 160)
    private String sender;

    @Transient
    private String sendOrderVal;

    @Transient
    private String recipientTypeVal;

    @Transient
    private String sendTypeVal;

    @Transient
    private String sendDateVal;

    public String getSendOrderVal() {
        return getSendOrder().toString();
    }

    public String getRecipientTypeVal() {
        return getRecipientType().toString();
    }

    public String getSendTypeVal() {
        return getSendType().toString();
    }

    public String getSendDateVal() {
        return format.format(getSendDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<SendInfo> getSendList() {
        return sendList;
    }

    public void setSendList(List<SendInfo> sendList) {
        this.sendList = sendList;
    }

    public String getBussinessType() {
        return bussinessType;
    }

    public void setBussinessType(String bussinessType) {
        this.bussinessType = bussinessType;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public RecipientType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(RecipientType recipientType) {
        this.recipientType = recipientType;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getKeepDate() {
        return keepDate;
    }

    public void setKeepDate(Date keepDate) {
        this.keepDate = keepDate;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean isSend) {
        this.isSend = isSend;
    }

    public Order getSendOrder() {
        return sendOrder;
    }

    public void setSendOrder(Order sendOrder) {
        this.sendOrder = sendOrder;
    }

    public static Message obtainMessage(String bussinessType, String bussinessId, RecipientType recipientType,
            String recipients, SendType sendType, String title, String content) {
        Message message = new Message();
        message.setBussinessType(bussinessType);
        message.setBussinessId(bussinessId);
        message.setRecipientType(recipientType);
        message.setRecipients(recipients);
        message.setSendType(sendType);
        message.setTitle(title);
        message.setContent(content);
        return message;
    }
//
//    @Transient
//    private static MessageServiceImpl messageService;
//
//    public void send() {
//        if (messageService == null) {
//            messageService = SpringUtil.getBean("messageService");
//        }
//        if (messageService == null) {
//            throw new BusinessException("messageService未定义");
//        }
//
//        messageService.execSendMessage(this);
//    }
//    
//    public void sendAtOnce(){
//        send();
//        messageService.execDoSendMessage(this);
//    }
}
