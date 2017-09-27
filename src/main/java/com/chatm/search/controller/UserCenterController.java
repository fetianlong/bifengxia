package com.chatm.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.model.RegistUser;
import com.chatm.search.service.RegistUserService;
import com.chatm.search.shiro.ShiroDbRealm.ShiroUser;

@Controller
@RequestMapping("/userCenter")
public class UserCenterController {
	protected static final Log LOG = LogFactory.getLog(UserCenterController.class);
	@Autowired
	private RegistUserService registUserService ;
	

	@RequestMapping("/gotoUserCenter")
	public String gotoUserCenter(Model model){
		RegistUser registUser = registUserService.selectByPrimaryKey(getRegistUser().id);
		model.addAttribute("registUser", registUser);
		
		return "userCenter/personalset";
	}
	

	//绑定新手机  0 绑定成功 1 验证码不正确 2 用户session超时
	@RequestMapping("/changePhone")
	@ResponseBody
	public String  changePhone(HttpServletRequest request, Model model) {
		String newphone = request.getParameter("newphone");
		String invCode = request.getParameter("invCode");
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		String vCode = (String) session.getAttribute("changePhoneCode");
		if (vCode!=null) {
			String[] strings = vCode.split("-");
			if (strings.length==2) {
				if (!strings[1].equals(newphone)) {
					return "1";//手机号不正确-->验证码不正确
				}
			}
			vCode = strings[0];
			if (vCode.equals(invCode)) {
				//更改手机
				RegistUser registUser = registUserService.selectByPrimaryKey(getRegistUser().id);
				registUser.setPhone(newphone);
				registUser.setUserName(newphone);
				registUserService.updateByPrimaryKey(registUser);
				session.setAttribute("registUser", registUser);
				session.setAttribute("changePhoneCode", null);
				return "0";
			}else {
				return "1";
			}
		}
		return "1";
	}
	
	//更改个人信息
	@RequestMapping("/upRegistUser")
//	@ResponseBody
	public String upRegistUser(@ModelAttribute("registUser") RegistUser registUser, HttpServletRequest request,Model model) {
		try {
//			RegistUser registUser = registUserService.selectByPrimaryKey(getRegistUser().id);
			registUserService.updateByPrimaryKeySelective(registUser);
			model.addAttribute("registUser", registUser);
		} catch (Exception e) {
			e.printStackTrace();
//			LOG.warn(e);
//			return 1;
		}
		return "redirect:/userCenter/gotoUserCenter";
//		return 0;
	}
	
	private ShiroUser getRegistUser(){
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) currentUser.getPrincipal();
		return user;
	}
	
}
