
/**    
 * Copyright (C),Deloitte
 * @FileName: BaseLegalEntityInfDaoImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.dao.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月25日 下午2:18:27  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.dao.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月25日 下午2:18:27 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(BaseLegalEntityInfDao.BEAN_ID)
public class BaseLegalEntityInfDaoImpl extends BaseDao<BaseLegalEntityInf> implements
		BaseLegalEntityInfDao {

	
	/**   
	 * @param params
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao#findBaseLegalEntityInf(java.util.Map)  
	 */
	
	@Override
	public List<BaseLegalEntityInf> findBaseLegalEntityInf(
			Map<String, Object> params) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		buildBaseLegalEntityInfQuery(query, values, params);
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
	
	private void buildBaseLegalEntityInfQuery(StringBuffer query,
			Map<String, Object> values, Map<String, Object> params) {
		query.append(" from BaseLegalEntityInf where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		value = params.get("interfaceTrxFlag");
		if(value!=null) {
			query.append(" and interfaceTrxFlag=:interfaceTrxFlag");
			values.put("interfaceTrxFlag", value);
		}
		value = params.get("startDate");		
		if(value!=null) {
			Date dateValue=  DateUtils.getSimpleDateNoNanos((Date)params.get("startDate"));
			query.append(" and startDate=:startDate");
			values.put("startDate", dateValue);
		}
		value = params.get("legalEntityType");
		if(value!=null) {
			query.append(" and legalEntityType=:legalEntityType");
			values.put("legalEntityType", value);
		}
	}

	/**   
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao#findBaseLegalEntityInf()  
	 */
	
	@Override
	public List<BaseLegalEntityInf> findBaseLegalEntityInf() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
