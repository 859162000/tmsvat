
/**    
 * Copyright (C),Deloitte
 *  
 * @Description: //文件处理job
 * @Author stonshi  
 * @Date 2016年3月21日 下午9:10:47  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.purchinvoice.job;

import javax.annotation.Resource;

import org.apache.commons.net.ftp.FTPClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.vat.fscsap.jobs.task.SapJobTask;


/**  
 * 用户文件处理job
 * @author tigchen
 * @create 2016年3月21日 下午9:10:47 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("sapJob")
public class SapJob implements Job, JobTest {

	//private static final Logger LOGGER = LoggerFactory.getLogger(FscSapJob.class);
	
	@Resource
	private SapJobTask sapJobTask;
	
	
	/**
	 * @see 详细参考父方法定义  
	 */
	@Override
	public void execute() {
		
		System.out.println("fscsapjob.java > execute() starting ...");
		this.sapJobTask.executeProcessFile("filePath", "fileOutPath");
		FTPClient a;
		}

	/**   
	 * @see 详细参考父方法定义  
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
