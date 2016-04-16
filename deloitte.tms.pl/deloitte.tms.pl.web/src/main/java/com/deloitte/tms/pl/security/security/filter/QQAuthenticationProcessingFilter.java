package com.deloitte.tms.pl.security.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.commons.utils.WebUtils;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.model.impl.QQOauth2User;
import com.deloitte.tms.pl.security.security.authentication.Oauth2Authentication;
import com.deloitte.tms.pl.security.service.Oauth2Service;

public class QQAuthenticationProcessingFilter extends
		UsernamePasswordAuthenticationFilter {
	Oauth2Service oauth2Service;
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (request.getParameter("code") == null
				|| request.getParameter("code").length() <= 0) {
			throw new AuthenticationServiceException(
					"TopOauth2AuthenticationFilter 错误:");
		}
		String queryString = ((HttpServletRequest) request).getQueryString();
		Matcher m = Pattern.compile("code=(\\w+)&state=(\\w+)&?").matcher(
				queryString);
		String authCode = "";
		String state = "";
		if (m.find()) {
			authCode = m.group(1);
			state = m.group(2);
		}
		DefaultUser user = new DefaultUser();

		String responseJson = "";
		try {
			// 获取令牌
			Map<String, String> param = new HashMap<String, String>();
			param.put("grant_type", "authorization_code");
			param.put("code", authCode);
			param.put("client_id", (String) PropertiesUtils.get("qq.appkey"));
			param.put("client_secret",
					(String) PropertiesUtils.get("qq.appSecret"));
			param.put("redirect_uri",
					(String) PropertiesUtils.get("qq.redirect_uri"));
			param.put("format", "josn");
			String responsetoken = WebUtils.doPost(
					(String) PropertiesUtils.get("qq.token_url"), param, 3000,
					3000);
			System.out.println(responsetoken);
			String access_token = "";
			Matcher m1 = Pattern
					.compile(
							"^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$")
					.matcher(responsetoken);
			if (m1.find()) {
				access_token = m1.group(1);
			} else {
				Matcher m2 = Pattern.compile(
						"^access_token=(\\w+)&expires_in=(\\w+)$").matcher(
						responsetoken);
				if (m2.find()) {
					access_token = m2.group(1);
				}
			}
			if(!AssertHelper.notEmpty(access_token))
			{
				throw new AuthenticationServiceException("qq认证失败");
			}
			// 获取openid
			Map<String, String> meparam = new HashMap<String, String>();
			meparam.put("access_token", access_token);
			meparam.put("format", "josn");
			String responseme = WebUtils.doPost(
					"https://graph.qq.com/oauth2.0/me", meparam, 3000, 3000);
			String openid="";
			String erro="";
			Matcher mme = Pattern.compile("^callback\\( \\{\"client_id\":\"(\\w+)\",\"openid\":\"(\\w+)\"\\} \\);$").matcher(responseme);
			if (mme.find()) {
				openid = mme.group(2);
			} else {
				mme = Pattern.compile("^callback\\( \\{\"error\":(\\w+),\"error_description\":\"(\\w+)\"\\} \\);$").matcher(responseme);
				if (mme.find()) {
					erro = mme.group(1);
				}
			}
			if(!AssertHelper.notEmpty(openid))
			{
				throw new AuthenticationServiceException("qq认证失败");
			}
			// 获取用户信息
			Map<String, String> tokenparam = new HashMap<String, String>();
			tokenparam.put("access_token", access_token);
			tokenparam.put("oauth_consumer_key",
					(String) PropertiesUtils.get("qq.appkey"));
			tokenparam.put("openid", openid);
			tokenparam.put("format", "josn");
			responseJson = WebUtils.doPost(
					"https://graph.qq.com/user/get_user_info", tokenparam, 3000,
					3000);
			JSONObject dataJson = JSONObject.fromObject(responseJson);
			String appkey = openid;
			if (!AssertHelper.notEmpty(appkey)) {
				throw new AuthenticationServiceException("qq认证失败");
			}
			QQOauth2User qQOauth2User = oauth2Service.getQQOauth2User(appkey);
			if (qQOauth2User == null) {
				user = new DefaultUser();
				user.setUsername(appkey);
				user.setAdministrator(false);
				user.setCname(dataJson.getString("nickname"));
				String salt = String.valueOf(RandomUtils.nextInt(100));
				String password = passwordEncoder.encodePassword(appkey, salt);
				user.setPassword(password);
				user.setSalt(salt);
				user.setEnabled(true);
				user.setMale("男".equals(dataJson.getString("gender"))?true:false);
				user.setCompanyId(ContextUtils.getFixedCompanyId());
				oauth2Service.saveNewuser(user, access_token, appkey);
			} else {
				user = oauth2Service
						.getUserByUsername(qQOauth2User.getUserid());
				if (user == null) {
					throw new AuthenticationServiceException("保存的登录信息不存在： "
							+ qQOauth2User.getUserid());
				}
				user.setMale("男".equals(dataJson.getString("gender"))?true:false);
				user.setCname(dataJson.getString("nickname"));
				oauth2Service.update(user);
			}
		} catch (IOException e) {
			throw new AuthenticationServiceException(
					"TopOauth2AuthenticationFilter get toke 错误:");
		} catch (JSONException e) {
			throw new AuthenticationServiceException("qq登陆信息解析失败"
					+ e.toString());
		}
		// 自己处理登录流程
		Oauth2Authentication authRequest = new Oauth2Authentication(
				user.getUsername());
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public Oauth2Service getOauth2Service() {
		return oauth2Service;
	}

	public void setOauth2Service(Oauth2Service oauth2Service) {
		this.oauth2Service = oauth2Service;
	}

	// out.println(userInfoBean.getNickname() + "<br/>");
	// out.println(userInfoBean.getGender() + "<br/>");
	// out.println("黄钻等级： " + userInfoBean.getLevel() +
	// "<br/>");
	// out.println("会员 : " + userInfoBean.isVip() + "<br/>");
	// out.println("黄钻会员： " + userInfoBean.isYellowYearVip() +
	// "<br/>");
	// out.println("<image src=" +
	// userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
	// out.println("<image src=" +
	// userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
	// out.println("<image src=" +
	// userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");
	// out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在微博的昵称等信息 ---------------------------- start </p>");
	// com.qq.connect.api.weibo.UserInfo weiboUserInfo = new
	// com.qq.connect.api.weibo.UserInfo(accessToken, openID);
	// com.qq.connect.javabeans.weibo.UserInfoBean weiboUserInfoBean
	// = weiboUserInfo.getUserInfo();
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
