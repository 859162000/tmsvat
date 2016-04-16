package com.deloitte.tms.pl.workflow.command.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.Task;
import com.deloitte.tms.pl.workflow.model.task.TaskAppointor;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年10月20日
 */
public class SaveTaskAppointorCommand implements Command<Object> {
	private Task task;
	private String taskNodeName;
	private String[] assignees;
	public SaveTaskAppointorCommand(Task task,String taskNodeName,String[] assignees){
		this.task=task;
		this.taskNodeName=taskNodeName;
		this.assignees=assignees;
	}
	public Object execute(Context context) {
		Session session=context.getSession();
		Query query=session.createQuery("delete "+TaskAppointor.class.getName()+" where taskNodeName=:nodeName and processInstanceId=:processInstanceId");
		query.setString("nodeName",taskNodeName).setLong("processInstanceId", task.getRootProcessInstanceId()).executeUpdate();
		for(String assignee:assignees){
			TaskAppointor appointor=new TaskAppointor();
			appointor.setId(IDGenerator.getInstance().nextId());
			appointor.setAppointor(task.getAssignee());
			appointor.setOwner(assignee);
			appointor.setAppointorNode(task.getNodeName());
			appointor.setProcessInstanceId(task.getRootProcessInstanceId());
			appointor.setTaskNodeName(taskNodeName);
			session.save(appointor);
		}
		return null;
	}
}
