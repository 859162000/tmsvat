package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryGenerator;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
import com.deloitte.tms.pl.security.service.IRoleService;

@Component
public class AllRoleDictionaryGenerator implements DictionaryGenerator{
	
	@Resource
	IRoleService roleService;

	@Override
	public List<DictionaryEntity> getDictionaryEntities() {
		List<DefaultRole> roles=roleService.loadAllRoles();
		List<DictionaryEntity> results=new ArrayList<DictionaryEntity>();
		for(DefaultRole role:roles){
			DictionaryEntity dictionaryEntity=new DictionaryEntity();
			dictionaryEntity.setCode(role.getName());
			dictionaryEntity.setName(role.getDesc());
			results.add(dictionaryEntity);
		}
		return results;
	}

	@Override
	public String getDictType() {
		
		return "ALLROLE";
	}

}
