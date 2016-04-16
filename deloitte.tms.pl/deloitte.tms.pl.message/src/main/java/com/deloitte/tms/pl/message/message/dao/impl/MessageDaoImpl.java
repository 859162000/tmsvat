package com.deloitte.tms.pl.message.message.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.message.message.dao.MessageDao;
import com.deloitte.tms.pl.message.message.model.Message;
import com.deloitte.tms.pl.message.message.model.SendInfo;
import com.deloitte.tms.pl.security.model.SecurityUser;

@Component("messageDao")
public class MessageDaoImpl extends BaseDao implements MessageDao{

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public DaoPage findSendInfoList(String username, String businessType, Integer pageIndex, Integer pageSize) {
        StringBuffer hql = new StringBuffer(
                "from SendInfo i where i.isClose=:isClose and i.recipient=:recipient and i.message.bussinessType=:businessType order by isRead,sendDate desc");
        Map values = new HashMap();
        values.put("isClose", false);
        values.put("recipient", username);
        values.put("businessType", businessType);
        return pageBy(hql, values, pageIndex, pageSize);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Message findMessage(Long id) {
        String hql = "from Message e where e.id=:id";
        Map values = new HashMap();
        values.put("id", id);
        List<Message> message = findBy(hql, values);
        if (message.isEmpty()) {
            return null;
        }

        return message.get(0);
    }

    public Long loadMessageNum(String recipient, String businessType) {
        String hql = "select count(*) from SendInfo e where e.isClose=:isClose and e.isRead=false and e.recipient=:recipient and e.message.bussinessType=:bussinessType";
        Map values = new HashMap();
        values.put("isClose", false);
        values.put("recipient", recipient);
        values.put("bussinessType", businessType);
        List<Long> message = findBy(hql, values);
        return message.get(0);
    }

    /**
     * 统计当日日历事件
     * 
     * @param userId
     * @return
     */
    public Long loadMessageNumForCalendar(String userId) {
        String hql = "select count(*) from WorkPlanCustom e where userId=:userId and msgStatus in (0) and year(startTime)=year(getdate()) and month(startTime)=month(getdate()) and day(startTime)=day(getdate())";
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("userId", userId);
        List<Long> message = findBy(hql, values);
        return message.get(0);
    }

    /**
     * 查询当日日历事件
     * 
     * @param userId
     * @return
     */
	public DaoPage loadWorkPlanCustom(String userId, Integer pageIndex, Integer pageSize) {
		StringBuffer hql = new StringBuffer("from WorkPlanCustom e where userId=:userId and msgStatus in (0,1) and year(startTime)=year(getdate()) and month(startTime)=month(getdate()) and day(startTime)=day(getdate()) order by msgStatus,startTime desc");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userId", userId);
		return pageBy(hql, values, pageIndex, pageSize);
	}
	
	/**
	 * 更新日历事件状态
	 * 
	 * @param ids
	 * @param msgStatus
	 */
	public void updateMsgStatus(List<Long> ids, Integer msgStatus) {
		String hql = "update WorkPlanCustom set msgStatus=:msgStatus where id in(:id)";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("id", ids);
		values.put("msgStatus", msgStatus);
		executeHqlProduce(hql, values);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Message> findUnSendMessages() {
        String hql = "from Message e where e.isSend=:isSend";
        Map values = new HashMap();
        values.put("isSend", false);
        List<Message> message = findBy(hql, values);
        return message;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void messageIsRead(Long id, Boolean isRead) {
        String hql = "update SendInfo set isRead=:isRead where id=:id";
        Map values = new HashMap();
        values.put("id", id);
        values.put("isRead", isRead);
        executeHqlProduce(hql, values);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void messageIsClose(List<Long> ids) {
        String hql = "update SendInfo set isClose=:isClose where id in(:id)";
        Map values = new HashMap();
        values.put("id", ids);
        values.put("isClose", true);
        executeHqlProduce(hql, values);
    }

    public void saveMessage(Message message) {
        save(message);
    }

    public void updateMessage(Message message) {
        update(message);
    }

    public void saveSendInfo(SendInfo sendInfo) {
        save(sendInfo);
    }

    public void updateSendInfo(SendInfo sendInfo) {
        update(sendInfo);
    }

    @SuppressWarnings("unchecked")
    public List<SecurityUser> getAllUsers() {
        return find("from DefaultUser");
    }

    private void addUser(List<String> users, String user) {
        if (AssertHelper.notEmpty(user) && !users.contains(user)) {
            users.add(user);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<SecurityUser> getUserByRole(String[] roles) {
        String hql = "from DefaultUser u where u.username in"
                + "(select m.username from RoleMember m where m.roleId = "
                + "(select r.id from DefaultRole r where r.name=:role))";

        List<SecurityUser> result = new ArrayList<SecurityUser>();

        Map values = null;
        for (String role : roles) {
            values = new HashMap();
            values.put("role", role);
            result.addAll(findBy(hql, values));
        }

        return result;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<SecurityUser> getUserByNames(String[] names) {
        String hql = "from DefaultUser u where u.username in (:names)";

        Map values = new HashMap();
        values.put("names", names);
        return findBy(hql, values);
    }
}
