
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfDaoImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.dao.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午8:13:49  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;

/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月19日 下午8:13:49 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(TmsCrvatTrxInfDao.BEAN_ID)
public class TmsCrvatTrxInfDaoImpl extends BaseDao<TmsCrvatTrxInf> implements TmsCrvatTrxInfDao {

	/**   
	 * @param params
	 * @return  
	 * @see 详细参考父方法    
	 */
	@Override
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf(Map<String, Object> params) {
		
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		buildTmsCrvatTrxInfQuery(query, values, params);
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
	private void buildTmsCrvatTrxInfQuery(StringBuffer query,
			Map<String, Object> values, Map<String, Object> params) {
		query.append(" from TmsCrvatTrxInf where 1=1 ");
		Object value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		} 
		value = params.get("interfaceTrxFlag");
		if(AssertHelper.notEmpty(value)) {
			query.append(" and interfaceTrxFlag=:interfaceTrxFlag");
			values.put("interfaceTrxFlag", value);
		}
	}
	
	/**   
	 * @return List<TmsCrvatTrxInf>
	 * @see 详细参考父方法    
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf() {
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatTrxInf where 1=1 ");
		return find(query.toString()); 
	}

	@Override
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf(String ready, int pageIndex,
			int pageSize) {
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatTrxInf where 1=1 ");
		return findByPage(query, new HashMap<String, Object>(), pageIndex, pageSize); 
	}

}
