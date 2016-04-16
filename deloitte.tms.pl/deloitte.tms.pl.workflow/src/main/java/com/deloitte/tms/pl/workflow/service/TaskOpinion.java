package com.deloitte.tms.pl.workflow.service;

/**
 * @author Jacky.gao
 * @since 2013年9月26日
 */
public class TaskOpinion {
	private String opinion;
	public TaskOpinion(String opinion){
		this.opinion=opinion;
	}
	public String getOpinion() {
		if(opinion==null){
			return opinion;
		}
		if(opinion.length()>250){
			opinion=opinion.substring(0,250);
		}
		return opinion;
	}
}
