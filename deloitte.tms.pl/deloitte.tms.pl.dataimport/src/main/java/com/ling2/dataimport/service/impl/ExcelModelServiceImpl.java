package com.ling2.dataimport.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.dao.IDao;
import com.ling2.core.service.impl.BaseService;
import com.ling2.dataimport.dao.IExcelModelDao;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;
import com.ling2.dataimport.service.IExcelModelService;

@Service(IExcelModelService.BEAN_ID)
public class ExcelModelServiceImpl extends BaseService implements IExcelModelService{
	@Resource
	IExcelModelDao excelModelDao;
	@Override
	public IDao getDao() {
		return excelModelDao;
	}

	@Override
	public DaoPage loadExcelModels(DetachedCriteria detachedCriteria,
			int pageIndex, int pageSize) throws Exception {
		return excelModelDao.loadExcelModels(detachedCriteria, pageIndex, pageSize);
	}

	@Override
	public void insertExcelModel(ExcelModel excelModel) throws Exception {
		excelModelDao.insertExcelModel(excelModel);
	}

	@Override
	public void deleteExcelModelById(String excelModelId) throws Exception {
		excelModelDao.deleteExcelModelById(excelModelId);
	}

	@Override
	public void updateExcelModel(ExcelModel excelModel) throws Exception {
		excelModelDao.updateExcelModel(excelModel);
	}

	@Override
	public ExcelModel findExcelModelById(String excelModelId) throws Exception {
		return excelModelDao.findExcelModelById(excelModelId);
	}

	@Override
	public List<ExcelModelDetail> findExcelModelDetailByModelId(String modelId)
			throws Exception {
		return excelModelDao.findExcelModelDetailByModelId(modelId);
	}

	@Override
	public void insertExcelModelDetail(ExcelModelDetail excelModelDetail)
			throws Exception {
		excelModelDao.insertExcelModelDetail(excelModelDetail);
	}

	@Override
	public void deleteExcelModelDetailById(String excelModelDetailId)
			throws Exception {
		excelModelDao.deleteExcelModelDetailById(excelModelDetailId);
	}

	@Override
	public void deleteExcelModelDetailByModelId(String excelModelId)
			throws Exception {
		excelModelDao.deleteExcelModelDetailByModelId(excelModelId);
	}

	@Override
	public void updateExcelModelDetail(ExcelModelDetail excelModelDetail)
			throws Exception {
		excelModelDao.updateExcelModelDetail(excelModelDetail);
	}

	@Override
	public ExcelModelDetail findExcelModelDetail(String modelId, int excelColumn)
			throws Exception {
		return excelModelDao.findExcelModelDetail(modelId, excelColumn);
	}

	@Override
	public ExcelModelDetail findExcelModelDetailByModelIdAndPrimaryKey(
			String modelId, String tableColumn) throws Exception {
		return excelModelDao.findExcelModelDetailByModelIdAndPrimaryKey(modelId, tableColumn);
	}

}
