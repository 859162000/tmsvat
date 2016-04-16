package com.deloitte.tms.pl.system.service;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.system.model.ByteContent;

public interface ByteContentService extends IService<ByteContent>{
	public static final String BEAN_ID="byteContentService";
	public ByteContent getByteContent(String groupId,String relationId);
	public String getByteContentAsString(String groupId,String relationId);
	public String getByteContentAsString(Long id);
}
