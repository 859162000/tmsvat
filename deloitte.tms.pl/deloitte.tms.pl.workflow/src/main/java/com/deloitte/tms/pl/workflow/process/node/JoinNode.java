package com.deloitte.tms.pl.workflow.process.node;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.expr.ExpressionContext;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.model.ProcessInstanceState;
import com.deloitte.tms.pl.workflow.model.variable.Variable;
import com.deloitte.tms.pl.workflow.query.ProcessVariableQuery;
import com.deloitte.tms.pl.workflow.service.ProcessService;

/**
 * @author Jacky.gao
 * @since 2013年7月31日
 */
public class JoinNode extends Node {
	private static final long serialVersionUID = 1L;
	private int multiplicity;
	private int percentMultiplicity;
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized boolean enter(Context context, ProcessInstance processInstance) {
		Session session=context.getSession();
		processInstance.setState(ProcessInstanceState.End);
		ProcessService processService=context.getProcessService();
		ExpressionContext expressionContext=context.getExpressionContext();
		expressionContext.moveContextToParent(processInstance);
		expressionContext.removeContext(processInstance);
		long parentId=processInstance.getParentId();
		
		ProcessInstance parentProcessInstance=processService.getProcessInstanceById(parentId);
		createActivityHistory(context, parentProcessInstance);
		doEnterEventHandler(context, parentProcessInstance);
		
		ProcessVariableQuery query=processService.createProcessVariableQuery();
		query.processInstanceId(processInstance.getId());
		for(Variable var:query.list()){
			var.setProcessInstanceId(parentId);
			session.update(var);
		}
		session.delete(processInstance);
		List<ProcessInstance> noneCompleteProcessInstances=session.createCriteria(ProcessInstance.class).add(Restrictions.eq("parentId", parentId)).list();
		int parallelCount=processInstance.getParallelInstanceCount();
		int completedCount=parallelCount-noneCompleteProcessInstances.size();
		ProcessDefinition pd=processService.getProcessById(processInstance.getProcessId());
		if(multiplicity>0){
			if(completedCount>=multiplicity){
				for(ProcessInstance pi:noneCompleteProcessInstances){
					Node node=pd.getNode(pi.getCurrentNode());
					node.cancel(context, processInstance);
					session.createQuery("delete "+Variable.class.getName()+" where processInstanceId=:piId").setLong("piId", pi.getId()).executeUpdate();
					processService.deleteProcessInstance(pi);
					node.completeActivityHistory(context, pi,null);
					expressionContext.removeContext(pi);
				}
				return true;
			}
		}else if(percentMultiplicity>0){
			double percent=Double.valueOf(percentMultiplicity)/Double.valueOf(100);
			double alreadyCompletedPercent=(Double.valueOf(completedCount)/Double.valueOf(parallelCount));
			if(alreadyCompletedPercent>=percent){
				for(ProcessInstance pi:noneCompleteProcessInstances){
					Node node=pd.getNode(pi.getCurrentNode());
					node.cancel(context, pi);
					session.createQuery("delete "+Variable.class.getName()+" where processInstanceId=:piId").setLong("piId", pi.getId()).executeUpdate();
					processService.deleteProcessInstance(pi);
					node.completeActivityHistory(context, pi,null);
					expressionContext.removeContext(pi);
				}
				return true;
			}
		}else if(noneCompleteProcessInstances.size()==0){
			return true;
		}
		return false;
	}

	@Override
	public String leave(Context context, ProcessInstance processInstance,String flowName) {
		Session session=context.getSession();
		long parentProcessInstanceId=processInstance.getParentId();
		ProcessInstance parentProcessInstance=(ProcessInstance)session.createCriteria(ProcessInstance.class).add(Restrictions.eq("id", parentProcessInstanceId)).uniqueResult();
		ProcessDefinition pd=context.getProcessService().getProcessById(processInstance.getProcessId());
		Node node=pd.getNode(parentProcessInstance.getCurrentNode());
		node.doLeaveEventHandler(context, parentProcessInstance);
		node.completeActivityHistory(context, parentProcessInstance, flowName);
		parentProcessInstance.setCurrentNode(getName());
		return leaveNode(context, parentProcessInstance, flowName);
	}
	@Override
	public void cancel(Context context, ProcessInstance processInstance) {
	}

	public int getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

	public int getPercentMultiplicity() {
		return percentMultiplicity;
	}

	public void setPercentMultiplicity(int percentMultiplicity) {
		this.percentMultiplicity = percentMultiplicity;
	}
}
