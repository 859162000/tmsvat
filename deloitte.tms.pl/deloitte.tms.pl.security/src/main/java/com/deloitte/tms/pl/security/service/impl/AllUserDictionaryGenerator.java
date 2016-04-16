package com.deloitte.tms.pl.security.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;
import com.deloitte.tms.pl.dictionary.service.DictionaryGenerator;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.service.IUserService;

@Component
public class AllUserDictionaryGenerator implements DictionaryGenerator{
	
	@Resource
	IUserService userService;

	@Override
	public List<DictionaryEntity> getDictionaryEntities() {
		Collection<SecurityUser> users=userService.loadUsers(new HashMap());
		List<DictionaryEntity> results=new ArrayList<DictionaryEntity>();
		for(SecurityUser user:users){
			DictionaryEntity dictionaryEntity=new DictionaryEntity();
			dictionaryEntity.setCode(user.getUsername());
			dictionaryEntity.setName(user.getCname());
			results.add(dictionaryEntity);
		}
		return results;
	}

	@Override
	public String getDictType() {
		
		return "ALLUSER";
	}

}
