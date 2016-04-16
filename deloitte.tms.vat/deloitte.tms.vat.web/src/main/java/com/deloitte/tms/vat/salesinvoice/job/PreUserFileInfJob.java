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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.pl.security.dao.IUserDao;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobFileUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.service.UserFileProcessJobTask;

/**
 * 用户文件处理job
 * 
 * @author stonshi
 * @create 2016年3月21日 下午9:10:47
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Component("preUserFileInfJob")
public class PreUserFileInfJob implements Job, JobTest {

	@Resource
	private UserFileProcessJobTask fileProcessJob;
	@Resource
	BaseOrgDao baseOrgDao;
	@Resource
	IUserDao userDao;
	
	@Resource(name = "ling2.passwordEncoder")
	private PasswordEncoder passwordEncoder;
	
	private final static Logger log = Logger.getLogger(PreUserFileInfJob.class);

	// 输入文件
	@Value("${ling2.fileInPath}")
	String fileInPath;

	// 输出文件
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
		String fileType = StringPool.UAUSBA;
		
		List<String> toRunFileName = JobFileUtils.scanAvabileFiles(fileInPath, fileType, trigger);
		if (CollectionUtils.isEmpty(toRunFileName)) {
			throw new BusinessException("没有找到生成的文件");
		}
		//预加载数据
		Map<String,BaseOrg> orgMap = new HashMap<String,BaseOrg>();
		List<BaseOrg> allOrgs=baseOrgDao.findAllBaseOrg();
		for(BaseOrg baseOrg:allOrgs){
			orgMap.put(baseOrg.getOrgCode(), baseOrg);
		}
		Map<String,BaseUserOrg> userOrgMap = new HashMap<String,BaseUserOrg>();
		List<BaseUserOrg> allUserOrgs= baseOrgDao.findAllBaseUserOrg();
		for(BaseUserOrg baseUserOrg:allUserOrgs){
			userOrgMap.put(baseUserOrg.getUsername(), baseUserOrg);
		}		
		Map<String,DefaultUser> userMap = new HashMap<String,DefaultUser>();
		List<DefaultUser> alluUsers=userDao.loadAllUsers();
		for(DefaultUser user:alluUsers){
			userMap.put(user.getUsername(),user);
		}
		//开始处理文件
		for (String toRun : toRunFileName) {//有可能有多个
			String filePath = fileInPath + toRun;// 路径+文件名
			
			File file = new File(filePath);
			if (!file.isFile() || !file.exists()) {
				throw new BusinessException("原文件异常！");
			}			
			//从文件得到用户信息
			List<DefaultUser> fileUsers=new ArrayList<DefaultUser>();
			
			int postcount=processReadUserInfoFromFile(file,fileUsers, userMap);
			
			/*****************************开始批量处理用户数据**************************/
			int pageIndex=0;
			int pageSize=2000;
			int totalsucess=0;
			
			List<DefaultUser> batchDefaultUsers=new ArrayList<DefaultUser>();
			Long totalsapstart = System.currentTimeMillis();
			for (DefaultUser user : fileUsers) {
				if(pageIndex<pageSize){
					batchDefaultUsers.add(user);
					pageIndex++;
				}else{
					int sucessnum = fileProcessJob.saveBaseUserList(batchDefaultUsers,orgMap,userOrgMap);
					//重置计数器
					totalsucess=totalsucess+sucessnum;
					batchDefaultUsers=new ArrayList<DefaultUser>();					
					pageIndex=0;
				}
			}
			//末尾数据处理
			int sucessnum = fileProcessJob.saveBaseUserList(batchDefaultUsers,orgMap,userOrgMap);
			
			totalsucess=totalsucess+sucessnum;
			//处理合计输出
			log.info("PreUserFileInfJob "+fileUsers.size()+" data costs:："
					+ (System.currentTimeMillis() - totalsapstart) + " ms"
					+" sucess:"+totalsucess+"fail:"+(fileUsers.size()-totalsucess));
			/***************************** end 批量处理用户数据**************************/
			//接口文件处理
			processInfEndFile(fileType, filePath, postcount,fileUsers.size(),totalsucess);
		}

	}
	private void processInfEndFile(String fileType, String filePath,
			int postcount,int totalUserSize,int failSize) {
		Map<String, String> mapFile = new HashMap<String, String>();
		mapFile.put("doneFilePath", JobFileUtils.genDoneFile(filePath));// 生成处理完的文件
		mapFile.put("trigFilePath", JobFileUtils.genTriggerFile(filePath));// 生成触发文件
		mapFile.put("okFilePath", JobFileUtils.genOkFile(filePath));
		mapFile.put("filePath", filePath);
		mapFile.put("fileOutPath", fileOutPath);
		mapFile.put("fileType", fileType);
		generateDoneFileContent(postcount,totalUserSize,failSize,mapFile);
		processAllFiles(mapFile);
	}
	/**
	 * 生成处理完成提示文件，并放到output目录下
	 * 
	 * @param count
	 * @param userErrCount
	 * @param userOrgErrCount
	 * @param mapFile
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private void generateDoneFileContent(int postedCount,int totalUserSize,int failSize, Map<String, String> mapFile) {
		String tab = "";
		String paramTable = mapFile.get("fileType");
		String trxDate = DateUtils.format("yyyyMMdd", new Date());
		String targetSystemCode = "VATS";
		String targetParamTable = "BASE_USER";
		String postedDate = DateUtils.format("yyyyMMdd HH:mm:ss", new Date());

		StringBuffer sbS = new StringBuffer();
		sbS.append(paramTable).append(tab).append(trxDate).append(tab).append(postedCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(postedCount-failSize).append(tab).append(postedDate).append(tab).append("S").append(tab).append("SUCCESS");

		StringBuffer sbF = new StringBuffer();
		sbF.append(paramTable).append(tab).append(trxDate).append(tab).append(postedCount).append(tab).append(targetSystemCode).append(tab)
				.append(targetParamTable).append(tab).append(failSize).append(tab).append(postedDate).append(tab).append("F").append(tab).append("FAIL");

		try {
			FileOutputStream fos = new FileOutputStream(new File(mapFile.get("doneFilePath")));
			OutputStreamWriter osw = new OutputStreamWriter(fos, StringPool.UTF8_ENCODING);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.append(sbS + "\n");
			bw.append(sbF);
			FileUtils.safeClose(bw);
			FileUtils.safeClose(osw);
			FileUtils.safeClose(fos);
		} catch (Exception e) {
			log.info(" process DoneFileContent erro, info:"+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 处理后续文件
	 * 
	 * @param mapFile
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */

	private void processAllFiles(Map<String, String> mapFile) {
		String doneFilePath = mapFile.get("doneFilePath");
		String fileOutPath = mapFile.get("fileOutPath");
		String filePath = mapFile.get("filePath");
		String okFilePath = mapFile.get("okFilePath");
		String trigFilePath = mapFile.get("trigFilePath");
		try {
			FileUtils.moveFile(doneFilePath, fileOutPath);
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.createNewFile(okFilePath, "");
			FileUtils.moveFile(okFilePath, fileOutPath);
			FileUtils.deleteFile(trigFilePath);

		} catch (Exception e) {
			log.info(" process DoneFile move erro, info:"+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 将文本文件解析出并放到List中
	 * 
	 * @param mapFile
	 * @param file
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private int processReadUserInfoFromFile(File file,List<DefaultUser> fileUsers,Map<String,DefaultUser> userMap) {
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg para User from File******************** ");
		String lineTxt = null;// 一行字符串定义
		InputStreamReader read = null;
		BufferedReader bufferedReader = null;
		
		int postcount=0;
		int errocount=0;
		int sucessnum=0;
		try {
			read = new InputStreamReader(new FileInputStream(file), StringPool.UTF8_ENCODING);
			bufferedReader = new BufferedReader(read);
			while ((lineTxt = bufferedReader.readLine()) != null) {
				postcount++;
				try {
					DefaultUser defaultUser = convertToEntity(lineTxt, userMap);
					if (defaultUser != null) {
						fileUsers.add(defaultUser);
						sucessnum++;
					}else{
						errocount++;
					}					
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
		log.info("postcount read user:"+postcount);
		log.info("sucess read user:"+sucessnum);
		log.info("fail read user:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end para User from File******************** ");
		return postcount;
	}
	/**
	 * 用户实体转换
	 * 
	 * @param lineTxt
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private DefaultUser convertToEntity(String lineTxt,Map<String,DefaultUser> userMap) {
		try {
			String[] splitLineTxt = lineTxt.split("", -1);
			String userName=StringUtils.trim(splitLineTxt[1]);
			AssertHelper.notEmpty_assert(userName, "username can't null lineTxt:"+lineTxt);
			DefaultUser defaultUser=userMap.get(userName);
			if(defaultUser==null){
				defaultUser = new DefaultUser();
				defaultUser.setUsername(StringUtils.trim(splitLineTxt[1]));
				defaultUser.setIsnew(true);
			}else{
				defaultUser.setIsnew(false);
			}			
			defaultUser.setUserCode(StringUtils.trim(splitLineTxt[2]));
			defaultUser.setCname(StringUtils.trim(splitLineTxt[3]));
			defaultUser.setEname(StringUtils.trim(splitLineTxt[4]));
			defaultUser.setAttribute1(StringUtils.trim(splitLineTxt[5]));// 人事编号
			defaultUser.setAttribute2(StringUtils.trim(splitLineTxt[6]));// 用户归属分行组织机构代码
			defaultUser.setIndentifyType(StringUtils.trim(splitLineTxt[7])); // 证件类型
			defaultUser.setIndentifyCode(StringUtils.trim(splitLineTxt[8]));// 证件号码
			if ("1".equals(StringUtils.trim(splitLineTxt[9]))) { // 1:男 2：女
				defaultUser.setMale(true);// 性别男
			} else {
				defaultUser.setMale(false);// 性别女
			}
			String birthday = StringUtils.trim(splitLineTxt[10]);
			if (JobDateUtils.isValidDate(birthday, "yyyyMMdd")) {
				defaultUser.setBirthday(JobDateUtils.dateParse(birthday));// 出生日期
			}
			defaultUser.setEducation(StringUtils.trim(splitLineTxt[11]));// 学历
			defaultUser.setAttribute3(StringUtils.trim(splitLineTxt[12]));// 归属机构
			String onboardDate = StringUtils.trim(splitLineTxt[13]);
			if (JobDateUtils.isValidDate(onboardDate, "yyyyMMdd")) {
				defaultUser.setBirthday(JobDateUtils.dateParse(onboardDate));// 入行日期
			}
			defaultUser.setCname(StringUtils.trim(splitLineTxt[14]));// 联系方式内容
			defaultUser.setUserType(StringUtils.trim(splitLineTxt[15]));// 用户类型
			// defaultUser.setAttribute3((StringUtils.trim(splitLineTxt[16])));//用户归属渠道
			defaultUser.setUserAuthType((StringUtils.trim(splitLineTxt[17])));// 用户身份认证方式
			defaultUser.setEmpStatus((StringUtils.trim(splitLineTxt[18])));// 用户状态
			defaultUser.setUserSignedFlag((StringUtils.trim(splitLineTxt[19])));// 用户签到标识
			defaultUser.setSignTerminal((StringUtils.trim(splitLineTxt[20])));// 签到终端
			String signInDate = StringUtils.trim(splitLineTxt[21]);
			if (JobDateUtils.isValidDate(signInDate, "yyyyMMdd")) {
				defaultUser.setSignInDate(JobDateUtils.dateParse(signInDate));// 用户签到日期
			}
			String signInTime = StringUtils.trim(splitLineTxt[22]);
			if (JobDateUtils.isValidDate(signInTime, "HH:mm:ss")) {
				defaultUser.setSignInTime(DateUtils.convertText2Date(signInTime));// 用户签到时间
			}
			String signOutDate = StringUtils.trim(splitLineTxt[23]);
			if (JobDateUtils.isValidDate(signOutDate, "yyyyMMdd")) {
				defaultUser.setSignInDate(JobDateUtils.dateParse(signOutDate));// 用户签退日期
			}
			String signOutTime = StringUtils.trim(splitLineTxt[24]);
			if (JobDateUtils.isValidDate(signOutTime, "HH:mm:ss")) {
				defaultUser.setSignOutTime(DateUtils.convertText2Date(signOutTime));// 用户签退时间
			}
			defaultUser.setTaskCheckFlag(StringUtils.trim(splitLineTxt[25]));// 用户是否完成业务日志勾兑标识
			defaultUser.setLogCheckFlag(StringUtils.trim(splitLineTxt[26]));// 业务日志勾兑复核用户
			defaultUser.setSessionCode(StringUtils.trim(splitLineTxt[27]));// 保存的用户会话号
			defaultUser.setCreatedBy(StringUtils.trim(splitLineTxt[28]));// 建立用户
			defaultUser.setCreatedOrg(StringUtils.trim(splitLineTxt[29]));// 建立机构
			String createDate = StringUtils.trim(splitLineTxt[30]);
			if (JobDateUtils.isValidDate(createDate, "yyyyMMdd")) {
				defaultUser.setCreateDate(JobDateUtils.dateParse(createDate));// 建立日期
			}
			defaultUser.setModifiedBy(StringUtils.trim(splitLineTxt[31]));// 维护用户
			defaultUser.setModifiedOrg(StringUtils.trim(splitLineTxt[32]));// 维护机构
			String modifiedDate = StringUtils.trim(splitLineTxt[33]);
			if (JobDateUtils.isValidDate(modifiedDate, "yyyyMMdd")) {
				defaultUser.setModifiedDate(JobDateUtils.dateParse(modifiedDate));// 维护日期
			}
			// defaultUser.setTimestamp(Timestamp.valueOf(StringUtils.trim(splitLineTxt[34]!=null?splitLineTxt[34]:JobDateUtils.genTimeStamp())));//时间戳
			defaultUser.setAdministrator(false);
			defaultUser.setEnabled(true);
			if (defaultUser.getPassword() == null) {
				defaultUser.setPassword("111111");
			}
			int salt = RandomUtils.nextInt(1000);
			defaultUser.setPassword(passwordEncoder.encodePassword(defaultUser.getPassword(), salt));
			defaultUser.setSalt(String.valueOf(salt));
			defaultUser.setBizOrgCode(StringUtils.trim(splitLineTxt[12]));
			userMap.put(userName, defaultUser);
			return defaultUser;
		} catch (Exception e) {
			log.error("line erro:"+lineTxt);
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @see 详细参考父方法定义
	 */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		this.execute();
	}

}
