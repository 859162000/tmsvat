package com.deloitte.tms.pl.core.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.CriteriaImpl.OrderEntry;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;



@SuppressWarnings("unchecked")
public class CriteriaUtils {

	/**
	 * CRITERIA 组装方法
	 * 提供了最基本的查询条件组
	 * @param criteria
	 * @param filterMap
	 * @return 组装完成的criteria
	 */
	public static Criteria assemble(Criteria criteria, Map filterMap) {
		Assert.notNull(criteria);
		Assert.notEmpty(filterMap);
		Iterator<?> iterator = filterMap.keySet().iterator();
		while (iterator.hasNext()) {
			String propertyName = (String) iterator.next();
			Assert.notNull(propertyName);
			Object value =  filterMap.get(propertyName);
			Assert.notNull(value);
			criteria.add(Restrictions.eq(propertyName, value));
		}
		return criteria;
	}
	
	/**
	 * 获取Criteria的Projection
	 * 
	 * @param criteria criteria查询
	 * @return projection
	 */
    public static Projection getProjection(Criteria criteria) {
        CriteriaImpl impl = (CriteriaImpl) criteria;
        return impl.getProjection();
    }

    /**
     * 获取Criteria的Orders
     * 
     * @param criteria criteria查询
     * @return orderEntry[]
     */
    @SuppressWarnings("unchecked")
	public static OrderEntry[] getOrders(Criteria criteria) {
        CriteriaImpl impl = (CriteriaImpl) criteria;
        Field field = getOrderEntriesField(criteria);
        try {
            return (OrderEntry[]) ((List) field.get(impl)).toArray(new OrderEntry[0]);
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility can't throw ");
        }
    }

    /**
     * 移除Criteria的Orders
     * 
     * @param criteria
     * @return 移除完Orders的criteria
     */
    public static Criteria removeOrders(Criteria criteria) {
        CriteriaImpl impl = (CriteriaImpl) criteria;
        try {
            Field field = getOrderEntriesField(criteria);
            field.set(impl, new ArrayList());
            return impl;
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility can't throw ");
        }
    }

    /**
     * 添加Criteria的Orders
     * 
     * @param criteria
     * @param orderEntries
     * @return 添加完Orders的criteria
     */
    @SuppressWarnings("unchecked")
	public static Criteria addOrders(Criteria criteria, OrderEntry[] orderEntries) {
        CriteriaImpl impl = (CriteriaImpl) criteria;
        try {
            Field field = getOrderEntriesField(criteria);
            for (int i = 0; i < orderEntries.length; i++) {
                Object obj = field.get(criteria);
                
                if (obj instanceof List) {
                	List innerOrderEntries = (List) obj;
                	innerOrderEntries.add(orderEntries[i]);
                }
            }
            return impl;
        } catch (Exception e) {
            throw new InternalError(" Runtime Exception impossibility can't throw ");
        }
    }

    /**
     * 获取OrderEntry的Field对象
     * 
     * @param criteria
     * @return field
     */
    private static Field getOrderEntriesField(Criteria criteria) {
        Assert.notNull(criteria, " criteria is requried. ");
        try {
            Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            throw new InternalError();
        }
    }
    
    /**
     * 判断detachedCriteria是否有效
     * 约定如下：如果detachedCriteria的order、criterion、projection没有的话则无效，返回false
     * 
     * @param detachedCriteria 查询的DetachedCriteria
     * @return 是否有效
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static boolean isAvailableDetachedCriteria(DetachedCriteria detachedCriteria) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
    	CriteriaImpl criteriaImpl = (CriteriaImpl) ReflectUtils.getPropertyValue(detachedCriteria, "impl");
		List criterionEntries = (List) ReflectUtils.getPropertyValue(criteriaImpl, "criterionEntries");
		List orderEntries = (List) ReflectUtils.getPropertyValue(criteriaImpl, "orderEntries");
		Projection projection = (Projection) ReflectUtils.getPropertyValue(criteriaImpl, "projection");
		if(criterionEntries != null && criterionEntries.size() > 0) {
			return true;
		}
		if(orderEntries != null && orderEntries.size() > 0) {
			return true;
		}
		if(projection != null) {
			return true;
		}
		return false;
    }
    
}
