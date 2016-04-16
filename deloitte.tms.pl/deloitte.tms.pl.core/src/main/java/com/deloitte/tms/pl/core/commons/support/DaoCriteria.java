package com.deloitte.tms.pl.core.commons.support;

import java.util.ArrayList;
import java.util.List;

public class DaoCriteria implements Cloneable {
	List<DaoCriterion> criterions = new ArrayList<DaoCriterion>();
	List<DaoOrder> orders = new ArrayList<DaoOrder>();

	public void addCriterion(DaoCriterion criterion) {
		criterions.add(criterion);
	}

	public List<DaoCriterion> getCriterions() {
		return criterions;
	}

	public void addOrder(DaoOrder order) {
		orders.add(order);
	}

	public List<DaoOrder> getOrders() {
		return orders;
	}
}