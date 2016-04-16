package com.deloitte.tms.pl.message.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.StringUtils;

@Component
public class MailUtil implements ApplicationContextAware {

    @Resource
    private VelocityEngine velocityEngine;

    private static MailUtil mailTool;

    private static Map<String, JavaMailSender> mailSenderMap;

    private static Properties ps;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() throws IOException {
        mailTool = this;
        ps = PropertiesLoaderUtils.loadAllProperties("config/mail.properties");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void sendMail(Email email, String mailSender) {
        mailTool.chekcMailSender();

        JavaMailSender mailSenderImpl = mailSenderMap.get(mailSender);
        if (mailSenderImpl == null) {
            throw new MailSendException("找不到指定的mailSender：" + mailSender);
        }

        String realSender = ps.getProperty(mailSender + ".name");
        if (realSender != null) {
            email.setSender(realSender);
        }

        String realNick = ps.getProperty(mailSender + ".nick");
        if (realNick != null) {
            email.setNick(realNick);
        }

        MailUtil.sendMail(email, mailSenderImpl);
    }

    public static void sendMail(Email email, JavaMailSender mailSender) {
        try {
            mailTool.send(email, mailSender);
        } catch (UnsupportedEncodingException e) {
            throw new MailSendException("邮件发送失败", e);
        } catch (MessagingException e) {
            throw new MailSendException("邮件发送失败", e);
        }
    }

    public static void sendMail(Email email) {
        MailUtil.sendMail(email, "defaultMailSender");
    }

    public static String buildMessage(String templateLocation, Map<String, Object> model) {
        return mailTool._buildMessage(templateLocation, model);
    }

    private synchronized void chekcMailSender() {
        if (mailSenderMap == null) {
            mailSenderMap = mailTool.applicationContext.getBeansOfType(JavaMailSender.class);
        }
    }

    private MimeMessage buildMimeMessage(Email email, JavaMailSender mailSender) throws MessagingException,
            UnsupportedEncodingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");
        if (StringUtils.hasText(email.getNick())) {
            messageHelper.setFrom(new InternetAddress(new StringBuilder(email.getNick()).append("<")
                    .append(email.getSender()).append(">").toString()));
        } else {
            messageHelper.setFrom(new InternetAddress(email.getSender()));
        }
        messageHelper.setTo(email.getRecipients().toArray(new String[email.getRecipients().size()]));

        messageHelper.setSubject(email.getSubject());
        messageHelper.setText(email.getText(), true);
        for (Iterator<Entry<String, File>> iterator = email.getAttachmentMap().entrySet().iterator(); iterator
                .hasNext();) {
            Entry<String, File> entry = iterator.next();
            messageHelper.addAttachment(MimeUtility.encodeWord(entry.getKey()), entry.getValue());
        }
        for (Iterator<Entry<String, File>> iterator = email.getInlineMap().entrySet().iterator(); iterator.hasNext();) {
            Entry<String, File> entry = iterator.next();
            messageHelper.addInline(MimeUtility.encodeWord(entry.getKey()), entry.getValue());
        }

        return mailMessage;
    }

    private String _buildMessage(String templateLocation, Map<String, Object> model) {
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "utf-8", model);
    }

    private void send(Email email, JavaMailSender mailsender) throws UnsupportedEncodingException, MessagingException {
        MimeMessage mailMessage = buildMimeMessage(email, mailsender);

        mailsender.send(mailMessage);

//         JavaMailSenderImpl impl = (JavaMailSenderImpl) mailsender;
//         Store store = impl.getSession().getStore("imap");
//         store.connect(impl.getHost(), 110, impl.getUsername(),impl.getPassword());
//         Folder folder = store.getFolder("Drafts");
//         MimeMessage draftMessages[] = { mailMessage };
//         folder.appendMessages(draftMessages);
    }
    
    /**
     * 保存草稿
     * @param email
     * @throws SendFailedException
     */
    public static void sendDrafts(Email email) throws SendFailedException {
        Session session = null;
        Message msg = null;
        try {
            String host = "imap.163.com";
            final String userName = email.getSender();
            final String password = email.getSender();
            String from = userName + "@163.com";
            String friendName = email.getNick();
            try {
                friendName = javax.mail.internet.MimeUtility.encodeText(email.getNick());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String to = "lihui.he@newtouch.cn";
            String subject = email.getSubject();
            String body = email.getText();

            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imap");
            props.setProperty("mail.imap.host", host);
            props.setProperty("mail.imap.port", "143");
            props.setProperty("mail.smtp.auth", "true");
            session = Session.getInstance(props);
            Store store = session.getStore("imap");
            store.connect(userName, password);
            Folder folder = store.getFolder("草稿箱");

            msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(new StringBuilder(friendName).append("<").append(from).append(">")
                    .toString()));
            InternetAddress[] toAddress = { new InternetAddress() };
            toAddress[0].setAddress(to);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject);
            msg.setText(body);
            MimeMessage draftMessages[] = { (MimeMessage) msg };
            folder.appendMessages(draftMessages);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (SendFailedException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
//    	MailUtil.sendDrafts();
	}


}
