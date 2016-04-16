package com.deloitte.tms.pl.core.context;
//package com.ling2.security.context;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.ling2.security.Configure;
//import com.ling2.security.io.ResourceLoader;
//import com.ling2.security.pkgs.PackageConfigurer;
//
//public class CoreContextLocationConfigurer implements PackageConfigurer {
//
//	public String[] getPropertiesConfigLocations(ResourceLoader resourceLoader) throws Exception {
//		return new String[]{"classpath:com/bstek/bdf2/core/config/ling2.core.properties"};
//	}
//
//	public String[] getContextConfigLocations(ResourceLoader resourceLoader) throws Exception {
//		String contextCore="classpath:com/bstek/bdf2/core/config/bdf2-core-configs.xml";
//		String contextSecurity="classpath:com/bstek/bdf2/core/config/bdf2-security-configs.xml";
//		String dataSourceContextLocation=(String) PropertiesUtils.get("ling2.dataSourceContextLocation");
//		if(StringUtils.isNotEmpty(dataSourceContextLocation)){
//			return new String[]{contextCore,contextSecurity,dataSourceContextLocation};			
//		}else{
//			return new String[]{contextCore,contextSecurity};						
//		}
//	}
//
//	public String[] getServletContextConfigLocations(ResourceLoader resourceLoader) throws Exception {
//		return null;
//	}
//}
