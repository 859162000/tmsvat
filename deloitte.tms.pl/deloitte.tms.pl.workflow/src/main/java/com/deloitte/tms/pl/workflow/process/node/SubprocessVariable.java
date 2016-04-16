package com.deloitte.tms.pl.workflow.process.node;

/**
 * @author Jacky.gao
 * @since 2013年8月8日
 */
public class SubprocessVariable implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String inParameterKey;
	private String outParameterKey;
	public SubprocessVariable(String inParameterKey,String outParameterKey){
		this.inParameterKey=inParameterKey;
		this.outParameterKey=outParameterKey;
	}
	public String getInParameterKey() {
		return inParameterKey;
	}
	public void setInParameterKey(String inParameterKey) {
		this.inParameterKey = inParameterKey;
	}
	public String getOutParameterKey() {
		return outParameterKey;
	}
	public void setOutParameterKey(String outParameterKey) {
		this.outParameterKey = outParameterKey;
	}
}
