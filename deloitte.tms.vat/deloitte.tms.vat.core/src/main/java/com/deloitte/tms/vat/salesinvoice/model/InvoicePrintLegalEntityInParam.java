
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoicePrintPoolH.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月17日 下午12:12:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;



/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月17日 下午12:12:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InvoicePrintLegalEntityInParam{
	/**
	 * 打印终端ID
	 */
	private String equipmentId;
	/**
	 * 打印终端名称
	 */
	private String equipmentName;
	/**
	 * 打印终端编号
	 */
	private String equipmentCode;
	/**
	 * 终端IP
	 */
	private String equipmentIp;
	
	/**
	 * 终端端口号
	 */
	private String equipmentPort;
	
	
	public String getEquipmentIp() {
		return equipmentIp;
	}
	public void setEquipmentIp(String equipmentIp) {
		this.equipmentIp = equipmentIp;
	}
	public String getEquipmentPort() {
		return equipmentPort;
	}
	public void setEquipmentPort(String equipmentPort) {
		this.equipmentPort = equipmentPort;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	
}
