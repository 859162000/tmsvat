
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsMdCustomersInfDaoImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.dao.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月25日 下午2:13:55  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsMdCustomersInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月25日 下午2:13:55 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(TmsMdCustomersInfDao.BEAN_ID)
public class TmsMdCustomersInfDaoImpl extends BaseDao<TmsMdCustomersInf> implements TmsMdCustomersInfDao {

	
	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsMdCustomersInfDao#findTmsMdCustomersInf(java.util.Map)  
	 */
	
	@Override
	public List<TmsMdCustomersInf> findTmsMdCustomersInf(
			Map<String, Object> params) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		buildTmsMdCustomersInfQuery(query, values, params);
		return findBy(query, values);
	}

	
	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param query
	 * @param values
	 * @param params
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private void buildTmsMdCustomersInfQuery(StringBuffer query,
			Map<String, Object> values, Map<String, Object> params) {
		query.append(" from TmsMdCustomersInf where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		
		value = params.get("startDate");
		//String valueStr = value.toString().trim();
		if(AssertHelper.notEmpty(value))
		{
			query.append(" and startDate=:startDate");
			values.put("startDate", value);
		}
	}


	/**   
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsMdCustomersInfDao#findTmsMdCustomersInf()  
	 */
	
	@Override
	public List<TmsMdCustomersInf> findTmsMdCustomersInf() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
