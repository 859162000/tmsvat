package com.deloitte.tms.base.masterdata.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

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

	static final String BEAN_ID="equipmentService";
	
	List<TmsMdEquipment> finAllEquipment();
	List<TmsMdEquipment> loadAllEquipment();

	DaoPage findEquipmentByParams(Map params, Integer pageIndex,Integer pageSize);

	TmsMdEquipmentInParam convertEquipmentToInParam(TmsMdEquipment model);

	List<TmsMdEquipmentInParam> loadEquipmentData(Map params);

	DaoPage findLegalEntityByParams(Map params, Integer pageIndex,Integer pageSize);

	TmsMdEquipment convertEquipmentInParamToEntity(TmsMdEquipmentInParam inParam);

	JSONObject deleteEquipment(String id);
}
