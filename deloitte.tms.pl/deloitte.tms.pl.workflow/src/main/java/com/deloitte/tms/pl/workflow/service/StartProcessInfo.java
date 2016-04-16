package com.deloitte.tms.pl.workflow.service;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
@XmlRootElement(name="StartProcessInfo")
public class StartProcessInfo {
	public static final String KEY=StartProcessInfo.class.getName();
	private String tag;
	private String promoter;
	private String sequenceFlowName;
	private String businessId;
	private Map<String,Object> variables;
	private boolean completeStartTask=true;
	public StartProcessInfo(){
	}
	public StartProcessInfo(String promoter){
		this.promoter=promoter;
	}
	public StartProcessInfo setBusinessId(String businessId){
		this.businessId=businessId;
		return this;
	}
	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}
	public String getBusinessId() {
		return businessId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSequenceFlowName() {
		return sequenceFlowName;
	}
	public void setSequenceFlowName(String sequenceFlowName) {
		this.sequenceFlowName = sequenceFlowName;
	}
	public boolean isCompleteStartTask() {
		return completeStartTask;
	}
	public void setCompleteStartTask(boolean completeStartTask) {
		this.completeStartTask = completeStartTask;
	}
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
