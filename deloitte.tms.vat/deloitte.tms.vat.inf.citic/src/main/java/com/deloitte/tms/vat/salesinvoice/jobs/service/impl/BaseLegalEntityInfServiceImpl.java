
/**    
 * Copyright (C),Deloitte
 * @FileName: BaseLegalEntityInfService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月25日 下午2:28:59  
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
import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfService;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月25日 下午2:28:59 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SuppressWarnings("rawtypes")
@Component(BaseLegalEntityInfService.BEAN_ID)
public class BaseLegalEntityInfServiceImpl extends BaseService implements
		BaseLegalEntityInfService {

	@Resource
	private BaseLegalEntityInfDao baseLegalEntityInfDao;
	/**   
	 * @return  
	 * @see com.deloitte.tms.pl.core.service.IService#getDao()  
	 */
	
	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfService#findBaseLegalEntityInf(java.util.Map)  
	 */
	
	@Override
	public List<BaseLegalEntityInf> findBaseLegalEntityInf(
			Map<String, Object> params) {
		return baseLegalEntityInfDao.findBaseLegalEntityInf(params);
	}


	
	/**   
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfService#findBaseLegalEntityInf()  
	 */
	
	@Override
	public List<BaseLegalEntityInf> findBaseLegalEntityInf() {
		// TODO Auto-generated method stub
		return null;
	}

}
