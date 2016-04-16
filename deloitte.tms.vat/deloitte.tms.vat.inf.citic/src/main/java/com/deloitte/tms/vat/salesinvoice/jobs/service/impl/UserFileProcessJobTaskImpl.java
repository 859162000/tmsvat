/**    
 * Copyright (C),Deloitte
 * @FileName: UserFileProcessJobTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午10:11:59  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.BaseUserOrg;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.dao.impl.DefaultUserDao;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.salesinvoice.jobs.service.UserFileProcessJobTask;

/**
 * 用户文件处理
 * 
 * @author stonshi
 * @create 2016年3月24日 上午10:11:59
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SuppressWarnings("deprecation")
@Component(UserFileProcessJobTask.BEAN_ID)
public class UserFileProcessJobTaskImpl implements UserFileProcessJobTask {

	@Resource
	private DefaultUserDao defaultUserDao;
	
	private final static Logger log = Logger.getLogger(UserFileProcessJobTaskImpl.class);
	
	public int saveBaseUserList(List<DefaultUser> fileUsers,
			Map<String, BaseOrg> orgMap, Map<String, BaseUserOrg> userOrgMap) {
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg process User ******************** ");
		int sucessnum=0;
		int errocount=0;
		for (DefaultUser defaultUser : fileUsers) {
			try {//隔离,防止对别的数据影响
				if(defaultUser.isIsnew()){
					defaultUserDao.save(defaultUser);
				}else{
					defaultUserDao.update(defaultUser);
				}				
				String orgCode = defaultUser.getAttribute3();
				
				if(AssertHelper.notEmpty(orgCode)){
					BaseUserOrg baseUserOrg = userOrgMap.get(defaultUser.getUsername());
					BaseOrg baseOrg = orgMap.get(orgCode);
					if(baseOrg==null){
						log.error("username:"+defaultUser.getUsername()+"'s org code:"+orgCode+"'s org not found");
					}else{
						if(baseUserOrg==null){	
							baseUserOrg=new BaseUserOrg();
							baseUserOrg.setOrgId(baseOrg.getId());
							baseUserOrg.setUsername(defaultUser.getUsername());
							userOrgMap.put(defaultUser.getUsername(), baseUserOrg);
							defaultUserDao.save(baseUserOrg);
						}else{
							baseUserOrg.setOrgId(baseOrg.getId());
							baseUserOrg.setUsername(defaultUser.getUsername());
							defaultUserDao.update(baseUserOrg);
						}
					}					
				}else{
					log.error("user :"+defaultUser.getUsername()+"'s orgCode can't null");
				}
				sucessnum++;
			} catch (Exception e) {
				errocount++;
				log.error("process user username:"+defaultUser.getUsername()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("sucess read user:"+sucessnum);
		log.info("fail read user:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end process User ******************** ");
		return sucessnum;
	}
}
