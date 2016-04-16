package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryGenerator;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

@Component
public class LegalEntityDictionaryGenerator implements DictionaryGenerator {

	@Override
	public List<DictionaryEntity> getDictionaryEntities() {
		List<DictionaryEntity> results=new ArrayList<DictionaryEntity>();
		LegalEntityNode orgNode=LegalEntityCacheUtils.getTopNode();
		Enumeration<Node> enumeration = orgNode.children();
		while (enumeration.hasMoreElements()) {
			LegalEntityNode childNode=(LegalEntityNode)enumeration.nextElement();
			DictionaryEntity dictionaryEntity=new DictionaryEntity();
			dictionaryEntity.setCode(childNode.getId());
			dictionaryEntity.setName(childNode.getName());
			results.add(dictionaryEntity);
			processOrgNode(childNode,results);
		}
		return results;
	}
	void processOrgNode(LegalEntityNode orgNode,List<DictionaryEntity> results){
		Enumeration<Node> enumeration = orgNode.children();
		while (enumeration.hasMoreElements()) {
			LegalEntityNode childNode=(LegalEntityNode)enumeration.nextElement();
			DictionaryEntity dictionaryEntity=new DictionaryEntity();
			dictionaryEntity.setCode(childNode.getId());
			dictionaryEntity.setName(childNode.getName());
			results.add(dictionaryEntity);
		}
	}
	@Override
	public String getDictType() {
		return "BASE_LEGAL_ENTITY";
	}

}
