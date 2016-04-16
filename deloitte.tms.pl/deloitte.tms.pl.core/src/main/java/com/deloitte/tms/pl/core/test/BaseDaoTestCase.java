package com.deloitte.tms.pl.core.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;


/**
 * <pre>
 * DAO层测试基类，
 * 此类将自动加载应用所需的配置文件，测试类使用时需要实现getSpringContext()方法。
 * 默认情况下该测试基类根据BEAN类型自动从容器中匹配对象，
 * 并在每个测试方法结束后回滚对数据库的操作。
 * 
 * <strong>程序范例：</strong>
 * 
 * public class SampleDaoTest extends BaseDaoTestCase {
 *     
 *     private SampleDao sampleDao;
 *     
 *     public void setSampleDao(SampleDao sampleDao) {
 *         this.sampleDao = sampleDao;
 *     }
 *     
 *     @Override
 *     protected String[] getSpringContext() {
 *         return new String[] {
 *             "sample/sampleDao.xml"
 *         };
 *     }
 *     
 *     public void testFindSamples() {
 *         ....
 *     }
 * }
 * 
 * 如果想要改变数据库的事务行为，则需要重载以下方法：
 * 
 * @Override
 * protected void onSetUpInTransaction() throws Exception {
 *     super.onSetUpInTransaction();
 *     setDefaultRollback(true);
 * }
 * 
 * </pre>
 * @author dada
 */
public abstract class BaseDaoTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	private final static String PREFIX = "classpath*:/beans/";
	
	protected abstract String[] getSpringContext();
	
	/**
	 * 获取配置信息
	 * 
	 * @author dada
	 */
	protected String[] getConfigLocations() {
		List<String> configLocations = new ArrayList<String>();
		configLocations.add(PREFIX + "context/applicationContext-annotation.xml");
		configLocations.add(PREFIX + "context/applicationContext-config.xml");
		configLocations.add(PREFIX + "context/applicationContext-dataSource.xml");
		configLocations.add(PREFIX + "context/applicationContext-hibernate.xml");
		for (String context : getSpringContext()) {
			configLocations.add(PREFIX + "dao/" + context);
		}
		return (String[]) configLocations.toArray(new String[0]);
	}

}
