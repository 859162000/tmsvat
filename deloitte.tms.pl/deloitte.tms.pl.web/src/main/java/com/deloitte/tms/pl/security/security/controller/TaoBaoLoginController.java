package com.deloitte.tms.pl.security.security.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.controller.IController;

@Component
public class TaoBaoLoginController implements IController {
	@Value("${taobao.appkey}")
	String appkey;
	@Value("${taobao.appSecret}")
	String appSecret;
	@Value("${taobao.response_type}")
	String response_type;
	@Value("${taobao.redirect_uri}")
	String redirect_uri;
	@Value("${taobao.authorize_url}")
	String authorize_url;
	@Value("${taobao.scope}")
	String scope;
	@Override
	public String getUrl() {
		return "/taobaologin";
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		String client_id = appkey;
		String view = "web";
		String url = authorize_url+"?";// code获取地址
		url += "response_type=" + response_type + "&client_id=" + client_id
				+ "&redirect_uri=" + redirect_uri + "&scope=" + scope
				+ "&view=" + view;
		response.sendRedirect(url);
	}

	@Override
	public boolean anonymousAccess() {
		return true;
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

}
