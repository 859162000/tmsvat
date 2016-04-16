package com.deloitte.tms.pl.core.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * <pre>
 *  SERVICE层测试基类，
 *  此类将自动加载应用所需的配置文件，测试类使用时需要实现getSpringContext()方法。
 *  默认情况下该测试基类根据BEAN类型自动从容器中匹配对象，
 *  并在每个测试方法结束后回滚对数据库的操作。
 *  
 *  &lt;strong&gt;程序范例：&lt;/strong&gt;
 *  
 *  public class SampleServiceTest extends BaseServiceTestCase {
 *      
 *      private SampleService sampleService;
 *      
 *      private SampleDao sampleDao;
 *      
 *      public void setSampleDao(SampleDao sampleDao) {
 *          this.sampleDao = sampleDao;
 *      }
 *      
 *      public void setSampleService(SampleService sampleService) {
 *          this.sampleService = sampleService;
 *      }
 *      
 *      @Override
 *      protected String[] getSpringContext() {
 *          return new String[] {
 *              &quot;dao/sample/sampleDao.xml&quot;,
 *              &quot;service/sample/sampleService.xml&quot;
 *          };
 *      }
 *      
 *      @Override
 *      protected void onSetUpInTransaction() throws Exception {
 *          super.onSetUpInTransaction();
 *          
 *          Sample sample = new Sample();
 *          sample.setText(&quot;test&quot;);
 *          sampleDao.save(sample);
 *          
 *      }
 *      
 *      public void testFindSamples() {
 *          Collection&lt;Sample&gt; samples = sampleService.findSampleByText(&quot;test&quot;);
 *          assertNotNull(samples);
 *          assertEquals(1, samples.size());
 *      }
 *  }
 *  
 *  使用此基础类时，首先需要于onSetUpInTransaction()中初始化测试需要的数据，
 *  在测试方法中对初始化后的数据进行测试。
 *  详细的测试流程可参考ChaRoleServiceTest的相关方法
 *  
 *  
 *  
 *  如果想要改变数据库的事务行为，则需要重载以下方法：
 *  
 *  @Override
 *  protected void onSetUpInTransaction() throws Exception {
 *      super.onSetUpInTransaction();
 *      setDefaultRollback(true);
 *  }
 *  
 * </pre>
 * 
 * @author dada
 */
public abstract class BaseServiceTestCase extends
	AbstractTransactionalJUnit4SpringContextTests {

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
		configLocations.add(PREFIX
				+ "context/applicationContext-annotation.xml");
		configLocations.add(PREFIX + "context/applicationContext-config.xml");
		configLocations.add(PREFIX
				+ "context/applicationContext-dataSource.xml");
		configLocations
				.add(PREFIX + "context/applicationContext-hibernate.xml");
		String[] contexts = getSpringContext();
		for (int i = 0; i < contexts.length; i++) {
			configLocations.add("classpath*:/beans/" + contexts[i]);
		}
		return (String[]) configLocations.toArray(new String[0]);
	}

	protected void flushSession() {
		SessionFactory sessionFactory = (SessionFactory) applicationContext
				.getBean("sessionFactory");
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

}
