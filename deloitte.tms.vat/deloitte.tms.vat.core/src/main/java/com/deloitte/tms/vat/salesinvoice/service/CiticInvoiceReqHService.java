
/**    
 * Copyright (C),Deloitte
 * @FileName: CiticInvoiceReqHService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.service  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年4月10日 下午7:11:45  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.service;

import java.text.ParseException;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPoolInParam;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author sqing
 * @create 2016年4月10日 下午7:11:45 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface CiticInvoiceReqHService extends IService{
	public static final String BEAN_ID="citicInvoiceReqHService";
	public String setUpHead(Map<String, Object>map) throws ParseException;
	public InvoiceTrxPoolInParam convertInvoiceTrxPoolToInParam(InvoiceTrxPool pool);
	public InvoiceTrxPool findInvoiceTrxPoolByParams(Map<String, Object> map);
	public void saveCustomerAndReq(Map<String, Object> map);
}
