package com.deloitte.tms.pl.message.message.service.impl;

import com.deloitte.tms.pl.message.message.model.Message;
import com.deloitte.tms.pl.message.message.model.SendInfo;
import com.deloitte.tms.pl.message.message.service.ISendMessageCallback;
import com.deloitte.tms.pl.security.model.SecurityUser;

public class DefaultSendMessageCallback implements ISendMessageCallback {

    @Override
    public void beforeSend(Message message, SendInfo sendInfo, SecurityUser user) {

    }

    @Override
    public void onSuccess(Message message, SendInfo sendInfo, SecurityUser user) {

    }

    @Override
    public void onError(Message message, SendInfo sendInfo, SecurityUser user) {

    }

}
