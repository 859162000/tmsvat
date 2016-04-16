package com.deloitte.tms.base.masterdata.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipmentInParam;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;

/**
 * 
 * @author tigchen
 *
 */
public interface EquipmentService extends IService{

	public static final String BEAN_ID="equipmentService";
	
	public List<TmsMdEquipment> finAllEquipment();
	public List<TmsMdEquipment> loadAllEquipment();

	public DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize);

	public TmsMdEquipmentInParam convertEquipmentToInParam(TmsMdEquipment model);

	public List<TmsMdEquipmentInParam> loadEquipmentData(Map params);

	public DaoPage findLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize);

	public TmsMdEquipment convertEquipmentInParamToEntity(TmsMdEquipmentInParam inParam);

	public void deleteEquipment(String id);
}
