package com.deloitte.tms.pl.workflow.command.impl.jump;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jacky.gao
 * @since 2013年9月27日
 */
public class JumpNode {
	private List<String> parent=new LinkedList<String>();
	private int level;
	private String name;
	private boolean isTask;
	public JumpNode(){}
	public JumpNode(String name) {
		this.name = name;
	}

	public boolean isTask() {
		return isTask;
	}

	public void setTask(boolean isTask) {
		this.isTask = isTask;
	}
	public void addParent(String name){
		this.parent.add(name);
	}
	
	public void decreaseParent(){
		int size=parent.size();
		if(size>0){
			this.parent.remove((parent.size()-1));			
		}
	}
	
	public List<String> getParent() {
		return parent;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
