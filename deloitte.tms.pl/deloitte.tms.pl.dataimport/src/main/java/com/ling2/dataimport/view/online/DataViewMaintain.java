package com.ling2.dataimport.view.online;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.type.DefaultEntityDataType;
import com.bstek.dorado.data.type.property.BasePropertyDef;
import com.bstek.dorado.view.View;
import com.bstek.dorado.view.widget.Align;
import com.bstek.dorado.view.widget.base.IFrame;
import com.bstek.dorado.view.widget.grid.DataColumn;
import com.bstek.dorado.view.widget.grid.DataGrid;
import com.bstek.dorado.view.widget.grid.RowNumColumn;
import com.bstek.dorado.web.DoradoContext;
import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.core.commons.utils.reflect.ReflectUtils;
import com.ling2.dataimport.constant.ProcessTypeDef;
import com.ling2.dataimport.model.CellWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;
import com.ling2.dataimport.model.RowWrapper;
import com.ling2.dataimport.parse.impl.DefaultExcelParser;
import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * 缓存数据预览
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 */
@Controller("ling2.DataViewMaintain")
public class DataViewMaintain {
	private static final String COLUMN_WIDTH = "150";
	@Resource(name = DefaultExcelParser.BEAN_ID)
	public DefaultExcelParser excelParser;
	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;
	
	@DataProvider
	public List<Map<String, Object>> loadQueryData() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		ExcelDataWrapper data = excelParser.getCacheExcelData();
		if (data == null) {
			return list;
		}
		if(ProcessTypeDef.CLASSPROCESS.equals(data.getExcelModel().getProcessorType()))
		{
			ExcelModel excelModel = data.getExcelModel();
			String processor=excelModel.getProcessor();
			AssertHelper.notEmpty_assert(processor, "class导入方案没设置处理bean");
			ImportProcessor importProcessor=SpringUtil.getBean(processor);
			if(importProcessor==null)
			{
				throw new BusinessException("class导入方案设置的处理bean没有找到");
			}
			List saveData=importProcessor.loadPreviewData();
			list=ReflectUtils.convertListMap(saveData);
		}
		else{
			Collection<RowWrapper> rows = data.getRowWrappers();
			for (RowWrapper row : rows) {
				Collection<CellWrapper> cells = row.getCellWrappers();
				Map<String, Object> map = new HashMap<String, Object>();
				for (CellWrapper cell : cells) {
					map.put(cell.getName(), cell.getValue());
				}
				list.add(map);
			}
		}
		return list;
	}

	public void onInit(DefaultEntityDataType dataTypeData) throws Exception {
		ExcelDataWrapper data = excelParser.getCacheExcelData();
		if (data == null) {
			return;
		}
		BasePropertyDef pd;
		for (ExcelModelDetail detail : data.getExcelModel().getListExcelModelDetail()) {
			pd = new BasePropertyDef();
			pd.setName(detail.getTableColumn());
			dataTypeData.addPropertyDef(pd);
		}
	}

	public void onInit(DataGrid dataGridData) throws Exception {
		dataGridData.setWidth("100%");
		ExcelDataWrapper data = excelParser.getCacheExcelData();
		if (data == null) {
			return;
		}
		List<ExcelModelDetail> details = data.getExcelModel().getListExcelModelDetail();
		DataColumn column;
		if(!details.isEmpty()){
			RowNumColumn rowNumColumn=new RowNumColumn();
//			rowNumColumn.setWidth("30");
			dataGridData.addColumn(rowNumColumn);
			for (ExcelModelDetail detail : details) {
				column = new DataColumn();
				column.setName(detail.getTableColumn());
				column.setAlign(Align.center);
//				column.setCaption(detail.getName() + "(第" + detail.getExcelColumn() + "列)");
				column.setCaption(detail.getName());
				column.setWidth(COLUMN_WIDTH);
				dataGridData.addColumn(column);
			}
		}
	}
	public void onInit(View view) throws Exception {
		String excelModelId=DoradoContext.getAttachedRequest().getParameter("excelModelId");
		ExcelModel excelModel = excelModelService.findExcelModelById(excelModelId);
		String previewpage=excelModel.getPreviewPage();
		if(!AssertHelper.notEmpty(previewpage)){
			previewpage="dataimport.view.online.DataViewMaintain_default.ling";
		}
		IFrame iFrame=(IFrame) view.getViewElement("iFramePreview");
		iFrame.setPath(previewpage);
	}
}
