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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
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
	
	private final static Logger log = Logger.getLogger(PreOrgFileInfJob.class);

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
			executeProcessFile(path, fileType, fileOutPath);
		}

	}

	/**
	 * @see 详细参考父方法定义
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}
	/**
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.OrgFileProcessJobTask#executeProcessFile(java.lang.String,
	 *      java.lang.String)
	 */
	public int executeProcessFile(String filePath, String fileType, String fileOutPath) {

		File file = new File(filePath);

		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完的文件
		map.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		map.put("okFilePath", JobFileUtils.genOkFile(filePath));
		map.put("filePath", filePath);
		map.put("fileOutPath", fileOutPath);
		map.put("fileType", fileType);

		//读取接口文件数据到legalEntityInfs
		List<BaseLegalEntityInf> legalEntityInfs=new ArrayList<BaseLegalEntityInf>();		
		int postcount=processBaseLegalEntityInf(map, file,legalEntityInfs);
		
		/*****************************开始批量处理机构数据**************************/
		int pageIndex=0;
		int pageSize=2000;
		int totalsucess=0;
		
		List<BaseLegalEntityInf> batchBaseLegalEntityInfs=new ArrayList<BaseLegalEntityInf>();
		Long totalsapstart = System.currentTimeMillis();

		for (BaseLegalEntityInf baseLegalEntityInf : legalEntityInfs) {
			if(pageIndex<pageSize){
				batchBaseLegalEntityInfs.add(baseLegalEntityInf);
				pageIndex++;
			}else{
				batchBaseLegalEntityInfs.add(baseLegalEntityInf);
				int sucessnum = fileProcessJob.executeProcessPreOrg(batchBaseLegalEntityInfs);
				//重置计数器
				totalsucess=totalsucess+sucessnum;
				batchBaseLegalEntityInfs=new ArrayList<BaseLegalEntityInf>();					
				pageIndex=0;
			}
		}
		//末尾数据处理
		int sucessnum = fileProcessJob.executeProcessPreOrg(batchBaseLegalEntityInfs);
		totalsucess=totalsucess+sucessnum;
		//处理合计输出
		log.info("PreUserFileInfJob "+legalEntityInfs.size()+" data costs:："
				+ (System.currentTimeMillis() - totalsapstart) + " ms"
				+" sucess:"+totalsucess+"fail:"+(legalEntityInfs.size()-totalsucess));
		/***************************** end 批量处理机构数据**************************/
		//接口文件处理
		generateDoneFileContent(postcount, legalEntityInfs.size()-totalsucess, map);

		processAllFiles(map);

		return postcount;
	}

	/**
	 * 生成处理成功标志文件
	 * @param count2
	 * @param errCount
	 * @param map
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private void generateDoneFileContent(int postCount, int errCount, Map<String, String> map) {
		String tab = "";//分隔符
		String paramTable = map.get("fileType");
		String trxDate = DateUtils.format("yyyyMMdd", new Date());
		String targetSystemCode = "VATS";
		String targetParamTable = "BASE_ORG";
		String postedDate = DateUtils.format("yyyyMMdd HH:mm:ss", new Date());

		StringBuffer sbS = new StringBuffer();
		sbS.append(paramTable).append(tab).append(trxDate).append(tab).append(postCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(postCount-errCount).append(tab).append(postedDate).append(tab).append("S").append(tab).append("SUCCESS");

		StringBuffer sbF = new StringBuffer();
		sbF.append(paramTable).append(tab).append(trxDate).append(tab).append(postCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(errCount).append(tab).append(postedDate).append(tab).append("F").append(tab).append("FAIL");

		try {
			FileOutputStream fos = new FileOutputStream(new File(map.get("doneFilePath")));
			OutputStreamWriter osw = new OutputStreamWriter(fos, StringPool.UTF8_ENCODING);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(sbS + "\n");
			bw.append(sbF);	
			
			FileUtils.safeClose(bw);
			FileUtils.safeClose(osw);
			FileUtils.safeClose(fos);
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 处理后续文件
	 * @param map
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private void processAllFiles(Map<String, String> map) {
		String doneFilePath = map.get("doneFilePath");
		String fileOutPath = map.get("fileOutPath");
		String filePath = map.get("filePath");
		String okFilePath = map.get("okFilePath");
		String trigFilePath = map.get("trigFilePath");
		try {
			FileUtils.moveFile(doneFilePath, fileOutPath);
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.createNewFile(okFilePath, "");
			FileUtils.moveFile(okFilePath, fileOutPath);
			FileUtils.deleteFile(trigFilePath);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 解析前端传来的文件并生成实体
	 * @param map
	 * @param file
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private int processBaseLegalEntityInf(Map<String, String> map, File file,List<BaseLegalEntityInf> legalEntityInfs) {
		
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg para LegalEntityInf from File******************** ");
		int postcount=0;
		int errocount=0;
		int sucessnum=0;

		String lineTxt = null;// 一行字符串定义
		BufferedReader bufferedReader = null;
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			bufferedReader = new BufferedReader(read);
			
			while ((lineTxt = bufferedReader.readLine()) != null) {
				postcount++;
				try {
					BaseLegalEntityInf baseLegalEntityInf = new BaseLegalEntityInf();
					baseLegalEntityInf = convertToEntity(lineTxt);
					baseLegalEntityInf.setInterfaceTrxDate(new Date());// 接口处理日期
					baseLegalEntityInf.setInterfaceTrxFlag("READY");// 接口处理标记
					baseLegalEntityInf.setInterfaceTrxMsg(map.get("doneFilePath"));// 接口处理信息存放处理文件名+第几批
					legalEntityInfs.add(baseLegalEntityInf);
					sucessnum++;
				} catch (Exception e) {
					errocount++;
					log.error("para:"+lineTxt+" erro,info:"+e.getMessage());
					e.printStackTrace();
				}
			}
			FileUtils.safeClose(bufferedReader);
			FileUtils.safeClose(read);
		} catch (Exception e) {			
			log.error("file erro:"+e.getMessage());
			e.printStackTrace();
		}
		log.info("postcount read LegalEntityInf:"+postcount);
		log.info("sucess read LegalEntityInf:"+sucessnum);
		log.info("fail read LegalEntityInf:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end para LegalEntityInf from File******************** ");
		return postcount;
	}


	/**
	 * 组织机构实体转换
	 * 
	 * @param lineTxt
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private BaseLegalEntityInf convertToEntity(String lineTxt) {

		BaseLegalEntityInf baseLegalEntityInf = null;

		try {
			String[] splitLineTxt = lineTxt.split("", -1);
			baseLegalEntityInf = new BaseLegalEntityInf();
			baseLegalEntityInf.setReference1(StringUtils.trim(splitLineTxt[0]));
			baseLegalEntityInf.setLegalEntityId(StringUtils.trim(splitLineTxt[1]));
			baseLegalEntityInf.setLegalEntityLevel(StringUtils.trim(splitLineTxt[2]));
			baseLegalEntityInf.setLegalEntityType(StringUtils.trim(splitLineTxt[3]));
			baseLegalEntityInf.setParentId(StringUtils.trim(splitLineTxt[4]));
			String startDate = StringUtils.trim(splitLineTxt[5]);
			if (JobDateUtils.isValidDate(startDate, "yyyyMMdd")) {
				baseLegalEntityInf.setStartDate(JobDateUtils.dateParse(startDate));
			}
			String endDate = StringUtils.trim(splitLineTxt[6]);
			if (JobDateUtils.isValidDate(endDate, "yyyyMMdd")) {
				baseLegalEntityInf.setEndDate(JobDateUtils.dateParse(endDate));
			}
			String sourceCreationDate = StringUtils.trim(splitLineTxt[7]);
			if (JobDateUtils.isValidDate(sourceCreationDate, "yyyyMMdd")) {
				baseLegalEntityInf.setSourceCreationDate(JobDateUtils.dateParse(sourceCreationDate));
			}
			baseLegalEntityInf.setSourceCreatedBy(StringUtils.trim(splitLineTxt[8]));
			String sourceLastUpdateDate = StringUtils.trim(StringUtils.trim(splitLineTxt[9]));
			if (JobDateUtils.isValidDate(sourceLastUpdateDate, "yyyyMMdd")) {
				baseLegalEntityInf.setSourceLastUpdateDate(JobDateUtils.dateParse(sourceLastUpdateDate));
			}
			baseLegalEntityInf.setSourceLastUpdatedBy(StringUtils.trim(splitLineTxt[10]));
			String sourceDate = StringUtils.trim(splitLineTxt[11]);
			if (JobDateUtils.isValidDate(sourceDate, "yyyyMMdd")) {
				baseLegalEntityInf.setSourceDate(JobDateUtils.dateParse(sourceDate));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return baseLegalEntityInf;
	}
}
