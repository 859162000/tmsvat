package com.deloitte.tms.pl.message.message.service;

import com.deloitte.tms.pl.message.message.model.Message;
import com.deloitte.tms.pl.message.message.model.SendInfo;
import com.deloitte.tms.pl.security.model.SecurityUser;

public interface ISendMessageCallback {
    public void beforeSend(Message message, SendInfo sendInfo, SecurityUser user);

    public void onSuccess(Message message, SendInfo sendInfo, SecurityUser user);

    public void onError(Message message, SendInfo sendInfo, SecurityUser user);
}
