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
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfJobTask;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsMdCustomersInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.socket.CustomersPreUtils;

/**
 * 文件处理job,从接口表中抽取数据到相应的表中
 * 
 * @author stonshi
 * @create 2016年3月19日 下午8:36:34
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("postCustomerInfJob")
public class PostCustomerInfJob implements Job, JobTest {

	@Resource
	private TmsCrvatTrxInfJobTask tmsCrvatTrxInfJob;

	@Resource
	private TmsMdCustomersInfService tmsMdCustomersInfService;

	@Resource
	private CustomerDao customerDao;

	@Value("${ling2.socketHostIP}")
	String socketHostIP;

	@Value("${ling2.socketHostPort}")
	String socketHostPort;

	@Value("${ling2.socketHostTimeout}")
	String socketHostTimeout;

	@Value("${ling2.xmlPath}")
	String socketReturnXmlPath;

	/**
	 * @see 详细参考父方法
	 */
	@Override
	public void execute() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("interfaceTrxFlag", StringPool.READY);
		map.put("startDate", new Date());// 处理生效日期为当天的
		map.put("sourceCustomerCode", StringPool.NCBS);// 针对核心客户
		List<TmsMdCustomersInf> listTmsMdCustomersInf = tmsMdCustomersInfService.findTmsMdCustomersInf(map);
		System.out.println("*there are " + listTmsMdCustomersInf.size() + "listTmsMdCustomersInf ***");
		List<String> customerIds = new ArrayList<String>();
		int count = 0;
		for (TmsMdCustomersInf list : listTmsMdCustomersInf) {
			customerIds.add(list.getCustomerId());
			count++;
		}
		System.out.println("*testing****Socket***");
		Map<String, String> mapSocket = new HashMap<String, String>();
		mapSocket.put("socketHostIP", socketHostIP);
		mapSocket.put("socketHostPort", socketHostPort);
		mapSocket.put("socketHostTimeout", socketHostTimeout);
		mapSocket.put("socketReturnXmlPath", socketReturnXmlPath);
		// 向socket服务器提交请求
		List<Customer> list = CustomersPreUtils.postCustomerSocket(customerIds, count, mapSocket);
		System.out.println("get back socket result" + list.size() + "************");
		customerDao.saveOrUpdateAll(list);
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
