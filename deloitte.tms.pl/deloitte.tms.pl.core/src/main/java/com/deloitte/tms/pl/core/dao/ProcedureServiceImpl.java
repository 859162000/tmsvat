package com.deloitte.tms.pl.core.dao;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import com.deloitte.tms.pl.core.dao.impl.BaseDaoSimple;

/**
 * 存储过程调用默认实现类
 * @author Jake.Wang@bstek.com
 * @since Nov 29, 2012
 *
 */
public class ProcedureServiceImpl extends BaseDaoSimple implements ProcedureService{

	JdbcTemplate jdbcTemplate;
	
	public Map<Integer, Object> invoke(String procedureCall) {
		return this.invoke(procedureCall, null, null);
	}
	public Map<Integer, Object> invoke(String procedureCall, ProcedureArgument argument) {
		return this.invoke(procedureCall, argument, null);
	}
	public Map<Integer, Object> invoke(String procedureCall, String dataSourceName) {
		return this.invoke(procedureCall, null, dataSourceName);
	}
	public Map<Integer, Object> invoke(String procedureCall,ProcedureArgument argument, String dataSourceName) {
		return this.execute(procedureCall, argument, dataSourceName);
	}
	
    /**
     * @param procedureSql存储过程语句
     * @param arg 向存储过程传递的参数对象
     * @param dataSourceName 执行存储过程的数据源
     * @return Map<Integer, Object>对象
     */
    @SuppressWarnings({ "unchecked"})
    public Map<Integer, Object> execute(final String procedureCall, final ProcedureArgument arg, String dataSourceName) {
    	final Map<Integer, Object> outMap = new HashMap<Integer, Object>();
    	jdbcTemplate.execute(getCallableStatementCreator(procedureCall, arg), getCallableStatementCallback(arg, outMap));
        return outMap;
    }
    
    /**
     * 生成CallableStatementCreator对象
     * @param procedureCall 执行存储过程语句
     * @param arg 向存储过程传递的参数对象
     * @return CallableStatementCreator对象
     */
	private CallableStatementCreator getCallableStatementCreator(
			final String procedureCall, final ProcedureArgument arg) {
		return new CallableStatementCreator() {
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall(procedureCall);
                initializeParameters(arg, cs);
                return cs;
            }
        };
	}

    /**
     * 生成CallableStatement执行后的回调函数
     * @param arg 向存储过程传递的参数对象
     * @param outMap Map<Integer, Object>对象
     * @return CallableStatementCallback对象
     */
	@SuppressWarnings("rawtypes")
	private CallableStatementCallback getCallableStatementCallback(final ProcedureArgument arg, final Map<Integer, Object> outMap) {
		return new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
            	boolean hasResult = cs.execute();
            	getOutput(arg, outMap, cs, hasResult);
                return null;
            }
        };
	}
    
    /**
     * 设置输出返回参数值
     * @param arg 向存储过程传递的参数对象
     * @param outMap Map<Integer, Object>对象
     * @param cs CallableStatement对象
     * @param hasResult 是否返回结果集
     * @throws SQLException
     */
    private void getOutput(final ProcedureArgument arg,Map<Integer, Object> outMap, CallableStatement cs, boolean hasResult)
			throws SQLException {
    	// 获取SQLServer,MYSQL或DB2存储过程返回的结果集
    	getResultSet(arg, outMap, cs, hasResult);
    	if(arg != null){
    		for (Integer index : arg.getOutArguments().keySet()) {
    			Object obj = arg.getOutArguments().get(index);
    			if(obj instanceof  ResultSet){
    				// 获取ORACLE存储过程返回的结果集
    				getResultList(outMap, (ResultSet)cs.getObject(index), index);
    			}else if(obj instanceof Array){
    				// 获取ORACLE或DB2存储过程返回的数组
    				getArrayList(outMap, cs.getArray(index),index);
    			}else{
    				// 获取存储过程返回的单个值
    				outMap.put(index, cs.getObject(index));
    			}
    		}
    	}
	}

    /**
     * 获取SQLServer,MYSQL或DB2存储过程返回的结果集
     * @param arg 向存储过程传递的参数对象
     * @param outMap 要返回的Map<Integer, Object>对象
     * @param cs CallableStatement对象
     * @param hadResults 检查是否存在更多结果集  
     * @throws SQLException
     */
	private void getResultSet(final ProcedureArgument arg,
			Map<Integer, Object> outMap, CallableStatement cs,
			boolean hasResult) throws SQLException {
		Integer rsIndex = getRSIndex(arg);
        while (hasResult) {
            ResultSet rs = cs.getResultSet();  
            getResultList(outMap, rs, rsIndex);
            rsIndex ++;
            hasResult = cs.getMoreResults(); //检查是否存在更多结果集  
        }
	}

	/**
	 * 获取SQLServer,MYSQL或DB2的存储过程返回的结果集在outMap内的初始key值
	 * @param arg 存储过程参数对象
	 * @return SQLServer,MYSQL或DB2的存储过程返回的结果集在outMap内的初始key值
	 */
	private Integer getRSIndex(final ProcedureArgument arg) {
		if(arg != null){
			return arg.getInArguments().size() + arg.getOutArguments().size() + 1;
		}else{
			return 1;
		}
	}

	/**
	 * 获取存储过程返回的结果集
	 * @param outMap 要返回的Map<Integer, Object>对象
	 * @param rs 结果集
	 * @param index 参数索引
	 * @throws SQLException
	 */
	private void getResultList(Map<Integer,Object> outMap, ResultSet rs, Integer index)
			throws SQLException {
		List<Object> rowList = new ArrayList<Object>();
		while (rs != null && rs.next()) {
			Map<String, Object> obj = new HashMap<String, Object>();
			for(Integer i = 1; i <= rs.getMetaData().getColumnCount(); i++){
				obj.put("c" + i.toString(), rs.getObject(i));
			}
			rowList.add(obj);
	    }
		outMap.put(index, rowList);
	}
	
	/**
	 *  获取存储过程返回的数组
	 * @param outMap Map<Integer, Object>对象
	 * @param array Array对象
	 * @param index 参数索引
	 * @throws SQLException
	 */
	private void getArrayList(Map<Integer, Object> outMap, Array array, Integer index) throws SQLException{
		if(array != null){
			Object[] objArray = (Object[])array.getArray();
			List<Object> arrayList = new ArrayList<Object>();
			for(Object obj : objArray){
				arrayList.add(obj);
			}
			outMap.put(index, arrayList);
		}
	}
    
	/**
	 * 初始化存储过程参数
	 * @param arg 向存储过程传递的参数对象
	 * @param cs CallableStatement对象
	 * @throws SQLException
	 */
    private void initializeParameters(final ProcedureArgument arg, CallableStatement cs) throws SQLException {
		if(arg != null){
			setInArgument(cs, arg);
			registerOutParameter(cs,arg);
		}
	}
    
    /**
     * 注册存储过程输出类型
     * @param cs CallableStatement对象
     * @param arg 向存储过程传递的参数对象
     * @throws SQLException
     */
    private void registerOutParameter(CallableStatement cs, ProcedureArgument arg) throws SQLException {
    	for(Integer index : arg.getOutArguments().keySet()){
    		Object value = arg.getOutArguments().get(index);
    		if (value instanceof String) {
    			cs.registerOutParameter(index, Types.VARCHAR);
    		} else if (value instanceof Integer) {
    			cs.registerOutParameter(index, Types.INTEGER);
    		} else if (value instanceof Long) {
    			cs.registerOutParameter(index, Types.INTEGER);
    		} else if(value instanceof Double){
				cs.registerOutParameter(index, Types.DOUBLE);
			} else if(value instanceof Float){
				cs.registerOutParameter(index, Types.FLOAT);
			} else if(value instanceof BigDecimal){
				cs.registerOutParameter(index, Types.BIGINT);
			} else if(value instanceof Boolean){
				cs.registerOutParameter(index, Types.BOOLEAN);
			} else if(value instanceof Date){
				cs.registerOutParameter(index, Types.DATE);
			} else if(value instanceof Ref){
				cs.registerOutParameter(index, Types.REF);
			} else if(value instanceof Blob){
				cs.registerOutParameter(index, Types.BLOB);
			} else if(value instanceof Clob){
				cs.registerOutParameter(index, Types.CLOB);
			} else if(value instanceof Timestamp){
				cs.registerOutParameter(index, Types.TIMESTAMP);
			} else if(value instanceof Time){
				cs.registerOutParameter(index, Types.TIME);
			} else if(value instanceof Array){
				cs.registerOutParameter(index, Types.ARRAY,arg.getType(index));
			} else if(value instanceof ResultSet){
				cs.registerOutParameter(index, oracle.jdbc.OracleTypes.CURSOR);
			} 
    	}
	}
    
    /**
     * 设置存储过程输入参数值
     * @param cs CallableStatement对象
     * @param arg 向存储过程传递的参数对象
     * @throws SQLException
     */
	private void setInArgument(CallableStatement cs, ProcedureArgument arg) throws SQLException {
		for(Integer index : arg.getInArguments().keySet()){
			Object value = arg.getInArguments().get(index);
			if (value instanceof String) {
				cs.setString(index, (String) value);
			} else if (value instanceof Integer) {
				cs.setInt(index, (Integer) value);
			} else if (value instanceof Long) {
				cs.setLong(index, (Long) value);
			} else if(value instanceof Double){
				cs.setDouble(index, (Double) value);
			} else if(value instanceof Float){
				cs.setFloat(index, (Float) value);
			} else if(value instanceof BigDecimal){
				cs.setBigDecimal(index, (BigDecimal) value);
			} else if(value instanceof Boolean){
				cs.setBoolean(index, (Boolean) value);
			} else if(value instanceof Date){
				cs.setDate(index, (java.sql.Date) value);
			} else if(value instanceof Ref){
				cs.setRef(index, (Ref) value);
			} else if(value instanceof Blob){
				cs.setBlob(index, (Blob) value);
			} else if(value instanceof Clob){
				cs.setClob(index, (Clob) value);
			} else if(value instanceof Timestamp){
				cs.setTimestamp(index, (Timestamp) value);
			} else if(value instanceof Time){
				cs.setTime(index, (Time) value);
			} else if(value instanceof Array){
				cs.setArray(index, (Array) value);
			}
		}
	}
}
