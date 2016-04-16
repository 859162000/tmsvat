package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.HistoryProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class SaveHistoryProcessInstanceCommand implements Command<HistoryProcessInstance> {
	private ProcessInstance processInstance;
	public SaveHistoryProcessInstanceCommand(ProcessInstance processInstance){
		this.processInstance=processInstance;
	}
	public HistoryProcessInstance execute(Context context) {
		Session session=context.getSession();
		HistoryProcessInstance hisProcessInstance=null;
		if(processInstance.getState().equals(ProcessInstanceState.Start)){
			hisProcessInstance=new HistoryProcessInstance();
			hisProcessInstance.setId(processInstance.getHistoryProcessInstanceId());
			hisProcessInstance.setCreateDate(processInstance.getCreateDate());
			hisProcessInstance.setProcessId(processInstance.getProcessId());
			hisProcessInstance.setProcessInstanceId(processInstance.getId());
			hisProcessInstance.setTag(processInstance.getTag());
			hisProcessInstance.setBusinessId(processInstance.getBusinessId());
			hisProcessInstance.setPromoter(processInstance.getPromoter());
			session.save(hisProcessInstance);
		}
		if(processInstance.getState().equals(ProcessInstanceState.End)){
			hisProcessInstance=(HistoryProcessInstance)session.createCriteria(HistoryProcessInstance.class).add(Restrictions.eq("processInstanceId",processInstance.getId())).uniqueResult();
			hisProcessInstance.setEndDate(new Date());
			session.update(hisProcessInstance);
		}
		return hisProcessInstance;
	}

}
