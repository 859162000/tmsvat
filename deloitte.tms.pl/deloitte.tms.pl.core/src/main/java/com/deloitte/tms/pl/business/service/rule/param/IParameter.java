package com.deloitte.tms.pl.business.service.rule.param;

/**
 * 
* @ClassName: IParameter
* @Description: 程序参数
* @author bo.wang
* @date 2013-3-26 上午10:46:37
*
 */
public interface IParameter {
	
	/**
	 * 
	* @Title: getParamCode
	* @Description: 参数代码 每个handler需要的参数类型都不一样
	* @param @return
	* @return String
	* @throws
	 */
	public String getParamcode();
	public void setParamcode(String paramcode);
	/**
	 * 
	* @Title: getParamLabel
	* @Description: 参数的描述名称
	* @param @return
	* @return String
	* @throws
	 */
	public String getLabel();
	public void setLabel(String label);
	/**
	 * 
	* @Title: getParamValue
	* @Description:参数的值,根绝参数的type来组装
	* @param @return
	* @return String
	* @throws
	 */
	public String getParamvalue();
	public void setParamvalue(String paramvalue);
	/**
	 * 
	* @Title: getParamType
	* @Description: 根据type将value还原为保存的状态
	* @param @return
	* @return String
	* @throws
	 */
	public String getType();
	public void setType(String type);
	/** 
	* @Description: 描述
	* @param @return
	* @return String
	* @throws 
	*/ 
	public String getDes();
	public void setDes(String des);

}
