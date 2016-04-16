package com.deloitte.tms.pl.message.message.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = SendInfo.TABLE)
public class SendInfo extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 7473972114945090653L;
    public static final String TABLE = TablePreDef.MESSAGE + "SendInfo";
    public static final String SEQ = TABLE;

    @Id
    @GenericGenerator(name = SEQ + "_GENERATOR", strategy = Ling2IdGenerator.STRATEGY_NAME, parameters = { @Parameter(name = "pkColumnValue", value = SEQ) })
    @GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ + "_GENERATOR")
    @Column(name = "id", length = 16)
    private Long id;

    @Column(name = "message_id", length = 16)
    private Long messageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Cascade(CascadeType.REFRESH)
    @JoinColumn(name = "message_id", insertable = false, updatable = false)
    private Message message;

    @Column(name = "recipient", length = 100)
    private String recipient;

    @Column(name = "sender", length = 160)
    private String sender;

    @Column(name = "sendDate")
    private Date sendDate;

    @Column(name = "is_Read")
    private Boolean isRead = false;

    @Column(name = "is_Send")
    private Boolean isSend = false;

    @Column(name = "is_Close")
    private Boolean isClose = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "send_status")
    private SendStatus sendStatus = SendStatus.NORMAL;

    @Transient
    private String sendStatusVal;

    public String getSendStatusVal() {
        return getSendStatus().toString();
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

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Boolean getIsSend() {
        return isSend;
    }

    public void setIsSend(Boolean isSend) {
        this.isSend = isSend;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public SendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Boolean getIsClose() {
        return isClose;
    }

    public void setIsClose(Boolean isClose) {
        this.isClose = isClose;
    }

}
