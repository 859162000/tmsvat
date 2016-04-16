package com.deloitte.tms.pl.workflow.process.node;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.process.flow.SequenceFlowImpl;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class ForkNode extends Node {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean enter(Context context, ProcessInstance processInstance) {
		return true;
	}

	@Override
	public String leave(Context context, ProcessInstance processInstance,String flowName) {
		Session session=context.getSession();
		List<SequenceFlowImpl> flows=new ArrayList<SequenceFlowImpl>();
		for(SequenceFlowImpl flow:getSequenceFlows()){
			if(!flow.canExecute(context, processInstance)){
				continue;
			}
			flows.add(flow);
		}
		for(SequenceFlowImpl flow:flows){
			if(!flow.canExecute(context, processInstance)){
				continue;
			}
			ProcessInstance subProcessInstance=new ProcessInstance();
			subProcessInstance.setId(IDGenerator.getInstance().nextId());
			subProcessInstance.setProcessId(getProcessId());
			subProcessInstance.setParentId(processInstance.getId());
			subProcessInstance.setCreateDate(new Date());
			subProcessInstance.setState(ProcessInstanceState.Start);
			subProcessInstance.setRootId(processInstance.getRootId());
			subProcessInstance.setParallelInstanceCount(flows.size());
			subProcessInstance.setPromoter(processInstance.getPromoter());
			subProcessInstance.setHistoryProcessInstanceId(processInstance.getHistoryProcessInstanceId());
			subProcessInstance.setCurrentTask(processInstance.getCurrentTask());
			subProcessInstance.setBusinessId(processInstance.getBusinessId());
			subProcessInstance.setTag(processInstance.getTag());
			session.save(subProcessInstance);
			context.getExpressionContext().createContext(subProcessInstance, null);
			flow.execute(context, subProcessInstance);
			flowName=flow.getName();
		}
		return flowName;
	}
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
	}
}
