package com.deloitte.tms.base.masterdata.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.LegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoice;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface LegalEntityDao  extends IDao{
	
	public static final String BEAN_ID="legalEntityDao";
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param cla
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	DaoPage loadPageTaxDepts(Class cla, Map<String, String> params,
			int pageIndex, int pageSize);
	
	/**
	 * 
	 * @param ids
	 * @param cla
	 */
	void delByIds(ArrayList<String> ids, Class cla);
	/**
	 * 查询当前有效的纳税人实体数据
	 */
	List<TmsMdLegalEntity> getAllLegalEntities();
	/**
	 * 查询当前有效的纳税主体与纳税主体与汇缴(税种)关系 组成回角关系树
	 * @param taxCode
	 */
	List<LegalTaxCategory> getAllLegalTaxCategories(String taxCode);
	
	/**
	 * 获取当前有效的组织和纳税主体关系
	 */
	List<TmsMdOrgLegalEntity> getAllOrgLegalEntities();
	
	/**
	 * 获取当前有效的纳税人限额
	 */
	List<TmsMdLegalInvoice> getAllLegalInvoice();
	/**
	 * 获取当前有效的是否采用自身纳税识别号
	 * @param taxCode
	 * @return
	 */
	public List<TmsMdUsageLocalLegal> getAllTmsMdUsageLocalLegals();
	/**
	 * 获取当前有效的纳税主体与打印终端关系
	 * @return
	 */
	public List<TmsMdLegalEquipment> getAllTmsMdLegalEquipments();
	/**
	 * 获取当前有效的是否打印点 纳税人主体与打印点关系 
	 * @return
	 */
	public List<TmsMdLegalEnablePrint> getAllTmsMdLegalEnablePrints();

	

	public DaoPage loadPageTaxOrg(Class cla, int pageIndex, int pageSize);

	public void delByIdsUseFlag(ArrayList<String> ids, Class cla);

	public List<TmsMdLegalEntity> searchLegal(HashMap<String, Object> para);
	
}
