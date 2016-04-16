/**    
 * Copyright (C),Deloitte
 * @FileName: FileProcessServiceImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月21日 下午8:10:01  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TrxFileProcessJobTask;

/**
 * 交易池文件处理实现类
 * 
 * @author stonshi
 * @create 2016年3月21日 下午8:10:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component(TrxFileProcessJobTask.BEAN_ID)
public class TrxFileProcessJobTaskImpl implements TrxFileProcessJobTask {
	private final static Logger log = Logger.getLogger(TrxFileProcessJobTaskImpl.class);
	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;
	
	/**
	 * 保存全部TmsCrvatTrxInf
	 * 
	 * @param list
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int processSaveTmsCrvatTrxInf(List<TmsCrvatTrxInf> list) {
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg process tmsCrvatInf ******************** ");
		int sucessnum=0;
		int errocount=0;
		for (TmsCrvatTrxInf tmsCrvatInf : list) {
			try {
				tmsCrvatTrxInfDao.saveOrUpdate(tmsCrvatInf);
				sucessnum++;
			} catch (Exception e) {
				errocount++;
				log.error("process tmsCrvatInf TrxBatchNum:"+tmsCrvatInf.getTrxBatchNum()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("sucess read tmsCrvatInf:"+sucessnum);
		log.info("fail read tmsCrvatInf:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end process tmsCrvatInf ******************** ");
		return sucessnum;
	}
}
