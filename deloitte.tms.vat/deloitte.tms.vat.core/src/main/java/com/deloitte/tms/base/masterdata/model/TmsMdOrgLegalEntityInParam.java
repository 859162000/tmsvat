
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsMdLegalInvoiceInParam.java  
 * @Package: com.deloitte.tms.base.masterdata.model  
 * @Description: //模块目的、功能描述  
 * @Author jasonpu  
 * @Date 2016年3月23日 下午20:11:13  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.deloitte.tms.pl.security.utils.LittleUtils;

/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author jasonpu
 * @create 2016年3月23日 下午20:11:13
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class TmsMdOrgLegalEntityInParam extends TmsMdOrgLegalEntity implements Serializable{
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger(TmsMdOrgLegalEntityInParam.class);
	
	public static ArrayList<String>  needFields = new ArrayList<String>();
	
	static{
		needFields.add("mainC.id");
		needFields.add("mainC.orgId");
		needFields.add("leftC.orgCode");
		needFields.add("leftC.orgName");
		needFields.add("mainC.legalEntityId");
		needFields.add("mainC.legalEntityType");
		needFields.add("mainC.enabledFlag");
		needFields.add("rightC.registrationNumber");
		needFields.add("rightC.legalEntityName");		
	}
	

	private String registrationNumber;
	private String legalEntityName;
	private String orgName;
	private String legalEntityCode;
	private String legalEntityTypeName;
	private String orgCode;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getLegalEntityName() {
		return legalEntityName;
	}
	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLegalEntityCode() {
		return legalEntityCode;
	}
	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}
	public String getLegalEntityTypeName() {
		return legalEntityTypeName;
	}
	public void setLegalEntityTypeName(String legalEntityTypeName) {
		this.legalEntityTypeName = legalEntityTypeName;
	}	
	
	
	
	public TmsMdOrgLegalEntityInParam(){
		
	}
	
	/*
	 * 		String leftC=BaseOrg.class.getName();
		String rightC=TmsMdLegalEntity.class.getName();
		String mainC=TmsMdOrgLegalEntity.class.getName();
		
		sb.append(" select mainC.id, mainC.orgId, leftC.orgCode, leftC.orgName ");
		sb.append(" , mainC.legalEntityId, mainC.legalEntityType , mainC.enabledFlag");
		sb.append(" , rightC.registrationNumber , rightC.legalEntityName");
		
		sb.append(" from leftC, rightC, mainC where ");
	 */
	public TmsMdOrgLegalEntityInParam(Object[] object){
		
		try{
			
		int i=0;
		for(Object obj : object){
					
			String tempStr = needFields.get(i++).split("\\.")[1];
			
			StringBuffer sb = new StringBuffer();
			sb.append("set").append( tempStr.substring(0, 1).toUpperCase() ).append(tempStr.substring(1));
					
			Method method = TmsMdOrgLegalEntityInParam.class.getMethod(sb.toString(), String.class );
			
			if(obj instanceof String){
				
				method.invoke(this, (String)obj );
			}else{
				logger.info( obj + " is not instance of String, please expand the Constructor to support type of o");
			}
			
			this.setFlag(LittleUtils.one);
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}	
		
	}
	
}
