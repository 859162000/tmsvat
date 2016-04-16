package com.ling2.dataimport.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.context.utils.ContextUtils;
import com.ling2.core.dao.impl.BaseDao;
import com.ling2.dataimport.dao.IExcelModelDao;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;

/**
 * Excel导入数据库表维护
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 */
@Repository(IExcelModelDao.BEAN_ID)
public class ExcelModelDaoImpl extends BaseDao implements IExcelModelDao{	

	public DaoPage loadExcelModels(DetachedCriteria detachedCriteria,int pageIndex, int pageSize) throws Exception {
		detachedCriteria.add(Restrictions.eq("companyId", ContextUtils.getFixedCompanyId()));
		return pageBy(detachedCriteria, pageIndex, pageSize);
	}

	public void insertExcelModel(ExcelModel excelModel) throws Exception {
		excelModel.setCreateDate(new Date());
		excelModel.setCompanyId(ContextUtils.getFixedCompanyId());
		save(excelModel);
	}

	public void deleteExcelModelById(String excelModelId) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		String hqlDelete = "delete ExcelModel e where e.id = :excelModelId";
		session.createQuery(hqlDelete).setString("excelModelId", excelModelId).executeUpdate();
	}

	public void updateExcelModel(ExcelModel excelModel) throws Exception {
		update(excelModel);
	}

	@SuppressWarnings("rawtypes")
	public ExcelModel findExcelModelById(String excelModelId) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ExcelModel.class, "m");
		detachedCriteria.add(Restrictions.eq("id", excelModelId));
		List list = findByDetachedCriteria(detachedCriteria);
		if (list.isEmpty()) {
			return null;
		} else {
			return (ExcelModel) list.get(0);
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<ExcelModelDetail> findExcelModelDetailByModelId(String modelId) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ExcelModelDetail.class, "m");
		detachedCriteria.add(Restrictions.eq("excelModelId", modelId));
		detachedCriteria.addOrder(Order.asc("excelColumn"));
		List list = findByDetachedCriteria(detachedCriteria);
		return list;
	}

	public void insertExcelModelDetail(ExcelModelDetail excelModelDetail) throws Exception {
		save(excelModelDetail);
	}

	public void deleteExcelModelDetailById(String excelModelDetailId) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		String hqlDelete = "delete ExcelModelDetail e where e.id = :excelModelDetailId";
		session.createQuery(hqlDelete).setString("excelModelDetailId", excelModelDetailId).executeUpdate();
	}

	public void deleteExcelModelDetailByModelId(String excelModelId) throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		String hqlDelete = "delete ExcelModelDetail e where e.excelModelId = :excelModelId";
		session.createQuery(hqlDelete).setString("excelModelId", excelModelId).executeUpdate();
	}

	public void updateExcelModelDetail(ExcelModelDetail excelModelDetail) throws Exception {
		update(excelModelDetail);
	}

	@SuppressWarnings("rawtypes")
	public ExcelModelDetail findExcelModelDetail(String modelId, int excelColumn) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ExcelModelDetail.class, "m");
		detachedCriteria.add(Restrictions.eq("excelModelId", modelId));
		detachedCriteria.add(Restrictions.eq("excelColumn", excelColumn));
		List list = findByDetachedCriteria(detachedCriteria);
		if (list.isEmpty()) {
			return null;
		} else {
			return (ExcelModelDetail) list.get(0);
		}
	}

	@SuppressWarnings("rawtypes")
	public ExcelModelDetail findExcelModelDetailByModelIdAndPrimaryKey(String modelId, String tableColumn) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ExcelModelDetail.class, "m");
		detachedCriteria.add(Restrictions.eq("excelModelId", modelId));
		detachedCriteria.add(Restrictions.eq("tableColumn", tableColumn));
		List list = findByDetachedCriteria(detachedCriteria);
		if (list.isEmpty()) {
			return null;
		} else {
			return (ExcelModelDetail) list.get(0);
		}
	}
}
