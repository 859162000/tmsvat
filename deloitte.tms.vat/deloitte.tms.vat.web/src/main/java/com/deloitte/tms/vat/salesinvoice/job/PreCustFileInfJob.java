/**    
 * Copyright (C),Deloitte
 * @FileName: PreCustomerInfJob.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.job  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月23日 上午9:47:44  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.job;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.service.CustFileProcessJobTask;

/**
 * 客户变更接口处理Job T+1日3:00左右提供T日数据
 * 文件名:CC+NCBS+yyyymmdd+01,其中CC表明此文件是客户信息变更文件,yyyymmdd是T日
 * 
 * @author stonshi
 * @create 2016年3月23日 上午9:47:44
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("preCustFileInfJob")
public class PreCustFileInfJob implements Job, JobTest {

	@Resource
	private CustFileProcessJobTask custFileProcessTask;

	@Value("${ling2.fileInPath}")
	String fileInPath;

	// C:/stone/projects/VAT/data/cust/inbound/

	@Value("${ling2.fileOutPath}")
	String fileOutPath;

	// C:/stone/projects/VAT/data/cust/outbound/

	@Value("${ling2.trigger}")
	String trigger;

	/**
	 * @see com.deloitte.tms.pl.job.task.JobTest#execute()
	 */
	@Override
	public void execute() {
		String fileType = StringPool.CC;
		List<String> toRunFileName = JobFileUtils.scanAvabileFiles(fileInPath, fileType, trigger);
		if (CollectionUtils.isEmpty(toRunFileName)) {
			throw new BusinessException("没有找到生成的文件");
		}
		for (String toRun : toRunFileName) {
			String path = fileInPath + toRun;
			custFileProcessTask.executeProcessFile(path, fileOutPath);
		}
	}

	/**
	 * @param arg0
	 * @throws JobExecutionException
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		this.execute();
	}

}
