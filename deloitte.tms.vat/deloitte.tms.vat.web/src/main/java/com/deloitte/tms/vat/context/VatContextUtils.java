package com.deloitte.tms.vat.context;

import com.deloitte.tms.pl.core.context.utils.ContextUtils;

public class VatContextUtils extends ContextUtils{
	
	public static String getCurrentLegalId()
	{
		return getCachedProperty(VatContextDef.CURRENT_LEGAL_ENTITY_ID);
	}
}
