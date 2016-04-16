package com.deloitte.tms.pl.workflow.utils;

import com.deloitte.tms.pl.workflow.model.task.Task;

public class TaskTesult {
	boolean statu=false;
	Task currentTask;
	Task nextTask;
	
	public boolean isStatu() {
		return statu;
	}
	public void setStatu(boolean statu) {
		this.statu = statu;
	}
	public Task getCurrentTask() {
		return currentTask;
	}
	public void setCurrentTask(Task currentTask) {
		this.currentTask = currentTask;
	}
	public Task getNextTask() {
		return nextTask;
	}
	public void setNextTask(Task nextTask) {
		this.nextTask = nextTask;
	}
	
}
