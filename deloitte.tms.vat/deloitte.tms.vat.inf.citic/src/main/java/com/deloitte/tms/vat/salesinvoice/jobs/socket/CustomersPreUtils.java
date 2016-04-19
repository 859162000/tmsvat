/**    
 * Copyright (C),Deloitte
 * @FileName: TalkClient.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.socket  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午10:47:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.socket;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月24日 上午10:47:48
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class CustomersPreUtils {

	private final static Logger log = Logger.getLogger(CustomersPreUtils.class);

	public static List<Customer> postCustomerSocket(List<String> customerIds, int count, Map<String, String> map) {

		List<Customer> customers = new ArrayList<Customer>();

		for (String customerId : customerIds) {
			String message = genCustomerXml(customerId);
			log.info("**generate customer xml file***" + message);
			customers.add(processResult(message, map));
		}

		return customers;
	}

	/**
	 * 〈一句话功能简述〉 功能详细描述
	 * 
	 * @param message
	 * @param map
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private static Customer processResult(String message, Map<String, String> map) {

		String socketHostIP = map.get("socketHostIP");
		String socketHostPort = map.get("socketHostPort");
		String socketHostTimeout = map.get("socketHostTimeout");
		String socketReturnXmlPath = map.get("socketReturnXmlPath");

		String returnMsgGbk = null;
		byte[] returnMsg = null;
		String fullPath = null;
		String fileName = null;
		Customer customer = null;

		try {
			returnMsg = SocketUtils.sendDataToPlatform(socketHostIP, Integer.valueOf(socketHostPort), 5, message.getBytes("GBK"),
					Integer.valueOf(socketHostTimeout));
			
			log.info("***response from data platform is" + returnMsg + "****");
			
			if (returnMsg != null && returnMsg.length > 0) {
				fileName = JobDateUtils.genNameByCurrentTime();
				fullPath = socketReturnXmlPath + "/" + fileName;
				returnMsgGbk = new String(returnMsg, "GBK");
				OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(fullPath));
				if (os != null) {
					BufferedWriter bw = new BufferedWriter(os);
					bw.write(returnMsgGbk);
					bw.close();
				}
				os.close();
				System.out.println("**TESTING WRITE FINISHED,GBK:" + returnMsgGbk);
				customer = XmlUtils.extractXmlToEntity(returnMsgGbk, fullPath);
			}
		} catch (Exception e) {
			log.error("****" + e.getMessage());
		}
		return customer;

	}


	public static String genCustomerXml(String customerId) {

		StringBuffer xmlContent = new StringBuffer();

		String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String startRootElement = "<ROOT>";
		String endRootElement = "</ROOT>";
		String std400imtl = "<std400imtl></std400imtl>";// 报文数据长度
		String std400flag = "<std400flag>N</std400flag>";// 新旧报文标识
		String stdmsgtype = "<stdmsgtype>0100</stdmsgtype>";// 报文类型
		String std400pgqf = "<std400pgqf>N</std400pgqf>";// 多页查询标识
		String std400dauf = "<std400dauf>N</std400dauf>";// 动态授权标志
		String std400revf = "<std400revf>N</std400revf>";// 冲账标志
		String std400hfdf = "<std400hfdf>N</std400hfdf>";// 半自动收费标识
		String std400nfhf = "<std400nfhf>N</std400nfhf>";// 非金融历史记录标识
		String std400macf = "<std400macf>N</std400macf>";// 有无MAC标识
		String stdtermtyp = "<stdtermtyp>VATS</stdtermtyp>";// 请求终端类型
		String stdtermid = "<stdtermid>VATS</stdtermid>";// 终端号
		String stdprocode = "<stdprocode>0101230</stdprocode>";// 交易码
		String stdbankno = "<stdbankno>001</stdbankno>";// 银行号
		String std400usno = "<std400usno>9199</std400usno>";// 用户名
		String std400aqid = "<std400aqid>VATS</std400aqid>";// 发起方系统代码
		String std400rbdt = "<std400rbdt>" + DateUtils.format("yyyyMMdd", new Date()) + "</std400rbdt>";// 发起方系统日期--发起方填写(YYYYMMDD)
		String std400rsts = "<std400rsts>" + JobDateUtils.genTimeStamp() + "</std400rsts>";// 发起方系统时间戳
		String std400bfrl = "<std400bfrl></std400bfrl>";// 修改前数据长度
		String std400inpl = "<std400inpl></std400inpl>";// 输入数据长度

		/*
		 * String listStartRoot = "<LIST>"; String rowStartRoot = "<ROWS>";
		 * String rowEndRoot = "</ROWS>"; String listEndRoot = "</LIST>";
		 */
		StringBuffer std400cuno = new StringBuffer();

		std400cuno.append("<std400cuno>" + customerId + "</std400cuno>");// 客户号

		// String std400cuno = "<std400cuno>"+customerId+"</std400cuno>";
		xmlContent.append(xmlHeader);
		xmlContent.append(startRootElement);
		xmlContent.append(std400imtl).append(std400flag).append(stdmsgtype).append(std400pgqf).append(std400dauf).append(std400revf).append(std400hfdf)
				.append(std400nfhf).append(std400macf).append(stdtermtyp).append(stdtermid).append(stdprocode).append(stdbankno).append(std400usno)
				.append(std400aqid).append(std400rbdt).append(std400rsts).append(std400bfrl).append(std400inpl);

		// xmlContent.append(listStartRoot).append(rowStartRoot);
		xmlContent.append(std400cuno);
		// xmlContent.append(rowEndRoot).append(listEndRoot);

		xmlContent.append(endRootElement);

		return xmlContent.toString();
	}

}
