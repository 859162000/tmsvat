package com.ling2.dataimport.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.dao.IDao;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;

public interface IExcelModelDao extends IDao{
	
	public static final String BEAN_ID = "excelModelDao";
	
	public DaoPage loadExcelModels(DetachedCriteria detachedCriteria,int pageIndex, int pageSize) throws Exception;

	public void insertExcelModel(ExcelModel excelModel) throws Exception;

	public void deleteExcelModelById(String excelModelId) throws Exception;

	public void updateExcelModel(ExcelModel excelModel) throws Exception;

	public ExcelModel findExcelModelById(String excelModelId) throws Exception ;

	public List<ExcelModelDetail> findExcelModelDetailByModelId(String modelId) throws Exception;

	public void insertExcelModelDetail(ExcelModelDetail excelModelDetail) throws Exception;
	
	public void deleteExcelModelDetailById(String excelModelDetailId) throws Exception;

	public void deleteExcelModelDetailByModelId(String excelModelId) throws Exception;

	public void updateExcelModelDetail(ExcelModelDetail excelModelDetail) throws Exception;

	public ExcelModelDetail findExcelModelDetail(String modelId, int excelColumn) throws Exception ;

	public ExcelModelDetail findExcelModelDetailByModelIdAndPrimaryKey(String modelId, String tableColumn) throws Exception;
}
