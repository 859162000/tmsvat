/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfJob.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.controller  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午8:36:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEnablePrintDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdOrgLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdUsageLocalLegalDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.pl.core.commons.constant.PageConstant;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfJobTask;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfService;

/**
 * 文件处理job,从接口表中抽取数据到相应的表中
 * 
 * @author stonshi
 * @create 2016年3月19日 下午8:36:34
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component("postOrgInfJob")
public class PostOrgInfJob implements Job, JobTest {
	
	private final static Logger log = Logger.getLogger(PostOrgInfJob.class);

	@Resource
	private BaseLegalEntityInfJobTask baseLegalEntityInfJob;

	@Resource
	private BaseLegalEntityInfService baseLegalEntityInfService;
	
	@Resource
	private TmsMdLegalEntityDao tmsMdLegalEntityDao;

	@Resource 
	private TmsMdLegalEnablePrintDao tmsMdLegalEnablePrintDao;
	
	@Resource
	private BaseOrgDao baseOrgDao;
	
	@Resource
	private TmsMdUsageLocalLegalDao tmsMdUsageLocalLegalDao;
	
	@Resource
	private TmsMdOrgLegalEntityDao tmsMdOrgLegalEntityDao;
	
	/**
	 * @see 详细参考父方法
	 */
	@Override
	public void execute() {
		Long totalsapstart = System.currentTimeMillis();
		log.info("******************************beg process org*****************************");
		
		//获取需要处理的接口数据
		List<BaseLegalEntityInf> listTmsMdCustomersInf = getToRunBaseLegalEntityInf();
		//获取机构相关的缓存数据
		List<TmsMdLegalEntity> allTmsMdLegalEntity = tmsMdLegalEntityDao.findAllTmsMdLegalEntity();
		List<BaseOrg> allBaseOrg = baseOrgDao.findAllBaseOrg();
		List<TmsMdOrgLegalEntity> allTmsMdOrgLegalEntity = tmsMdOrgLegalEntityDao.findAllTmsMdOrgLegalEntities();
		//打印关系
		List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint = tmsMdLegalEnablePrintDao.findAllTmsMdLegalEnablePrint();
		//纳税识别号关系
		List<TmsMdUsageLocalLegal> allTmsMdUsageLocalLegal = tmsMdUsageLocalLegalDao.findAllTmsMdUsageLocalLegal();
		
		
		Map<String , TmsMdLegalEntity> tmsMdLegalEntityMap=new HashMap<String, TmsMdLegalEntity>();		
		Map<String , BaseOrg>baseOrgMap=new HashMap<String, BaseOrg>();	
		Map<String , TmsMdOrgLegalEntity> tmsMdOrgLegalEntityMap_LegalEntity=new HashMap<String, TmsMdOrgLegalEntity>();
//		Map<String , TmsMdOrgLegalEntity> tmsMdOrgLegalEntityMap_Org=new HashMap<String, TmsMdOrgLegalEntity>();
//		
		Map<String , TmsMdLegalEnablePrint> tmsMdLegalEnablePrintMap=new HashMap<String, TmsMdLegalEnablePrint>();
		Map<String , TmsMdUsageLocalLegal> tmsMdUsageLocalLegalMap=new HashMap<String, TmsMdUsageLocalLegal>();

		//LegalEntityCode为key
		for(TmsMdLegalEntity tmsMdLegalEntity: allTmsMdLegalEntity){
			tmsMdLegalEntityMap.put(tmsMdLegalEntity.getLegalEntityCode(), tmsMdLegalEntity);
		}
		//OrgCode为key
		for(BaseOrg baseOrg: allBaseOrg){
			baseOrgMap.put(baseOrg.getOrgCode(), baseOrg);
		}
		//LegalEntityId为key
		for(TmsMdOrgLegalEntity tmsMdOrgLegalEntity: allTmsMdOrgLegalEntity){
			tmsMdOrgLegalEntityMap_LegalEntity.put(tmsMdOrgLegalEntity.getLegalEntityId(), tmsMdOrgLegalEntity);
		}
//		//OrgId为key
//		for(TmsMdOrgLegalEntity tmsMdOrgLegalEntity: allTmsMdOrgLegalEntity){
//			tmsMdOrgLegalEntityMap_Org.put(tmsMdOrgLegalEntity.getOrgId(), tmsMdOrgLegalEntity);
//		}
		//childid为key,找出的数据,更新父id.树由子-父关系建立,所以处理掉这个数据后,树就正确了
		for(TmsMdLegalEnablePrint tmsMdLegalEnablePrint: allTmsMdLegalEnablePrint){
			tmsMdLegalEnablePrintMap.put(tmsMdLegalEnablePrint.getLegalEntityId(), tmsMdLegalEnablePrint);
		}
		//childid为key,找出的数据,更新父id,重建关系并放入map,否则不做任何事情.树由子-父关系建立,所以处理掉这个数据后,树就正确了
		for(TmsMdUsageLocalLegal tmsMdUsageLocalLegal: allTmsMdUsageLocalLegal){
			tmsMdUsageLocalLegalMap.put(tmsMdUsageLocalLegal.getLegalEntityId(), tmsMdUsageLocalLegal);
		}

		/***************************** beg 开始批量处理机构数据**************************/
		int pageIndex=0;
		int pageSize=2000;
		int totalsucess=0;
		//批提交处理数据
		List<BaseLegalEntityInf> batchBaseLegalEntityInfs = new ArrayList<BaseLegalEntityInf>();
		Long start = System.currentTimeMillis();
		for (BaseLegalEntityInf baseLegalEntityInf : listTmsMdCustomersInf) {
			if ((StringPool.READY).equals(baseLegalEntityInf.getInterfaceTrxFlag())) {
				if(pageIndex<pageSize) {
					batchBaseLegalEntityInfs.add(baseLegalEntityInf);
					pageIndex++;
				} else {
					batchBaseLegalEntityInfs.add(baseLegalEntityInf);
					//返回处理成功数量
					int sucessnum =baseLegalEntityInfJob.executeBaseLegalEntityInfDatas(
							batchBaseLegalEntityInfs
							,tmsMdLegalEntityMap
							,baseOrgMap
							,tmsMdOrgLegalEntityMap_LegalEntity
							,tmsMdLegalEnablePrintMap
							,tmsMdUsageLocalLegalMap);
					
					//重置计数器
					totalsucess=totalsucess+sucessnum;
					batchBaseLegalEntityInfs = new ArrayList<BaseLegalEntityInf>();	
					pageIndex = 0;
				}
			}
		}
		//处理末尾数据
		int sucessnum = baseLegalEntityInfJob.executeBaseLegalEntityInfDatas(
				batchBaseLegalEntityInfs
				,tmsMdLegalEntityMap
				,baseOrgMap
				,tmsMdOrgLegalEntityMap_LegalEntity
				,tmsMdLegalEnablePrintMap
				,tmsMdUsageLocalLegalMap);
		totalsucess=totalsucess+sucessnum;
		/***************************** end 批量处理机构数据**************************/
		//处理合计输出
		log.info("PostOrgInfJob "+listTmsMdCustomersInf.size()+" data costs:："
				+ (System.currentTimeMillis() - totalsapstart) + " ms"
				+" sucess:"+totalsucess+"fail:"+(listTmsMdCustomersInf.size()-totalsucess));
		log.info("******************************end processing org *****************************");
	}

	
	
	
//	/** 
//	 * 处理数据
//	 * @param mapProcess
//	 * @return
//	 * @see [相关类/方法]（可选）
//	 * @since [产品/模块版本] （可选）
//	 */
//	
//	private int processList(Map<String, Object> mapProcess) {
//		Long sapstart = System.currentTimeMillis();
//		int sucessnum=baseLegalEntityInfJob.executeBaseLegalEntityInfDatas(mapProcess);
//		@SuppressWarnings("unchecked")
//		List<BaseLegalEntityInf> batchBaseLegalEntityInf = (List<BaseLegalEntityInf>) mapProcess.get("batchBaseLegalEntityInf");
//		log.info("PostOrgInfJob "+batchBaseLegalEntityInf.size()+" data costs:："
//				+ (System.currentTimeMillis() - sapstart) + " ms"
//				+" sucess:"+sucessnum+"fail:"+(batchBaseLegalEntityInf.size()-sucessnum));
//		return sucessnum;
//	}



	/** 
	 * 查出所有相关的全部数据并做缓存
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
//	private Map<String, Object> getAllRelatedObjects() {
//		
//		Long start = System.currentTimeMillis();
//		log.info("$$$$$$$$$$$$$$$$$$$$$$begin to cache data at "+start+"$$$$$$$$$$$$$$$$$$$$$$");
//		List<TmsMdLegalEntity> allTmsMdLegalEntity = tmsMdLegalEntityDao.findAllTmsMdLegalEntity();
//		List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint = tmsMdLegalEnablePrintDao.findAllTmsMdLegalEnablePrint();
//		List<BaseOrg> allBaseOrg = baseOrgDao.findAllBaseOrg();
//		List<TmsMdUsageLocalLegal> allTmsMdUsageLocalLegal = tmsMdUsageLocalLegalDao.findAllTmsMdUsageLocalLegal();
//		List<TmsMdOrgLegalEntity> allTmsMdOrgLegalEntity = tmsMdOrgLegalEntityDao.findAllTmsMdOrgLegalEntities();
//		
//		Map<String,Object> mapProcess = new HashMap<String,Object>();
//		mapProcess.put("allTmsMdLegalEntity", allTmsMdLegalEntity);
//		mapProcess.put("allTmsMdLegalEnablePrint", allTmsMdLegalEnablePrint);
//		mapProcess.put("allBaseOrg", allBaseOrg);
//		mapProcess.put("allTmsMdUsageLocalLegal",allTmsMdUsageLocalLegal);
//		mapProcess.put("allTmsMdOrgLegalEntity", allTmsMdOrgLegalEntity);
//		
//		log.info("$$$$$$$$$$$$$$$$$$$$$$begin to cache data at "+ System.currentTimeMillis()+"$$$$$$$$$$$$$$$$$$$$$$");
//		return mapProcess;
//		
//	}


	/** 
	 * 从接口表中查出所有符合条件的数据
	 * @return 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private List<BaseLegalEntityInf> getToRunBaseLegalEntityInf() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("interfaceTrxFlag", StringPool.READY);
//		map.put("startDate", new Date());// 处理生效日期为当天的
		map.put("legalEntityType", "BILL");
		List<BaseLegalEntityInf> listTmsMdCustomersInf = baseLegalEntityInfService.findBaseLegalEntityInf(map);
		if (listTmsMdCustomersInf.size() > 0) {
			return listTmsMdCustomersInf;
		} else {
			throw new BusinessException("接口表中没有数据或状态不正确，没有数据要执行！");
		}
	}

	/**
	 * @param arg0
	 * @throws JobExecutionException
	 * @see 详细参考父方法
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
