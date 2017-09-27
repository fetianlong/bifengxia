package com.chatm.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.util.CheckMobile;
import com.chatm.search.weixinpay.MD5Util;
import com.chatm.search.weixinpay.PayConfigUtil;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 
 * 真正登录的POST请求由Filter完
 * 
 * @description   
 * @version currentVersion(1.0)  
 * @author pujianhua  
 * @createtime 2016年3月29日 下午4:00:08
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		//session中放入前页路径
		String url = request.getHeader("Referer");
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		PrincipalCollection principalCollection = subject.getPrincipals();
		if(principalCollection != null){
			return "redirect:index";
		}
		
		session.setAttribute("oldUrl", url);
		
		return "/login/login";
	}
	
	@RequestMapping("/getLoginUrl/{type}")
	@ResponseBody
	public String otherLogin(@PathVariable("type") int type, HttpServletRequest request){
		
		boolean isFromMobile = false; 
		String oldURL = request.getHeader("Referer");
//		System.out.println("前级URL："  + oldURL);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		String tokenId = (String) session.getAttribute("tokenId");
		
		if(StringUtils.isBlank(tokenId)){
			tokenId = MD5Util.getToken(PayConfigUtil.APPID);
			session.setAttribute("tokenId", tokenId);
			session.setAttribute("loginOldURL", oldURL);
		}
        
		String url = "";
		if(type==2){	//微信登陆方式
			//获取ua，用来判断是否为移动端访问  
	        String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
	        if(null == userAgent){    
	            userAgent = "";    
	        }
	        isFromMobile = CheckMobile.check(userAgent);
	        if(isFromMobile){
	        	System.out.println("wx 手机端登陆");
	        	url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+PayConfigUtil.APPID+"&redirect_uri="+PayConfigUtil.REDIRECTURI+"&response_type=code&scope=snsapi_login&state="+tokenId+"#wechat_redirect";
	        }else{
	        	System.out.println("wx web端登陆");
	        	url = "https://open.weixin.qq.com/connect/qrconnect?appid="+PayConfigUtil.APPID+"&redirect_uri="+PayConfigUtil.REDIRECTURI+"&response_type=code&scope=snsapi_login&state="+tokenId+"#wechat_redirect";
	        }
		}else if(type==3){	//qq登陆方式
			System.out.println("qq登陆");
			url = "https://graph.qq.com/oauth2.0/authorize?response_type=code&state="+tokenId+"&client_id="+PayConfigUtil.QQ_APPID+"&redirect_uri="+PayConfigUtil.QQ_REDIRECTURI+"&scope=get_user_info";
		}
		return url;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login/login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject(); 
		subject.logout();
		
		String url = request.getHeader("Referer");
		
		return "/login/login";
	}
}
