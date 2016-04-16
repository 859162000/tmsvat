
/**    
 * Copyright (C),Deloitte
 * @FileName: FileProcessingService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月21日 下午8:06:01  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;

/**  
 * 用户信息文件处理
 * @author stonshi
 * @create 2016年3月21日 下午8:06:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface UserFileProcessJobTask {
	
	public static final String BEAN_ID = "UserFileProcessJobTask";
	
	int saveBaseUserList(List<DefaultUser> fileUsers
			,Map<String, BaseOrg> orgMap
			,Map<String, BaseUserOrg> userOrgMap);
}
