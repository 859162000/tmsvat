package com.deloitte.tms.base.masterdata.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;

public interface EquipmentDao extends IDao<TmsMdEquipment> {
	static final String BEAN_ID="equipmentDao";

	DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize);
	
	List<TmsMdEquipment> loadEquipmentData(Map params);
	
	/**
	 * 获取子节点
	 * @param parentEquipmentId
	 * @return
	 */
	List<TmsMdEquipment> loadSonEquipments(String parentEquipmentId);

	DaoPage findLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize);

	void deleteEquipment(String id);
}
