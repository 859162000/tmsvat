/**    
 * Copyright (C),Deloitte
 * @FileName: OrgJobTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午12:50:27  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.OrgFileProcessJobTask;

/**
 * 组织机构文件处理
 * @author stonshi
 * @create 2016年3月24日 上午12:50:27
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component(OrgFileProcessJobTask.BEAN_ID)
public class OrgFileProcessJobTaskImpl implements OrgFileProcessJobTask {
	
	private final static Logger log = Logger.getLogger(OrgFileProcessJobTaskImpl.class);
	
	@Resource
	private BaseLegalEntityInfDao baseLegalEntityInfDao;

	@Override
	public int executeProcessPreOrg(List<BaseLegalEntityInf> legalEntityInfs) {
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg batch process BaseLegalEntityInf ******************** ");
		int sucessnum=0;
		int errocount=0;
		for (BaseLegalEntityInf baseLegalEntityInf : legalEntityInfs) {
			try {//隔离,防止对别的数据影响
				baseLegalEntityInfDao.saveOrUpdate(baseLegalEntityInf);
				sucessnum++;
			} catch (Exception e) {
				errocount++;
				log.error("process BaseLegalEntityInf name:"+baseLegalEntityInf.getLegalEntityName()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("sucess read BaseLegalEntityInf:"+sucessnum);
		log.info("fail read BaseLegalEntityInf:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end batch process BaseLegalEntityInf ******************** ");
		return sucessnum;
	}

	

}
