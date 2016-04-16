/**    
 * Copyright (C),Deloitte
 * @FileName: FileProcessServiceImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月21日 下午8:10:01  
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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatTrxInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatTrxInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TrxFileProcessJobTask;

/**
 * 交易池文件处理实现类
 * 
 * @author stonshi
 * @create 2016年3月21日 下午8:10:01
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component(TrxFileProcessJobTask.BEAN_ID)
public class TrxFileProcessJobTaskImpl implements TrxFileProcessJobTask {

	@Resource
	private TmsCrvatTrxInfDao tmsCrvatTrxInfDao;
	
	@Resource
	private TmsCrvatTrxInfService tmsCrvatTrxInfService;

	private int allCount;

	private int convertErrCount;

	private int processErrCount;

	StringBuffer sbError = new StringBuffer();

	StringBuffer errInfo = new StringBuffer();

	/**
	 * @see 详细参考说明参考父方法
	 */
	@Override
	public int executeProcessFile(String filePath, String fileOutPath) {

		File file = new File(filePath);
		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}

		Map<String, String> mapFile = new HashMap<String, String>();
		mapFile.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完文件
		mapFile.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		mapFile.put("filePath", filePath);
		mapFile.put("fileOutPath", fileOutPath);

		List<TmsCrvatTrxInf> list = processTmsCrvatTrxInf(mapFile, file);

		int saveErrCount = processSaveTmsCrvatTrxInf(list);
		int postFileErrCount = processAllFiles(mapFile);

		if (validateResult(saveErrCount, postFileErrCount, convertErrCount, processErrCount)) {
			throw new BusinessException(sbError.toString());
			//System.out.print(sbError.toString());
		}

		return allCount;
	}

	/**
	 * 〈一句话功能简述〉 功能详细描述
	 * 
	 * @param saveErrCount
	 * @param postFileErrCount
	 * @param convertErrCount2
	 * @param processErrCount2
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private boolean validateResult(int saveErrCount, int postFileErrCount, int convertErrCount2, int processErrCount2) {

		int i = 0;
		sbError.append("一共有" + allCount + "条数据，");

		if (saveErrCount > 0) {
			sbError.append(saveErrCount).append("笔数据保存失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (postFileErrCount > 0) {
			sbError.append(postFileErrCount).append("笔数文件后续处理失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (convertErrCount > 0) {
			sbError.append(convertErrCount).append("笔数据转换失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (processErrCount > 0) {
			sbError.append(processErrCount).append("笔数据转换失败!");
			sbError.append("\n");
			sbError.append(errInfo);
			sbError.append("\n");
			i++;
		}
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 保存全部TmsCrvatTrxInf
	 * 
	 * @param list
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private int processSaveTmsCrvatTrxInf(List<TmsCrvatTrxInf> list) {

		int saveErrCount = 0;

		for (TmsCrvatTrxInf tmsCrvatInf : list) {
			try {
				tmsCrvatTrxInfDao.saveOrUpdate(tmsCrvatInf);
				//tmsCrvatTrxInfService.saveOrUpdate(tmsCrvatInf);
			} catch (Exception e) {
				errInfo.append(e.getMessage());
				errInfo.append("\n");
				saveErrCount++;
			}
		}
		return saveErrCount;
	}

	/**
	 * 处理前端传来的文件
	 * 
	 * @param mapFile
	 * @param file
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private List<TmsCrvatTrxInf> processTmsCrvatTrxInf(Map<String, String> mapFile, File file) {

		String lineTxt = null;// 一行字符串定义
		InputStreamReader read = null;
		BufferedReader br = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		TmsCrvatTrxInf tmsCrvatTrxInf = new TmsCrvatTrxInf();
		List<TmsCrvatTrxInf> list = new ArrayList<TmsCrvatTrxInf>();

		try {
			read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			br = new BufferedReader(read);
			fw = new FileWriter(new File(mapFile.get("doneFilePath")));
			bw = new BufferedWriter(fw);

			while ((lineTxt = br.readLine()) != null) {
				tmsCrvatTrxInf = convertToEntity(lineTxt);
				if (tmsCrvatTrxInf != null) {
					tmsCrvatTrxInf.setInterfaceTrxDate(new Date());// 接口处理日期
					tmsCrvatTrxInf.setInterfaceTrxFlag(StringPool.READY);// 接口处理标记
					tmsCrvatTrxInf.setInterfaceTrxMsg(mapFile.get("doneFilePath") + "_" + allCount);// 接口处理信息存放处理文件名+第几批
					list.add(tmsCrvatTrxInf);
				}
				bw.append(lineTxt + "\n");
				allCount++;
			}
			bw.close();
			fw.close();
			br.close();
			read.close();
		} catch (Exception e) {
			errInfo.append(e.getMessage());
			errInfo.append("\n");
			processErrCount++;
		}
		return list;
	}

	/**
	 * 处理后续文件
	 * 
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
		} catch (IOException e) {
			errInfo.append(e.getMessage());
			errInfo.append("\n");
			postFileErrCount++;
		}
		return postFileErrCount;
	}

	/**
	 * 装载数据到TmsCrvatTrxInf实体
	 * 
	 * @param lineTxt
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private TmsCrvatTrxInf convertToEntity(String lineTxt) {

		TmsCrvatTrxInf tmsCrvatTrxInf = new TmsCrvatTrxInf();;

		try {
			String[] splitLineTxt = lineTxt.split("", -1);
			tmsCrvatTrxInf.setOrgCode(StringUtils.trim(splitLineTxt[0])); // 机构号
			tmsCrvatTrxInf.setOrgName(StringUtils.trim(splitLineTxt[0])); // 机构名称
			tmsCrvatTrxInf.setSourceCode(StringUtils.trim(splitLineTxt[1])); // 来源系统
			tmsCrvatTrxInf.setTrxBatchNum(StringUtils.trim(splitLineTxt[2])); // 交易批次号
			tmsCrvatTrxInf.setTrxNumber(StringUtils.trim(splitLineTxt[3])); // 交易流水号
			tmsCrvatTrxInf.setTrxDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[4])));// 交易日期
			Double oriCurrencyAmount = Double.valueOf(StringUtils.trim(splitLineTxt[5]));// 交易金额
			if (oriCurrencyAmount < 0) {
				tmsCrvatTrxInf.setOriginalCurrencyAmount(-1 * oriCurrencyAmount);
			} else {
				tmsCrvatTrxInf.setOriginalCurrencyAmount(oriCurrencyAmount);
			}
			tmsCrvatTrxInf.setOriginalCurrencyCode(StringUtils.trim(splitLineTxt[6]));// 原币币种
			tmsCrvatTrxInf.setCurrencyCode(StringUtils.trim(splitLineTxt[7]));// 本位币
			Double currencyAmount = Double.valueOf(StringUtils.trim(splitLineTxt[8]));// 本位币交易金额
			if (currencyAmount < 0) {
				tmsCrvatTrxInf.setCurrencyAmount(-1 * currencyAmount);
			} else {
				tmsCrvatTrxInf.setCurrencyAmount(currencyAmount);
			}
			tmsCrvatTrxInf.setExchangeRate(Double.valueOf(StringUtils.trim(splitLineTxt[9])));// 开票汇率
			tmsCrvatTrxInf.setRateDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[10])));// 汇率日期
			tmsCrvatTrxInf.setTaxRate(Double.valueOf(StringUtils.trim(splitLineTxt[11])));// 税率
			tmsCrvatTrxInf.setInvoiceCategory(StringUtils.trim(splitLineTxt[12])); // 交易开票规则
			tmsCrvatTrxInf.setDeptId(StringUtils.trim(splitLineTxt[13])); // 部门
			tmsCrvatTrxInf.setInventoryItemNumber(StringUtils.trim(splitLineTxt[14])); // 商品及服务分类编码货物或应税劳务名称
			tmsCrvatTrxInf.setInventoryItemDescripton(StringUtils.trim(splitLineTxt[14])); // 商品及服务分类编码货物或应税劳务名称
			tmsCrvatTrxInf.setInventoryItemModels(StringUtils.trim(splitLineTxt[15]));
			tmsCrvatTrxInf.setUomCode(StringUtils.trim(splitLineTxt[16]));
			tmsCrvatTrxInf.setInventoryItemQty(Integer.valueOf(StringUtils.trim(splitLineTxt[17]))); // 数量
			tmsCrvatTrxInf.setPriceOfUnit(Double.valueOf(StringUtils.trim(splitLineTxt[18])));// 单价
			tmsCrvatTrxInf.setCustomerAccount(StringUtils.trim(splitLineTxt[19]));// 客户账号
			//tmsCrvatTrxInf.setCustomerAccountLeCode(StringUtils.trim(splitLineTxt[20]));//客户账号开户机构号
			tmsCrvatTrxInf.setCustomerNumber(StringUtils.trim(splitLineTxt[20])); // 客户号
			tmsCrvatTrxInf.setCustomerName(StringUtils.trim(splitLineTxt[21]));// 客户名称
			tmsCrvatTrxInf.setCustomerType(StringUtils.trim(splitLineTxt[22]));// 客户类型
			tmsCrvatTrxInf.setLegalEntityType(StringUtils.trim(splitLineTxt[23]));// 纳税人类型
			tmsCrvatTrxInf.setRegistrationNumber(StringUtils.trim(splitLineTxt[24]));// 纳税人识别号
			tmsCrvatTrxInf.setCustBankBranchName(StringUtils.trim(splitLineTxt[25]));// 纳税人开户行名称
			tmsCrvatTrxInf.setCustBankAccountNum(StringUtils.trim(splitLineTxt[26]));// 纳税人账号
			tmsCrvatTrxInf.setCustRegistrationAddress(StringUtils.trim(splitLineTxt[27]));// 纳税人地址
			tmsCrvatTrxInf.setCustContactPhone(StringUtils.trim(splitLineTxt[28]));// 纳税人联系电话
			tmsCrvatTrxInf.setPrintPeriodName(StringUtils.trim(splitLineTxt[29]));// 打印频率
			tmsCrvatTrxInf.setInvoicingType(StringUtils.trim(splitLineTxt[30]));// 开票方式
			tmsCrvatTrxInf.setRecipientName(StringUtils.trim(splitLineTxt[31]));// 收件人姓名
			tmsCrvatTrxInf.setRecipientComp(StringUtils.trim(splitLineTxt[32]));// 收件人公司
			tmsCrvatTrxInf.setRecipientAddr(StringUtils.trim(splitLineTxt[33]));// 收件人地址
			tmsCrvatTrxInf.setRecipientPhone(StringUtils.trim(splitLineTxt[34]));// 收件人电话
			tmsCrvatTrxInf.setAutoreverseFlag(StringUtils.trim(splitLineTxt[35]));// 明细冲账标识
			tmsCrvatTrxInf.setReverseTypeCode(StringUtils.trim(splitLineTxt[36]));// 冲账类型
			tmsCrvatTrxInf.setReversalDate(JobDateUtils.dateParse(StringUtils.trim(splitLineTxt[37])));// 被冲/冲账交易日期
			tmsCrvatTrxInf.setReversalTrxNumber(StringUtils.trim(splitLineTxt[38]));// 冲账交易日志号
			tmsCrvatTrxInf.setAppointInvoiceOrgCode(StringUtils.trim(splitLineTxt[39]));// 预约开票管理机构
			tmsCrvatTrxInf.setAttribute1(StringUtils.trim(splitLineTxt[40]));
			tmsCrvatTrxInf.setAttribute2(StringUtils.trim(splitLineTxt[41]));
			tmsCrvatTrxInf.setIsAccount("Y");
			return tmsCrvatTrxInf;
		} catch (Exception e) {
			errInfo.append(e.getMessage());
			errInfo.append("\n");
			convertErrCount++;
			return null;
		}
		
	}
}
