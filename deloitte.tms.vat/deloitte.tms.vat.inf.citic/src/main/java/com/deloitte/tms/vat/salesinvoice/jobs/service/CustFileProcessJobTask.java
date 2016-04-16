
/**    
 * Copyright (C),Deloitte
 * @FileName: CustFileProcessTask.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月23日 上午10:14:00  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service;

/**  
 * 客户信息变更
 * @author stonshi
 * @create 2016年3月23日 上午10:14:00 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface CustFileProcessJobTask {

	public static final String BEAN_ID = "CustFileProcessJobTask";

	/**
	 * 处理文件
	 * @param filePath
	 * @param fileOutPath
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int executeProcessFile(String filePath, String fileOutPath);
}
