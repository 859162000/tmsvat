package com.deloitte.tms.base.masterdata.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import secapipackage.SecApi;

import com.deloitte.tms.pl.core.commons.springmvc.controller.BaseController;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.security.ISecurityInterceptor;
import com.deloitte.tms.pl.security.service.impl.DefaultUserService;


@Controller
public class SingleLoginController extends BaseController{
	
	//public  String errorPage="/vat/remoteErrorPage.html";
	public  String errorPage="/vat/index.html";
	public  String firstPgae="/vat/index.html";
	
	public  byte[] NodeId = "0101".getBytes();
	
	
	public  byte EncOpt='0'; //0解密 1加密
	
	
	public  byte[] APPId = "1189".getBytes();
	
	public String defaultUser="admin";
	public String useDefaultUser="N";
	
	public String csmpapiconfpath="";

	@Resource
	DefaultUserService defaultUserService;
	
	@Resource
	ISecurityInterceptor securityInterceptor;

	
	public boolean getSelfConfig(){
		
		InputStream inputStream =null;
		FileInputStream fileIn = null;
		
		try {
			
			/*
			ClassLoader cl = this.getClass().getClassLoader();
			if( cl != null){
				
				 cl.getResourceAsStream("config.properties");
			}else{
				
				ClassLoader.getSystemResourceAsStream("config.properties");
			}
		
*/			
/*			try{
				fileIn = new FileInputStream("/vat/user/test/singleLoginConfig.properties");
			
				if(fileIn!=null){
					inputStream=fileIn;
				}
			}catch(Exception ex){
				info("/vat/user/test/singleLoginConfig.properties no exist, will try to use /config/singleLoginConfig.properties ");
			}*/
			
			if(inputStream==null){
				inputStream = this.getClass().getResourceAsStream("/config/singleLoginConfig.properties");
				if(inputStream==null){
					info("/config/singleLoginConfig.properties can't find !!");
					return false;
				}else{
					info("/config/singleLoginConfig.properties ok, will try to get properties to see.");
				}
			}
			
			
			
			Properties pro =  new  Properties();        
			pro.load(inputStream);
			
			String useMe=(String) pro.get("useMe");
			
			if(useMe != null && "Y".equalsIgnoreCase(useMe)){
				
				errorPage=(String) pro.get("errorPage");
				firstPgae=(String) pro.get("firstPgae");
				NodeId= ((String) pro.get("NodeId")).getBytes();
				
				APPId=((String) pro.get("APPId")).getBytes();
				defaultUser=(String) pro.get("defaultUser");
				useDefaultUser=(String) pro.get("useDefaultUser");
				
				
				
				csmpapiconfpath=(String) pro.get("csmpapiconfpath");
				if(AssertHelper.empty(csmpapiconfpath)){
					
					csmpapiconfpath="";
				}
				
				inputStream.close();
				
				
				return true;
			}else{
				
				inputStream.close();
				return false;
			}
			

		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			if(inputStream !=null){
				
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(fileIn!=null){
				try {
					fileIn.close();
				} catch (IOException exx) {
					// TODO Auto-generated catch block
					exx.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
	/**
	 * 
	 */
	@RequestMapping("/handleSingle/remoteLogin.do")
	public void fromRemoteLogin(HttpServletResponse response, HttpServletRequest request){
		
		
		try{
			String userName=null;
			
			if(getSelfConfig()){
				
				info("getSelfConfig() ok useDefaultUser:"+useDefaultUser);
			
				if("Y".equalsIgnoreCase(this.useDefaultUser)){
					
					userName=this.defaultUser;
				}else{
					
					userName=this.extract(request);
				}
				
				
			}else{
				
				info("getSelfConfig() no ok!!!!!!!!! so will use program default value to extract ");
				
				userName=this.extract(request);
			}
			
			
			
			/////////////////////////////////////////////////////using good code as above
			/*to-do select * from base_user where username=userName
			*
			*/
			
			this.info("from remote after extract get > userName ----------:"+userName);
			
			if(AssertHelper.empty(userName)){
				
				
				response.sendRedirect(errorPage);
				return;
				//return errorPage;
			}
			
			SecurityUser securityUser =  this.defaultUserService.getByUserName(userName);
			
			
			info("from our DB get > userName:"+securityUser.getUsername()+";password:"+securityUser.getPassword()+";authorities:"+securityUser.getAuthorities());
			
			if(AssertHelper.empty(securityUser)){
				
				
				response.sendRedirect(errorPage);
				return;
				
			}else{
				
				HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request, response);
				
							
				securityInterceptor.registerLoginInfo(securityUser, holder);
				
				holder.getResponse().sendRedirect(firstPgae); 

				
				
				//return firstPgae;
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				response.sendRedirect(errorPage);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return;
		}
		
	}
	
	
	/**
	 *@author tigchen 
	 */
	
	public String extract(HttpServletRequest httpServletRequest){
		
		String stUserId = null;
		try{
		
		String ST=httpServletRequest.getParameter("ST");
		info("request get parameter ST:"+ST);
		
		ST = iso2gbk(httpServletRequest.getParameter("ST")).toString();
		info("iso2gbk ST:"+ST);
		
		String SAR = iso2gbk(httpServletRequest.getParameter("SAR")).toString();
		info("request get parameter SAR:"+SAR);
		
		SAR = iso2gbk(httpServletRequest.getParameter("SAR")).toString();
		info("iso2gbk SAR:"+SAR);
		
		
		if(AssertHelper.empty(ST) || AssertHelper.empty(SAR) ){
			
			info("after iso2gbk have null ST or SAR detail as above, will run below way to try get ST SAR");
			
			StringBuffer remoteURLStr =  httpServletRequest.getRequestURL();
			String remoteURIStr =  httpServletRequest.getRequestURI();
			
			if(remoteURLStr ==null || remoteURIStr==null){
				
				System.out.println("client request with null (request.getRequestURL()) remoteStr: "+remoteURLStr);
				System.out.println("client request with null (request.getRequestURI()) remoteURIStr: "+remoteURIStr);
				return "";
			}
			
			
			
			System.out.println("client请求url: "+ remoteURLStr.toString());
			System.out.println("client请求urI: "+ remoteURIStr);
			
			HashMap<String, String> map = this.getDetail(remoteURIStr);
			ST = map.get("ST");
			SAR = map.get("SAR");
			
			if( AssertHelper.empty(ST) || AssertHelper.empty(SAR) ){
				
				System.out.println("URI can't get info, now use URL to get");
				map = this.getDetail(remoteURLStr.toString());
				
				ST = map.get("ST");
				SAR = map.get("SAR");
				
				if(AssertHelper.empty(ST) || AssertHelper.empty(SAR)){
					
					System.out.println("ST or SAR 获取失败: ST:"+ST);
					System.out.println("ST or SAR 获取失败: SAR:"+SAR);
					
					return "";
				}
			}
			
		}
		
		
		
		
		//String userId = ssr.UserVerify1(ST, SAR);
		
		stUserId = this.UserVerify1(ST, SAR);
		info("extract: stUserId:"+stUserId);
		
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return stUserId;
	}
	
	
	public String fillZero(int length, int num) {
		String len = String.valueOf(length);
		while (len.length() < num) {
		len = "0" + len;
		}
		return len;
		}

	
	public static byte[] convertToHalf(byte[] data) throws IOException {
		if (data.length % 2 != 0) {
		throw new RuntimeException("字符转化时字符长度不是2的倍数！");
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (int i = 0; i < data.length; i = i + 2) {
		char first = (char) data[i];
		char last = (char) data[i + 1];
		if (first == 0x00 && last == 0x00) {
		continue;
		}
		String content = new String(new char[] { first, last });
		int con = Integer.parseInt(content, 16);
		baos.write(con);
		}
		baos.flush();
		return baos.toByteArray();
		}

	
	public String UserVerify1(String strDataST, String strDataSAR) {
		info("ST ="+strDataST);
		info("SAR ="+strDataSAR);
		info("进入判断用户是否合法用户.info");
		String stUserId = ""; // 解密ST密文所得到的用户ID
		String stSessionKey = ""; // 解密ST密文所得到的密钥
		String sarUserId = ""; // 解密SAR密文所得到的用户ID
		String tmpDataST = cryptDataST1(strDataST);
		info("tmpDataST ="+tmpDataST);
		if ( "".equals(tmpDataST)   ) {
			return null;
		}
		stUserId = tmpDataST.substring(0, 32).trim();
		info("stUserId ="+stUserId);
	    String stAppid = tmpDataST.substring(32,42).trim();
	    info("stAppid ="+stAppid);
		String stUserNo = tmpDataST.substring(42,66).trim();
		info("stUserNo ="+stUserNo);
		stSessionKey = tmpDataST.substring(66).trim();
		info("stSessionKey ="+stSessionKey);
		sarUserId = cryptDataSAR(strDataSAR, stSessionKey).substring(0,32).trim();
		info("sarUserId ="+sarUserId);
		// 判断ST解析出来的用户名跟SAR解析出来的用户ID是否相同
		if (!stUserId.equals(sarUserId)) {
			return null;
		}else{
			info("sarUserId ="+sarUserId);
		}
		return stUserId;
	}
	

	
	/*
	 * SecAapi0310.jar
	 */
	public String cryptDataSAR(String InDataSAR, String SessionKey) {
		
		 //0 解密， 1加密
		
		info("调用SecApi中的方法对SAR密文进行解密");
		//SecApi secApi = new SecApi("/home/vatusr/test/hsmapi.conf"); // 创建解密类对象
		
		SecApi secApi = new SecApi(csmpapiconfpath); // 创建解密类对象 before is csmpapiconfpath+"csmpapi.conf", now include file into path
		int tmpKeyLen = SessionKey.length()/2;
		byte[] bKeyLen = fillZero(tmpKeyLen, 4).getBytes(); // 密钥长度
		int tmpSARLen = InDataSAR.length() / 2;
		byte[] DataLen = fillZero(tmpSARLen, 4).getBytes(); // SAR密文长度
		int length = InDataSAR.length() + 16 - InDataSAR.length() % 16; // 返回的报文长度
		byte[] OutData = new byte[length]; // 返回的报文
		int nRet = secApi.cryptDataPlainKey(bKeyLen, SessionKey.getBytes(),
				EncOpt, DataLen, InDataSAR.getBytes(), OutData); // 对SAR密文进行解密
		info("nRet == " + String.valueOf(nRet));  //-1
		if (nRet != 0) {
			return null;
		}
		String strData = "";
		try {
			byte[] b = convertToHalf(OutData);
		
		    strData = new String(b);
			strData = strData.trim();
			info("解密后的串: "+strData);
	         } catch (IOException e) {
			error("16进制转ascII码出错:"+e.getMessage());
		}
		return strData;
	}
	
	
	public String cryptDataST1(String InDataST) {
		
///////////////////////////////imortant .................

		// 应用编号：1189 ， 节点编号：0101 ， 开发环境地址及端口号：22.5.230.14：40005 ， 测试环境地址及端口号：22.5.238.18：40005
		
		///////////////////////////////imortant .................
		
		info("调用SecApi类中的解密方法对ST密文进行解密");
		String strData = "";
		//SecApi secApi = new SecApi("D:\\hsmapi.conf");
		
		SecApi secApi = new SecApi(csmpapiconfpath);
		int length = InDataST.length() + 16 - InDataST.length() % 16; // 返回的报文长度
		info("返回的报文长度="+length);
		byte[] OutData = new byte[length]; // 返回st的报文
		int InDataLen = InDataST.length() / 2;
		info("InDataST="+InDataST);
		info("InDataLen="+InDataLen);
		info("AppId="+"88" + "--------->我们的应用编号是 1189: byte[]="+APPId);        // 应用编号：1189 ， 节点编号：0101 ， 开发环境地址及端口号：22.5.230.14：40005 ， 测试环境地址及端口号：22.5.238.18：40005 
		
		//info("NodeId="+nodeId);
		info("NodeId=0101, byte[]="+NodeId);
		
		info("EncOpt="+0);

		//int nRet = secApi.cryptDataCipherKey("88".getBytes(), NodeId, EncOpt, fillZero(InDataLen, 4).getBytes(), InDataST.getBytes(), OutData); // 对ST密文进行解密
		
		int nRet = secApi.cryptDataCipherKey(APPId, NodeId, EncOpt, fillZero(InDataLen, 4).getBytes(), InDataST.getBytes(), OutData); // 对ST密文进行解密
		
		info("nret ==" + nRet);
		try {
			byte[] bSTData  = convertToHalf(OutData);// 16进制转ascII出错!
			 strData = new String(bSTData);
				strData = strData.trim();
		} catch (IOException e) {
	
		error("16进制转ascII出错!"+e.getMessage());
		
		}
	//	strData = Bytes2HexString(bSTData);// byte数组转换成16进制字符串
	//	strData = toStringHex(strData).trim();// 16进制字符串转换为字符串
		info("strData 47==" + strData);
		return strData;
	}
	
	
	
    public  String iso2gbk (String strIso) throws IOException {
        if(strIso != null && !strIso.trim().equals("")) {
            return new String(strIso.getBytes("iso-8859-1"),"gbk");
        }  else {
            return "";
        }
    }
	
	
	/**
	 * @author tigchen
	 * @param remoteStr
	 * @return
	 */
	public HashMap<String, String> getDetail(String remoteStr){
		
		HashMap<String, String> map = new HashMap<String, String>();
		try{
		
			if(remoteStr==null){
				return map;
			}
		
			/*
			 * 
			 * http://22.5.231.111:9080/ed/loginDAO.do?
ST=C99823D3F76E7E0DB41032441A9AB956CF95BE9C3789B5E3CF95BE9C3789B5E3A00076A785336E8447D95077AD94B1B8709C74DED51A9EC6CF95BE9C3789B5E3CF95BE9C3789B5E30518FBFA16B55E9C6A0D479C22E01CDC03BB5362A0CC1F6D
&&SAR=A5DB67AB9E256097925F1EA4C734F06100BFF1ECBD6C49C100BFF1ECBD6C49C1D309070EF9FDB705AF26DA78F128124CA96AAD67C540A311F799DD8B0F3652E6
&&RONM=4
&&POLE=KJ01000024KJ01000025KJ01000026KJ01000031
			 */
			String orString = remoteStr;
			
			String[] all = orString.split("&&");
			
			System.out.println("length is: "+all.length);
			
			for(String each : all){
				
				if(each.indexOf("ST=")>-1){
					
					map.put("ST", each.substring(each.indexOf("ST=")+3));
				}else if(each.indexOf("SAR=") > -1){
					
					map.put("SAR", each.substring(each.indexOf("SAR=")+3));
				}
			}
			
			return map;
		
		
		}catch(Exception e){
			
		}
		
		return map;
		
	}
	
	
	public static void info(Object o){
		
		System.out.println(o);
	}
	
	public static void error(Object o){
		System.out.println("!!error: "+o);
	}
	
	


}





