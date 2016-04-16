package com.deloitte.tms.pl.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.tms.pl.core.commons.utils.ConverterUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.system.dao.ByteContentDao;
import com.deloitte.tms.pl.system.model.ByteContent;
import com.deloitte.tms.pl.system.service.ByteContentService;

@Service(ByteContentService.BEAN_ID)
public class ByteContentServiceImpl extends BaseService<ByteContent> implements ByteContentService {

	@Resource
	ByteContentDao byteContentDao;
	
	@Override
	public ByteContent getByteContent(String groupId, String relationId) {
		return byteContentDao.getByteContent(groupId, relationId);
	}

	@Override
	public String getByteContentAsString(String groupId, String relationId) {
		ByteContent byteContent=getByteContent(groupId, relationId);
		return convertByteToString(byteContent);
	}

	@Override
	public String getByteContentAsString(Long id) {
		ByteContent byteContent= (ByteContent) get(ByteContent.class, id);
		return convertByteToString(byteContent);
	}

	@Override
	public IDao getDao() {
		return byteContentDao;
	}
	
	private String convertByteToString(ByteContent byteContent){
		if(byteContent==null){
			return null;
		}
		return ConverterUtils.getString(byteContent.getContent());
	}
}
