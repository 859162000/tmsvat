package com.deloitte.tms.pl.system.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.system.dao.ByteContentDao;
import com.deloitte.tms.pl.system.model.ByteContent;

@Repository(ByteContentDao.BEAN_ID)
public class ByteContentDaoImpl extends BaseDao<ByteContent> implements ByteContentDao {

	@Override
	public ByteContent getByteContent(String groupId, String relationId) {
		AssertHelper.notEmpty_assert(groupId,"分组类型不能为空");
		AssertHelper.notEmpty_assert(relationId,"业务主键不能为空");
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from ByteContent where 1=1 ");
		query.append(" and  groupId=:groupId ");
		query.append(" and  relationId=:relationId ");
		values.put("groupId", groupId);
		values.put("relationId", relationId);
		return (ByteContent) getFirstRecord(findBy(query,values));
	}

}
