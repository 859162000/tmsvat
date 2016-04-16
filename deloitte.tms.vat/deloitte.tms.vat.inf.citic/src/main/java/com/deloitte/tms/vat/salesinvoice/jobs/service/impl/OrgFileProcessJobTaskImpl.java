/**    
 * Copyright (C),Deloitte
 * @FileName: OrgJobTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午12:50:27  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

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

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.OrgFileProcessJobTask;

/**
 * 组织机构文件处理
 * @author stonshi
 * @create 2016年3月24日 上午12:50:27
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component(OrgFileProcessJobTask.BEAN_ID)
public class OrgFileProcessJobTaskImpl implements OrgFileProcessJobTask {

	private int count;

	@Resource
	private BaseLegalEntityInfDao baseLegalEntityInfDao;

	/**
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.OrgFileProcessJobTask#executeProcessFile(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int executeProcessFile(String filePath, String fileType, String fileOutPath) {

		File file = new File(filePath);

		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}
		
		int errCount = 0;

		Map<String, String> map = new HashMap<String, String>();
		map.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完的文件
		map.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		map.put("okFilePath", JobFileUtils.genOkFile(filePath));
		map.put("filePath", filePath);
		map.put("fileOutPath", fileOutPath);
		map.put("fileType", fileType);

		StringBuilder errInfo = new StringBuilder();

		List<BaseLegalEntityInf> list = processBaseLegalEntityInf(map, file);

		for (BaseLegalEntityInf baseLegalEntityInf : list) {
			try {
				baseLegalEntityInfDao.saveOrUpdate(baseLegalEntityInf);
			} catch (Exception e) {
				errInfo.append(e.getMessage());
				errInfo.append("\n");
				errCount++;
			}
		}
		if (errCount > 0) {
			throw new BusinessException(errCount + "个文件处理失败:" + errInfo.toString());
		}

		generateDoneFileContent(count, errCount, map);

		processAllFiles(map);

		return count;
	}

	/**
	 * 生成处理成功标志文件
	 * @param count2
	 * @param errCount
	 * @param map
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private void generateDoneFileContent(int count2, int errCount, Map<String, String> map) {
		String tab = "";//分隔符
		String paramTable = map.get("fileType");
		int postCount = count;
		String trxDate = DateUtils.format("yyyyMMdd", new Date());
		String targetSystemCode = "VATS";
		String targetParamTable = "BASE_ORG";
		int postedCount = count - errCount;
		int postedErrCount = errCount;
		String postedDate = DateUtils.format("yyyyMMdd HH:mm:ss", new Date());

		StringBuffer sbS = new StringBuffer();
		sbS.append(paramTable).append(tab).append(trxDate).append(tab).append(postCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(postedCount).append(tab).append(postedDate).append(tab).append("S").append(tab).append("SUCCESS");

		StringBuffer sbF = new StringBuffer();
		sbF.append(paramTable).append(tab).append(trxDate).append(tab).append(postCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(postedErrCount).append(tab).append(postedDate).append(tab).append("F").append(tab).append("FAIL");

		try {
			FileOutputStream fos = new FileOutputStream(new File(map.get("doneFilePath")));
			OutputStreamWriter osw = new OutputStreamWriter(fos, StringPool.UTF8_ENCODING);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(sbS + "\n");
			bw.append(sbF);
			bw.close();
			osw.close();
			fos.close();
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
	private List<BaseLegalEntityInf> processBaseLegalEntityInf(Map<String, String> map, File file) {

		String lineTxt = null;// 一行字符串定义
		List<BaseLegalEntityInf> list = new ArrayList<BaseLegalEntityInf>();
		BufferedReader bufferedReader = null;
		InputStreamReader read = null;
		BaseLegalEntityInf baseLegalEntityInf = null;
		try {
			read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			bufferedReader = new BufferedReader(read);
			baseLegalEntityInf = new BaseLegalEntityInf();

			while ((lineTxt = bufferedReader.readLine()) != null) {
				baseLegalEntityInf = convertToEntity(lineTxt);
				baseLegalEntityInf.setInterfaceTrxDate(new Date());// 接口处理日期
				baseLegalEntityInf.setInterfaceTrxFlag("READY");// 接口处理标记
				baseLegalEntityInf.setInterfaceTrxMsg(map.get("doneFilePath") + "_" + count);// 接口处理信息存放处理文件名+第几批
				list.add(baseLegalEntityInf);
				count++;
			}
			bufferedReader.close();
			read.close();

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return list;
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
