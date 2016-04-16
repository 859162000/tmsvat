package com.deloitte.tms.pl.core.commons.springmvc;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.deloitte.tms.pl.core.commons.springmvc.annotation.LoginAnnotation;
import com.deloitte.tms.pl.core.commons.springmvc.constant.FailStatus;
import com.deloitte.tms.pl.core.commons.springmvc.constant.ResultField;
import com.deloitte.tms.pl.core.commons.springmvc.constant.ResultStatus;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
/**
 * mvc方法权限控制
 * 方法默认都是需要登录才能访问的
 * 如果添加AnonymousAnnotation则可以匿名访问
 * RoleAnnotation 表示只有登陆并且是roles中的角色才能登陆,多个角色用","分开
 * @author bo.wang
 *
 */
public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {
 
    final Logger logger = LoggerFactory.getLogger(getClass());
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	if(handler instanceof HandlerMethod){
	    	boolean pass=true;
	    	int failStatus=0;
	        HandlerMethod handlerMethod=(HandlerMethod) handler;
	//        AnonymousAnnotation anonymousAnnotation = handlerMethod.getMethodAnnotation(AnonymousAnnotation.class);
	//        if(anonymousAnnotation!=null){
	//            return true;
	//        }
//	        for(MethodParameter parameter:handlerMethod.getMethodParameters()){
//	        	System.out.println(parameter.getParameterName());
//	        	System.out.println(parameter.getParameterType());
//	        	if(parameter.getParameterType()==Map.class){
//	        	}
//	        }
	        LoginAnnotation loginAnnotation = handlerMethod.getMethodAnnotation(LoginAnnotation.class);
	        if(loginAnnotation!=null){
	            if(ContextUtils.getCurrentUser()!=null){
	            	pass=true;
	            }else {
	            	failStatus=FailStatus.NOTLOGIN;
	            	pass=false;
				}
	        }
	        //没有访问权限 异常处理
	        ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
	        
	        if(!pass){
		        if (responseBody==null) {
		            //传统的登录页面              
		            StringBuilder sb = new StringBuilder();
		            sb.append(request.getContextPath());
		            sb.append("/oprst.jsp?oprst=false&opmsg=").append(URLEncoder.encode("没有权限访问","utf-8"));
		            response.sendRedirect(sb.toString());
		        } else{
		            //ajax类型的登录提示
		            response.setCharacterEncoding("utf-8");
		            response.setContentType("text/html;charset=UTF-8");
		            OutputStream out = response.getOutputStream();
		            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
		            pw.println("{\""+ResultField.STATUS+"\":\""+ResultStatus.FAIL+"\",\""+ResultField.ERROCODE+"\":"+failStatus+",\""+ResultField.ERROMESSAGE+"\":\""+"没有权限访问"+"\"}");
		            pw.flush();
		            pw.close();
		        }
		        return false;
	        }
	        return true;
    	}else{
    		return true;
    	}
    }
}