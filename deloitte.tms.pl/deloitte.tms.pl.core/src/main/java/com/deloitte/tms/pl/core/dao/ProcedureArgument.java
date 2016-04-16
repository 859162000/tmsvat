package com.deloitte.tms.pl.core.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.util.Assert;

/**
 * 存储过程参数类
 * @author Jake.Wang@bstek.com
 * @since Nov 29, 2012
 *
 */
public class ProcedureArgument {
	// 存放存储过程入参
	private Map<Integer, Object> inArguments = new HashMap<Integer, Object>();
	// 存放存储过程出参
	private Map<Integer, Object> outArguments = new HashMap<Integer, Object>();
	// 存放存储过程出参数组类型对象名称
	private Map<Integer, String> arrayTypes = new HashMap<Integer, String>();
	
	public static ProcedureArgument getInstance(){
		return new ProcedureArgument();
	}
	/**
	 * 添加存储过程输入参数
	 * @param index 参数在存储过程中的索引
	 * @param value 参数值
	 */
	public void addInput(Integer index, Object value){
		validate(index);
		inArguments.put(index, value);
	}
	
	/**
	 * 删除已添加的存储过程输入参数
	 * @param index 参数在存储过程中的索引
	 */
	public void removeInput(Integer index){
		inArguments.remove(index);
	}
	
	/**
	 * 添加复杂的存储过程输出参数
	 * @param index 参数在存储过程中的索引
	 * @param value 参数值
	 * @param typeName 参数类型（应用于用户在数据库中定义的数据类型，例如：数组）
	 */
	public void addOutput(Integer index, Object value, String typeName){
		addOutput(index, value);
		validateTypeName(typeName);
		arrayTypes.put(index, typeName);
	}

	/**
	 * 验证参数类型名称
	 * @param typeName 参数类型（应用于用户在数据库中定义的数据类型，例如：数组）
	 */
	private void validateTypeName(String typeName) {
		Assert.isTrue(StringHelper.isNotEmpty(typeName), "参数类型不能为空！");
	}
	
	/**
	 * 添加存储过程输出参数
	 * @param index 参数在存储过程中的索引
	 * @param value 参数值
	 */
	public void addOutput(Integer index, Object value){
		validate(index);
		outArguments.put(index, value);
	}

	/**
	 * 验证参数是否设置
	 * @param index 参数在存储过程中的索引
	 */
	private void validate(Integer index) {
		Assert.isTrue(!isAdded(index), "参数添加不正确，改参数已经设置！");
	}
	
	/**
	 * 删除存储过程中已添加的输出参数
	 * @param index 参数在存储过程中的索引
	 */
	public void removeOutput(Integer index){
		outArguments.remove(index);
		arrayTypes.remove(index);
	}
	
	/**
	 * 检测存储过程中的某个参数是否已经添加
	 * @param index 参数在存储过程中的索引
	 * @return true（已添加）、false（未添加）
	 */
	public boolean isAdded(Integer index){
		if(inArguments.keySet().contains(index) || outArguments.keySet().contains(index)){
			return true;	
		}else{
			return false;
		}
	}
	
	/**
	 * 获取存储过程的参数值
	 * @param index  参数在存储过程中的索引
	 * @return 存储过程在index位置下参数值或null（无效index）
	 */
	public Object get(Integer index){
		if(inArguments.containsKey(index)){
			return inArguments.get(index);
		}else if(outArguments.containsKey(index)){
			return outArguments.get(index);
		}else{
			return null;
		}
	}
	
	/**
	 * 获取参数类型
	 * @param index 参数在存储过程中的索引
	 * @return 参数类型字符串
	 */
	public String getType(Integer index){
		return arrayTypes.get(index);
	}
	
	public Map<Integer, Object> getInArguments(){
		return inArguments;
	}
	
	public Map<Integer, Object> getOutArguments(){
		return outArguments;
	}

	public Map<Integer, String> getArrayTypes() {
		return arrayTypes;
	}

}
