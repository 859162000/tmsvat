package com.ling2.dataimport.processor.excelprocessor.impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ling2.dataimport.model.CellWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;
import com.ling2.dataimport.model.GeneratePkStrategry;
import com.ling2.dataimport.model.RowWrapper;
import com.ling2.dataimport.model.SupportSequenceDb;
import com.ling2.dataimport.processor.excelprocessor.IExcelProcessor;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * @author matt.yao@bstek.com
 * @since 2.0
 */
@Service(DefaultExcelProcessor.BEAN_ID)
public class DefaultExcelProcessor implements IExcelProcessor {
	@Resource
	JdbcTemplate jdbcTemplate;
	public static final String BEAN_ID = "defaultProcessor";
	public final Log logger = LogFactory.getLog(DefaultExcelProcessor.class);

	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;

	public String getName() {
		return "系统默认单表解析入库处理类";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bstek.bdf.excel.processor.Processor#execute(com.bstek.bdf.excel.domain
	 * .ExcelDataWrapper)
	 */
	public int execute(ExcelDataWrapper excelDataWrapper) throws Exception {
		if (!excelDataWrapper.isValidate()) {
			throw new RuntimeException("当前数据没有通过验证,不能解析入库！");
		}
		ExcelModel excelModel = excelDataWrapper.getExcelModel();
		ExcelModelDetail excelModelDetail = null;
		if (StringUtils.hasText(excelModel.getPrimaryKey())) {
			excelModelDetail = excelModelService.findExcelModelDetailByModelIdAndPrimaryKey(excelModel.getId(), excelModel.getPrimaryKey());
		}

		SupportSequenceDb type = this.validateDb(excelModel.getDatasourceName());
		if (type != null) {
			excelModel.setDbType(type.name());
		}
		Collection<RowWrapper> rowWrappers = excelDataWrapper.getRowWrappers();
		int count = 0;
		for (RowWrapper rowWrapper : rowWrappers) {
			String tableName = rowWrapper.getTableName();
			Collection<CellWrapper> cellWrappers = rowWrapper.getCellWrappers();
			// 系统定义主键策略
			if (excelModelDetail == null && cellWrappers.size() > 0) {
				if (StringUtils.hasText(excelModel.getPrimaryKey())) {
					if (excelModel.getPrimaryKeyType().equals(GeneratePkStrategry.VMID.name())) {
						CellWrapper cellWrapper = new CellWrapper();
						cellWrapper.setIsPrimaryKey(true);
						cellWrapper.setColumnName(excelModel.getPrimaryKey());
						cellWrapper.setValue(UUID.randomUUID().toString());
						cellWrappers.add(cellWrapper);
					} else if (excelModel.getPrimaryKeyType().equals(GeneratePkStrategry.UUID.name())) {
						CellWrapper cellWrapper = new CellWrapper();
						cellWrapper.setIsPrimaryKey(true);
						cellWrapper.setColumnName(excelModel.getPrimaryKey());
						cellWrapper.setValue(UUID.randomUUID().toString());
						cellWrappers.add(cellWrapper);
					}
				}
			}
			List<String> columnNameList = new ArrayList<String>();
			List<Object> columnValueList = new ArrayList<Object>();
			for (CellWrapper cellWrapper : cellWrappers) {
				String columnName = cellWrapper.getColumnName();
				if (StringUtils.hasText(columnName)) {
					Object columnValue = cellWrapper.getValue();
					columnNameList.add(columnName);
					columnValueList.add(columnValue);
					if (cellWrapper.getIsPrimaryKey() && excelModel.getPrimaryKeyType().equals(GeneratePkStrategry.ASSIGNED.name())) {
						// 判断主键是否在数据库重复
						String sql = "select count(*) from " + tableName + " where " + columnName + "=?";
						int sum = jdbcTemplate.queryForInt(sql, new Object[] { columnValue });
						if (sum > 0) {
							throw new RuntimeException("数据库表[" + tableName + "]的字段[" + columnName + "]为主键，键值[" + columnValue + "]重复！");
						}
					}
				}

			}
			int n = this.insertRowWrapper2Table(excelModel, columnNameList, columnValueList);
			if (n == 1) {
				count++;
			}
		}
		logger.info("解析excel入库成功，导入[" + count + "]条数据！");
		return count;

	}

	private int insertRowWrapper2Table(ExcelModel excelModel, List<String> columnNameList, List<Object> columnValueList) throws Exception {
		String tableName = excelModel.getTableName();
		StringBuffer sb = new StringBuffer("insert into ");
		sb.append(tableName + "( ");
		StringBuffer sbValues = new StringBuffer(" values(");
		int j = 1;
		if (StringUtils.hasText(excelModel.getPrimaryKey())) {
			if (excelModel.getPrimaryKeyType().equals(GeneratePkStrategry.SEQUENCE.name())) {
				if (excelModel.getDbType().equals(SupportSequenceDb.oracle.name())) {
					sb.append(excelModel.getPrimaryKey());
					sbValues.append(this.getOracleNextval(excelModel.getSequenceName()));
				} else if (excelModel.getDbType().equals(SupportSequenceDb.db2.name())) {
					sb.append(excelModel.getPrimaryKey());
					sbValues.append(this.getDB2Nextval(excelModel.getSequenceName()));
				}
				if (columnNameList.size() > 0 && columnValueList.size() > 0) {
					sb.append(",");
					sbValues.append(",");
				}
			}
		}
		for (String s : columnNameList) {
			if (columnNameList.size() == j) {
				sb.append(s);
				sbValues.append("?");
			} else {
				sb.append(s + ",");
				sbValues.append("?,");
			}
			j++;

		}
		sb.append(" )");
		sbValues.append(" )");
		String sql = sb.append(sbValues).toString();
		logger.info("insert into :" + sql);
		return jdbcTemplate.update(sql, columnValueList.toArray());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private SupportSequenceDb validateDb(String datasourceName) {
		return jdbcTemplate.execute(new ConnectionCallback() {
			public Object doInConnection(Connection con) throws SQLException, DataAccessException {
				DatabaseMetaData databaseMetaData = con.getMetaData();
				String databaseProductName = databaseMetaData.getDatabaseProductName();
				if (org.apache.commons.lang.StringUtils.containsIgnoreCase(databaseProductName, SupportSequenceDb.oracle.name())) {
					return SupportSequenceDb.oracle;
				} else if (org.apache.commons.lang.StringUtils.containsIgnoreCase(databaseProductName, SupportSequenceDb.db2.name())) {
					return SupportSequenceDb.db2;
				}
				return null;
			}
		});
	}

	public String getOracleNextval(String sequenceName) {
		return sequenceName + ".nextval";
	}

	public String getDB2Nextval(String sequenceName) {
		return "NEXTVAL FOR " + sequenceName;
	}

	@Override
	public int execute(Object cachedata) throws Exception {
		return 0;
	}

}
