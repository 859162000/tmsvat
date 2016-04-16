/**    
 * Copyright (C),Deloitte
 * @FileName: PreTmsCrvatTrxInfJobController.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.job  
 * @Description: //文件处理job
 * @Author stonshi  
 * @Date 2016年3月21日 下午9:10:47  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.job;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TrxFileProcessJobTask;

/**
 * 文件处理job 针对开票流水，从银行各系统导入VAT系统的接口表中，主要包括以下几个： 1.核心销项开票流水 2.信用卡销项开票流水 3.托管销项开票流水
 * 4.SAP销项开票流水 核心销项开票流水 核心系统 增值税管理系统 "文件名:BT+NCBS+yyyymmdd+01，其中BT表明此文件是开票流水文件
 * yyyymmdd是T日" 以16进制字符“0x03”分割 信用卡销项开票流水 信用卡中心 增值税管理系统
 * "文件名:BT+XACP+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日" 以16进制字符“0x03”分割
 * 托管销项开票流水 托管系统 增值税管理系统 "文件名:BT+CMIS+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日"
 * 以16进制字符“0x03”分割 SAP销项开票流水 SAP 增值税管理系统
 * "文件名:BT+SAPS+yyyymmdd+01，其中BT表明此文件是开票流水文件 yyyymmdd是T日" 以16进制字符“0x03”分割
 * 
 * @author stonshi
 * @create 2016年3月21日 下午9:10:47
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("preTrxFileInfJob")
public class PreTrxFileInfJob implements Job, JobTest {

	@Resource
	private TrxFileProcessJobTask fileProcessJob;

	//输入文件
	@Value("${ling2.fileInPath}")
	String fileInPath;

	// 输出文件
	@Value("${ling2.fileOutPath}")
	String fileOutPath;

	@Value("${ling2.trigger}")
	String trigger;

	@Override
	public void execute() {
		String fileType = StringPool.BT;
		List<String> toRunFileName = JobFileUtils.scanAvabileFiles(fileInPath, fileType, trigger);
		if (CollectionUtils.isEmpty(toRunFileName)) {
			throw new BusinessException("没有找到生成的文件");
		}
		for (String toRun : toRunFileName) {
			String path = fileInPath + toRun;
			fileProcessJob.executeProcessFile(path, fileOutPath);
		}
		
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
