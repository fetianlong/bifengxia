package com.chatm.search.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.model.RegistUser;
import com.chatm.search.service.RegistUserService;
import com.chatm.search.util.MessageClient;
import com.chatm.search.util.UserUtils;

@Controller
@RequestMapping("/registUser")
public class RegistUserController {
	
	protected static final Log LOG = LogFactory.getLog(RegistUserController.class);
	
	@Autowired
	private RegistUserService registUserService ;
	
	@RequestMapping(method = RequestMethod.GET)
	public String regist(Model model,HttpServletRequest request) throws IOException {
		
		return "/userInfo/registUser";
	}
	@RequestMapping("/findpwd")
	public String findpwd(){
		return "userInfo/findpwd";
	}
	
	/**
	 * 验证短信验证码是否正确
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/isValitaCode")
	@ResponseBody
	public Boolean isValitaCode(HttpServletRequest request, Model model) {
		boolean flag = true;

		String phone = request.getParameter("mobilephone");
		String vCode = request.getParameter("vCode");
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		Integer vcode = (Integer) session.getAttribute(phone);
		if(Integer.parseInt(vCode) != vcode){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 发送短信消息，并把验证码存放在session中
	 * @param type 发送验证码类型，0是注册，1是忘记密码
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendMeseege/{type}")
	@ResponseBody
	public String sendMeseege(@PathVariable("type")Integer type, HttpServletRequest request, Model model) {
		String phone = request.getParameter("mobilephone");
		
		String sn="SDK-BBX-010-25168";
		String pwd="9-ca8f-[";
		MessageClient client;
		try {
			client = new MessageClient(sn,pwd);
			//获取信息
			int code = (int) (Math.random()*9000+1000);
			String content = URLEncoder.encode("您的验证码是"+code+"【碧峰峡】", "utf-8");
			if(type==1 && registUserService.isExistByPhone(phone)){	//当前为找回密码，且当前手机号已注册
				String msg = client.mdsmssend(phone, content, "", "", "", "");
				LOG.info("找回密码--发送短信信息：" + msg);
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				session.setAttribute(phone, code);
			}else if(type==0 && !registUserService.isExistByPhone(phone)){	//当前为注册，且当前手机未注册
				String msg = client.mdsmssend(phone, content, "", "", "", "");
				LOG.info("注册--发送短信信息：" + msg);
				Subject currentUser = SecurityUtils.getSubject();  
				Session session = currentUser.getSession();
				session.setAttribute(phone, code);
			}
		} catch (UnsupportedEncodingException e) {
			LOG.error("发送短信失败：" + e);
		}
		
		return "";
	}
	
	/**
	 *存在用户返回 1 不存在返回 0
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/isExistByEmail")
	@ResponseBody
	public String isExistByEmail(HttpServletRequest request, Model model) {
		//前端判断传入类型  0 手机  1用户名
		String email = request.getParameter("email");
		if (!StringUtils.isEmpty(email)) {
			return true==registUserService.isExistByEmail(email) ? "1":"0";
		}else {
			return "0";
		}
	}
	
	
	/**
	 * 验证手机号是否存在
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkPhoneExist/{type}")
	@ResponseBody
	public Boolean checkPhoneExist(@PathVariable("type")Integer type, HttpServletRequest request, Model model) {
		Boolean flag = false;
		String mobilephone=request.getParameter("userName").trim();
		if (registUserService.isExistByPhone(mobilephone)) {
			
		}else {
			flag = true;
		}
		if(type==1){
			if(flag){
				flag=false;
			}else{
				flag=true;
			}
		}
		return flag;
		
	}
	
	@RequestMapping(value = "/regRegistUser", method = RequestMethod.POST)
	public String regRegistUser(@Valid @ModelAttribute("registUser") RegistUser registUser, HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		String mobilephone = registUser.getUserName().trim();
		Subject subject = SecurityUtils.getSubject();  
//		Session session = currentUser.getSession();
//		String vCode = (String) session.getAttribute("vCode");
		if (!UserUtils.isMobileNO(mobilephone)) {
			return "redirect:/logout";
		}
		//先查是否已存在该用户
		if (registUserService.isExistByPhone(mobilephone)) {
			return "registUser/findpwd";
		}
		registUser.setPhone(mobilephone);
		registUser.setRegistTime(new Date(System.currentTimeMillis()));
		registUser.setRegistType(1);
		registUser.setRealName(URLEncoder.encode(mobilephone, "utf-8"));
		
		registUserService.insertSelective(registUser);
		
		Session session = subject.getSession();
		session.setAttribute(mobilephone, "");	//注册成功后，清除session中的验证码
//		return "redirect:/login";
		return "userInfo/registOk";
		
	}
		
	@RequestMapping(value = "/findPwd",method = RequestMethod.POST)
	public String findPwd(@Valid @ModelAttribute("registUser") RegistUser registUser, HttpServletRequest request, Model model){
		
		RegistUser user = registUserService.selectRegistUserByMobile(registUser.getUserName());
		if(null != user && user.getId()>0){
			registUser.setId(user.getId());
		}
//		String code = request.getParameter("code").trim();
		Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		registUserService.updateByPrimaryKeySelective(registUser);
		session.setAttribute(registUser.getUserName(), "");
		
		return "userInfo/findpwdOk";
	}
	
}
