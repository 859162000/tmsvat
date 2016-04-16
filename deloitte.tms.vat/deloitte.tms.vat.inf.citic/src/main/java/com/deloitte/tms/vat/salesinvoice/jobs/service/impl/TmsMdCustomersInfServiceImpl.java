
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfServiceImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午6:35:45  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsMdCustomersInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsMdCustomersInfService;


/**  
 * TmsCrvatTrxInfService实现类
 * @author stonshi
 * @create 2016年3月19日 下午6:35:45 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SuppressWarnings("rawtypes")
@Component(TmsMdCustomersInfService.BEAN_ID)
public class TmsMdCustomersInfServiceImpl extends BaseService implements TmsMdCustomersInfService {

	@Resource
	private TmsMdCustomersInfDao tmsMdCustomersInfDao;
			
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.core.service.IService#getDao()  
	 */
	
	@Override
	public IDao getDao() {
		return null;
	}

	
	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.TmsMdCustomersInfService#findTmsMdCustomersInf(java.util.Map)  
	 */
	
	@Override
	public List<TmsMdCustomersInf> findTmsMdCustomersInf(
			Map<String, Object> params) {
		return tmsMdCustomersInfDao.findTmsMdCustomersInf(params);
	}

	
	/**   
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.TmsMdCustomersInfService#findTmsMdCustomersInf()  
	 */
	
	@Override
	public List<TmsMdCustomersInf> findTmsMdCustomersInf() {
		return null;
	}

	

}
