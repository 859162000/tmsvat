package com.deloitte.tms.pl.message.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.StringUtils;

public class Email {

    private String sender;
    private String senderNick;
    private List<String> recipients = new ArrayList<String>();
    private String subject;
    private String text;
    private Map<String, File> attachmentMap = new HashMap<String, File>();
    private Map<String, File> inlineMap = new HashMap<String, File>();

    public static Email make(String[] recipients, String subject, String text) {
        return new Email(null, recipients, subject, text);
    }
    
    public static Email make(String recipient, String subject, String text) {
        return new Email(null, new String[] { recipient }, subject, text);
    }

    public static Email make(String sender, String[] recipients, String subject, String text) {
        return new Email(sender, recipients, subject, text);
    }

    public static Email make(String sender, String recipient, String subject, String text) {
        return new Email(sender, new String[] { recipient }, subject, text);
    }

    private Email(String sender, String[] recipients, String subject, String text) {
        this.sender = sender;
        for (String recipient : recipients) {
            this.recipients.add(recipient);
        }
        this.subject = subject;
        this.text = text;
    }

    public Email addAttachment(String attachmentFilename, File file) {
        if (file == null || !file.exists()) {
            throw new MailSendException("附件不存在");
        }
        if (!StringUtils.hasText(attachmentFilename)) {
            attachmentFilename = file.getName();
        }

        attachmentMap.put(attachmentFilename, file);
        return this;
    }

    protected void setSender(String sender) {
        this.sender = sender;
    }

    public Email addInline(String contentId, File file) {
        if (!StringUtils.hasText(contentId)) {
            throw new MailSendException("必须指定contentId");
        }

        if (file == null || !file.exists()) {
            throw new MailSendException("文件不存在");
        }

        inlineMap.put(contentId, file);
        return this;
    }

    public Email addAttachment(File file) {
        return addAttachment(null, file);
    }

    public Email setNick(String nick) {
        try {
            this.senderNick = MimeUtility.encodeText(nick, "utf-8", null);
        } catch (UnsupportedEncodingException e) {
        }
        return this;
    }

    public String getNick() {
        return senderNick;
    }

    public void send() {
        MailUtil.sendMail(this);
    }

    public void send(String mailSender) {
        MailUtil.sendMail(this, mailSender);
    }

    public void send(JavaMailSender mailSender) {
        MailUtil.sendMail(this, mailSender);
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, File> getAttachmentMap() {
        return attachmentMap;
    }

    public Map<String, File> getInlineMap() {
        return inlineMap;
    }

    public String getText() {
        return text;
    }
}
