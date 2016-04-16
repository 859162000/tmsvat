
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午6:29:41  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;


/**  
 * 对TmsCrvatTrxInf实体操作，对表操作
 * @author stonshi
 * @create 2016年3月19日 下午6:29:41 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@SuppressWarnings("rawtypes")
public interface TmsMdCustomersInfService extends IService {
	
	public static final String BEAN_ID="TmsMdCustomersInfService";
	
	/**
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param params
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<TmsMdCustomersInf> findTmsMdCustomersInf(Map<String,Object> params);
	
	/**
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public List<TmsMdCustomersInf> findTmsMdCustomersInf();
}
