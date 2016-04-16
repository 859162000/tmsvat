package com.deloitte.tms.pl.core.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CollectionUtils {
	public static  CollectionUtils to(Class targetType) {
		return new CollectionUtils();
	}
	
	/**
	 * 判断集合是否有效,空对象和数据
	 * 
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection collection) {
		if (collection == null) {
			return true;
		}
		if (collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(Collection collection) {
		return !isEmpty(collection);
	}
//	public static Map assembleDatasetMap(DataSet dataSet) {
//		Map datasetMap = new HashMap();
//		List insertList = (List) dataSet.getRecords(DataSet.NEW);
//		List updateList = (List) dataSet.getRecords(DataSet.MODIFIED);
//		List deleteList = (List) dataSet.getRecords(DataSet.DELETED);
//		datasetMap.put(DataStatus.DELETE, deleteList);
//		datasetMap.put(DataStatus.INSERT, insertList);
//		datasetMap.put(DataStatus.MODIFIED, updateList);
//		return datasetMap;
//	}
//	public static <X> X getBatchEntities(DataSet dataSet) {
//		Map<DataStatus, Collection> result=new HashMap<DataStatus, Collection>();
//		result.put(DataStatus.INSERT, (Collection) getInsertEntities(dataSet));
//		result.put(DataStatus.MODIFIED, (Collection) getModifiedEntities(dataSet));
//		result.put(DataStatus.DELETE, (Collection) getDeleteEntities(dataSet));
//		return (X) result;
//	}
//	public static <X> X getAllEntities(DataSet dataSet) {
//		return (X)dataSet.getRecords(DataSet.ALL);
//	}
//	public static <X> X getInsertEntities(DataSet dataSet) {
//		return (X) dataSet.getRecords(DataSet.NEW);
//	}
//	public static <X> X getModifiedEntities(DataSet dataSet) {
//		return (X) dataSet.getRecords(DataSet.MODIFIED);
//	}
//	public static <X> X getDeleteEntities(DataSet dataSet) {
//		return (X) dataSet.getRecords(DataSet.DELETED);
//	}
	public static List convertSetToList(Set set)
	{
		List list=new ArrayList();
		if(set!=null)
		{
			Iterator iterator=set.iterator();
			if(iterator.hasNext())
			{
			Object object=iterator.next();
			while (object!=null) {
				list.add(object);
				if(iterator.hasNext())
				{
					object=iterator.next();
				}
				else {
					object=null;
				}
			}
			}
		}
		return list;
	}
	public static Set convertListToSet(List list)
	{
		Set set=new HashSet();
		if(list!=null)
		{
			for(Object object:list)
			{
				set.add(object);
			}
		}
		return set;
	}
}
