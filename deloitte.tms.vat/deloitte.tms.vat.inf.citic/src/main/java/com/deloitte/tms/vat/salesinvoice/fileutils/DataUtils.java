
/**    
 * Copyright (C),Deloitte
 * @FileName: FormatDataUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.fileutils  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年4月17日 下午11:51:29  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.fileutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年4月17日 下午11:51:29 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class DataUtils {

	public static String getNumeric(String str){
//		Pattern pattern = Pattern.compile("^(\\d+)(.*)");
//		Matcher isNum = pattern.matcher(str);
//		
//		if( !isNum.matches() ){
//	       return isNum.group(1);
//		}
//	   return null;
		return str;
	}
}
