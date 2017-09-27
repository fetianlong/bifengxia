package com.chatm.search.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.chatm.search.model.RegistUser;
import com.chatm.search.service.RegistUserService;
import com.chatm.search.util.Constants;
import com.chatm.search.weixinpay.PayConfigUtil;
import com.chatm.search.weixinpay.WXHttpUtil;

@Controller
@RequestMapping("/weixin")
public class WeixinLoginController {

	protected static final Log logger = LogFactory.getLog(WeixinLoginController.class);
	
	@Autowired
	RegistUserService registUserService;
	
//	private static String APPID = "wx98dfa4f4df3122e7";
//	private static String SECURITY = "94762a117cc80037";
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) throws IOException {
		try {
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			String tokenId = (String) session.getAttribute("tokenId");
			String loginOldURL = (String) session.getAttribute("loginOldURL");	//登录前的页面
			System.out.println("session中的loginOldURL：" + loginOldURL);
//			String gw = "http://www.bifengxia.com";
			String returnUrl = loginOldURL.replace(Constants.gwadd, "");	//跳转的页面
			//用户授权登录
			if(!StringUtils.isEmpty(code) && !StringUtils.isBlank(state) && state.equals(tokenId)){
				String accessToken = null;
				String openId = null;
				String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + PayConfigUtil.APPID + "&secret="+PayConfigUtil.SECRET+"&code="+code+"&grant_type=authorization_code";
	//					"https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + PayConfigUtil.APP_ID + "&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
				// 获取网页授权凭证,通过code获取access_token
				JSONObject jsonObject = WXHttpUtil.httpsRequestJson(url, "GET", null);
				if (null != jsonObject) {
	//				System.out.println("用户授权成功，获取参数。。。。");
					//主要的2个参数
					accessToken = jsonObject.getString("access_token");
					openId = jsonObject.getString("openid");
					if(!StringUtils.isBlank(accessToken) && !StringUtils.isBlank(openId)){
						String unionid = jsonObject.getString("unionid");	//当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
						Integer expiresIn = jsonObject.getInteger("expires_in");
						String refreshToken = jsonObject.getString("refresh_token");
						String scope = jsonObject.getString("scope");
	//					int errorCode = jsonObject.getInteger("errcode");
						String errorMsg = jsonObject.getString("errmsg");
						
						//获取用户信息   1. access_token有效且未超时    2. 微信用户已授权给第三方应用帐号相应接口作用域（scope）。
						String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openId;
						// 通过网页授权获取用户信息
						JSONObject jsonObject1 = WXHttpUtil.httpsRequestJson(requestUrl, "GET", null);
						if (null != jsonObject1) {
	//						System.out.println("获取用户信息成功，处理中。。。");
							String useropenId = jsonObject1.getString("openid");	//用户标识
							String nickname = jsonObject1.getString("nickname");	//用户昵称
	//						Integer sex = jsonObject1.getInteger("sex");
							String country = jsonObject1.getString("country");	//所在国家
							String province = jsonObject1.getString("province");	//所在省
							String city = jsonObject1.getString("city");	//所在省
							String headimgurl = jsonObject1.getString("headimgurl");	//头像
							
	//						System.out.println("用户唯一标识" + useropenId);
	//						System.out.println("用户昵称" + nickname);
							
							RegistUser registUser = registUserService.selectByUniqueKey(useropenId);
							if(null == registUser || registUser.getId()<0){
								RegistUser user = new RegistUser();
								user.setUniqueKey(useropenId);
								user.setPassword(useropenId);
								user.setUserName(useropenId);
								user.setPlainPassword(useropenId);
								user.setRealName(URLEncoder.encode(nickname, "utf-8"));
								user.setRegistType(2);
								user.setRegistTime(new Date());
								user.setAddress(country+province+city);
								user.setHeadpicture(headimgurl);
								
								logger.debug("当前用户 " + nickname + " 不存在，新增用户后登录");
								registUserService.insertSelective(user);
								
								UsernamePasswordToken token = new UsernamePasswordToken(useropenId, useropenId);
								subject.login(token);
		//						UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
							}else{
								logger.debug("当前用户 " + registUser.getRealName() + " 已存在，直接登录");
								
								UsernamePasswordToken token = new UsernamePasswordToken(useropenId, useropenId);
								subject.login(token);
							}
							return "redirect:" + returnUrl;
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "index";
	}

}
