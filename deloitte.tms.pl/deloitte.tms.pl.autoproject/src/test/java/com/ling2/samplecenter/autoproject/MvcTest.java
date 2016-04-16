package com.ling2.samplecenter.autoproject;

import com.deloitte.tms.base.taxsetting.model.TmsMdFlexValueSets;
import com.deloitte.tms.base.taxsetting.model.TmsMdFlexValues;
import com.deloitte.tms.pl.autoproject.model.OneToManyPojo;
import com.deloitte.tms.pl.autoproject.utils.AutoProjectUtils;



public class MvcTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resultfolder="D:\\workspace\\source\\deloitte.tms.parent\\deloitte.tms.vat\\deloitte.tms.vat.core";
		String viewresultfolder="D:\\workspace\\source\\deloitte.tms.parent\\deloitte.tms.vat\\deloitte.tms.vat.web";	
		getOrderViewFile(resultfolder,viewresultfolder);
	}
	private static void getOrderViewFile(String resultfolder,String viewresultfolder) {
		OneToManyPojo oneToManyPojo=new OneToManyPojo();
		oneToManyPojo.setOne(AutoProjectUtils.revertClass2Pojo(TmsMdFlexValueSets.class));
		oneToManyPojo.addMany(TmsMdFlexValues.class);
		AutoProjectUtils.executeMvcJsp(resultfolder,viewresultfolder,oneToManyPojo );
		//TmsMdCurrencyRate  TmsMdFlexStructures TmsMdFlexSegments
		//TmsMdFlexValueSets TmsMdFlexValues
		//TmsMdInventoryCategories TmsMdInventoryItems
		// TmsMdOrgBsnStructures
		//TmsMdOrgParameter
		
		//TmsMdTrxAffirmSetting 
		
		
		//
	}
}
