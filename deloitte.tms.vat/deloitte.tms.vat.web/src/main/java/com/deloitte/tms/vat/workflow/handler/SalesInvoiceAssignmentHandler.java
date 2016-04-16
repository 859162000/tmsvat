
/**    
 * Copyright (C),Deloitte
 * @FileName: SalesInvoiceAssignmentHandler.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.view  
 * @Description: //模块目的、功能描述  
 * @Author bo.wang  
 * @Date 2016年3月17日 下午6:10:55  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.workflow.handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.handler.AssignmentHandler;
import com.deloitte.tms.pl.workflow.process.node.TaskNode;


/**  
 * 
 * @author bo.wang
 * @create 2016年3月17日 下午6:10:55 
 * @version 1.0.0
 */
@Component("salesInvoiceAssignmentHandler")
public class SalesInvoiceAssignmentHandler implements AssignmentHandler {

	/**   
	 * @param taskNode
	 * @param processInstance
	 * @param context
	 * @return  
	 * @see com.deloitte.tms.pl.workflow.process.handler.AssignmentHandler#handle(com.deloitte.tms.pl.workflow.process.node.TaskNode, com.deloitte.tms.pl.workflow.model.ProcessInstance, com.deloitte.tms.pl.workflow.env.Context)  
	 */

	@Override
	public Collection<String> handle(TaskNode taskNode,
			ProcessInstance processInstance, Context context) {
		List<String> results=new ArrayList();
		results.add("admin");
		return results;
	}

}
