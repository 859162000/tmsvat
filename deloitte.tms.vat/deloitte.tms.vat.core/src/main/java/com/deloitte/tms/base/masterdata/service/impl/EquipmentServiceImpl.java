package com.deloitte.tms.base.masterdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.EquipmentDao;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipmentInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntityInParam;
import com.deloitte.tms.base.masterdata.service.EquipmentService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxH;



@Component(EquipmentService.BEAN_ID)
public class EquipmentServiceImpl extends BaseService implements EquipmentService {
	
	@Resource
	EquipmentDao equipmentDao;
	
	@Override
	public IDao getDao() {
		return equipmentDao;
	}
	
	@Override
	public List<TmsMdEquipment> loadAllEquipment() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		return	equipmentDao.loadEquipmentData(map);
		
		//return equipmentDao.find("from TmsMdEquipment  flag = 1 ");
	}

	@Override
	public DaoPage findEquipmentByParams(Map params, Integer pageIndex, Integer pageSize) {
		
		try{
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= equipmentDao.findEquipmentByParams(params, pageIndex, pageSize);
		
		if(daoPage!=null){
			
			daoPage.setResult(convertEquipmentToInParam((List<TmsMdEquipment>) daoPage.getResult()));			
		}	
		
		
		return daoPage;
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("EquipmentServiceImpl > findEquipmentByParams exception will return null");
			return null;
		}
	}
	
	public List<TmsMdEquipment> finAllEquipment()
	{
		return equipmentDao.find("from TmsMdEquipment where 1=1 and  flag = 1 ");
	}
	
	private List<TmsMdEquipmentInParam> convertEquipmentToInParam(List<TmsMdEquipment> models){
		List<TmsMdEquipmentInParam> result=new ArrayList<TmsMdEquipmentInParam>();
		for(TmsMdEquipment initiation:models){
			TmsMdEquipmentInParam inparam=convertEquipmentToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	
	public TmsMdEquipmentInParam convertEquipmentToInParam(TmsMdEquipment model){
		TmsMdEquipmentInParam inparam=new TmsMdEquipmentInParam();
		if(null==model.getParentEquipmentId()){
			inparam.setParentEquipmentName("");
		}else{
			TmsMdEquipment parent = (TmsMdEquipment) this.get(TmsMdEquipment.class, model.getParentEquipmentId());
			if(null!=parent){
				inparam.setParentEquipmentName(parent.getEquipmentName());
			}
		}
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	
	@Override
	public List<TmsMdEquipmentInParam> loadEquipmentData(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsMdEquipment> temp= equipmentDao.loadEquipmentData(params);
		List<TmsMdEquipmentInParam> result=new ArrayList<TmsMdEquipmentInParam>();
		for(TmsMdEquipment initiation:temp){
			TmsMdEquipmentInParam inparam=new TmsMdEquipmentInParam();
			ReflectUtils.copyProperties(initiation, inparam);
			result.add(inparam);
		}
		return result;
	}
	
	@Override
	public DaoPage findLegalEntityByParams(Map params, Integer pageIndex, Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= equipmentDao.findLegalEntityByParams(params, pageIndex, pageSize);
		
		if(daoPage!=null){
			daoPage.setResult(convertLegalEntityToInParam((List<TmsMdLegalEntity>) daoPage.getResult()));
		}
		
		return daoPage;
	}
	
	private List<TmsMdLegalEntityInParam> convertLegalEntityToInParam(List<TmsMdLegalEntity> models){
		List<TmsMdLegalEntityInParam> result=new ArrayList<TmsMdLegalEntityInParam>();
		for(TmsMdLegalEntity initiation:models){
			TmsMdLegalEntityInParam inparam=convertLegalEntityToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	
	public TmsMdLegalEntityInParam convertLegalEntityToInParam(TmsMdLegalEntity model){
		TmsMdLegalEntityInParam inparam=new TmsMdLegalEntityInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
	
	public TmsMdEquipment convertEquipmentInParamToEntity(TmsMdEquipmentInParam inParam){
		TmsMdEquipment entity=new TmsMdEquipment();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
	@Override
	public JSONObject deleteEquipment(String id) {
		JSONObject result = new JSONObject();
		String msg;
		if(LittleUtils.strEmpty(id)){
			msg = "请选择要删除的记录！";
			result.put("success", false);
			result.put("msg", msg);
			return result;
		}
		TmsMdEquipment equipment = (TmsMdEquipment) equipmentDao.get(TmsMdEquipment.class, id);
		//判断是否有子节点
		List<TmsMdEquipment> sonEquips = equipmentDao.loadSonEquipments(equipment.getId());
		if(sonEquips!=null && sonEquips.size()>0){
			msg = "该服务器下含有终端，不可删除！";
			result.put("success", false);
			result.put("msg", msg);
			return result;
		}
		
		equipmentDao.remove(equipment);
		msg = "删除成功！";
		result.put("success", true);
		result.put("msg", msg);
		return result;
	}
}
