/**
 * 
 */
package com.ling2.dataimport.view;

import java.rmi.dgc.VMID;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.web.DoradoContext;
import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.D7PageUtils;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.dataimport.constant.ProcessTypeDef;
import com.ling2.dataimport.interceptor.ICellDataInterceptor;
import com.ling2.dataimport.model.DbDataWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;
import com.ling2.dataimport.parse.ExcelParser;
import com.ling2.dataimport.parse.impl.DefaultExcelParser;
import com.ling2.dataimport.processor.excelprocessor.IExcelProcessor;
import com.ling2.dataimport.processor.excelprocessor.impl.DefaultExcelProcessor;
import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * excel表维护定义、解析入库
 * 
 * @author matt.yao@bstek.com
 * @since 1.0
 */
@Controller("bdf.ExcelMaintain")
public class ExcelMaintain{
	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;
	@Resource(name = DefaultExcelParser.BEAN_ID)
	public DefaultExcelParser excelParser;

	@DataProvider
	public void loadExcelModels(Page<ExcelModel> page, Criteria criteria) throws Exception {
		DetachedCriteria detachedCriteria =D7PageUtils.buildDetachedCriteria(criteria, ExcelModel.class, "m");
		detachedCriteria.add(Restrictions.ne("processorType", ProcessTypeDef.CLASSPROCESS));
		DaoPage daoPage=excelModelService.loadExcelModels(detachedCriteria, page.getFirstEntityIndex(),page.getPageCount());
		D7PageUtils.daoPageToPage(daoPage, page);		
	}
	@DataProvider
	public void loadClassExcelModels(Page<ExcelModel> page, Criteria criteria) throws Exception {
		DetachedCriteria detachedCriteria =D7PageUtils.buildDetachedCriteria(criteria, ExcelModel.class, "m");
		detachedCriteria.add(Restrictions.eq("processorType", ProcessTypeDef.CLASSPROCESS));
		DaoPage daoPage=excelModelService.loadExcelModels(detachedCriteria, page.getFirstEntityIndex(),page.getPageCount());
		D7PageUtils.daoPageToPage(daoPage, page);		
	}
	@DataProvider
	public List<ExcelModelDetail> loadExcelModelDetails(String excelModeId) throws Exception {
		return excelModelService.findExcelModelDetailByModelId(excelModeId);
	}

	@Expose
	public String checkExcelModelId(String excelModelId) throws Exception {
		ExcelModel model = excelModelService.findExcelModelById(excelModelId);
		if (model == null) {
			return null;
		} else {
			return "方案编号已经存在";
		}
	}

	@DataResolver
	@Transactional
	public void saveExcelModels(Collection<ExcelModel> excelModels) throws Exception {
		for (ExcelModel excelModel : excelModels) {
			EntityState state = EntityUtils.getState(excelModel);
			if (state.equals(EntityState.NEW)) {
				excelModelService.insertExcelModel(excelModel);
			} else if (state.equals(EntityState.MODIFIED)) {
				excelModelService.updateExcelModel(excelModel);
			} else if (state.equals(EntityState.DELETED)) {
				excelModelService.deleteExcelModelById(excelModel.getId());
				excelModelService.deleteExcelModelDetailByModelId(excelModel.getId());
			}
			List<ExcelModelDetail> excelModelDetails = excelModel.getListExcelModelDetail();
			if (excelModelDetails != null) {
				this.saveExcelModelDetails(excelModelDetails);
			}
		}
	}

	public void saveExcelModelDetails(Collection<ExcelModelDetail> excelModelDetails) throws Exception {
		for (ExcelModelDetail excelModelDetail : excelModelDetails) {
			EntityState state = EntityUtils.getState(excelModelDetail);
			if (state.equals(EntityState.NEW)) {
				excelModelDetail.setId(new VMID().toString());
				excelModelService.insertExcelModelDetail(excelModelDetail);
			} else if (state.equals(EntityState.MODIFIED)) {
				excelModelService.updateExcelModelDetail(excelModelDetail);
			} else if (state.equals(EntityState.DELETED)) {
				excelModelService.deleteExcelModelDetailById(excelModelDetail.getId());
			}
		}
	}

	/**
	 * 查询系统注册的数据源
	 * 
	 * @return
	 */
	@DataProvider
	public Collection<DbDataWrapper> loadDatasourceNames() {
		DbDataWrapper dbDataWrapper;
		List<DbDataWrapper> list = new ArrayList<DbDataWrapper>();
//		Map<String, DataSourceRegister> dataSourceRegisters = DoradoContext.getAttachedWebApplicationContext().getBeansOfType(DataSourceRegister.class);
//		for (DataSourceRegister dataSourceRegister : dataSourceRegisters.values()) {
//			dbDataWrapper = new DbDataWrapper();
//			dbDataWrapper.setDataSourceName(dataSourceRegister.getName());
//			list.add(dbDataWrapper);
//		}
		return list;
	}

	@Expose
	@DataProvider
	public Collection<DbDataWrapper> loadTables(String dataSourceName) throws Exception {
		Collection<DbDataWrapper> tableInfos = new ArrayList<DbDataWrapper>();
//		if (StringUtils.isNotEmpty(dataSourceName)) {
//			List<String> list = excelModelService.findAllTables(dataSourceName);
//			DbDataWrapper dbDataWrapper = null;
//			for (String s : list) {
//				dbDataWrapper = new DbDataWrapper();
//				dbDataWrapper.setDataSourceName(dataSourceName);
//				dbDataWrapper.setTableName(s);
//				tableInfos.add(dbDataWrapper);
//			}
//		}
		return tableInfos;
	}

	@Expose
	@DataProvider
	public Collection<DbDataWrapper> loadTableColumnNames(Map<String, Object> map) throws Exception {
		Collection<DbDataWrapper> tableInfos = new ArrayList<DbDataWrapper>();
//		if (map != null && map.get("dataSourceName") != null && map.get("tableName") != null) {
//			String dataSourceName = (String) map.get("dataSourceName");
//			String tableName = (String) map.get("tableName");
//			List<String> list = excelModelService.findTableColumnNames(dataSourceName, tableName);
//			DbDataWrapper table = null;
//			for (String s : list) {
//				table = new DbDataWrapper();
//				table.setTableColumn(s);
//				tableInfos.add(table);
//			}
//		}
		return tableInfos;
	}

	@Expose
	@DataProvider
	public Collection<DbDataWrapper> loadTablePrimaryKeys(Map<String, Object> map) throws Exception {
		List<String> list = new ArrayList<String>();
//		if (map != null) {
//			String dataSourceName = (String) map.get("dataSourceName");
//			String tableName = (String) map.get("tableName");
//			list = excelModelService.findTablePrimaryKeys(dataSourceName, tableName);
//		}
		Collection<DbDataWrapper> tableInfos = new ArrayList<DbDataWrapper>();
//		DbDataWrapper table = null;
//		for (String s : list) {
//			table = new DbDataWrapper();
//			table.setTablePrimaryKey(s);
//			tableInfos.add(table);
//		}
		return tableInfos;
	}

	@DataProvider
	public Collection<DbDataWrapper> loadPrimaryKeyTypes() throws Exception {
		Collection<String> list = excelParser.getPrimaryTypes();
		Collection<DbDataWrapper> tableInfos = new ArrayList<DbDataWrapper>();
		DbDataWrapper table = null;
		for (String s : list) {
			table = new DbDataWrapper();
			table.setPrimaryKeyType(s);
			tableInfos.add(table);
		}
		return tableInfos;
	}

	@DataProvider
	public List<ExcelModelDetail> loadCellDataIntercepter() throws Exception {
		List<ExcelModelDetail> list = new ArrayList<ExcelModelDetail>();
		Map<String, ICellDataInterceptor> cellDataInterceptors = DoradoContext.getAttachedWebApplicationContext().getBeansOfType(ICellDataInterceptor.class);
		ExcelModelDetail excelModelDetail;
		for (Map.Entry<String, ICellDataInterceptor> entry : cellDataInterceptors.entrySet()) {
			excelModelDetail = new ExcelModelDetail();
			excelModelDetail.setName(entry.getKey() + "{" + entry.getValue().getName() + "}");
			excelModelDetail.setInterceptor(entry.getKey());
			list.add(excelModelDetail);
		}
		return list;
	}

	@DataProvider
	public List<ExcelModelDetail> loadExcelProcessor() throws Exception {
		List<ExcelModelDetail> list = new ArrayList<ExcelModelDetail>();
		Map<String, IExcelProcessor> excelProcessors = DoradoContext.getAttachedWebApplicationContext().getBeansOfType(IExcelProcessor.class);
		ExcelModelDetail excelModelDetail;
		for (Map.Entry<String, IExcelProcessor> entry : excelProcessors.entrySet()) {
			excelModelDetail = new ExcelModelDetail();
			if (!entry.getKey().equals(DefaultExcelProcessor.BEAN_ID)) {
				excelModelDetail.setName(entry.getKey() + "{" + entry.getValue().getName() + "}");
				excelModelDetail.setInterceptor(entry.getKey());
				list.add(excelModelDetail);
			}

		}
		return list;
	}
	@DataProvider
	public List<ExcelModelDetail> loadImportProcessor() throws Exception {
		List<ExcelModelDetail> list = new ArrayList<ExcelModelDetail>();
		Map<String, ImportProcessor> excelProcessors = DoradoContext.getAttachedWebApplicationContext().getBeansOfType(ImportProcessor.class);
		ExcelModelDetail excelModelDetail;
		for (Map.Entry<String, ImportProcessor> entry : excelProcessors.entrySet()) {
			excelModelDetail = new ExcelModelDetail();
			if (!entry.getKey().equals(DefaultExcelProcessor.BEAN_ID)) {
				excelModelDetail.setName(entry.getKey() + "{" + entry.getValue().getName() + "}");
				excelModelDetail.setInterceptor(entry.getKey());
				list.add(excelModelDetail);
			}

		}
		return list;
	}
	
	@Expose
	@Transactional
	public int processParserdExcelData(String excelModelId) throws Exception {
		AssertHelper.notEmpty_assert(excelModelId,"excelModelId不能为空");
		
		ExcelModel excelModel = excelModelService.findExcelModelById(excelModelId);
		String excelParser_bean=excelModel.getExcelParser();
		if(AssertHelper.empty(excelParser_bean)){
			return excelParser.processParserdExcelData();
		}else{
			ExcelParser excelParser_customer=SpringUtil.getBean(excelParser_bean);
			return excelParser_customer.processParserdExcelData();
		}
	}

	@Expose
	public boolean validateCacheData() throws Exception {
		Object data = excelParser.getCacheExcelData();
		if (data != null) {
			if(data instanceof ExcelDataWrapper){
				ExcelDataWrapper excelDataWrapper=(ExcelDataWrapper)data;
				if(excelDataWrapper.validate){
					return true;
				}else{
					return false;
				}
			}				
			return true;
		}	
		return false;
	}
}
