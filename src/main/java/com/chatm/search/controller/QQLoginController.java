package com.chatm.search.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

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
import com.chatm.search.util.HttpClientTemplate;
import com.chatm.search.weixinpay.HttpUtil;
import com.chatm.search.weixinpay.PayConfigUtil;

@Controller
@RequestMapping("/qq")
public class QQLoginController {
	
	protected static final Log logger = LogFactory.getLog(QQLoginController.class);
	
	@Autowired
	RegistUserService registUserService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) throws IOException {
		try {
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			String tokenId = (String) session.getAttribute("tokenId");
			
			String loginOldURL = (String) session.getAttribute("loginOldURL");	//登录前的页面
//			String gw = "http://www.bifengxia.com";
			String returnUrl = loginOldURL.replace(Constants.gwadd, "");	//跳转的页面
			//用户授权登录
			if(!StringUtils.isEmpty(code) && !StringUtils.isBlank(state) && state.equals(tokenId)){
				String accessToken = null;
				String openId = null;
				String redirectUrl = "redirect_uri";
				String url = "https://graph.qq.com/oauth2.0/token?client_id="+PayConfigUtil.QQ_APPID+"&client_secret="+PayConfigUtil.QQ_APPKEY+"&code="+code+"&grant_type=authorization_code&redirect_uri="+PayConfigUtil.QQ_REDIRECTURI;
//					"https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
				// 获取网页授权凭证,通过code获取access_token
//				HttpClientTemplate htttpClient = new HttpClientTemplate(url);
				
				Map jsonObject = HttpUtil.httpsRequestMap(url, "GET", null);
				if(!jsonObject.isEmpty()) {
					//主要的2个参数
					accessToken = (String) jsonObject.get("access_token");
					openId = (String) jsonObject.get("openid");
					
//				String unionid = (String) jsonObject.get("unionid");	//当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段。
//				String expiresIn = (String) jsonObject.get("expires_in");
//				String refreshToken = (String) jsonObject.get("refresh_token");
//				String scope = (String) jsonObject.get("scope");
//				String errorCode = (String) jsonObject.get("errcode");
//				String errorMsg = (String) jsonObject.get("errmsg");
					
//				System.out.println("获取到的accessToken：" + accessToken);
//				System.out.println("获取到的openId：" + openId);
					
					//获取用户openid_oauth2-0
					String url1 = "https://graph.qq.com/oauth2.0/me?access_token="+accessToken+"&grant_type=authorization_code&client_id="+PayConfigUtil.QQ_APPID+"&client_secret="+PayConfigUtil.QQ_APPKEY+"&code="+code+"&grant_type=authorization_code&redirect_uri="+PayConfigUtil.QQ_REDIRECTURI;
					
					// 获取调用OpenAPI接口权限   获取client_id和openid
					JSONObject jsonObject1 = HttpUtil.httpsRequestJson(url1, "GET", null);
					if (null != jsonObject1) {
						String useropenId = jsonObject1.getString("openid");	//用户身份的标识
						String clientId = jsonObject1.getString("client_id");	//网站应用appid
						
						if(!StringUtils.isEmpty(useropenId)){
							String getUserInfoUrl = "https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key="+clientId+"&openid="+useropenId;
							JSONObject getUserInfo = HttpUtil.httpsRequestJson(getUserInfoUrl, "GET", null);
							
							String nickname = getUserInfo.getString("nickname");	//用户昵称
//						Integer sex = getUserInfo.getInteger("sex");
//						String country = getUserInfo.getString("country");	//所在国家
//						String province = getUserInfo.getString("province");	//所在省
//						String city = getUserInfo.getString("city");	//所在省
							String headimgurl = getUserInfo.getString("headimgurl");	//头像
							
							RegistUser registUser = registUserService.selectByUniqueKey(useropenId);
							if(null == registUser || registUser.getId()<0){
								RegistUser user = new RegistUser();
								user.setUniqueKey(useropenId);
								user.setPassword(useropenId);
								user.setUserName(useropenId);
								user.setPlainPassword(useropenId);
								
								String realName = URLEncoder.encode(nickname, "utf-8");
								
								user.setRealName(realName);
								user.setRegistType(3);
								user.setRegistTime(new Date());
//							user.setAddress(country+province+city);
								user.setHeadpicture(headimgurl);
								
								logger.debug("当前用户 " + nickname + " 不存在，新增用户后登录");
								
								registUserService.insertSelective(user);
								
								UsernamePasswordToken token = new UsernamePasswordToken(useropenId, useropenId);
								subject.login(token);
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
			logger.error("QQ登陆异常：" + e.getLocalizedMessage());
		}
		return "redirect:/index";
//		return "index";
	}

}
