/**    
 * Copyright (C),Deloitte
 * @FileName: JobFileUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.fileutils  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月28日 下午5:46:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.fileutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月28日 下午5:46:34
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class JobFileUtils {

	private static String trigsuffix = "";
	/**
	 * 遍历是否存在trigger文件
	 * 
	 * @param suffix
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static List<String> scanAvabileFiles(String fileInPath,
			String fileType,String trigger) {
		trigsuffix = trigger;
		File file = new File(fileInPath);
		String[] fileNameArray = file.list();
		List<String> listToRunFileName = new ArrayList<String>();
		if(StringUtils.isNotEmpty(trigger)) {
			for (String fileName : fileNameArray) {
				if (StringUtils.endsWithIgnoreCase(fileName, trigger) && fileName.startsWith(fileType)) {
					String tempName = FileUtils.getFileName(fileName);
					File newFile = new File(tempName);
					if (!newFile.exists()) {
						listToRunFileName.add(tempName);
					}
				}
			}
		} else {
			throw new BusinessException("配置文件为空");
		}
		
		return listToRunFileName;
	}

	public static String genTriggerFile(String filePath) {
		return filePath + "." + trigsuffix;
	}

	public static String genDoneFile(String filePath) {
		String suffix = "VATS.txt";
		String fileName = FileUtils.getFileName(filePath);
		File file = new File(filePath);
		String parent = file.getParent();
		return parent + "/" + fileName + suffix;
	}
	
	public static String genOkFile(String filePath) {
		String suffix = "VATS.txt.OK";
		String fileName = FileUtils.getFileName(filePath);
		File file = new File(filePath);
		String parent = file.getParent();
		return parent + "/" + fileName + suffix;
	}
	
	//处理后续文件
	public static int processAllFiles(Map<String,String> mapFile) {
		
		int postFileErrCount = 0;

		String doneFilePath = mapFile.get("doneFilePath");
		String fileOutPath = mapFile.get("fileOutPath");
		String filePath = mapFile.get("filePath");
		String okFilePath = mapFile.get("okFilePath");
		String trigFilePath = mapFile.get("trigFilePath");
		
		try {
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.deleteFile(trigFilePath);
			if(!StringUtils.isEmpty(okFilePath)) {
				FileUtils.moveFile(doneFilePath, fileOutPath);
				FileUtils.createNewFile(okFilePath, "");
				FileUtils.moveFile(okFilePath, fileOutPath);
			} else {
				FileUtils.deleteFile(doneFilePath);
			}
			
		} catch (IOException e) {
			postFileErrCount++;
		}
		return postFileErrCount;
	}
}
