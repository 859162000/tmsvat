package com.deloitte.tms.pl.core.commons.springmvc;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DateEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.DoubleEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.FloatEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.IntegerEditor;
import com.deloitte.tms.pl.core.commons.springmvc.propertyeditor.LongEditor;

public class LingWebBindingInitializer implements WebBindingInitializer {
	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(float.class, new FloatEditor());
	}
}
