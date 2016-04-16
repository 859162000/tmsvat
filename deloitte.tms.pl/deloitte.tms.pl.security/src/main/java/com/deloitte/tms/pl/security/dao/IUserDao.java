package com.deloitte.tms.pl.security.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;

/**
 * @since 2013-1-18
 * @author Jacky.gao
 */
public interface IUserDao extends IDao{
	public static final String BEAN_ID="ling2.userDao";
	/**
	 * 分页加载用户数据
	 * @param page Dorado7分页对象，其中包含pageNo,pageSize，分页后的数据也填充到这个page对象当中，该参数不可为空
	 * @param companyId 要加载哪个companyId下的用户信息，该参数不可为空
	 * @param criteria Dorado7条件对象，可从中取到相应的条件值，该参数可为空
	 */
	public DaoPage loadPageUsers(Map params,int pageIndex,int pageSize);
	public Collection<SecurityUser>  loadUsers(Map params);
	public List<DefaultUser>  loadAllUsers();
	
	SecurityUser loadUserByUsername(String username);
	public SecurityUser loadUserByMobile(String mobile);
	public SecurityUser loadUserByEmail(String email);
	
	/**
	 * 加载指定部门ID下的用户信息
	 * @param deptId 隶属的部门ID，该参数不可为空
	 * @return 返回取到的用户集合
	 */
	Collection<SecurityUser> loadUsersByDeptId(String deptId);
	
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
	
	public String userIsExists(String username);
	
	public DefaultUser getDefaultUserByUserId(String userId);
}
