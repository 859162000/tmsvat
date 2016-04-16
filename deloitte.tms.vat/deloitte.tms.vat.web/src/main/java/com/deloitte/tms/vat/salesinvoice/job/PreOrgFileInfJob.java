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
import com.deloitte.tms.vat.salesinvoice.jobs.service.OrgFileProcessJobTask;

/**
 * 组织机构文件处理job
 * 
 * @author stonshi
 * @create 2016年3月21日 下午9:10:47
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("preOrgFileInfJob")
public class PreOrgFileInfJob implements Job, JobTest {

	@Resource
	private OrgFileProcessJobTask fileProcessJob;

	/**
	 * 输入文件
	 */
	@Value("${ling2.fileInPath}")
	String fileInPath;

	/**
	 * 输出文件
	 */
	@Value("${ling2.fileOutPath}")
	String fileOutPath;

	@Value("${ling2.trigger}")
	String trigger;

	@Value("${ling2.dayToRun}")
	int dayToRun;

	/**
	 * @see 详细参考父方法定义
	 */
	@Override
	public void execute() {
		
		String fileType = StringPool.ORRLTA;
		// 判断今天是否为5号，如果是5号，则全量文件，不是5号，则增量文件
		// if(JobDateUtils.isRunDayOfMonth(dayToRun)) {//是5号
		// fileType = StringPool.ORRLTA_ALL;
		// } else {
		// fileType = StringPool.ORRLTA_ADD;
		// }
		List<String> toRunFileName = JobFileUtils.scanAvabileFiles(fileInPath, fileType, trigger);
		if (CollectionUtils.isEmpty(toRunFileName)) {
			throw new BusinessException("没有找到生成的文件");
		}
		for (String toRun : toRunFileName) {
			String path = fileInPath + toRun;
			fileProcessJob.executeProcessFile(path, fileType, fileOutPath);
		}

	}

	/**
	 * @see 详细参考父方法定义
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
