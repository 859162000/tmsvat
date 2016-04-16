package com.deloitte.tms.pl.core.test;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;

/**
 * <pre>
 * SERVICE层自定义事务测试基类，
 * 此类将自动加载应用所需的配置文件，测试类使用时需要实现getSpringContext()方法。
 * 此方法需要自定义事务边界，并自己控制提交回滚操作。
 */
public abstract class BaseServiceWithCustomTransactionTestCase extends AbstractTransactionalJUnit4SpringContextTests  {
	@Resource
	protected BaseDao baseDao;
	@Resource(name="baseService")
	protected BaseService baseService;
	protected final Log log = LogFactory.getLog(getClass());
}
