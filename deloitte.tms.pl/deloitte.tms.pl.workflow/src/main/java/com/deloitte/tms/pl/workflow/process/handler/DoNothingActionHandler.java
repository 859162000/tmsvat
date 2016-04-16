
/**    
 * Copyright (C),Deloitte
 * @FileName: DoNothingActionHandler.java  
 * @Package: com.deloitte.tms.pl.workflow.process.handler  
 * @Description: //模块目的、功能描述  
 * @Author bo.wang  
 * @Date 2016年3月17日 下午6:06:51  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.pl.workflow.process.handler;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;


/**  
 * 
 * @author bo.wang
 * @create 2016年3月17日 下午6:06:51 
 * @version 1.0.0
 */
@Component("doNothingActionHandler")
public class DoNothingActionHandler implements ActionHandler{

	
	/**   
	 * @param processInstance
	 * @param context  
	 * @see com.deloitte.tms.pl.workflow.process.handler.ActionHandler#handle(com.deloitte.tms.pl.workflow.model.ProcessInstance, com.deloitte.tms.pl.workflow.env.Context)  
	 */
	
	@Override
	public void handle(ProcessInstance processInstance, Context context) {
		
	}

}
