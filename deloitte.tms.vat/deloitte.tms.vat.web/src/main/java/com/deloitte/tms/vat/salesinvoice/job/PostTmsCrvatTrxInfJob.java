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
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.dao.CustomerDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdOrgLegalEntityDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.CustBankAccount;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerSite;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.pl.core.commons.constant.PageConstant;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfJobTask;

/**
 * 文件处理job,从接口表中抽取数据到相应的表中
 * 
 * @author stonshi
 * @create 2016年3月19日 下午8:36:34
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("postTmsCrvatTrxInfJob")
public class PostTmsCrvatTrxInfJob implements Job, JobTest {

	@Resource
	private TmsCrvatTrxInfJobTask tmsCrvatTrxInfJob;

	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;
	
	@Resource
	TmsMdLegalEntityDao tmsMdLegalEntityDao;
	
	@Resource
	TmsMdOrgLegalEntityDao tmsMdOrgLegalEntityDao;
	@Resource
	BaseOrgDao baseOrgDao;
	@Resource
	CustomerDao customerDao;
	
	private final static Logger log = Logger.getLogger(PostTmsCrvatTrxInfJob.class);

	/**
	 * @see 详细参考父方法
	 */
	@Override
	public void execute() {
		
		Long totalsapstart = System.currentTimeMillis();
		//缓存需要的文件
		List<TmsMdLegalEntity> allLegalEntities=tmsMdLegalEntityDao.findAllTmsMdLegalEntity();	
//		List<TmsMdOrgLegalEntity> allOrgLegalEntities=tmsMdOrgLegalEntityDao.findAllTmsMdOrgLegalEntities();
		List<BaseOrg> allOrgs=baseOrgDao.findAllBaseOrg();
		List<Customer> allCustomers=getAllCustomer();	
		List<CustomerSite> allListSite=getALLCustomerSite();
		List<CustBankAccount> allCustBankAccount=getAllCustBankAccount();
		/*
		Map<String,BaseOrg> orgMap = new HashMap<String,BaseOrg>();
		for(BaseOrg baseOrg:allOrgs){
			orgMap.put(baseOrg.getOrgCode(), baseOrg);
		}
		Map<String,Customer> customerMap = new HashMap<String,Customer>();
		for(Customer customer:allCustomers){
			customerMap.put(customer.getCustomerNumber(), customer);
		}
		Map<String,CustomerSite> CustomerSiteMap = new HashMap<String,CustomerSite>();
		for(CustomerSite customerSite:allListSite){
			customerMap.put(customer.getCustomerNumber(), customer);
		}
		*/
		
		//分页业务处理
		int pageSize=2000;
		int totalsucess=0;
		int totalsize=0;
		
		//分页查询的时候写数据,导致数据状态在变,所以会丢数据,所以先查询出总数,算出分页数
		int totalNum=tmsCrvatTrxInfDao.findTmsCrvatTrxInfNum(StringPool.READY);
		int pageNum=totalNum/pageSize+1;
		for(int i=0;i<pageNum;i++){
			//分批查询数据
			List<TmsCrvatTrxInf> batchCrvatTrxInfs = tmsCrvatTrxInfDao.findTmsCrvatTrxInf(StringPool.READY,1, pageSize);
			//总数计数
			totalsize=totalsize+batchCrvatTrxInfs.size();
			//执行业务
			int sucessnum = processList(batchCrvatTrxInfs
					,allLegalEntities
					, allOrgs,allCustomers
					,allListSite
					,allCustBankAccount);
			//成功计数
			totalsucess=totalsucess+sucessnum;
		}
		//输出统计日志
		log.info("PostTmsCrvatTrxInfJob "+totalsize+" data costs:："
				+ (System.currentTimeMillis() - totalsapstart) + " ms"
				+" sucess:"+totalsucess+"fail:"+(totalsize-totalsucess));
	}

	private List<CustBankAccount> getAllCustBankAccount() {
		int pageIndex = 1;
		int pageSize = PageConstant.PAGE_SIZE;
		int size = 0;
		List<CustBankAccount> allCustBankAccounts = new ArrayList<CustBankAccount>();
		do {
			List customerList = customerDao.getCustBankAccount(pageIndex, pageSize);
			allCustBankAccounts.addAll(customerList);
			size = customerList.size();
			pageIndex++;
		} while (size == pageSize);
		return allCustBankAccounts;
	}

	private List<CustomerSite> getALLCustomerSite() {
		int pageIndex = 1;
		int pageSize = PageConstant.PAGE_SIZE;
		int size = 0;
		List<CustomerSite> allCustomerSites = new ArrayList<CustomerSite>();
		do {
			List customerList = customerDao.getCustomerSite(pageIndex, pageSize);
			allCustomerSites.addAll(customerList);
			size = customerList.size();
			pageIndex++;
		} while (size == pageSize);
		return allCustomerSites;
	}

	List<Customer> getAllCustomer() {
		int pageIndex = 1;
		int pageSize = PageConstant.PAGE_SIZE;
		int size = 0;
		List<Customer> allCustomers = new ArrayList<Customer>();
		do {
			List customerList = customerDao.getCustomer(pageIndex, pageSize);
			allCustomers.addAll(customerList);
			size = customerList.size();
			pageIndex++;
		} while (size == pageSize);
		return allCustomers;
	}
	
	
	private int processList(
			List<TmsCrvatTrxInf> batchCrvatTrxInfs
			,List<TmsMdLegalEntity> allLegalEntities
//			,List<TmsMdOrgLegalEntity> allOrgLegalEntities,
			,List<BaseOrg> allOrgs
			,List<Customer> allCustomers
			, List<CustomerSite> allListSite
			, List<CustBankAccount> allCustBankAccount) {
		Long sapstart = System.currentTimeMillis();
		int sucessnum=tmsCrvatTrxInfJob.executeTransactionInfDatas(batchCrvatTrxInfs
				,allLegalEntities
				,allOrgs,allCustomers
				,allListSite
				,allCustBankAccount);
		log.info("PostTmsCrvatTrxInfJob "+batchCrvatTrxInfs.size()+" data costs:："
				+ (System.currentTimeMillis() - sapstart) + " ms"
				+" sucess:"+sucessnum+"fail:"+(batchCrvatTrxInfs.size()-sucessnum));
		return sucessnum;
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
