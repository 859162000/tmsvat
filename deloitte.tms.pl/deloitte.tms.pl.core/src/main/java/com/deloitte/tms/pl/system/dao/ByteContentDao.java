package com.deloitte.tms.pl.system.dao;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.system.model.ByteContent;

public interface ByteContentDao extends IDao<ByteContent>{
	public static final String BEAN_ID="byteContentDao";
	public ByteContent getByteContent(String groupId,String relationId);
}
