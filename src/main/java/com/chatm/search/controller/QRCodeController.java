package com.chatm.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.weixinpay.GenerateQrCodeUtil;

@Controller
public class QRCodeController {
	
	@RequestMapping("/{code}")
	public String getQrcode(@PathVariable("code") String code, HttpServletRequest request) {
		return "forward:/qr_codeImg?code_url=" + code;
	}
	
	/**
	 * 以流的方式返回数据到页面
	 * @param request
	 * @param response  
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年6月20日 下午2:06:25
	 */
	@RequestMapping("/qr_codeImg")
	public void getQRCode(HttpServletRequest request, HttpServletResponse response) {
		String content = request.getParameter("code_url");
        GenerateQrCodeUtil.encodeQrcode(content, response);
	}
}
