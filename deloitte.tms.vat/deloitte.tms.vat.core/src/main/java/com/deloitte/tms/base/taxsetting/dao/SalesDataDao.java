package com.deloitte.tms.base.taxsetting.dao;

import com.deloitte.tms.base.taxsetting.model.TaxSettingParam;


public interface SalesDataDao{
	public static final String BEAN_ID="salesDataDao";
	public void SalesDataJob(final TaxSettingParam param);
}

