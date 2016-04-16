/**    
 * Copyright (C),Deloitte
 * @FileName: CustFileProcessTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月23日 上午10:15:25  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.CustomerDao;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsMdCustomersInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.CustFileProcessJobTask;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月23日 上午10:15:25
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(CustFileProcessJobTask.BEAN_ID)
public class CustFileProcessJobTaskImpl implements CustFileProcessJobTask {

	@Resource
	private CustomerDao customerDao;

	@Resource
	private TmsMdCustomersInfDao tmsMdCustomersInfDao;

	private int convertErrCount;
	
	private int allCount;
	
	private int processErrCount;

	StringBuilder errInfo = new StringBuilder();
	
	/**
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.CustFileProcessJobTask#executeProcessFile(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int executeProcessFile(String filePath, String fileOutPath) {

		File file = new File(filePath);

		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}
		
		int postFileErrCount = 0;
		int saveErrCount = 0;

		

		Map<String, String> mapFile = new HashMap<String, String>();
		mapFile.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完的文件
		mapFile.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		mapFile.put("okFilePath", JobFileUtils.genOkFile(filePath));
		mapFile.put("filePath", filePath);
		mapFile.put("fileOutPath", fileOutPath);

		List<TmsMdCustomersInf> list = processTmsMdCustomersInf(mapFile,file);
		
		saveErrCount = processSaveTmsMdCustomersInf(list);
		
		postFileErrCount = processAllFiles(mapFile);
		
		StringBuffer sbError = new StringBuffer();

		if (saveErrCount > 0 || postFileErrCount > 0 || convertErrCount > 0 || processErrCount > 0) {

			sbError.append("一共有" + allCount + "条数据，");
			sbError.append("保存接口文件有以下错误：");
			sbError.append("\n");
			sbError.append(convertErrCount).append("笔数据转换失败!");
			sbError.append(saveErrCount).append("笔数据保存失败!");
			sbError.append(postFileErrCount).append("笔数文件后续处理失败!");

			throw new BusinessException(sbError.toString());
		}

		return allCount;
	}

	
	
	
	/** 
	 * 保存TmsMdCustomersInf
	 * @param list
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private int processSaveTmsMdCustomersInf(List<TmsMdCustomersInf> list) {
		
		int procSaveErrorCount = 0;
		
		for (TmsMdCustomersInf tmsMdCustomersInf : list) {
			try {
				tmsMdCustomersInfDao.saveOrUpdate(tmsMdCustomersInf);
			} catch (Exception e) {
				errInfo.append(e.getMessage());
				errInfo.append("\n");
				procSaveErrorCount++;
			}
		}
		
		return procSaveErrorCount;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param mapFile
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private int processAllFiles(Map<String, String> mapFile) {
		int postFileErrCount = 0;
		String doneFilePath = mapFile.get("doneFilePath");
		String fileOutPath = mapFile.get("fileOutPath");
		String filePath = mapFile.get("filePath");
		String trigFilePath = mapFile.get("trigFilePath");
		try {
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.deleteFile(doneFilePath);
			FileUtils.deleteFile(trigFilePath);
		} catch (Exception e) {
			postFileErrCount++;
		}
		return postFileErrCount;
	}


	/** 
	 * 处理前端的文本文件
	 * @param mapFile
	 * @param file
	 * @return 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private List<TmsMdCustomersInf> processTmsMdCustomersInf(Map<String, String> mapFile, File file) {
		String lineTxt = null;// 一行字符串定义
		List<TmsMdCustomersInf> list = new ArrayList<TmsMdCustomersInf>();
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			BufferedReader bufferedReader = new BufferedReader(read);
			FileWriter fw = new FileWriter(new File(mapFile.get("doneFilePath")));
			BufferedWriter bw = new BufferedWriter(fw);
			TmsMdCustomersInf tmsMdCustomersInf = new TmsMdCustomersInf();
			while ((lineTxt = bufferedReader.readLine()) != null) {
				tmsMdCustomersInf = convertToEntity(lineTxt);
				if (tmsMdCustomersInf != null) {
					tmsMdCustomersInf.setInterfaceTrxDate(new Date());// 接口处理日期
					tmsMdCustomersInf.setInterfaceTrxFlag("READY");// 接口处理标记
					tmsMdCustomersInf.setInterfaceTrxMsg(mapFile.get("doneFilePath") + "_" + allCount);// 接口处理信息存放处理文件名+第几批
					list.add(tmsMdCustomersInf);
				}
				bw.append(lineTxt + "\n");
				allCount++;
			}
			bufferedReader.close();
			read.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			processErrCount++;
		}
		return list;
	}

	/**
	 * 封装TmsMdCustomersInf实体
	 * 
	 * @param lineTxt
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private TmsMdCustomersInf convertToEntity(String lineTxt) {

		TmsMdCustomersInf tmsMdCustomersInf = null;
		
		try {
			String[] splitLineTxt = lineTxt.split("", -1);
			tmsMdCustomersInf = new TmsMdCustomersInf();
			tmsMdCustomersInf.setSourceCustomerCode(splitLineTxt[0]);
			tmsMdCustomersInf.setCustomerId(splitLineTxt[1]);
			tmsMdCustomersInf.setStartDate(JobDateUtils.dateParse(splitLineTxt[2]));
			tmsMdCustomersInf.setAttribute1(splitLineTxt[3]);
			tmsMdCustomersInf.setAttribute1(splitLineTxt[4]);
		} catch (Exception e) {
			convertErrCount++;
		}
		return tmsMdCustomersInf;
	}

}
