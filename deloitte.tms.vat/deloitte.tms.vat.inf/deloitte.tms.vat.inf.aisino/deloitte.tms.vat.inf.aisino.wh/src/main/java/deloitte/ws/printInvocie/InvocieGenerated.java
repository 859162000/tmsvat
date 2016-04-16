//package deloitte.ws.printInvocie;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.DecimalFormat;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.webbuilder.utils.WebUtil;
//import com.xzsoft.xip.platform.util.PlatformUtil;
//
//import sajt.webservice.ws.service.SajtIssueInvoiceServiceStub;
//import sajt.webservice.ws.service.SaveDocument;
//import sajt.webservice.ws.service.SaveDocumentResponse;
//
//public class InvocieGenerated {
//	
//	private static String  machine_tax_id="31010105298235X";
//	private static String machine_id="0";  
//	DecimalFormat dformat = new DecimalFormat("0.00");
//	private static String url="http://192.168.1.103:8085/axis2/services/SajtIssueInvoiceService";  
//	private  static SajtIssueInvoiceServiceStub stub ;
//	private static SaveDocumentResponse response;
//	private static String returnMsg;
//	private static String str_code;
//	private static String str_num; 
//    
//	public static void main(String[] machine_tax_id) throws Exception {
///*		HttpServletRequest request=null;
//		 HttpServletResponse response=null;*/
//		 printInv(null,null);
//	}
//
//  public static void printInv(HttpServletRequest request, HttpServletResponse response)
//    throws Exception
//  {
//	  stub=new SajtIssueInvoiceServiceStub(url); 	 
///*      String PRINT_POOL_H_ID = request.getParameter("PRINT_POOL_H_ID");
//	  String INVOICE_TYPE = request.getParameter("INVOICE_TYPE");
//	  String SOURCE_TYPE = request.getParameter("SOURCE_TYPE");*/
//	//String PRINT_POOL_H_ID ="FE8A4D63D8E54E3AB8F615DDA27F85DF";
//	//String INVOICE_TYPE="1";
//	//String SOURCE_TYPE="RED"; //PRN
//	  String PRINT_POOL_H_ID="617BB516A6434AD3A0EB7C5D6A9D4D24";
//	  String INVOICE_TYPE="1";
//	  String SOURCE_TYPE="RED";
//    System.out.println("发票打印池主ID：" + PRINT_POOL_H_ID);
//    System.out.println("发票类型：" + INVOICE_TYPE);
//    System.out.println("发票性质：" + SOURCE_TYPE);
//    int flag=1;  //0成功 1失败
//
//    //machine_tax_id = PlatformUtil.getXipConfigVal("machine.tax.id");
//   // machine_id = PlatformUtil.getXipConfigVal("machine.id");
//    //url= PlatformUtil.getXipConfigVal("Sajt.IssueInvoice.ServiceStub.Url");
//    
//    
//    //专票 1
//    if(Integer.parseInt(INVOICE_TYPE)==1){
//    	if(StringUtils.equalsIgnoreCase(SOURCE_TYPE, "RED")){
//    		flag=printSpecialRedInv(PRINT_POOL_H_ID);
//    	}else{
//    		flag=printSpecialInv(PRINT_POOL_H_ID);
//    	}
//    	
//    }else{
//    	if(StringUtils.equalsIgnoreCase(SOURCE_TYPE, "RED")){
//    		flag=printNormalRedInv(PRINT_POOL_H_ID);
//    	}else{
//    		flag=printNormalInv(PRINT_POOL_H_ID);
//    	}
//    	
//    }
//    
//    if(flag==0){
//   	 	WebUtil.response(response, "{msg:'发票打印成功',success:true,flag:0}");
//    }else{
//   	    WebUtil.response(response, "{msg:'发票打印失败',success:true,flag:1}");
//    }
//  }
//  
//   /*  正常专用发票*/
//  public static int printSpecialInv(String PRINT_POOL_H_ID) throws Exception{
//	  StringBuffer commandGen = new StringBuffer();
//	  StringBuffer errorMsg = new StringBuffer();
//	  
//	  VATInvoiceSpecial resultSet= InvocieGenerated.queryVATInvoiceSpecial(PRINT_POOL_H_ID);
//	  if(resultSet!=null){
//		  commandGen.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
//		            .append("<issueinv><invrecord><invrecordhead><machinetaxnr>").append(machine_tax_id)
//		            .append("</machinetaxnr><machinenr>").append(machine_id)
//		            .append("</machinenr><invkind><value>").append(0)
//		            .append("</value></invkind><doctype><value>").append(1)
//		            .append("</value></doctype><docnr>").append(resultSet.getBus_code())
//		            .append("</docnr><docdate>").append(resultSet.getDocdate())
//		            .append("</docdate><custnr>").append(resultSet.getBuyer_code())
//		            .append("</custnr><custname>").append(resultSet.getBuyer_name())
//		            .append("</custname><custtaxnr>").append(resultSet.getBuyer_taxpayer_no())
//		            .append("</custtaxnr><custaddrtel>").append(resultSet.getBuyer_address()).append(" ").append(resultSet.getBuyer_phone())
//		            .append("</custaddrtel><custbankacct>").append(resultSet.getBuyer_bank()).append(" ").append(resultSet.getBuyer_bank_account())
//		            .append("</custbankacct><memo>").append(resultSet.getInv_contents())
//		            .append("</memo><memo2></memo2><refinvcode></refinvcode><refinvnr></refinvnr>")
//		            .append("<rednoticenr></rednoticenr><issuer>").append(resultSet.getWrite_inv())
//		            .append("</issuer><checker></checker><payee></payee><sellerbankacct>").append(resultSet.getSale_bank()).append(" ").append(resultSet.getSale_bank_account())
//		            .append("</sellerbankacct><selleraddresstel>").append(resultSet.getSale_phone_address()).append(" ").append(resultSet.getSale_phone())
//		            .append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>").append(resultSet.getProduct_code())
//		            .append("</prodnr><prodname>").append(resultSet.getProduct_name())
//		            .append("</prodname><spec>").append(resultSet.getProduct_style())
//		            .append("</spec><unit>").append(resultSet.getProduct_unit())
//		            .append("</unit><quantity>1</quantity><price>").append(resultSet.getProduct_price())
//		            .append("</price><amount>").append(resultSet.getProduct_price())
//		            .append("</amount><taxrate>").append(resultSet.getTax_rate())
//		            .append("</taxrate><tax>").append(resultSet.getTax_val())
//		            .append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
//		            .append("</invrecord></issueinv></siiscmd>");
//		  
//		  			System.out.println(commandGen);
//					SaveDocument sd=new SaveDocument();
//					sd.setCommand(commandGen.toString());
//					response = stub.saveDocument(sd);
//					returnMsg="";
//					//S成功，F失败  推送信息
//					if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//						returnMsg = response.get_return().getCmdMessage();	
//						str_code = getINVInformation(returnMsg,"<binvcode>","</binvcode>");
//						str_num = getINVInformation(returnMsg,"<binvnr>","</binvnr>");
//						
//						String pcommand=GeneratPrintCommand(str_code,str_num,0);
//						SaveDocument sd2=new SaveDocument();
//						sd2.setCommand(pcommand);
//						response = stub.saveDocument(sd2);
//						//打印发票成功
//						if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//							return 0;
//						}else{
//							return 1;
//						}
//					}else{
//						//错误信息
//						return 1;
//					}
//
//	  }else{
// 		 errorMsg.append("error ");
// 		return 1;
//		}	  
//			  
//  }
//  
//  /*  正常普通发票*/
// public static int printNormalInv(String PRINT_POOL_H_ID) throws Exception{
//	  StringBuffer commandGen = new StringBuffer();
//	  StringBuffer errorMsg = new StringBuffer();
//	  
//	  VATInvoiceSpecial resultSet= InvocieGenerated.queryVATInvoiceSpecial(PRINT_POOL_H_ID);
//	  if(resultSet!=null){
//		  commandGen.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
//		            .append("<issueinv><invrecord><invrecordhead><machinetaxnr>").append(machine_tax_id)
//		            .append("</machinetaxnr><machinenr>").append(machine_id)
//		            .append("</machinenr><invkind><value>").append(2)
//		            .append("</value></invkind><doctype><value>").append(1)
//		            .append("</value></doctype><docnr>").append(resultSet.getBus_code())
//		            .append("</docnr><docdate>").append(resultSet.getDocdate())
//		            .append("</docdate><custnr>").append(resultSet.getBuyer_code())
//		            .append("</custnr><custname>").append(resultSet.getBuyer_name())
//		            .append("</custname><custtaxnr>").append(resultSet.getBuyer_taxpayer_no())
//		            .append("</custtaxnr><custaddrtel>").append(resultSet.getBuyer_address()).append(" ").append(resultSet.getBuyer_phone())
//		            .append("</custaddrtel><custbankacct>").append(resultSet.getBuyer_bank()).append(" ").append(resultSet.getBuyer_bank_account())
//		            .append("</custbankacct><memo>").append(resultSet.getInv_contents())
//		            .append("</memo><memo2></memo2><refinvcode></refinvcode><refinvnr></refinvnr>")
//		            .append("<rednoticenr></rednoticenr><issuer>").append(resultSet.getWrite_inv())
//		            .append("</issuer><checker></checker><payee></payee><sellerbankacct>").append(resultSet.getSale_bank()).append(" ").append(resultSet.getSale_bank_account())
//		            .append("</sellerbankacct><selleraddresstel>").append(resultSet.getSale_phone_address()).append(" ").append(resultSet.getSale_phone())
//		            .append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>").append(resultSet.getProduct_code())
//		            .append("</prodnr><prodname>").append(resultSet.getProduct_name())
//		            .append("</prodname><spec>").append(resultSet.getProduct_style())
//		            .append("</spec><unit>").append(resultSet.getProduct_unit())
//		            .append("</unit><quantity>1</quantity><price>").append(resultSet.getProduct_price())
//		            .append("</price><amount>").append(resultSet.getProduct_price())
//		            .append("</amount><taxrate>").append(resultSet.getTax_rate())
//		            .append("</taxrate><tax>").append(resultSet.getTax_val())
//		            .append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
//		            .append("</invrecord></issueinv></siiscmd>");
//		  
//		  			System.out.println(commandGen);
//		  				SaveDocument sd=new SaveDocument();
//		  				sd.setCommand(commandGen.toString());
//		  				response = stub.saveDocument(sd);
//		  				returnMsg="";
//			//S成功，F失败
//			if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//				returnMsg = response.get_return().getCmdMessage();	
//				str_code = getINVInformation(returnMsg,"<binvcode>","</binvcode>");
//				str_num = getINVInformation(returnMsg,"<binvnr>","</binvnr>");
//				
//				String pcommand=GeneratPrintCommand(str_code,str_num,2);
//				SaveDocument sd2=new SaveDocument();
//				sd2.setCommand(pcommand);
//				response = stub.saveDocument(sd2);
//				//打印发票成功
//				if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//					return 0;
//				}else{
//					return 1;
//				}
//			}else{
//				return 1;
//			}
//			
//	  }else{
//		 errorMsg.append("error ");
//		 return 1;
//		}	  
//			  
// }
// 
// 
// /*  红冲专票单据*/
//public static int printSpecialRedInv(String PRINT_POOL_H_ID) throws Exception{
//	  StringBuffer commandGen = new StringBuffer();
//	  StringBuffer errorMsg = new StringBuffer();
//	  
//	  VATInvoiceRed resultSet= InvocieGenerated.queryVATInvoiceRed(PRINT_POOL_H_ID);
//	  if(resultSet!=null){
//		
//		  commandGen.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
//		            .append("<issueinv><invrecord><invrecordhead><machinetaxnr>").append(machine_tax_id)
//		            .append("</machinetaxnr><machinenr>").append(machine_id)
//		            .append("</machinenr><invkind><value>").append(0)
//		            .append("</value></invkind><doctype><value>").append(2)
//		            .append("</value></doctype><docnr>").append(resultSet.getBus_code())
//		            .append("</docnr><docdate>").append(resultSet.getDocdate())
//		            .append("</docdate><custnr>").append(resultSet.getBuyer_code())
//		            .append("</custnr><custname>").append(resultSet.getBuyer_name())
//		            .append("</custname><custtaxnr>").append(resultSet.getBuyer_taxpayer_no())
//		            .append("</custtaxnr><custaddrtel>").append(resultSet.getBuyer_address()).append(" ").append(resultSet.getBuyer_phone())
//		            .append("</custaddrtel><custbankacct>").append(resultSet.getBuyer_bank()).append(" ").append(resultSet.getBuyer_bank_account())
//		            .append("</custbankacct><memo>").append(resultSet.getInv_contents())
//		            .append("</memo><memo2></memo2><refinvcode>").append(resultSet.getRefinvcode())
//		            .append("</refinvcode><refinvnr>").append(resultSet.getRefInvnr()).append("</refinvnr>")
//		            .append("<rednoticenr>").append(resultSet.getRednoticenr()).append("</rednoticenr><issuer>").append(resultSet.getWrite_inv())
//		            .append("</issuer><checker></checker><payee></payee><sellerbankacct>").append(resultSet.getSale_bank()).append(" ").append(resultSet.getSale_bank_account())
//		            .append("</sellerbankacct><selleraddresstel>").append(resultSet.getSale_phone_address()).append(" ").append(resultSet.getSale_phone())
//		            .append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>").append(resultSet.getProduct_code())
//		            .append("</prodnr><prodname>").append(resultSet.getProduct_name())
//		            .append("</prodname><spec>")
//		            .append("</spec><unit>")
//		            .append("</unit><quantity>1</quantity><price>").append(resultSet.getProduct_price())
//		            .append("</price><amount>").append(resultSet.getProduct_price())
//		            .append("</amount><taxrate>").append(resultSet.getTax_rate())
//		            .append("</taxrate><tax>").append(resultSet.getTax_val())
//		            .append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
//		            .append("</invrecord></issueinv></siiscmd>");
//
//			System.out.println(commandGen);
//				SaveDocument sd=new SaveDocument();
//				sd.setCommand(commandGen.toString());
//				SaveDocumentResponse response = stub.saveDocument(sd);
//				returnMsg="";
//				//S成功，F失败
//				if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//					returnMsg = response.get_return().getCmdMessage();	
//					str_code = getINVInformation(returnMsg,"<binvcode>","</binvcode>");
//					str_num = getINVInformation(returnMsg,"<binvnr>","</binvnr>");
//					
//					String pcommand=GeneratPrintCommand(str_code,str_num,0);
//					SaveDocument sd2=new SaveDocument();
//					sd2.setCommand(pcommand);
//					response = stub.saveDocument(sd2);
//					//打印发票成功
//					if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//						return 0;
//					}else{
//						return 1;
//					}
//				}else{
//					return 1;
//				}
//				
//		 
//	  }else{
//		 errorMsg.append("error");
//		 return 1;
//		}	  
//			  
//}
//
///*  红冲普票单据*/
//public static int printNormalRedInv(String PRINT_POOL_H_ID) throws Exception{
//	  StringBuffer commandGen = new StringBuffer();
//	  StringBuffer errorMsg = new StringBuffer();
//	  
//	  VATInvoiceRed resultSet= InvocieGenerated.queryVATInvoiceRed(PRINT_POOL_H_ID);
//	  if(resultSet!=null){
//		
//		  commandGen.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.111cn\">")
//		            .append("<issueinv><invrecord><invrecordhead><machinetaxnr>").append(machine_tax_id)
//		            .append("</machinetaxnr><machinenr>").append(machine_id)
//		            .append("</machinenr><invkind><value>").append(2)
//		            .append("</value></invkind><doctype><value>").append(2)
//		            .append("</value></doctype><docnr>").append(resultSet.getBus_code())
//		            .append("</docnr><docdate>").append(resultSet.getDocdate())
//		            .append("</docdate><custnr>").append(resultSet.getBuyer_code())
//		            .append("</custnr><custname>").append(resultSet.getBuyer_name())
//		            .append("</custname><custtaxnr>").append(resultSet.getBuyer_taxpayer_no())
//		            .append("</custtaxnr><custaddrtel>").append(resultSet.getBuyer_address()).append(" ").append(resultSet.getBuyer_phone())
//		            .append("</custaddrtel><custbankacct>").append(resultSet.getBuyer_bank()).append(" ").append(resultSet.getBuyer_bank_account())
//		            .append("</custbankacct><memo>").append(resultSet.getInv_contents())
//		            .append("</memo><memo2></memo2><refinvcode>").append(resultSet.getRefinvcode())
//		            .append("</refinvcode><refinvnr>").append(resultSet.getRefInvnr()).append("</refinvnr>")
//		            .append("<rednoticenr>").append(resultSet.getRednoticenr()).append("</rednoticenr><issuer>").append(resultSet.getWrite_inv())
//		            .append("</issuer><checker></checker><payee></payee><sellerbankacct>").append(resultSet.getSale_bank()).append(" ").append(resultSet.getSale_bank_account())
//		            .append("</sellerbankacct><selleraddresstel>").append(resultSet.getSale_phone_address()).append(" ").append(resultSet.getSale_phone())
//		            .append("</selleraddresstel></invrecordhead><invrecorditem><prodnr>").append(resultSet.getProduct_code())
//		            .append("</prodnr><prodname>").append(resultSet.getProduct_name())
//		            .append("</prodname><spec>")
//		            .append("</spec><unit>")
//		            .append("</unit><quantity>1</quantity><price>").append(resultSet.getProduct_price())
//		            .append("</price><amount>").append(resultSet.getProduct_price())
//		            .append("</amount><taxrate>").append(resultSet.getTax_rate())
//		            .append("</taxrate><tax>").append(resultSet.getTax_val())
//		            .append("</tax><includetax>0</includetax><discountvalue></discountvalue><discounttax></discounttax></invrecorditem>")
//		            .append("</invrecord></issueinv></siiscmd>");
//
//			System.out.println(commandGen);
//			SaveDocument sd=new SaveDocument();
//			sd.setCommand(commandGen.toString());
//			response = stub.saveDocument(sd);
//			returnMsg="";
//			//S成功，F失败
//			if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//				returnMsg = response.get_return().getCmdMessage();	
//				str_code = getINVInformation(returnMsg,"<binvcode>","</binvcode>");
//				str_num = getINVInformation(returnMsg,"<binvnr>","</binvnr>");
//				
//				String pcommand=GeneratPrintCommand(str_code,str_num,2);
//				SaveDocument sd2=new SaveDocument();
//				sd2.setCommand(pcommand);
//				response = stub.saveDocument(sd2);
//				//打印发票成功
//				if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//					return 0;
//				}else{
//					return 1;
//				}
//			}else{
//				return 1;
//			}
//			
//	  }else{
//		 errorMsg.append("error ");
//		 return 1;
//		}	  
//			  
//}
//
////正常查询
//	 public static VATInvoiceSpecial queryVATInvoiceSpecial(String PRINT_POOL_H_ID) throws Exception{
//		 
//		    VATInvoiceSpecial result=null;
//			// 获取数据库连接Connection对象  
//	        Connection conn = getConnection();  
//	        // 插入用户注册信息的SQL语句  
//	        StringBuffer SpecialQuery= new StringBuffer();
//	        SpecialQuery.append(" SELECT H.INVOICE_TYPE      INVOICE_TYPE,")
//	  		  .append(" H.SOURCE_TYPE       SOURCE_TYPE,")
//			  .append(" TO_CHAR(H.OPEN_DATE,'YYYY-MM-DD HH:MM:SS') OPEN_DATE,")
//			  .append(" H.PRINT_POOL_H_ID   PRINT_POOL_H_ID, ")
//			  .append(" C.CLIENT_NAME       BUYER_NAME,")
//			  .append(" C.CLIENT_CODE       BUYER_CODE,")
//			  .append(" C.CLIENT_ENTITY_NUM BUYER_TAXPAYER_NO,")
//			  .append(" C.COMP_ADDR         BUYER_ADDRESS,")
//			  .append(" C.COMP_PHONE        BUYER_PHONE,")
//			  .append(" C.BANK              BUYER_BANK,")
//			  .append(" C.BANK_NUM          BUYER_BANK_ACCOUNT,")
//			  .append("(SELECT US.USER_NAME FROM XIP_PUB_USERS US  WHERE H.OPEN_PEOPLE = US.USER_ID) OPEN_PEOPLE,")
//			  .append("  E.ACCOUNT_BANK    SALE_BANK, ")
//			  .append("  E.ACCOUNT_NUMBER  SALE_BANK_ACCOUNT, ")
//			  .append("  E.REGISTE_ADDRESS SALE_ADDRESS, ")
//			  .append("  E.REGISTE_PHONE   SALE_PHONE, ")
//			  .append("  (SELECT I.ITEM_CODE FROM TMS_BA_TAX_ITEMS I WHERE I.ITEM_NAME=L.GOODS_NAME)  GOODS_CODE, ")    //商品编号
//			  .append("  L.GOODS_NAME GOODS_NAME, ")   //商品名称
//			  .append("  L.SPEC_MODELS SPEC_MODELS, ")
//			  .append("   L.UNIT_NAME UNIT_NAME, ")
//			  .append("   L.COUNT COUNT,  ")
//			  .append("   L.NET_VAL NET_VAL, ")   //单价
//			  .append("  L.NET_VAL NET_VAL,  ")   //金额
//			  .append("  L.TAX_RATE TAX_RATE,  ") 
//			  .append("  L.TAX_VAL  TAX_VAL,  ")  
//			  .append("  H.ATTRIBUTE3  INV_CONTENTS  ")  //备注
//			  .append("  FROM TMS_INV_OUTTAX_PRINT_POOL_H H  ") 
//			  .append("  JOIN TMS_INV_OUTTAX_PRINT_POOL_L L  ") 
//			  .append("  ON H.PRINT_POOL_H_ID = L.PRINT_POOL_H_ID  ") 
//			  .append("  LEFT JOIN TMS_BA_CLIENT C ") 
//			  .append("  ON H.BUYER_ID = C.CLIENT_ID  ") 
//			  .append("  LEFT JOIN TMS_BA_TAX_ENTITY E  ") 
//			  .append("  ON H.SALE_ID = E.ENTITY_ID   WHERE H.PRINT_POOL_H_ID = '") 
//			  .append(PRINT_POOL_H_ID).append("'");
//	        try { 
//	            // 获取PreparedStatement对象  
//	            PreparedStatement ps = conn.prepareStatement(SpecialQuery.toString());
//	            // 对SQL语句的占位符参数进行动态赋值  
////	            ps.setString(1, user.getUsername());  
////	            ps.setString(2, user.getPassword());  
//
//	            // 执行更新操作  
//	            ResultSet rs=ps.executeQuery();
//	            if(rs.next()){
//	            	result=new VATInvoiceSpecial();
//	            	result.setBus_code(rs.getString("PRINT_POOL_H_ID"));
//	            	result.setBuyer_name(rs.getString("BUYER_NAME"));
//	            	result.setBuyer_code(rs.getString("BUYER_CODE"));
//	            	result.setBuyer_taxpayer_no(rs.getString("BUYER_TAXPAYER_NO"));
//	 	            result.setBuyer_address(rs.getString("BUYER_ADDRESS"));
//	 	            result.setBuyer_phone(rs.getString("BUYER_PHONE"));
//	 	            result.setBuyer_bank(rs.getString("BUYER_BANK"));
//	 	            result.setBuyer_bank_account(rs.getString("BUYER_BANK_ACCOUNT"));
//	 	            
//	 	            result.setDocdate(rs.getString("OPEN_DATE"));
//	 	            result.setWrite_inv(rs.getString("OPEN_PEOPLE"));
//	 	            result.setCheck_inv("");
//	 	            result.setReciver_inv("");
//	 	            
//	 	           result.setSale_bank(rs.getString("SALE_BANK"));
//	 	           result.setSale_bank_account(rs.getString("SALE_BANK_ACCOUNT"));
//	 	           result.setSale_phone(rs.getString("SALE_PHONE"));
//	 	           result.setSale_phone_address(rs.getString("SALE_ADDRESS"));
//	 	           
//	 	           result.setProduct_code(rs.getString("GOODS_CODE"));
//	 	           result.setProduct_name(rs.getString("GOODS_NAME"));
//	 	           result.setProduct_style(rs.getString("SPEC_MODELS"));
//	 	           result.setProduct_unit(rs.getString("UNIT_NAME"));
//	 	           result.setProduct_num(1);
//	 	           BigDecimal Product_price=new BigDecimal(rs.getString("NET_VAL"));
//	 	           BigDecimal tax_val=new BigDecimal(rs.getString("TAX_VAL"));
//	 	           result.setProduct_price(Product_price);
//	 	           result.setProduct_amount(Product_price);
//	 	           
//	 	           result.setTax_rate(Integer.parseInt(rs.getString("TAX_RATE")));
//	 	           result.setTax_val(tax_val);
//	 	           result.setInv_contents(rs.getString("INV_CONTENTS"));
//	            }	
//	            // 释放此 PreparedStatement 对象的数据库和 JDBC 资源  
//	            ps.close();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//		//WebUtil.response(response, "{msg:'" + errorImport2.toString().replace("\n", "") + "',success:true,flag:0}");
//	        }finally{  
//	            // 关闭数据库连接  
//	            closeConnection(conn);  
//	        } 
//	        return result;
//	 } 
//  
//  
//	 //红冲查询
//	 public static VATInvoiceRed queryVATInvoiceRed(String PRINT_POOL_H_ID) throws Exception{
//		 
//		    VATInvoiceRed result=null;
//			// 获取数据库连接Connection对象  
//	        Connection conn = getConnection();  
//	        // 插入用户注册信息的SQL语句  
//	        StringBuffer RedQuery= new StringBuffer();
//	        RedQuery.append(" SELECT H.INVOICE_TYPE      INVOICE_TYPE,")
//	  		  .append(" H.SOURCE_TYPE       SOURCE_TYPE,")
//			  .append(" TO_CHAR(H.OPEN_DATE,'YYYY-MM-DD HH:MM:SS') OPEN_DATE,")
//			  .append(" H.PRINT_POOL_H_ID   PRINT_POOL_H_ID, ")
//			  .append(" C.CLIENT_NAME       BUYER_NAME,")
//			  .append(" C.CLIENT_CODE       BUYER_CODE,")
//			  .append(" C.CLIENT_ENTITY_NUM BUYER_TAXPAYER_NO,")
//			  .append(" C.COMP_ADDR         BUYER_ADDRESS,")
//			  .append(" C.COMP_PHONE        BUYER_PHONE,")
//			  .append(" C.BANK              BUYER_BANK,")
//			  .append(" C.BANK_NUM          BUYER_BANK_ACCOUNT,")
//			  .append(" R.RED_CODE RED_CODE,")
//			  .append("(SELECT US.USER_NAME FROM XIP_PUB_USERS US  WHERE H.OPEN_PEOPLE = US.USER_ID) OPEN_PEOPLE,")
//			  .append("  E.ACCOUNT_BANK    SALE_BANK, ")
//			  .append("  E.ACCOUNT_NUMBER  SALE_BANK_ACCOUNT, ")
//			  .append("  E.REGISTE_ADDRESS SALE_ADDRESS, ")
//			  .append("  E.REGISTE_PHONE   SALE_PHONE, ")
//			  .append("  (SELECT I.ITEM_CODE  FROM TMS_BA_TAX_ITEMS I, TMS_INV_OUTTAX_RED_L L WHERE I.ITEM_NAME = L.MT_NAME  AND L.RED_H_ID = R.RED_H_ID) MT_CODE,")    //商品编号
//			  .append(" (SELECT L.MT_NAME  FROM TMS_INV_OUTTAX_RED_L L  WHERE L.RED_H_ID = R.RED_H_ID) MT_NAME,")   //商品名称
//			  .append(" (SELECT SUM(MT_VAL)  FROM TMS_INV_OUTTAX_RED_L L  WHERE L.RED_H_ID = R.RED_H_ID) RED_VAL,")   //单价
//			  .append(" (SELECT SUM(MT_VAL)  FROM TMS_INV_OUTTAX_RED_L L WHERE L.RED_H_ID = R.RED_H_ID) RED_VAL, ")   //金额
//			  .append(" (SELECT DISTINCT MT_TAX  FROM TMS_INV_OUTTAX_RED_L L  WHERE L.RED_H_ID = R.RED_H_ID) RED_RATE,   ") 
//			  .append("  (SELECT SUM(MT_TAX_VAL) FROM TMS_INV_OUTTAX_RED_L L WHERE L.RED_H_ID = R.RED_H_ID) RED_TAX_VAL, ")  
//			  .append("  R.COMMENTS COMMENTS,  ")  //备注
//			  .append("  H.INVOICE_CODE  INVOICE_CODE,  ")  //代码
//			  .append("  H.INVOICE_NAME  INVOICE_NAME   ")  //发票号码
//			  .append("  FROM TMS_INV_OUTTAX_PRINT_POOL_H H  ") 
//			  .append("  LEFT JOIN TMS_INV_OUTTAX_RED_H R ") 
//			  .append("   ON H.SOURCE_H_ID = R.RED_H_ID ") 
//			  .append("  LEFT JOIN TMS_BA_CLIENT C ") 
//			  .append("  ON H.BUYER_ID = C.CLIENT_ID  ") 
//			  .append("  LEFT JOIN TMS_BA_TAX_ENTITY E  ") 
//			  .append("  ON H.SALE_ID = E.ENTITY_ID   WHERE H.PRINT_POOL_H_ID = '") 
//			  .append(PRINT_POOL_H_ID).append("'");
//	        try { 
//	            // 获取PreparedStatement对象  
//	            PreparedStatement ps = conn.prepareStatement(RedQuery.toString());
//	            // 对SQL语句的占位符参数进行动态赋值  
////	            ps.setString(1, user.getUsername());  
////	            ps.setString(2, user.getPassword());  
//
//	            // 执行更新操作  
//	            ResultSet rs=ps.executeQuery();
//	            if(rs.next()){
//	            	result=new VATInvoiceRed();
//	            	result.setBus_code(rs.getString("PRINT_POOL_H_ID"));
//	            	result.setBuyer_name(rs.getString("BUYER_NAME"));
//	            	result.setBuyer_code(rs.getString("BUYER_CODE"));
//	            	result.setBuyer_taxpayer_no(rs.getString("BUYER_TAXPAYER_NO"));
//	 	            result.setBuyer_address(rs.getString("BUYER_ADDRESS"));
//	 	            result.setBuyer_phone(rs.getString("BUYER_PHONE"));
//	 	            result.setBuyer_bank(rs.getString("BUYER_BANK"));
//	 	            result.setBuyer_bank_account(rs.getString("BUYER_BANK_ACCOUNT"));
//	 	            
//	 	            result.setWrite_inv(rs.getString("OPEN_PEOPLE"));
//	 	            result.setCheck_inv("");
//	 	            result.setReciver_inv("");
//	 	            
//	 	           result.setSale_bank(rs.getString("SALE_BANK"));
//	 	           result.setSale_bank_account(rs.getString("SALE_BANK_ACCOUNT"));
//	 	           result.setSale_phone(rs.getString("SALE_PHONE"));
//	 	           result.setSale_phone_address(rs.getString("SALE_ADDRESS"));
//	 	           
//	 	           if(StringUtils.isNotEmpty(rs.getString("RED_VAL")) && StringUtils.isNotEmpty(rs.getString("RED_TAX_VAL"))){
//		 	           BigDecimal Product_price=new BigDecimal(rs.getString("RED_VAL"));
//		 	           BigDecimal tax_val=new BigDecimal(rs.getString("RED_TAX_VAL"));
//		 	           result.setProduct_price(Product_price);
//		 	           result.setProduct_amount(Product_price);
//		 	           result.setTax_val(tax_val);
//	 	           }else{
//		 	           result.setProduct_price(new BigDecimal(0));
//		 	           result.setProduct_amount(new BigDecimal(0));
//		 	           result.setProduct_amount(new BigDecimal(0));
//	 	           }
//
//	 	           result.setProduct_code(rs.getString("MT_CODE"));
//	 	           result.setProduct_name(rs.getString("MT_NAME"));
//
//	 	           if(StringUtils.isNotEmpty(rs.getString("RED_RATE"))){
//	 	        	  result.setTax_rate(Integer.parseInt(rs.getString("RED_RATE")));
//	 	           }else{
//	 	        	  result.setTax_rate(0);
//	 	           }
//	 	           
//	 	          
//	 	           result.setInv_contents(rs.getString("COMMENTS"));
//	 	           result.setRednoticenr(rs.getString("RED_CODE"));
//	 	           result.setRefinvcode(rs.getString("INVOICE_CODE"));
//	 	           result.setRefInvnr(rs.getString("INVOICE_NAME"));
//	 	          result.setDocdate(rs.getString("OPEN_DATE"));
//	            }	
//	            // 释放此 PreparedStatement 对象的数据库和 JDBC 资源  
//	            ps.close();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//		//WebUtil.response(response, "{msg:'" + errorImport2.toString().replace("\n", "") + "',success:true,flag:0}");
//	        }finally{  
//	            // 关闭数据库连接  
//	            closeConnection(conn);  
//	        } 
//	        return result;
//	 } 
// 
//
//	 /** 
//  * 获取数据库连接 
//  * @return Connection对象 
//  */  
// public static Connection getConnection(){  
//     Connection conn = null;  
//     try {  
//     	//服务器发布
//  	   String URL = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
//  	   String USERNAME = "TMSPROD";
//  	   String PASSWORD = "TMSPROD";
//         // 加载驱动  
//         Class.forName("oracle.jdbc.driver.OracleDriver");  
//
//         // 获取数据库连接  
//         conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);  
//     } catch (Exception e) {  
//         e.printStackTrace();  
//     }  
//     return conn;  
// }  
// /** 
//  * 关闭数据库连接 
//  * @param conn Connection对象 
//  */  
// public static void closeConnection(Connection conn){  
//     // 判断conn是否为空  
//     if(conn != null){  
//         try {  
//             conn.close();   // 关闭数据库连接  
//         } catch (SQLException e) {  
//             e.printStackTrace();  
//         }  
//     }  
// }  
// 
// 
// 
// public static String getINVInformation(String returnMsg,String search1,String search2) {   
//
//		int start=StringUtils.indexOf(returnMsg, search1);
//		int end=StringUtils.indexOf(returnMsg, search2);
//		String[] inv_code=StringUtils.substring(returnMsg, start, end).split(">");
//		String code=inv_code[1];
// 
//		return code;   
//  }   
// 
// public static String GeneratPrintCommand(String code,String num,int inv_kind) {   
//	 StringBuffer PrintQuery= new StringBuffer();
//	 PrintQuery.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.cn\">")
//	 			.append("<printinv><key><invkind><value>").append(inv_kind)
//	 			.append("</value></invkind><invcode>").append(code)
//	 			.append("</invcode><invnr>").append(num)
//	 			.append("</invnr></key></printinv></siiscmd>"); 
//	 return PrintQuery.toString();   
// }
// 
// 
//}