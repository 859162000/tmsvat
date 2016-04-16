/**    
 * Copyright (C),Deloitte
 * @FileName: PostCreditCardJob.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.job  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月29日 下午1:08:40  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.CustomerDao;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.HttpUtilHelper;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsMdCustomersInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.socket.XmlUtils;

/**
 * 信用卡客户处理
 * 
 * @author stonshi
 * @create 2016年3月29日 下午1:08:40
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component("postCreditCardJob")
public class PostCreditCardJob implements Job, JobTest {

	@Value("${ling2.xmlPath}")
	String xmlPath;

	@Value("${ling2.httpPostInfo}")
	String httpPostInfo;

	@Resource
	private TmsMdCustomersInfService tmsMdCustomersInfService;

	@Resource
	private CustomerDao customerDao;

	/**
	 * @see com.deloitte.tms.pl.job.task.JobTest#execute()
	 */
	@Override
	public void execute() {

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("interfaceTrxFlag", StringPool.READY);
		map.put("startDate", new Date());// 处理生效日期为当天的
		map.put("sourceCustomerCode", StringPool.XACP);
		
		List<TmsMdCustomersInf> listTmsMdCustomersInf = tmsMdCustomersInfService.findTmsMdCustomersInf(map);
		List<String> customerIds = new ArrayList<String>();
		
		for (TmsMdCustomersInf list : listTmsMdCustomersInf) {
			customerIds.add(list.getCustomerId());
		}

		String returnMsg = HttpUtilHelper.http(httpPostInfo, genParams(customerIds));
		System.out.println(returnMsg);
		List<Customer> listCustomer = XmlUtils.extractXACPXmlToEntity(returnMsg, xmlPath);

		customerDao.saveAll(listCustomer);

	}

	/**
	 * 生成customer_id
	 * @param customerIds
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private Map<String, String> genParams(List<String> customerIds) {
		Map<String, String> map = new HashMap<String, String>();
		for (String customerId : customerIds) {
			map.put("customer_id", customerId);
		}
		return map;
	}

	/**
	 * @param arg0
	 * @throws JobExecutionException
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
