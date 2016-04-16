package com.deloitte.tms.pl.core.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.enums.DataStatus;


/**
 * VIEW层记录批处理的工具类
 * 
 * @author dada
 */
@SuppressWarnings("unchecked")
public final class BatchUtils<T> {

	/**
	 * 将参数转换后的类型
	 * 
	 * @param <T>
	 * @param targetType
	 * @return
	 * @author dada
	 */
	public static <T> BatchUtils<T> to(Class<T> targetType) {
		return new BatchUtils<T>();
	}
	
	/**
	 * <pre>
	 * 获取分类后的批处理记录。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void loadDatasetSample(Map dataListsMap) throws Exception {
	 *     Map<DataStatus, Collection<Sample>> sampleEntities = BatchUtils.to(Sample.class).getBatchEntites(dataSet);
	 *     ...
	 * }
	 * 
	 * </pre>
	 * @param dataSet
	 * @return
	 * @author dada
	 */
	public Map<DataStatus, Collection<T>> getBatchEntities(Map dataListsMap) {
		Map<DataStatus, Collection<T>> entities = new HashMap<DataStatus, Collection<T>>();
		List<T> insertEntities = (List<T>) dataListsMap.get(DataStatus.INSERT);
		List<T> updateEntities = (List<T>) dataListsMap.get(DataStatus.MODIFIED);
		List<T> deleteEntities =(List<T>) dataListsMap.get(DataStatus.DELETE);
		entities.put(DataStatus.DELETE, deleteEntities);
		entities.put(DataStatus.INSERT, insertEntities);
		entities.put(DataStatus.MODIFIED, updateEntities);
		return entities;
	}
	
	/**
	 * <pre>
	 * 获取整个记录结果集。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void loadDatasetSample(Map dataListsMap) throws Exception {
	 *     Collection<Sample> entities = BatchUtils.to(Sample.class).getAllEntities(dataSet);
	 *     ...
	 * }
	 * </pre>
	 * @param dataSet
	 * @return
	 * @author dada
	 */
	public Collection<T> getAllEntities(Map dataListsMap) {
		Map<DataStatus, Collection<T>> entities = new HashMap<DataStatus, Collection<T>>();
		List<T> allList=new ArrayList<T>();
		List<T> insertEntities = (List<T>) dataListsMap.get(DataStatus.INSERT);
		List<T> updateEntities = (List<T>) dataListsMap.get(DataStatus.MODIFIED);
		List<T> deleteEntities =(List<T>) dataListsMap.get(DataStatus.DELETE);
		allList.addAll(deleteEntities);
		allList.addAll(insertEntities);
		allList.addAll(updateEntities);
		return allList;
	}
	
	/**
	 * <pre>
	 * 获取整个新增记录集合。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void loadDatasetSample(Map dataListsMap) throws Exception {
	 *     Collection<Sample> entities = BatchUtils.to(Sample.class).getInsertEntities(dataSet);
	 *     ...
	 * }
	 * </pre>
	 * 
	 * @param dataSet
	 * @return
	 * @author dada
	 */
	public Collection<T> getInsertEntities(Map dataListsMap) {
		return (Collection<T>) dataListsMap.get(DataStatus.INSERT);
	}
	
	/**
	 * <pre>
	 * 获取整个修改记录集合。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void loadDatasetSample(Map dataListsMap) throws Exception {
	 *     Collectoin<Sample> entities = BatchUtils.to(Sample.class).getModifiedEntities(dataSet);
	 *     ...
	 * }
	 * </pre>
	 * 
	 * @param dataSet
	 * @return
	 * @author dada
	 */
	public Collection<T> getModifiedEntities(Map dataListsMap) {
		return (Collection<T>) dataListsMap.get(DataStatus.MODIFIED);
	}
	
	/**
	 * <pre>
	 * 获取整个删除记录集合。
	 * 
	 * <strong>程序范例：</strong>
	 * 
	 * public void loadDatasetSample(Map dataListsMap) throws Exception {
	 *     Collection<Sample> entities = BatchUtils.to(Sample.class).getDeleteEntities(dataSet);
	 *     ...
	 * }
	 * </pre> 
	 * 
	 * @param dataSet
	 * @return
	 * @author dada
	 */
	public Collection<T> getDeleteEntities(Map dataListsMap) {
		return (Collection<T>) dataListsMap.get(DataStatus.DELETE);
	}
	
}
