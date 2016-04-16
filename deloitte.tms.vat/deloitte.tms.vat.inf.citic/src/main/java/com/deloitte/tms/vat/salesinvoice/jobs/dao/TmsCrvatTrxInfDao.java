
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfDao.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.dao  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午8:08:26  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月19日 下午8:08:26 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface TmsCrvatTrxInfDao extends IDao<TmsCrvatTrxInf> {
	
	public static final String BEAN_ID = "TmsCrvatTrxInfDao";
	
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf(Map<String,Object> params);
	
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf();

	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf(String ready, int pageIndex,
			int pageSize);
	
}
