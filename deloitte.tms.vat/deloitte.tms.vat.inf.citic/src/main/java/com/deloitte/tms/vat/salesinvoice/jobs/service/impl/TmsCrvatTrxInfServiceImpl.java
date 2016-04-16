
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
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfService;


/**  
 * TmsCrvatTrxInfService实现类
 * @author stonshi
 * @create 2016年3月19日 下午6:35:45 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SuppressWarnings("rawtypes")
@Component(TmsCrvatTrxInfService.BEAN_ID)
public class TmsCrvatTrxInfServiceImpl extends BaseService implements TmsCrvatTrxInfService {

	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;
	
	/**   
	 * @see 详细参考父方法
	 */
	@Override
	public IDao getDao() {
		return null;
	}

	/**   
	 * @see 详细参考父方法 
	 */
	@Override
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf(Map<String, Object> params) {
		return tmsCrvatTrxInfDao.findTmsCrvatTrxInf(params);
	}


	/**   
	 * @see 详细参考父方法   
	 */
	@Override
	public List<TmsCrvatTrxInf> findTmsCrvatTrxInf() {
		return tmsCrvatTrxInfDao.findTmsCrvatTrxInf();
	}

}
