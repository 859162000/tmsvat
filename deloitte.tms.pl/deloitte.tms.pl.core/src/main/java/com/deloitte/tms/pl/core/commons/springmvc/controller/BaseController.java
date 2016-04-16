package com.deloitte.tms.pl.core.commons.springmvc.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DateEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DoubleEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.FloatEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.IntegerEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.LongEditor;


/**
 * 所有Controller继承BaseController
* 异常控制，这便是异常细节可控，将来可用于支持国际化（异常信息国际化）
* */
public class BaseController {
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
//		return new ModelAndView().addObject("error", "错误信息");
//	}
	
	@InitBinder   
	protected void initBinder(WebDataBinder binder) {    
		binder.registerCustomEditor(Date.class, new DateEditor());   
	    binder.registerCustomEditor(int.class, new IntegerEditor());     
	    binder.registerCustomEditor(long.class, new LongEditor());    
	    binder.registerCustomEditor(double.class, new DoubleEditor());    
	    binder.registerCustomEditor(float.class, new FloatEditor());
	} 
}
