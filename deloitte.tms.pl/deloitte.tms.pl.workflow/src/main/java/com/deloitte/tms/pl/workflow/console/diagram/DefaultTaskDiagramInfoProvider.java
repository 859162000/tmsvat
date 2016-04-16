package com.deloitte.tms.pl.workflow.console.diagram;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.diagram.TaskDiagramInfoProvider;
import com.deloitte.tms.pl.workflow.diagram.TaskInfo;
import com.deloitte.tms.pl.workflow.model.task.TaskType;

/**
 * @author Jacky.gao
 * @since 2013年10月12日
 */
@Component
public class DefaultTaskDiagramInfoProvider implements TaskDiagramInfoProvider {
	@Value("${uflo.disableDefaultTaskDiagramInfoProvider}")
	private boolean disableDefaultTaskDiagramInfoProvider;
	public boolean disable() {
		return disableDefaultTaskDiagramInfoProvider;
	}

	public String getInfo(String nodeName,List<TaskInfo> tasks) {
		StringBuffer sb=new StringBuffer();
		int size=tasks.size();
		if(size>1){
			sb.append("<div style='line-height:26px'><strong>当前节点共产生"+size+"个任务</strong><br>");
			for(int i=0;i<size;i++){
				sb.append("<img src='dorado/res/uflo/icons/tip.png'>任务"+(i+1)+":<br>");
				sb.append(buildTaskInfo(tasks.get(i)));
			}	
			sb.append("</div>");
		}else if(size==1){
			sb.append("<div style='line-height:26px'><strong>当前节点产生的任务信息如下：</strong><br>");
			sb.append(buildTaskInfo(tasks.get(0)));
			sb.append("</div>");
		}
		return sb.toString();
	}
	
	private String buildTaskInfo(TaskInfo task){
		StringBuffer sb=new StringBuffer();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createDate=task.getCreateDate();
		Date endDate=task.getEndDate();
		Date dueDate=task.getDuedate();
		sb.append("&diams;&nbsp;当前任务名称为"+task.getTaskName()+".<br>");
		sb.append("&diams;&nbsp;创建于"+sd.format(createDate)+"");
		if(endDate!=null){
			sb.append(",结束于"+sd.format(endDate)+".<br>");
		}else{
			sb.append(",目前尚未结束.<br>");
		}
		if(dueDate!=null){
			sb.append("&diams;&nbsp;将于"+sd.format(dueDate)+"过期,");
			String dueInfo=null;
			if(endDate==null){
				dueInfo=(dueDate.getTime()>(new Date()).getTime())?"目前<strong><font color='green'>尚未过期</font></strong>":"目前<strong><font color='red'>已过期</font></strong>";				
			}else{
				dueInfo=(dueDate.getTime()>endDate.getTime())?"<strong><font color='green'>任务已在过期前处理</font></strong>":"<strong><font color='red'>任务在过期后才被处理</font></strong>";								
			}
			sb.append(dueInfo+".<br>");
		}else{
			sb.append("&diams;&nbsp;未配置处理时限.<br>");
		}
		TaskType type=task.getType();
		String assignee=task.getAssignee();
		if(type.equals(TaskType.Participative)){
			sb.append("&diams;&nbsp;任务类型为需要领取才能处理的任务，");
			if(StringUtils.isEmpty(assignee)){
				sb.append("目前还无人领取.<br>");
			}else{
				sb.append("已被"+assignee+"领取.<br>");
			}
		}else if(type.equals(TaskType.Countersign) || type.equals(TaskType.Normal)){
			if(type.equals(TaskType.Countersign)){
				sb.append("&diams;&nbsp;任务类型为会签任务，");
			}else{
				sb.append("&diams;&nbsp;任务类型为普通个人任务，");				
			}
			sb.append(assignee+"为处理人.<br>");
		}
		return sb.toString();
	}

	public boolean isDisableDefaultTaskDiagramInfoProvider() {
		return disableDefaultTaskDiagramInfoProvider;
	}

	public void setDisableDefaultTaskDiagramInfoProvider(
			boolean disableDefaultTaskDiagramInfoProvider) {
		this.disableDefaultTaskDiagramInfoProvider = disableDefaultTaskDiagramInfoProvider;
	}

}
