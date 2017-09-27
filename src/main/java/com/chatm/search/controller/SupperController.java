package com.chatm.search.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.chatm.search.model.Dictionary;
import com.chatm.search.service.DictionaryService;

@Controller
public class SupperController {
	private static final Logger logger = LoggerFactory.getLogger(SupperController.class);
	@Autowired
	DictionaryService dictionaryService;
	
	public void getDictionary(String code, String codeValue, Model model) {
		try {
//			Subject currentUser = SecurityUtils.getSubject();
//			Session session = currentUser.getSession();
//			Object obj = session.getAttribute("dictionary");
//			if(null == obj) {
				Dictionary dictionary = new Dictionary();
			    dictionary.setCode(code);
		//	    dictionary.setCode("sptype");
			    dictionary.setCodeValue(codeValue);
			    List<Dictionary> listDictionary = dictionaryService.getListDictionary(dictionary);
			    if (null != listDictionary && !listDictionary.isEmpty()) {
			    	dictionary = listDictionary.get(0);
			    } else {
			    	logger.info("获取商品模板信息失败");
			    }
			    model.addAttribute("dictionary", dictionary);
//				session.setAttribute("dictionary", dictionary);
//			}
		} catch (Exception e) {
			logger.error("获取商品模板信息异常：" + e.getLocalizedMessage());
		}
	}
	
}
