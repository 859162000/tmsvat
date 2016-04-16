package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.LoginConstantDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.CookieUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.dao.IUserDao;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.UserDept;
import com.deloitte.tms.pl.security.model.impl.UserPosition;
import com.deloitte.tms.pl.security.service.IUserService;

/**
 * @since 2013-1-18
 * @author Jacky.gao
 */
@Component(IUserService.BEAN_ID)
public class DefaultUserService extends BaseService implements IUserService {
	@Resource
	IUserDao userDao;
	@Resource(name="ling2.passwordEncoder")
	private PasswordEncoder passwordEncoder;
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AssertHelper.notEmpty_assert(username,"用户名不能为空");
		SecurityUser user= userDao.loadUserByUsername(username);
		if(user==null)
		{
			String erroString="用户名 "+username+"不存在";
			CookieUtils.addCookie(ContextHolder.getRequest(),ContextHolder.getResponse(),LoginConstantDef.LOGIN_ERROKEY,erroString);
			throw new UsernameNotFoundException(erroString);	
		}else {
			if(!user.isEnabled()){
				throw new BusinessException("用户已禁用");
			}
			return user;
		}
	}
	public DaoPage loadPageUsers(Map params,int pageIndex,int pageSize) {
		return userDao.loadPageUsers(params, pageIndex, pageSize);
	}
	public Collection<SecurityUser>  loadUsers(Map params) {
		return userDao.loadUsers(params);
	}
	public Collection<SecurityUser> loadUsersByDeptId(String deptId) {
		return userDao.loadUsersByDeptId(deptId);
	}
	
	public void changePassword(String username, String newPassword) {
		DefaultUser user=(DefaultUser) loadUserByUsername(username);		
		int salt=RandomUtils.nextInt(1000);
		user.setPassword(passwordEncoder.encodePassword(newPassword, salt));
		user.setSalt(String.valueOf(salt));
		userDao.update(user);
	}
	
	public String checkPassword(String username, String password) {
		AssertHelper.notEmpty(username);
		DefaultUser user=(DefaultUser) getByUserName(username);
		AssertHelper.notEmpty_assert(user,"用户没有找到");
		if(!passwordEncoder.isPasswordValid(user.getPassword(),password, user.getSalt())){
			return "密码不正确";
		}else{
			return null;			
		}
	}
	
	public SecurityUser newUserInstance(String username) {
		return new DefaultUser(username);
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	public void registerAdministrator(String username, String cname,
			String ename, String password, String email,String mobile, String companyId) {
		int salt=RandomUtils.nextInt(1000);
		password=passwordEncoder.encodePassword(password, salt);
		DefaultUser user=new DefaultUser();
		user.setUsername(username);
		user.setCname(cname);
		user.setEname(ename);
		user.setPassword(password);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setCompanyId(companyId);
		user.setPassword(password);
		user.setSalt(String.valueOf(salt));
		user.setAdministrator(true);
		userDao.save(user);	
	}
	public void registerCommonUser(String username,String password, String cname,
			String ename, String email,String mobile,String companyId) {
		int salt=RandomUtils.nextInt(1000);
		password=passwordEncoder.encodePassword(password, salt);
		DefaultUser user=new DefaultUser();
		user.setUsername(username);
		user.setCname(cname);
		user.setEname(ename);
		user.setPassword(password);
		user.setEmail(email);
		user.setMobile(mobile);
		user.setCompanyId(companyId);
		user.setPassword(password);
		user.setSalt(String.valueOf(salt));
		userDao.save(user);	
	}
	public void registerCommonUser(DefaultUser user) {
		AssertHelper.notEmpty_assert(user,"注册信息错误,空数据");
		AssertHelper.notEmpty_assert(user.getUsername(),"用户名不能为空");
		int salt=RandomUtils.nextInt(1000);
		String decodepwd=user.getPassword()==null?"111111":user.getPassword();
		String password=passwordEncoder.encodePassword(decodepwd, salt);
		user.setPassword(password);
		user.setSalt(String.valueOf(salt));
		user.setCompanyId("ling2");
		userDao.save(user);	
	}
	@Override
	public IDao getDao() {
		return userDao;
	}
	@Override
	public void saveUsers(Map dataListsMap) {
		Collection<DefaultUser> deleteList = BatchUtils.to(DefaultUser.class).getDeleteEntities(dataListsMap);
		Collection<DefaultUser> insertList =  BatchUtils.to(DefaultUser.class).getInsertEntities(dataListsMap);
		Collection<DefaultUser> updateList =  BatchUtils.to(DefaultUser.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			userDao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			for(DefaultUser user:insertList)
			{
				String salt = String.valueOf(RandomUtils.nextInt(100));
				String password = passwordEncoder.encodePassword(
						user.getPassword(), salt);
				user.setPassword(password);
				user.setSalt(salt);
				userDao.save(user);
			}
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			for(DefaultUser user:deleteList)
			{
				userDao.remove(user);
			}
		}
	}
	@Override
	public String userIsExists(String username) {
		return userDao.userIsExists(username);
	}
	public void insertUserPosition(String username, String ids) {
		Map params=new HashMap();
		params.put("username", username);
		userDao.executeHqlProduce(
					"delete from " + UserPosition.class.getName()
					+ " u where u.username = :username",params);
			
			if (StringUtils.isNotEmpty(ids)) {
				UserPosition userPosition;
				for (String id : ids.split(",")) {
					userPosition = new UserPosition();
					userPosition.setId(UUID.randomUUID().toString());
					userPosition.setPositionId(id);
					userPosition.setUsername(username);
					userDao.save(userPosition);
				}
			}
	}
	public void insertUserDept(String username, String ids) {
		Map params=new HashMap();
		params.put("username", username);
		userDao.executeHqlProduce(
					"delete " + UserDept.class.getName()
					+ " u where u.username = :username",params);
			
			if (StringUtils.isNotEmpty(ids)) {
				UserDept userDept;
				for (String id : ids.split(",")) {
					userDept = new UserDept();
					userDept.setId(UUID.randomUUID().toString());
					userDept.setDeptId(id);
					userDept.setUsername(username);
					userDao.save(userDept);
				}
			}
	}
	@Override
	public SecurityUser getByUserName(String username) {
		AssertHelper.notEmpty_assert(username,"用户名不能为空");
		SecurityUser user= userDao.loadUserByUsername(username);
		return user;
	}
	public SecurityUser loadUserByMobile(String mobile){
		AssertHelper.notEmpty_assert(mobile,"手机号码不能为空");
		SecurityUser user= userDao.loadUserByMobile(mobile);
		return user;
	}
	public SecurityUser loadUserByEmail(String email){
		AssertHelper.notEmpty_assert(email,"邮箱地址不能为空");
		SecurityUser user= userDao.loadUserByEmail(email);
		return user;
	}
	@Override
	public DefaultUser getDefaultUserByUserId(String userId) {
		// TODO Auto-generated method stub
		 return userDao.getDefaultUserByUserId(userId);
	}
	
	@Override
	//public List<Map<String, Object>> loadUsernamesByOrgId(String orgId) {	
	public ArrayList<String> loadUsernamesByOrgId(String orgId) {
		Collection<SecurityUser> userList = userDao.loadUsersByDeptId(orgId);
		
		//ArrayList<Map<String, Object>> usernames=new ArrayList<Map<String, Object>>();
		ArrayList<String> usernames=new ArrayList<String>();
		
		for(SecurityUser user  : userList){
		
			//HashMap map = new HashMap();
			//map.put("username",  user.getUsername());
			
			usernames.add(   user.getUsername()       );
		}
		
		return usernames;
	}
	
}
