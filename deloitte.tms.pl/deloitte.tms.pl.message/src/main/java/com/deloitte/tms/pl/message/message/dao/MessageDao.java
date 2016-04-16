package com.deloitte.tms.pl.message.message.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.message.message.model.Message;
import com.deloitte.tms.pl.message.message.model.SendInfo;

public interface MessageDao {
	
	public void saveMessage(Message message);

	public void updateMessage(Message message);

	public void saveSendInfo(SendInfo sendInfo);

	public void updateSendInfo(SendInfo sendInfo);

	/**
	 * 更新日历事件状态
	 * 
	 * @param ids
	 * @param msgStatus
	 */
	public void updateMsgStatus(List<Long> ids, Integer msgStatus);
	
    public List<Message> findUnSendMessages();

    public void messageIsRead(Long id, Boolean isRead);

    public void messageIsClose(List<Long> ids);
}
