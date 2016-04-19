package com.deloitte.tms.vat.salesinvoice.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.PrintOrgCacheUtils;
import com.deloitte.tms.base.enums.PrintRangeEnums;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.vat.base.enums.CrvatTaxPoolStatuEnums;

@Component("citicExtractInvoicePoolDao")
public class CiticExtractInvoicePoolDaoImpl extends AbstractExtractInvoicePoolDaoImpl {

	@Override
	public void buildInvoiceTrxPoolQuery(StringBuffer query, Map values,
			Map params) {
       query.append(" from InvoiceTrxPool where 1=1");		
		//交易流水号
		if(AssertHelper.isOrNotEmpty_assert(params.get("trxNumber"))){
			query.append(" and trxNumber=:trxNumber");
			values.put("trxNumber",params.get("trxNumber") );
		}
		else{
			if(AssertHelper.isOrNotEmpty_assert(params.get("customerId"))){
				query.append(" and customerId=:customerId ");
				values.put("customerId", params.get("customerId"));
			}
			if(AssertHelper.isOrNotEmpty_assert(params.get("custRegistrationCode"))){
				query.append(" and custRegistrationCode=:custRegistrationCode ");
				values.put("custRegistrationCode", params.get("custRegistrationCode"));
			}
			if(AssertHelper.isOrNotEmpty_assert(params.get("custRegistrationNumber"))){
				query.append(" and custRegistrationNumber=:custRegistrationNumber");
				values.put("custRegistrationNumber", params.get("custRegistrationNumber"));
			}if(AssertHelper.isOrNotEmpty_assert(params.get("isExitsCustomer"))){
				if("1".equals(params.get("isExitsCustomer"))){
					query.append(" and customerId is null");
				}else if("0".equals(params.get("isExitsCustomer"))){
					query.append(" and customerId is not null");
				}
			}if(AssertHelper.isOrNotEmpty_assert(params.get("trxStartDate"))&&AssertHelper.isOrNotEmpty_assert(params.get("trxEndDate"))){
				query.append(" and (trxDate between :startDate and :endDate)");
				Date  datefrom =  DateUtils.parse(params.get("trxStartDate").toString(), "yyyy-MM-dd");
			    Date  dateTo=  DateUtils.parse(params.get("trxEndDate").toString(), "yyyy-MM-dd");
				values.put("startDate",datefrom );
				values.put("endDate", dateTo);
			}if(AssertHelper.isOrNotEmpty_assert(params.get("inventoryItemNumber"))){
				query.append(" and inventoryItemNumber=:inventoryItemNumber");
				values.put("inventoryItemNumber", params.get("inventoryItemNumber"));
			}
			if(AssertHelper.isOrNotEmpty_assert(params.get("customerId"))){
				String customerId = (String)params.get("customerId");
				String orgId = (String)params.get("orgId");
				List<String> customerBankAccounts = PrintOrgCacheUtils.getCustomerBankAccountsByCustomerIdAndOrgId(orgId, customerId);
				String[] arrayCustomerBankAccounts = customerBankAccounts.toArray(new String[customerBankAccounts.size()]);
				query.append(" and custmerBankAccountNum in :custmerBankAccountNum");
				values.put("custmerBankAccountNum", arrayCustomerBankAccounts);
			}		
		}
		query.append(" and ( status in :status");
		String[] status=new String[]{CrvatTaxPoolStatuEnums.OPEN.getValue(),CrvatTaxPoolStatuEnums.APPFORM_REVOKED.getValue(),CrvatTaxPoolStatuEnums.PREP_FORM_REVOKED.getValue()};
		values.put("status", status);
		query.append(" or status is null )");
		query.append(" and flag = 1");
		
	}
	
	/**
	 * 获取受理层级枚举
	 */
	private PrintRangeEnums findEnumsByValue(String value){
		if("1".equals(value)){
			return PrintRangeEnums.all;
		}else if("2".equals(value)){
			return PrintRangeEnums.current;
		}else if("3".equals(value)){
			return PrintRangeEnums.city;
		}
		return null;
		
	}

}
