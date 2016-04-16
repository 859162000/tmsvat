package com.deloitte.tms.pl.security.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.security.model.SecurityPosition;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface IPositionDao extends IDao {
	public static final String BEAN_ID="ling2.positionDao";
	SecurityPosition newPositionInstance(String positionId);
	List<SecurityPosition> loadUserPositions(String username);
	List<SecurityPosition> findAllPosition();
	SecurityPosition loadPositionById(String positionId);
	/**
	 * 分页加载岗位数据
	 * @param page Dorado7分页对象，其中包含pageNo,pageSize，分页后的数据也填充到这个page对象当中，该参数不可为空
	 * @param companyId 要加载哪个companyId下的岗位信息，该参数不可为空
	 * @param criteria Dorado7条件对象，可从中取到相应的条件值，该参数可为空
	 */
	public DaoPage loadPagePositions(Map params, int pageIndex,int pageSize);
	public List<SecurityPosition> loadPagePositions(Map params);
	public String uniqueCheck(String id);
}
