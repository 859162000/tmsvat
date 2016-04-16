package com.deloitte.tms.base.taxsetting.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.SalesDataDao;
import com.deloitte.tms.base.taxsetting.model.TaxSettingParam;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;

@Component(SalesDataDao.BEAN_ID)
public class SalesDataDaoImpl extends BaseDao<Object> implements SalesDataDao {

	private Logger logger = Logger.getLogger(SalesDataDaoImpl.class);

	public void SalesDataJob(final TaxSettingParam param) {

		Session session = this.getSession();

		session.doWork(new Work() {

			/**
			 * 从恒生系统数据，并传至销售汇总表
			 */
			public void execute(Connection connection) throws SQLException {

				String sql = "{call tms_crvat_cj_hs_sales_imp.import_hs_to_tmsvat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
				String p_hs_trans_date = "";

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				java.util.Date dd = new java.util.Date();
				p_hs_trans_date = sdf.format(dd);

				try {
					CallableStatement statement = connection.prepareCall(sql);
					statement.setInt(1, Integer.parseInt(p_hs_trans_date) - 1);
					statement.setString(2, param.getP_proc_hs_inf_flag());
					statement.setString(3, param.getP_proc_ss_inf_flag());
					statement.setString(4, "");
					statement.setDate(5,
							Date.valueOf(param.getP_archive_base_date()));
					statement.setString(6, param.getP_biz_org_code());
					statement.setString(7, param.getP_created_by());
					statement.setDate(8, new Date(System.currentTimeMillis()));
					statement.setString(9, param.getP_last_updated_by());
					statement.setDate(10, new Date(System.currentTimeMillis()));
					statement.setInt(11,
							Integer.parseInt(param.getP_record_version()));
					statement.setString(12, param.getP_deleted_flag());
					statement.setString(13, "");
					statement.setDate(14, new Date(System.currentTimeMillis()));
					statement.setString(15, "");
					statement.setString(16, "");
					statement.registerOutParameter(17, Types.VARCHAR);
					statement.registerOutParameter(18, Types.FLOAT);
					statement.registerOutParameter(19, Types.VARCHAR);

					statement.execute();

					logger.info("Get HS data  status: " + statement.getString(17));
					logger.info("return msg count: " + statement.getFloat(18));
					logger.info("return msg data: " + statement.getString(19));

					if (statement.getString(17).equals("S")) {
						test(connection, param);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 执行交易认定
	 * 
	 * @param connection
	 * @param sql
	 * @throws SQLException
	 */
	private void test(Connection connection, TaxSettingParam param)
			throws SQLException {
		String sql = "{call tms_crvat_ss_trx_affirm_pub.process_sales_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		;
		CallableStatement statement = connection.prepareCall(sql);
		statement.setString(1, "");
		statement.setString(2, "");
		statement.setString(3, "");
		statement.setDate(4, Date.valueOf(param.getP_archive_base_date()));
		statement.setString(5, param.getP_biz_org_code());
		statement.setString(6, param.getP_created_by());
		statement.setDate(7, new Date(System.currentTimeMillis()));
		statement.setString(8, param.getP_last_updated_by());
		statement.setDate(9, new Date(System.currentTimeMillis()));
		statement.setInt(10, Integer.parseInt(param.getP_record_version()));
		statement.setString(11, param.getP_deleted_flag());
		statement.setString(12, "");
		statement.setString(13, "");
		statement.setString(14, "");
		statement.setString(15, "");
		statement.registerOutParameter(16, Types.VARCHAR);
		statement.registerOutParameter(17, Types.FLOAT);
		statement.registerOutParameter(18, Types.VARCHAR);

		statement.execute();

		logger.info("Execute tax setting status: " + statement.getString(16));
		logger.info("return msg count: " + statement.getFloat(17));
		logger.info("return msg data: " + statement.getString(18));
	}
}