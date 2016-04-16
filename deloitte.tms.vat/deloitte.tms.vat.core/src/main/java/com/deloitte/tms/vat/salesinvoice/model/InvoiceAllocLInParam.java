
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceTrxHInParam.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月18日 下午9:10:43  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author weijia
 * @create 2016年3月18日 下午9:10:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceAllocLInParam extends InvoiceAllocL {
	String equipmentManager;

	public String getEquipmentManager() {
		return equipmentManager;
	}

	public void setEquipmentManager(String equipmentManager) {
		this.equipmentManager = equipmentManager;
	}
}
