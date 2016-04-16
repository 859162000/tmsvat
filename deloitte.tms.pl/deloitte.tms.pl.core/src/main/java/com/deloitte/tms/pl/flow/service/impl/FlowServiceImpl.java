package com.deloitte.tms.pl.flow.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.flow.dao.FlowDao;
import com.deloitte.tms.pl.flow.model.Flow;
import com.deloitte.tms.pl.flow.service.FlowService;

/**
 * 
 * 号码流水表管理
 * 
 * @author xingchang.zhang 2007-08-06
 * @version 1.0
 * 
 */
@Component
public abstract class FlowServiceImpl extends BaseService implements FlowService {

	@Resource
	protected FlowDao flowDao;
	
	public void saveFlows(Collection<Flow> flows) {
		for (Flow flow : flows) {
			if(flow.getId()==null){
				validateObj(flow);
//				String seq = flow.getSeq();
//				if (seq == null) {
//					// 如果号码流水号表的序列为空，则抛出业务异常
//					BusinessException be = new BusinessException(
//							"Flow.seq can not be null.");
//					throw be;
//				}
				flowDao.save(flow);
			}else {
				validateObj(flow);
//				String seq = flow.getSeq();
//				if (seq == null) {
//					// 如果号码流水号表的序列为空，则抛出业务异常
//					BusinessException be = new BusinessException(
//							"Flow.seq can not be null.");
//					throw be;
//				}
				flowDao.update(flow);
			}
		}
	}
	public Flow getFlowByOrgId(String orgId, String flowType) {
		Flow flow = new Flow();
		try {
			flow = (Flow) flowDao.getFlowByOrgId(orgId, flowType);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return flow;
	}
	public boolean validateObj(Flow obj) throws BusinessException {
		boolean flag = false;
		String[] propertyNames = new String[3];
		propertyNames[0] = "flowType";
		propertyNames[2] = "orgId";

		if (!flowDao.isUnique(obj, propertyNames)) {
			throw new BusinessException("流水号类型：" + obj.getFlowType()+ "，组织机构编码：" + obj.getOrgId()
					+ "确定的流水号不唯一！");
		} else {
			flag = true;
		}
		return flag;
	}

	public DaoPage getFlowByOrgId(String orgId, int index, int size) {
		return flowDao.getFlowByOrgId(orgId, index, size);
	}

	public List getFlowByOrgId(String orgId) {
		return flowDao.getFlowByOrgId(orgId);
	}

	// 用于FlowModuleTest
	public Flow getFlowByOrgId_Test(String orgcode, String flowType,
			String chatype) {

		List list = null;
		while ((list == null || list.isEmpty()) && orgcode != null) {
			list = this.flowDao.getFlowByOrgId_Test(orgcode, flowType,
					chatype);
			// parent = (DivisionEntity) parent.getSuperior();
		}
		if (list == null || list.isEmpty()) {
			throw new BusinessException("机构[" + orgcode + "]以及其上级没有定义流水代码!");
		}
		if (list.size() > 1) {
			throw new BusinessException("机构[" + orgcode + "]或其上级的流水号定义大于一个！");
		}
		return (Flow) list.get(0);
	}
	@Override
	public DaoPage findFlowByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		return flowDao.findFlowByParams(params, pageIndex, pageSize);
	}
	@Override
	public List findFlowByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		return flowDao.findFlowByParams(params);
	}
	@Override
	public void saveFlowDataListsMap(Map dataListsMap) {
		Collection<Flow> deleteList = BatchUtils.to(Flow.class).getDeleteEntities(dataListsMap);
		Collection<Flow> insertList =  BatchUtils.to(Flow.class).getInsertEntities(dataListsMap);
		Collection<Flow> updateList =  BatchUtils.to(Flow.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			flowDao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			flowDao.saveAll(insertList);
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			flowDao.removeAll(deleteList);
		}
		
	}

}
