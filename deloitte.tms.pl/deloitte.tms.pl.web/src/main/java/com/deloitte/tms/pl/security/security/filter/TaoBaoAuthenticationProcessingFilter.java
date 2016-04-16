package com.deloitte.tms.pl.security.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

public class TaoBaoAuthenticationProcessingFilter extends
		UsernamePasswordAuthenticationFilter {
	Oauth2Service oauth2Service;
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		DefaultUser user = new DefaultUser();
		Map<String, String> param = new HashMap<String, String>();
		param.put("grant_type", "authorization_code");
		if (request.getParameter("code") == null
				|| request.getParameter("code").length() <= 0) {
			throw new AuthenticationServiceException(
					"TopOauth2AuthenticationFilter 错误:");
		}
		param.put("code", request.getParameter("code"));		
		param.put("client_id", (String)PropertiesUtils.get("taobao.appkey"));
		param.put("client_secret", (String)PropertiesUtils.get("taobao.appSecret"));
		param.put("redirect_uri", (String)PropertiesUtils.get("taobao.redirect_uri"));
		param.put("scope", "item");

		String responseJson = "";
		try {
			responseJson = WebUtils.doPost((String)PropertiesUtils.get("taobao.token_url"),
					param, 3000, 3000);
			JSONObject  dataJson=JSONObject.fromObject(responseJson);
			
			String appkey=dataJson.getString("taobao_user_id");
			if(!AssertHelper.notEmpty(appkey))
			{
				throw new AuthenticationServiceException("淘宝认证失败");
			}
			QQOauth2User qQOauth2User=oauth2Service.getQQOauth2User(appkey);
			String gender=dataJson.getString("taobao_user_id");
			String nickname=dataJson.getString("taobao_user_nick");
			String accessToken=dataJson.getString("access_token");
			if(qQOauth2User==null)
			{
				user=new DefaultUser();
				user.setUsername(appkey);
				user.setAdministrator(false);
				user.setCname(nickname);
				String salt = String.valueOf(RandomUtils.nextInt(100));
				String password = passwordEncoder.encodePassword(appkey, salt);
				user.setPassword(password);
				user.setSalt(salt);
				user.setEnabled(true);
				user.setMale(null);
				user.setCompanyId(ContextUtils.getFixedCompanyId());
				oauth2Service.saveNewuser(user, accessToken, appkey);
			}else{
				user=oauth2Service.getUserByUsername(qQOauth2User.getUserid());
				if(user==null)
				{
					throw new AuthenticationServiceException(
							"保存的登录信息不存在： " + qQOauth2User.getUserid());
				}
				user.setMale(null);
				user.setCname(nickname);
				oauth2Service.update(user);
			}
			
		} catch (IOException e) {
			throw new AuthenticationServiceException(
					"TopOauth2AuthenticationFilter get toke 错误:");
		} catch (JSONException e) {
			throw new AuthenticationServiceException(
					"淘宝登陆信息解析失败"+e.toString());
		}
		// 自己处理登录流程
		Oauth2Authentication authRequest = new Oauth2Authentication(
				user.getUsername());
		// Allow subclasses to set the "details" property
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
