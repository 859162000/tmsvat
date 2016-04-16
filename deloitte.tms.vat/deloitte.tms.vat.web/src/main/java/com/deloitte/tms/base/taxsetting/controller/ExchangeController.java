package com.deloitte.tms.base.taxsetting.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deloitte.tms.vat.controller.BaseController;


@Controller
@RequestMapping("exchangePage")
public class ExchangeController extends BaseController{
	
	
	@RequestMapping(value = "/invoiceSendHInit", method = RequestMethod.GET)
	public String invoiceSendHInit() throws Exception {
		return 	 "vat/exchangeRate/exchangePage";
	}
	
}
