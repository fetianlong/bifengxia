package com.chatm.search.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.chatm.search.shiro.ShiroDbRealm.ShiroUser;

public class UserUtils {

	
	protected static final Log LOG = LogFactory.getLog(UserUtils.class);
	
	/**
	 * 判断是否登录
	 * @return  
	 * @description   
	 * @version 1.0  
	 * @author jiang.shao  
	 * @createtime 2016-4-8 下午5:01:00
	 */
	public static Boolean hasLogin() {

		Subject subject = SecurityUtils.getSubject();
		PrincipalCollection principalCollection = subject.getPrincipals();
		if (principalCollection == null) {// 未登录
			return false;
		} else {
			return true;
		}

	}
	
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}  
	
	@SuppressWarnings("unused")
	public static ShiroUser getRegistUser(){
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) currentUser.getPrincipal();
		return user;
	}
}
