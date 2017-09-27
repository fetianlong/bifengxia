package com.chatm.search.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.model.Article;
import com.chatm.search.model.KeyWords;
import com.chatm.search.model.MyPage;
import com.chatm.search.service.ArticleService;

@Controller
@RequestMapping("/keywordSearch")
public class KeywordController {
	
	private static final Logger logger = LoggerFactory.getLogger(KeywordController.class);
	
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public String list(@RequestParam("searchKey") String akeywords,Model model, 
			@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
		try{
			MyPage<Article> page = new MyPage<Article>();
			StringBuilder searchParams = new StringBuilder();
			if(!StringUtils.isEmpty(akeywords)) {
				searchParams.append("&searchKey="+akeywords);
				page.setNumber(pageNumber);
				page.getParams().put("status", 1);
				//查询条件
				model.addAttribute("searchKey", akeywords);
				page.getParams().put("keywords", "%" + akeywords + "%");
				
				List<Article> articleList = articleService.selectArticleLimit(page);
				model.addAttribute("articleList", articleList);
				model.addAttribute("searchParams", searchParams);
			}
			model.addAttribute("pageData", page);
		}catch(Exception e) {
			logger.error("首页查询关键字错误：" + e.getMessage());
		}
		return "article/articleList";
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		
		Article art = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", art);
		
		return "article/articleView";
	}
	
	/**
	 * 首页搜索时匹配关键字
	 * @param keyword
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getkeyword/{keyword}")
	@ResponseBody
	public List getkeyword(@PathVariable("keyword")String keyword,Model model){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<KeyWords> keywNList = (List<KeyWords>) session.getAttribute("keyWordsName");
		List<String> list = new ArrayList<String>();
		if(null != keywNList && !keywNList.isEmpty()){
			int ki = keywNList.size();
			for (int i = 0; i < ki; i++) {
				KeyWords kw = keywNList.get(i);
				String cname = kw.getcName();
				String pname = kw.getpName();
				if(StringUtils.contains(cname, keyword.trim()) || StringUtils.contains(pname, keyword.trim())){
					list.add(cname);
				}
			}
		}
		
		
		return list;
	}
	
	
}
