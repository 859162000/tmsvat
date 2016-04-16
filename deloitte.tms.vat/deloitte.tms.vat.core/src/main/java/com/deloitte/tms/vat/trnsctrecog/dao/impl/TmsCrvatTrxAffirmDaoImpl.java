package com.deloitte.tms.vat.trnsctrecog.dao.impl;


import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatTrxAffirm;
import com.deloitte.tms.vat.trnsctrecog.dao.TmsCrvatTrxAffirmDao;

import java.sql.Connection;
import java.text.SimpleDateFormat;

/**
 * Home object for domain model class TmsCrvatTrxAffirm.
 * 
 * @see com.deloitte.tms.vat.trnsctrecog.model
 * @author Hibernate Tools
 */
@Component(TmsCrvatTrxAffirmDao.BEAN_ID)
public class TmsCrvatTrxAffirmDaoImpl extends BaseDao<TmsCrvatTrxAffirm>
		implements TmsCrvatTrxAffirmDao { 
	/**
	 * 交易信息查询
	 */
	public DaoPage findTmsCrvatTrxAffirmByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		String trxBatchNum = (String) params.get("trxBatchNum");//交易批次号
		String sourceCode = (String) params.get("custLegalEntityType");//来源代码
		String orgId = (String) params.get("orgId");//交易组织
		String trxEndDate = (String) params.get("trxEndDate");//记账开始日期
		String trxBeginDate = (String) params.get("trxBeginDate");//记账结束日期
		String trxNumber = (String) params.get("trxNumber");// 交易流水号
		String customerCode = (String) params.get("customerCode");// 客户编号
		String taxTrxTypeId = (String) params.get("transactiontypeId");// 涉税交易类型ID
		String tips = (String) params.get("tips");// 涉税交易类型ID
		
		StringBuffer query = new StringBuffer();
		Map values = new HashMap();
		query.append(" from TmsCrvatTrxAffirm where 1=1");
		
		if (trxBatchNum != null && !"".equals(trxBatchNum)) {
			query.append(" and trxBatchNum = :trxBatchNum");
			values.put("trxBatchNum",trxBatchNum);
		} 
		if (sourceCode != null && !"".equals(sourceCode) && !"0".equals(sourceCode)) {
			query.append(" and sourceCode = :sourceCode");
			values.put("sourceCode",sourceCode);
		} 
		if (orgId != null && !"".equals(orgId)) {
			query.append(" and orgId = :orgId");
			values.put("orgId",orgId);
		} 
		if (trxNumber != null && !"".equals(trxNumber)) {
			query.append(" and trxNumber = :trxNumber");
			values.put("trxNumber",trxNumber);
		}
		if (taxTrxTypeId != null && !"".equals(taxTrxTypeId)) {
			query.append(" and taxTrxTypeId = :taxTrxTypeId");
			values.put("taxTrxTypeId",taxTrxTypeId);
		}
		if (trxEndDate != null && !"".equals(trxEndDate)) {
			query.append(" and accountDate <= :trxEndDate");
			values.put("trxEndDate",DateUtils.parseTime(trxEndDate,"yyyy-MM-dd"));
		}
		if (trxBeginDate != null && !"".equals(trxBeginDate)) {
			query.append(" and accountDate >= :trxBeginDate");
			values.put("trxBeginDate",DateUtils.parseTime(trxBeginDate, "yyyy-MM-dd"));
		}
		if("0".equals(tips)){//异常数据查询
			query.append(" and trxAffirmSettingId is null");
		}
		if("1".equals(tips)){
			query.append(" and trxAffirmSettingId is not null");
		}
		
		return pageBy(query, values, pageIndex, pageSize);
	}

	public List<TmsCrvatTrxAffirm> findTmsCrvatTrxAffirmByParams(Map params) {
		StringBuffer query = new StringBuffer();
		Map values = new HashMap();
		buildTmsCrvatTrxAffirmQuery(query, values, params);
		return findBy(query, values);
	}

	private void buildTmsCrvatTrxAffirmQuery(StringBuffer query, Map values,
			Map params) {
		query.append(" from TmsCrvatTrxAffirm where 1=1 ");
		Object value = params.get("status");
		if (value != null) {
			query.append(" and status=:status");
			values.put("status", value);
		}
	}

	
	
	/**
	 * 查询组织
	 */
	@Override
	public DaoPage findTmsCrvatTrxAffirmByOrganization_id(
			Map<String, Object> parameter, Integer pageIndex, Integer pageSize) {
		String organizationInput_id = (String) parameter
				.get("organizationInput_id");
		String searchivaluesetencoding_id = (String) parameter
				.get("searchivaluesetencoding_id");
		DaoPage daop;
		if (organizationInput_id != null && !"".equals(organizationInput_id)) {
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from BaseOrg where 1=1 and orgName like :orgCode");
			values.put("orgCode", "%" + organizationInput_id + "%");
			daop = this.pageBy(query, values, pageIndex, pageSize);
		}else if(searchivaluesetencoding_id != null && !"".equals(searchivaluesetencoding_id)){
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from BaseOrg where 1=1 and orgName like :orgCode");
			values.put("orgCode", "%" + searchivaluesetencoding_id + "%");
			daop = this.pageBy(query, values, pageIndex, pageSize);
		} else {
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from BaseOrg where 1=1 ");
			daop = pageBy(query, values, pageIndex, pageSize);
		}

		return daop;
	}
/**
 * 查询涉税交易类型
 */
	@Override
	public DaoPage transactiontype_id(Map<String, Object> parameter,
			Integer pageNumber, Integer pageSize) {
		String customerNameInSendForm = (String) parameter
				.get("customerNameInSendForm");
		String customerNameInSendFormid = (String) parameter
				.get("customerNameInSendFormid");
		DaoPage daop;
		if (customerNameInSendForm != null && !"".equals(customerNameInSendForm)) {
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from TmsMdTaxTrxType where 1=1 and taxTrxTypeName like :taxTrxTypeCode");
			values.put("trxNumber", "%" + customerNameInSendForm + "%");
			daop = this.pageBy(query, values, pageNumber, pageSize);
		} else if(customerNameInSendFormid != null && !"".equals(customerNameInSendFormid)){
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from TmsMdTaxTrxType where 1=1 and taxTrxTypeName like :taxTrxTypeCode");
			values.put("taxTrxTypeCode", "%" + customerNameInSendFormid + "%");
			daop = this.pageBy(query, values, pageNumber, pageSize);
		}else {
			StringBuffer query = new StringBuffer();
			Map values = new HashMap();
			query.append("from TmsMdTaxTrxType where 1=1 ");
			daop = pageBy(query, values, pageNumber, pageSize);
		}

		return daop;
	}
/**
 * 手工认定
 */
@Override
public String loadTransactionException(final Map<String, Object> parameter) {
	
	

	Session session = this.getSession();

	session.doWork(new Work() {
		public void execute(Connection connection) throws SQLException {
	
	String sql = "{call tms_crvat_ss_trx_affirm_pub.process_sales_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
    String  ids = (String)parameter.get("ids");
    String orgids = (String)parameter.get("orgids");
    String versionId = (String)parameter.get("versionId");
    
	String [] idsarr = ids.split(",");
	String [] orgidsarr = orgids.split(",");
	String [] versionIdarr = versionId.split(",");
	
	String p_org_id = "";//组织ID  
	String p_sales_trx_id = "";//销售数据汇表总ID
	int p_record_version = 0;//版本号
	
	for(int i=0;i<idsarr.length;i++){
	if(orgidsarr[i]!=null){
		p_org_id = orgidsarr[i];
	}
	if(idsarr[i]!=null){
		System.out.println("id-----"+idsarr[i]);
		p_sales_trx_id = idsarr[i];
	}
	if(versionIdarr[i]!=null){
		p_record_version = Integer.parseInt(versionIdarr[i])+1;
	}
	

	String p_operation_org_code = "";//运营组织代码(统一规范字段)   
    String p_biz_org_code = "010000000000000";//业务组织代码(统一规范字段)  
	String p_created_by = "-1";//创建人(统一规范字段)  
	String p_last_updated_by = "-1";//最后修改人(统一规范字段)  
	String p_deleted_flag = "1";//删除标记 (统一规范字段) 
	String p_deleted_by = "";//删除人 (统一规范字段) 
	String p_company_id = "";//所属公司id(统一规范字段) 
	String p_data_owner_code = "";//数据拥有者代码(机构/组织等) (统一规范字段)          
	try {
		CallableStatement statement = connection.prepareCall(sql);
		statement.setString(1,p_org_id);//组织ID  
		statement.setString(2, p_sales_trx_id);//销售数据汇表总ID    
		statement.setString(3, p_operation_org_code);//运营组织代码(统一规范字段)  
		statement.setDate(4, new Date(System.currentTimeMillis()+31104000));//归档日期(统一规范字段)   
		statement.setString(5, p_biz_org_code);//业务组织代码(统一规范字段)  
		statement.setString(6, p_created_by);//创建人(统一规范字段)    
		statement.setDate(7, new Date(System.currentTimeMillis()));//创建时间(统一规范字段) 
		statement.setString(8, p_last_updated_by);//最后修改人(统一规范字段)  
		statement.setDate(9, new Date(System.currentTimeMillis()));//最后修改时间(统一规范字段) 
		statement.setInt(10, p_record_version);//记录版本号(统一规范字段)  
		statement.setString(11, p_deleted_flag);//删除标记 (统一规范字段) 
		statement.setString(12, p_deleted_by);//删除人 (统一规范字段)  
		statement.setDate(13,null);//删除时间 (统一规范字段)  
		statement.setString(14, p_company_id);//所属公司id(统一规范字段) 
		statement.setString(15, p_data_owner_code);//数据拥有者代码(机构/组织等) (统一规范字段) 
		statement.registerOutParameter(16, Types.VARCHAR);
		statement.registerOutParameter(17, Types.INTEGER);
		statement.registerOutParameter(18, Types.VARCHAR);
		statement.execute();
	}catch(Exception e){
		System.out.println(e);
	}
	}}
		});
	
	return "s";
	
}
	

}
