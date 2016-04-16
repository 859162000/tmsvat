
/**    
 * Copyright (C),Deloitte
 * @FileName: SalesInvoiceDecisionHandler.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.view  
 * @Description: //模块目的、功能描述  
 * @Author bo.wang  
 * @Date 2016年3月17日 下午6:05:06  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.workflow.handler;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.handler.DecisionHandler;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePreService;


/**  
 * 
 * @author bo.wang
 * @create 2016年3月17日 下午6:05:06 
 * @version 1.0.0
 */
@Component("salesInvoiceDecisionHandler")
public class SalesInvoiceDecisionHandler implements DecisionHandler{

	
	/**   
	 * @param context
	 * @param processInstance
	 * @return  
	 * @see com.deloitte.tms.pl.workflow.process.handler.DecisionHandler#handle(com.deloitte.tms.pl.workflow.env.Context, com.deloitte.tms.pl.workflow.model.ProcessInstance)  
	 */
	
	@Resource
	InvoicePreService invoicePreService;
	
	@Override
	public String handle(Context context, ProcessInstance processInstance) {
		
//		return "to 已受理";
		return "to 已退回";
	}

}
