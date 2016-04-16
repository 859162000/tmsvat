package com.deloitte.tms.pl.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;

/**
 * @since 2013-1-18
 * @author Jacky.gao
 */
public interface IUserService extends UserDetailsService,IService {
	public static final String BEAN_ID="ling2.userService";
	/**
	 * 分页加载用户数据
	 * @param page Dorado7分页对象，其中包含pageNo,pageSize，分页后的数据也填充到这个page对象当中，该参数不可为空
	 * @param companyId 要加载哪个companyId下的用户信息，该参数不可为空
	 * @param criteria Dorado7条件对象，可从中取到相应的条件值，该参数可为空
	 */
	public DaoPage loadPageUsers(Map params,int pageIndex,int pageSize);
	public Collection<SecurityUser>  loadUsers(Map params);
	
	/**
	 * 加载指定部门ID下的用户信息
	 * @param deptId 隶属的部门ID，该参数不可为空
	 * @return 返回取到的用户集合
	 */
	Collection<SecurityUser> loadUsersByDeptId(String deptId);
	
	/**
	 * 检查用户密码是否正确，如果不正确返回错误消息，如正确则返回null
	 * @param username 用户名
	 * @param password 要检查未加密的密码
	 * @return 不正确返回错误消息，如正确则返回null
	 */
	String checkPassword(String username,String password);
	
	/**
	 * 修改指定用户的密码信息
	 * @param username 用户名
	 * @param newPassword 新密码
	 */
	void changePassword(String username,String newPassword);
	
	/**
	 * 注册一个系统管理员账号
	 * @param username 用于登录的用户名
	 * @param cname 中文名
	 * @param ename 英文名
	 * @param password 密码
	 * @param mobile 手机号	
	 * @param email 电子邮件
	 * @param companyId 所在公司ID
	 */
	void registerAdministrator(String username,String cname,String ename,String password,String email,String mobile,String companyId);
	void registerCommonUser(String username,String password, String cname,
			String ename, String email,String mobile,String companyId);
	public void registerCommonUser(DefaultUser user);
	/**
	 * 根据用户名，实现化一个空的用户对象供系统使用，实例化的用户对象，只需要将给定的用户名填充进去即可
	 * @param username 用户名
	 * @return 实例化后的用户对象
	 */
	SecurityUser newUserInstance(String username);
	
	SecurityUser getByUserName(String username);
	public SecurityUser loadUserByMobile(String mobile);
	public SecurityUser loadUserByEmail(String email);
	
	void saveUsers(Map allrecodsMap);
	public String userIsExists(String username);
	public void insertUserPosition(String username, String ids);
	public void insertUserDept(String username, String ids);
	public DefaultUser getDefaultUserByUserId(String userId);
	public ArrayList<String> loadUsernamesByOrgId(String deptId);
	
}
