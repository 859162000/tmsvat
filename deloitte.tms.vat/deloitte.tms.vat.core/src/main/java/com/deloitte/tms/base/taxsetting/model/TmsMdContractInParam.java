package com.deloitte.tms.base.taxsetting.model;

import java.math.BigDecimal;
import java.util.List;

public class TmsMdContractInParam extends TmsMdContract {
	private static final long serialVersionUID = 1L;

	private BigDecimal contractAmount;//合同金额

	private String projectName;//项目名称

	private String contractNumber;//合同编码

	private String contractName;//合同名称

	private String projectNumber;//项目编码
	
	private String projData;
	
	private List<TmsMdProject> list;//项目查询集合

	public String getProjData() {
		return projData;
	}

	public void setProjData(String projData) {
		this.projData = projData;
	}

	private BigDecimal projectAmount;//项目金额

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public BigDecimal getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(BigDecimal projectAmount) {
		this.projectAmount = projectAmount;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<TmsMdProject> getList() {
		return list;
	}

	public void setList(List<TmsMdProject> list) {
		this.list = list;
	}
}