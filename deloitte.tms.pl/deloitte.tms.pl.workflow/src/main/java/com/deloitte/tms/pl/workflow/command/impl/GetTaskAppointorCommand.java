package com.deloitte.tms.pl.workflow.command.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.task.TaskAppointor;

/**
 * @author Jacky.gao
 * @since 2013年8月19日
 */
public class GetTaskAppointorCommand implements Command<List<TaskAppointor>> {
	private String taskNodeName;
	private long processInstanceId;
	public GetTaskAppointorCommand(String taskNodeName,long processInstanceId){
		this.taskNodeName=taskNodeName;
		this.processInstanceId=processInstanceId;
	}
	@SuppressWarnings("unchecked")
	public List<TaskAppointor> execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(TaskAppointor.class);
		criteria.add(Restrictions.eq("processInstanceId", processInstanceId));
		criteria.add(Restrictions.eq("taskNodeName", taskNodeName));
		return criteria.list();
	}
}
