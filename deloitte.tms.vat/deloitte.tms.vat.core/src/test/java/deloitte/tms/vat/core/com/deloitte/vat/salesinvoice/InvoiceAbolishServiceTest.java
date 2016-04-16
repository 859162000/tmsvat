
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceAbolishServiceTest.java  
 * @Package: deloitte.tms.vat.core.com.deloitte.vat.salesinvoice  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月20日 上午12:07:55  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package deloitte.tms.vat.core.com.deloitte.vat.salesinvoice;

import java.util.HashMap;
import java.util.Map;

import com.deloitte.tms.vat.salesinvoice.service.InvoiceAbolishService;
import com.deloitte.tms.vat.salesinvoice.service.impl.InvoiceAbolishServiceImpl;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月20日 上午12:07:55 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceAbolishServiceTest {

	public static void main(String[] args) {
		InvoiceAbolishService invoiceAbolishService = new InvoiceAbolishServiceImpl();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("typeName","test");
		
//		invoiceAbolishService.findInvoiceAbolishByParams(map, 1, 10);
	}
}
