package com.deloitte.tms.pl.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import ognl.Ognl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.hibernate.internal.SessionImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.commons.enums.DataStatus;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.CriteriaUtils;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.HqlUtils;
import com.deloitte.tms.pl.core.commons.utils.PageUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.PropertyCriteria;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.PropertyCriteriaFactory;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.PropertyRestrictions;
import com.deloitte.tms.pl.core.commons.utils.support.DateSubstitute;
import com.deloitte.tms.pl.core.dao.IDao;

//@Repository(BaseDaoSimple.BEAN_ID)
public class BaseDaoSimple<T> implements IDao<T> {
	public static final String BEAN_ID="baseDaoSimple";
//	@Resource
	SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected Session getSession() {
		 //事务必须是开启的(Required)，否则获取不到
		Session session=sessionFactory.getCurrentSession();
        return session;
	}
	/**
	 * @param entity
	 */
	public void save( Object entity ) {
		getSession().save(entity);
	}

	public void saveOrUpdate(Object entity)
	{	
		try {
			getSession().saveOrUpdate(entity);
		} catch (Exception e) {
			throw new RuntimeException("批量更新反射异常");
		}	
	}
	public void saveOrUpdateAll(final Collection entities)
	{
		for (Object entity : entities) {
			saveOrUpdate(entity);
		}
	}
	/**
	 * @param entities
	 */
	@SuppressWarnings("unchecked")
	public void saveAll( final Collection entities ) {
		for (Object object : entities) {
			getSession().save(object);
		}
	}

	/**
	 * @param entity
	 * @param isSaveNull 是否将属性中的null值覆盖数据库的值
	 */
	public void update( Object entity, Boolean isSaveNull ) {
		if (isSaveNull) {
			getSession().saveOrUpdate(entity);
		} else {
			try {
				ClassMetadata classMetadata = getSessionFactory().getClassMetadata(entity.getClass());
				String identiferPropertyName = classMetadata.getIdentifierPropertyName();
				Serializable identiferValue = (Serializable) ReflectUtils.getFieldContent(entity, identiferPropertyName);				
				if (identiferValue == null) {
					getSession().save(entity);
				} else {
					Object persistentObject = getSession().get(
							entity.getClass(), identiferValue);
					ReflectUtils.replaceNullProperty(persistentObject, entity);
					getSession().saveOrUpdate(persistentObject);
				}
			} catch (Exception e) {
				throw new RuntimeException("批量更新反射异常");
			}
		}
	}


	/**
	 * @param entity
	 */
	public void update( Object entity ) {
		update(entity, true);
	}

	/**
	 * @param entities
	 * @param isSaveNull
	 */
	public void updateAll( Collection entities, Boolean isSaveNull ) {
		for (Object entity : entities) {
			update(entity,isSaveNull);
		}
	}


	/**
	 * @param entities
	 */
	public void updateAll( Collection entities ) {
		for (Object entity : entities) {
			update(entity);
		}
	}


	/**
	 * @param entity
	 */
	public void remove( Object entity ) {
		getSession().delete(entity);
	}
	/**
	 * @param clazz
	 * @param id
	 */
	public void removeById( Class clazz, Serializable id ) {
		Object entyty=get(clazz, id);
		remove(entyty);
	}

	/**
	 * @param entities
	 */
	public void removeAll( Collection entities ) {
		for (Object entity : entities) {
			remove(entity);
		}
	}
	/**
	 * @param entity
	 */
	public void removeDirectly( Object entity ) {
		getSession().delete(entity);
	}
	/**
	 * @param clazz
	 * @param id
	 */
	public void removeByIdDirectly( Class clazz, Serializable id ) {
		Object entyty=get(clazz, id);
		removeDirectly(entyty);
	}

	/**
	 * @param entities
	 */
	public void removeAllDirectly( Collection entities ) {
		for (Object entity : entities) {
			removeDirectly(entity);
		}
	}
	/**
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List find( final String queryString, final Map values ) {
		Assert.hasText(queryString);
		Assert.notNull(values);
		Assert.notEmpty(values);

		List mapParams = new ArrayList();
		List mapValues = new ArrayList();
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);
			mapParams.add(key);
			mapValues.add(value);
		}
		return this.findByNamedParam(queryString, (String[]) mapParams
				.toArray(new String[0]), mapValues.toArray());
	}
	/**
	 * @param queryString
	 * @param values
	 * @return
	 */
	public List find( final String queryString, final Serializable value ) {
		Assert.hasText(queryString);
		Assert.notNull(value);
		Session session=getSession();
		Query queryObject = session.createQuery(queryString);
		queryObject.setParameter(0, value);
		return queryObject.list();
	}
	/**
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List findByNamedParam( String queryString, String[] paramNames, Object[] values ) {
		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Collection) {
				Collection c = (Collection) values[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, paramNames,
							values, i, paramArray, paramValue);
					paramNames = (String[]) paramArray.toArray(new String[0]);
					values = paramValue.toArray();
				}
			}
		}
		Session session=getSession();
		Query queryObject = session.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
			}
		}
		return queryObject.list();
	}
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
			throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		}
		else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		}
		else {
			queryObject.setParameter(paramName, value);
		}
	}
	/**
	 * @param queryString
	 * @return
	 * @throws DataAccessException
	 */
	public List find( String queryString ) throws DataAccessException {
		Session session=getSession();
		Query queryObject = session.createQuery(queryString);
		return queryObject.list();
	}
	/**
	 * @param clazz
	 * @return
	 */
	protected List getAll( Class entityClass ) {
		Session session=getSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object get( Class clazz, Serializable id ) {
		return getSession().get(clazz, id);
	}
	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	public Object load( Class clazz, Serializable id ) {
		return getSession().load(clazz, id);
	}
	/**
	 * @param query
	 * @param values
	 * @return
	 */
	protected int findNumsql( StringBuffer query, Map values ) {
		List result=findBySql(query, values);
		String resultstrString=result
		.iterator().next().toString();
		Integer count = Integer.parseInt(resultstrString);
		return count;
	}
	/**
	 * @param query
	 * @param values
	 * @return
	 */
	protected int findNumhql( StringBuffer query, Map values ) {
		List result=findBy(query, values);
		if(result==null){
			return 0;
		}
		Object resultstrString=result
		.iterator().next();
		if(resultstrString==null){
			return 0;
		}
		Integer count = Integer.parseInt(resultstrString.toString());
		return count;
	}
	/**
	 * @param queryString
	 * @param values
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("rawtypes")
	public List find( final String queryString, final Object[] values ) throws DataAccessException {
		Assert.hasText(queryString);
		Session session=getSession();
		Query queryObject = session.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}
	@SuppressWarnings("unchecked")
	public List find(final String queryString, final Object[] values, final Type[] types) throws DataAccessException {
		Session session=getSession();
		Query queryObject = session.createQuery(queryString);
		if ((values != null) && (types != null)) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i], types[i]);
			}
		}
		return queryObject.list();
	}
	/**
	 * 原生SQL查询
	 *
	 * @param query
	 * @param oIn
	 * @return
	 * @author dada
	 */
	@SuppressWarnings("unchecked")
	public List findBySql( final String query, final Object[] oIn ) {
		Session session=getSession();
		Query queryObject = session.createSQLQuery(query);
		int iTmp = 1;
		for (int j = 0; j < oIn.length; j++) {
			iTmp = j + 1;
			if (oIn[j] instanceof String) {
				queryObject.setParameter(iTmp, oIn[j].toString());
			}
		}
		List result = queryObject.list();
		return result;
	}
	/**
	 * @param datasetMap
	 */
	public void batchExecute( Map datasetMap ) {
		batch(datasetMap);
	}

	/**
	 * @param entity
	 */
	protected void setUpdateUserInfo( Object entity ) {
	}

	/**
	 * @param entities
	 */
	protected void setUpdateUserInfoAll( Collection entities ) {
	}

	/**
	 * @param entity
	 */
	protected void setInsertUserInfo( Object entity ) {
	}

	/**
	 * @param entities
	 */
	protected void setInsertUserInfoAll( Collection entities ) {
	}

	/**
	 * @param entity
	 * @param state
	 */
	protected void setSecurityInfo( Object entity, String state ) {
	}

	/**
	 * @param entities
	 * @param state
	 */
	protected void setSecurityInfoAll( Collection entities, String state ) {
	}
	/**
	 * <pre>
	 *   根据传入的HQL进行数据访问。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   public List&lt;Sample&gt; findSamples() {
	 *       StringBuffer query = new StringBuffer();
	 *   	   query.append(&quot;from Sample sample&quot;);
	 *       return findBy(query);
	 *   }
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @return
	 * @author dada
	 */
	public List findBy(StringBuffer query) {
		Map<String, Object> values = new HashMap<String, Object>();
		return findBy(query, values);
	}
	public List findBy(String queryString, final Map<String, Object> values) {
		Assert.notNull(queryString);
		Assert.notNull(values);

		Session session=getSession();
		Query queryObject = session.createQuery(queryString);

		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);

			if (value instanceof Collection) {
				queryObject.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				queryObject.setParameterList(key, (Object[]) value);
			} else {
				queryObject.setParameter(key, value);
			}
		}

		return queryObject.list();
	}
	/**
	 * <pre>
	 *   根据传入的DetachedCriteria进行数据库访问。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   DetachedCriteria是Hibernate提供的分离对Hibernate session依赖的标准化查询工具。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   public List&lt;Sample&gt; findSamplesByText(String text) {
	 *       DetachedCriteria criteria = DetachedCriteria.forClass(Sample.class);
	 *       criteria.add(Restrictiosn.eq(&quot;text&quot;, text));
	 *       return findBy(criteria);
	 *   } 
	 *   
	 * </pre>
	 * 
	 * @param criteria
	 * @return
	 * @author dada
	 */
	public List findBy(final DetachedCriteria criteria) {
		Assert.notNull(criteria);
		Session session=getSession();
		Criteria executableCriteria = criteria
				.getExecutableCriteria(session);
		List result = executableCriteria.list();
		return result;
	}

	/**
	 * <pre>
	 *   根据传入的HQL，总记录数HQL，以及页码，每页记录数，获取分页对象。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   public List&lt;Sample&gt; pageSamples(int pageIndex, int pageSize) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;);
	 *       
	 *       StringBuffer queryCount = new StringBuffer();
	 *       queryCount.append(&quot;select count(*)&quot;);
	 *       			 .append(&quot; from Sample sample&quot;);
	 *       
	 *       
	 *       return pageBy(query, countQuery, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于比较复杂的分页查询(包含group by having等的情况)。
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @param queryCount
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, StringBuffer queryCount,
			final int pageIndex, final int pageSize) {
		Assert.notNull(query);
		Assert.notNull(queryCount);

		final String queryString = query.toString();
		final String queryCountString = queryCount.toString();

		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		Session session=getSession();
		Query hibernateQuery = session.createQuery(queryString);
		Query hibernateQueryCount = session
				.createQuery(queryCountString);
		int recordCount = ((Number) hibernateQueryCount.uniqueResult())
				.intValue();
		List result = hibernateQuery.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();

		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}

	public DaoPage pageBy(String queryString, String[] paramNames,
			Object[] values, int pageIndex, int pageSize) {
		Assert.hasText(queryString);

		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Collection) {
				Collection c = (Collection) values[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, paramNames,
							values, i, paramArray, paramValue);
					paramNames = (String[]) paramArray.toArray(new String[0]);
					values = paramValue.toArray();
				}
			}
		}

		String queryCountString = "select count(*) "
				+ HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		return pageBy(queryString, queryCountString, paramNames, values,
				pageIndex, pageSize);
	}

	/**
	 * 
	 */
	public DaoPage pageBy(String queryString, String queryCountString,
			String[] paramNames, Object[] values, int pageIndex, int pageSize) {
		Assert.hasText(queryString);

		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Collection) {
				Collection c = (Collection) values[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, paramNames,
							values, i, paramArray, paramValue);
					paramArray.clear();
					paramValue.clear();
					queryCountString = splitCollection(queryCountString,
							paramNames, values, i, paramArray, paramValue);
					paramNames = (String[]) paramArray.toArray(new String[0]);
					values = paramValue.toArray();
				}
			}
		}
		return pageBy1(queryString, queryCountString, paramNames, values,
				pageIndex, pageSize);
	}

	/**
	 * 如果有超过1000条记录，分割之
	 * 
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @param i
	 * @param paramArray
	 * @param paramValue
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String splitCollection(String queryString, String[] paramNames,
			Object[] values, int i, List paramArray, List paramValue) {
		StringBuffer query = new StringBuffer();
		for (int k = 0; k < i; k++) {
			paramArray.add(paramNames[k]);
			paramValue.add(values[k]);
		}
		String paramName = paramNames[i];
		Collection value = (Collection) values[i];
		List list = new ArrayList(value);

		double f = value.size() / 1000.0;
		if (value.size() % 1000 == 0) {
			f = value.size() / 1000;
		} else {
			f = f > 1 ? f + 1 : f;
		}

		int length = new Double(f).intValue();
		if (length > 1) {
			// (where/and deptid in (:deptid))
			// where trim(riskClass.classCode) in (:classCode)
			int index = queryString.indexOf(":" + paramName);
			int startIndex = queryString.lastIndexOf("and", index);
			int w = queryString.lastIndexOf("where", index);
			if (startIndex < 0 || w > startIndex) {
				startIndex = w + 5;
			} else {
				startIndex = startIndex + 3;
			}

			int endIndex = queryString.indexOf(' ', index);
			endIndex = endIndex < 0 ? queryString.length() : endIndex;
			if (queryString.charAt(index - 1) == '(') {
				int k = index + paramName.length() + 2;
				if (k < endIndex) {
					endIndex = k;
				}
			}

			query.append(queryString.substring(0, startIndex)).append(" (");
			String repeat = queryString.substring(startIndex, endIndex);
			// int k = repeat.indexOf(paramName) + paramName.length();
			int k = repeat.indexOf(":" + paramName) + paramName.length() + 1;
			for (int j = 0; j < length - 1; j++) {
				query.append(repeat.substring(0, k)).append(j);
				query.append(repeat.substring(k));
				query.append(" or ");
				paramArray.add(paramName + j);
				value = list.subList(j * 1000, j * 1000 + 1000);
				paramValue.add(value);
			}
			query.append(repeat.substring(0, k));
			query.append(length - 1).append(repeat.substring(k));
			query.append(") ").append(queryString.substring(endIndex));
			paramArray.add(paramName + (length - 1));
			value = list.subList((length - 1) * 1000, list.size());
			paramValue.add(value);

			queryString = query.toString();
		}
		for (int k = i + 1; k < values.length; k++) {
			paramArray.add(paramNames[k]);
			paramValue.add(values[k]);
		}
		return queryString;
	}

	@SuppressWarnings("unchecked")
	private DaoPage pageBy1(final String queryString,
			final String queryCountString, final String[] paramNames,
			final Object[] values, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		Assert.notNull(paramNames);
		Assert.notNull(values);
		Session session=getSession();
		Query query = session.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof Collection) {
					query.setParameterList(paramNames[i],
							(Collection) values[i]);
				} else if (values[i] instanceof Object[]) {
					query.setParameterList(paramNames[i],
							(Object[]) values[i]);
				} else {
					query.setParameter(paramNames[i], values[i]);
				}
			}
		}

		Query queryCount = session.createQuery(queryCountString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof Collection) {
					queryCount.setParameterList(paramNames[i],
							(Collection) values[i]);
				} else if (values[i] instanceof Object[]) {
					queryCount.setParameterList(paramNames[i],
							(Object[]) values[i]);
				} else {
					queryCount.setParameter(paramNames[i], values[i]);
				}
			}
		}

		Object obj = queryCount.uniqueResult();
		int recordCount = (obj == null ? 0 : ((Number) obj).intValue());

		List result = query.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，总记录数HQL，Page对象(对分页相关属性的封装)，获取分页对象。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   PageUtils提供了对Page对象的封装。
	 *    
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       Page page = PageUtils.datasetToPage(dataSet);
	 *       
	 *       ...
	 *   }
	 *   
	 *   DAO层：
	 *   public List&lt;Sample&gt; pageSamples(Page page) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;);
	 *       
	 *       StringBuffer queryCount = new StringBuffer();
	 *       queryCount.append(&quot;select count(*)&quot;);
	 *       			 .append(&quot; from Sample sample&quot;);
	 *       
	 *       
	 *       return pageBy(query, countQuery, page);
	 *   }
	 *   
	 *   此方法适用于比较复杂的分页查询(包含group by having等的情况)。
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @param queryCount
	 * @param page
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, StringBuffer queryCount, DaoPage page) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();
		return pageBy(query, queryCount, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，总记录数HQL，参数绑定Map对象，以及页码，每页记录数，获取分页对象。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   public List&lt;Sample&gt; pageSamplesByText (String text , int pageIndex, int pageSize) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;)
	 *            .append(&quot; where sample.text = :text&quot;);
	 *       
	 *       StringBuffer queryCount = new StringBuffer();
	 *       queryCount.append(&quot;select count(*)&quot;);
	 *       			 .append(&quot; from Sample sample&quot;)
	 *       			 .append(&quot; where sample.text = :text&quot;);
	 *       
	 *       Map&lt;String, Object&gt; values = new HashMap&lt;String, Object&gt;();
	 *       values.put(&quot;text&quot;, text);
	 *       
	 *       return pageBy(query, countQuery, values, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于比较复杂的分页查询(包含group by having等的情况)。
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @param queryCount
	 * @param values
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, StringBuffer queryCount,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);
		Assert.notNull(queryCount);

		final String queryString = query.toString();
		final String queryCountString = queryCount.toString();

		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		Session session=getSession();
		Query hibernateQuery = session.createQuery(queryString);
		Query hibernateQueryCount = session
				.createQuery(queryCountString);
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);

			if (value instanceof Collection) {
				hibernateQuery
						.setParameterList(key, (Collection) value);
				hibernateQueryCount.setParameterList(key,
						(Collection) value);
			} else if (value instanceof Object[]) {
				hibernateQuery.setParameterList(key, (Object[]) value);
				hibernateQueryCount.setParameterList(key,
						(Object[]) value);
			} else {
				hibernateQuery.setParameter(key, value);
				hibernateQueryCount.setParameter(key, value);
			}
		}
		int recordCount = ((Number) hibernateQueryCount.uniqueResult())
				.intValue();
		List result = hibernateQuery.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，总记录数HQL，参数绑定Map对象，Page对象(对分页相关属性的封装)，获取分页对象。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   PageUtils提供了对Page对象的封装。
	 *    
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       Page page = PageUtils.datasetToPage(dataSet);
	 *       
	 *       ...
	 *   }
	 *   
	 *   DAO层：
	 *   public List&lt;Sample&gt; pageSamplesByText(String text, Page page) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;)
	 *       		.append(&quot; where sample.text = :text&quot;);
	 *       
	 *       StringBuffer queryCount = new StringBuffer();
	 *       queryCount.append(&quot;select count(*)&quot;);
	 *       			 .append(&quot; from Sample sample&quot;)
	 *       			 .append(&quot; where sample.text = :text&quot;);
	 *       
	 *       Map&lt;String, Object&gt; values = new HashMap&lt;String, Object&gt;();
	 *       values.put(&quot;text&quot;, text);
	 *       
	 *       return pageBy(query, countQuery, values, page);
	 *   }
	 *   
	 *   此方法适用于比较复杂的分页查询(包含group by having等的情况)。
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @param queryCount
	 * @param values
	 * @param page
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, StringBuffer queryCount,
			Map<String, Object> values, DaoPage page) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();
		return pageBy(query, queryCount, values, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，参数绑定Map对象，以及页码，每页记录数，获取分页对象。
	 *   方法会自动去除传入的查询用HQL的select部分以及order by部分，拼装出记录数统计用的HQL。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *    
	 *   public List&lt;Sample&gt; pageSamplesByText (String text, int pageIndex, int pageSize) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;)
	 *       		.append(&quot; where sample.text = :text&quot;);
	 *       
	 *       Map&lt;String, Object&gt; values = new HashMap&lt;String, Object&gt;();
	 *       values.put(&quot;text&quot;, text);
	 *       return pageBy(query, values, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于普通的HQL分页查询。
	 *   *
	 * </pre>
	 * 
	 * @param query
	 * @param values
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(final StringBuffer query,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);
		String queryString = query.toString();
		Assert.hasText(queryString);

		String queryCountString = "select count(*) "
				+ HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);

		StringBuffer queryCount = new StringBuffer();
		queryCount.append(queryCountString);
		return pageBy(query, queryCount, values, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，参数绑定Map对象，以及页码，每页记录数，获取分页对象。
	 *   方法会自动去除传入的查询用HQL的select部分以及order by部分，拼装出记录数统计用的HQL。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *    
	 *   public List&lt;Sample&gt; pageSamplesByText (String text, int pageIndex, int pageSize) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;)
	 *       		.append(&quot; where sample.text = :text&quot;);
	 *       
	 *       Map&lt;String, Object&gt; values = new HashMap&lt;String, Object&gt;();
	 *       values.put(&quot;text&quot;, text);
	 *       return pageBy(query, values, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于普通的HQL分页查询。
	 *   *
	 * </pre>
	 * 
	 * @param query
	 * @param values
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	protected DaoPage pageByForSalesNet(final StringBuffer query,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);
		String queryString = query.toString();
		Assert.hasText(queryString);

		String queryCountString = "select count(distinct bankSalesNet) "
				+ HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);

		StringBuffer queryCount = new StringBuffer();
		queryCount.append(queryCountString);
		return pageBy(query, queryCount, values, pageIndex, pageSize);
	}

	/**
	 * 分页查询 不包括总记录数的获取
	 * 
	 * @param query
	 * @param values
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	public List findByPage(StringBuffer query,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		final String queryString = query.toString();
		Assert.hasText(queryString);
		Assert.notNull(values);
		Session session=getSession();
		Query hibernateQuery = session.createQuery(queryString);
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);

			if (value instanceof Collection) {
				hibernateQuery
						.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				hibernateQuery.setParameterList(key, (Object[]) value);
			} else {
				hibernateQuery.setParameter(key, value);
			}
		}
		List result = hibernateQuery.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return result;
	}

	/**
	 * <pre>
	 *   根据传入的HQL，参数绑定Map对象，Page对象，获取分页对象。
	 *   方法会自动去除传入的查询用HQL的select部分以及order by部分，拼装出记录数统计用的HQL。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       Page page = PageUtils.datasetToPage(dataSet);
	 *       
	 *       ...
	 *   }
	 *   
	 *   DAO层：  
	 *   public List&lt;Sample&gt; pageSamplesByText (String text,
	 *       , Page page) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;)
	 *            .append(&quot; where sample.text = :text&quot;);
	 *       
	 *       Map&lt;String, Object&gt; values = new HashMap&lt;String, Object&gt;();
	 *       values.put(&quot;text&quot;, text);
	 *       return pageBy(query, values, page);
	 *   }
	 *   
	 *   此方法适用于普通的HQL分页查询。 
	 * </pre>
	 * 
	 * @param query
	 * @param values
	 * @param page
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, Map<String, Object> values,
			DaoPage page) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();
		return pageBy(query, values, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，以及页码，每页记录数，获取分页对象。
	 *   方法会自动去除传入的查询用HQL的select部分以及order by部分，拼装出记录数统计用的HQL。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *    
	 *   public List&lt;Sample&gt; pageSamples (int pageIndex, int pageSize) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;);
	 *       
	 *       return pageBy(query, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于普通的HQL分页查询。
	 *   
	 * </pre>
	 * 
	 * @param query
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, final int pageIndex,
			final int pageSize) {
		Map values = new HashMap();
		return pageBy(query, values, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的HQL，Page对象，获取分页对象。
	 *   方法会自动去除传入的查询用HQL的select部分以及order by部分，拼装出记录数统计用的HQL。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       Page page = PageUtils.datasetToPage(dataSet);
	 *       
	 *       ...
	 *   }
	 *   
	 *   DAO层：  
	 *   public List&lt;Sample&gt; pageSamples (Page page) {
	 *       StringBuffer query = new StringBuffer();
	 *       query.append(&quot;from Sample sample&quot;);
	 *       
	 *       return pageBy(query, page);
	 *   }
	 *   
	 *   此方法适用于普通的HQL分页查询。 
	 * </pre>
	 * 
	 * @param query
	 * @param page
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(StringBuffer query, DaoPage page) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();
		return pageBy(query, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   根据传入的DetachedCriteria，以及页码，每页记录数，获取分页对象。
	 *   方法会自动去除传入的查询用DetachedCriteria的select部分以及order by部分，拼装出记录数统计用的DetachedCriteria。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *    
	 *   public List&lt;Sample&gt; pageSamples (int pageIndex, int pageSize) {
	 *   	   DetachedCriteria criteria = DetachedCriteria.forClass(Sample.class);
	 *       
	 *       return pageBy(criteria, pageIndex, pageSize);
	 *   }
	 *   
	 *   此方法适用于普通的分页查询。
	 *   
	 * </pre>
	 * 
	 * @param detachedCriteria
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	public DaoPage pageBy(final DetachedCriteria detachedCriteria,
			final int pageIndex, final int pageSize) {
		Assert.notNull(detachedCriteria);
		Session session=getSession();
		Criteria executableCriteria = detachedCriteria
				.getExecutableCriteria(session);
		OrderEntry[] orderEntries = CriteriaUtils
				.getOrders(executableCriteria);
		executableCriteria = CriteriaUtils
				.removeOrders(executableCriteria);

		Projection projection = CriteriaUtils
				.getProjection(executableCriteria);
		Criteria criteria=executableCriteria.setProjection(
				Projections.rowCount());
		Object uniqueResult=criteria.uniqueResult();
		if(uniqueResult==null)
		{
			throw new BusinessException("所查询对象未映射");
		}
		int recordCount = ((Long) uniqueResult).intValue();
		executableCriteria.setProjection(projection);
		if (projection == null) {
			executableCriteria
					.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		executableCriteria = CriteriaUtils.addOrders(
				executableCriteria, orderEntries);
		List result = executableCriteria.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}

	/**
	 * <pre>
	 *   根据传入的DetachedCriteria，以及Page封装对象，获取分页对象。
	 *   方法会自动去除传入的查询用DetachedCriteria的select部分以及order by部分，拼装出记录数统计用的DetachedCriteria。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   此方法在Oracle中的实现为标准的RowNum分页。
	 *    
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   public List&lt;Sample&gt; pageSamples (Page page) {
	 *   	   DetachedCriteria criteria = DetachedCriteria.forClass(Sample.class);
	 *       
	 *       return pageBy(criteria, page);
	 *   }
	 *   
	 *   此方法适用于普通的分页查询。
	 *   
	 * </pre>
	 * 
	 * @param detachedCriteria
	 * @param page
	 * @return
	 * @author dada
	 */
	protected DaoPage pageBy(DetachedCriteria detachedCriteria, DaoPage page) {
		int pageIndex = page.getPageIndex();
		int pageSize = page.getPageSize();

		return pageBy(detachedCriteria, pageIndex, pageSize);
	}

	/**
	 * <pre>
	 *   对数据进行批量新增。
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       List entities = BatchUtils.getInsertEntities(dataSet);
	 *       ...
	 *   }
	 *   
	 *   DAO层：
	 *   public void batch(List entities) {
	 *   		sampleDao.batchInserts(entities);
	 *   }
	 *   
	 * </pre>
	 * 
	 * @param inserts
	 * @author dada
	 */
	protected void batchInserts(Collection inserts) {
		if ((inserts != null) && !inserts.isEmpty()) {
			saveAll(inserts);
		}
	}

	/**
	 * <pre>
	 *   对数据进行批量删除。
	 *   由Hibernate的metadata中获取对应POJO的主键属性，
	 *   通过反射获取相应的主键值，按照主键将数据依次删除。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       List entities = BatchUtils.getDeleteEntities(dataSet);
	 *       ...
	 *   }
	 *   
	 *   DAO层：
	 *   public void batch(List entities) {
	 *   		sampleDao.batchDeletes(entities);
	 *   }
	 *   
	 * </pre>
	 * 
	 * @param deletes
	 * @author dada
	 */
	protected void batchDeletes(Collection deletes) {
		if ((deletes != null) && (deletes.size() > 0)) {
			String identiferPropertyName = null;

			for (Object persistent : deletes) {
				ClassMetadata classMetadata = getSessionFactory()
						.getClassMetadata(persistent.getClass());

				if (classMetadata != null) {
					identiferPropertyName = classMetadata
							.getIdentifierPropertyName();
					break;
				}
			}

			for (Object obj : deletes) {
				Serializable identiferValue;
				identiferValue = (Serializable) ReflectUtils.getPropertyValue(obj,
						identiferPropertyName);

				if (identiferValue != null) {
					Object entitiy = get(obj.getClass(),
							identiferValue);
					remove(entitiy);
				}
			}
		}
	}

	/**
	 * <pre>
	 *   对数据进行批量更新。
	 *   由Hibernate的metadata中获取对应POJO的主键属性，
	 *   通过反射获取相应的持久化对象。
	 *   
	 *   通过反射将传入的对象属性依次复制到持久化对象中，并将持久化对象更新。
	 *   属性复制的规则为：
	 *   1. 如果是one-to-many和many-to-many对象则不予处理。
	 *   2. 如果传入对象属性为NULL则不予处理。
	 *   3. 其他属性全部复制。
	 *   
	 *   此方法为protected，子类只能在自己的接口实现中调用。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   
	 *   VIEW层：
	 *   public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *       List entities = BatchUtils.getModifiedEntities(dataSet);
	 *       ...
	 *   }
	 *   
	 *   DAO层：
	 *   public void batch(List entities) {
	 *   		sampleDao.batchUpdates(entities);
	 *   }
	 *   
	 * </pre>
	 * 
	 * @param updates
	 * @author dada
	 */
	protected void batchUpdates(Collection updates) {
		if ((updates != null) && (updates.size() > 0)) {
			String identiferPropertyName = null;

			for (Object persistent : updates) {
				ClassMetadata classMetadata = getSessionFactory()
						.getClassMetadata(persistent.getClass());

				if (classMetadata != null) {
					identiferPropertyName = classMetadata
							.getIdentifierPropertyName();
					break;
				}
			}

			for (Object obj : updates) {
				Long identiferValue;

				identiferValue = (Long) ReflectUtils.getPropertyValue(obj,
						identiferPropertyName);

				if (identiferValue == null) {
					save(obj);
				} else {
					Object entity =get(obj.getClass(),
							identiferValue);

					PropertyCriteria criteria = PropertyCriteriaFactory
							.create();
					criteria.add(PropertyRestrictions.ignoreValue(null));
					ReflectUtils.copyProperties(obj, entity, criteria);
					update(entity);
				}
			}
		}
	}

	/**
	 * 原生SQL查询
	 * 
	 * @param query
	 * @param values
	 * @author dada
	 */
	public List findBySql(StringBuffer query,
			final Map<String, Object> values) {
		Assert.notNull(query);
		Assert.notNull(values);

		final String queryString = query.toString();
		Session session=getSession();
		Query queryObject = session.createSQLQuery(queryString);

		for (String key : values.keySet()) {
			Object value = values.get(key);

			if (value instanceof Collection) {
				queryObject.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				queryObject.setParameterList(key, (Object[]) value);
			} else {
				queryObject.setParameter(key, value);
			}
		}
		return queryObject.list();
	}

	/**
	 * 原生SQL查询(对外提供)
	 * 
	 * @param query
	 * @param values
	 * @author shunkai.zhou
	 */
	public List queryBySql(StringBuffer query, final Map<String, Object> values) {
		return this.findBySql(query, values);
	}

	/**
	 * 原生SQL查询分页
	 * 
	 * @param query
	 *            查询语句
	 * @param values
	 *            参数
	 * @author ming.chen
	 */
	protected DaoPage pageBySql(StringBuffer query, StringBuffer queryCount,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);
		Assert.notNull(values);

		// sql查询语句
		final String queryString = query.toString();
		// 记录数
		final String queryCountString = queryCount.toString();
		// 利用hibernate回调机制返回结果集
		// 查询结果集记录数和结果集
		Session session=getSession();
		Query queryObject = session.createSQLQuery(queryString);
		Query querySqlCount = session.createSQLQuery(queryCountString);
		// 设置参数
		for (String key : values.keySet()) {
			Object value = values.get(key);

			if (value instanceof Collection) {
				queryObject.setParameterList(key, (Collection) value);
				querySqlCount.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				queryObject.setParameterList(key, (Object[]) value);
				querySqlCount.setParameterList(key, (Object[]) value);
			} else {
				queryObject.setParameter(key, value);
				querySqlCount.setParameter(key, value);
			}
		}
		int recordCount = ((Number) querySqlCount.uniqueResult())
				.intValue();
		// 设置返回的结果集大小
		List result = queryObject.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}

	/**
	 * 对底层PageBysql的一个封装
	 * 
	 * @param query
	 * @param values
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	protected DaoPage pageBySql(final StringBuffer query,
			final Map<String, Object> values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);
		String queryString = query.toString();
		Assert.hasText(queryString);
		// 获得记录数
		String queryCountString = "select count(*) "
				+ HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		// 组成统计记录数的sql
		StringBuffer queryCount = new StringBuffer();
		queryCount.append(queryCountString);
		return pageBySql(query, queryCount, values, pageIndex, pageSize);
	}

	/**
	 * sql分页的对外接口
	 * 
	 * @param query
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	protected DaoPage pageBySql(StringBuffer query, final int pageIndex,
			final int pageSize) {
		Map values = new HashMap();
		return pageBy(query, values, pageIndex, pageSize);
	}

	public void updateAllplus(final Collection entities, final int counts) {
		int i = 0;
		Session session=getSession();
		for (Object object : entities) {
			session.saveOrUpdate(object);
			if (counts != 0 && i % counts == 0) {
				session.flush();
				session.clear();
			}
			i++;
		}
	}

	/**
	 * 使用JDBC的delete和update语句进行更新
	 * 
	 * @param query
	 *            sql语句
	 * @param values
	 *            参数
	 * @return 返回一个整型值，反映更新的记录数
	 */
	protected int updateBySql(final StringBuffer query,
			final Map<String, Object> values) {

		final String queryString = query.toString();
		Session session=getSession();
		// 使用jdbc的方法删除数据
		// 创建查询语句
		Query queryObject = session.createSQLQuery(queryString
				.toString());
		
		
		// 设置参数
		for (String key : values.keySet()) {
			Object value = values.get(key);
			queryObject.setParameter(key, value);
		}
		return queryObject.executeUpdate();

	}

	/**
	 * 对结果集进行汇总的查询
	 * 用于在完成一次明细查询后，进行一次汇总查询，汇总之前的明细结果
	 * add by ming.chen 2011年1月14日
	 * @param queryString 查询语句
	 * @param sumItems 需汇总的字段，以逗号分隔
	 * @param values 查询参数
	 * @return
	 */
	public List findByToatal(StringBuffer queryString, String sumItems,
			final Map<String, Object> values) {
		Assert.hasText(queryString.toString());
		//没有汇总字段
		if(sumItems==null){
			return null;
		}
        //解析字符串，获得需要汇总的数据
		StringTokenizer stringTokenizer = new StringTokenizer(sumItems, ",");
		List<String> list = new ArrayList<String>();
		while (stringTokenizer.hasMoreTokens()) {
			list.add(stringTokenizer.nextToken());
		}
		//拼接需要汇总的字段
		String sumString = " ";
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			sumString = sumString + "sum(" + element + "),";
		}
		sumString = sumString.substring(0, sumString.length() - 1) + " ";
		String querySumString = "select  " + sumString
				+ HqlUtils.removeSelect(queryString.toString());
		querySumString = HqlUtils.removeOrders(querySumString);
		return findBy(new StringBuffer(querySumString), values);
	}
	
	public DaoPage pageByJdbcTemplate(StringBuffer sql,List<Object> params,int pageIndex,int pageSize,JdbcTemplate jTemplate){
		Assert.notNull(sql);
		Assert.notNull(jTemplate);
		String queryString = sql.toString();
		Assert.hasText(queryString);
		
		//总记录数
		StringBuffer totalSql = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSql.append(sql);
		totalSql.append(" ) totalTable");
		int count = jTemplate.queryForInt(totalSql.toString(), params.toArray());
	    DaoPage page = new DaoPage(count, pageIndex, pageSize, null);
	    
	    //返回记录
	    StringBuffer listSql = new StringBuffer();
	    listSql.append(" SELECT * from (");
	    listSql.append(" SELECT temp.* ,ROWNUM num FROM ( ");
	    listSql.append(sql);
	    listSql.append(" ) temp");
	    listSql.append(" )");
	    listSql.append(" WHERE num >= " + new Integer(page.getRecordIndex() + 1));
	    listSql.append(" AND num <= " + new Integer(page.getRecordIndex() + pageSize));
	    
	    List result = jTemplate.queryForList(listSql.toString(), params.toArray());
	    page = new DaoPage(count, pageIndex, pageSize, result);
	  
	    return page;
  }
	/**
	 * MaigcWusir add
	 * 
	 * @param queryString
	 * @param map
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DaoPage pagedQueryByMap(String queryString, Map map, int pageIndex, int pageSize) {
		List keys = new ArrayList();
		List values = new ArrayList();
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = map.get(key);
			keys.add(key);
			values.add(value);
		}
		return pagedQueryByNamedParam(queryString.toString(), (String[]) keys.toArray(new String[0]), values.toArray(), pageIndex, pageSize);
	}
	public DaoPage pagedQueryByNamedParam(String queryString, String[] paramNames, Object[] values, int pageIndex, int pageSize) {

		List paramNamesList = new ArrayList();
		for (int k = 0; k < paramNames.length; k++) {
			paramNamesList.add(paramNames[k]);
		}

		List valuesList = new ArrayList();
		for (int m = 0; m < values.length; m++) {
			valuesList.add(values[m]);
		}

		String newQueryString = new String(queryString);

		List substitutes = DateSubstitute.getSubstitute();

		for (int g = 0; g < substitutes.size(); g++) {
			DateSubstitute dateSubstitute = (DateSubstitute) substitutes.get(g);

			for (int n = 0; n < paramNamesList.size(); n++) {
				String paramName = (String) paramNamesList.get(n);

				if (dateSubstitute.getDateParam().equals(paramName)) {
					Object comDateObject = valuesList.get(n);

					if (comDateObject instanceof java.util.Date) {
						Date comDate = (Date) comDateObject;

						if (queryString.indexOf(dateSubstitute.getDateSql()) > -1) {
							// 将comDate替换成to_date('20110331','yyyyMMdd')的格式
							paramNamesList.remove(n);
							valuesList.remove(n);

							newQueryString = newQueryString.replaceAll(dateSubstitute.getDateReplace(), DateUtils.getDateSQL(comDate));
						}
					} else if (comDateObject instanceof java.util.List) {
						List comDates = (List) comDateObject;

						if (queryString.indexOf(dateSubstitute.getDateSql()) > -1) {
							// 将comDate替换成to_date('20110331','yyyyMMdd')的格式
							paramNamesList.remove(n);
							valuesList.remove(n);

							newQueryString = newQueryString.replaceAll(dateSubstitute.getDateReplace(), DateUtils.getDateSetSQL(comDates));
						}

					}
				}
			}
		}

		return pagedQueryByNamedParamByNewWay(newQueryString, (String[]) paramNamesList.toArray((new String[0])), valuesList.toArray(), pageIndex, pageSize);
	}

	private DaoPage pagedQueryByNamedParamByNewWay(String queryString, String[] paramNames, Object[] values, int pageIndex, int pageSize) {
		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Collection) {
				Collection c = (Collection) values[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, paramNames, values, i, paramArray, paramValue);
					paramNames = (String[]) paramArray.toArray(new String[0]);
					values = paramValue.toArray();
				}
			}
		}
		String queryCountString = "select count(*) " + HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		return pagedQueryByNamedParam(queryString, queryCountString, paramNames, values, pageIndex, pageSize);
	}

	/**
	 * 
	 */
	public DaoPage pagedQueryByNameParams(String queryString, String queryCountString, String[] paramNames, Object[] values, int pageIndex, int pageSize) {
		Assert.hasText(queryString);
		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (values[i] instanceof Collection) {
				Collection c = (Collection) values[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, paramNames, values, i, paramArray, paramValue);
					paramArray.clear();
					paramValue.clear();
					queryCountString = splitCollection(queryCountString, paramNames, values, i, paramArray, paramValue);
					paramNames = (String[]) paramArray.toArray(new String[0]);
					values = paramValue.toArray();
				}
			}
		}
		return pagedQueryByNamedParam(queryString, queryCountString, paramNames, values, pageIndex, pageSize);
	}
	private DaoPage pagedQueryByNamedParam(final String queryString, final String queryCountString, final String[] paramNames, final Object[] values, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		AssertHelper.allNotNull(paramNames);
		AssertHelper.allNotNull(values);
		Session session=getSession();
		Query query = session.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof Collection) {
					query.setParameterList(paramNames[i], (Collection) values[i]);
				} else if (values[i] instanceof Object[]) {
					query.setParameterList(paramNames[i], (Object[]) values[i]);
				} else {
					query.setParameter(paramNames[i], values[i]);
				}
			}
		}
		Query queryCount = session.createQuery(queryCountString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				if (values[i] instanceof Collection) {
					queryCount.setParameterList(paramNames[i], (Collection) values[i]);
				} else if (values[i] instanceof Object[]) {
					queryCount.setParameterList(paramNames[i], (Object[]) values[i]);
				} else {
					queryCount.setParameter(paramNames[i], values[i]);
				}
			}
		}
		Object obj = queryCount.uniqueResult();
		int recordCount = (obj == null ? 0 : ((Number) obj).intValue());
		List result = query.setFirstResult(PageUtils.getRecordIndex(pageIndex, pageSize)).setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}
	public List findAll(final String queryString, final Object[] values) throws DataAccessException {
		Session session=getSession();
		Query queryObject = session.createSQLQuery(queryString);
		if ((values != null)) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject.list();
	}
	public void executeSql(final StringBuffer sql, final Map values) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql.toString());
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);
			if (value instanceof Collection) {
				query.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				query.setParameterList(key, (Object[]) value);
			} else {
				query.setParameter(key, value);
			}
		}
		query.executeUpdate();
	}
	/**
	 * @param sql
	 * @param values
	 */
	public void excuteProduce( final String sql, final String[] values ) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql);
		if ((values != null)) {
			for (int i = 0; i < values.length; i++) {
				query.setString(i, values[i]);
			}
		}
		query.executeUpdate();

	}
	public void executeProduce(final StringBuffer sql, final Map values) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql.toString());
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);
			if (value instanceof Collection) {
				query.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				query.setParameterList(key, (Object[]) value);
			} else {
				query.setParameter(key, value);
			}
		}
		query.executeUpdate();
	}
	/**
	 * @param sql
	 * @param values
	 */
	public List findByProduce( final String sql, final String[] values ) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql);
		if ((values != null)) {
			for (int i = 0; i < values.length; i++) {
				query.setString(i, values[i]);
			}
		}
		return query.list();

	}
	public List findByProduce(final StringBuffer sql, final Map values) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql.toString());
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);
			if (value instanceof Collection) {
				query.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				query.setParameterList(key, (Object[]) value);
			} else {
				query.setParameter(key, value);
			}
		}
		return query.list();
	}
	public DaoPage pagedQueryByNamedParam(String queryString, Map values, int pageIndex, int pageSize) {
		Assert.hasText(queryString);
		String queryCountString = "select count(*) " + HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		// new added
		Set keys = values.keySet();
		String[] mapName = new String[keys.size()];
		Object[] mapValue = new Object[keys.size()];
		int i = 0;
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			mapName[i] = key;
			mapValue[i] = values.get(key);
			i++;
		}
		List paramArray = new ArrayList();
		List paramValue = new ArrayList();
		for (i = 0; i < mapValue.length; i++) {
			if (mapValue[i] instanceof Collection) {
				Collection c = (Collection) mapValue[i];
				if (c.size() > 1000) {
					paramArray.clear();
					paramValue.clear();
					queryString = splitCollection(queryString, mapName, mapValue, i, paramArray, paramValue);
					mapName = (String[]) paramArray.toArray(new String[0]);
					mapValue = paramValue.toArray();
				}
			}
		}
		// values.clear();
		// for(i = 0; i < mapName.length; i++) {
		// values.put(mapName[i], mapValue[i]);
		// }
		// end of new added.
		return this.pagedQueryByNamedParam(queryString, mapName, mapValue, pageIndex, pageSize);
		// return pagedQueryByNamedParam(queryString, queryCountString, values,
		// pageIndex, pageSize);
	}
	public DaoPage pagedQueryByHQL(final String queryString, final String queryCountString, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		Session session=getSession();
		Query query = session.createQuery(queryString);
		Query queryCount = session.createQuery(queryCountString);
		int recordCount = ((Number) queryCount.uniqueResult()).intValue();
		List result = query.setFirstResult(PageUtils.getRecordIndex(pageIndex, pageSize)).setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}
	public DaoPage pagedQueryByHQL(final String queryString, final Object[] values, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		String queryCountString = "select count(*) " + HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		return pagedQueryByHQL(queryString, queryCountString, values, pageIndex, pageSize);
	}
	public DaoPage pagedQueryByHQL(final String queryString, final String queryCountString, final Object[] values, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		Assert.hasText(queryCountString);
		AssertHelper.allNotNull(values);
		Session session=getSession();
		Query query = session.createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		Query queryCount = session.createQuery(queryCountString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryCount.setParameter(i, values[i]);
			}
		}
		int recordCount = ((Number) queryCount.uniqueResult()).intValue();
		List result = query.setFirstResult(PageUtils.getRecordIndex(pageIndex, pageSize)).setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}
	/**
	 * Criteria 查询 单一属性"="查询
	 * 
	 * @param property
	 *            查询参数名
	 * @param value
	 *            参数值
	 * @return 结果集
	 * @see BaseHibernateDao#findByCriteria(Map)
	 */
	public List findByCriteria(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		Assert.notNull(value);
		Map filterMap = new HashMap();
		filterMap.put(propertyName, value);
		return findByCriteria(filterMap);
	}

	/**
	 * Criteria 查询 多属性"="查询
	 * 
	 * @param filterMap
	 * @return
	 */
	public List findByCriteria(final Map filterMap) {
		AssertHelper.allNotNull(filterMap.values());
		Assert.notEmpty(filterMap);
		Session session=getSession();
		Criteria criteria = session.createCriteria(getPersistentClass());
		criteria = CriteriaUtils.assemble(criteria, filterMap);
		return criteria.list();
	}

	/**
	 * DetachedCriteria 查询
	 * 
	 * @param filterMap
	 * @return
	 */
	public List findByDetachedCriteria(final DetachedCriteria detachedCriteria) {
		Assert.notNull(detachedCriteria);
		Session session=getSession();
		Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
		List result = executableCriteria.list();
		return result;
	}

	/**
	 * DetachedCriteria 分页查询 某些数据库中不支持count(*)的同时order by，所以去除
	 * 
	 * @param detachedCriteria
	 *            构建好的DetachedCriteria
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return page 分页结果
	 */
	public DaoPage pagedQueryByCriteria(final DetachedCriteria detachedCriteria, final int pageIndex, final int pageSize) {
		Assert.notNull(detachedCriteria);
		Session session=getSession();
		Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
		OrderEntry[] orderEntries = CriteriaUtils.getOrders(executableCriteria);
		executableCriteria = CriteriaUtils.removeOrders(executableCriteria);
		Projection projection = CriteriaUtils.getProjection(executableCriteria);
		int recordCount = ((Integer) executableCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		executableCriteria.setProjection(projection);
		if (projection == null) {
			executableCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		executableCriteria = CriteriaUtils.addOrders(executableCriteria, orderEntries);
		List result = executableCriteria.setFirstResult(PageUtils.getRecordIndex(pageIndex, pageSize)).setMaxResults(pageSize).list();
		return new DaoPage(recordCount, pageIndex, pageSize, result);
	}
	/**
	 * 获取持久CLASS类型
	 * 
	 * @return DAO对应的持久化类的类型
	 */
	protected Class getPersistentClass() {
		return null;
	}

	/**
	 * 根据主键返回持久化对象
	 * 
	 * @see BaseHibernateDao#getPersistentClass()
	 */
	public Object get(Serializable id) {
		return get(getPersistentClass(), id);
	}

	/**
	 * HQL 分页查询 需要传入获取总数的HQL语句，适用于通用方法无法解决的句子 Hibernate 3.2
	 * uniqueResult()返回Long,3.1 返回 Integer
	 * 
	 * @param queryString
	 *            查询语句
	 * @param queryCountString
	 *            记录数查询语句
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @param value
	 *            传入的参数
	 * @return page 分页结果
	 */
	public DaoPage pagedQueryByHQL(final String queryString, final String queryCountString, final Object value, final int pageIndex, final int pageSize) {
		Assert.notNull(value);
		return pagedQueryByHQL(queryString, queryCountString, new Object[] { value }, pageIndex, pageSize);
	}
	/**
	 * HQL 分页查询 自动生成HQL的总纪录数查询语句，不支持group by
	 * 
	 * @param queryString
	 *            hql查询语句
	 * @param pageIndex
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return page 分页结果集
	 */
	public DaoPage pagedQueryByHQL(final String queryString, final int pageIndex, final int pageSize) {
		Assert.hasText(queryString);
		String queryCountString = "select count(*) " + HqlUtils.removeSelect(queryString);
		queryCountString = HqlUtils.removeOrders(queryCountString);
		return pagedQueryByHQL(queryString, queryCountString, pageIndex, pageSize);
	}
	/**
	 * 判断持久化对象的某些属性是否在数据库中重复 通过classMetadata来检查entity是否是一个PO，如果是的话要判断时entity不计入
	 * 
	 * @param entity
	 *            持久化对象
	 * @param properties
	 *            需要判断的属性
	 * @param mode
	 *            true|false 添加不等于主键的设置|不添加不等于主见的设置
	 * @return true 唯一 false 不唯一
	 */
	public boolean isUnique(final Object entity, final String[] propertyNames) {
		AssertHelper.allHasText(propertyNames);
		Assert.notNull(entity);
		Session session=getSession();
		Criteria criteria = session.createCriteria(entity.getClass()).setProjection(Projections.rowCount());
		try {
			for (int i = 0; i < propertyNames.length; i++) {
				Object parse = Ognl.parseExpression(propertyNames[i]);
				Object queryObject = Ognl.getValue(parse, entity);
				criteria.add(Restrictions.eq(propertyNames[i], queryObject));
			}
			ClassMetadata classMetadata = getSessionFactory().getClassMetadata(entity.getClass());
			if (classMetadata != null) {
				String identiferPropertyName = classMetadata.getIdentifierPropertyName();
				Object identiferValue = ReflectUtils.getFieldContent(entity, identiferPropertyName);
				if (identiferValue != null) {
					criteria.add(Restrictions.not(Restrictions.eq(identiferPropertyName, identiferValue)));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("唯一性校验反射异常");
		}
		Object result= criteria.uniqueResult();
		if(result!=null)
		{
			return Boolean.valueOf(Integer.valueOf(result.toString())<1);
		}
		return true;
	}

	public boolean isUnique(Object entity, Collection c) {
		Assert.notNull(c);
		Assert.notEmpty(c);
		return isUnique(entity, (String[]) c.toArray(new String[0]));
	}
	
	public Object getFirstRecord(List list){
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	/**
	 * <pre>
	 * 对数据进行批量处理,依次执行新增、删除、更新操作。
	 * 此方法为protected，子类只能在自己的接口实现中调用。
	 * 
	 * &lt;strong&gt;程序范例：&lt;/strong&gt;
	 * 
	 * VIEW层：
	 * public void loadDatasetSample(DataSet dataSet) throws Exception {
	 *     Map&lt;DataStatus, List&lt;?&gt;&gt; entities = BatchUtils.getBatchEntities(dataSet);
	 *     ...
	 * }
	 * 
	 * SERVICE层：
	 * public void batch(Map&lt;DataStatus, List&lt;?&gt;&gt; entities) {
	 * 		sampleDao.batch(entities);
	 * }
	 * 
	 * 
	 * </pre>
	 * 
	 * @see DataStatus
	 * @param entities
	 * @author dada
	 */
	protected void batch(Map<DataStatus, Collection> entities) {
		Collection updates = entities.get(DataStatus.MODIFIED);
		Collection deletes = entities.get(DataStatus.DELETE);
		Collection inserts = entities.get(DataStatus.INSERT);

		saveAll(inserts);
		removeAll(deletes);
		updateAll(updates);
	}
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException {

		return findByNamedQueryAndNamedParam(queryName, new String[] {paramName}, new Object[] {value});
	}

	public List findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values)
			throws DataAccessException {

		if (paramNames != null && values != null && paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		Session session=getSession();
		Query queryObject = session.getNamedQuery(queryName);
//		prepareQuery(queryObject);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
			}
		}
		return queryObject.list();
	}
	public int queryForInt(String hql){
		return this.queryForInt(hql,null,null, null);
	}
	public int queryForInt(StringBuffer hql,Map<String,Object> parameterMap){
		return this.queryForInt(hql.toString(),null,parameterMap, null);
	}
	public int queryForInt(String hql,Map<String,Object> parameterMap){
		return this.queryForInt(hql,null,parameterMap, null);
	}
	public int queryForInt(String hql,Map<String,Object> parameterMap,String dataSourceName){
		return this.queryForInt(hql,null,parameterMap, dataSourceName);
	}
	public int queryForInt(String hql,Object[] parameters){
		return this.queryForInt(hql,parameters,null, null);
	}
	private int queryForInt(String hql,Object[] parameters,Map<String,Object> parameterMap,String dataSourceName){
		Session session=getSession();
		int count=0;
		Query countQuery=session.createQuery(hql);
		if(parameters!=null){
			setQueryParameters(countQuery,parameters);				
		}else if(parameterMap!=null){
			setQueryParameters(countQuery,parameterMap);								
		}
		Object countObj=countQuery.uniqueResult();
		if(countObj instanceof Long){
			count=((Long)countObj).intValue();
		}else if(countObj instanceof Integer){
			count=((Integer)countObj).intValue();
		}
		return count;
	}
	public Object queryForObject(String hql){
		return this.queryForObject(hql,null,null, null);
	}
	public Object queryForObject(StringBuffer hql,Map<String,Object> parameterMap){
		return this.queryForObject(hql.toString(),null,parameterMap, null);
	}
	public Object queryForObject(String hql,Map<String,Object> parameterMap){
		return this.queryForObject(hql,null,parameterMap, null);
	}
	public Object queryForObject(String hql,Map<String,Object> parameterMap,String dataSourceName){
		return this.queryForObject(hql,null,parameterMap, dataSourceName);
	}
	public Object queryForObject(String hql,Object[] parameters){
		return this.queryForObject(hql,parameters,null, null);
	}
	public Object findForObject(String hql){
		return this.queryForObject(hql,null,null, null);
	}
	public Object findForObject(StringBuffer hql,Map<String,Object> parameterMap){
		return this.queryForObject(hql.toString(),null,parameterMap, null);
	}
	public Object findForObject(String hql,Map<String,Object> parameterMap){
		return this.queryForObject(hql,null,parameterMap, null);
	}
	public Object findForObject(String hql,Map<String,Object> parameterMap,String dataSourceName){
		return this.queryForObject(hql,null,parameterMap, dataSourceName);
	}
	public Object findForObject(String hql,Object[] parameters){
		return this.queryForObject(hql,parameters,null, null);
	}
	private Object queryForObject(final String hql,final Object[] parameters,final Map<String,Object> parameterMap,String dataSourceName){
		Session session=getSession();
		Query countQuery=session.createQuery(hql);
		if(parameters!=null){
			setQueryParameters(countQuery,parameters);				
		}else if(parameterMap!=null){
			setQueryParameters(countQuery,parameterMap);								
		}
		Object countObj=countQuery.uniqueResult();
		return countObj;
	}
	public Object querySqlForObject(String sql){
		return this.querySqlForObject(sql,null,null, null);
	}
	public Object querySqlForObject(StringBuffer sql,Map<String,Object> parameterMap){
		return this.querySqlForObject(sql.toString(),null,parameterMap, null);
	}
	public Object querySqlForObject(String sql,Map<String,Object> parameterMap){
		return this.querySqlForObject(sql,null,parameterMap, null);
	}
	public Object querySqlForObject(String hql,Map<String,Object> parameterMap,String dataSourceName){
		return this.querySqlForObject(hql,null,parameterMap, dataSourceName);
	}
	public Object querySqlForObject(String sql,Object[] parameters){
		return this.querySqlForObject(sql,parameters,null, null);
	}
	public Object findSqlForObject(String sql){
		return this.querySqlForObject(sql,null,null, null);
	}
	public Object findSqlForObject(StringBuffer sql,Map<String,Object> parameterMap){
		return this.querySqlForObject(sql.toString(),null,parameterMap, null);
	}
	public Object findSqlForObject(String sql,Map<String,Object> parameterMap){
		return this.querySqlForObject(sql,null,parameterMap, null);
	}
	public Object findSqlForObject(String sql,Map<String,Object> parameterMap,String dataSourceName){
		return this.querySqlForObject(sql,null,parameterMap, dataSourceName);
	}
	public Object findSqlForObject(String sql,Object[] parameters){
		return this.querySqlForObject(sql,parameters,null, null);
	}
	private Object querySqlForObject(final String sql,final Object[] parameters,final Map<String,Object> parameterMap,String dataSourceName){
		Session session=getSession();
		Query countQuery=session.createSQLQuery(sql);
		if(parameters!=null){
			setQueryParameters(countQuery,parameters);				
		}else if(parameterMap!=null){
			setQueryParameters(countQuery,parameterMap);								
		}
		Object countObj=countQuery.uniqueResult();
		return countObj;
	}
	public void setQueryParameters(Query query,Object[] parameters){
		if(parameters==null)return;
		for(int i=0;i<parameters.length;i++){
			query.setParameter(i, parameters[i]);
		}
	}
	public void setQueryParameters(Query query,Map<String,Object> parameters){
		if(parameters==null)return;
		for(String name:parameters.keySet()){
			Object obj=parameters.get(name);
			if(obj instanceof Collection){
				query.setParameterList(name, (Collection<?>)obj);
			}else if(obj instanceof Object[]){
				query.setParameterList(name, (Object[])obj);
			}else{
				query.setParameter(name, obj);
			}
		}
	}

	@Override
	public Integer executeProduce(String sql, Map values) {
		Session session=getSession();
		Query query = session.createSQLQuery(sql);
		if ((values != null)) {
			for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = values.get(key);
				if (value instanceof Collection) {
					query.setParameterList(key, (Collection) value);
				} else if (value instanceof Object[]) {
					query.setParameterList(key, (Object[]) value);
				} else {
					query.setParameter(key, value);
				}
			}		}
		return query.executeUpdate();
	}
	@Override
	public Integer executeHqlProduce(String sql, Map values) {
		Session session=getSession();
		Query query = session.createQuery(sql);
		if ((values != null)) {
			for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object value = values.get(key);
				if (value instanceof Collection) {
					query.setParameterList(key, (Collection) value);
				} else if (value instanceof Object[]) {
					query.setParameterList(key, (Object[]) value);
				} else {
					query.setParameter(key, value);
				}
			}		}
		return query.executeUpdate();
	}
	public List findBy(StringBuffer hql, Map params, Integer pageIndex,
			Integer pageSize) {
		return findBy(hql, params, (int)pageIndex, (int)pageSize);
	}
	public List findBy(StringBuffer query,
			final Map values, final int pageIndex,
			final int pageSize) {
		Assert.notNull(query);

		final String queryString = query.toString();

		Assert.hasText(queryString);
		
		Session session=getSession();
		Query hibernateQuery = session.createQuery(queryString);
		for (Iterator iter = values.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			Object value = values.get(key);

			if (value instanceof Collection) {
				hibernateQuery
						.setParameterList(key, (Collection) value);
			} else if (value instanceof Object[]) {
				hibernateQuery.setParameterList(key, (Object[]) value);
			} else {
				hibernateQuery.setParameter(key, value);
			}
		}
		List result = hibernateQuery.setFirstResult(
				PageUtils.getRecordIndex(pageIndex, pageSize))
				.setMaxResults(pageSize).list();
		return result;
	}

	@Override
	public List findBy(StringBuffer hql, Map params) {
		return findBy(hql.toString(), params);
	}

	@Override
	public Long countBy(StringBuffer hql, Map params) {
		int num=queryForInt(hql,(Map<String,Object>)params);
		return Long.valueOf(num);
	}
	@Override
	public Date getDatabaseServerDate(){
		  Date dt = null;
		  List result = null;		
		  String dialect = ((SessionImpl) getSession()).getFactory().getDialect().getClass().getName();           
		  if (dialect == null)
		   return dt;

		  if (dialect.toLowerCase().contains("oracle")) {
		   result = this
		     .getSession()
		     .createSQLQuery(
		       "SELECT  to_char(SYSTIMESTAMP,'yyyy-mm-dd hh24:mi:ssxff') FROM DUAL")
		     .list();		   
		   dt = DateUtils.parseTime(result.get(0).toString(),"yyyy-MM-dd HH:mm:ss.SSS");
		  } else if (dialect.toLowerCase().contains("sqlserver")
		    || dialect.toLowerCase().contains("sybase")) {
		   result = this.getSession().createSQLQuery("select getdate()")
		     .list();
		   dt = (Date) result.get(0);
		  } else if (dialect.toLowerCase().contains("db2")) {
		   result = this.getSession()
		     .createSQLQuery("VALUES CURRENT TIMESTAMP").list();
		   dt = (Date) result.get(0);

		  } else if (dialect.toLowerCase().contains("mysql")) {
		   result = this.getSession()
		     .createSQLQuery("select FROM_UNIXTIME(unix_timestamp) ")
		     .list();
		   dt = (Date) result.get(0);

		  }
		  return dt;

	}
}
