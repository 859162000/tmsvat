package com.deloitte.tms.base.masterdata.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;

public interface EquipmentDao extends IDao<TmsMdEquipment> {
	public static final String BEAN_ID="equipmentDao";

	public DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdEquipment> loadEquipmentData(Map params);

	public DaoPage findLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize);

	public void deleteEquipment(String id);

}
