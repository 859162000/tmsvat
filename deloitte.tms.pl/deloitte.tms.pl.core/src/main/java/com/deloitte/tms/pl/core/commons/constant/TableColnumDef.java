package com.deloitte.tms.pl.core.commons.constant;

public class TableColnumDef {
	/**
	 * 数据实体通过flag来标示是否逻辑删除,flag=1标示没删除=0标示已经已经删除
	 */
	public static final String FLAG_DEF="DELETED_FLAG";
	public static final String FLAG_EFFECT="1";
	public static final String FLAG_DISABLED="0";
	
	/**
	 * 数据实体通过enable来标示数据是否启用
	 */
	public static final String ENABLE_DEF="enable";
	public static final String ENABLE_EFFECT="1";
	public static final String ENABLE_DISABLED="0";
	
	/**
	 * 数据实体通过status来标示数据工作流状态
	 */
	public static final String STATUS_DEF="status";
	
	public static final String COMPANY_ID_DEF="COMPANY_ID";
	public static final String COMPANY_ID_FILED_DEF="companyId";
}
