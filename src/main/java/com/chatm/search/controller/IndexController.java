package com.chatm.search.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
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
import com.chatm.search.model.ArticleResource;
import com.chatm.search.model.ArticleVote;
import com.chatm.search.model.Dictionary;
import com.chatm.search.model.KeyWords;
import com.chatm.search.model.MyPage;
import com.chatm.search.model.Programa;
import com.chatm.search.model.UserInfo;
import com.chatm.search.model.VoteDetail;
import com.chatm.search.service.ArticleResourceService;
import com.chatm.search.service.ArticleService;
import com.chatm.search.service.DashijiService;
import com.chatm.search.service.DictionaryService;
import com.chatm.search.service.KeyWordsService;
import com.chatm.search.service.ProgramaService;
import com.chatm.search.service.VoteDetailServer;
import com.chatm.search.util.IpTool;
import com.chatm.search.util.MessageClient;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	DictionaryService dictionaryService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleResourceService articleResourceService;
	@Autowired
	KeyWordsService keyWordsService;
	@Autowired
	ProgramaService programaService;
	@Autowired
	VoteDetailServer voteDetailServer;
	@Autowired
	DashijiService dashijiService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request) throws IOException {
		UserInfo user = keyWordsService.getUserInfo();
		if (null != user && user.getId()>0) {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			
			//获取所有关键字，放到session中，以便首页搜索匹配
			List<String> keywN = (List<String>) session.getAttribute("keyWordsName");
			if(null != keywN && !keywN.isEmpty()){
			}else{
				List<KeyWords> keyWordsList = keyWordsService.selectKeyWordsBykeyword(null);
				if(null != keyWordsList && !keyWordsList.isEmpty()){
					int ki = keyWordsList.size();
					List list = new ArrayList();
					for (int i = 0; i < ki; i++) {
						KeyWords kw = keyWordsList.get(i);
	//					String cname = kw.getcName();
						list.add(kw);
					}
					session.setAttribute("keyWordsName", list);
				}
			}
			String tokenId = (String) session.getAttribute("tokenId");
			
			//微信登录跳转
			if(!StringUtils.isBlank(tokenId)){
				String code = request.getParameter("code");	//微信登录返回的值
				String state = request.getParameter("state");	//微信登录返回的值
				if(tokenId.equals(state) && !StringUtils.isBlank(code)){
					return "forward:/weixin";
				}
			}
			
			//首页推荐位
			List<Article> listArticle = (List<Article>) session.getAttribute("listArticleTuijian");
			MyPage<Article> page = new MyPage<Article>();
			page.setSize(50);
			if(null == listArticle || listArticle.isEmpty()){
				page.getParams().put("isTuijian", 1);
				List<Article> listArticleTuijian = articleService.selectArticleLimit(page);
				session.setAttribute("listArticleTuijian", listArticleTuijian);
			}
			//首页banner图
			List<Article> lista= (List<Article>) session.getAttribute("listBanner");
			if(null == lista || lista.isEmpty()){
				List<ArticleResource> listBanner = new ArrayList<ArticleResource>();
				List<Article> listArticleP = articleService.selectArticleByPid(0L);
				if(null != listArticleP && !listArticleP.isEmpty()){
					Article art = listArticleP.get(0);
					listBanner = articleResourceService.selectListByProgramaId(art.getId());
				}
				session.setAttribute("listBanner", listBanner);
			}
			/**/
			//首页获取二级菜单
			setSessionMeun(new Long(0));
//			setModelMeun(new Long(1),"gpyhList",model);
//			setModelMeun(new Long(6),"jctyList",model);
//			setModelMeun(new Long(15),"mszsList",model);
//			setModelMeun(new Long(23),"jqdlList",model);
//			setModelMeun(new Long(26),"bzList",model);
			
			getSEO((long) 0,model);
			return "index";
		} else {
			return "error/500";
		}
	}
	
	/**
	 * 根据首页一级菜单ID获取子菜单
	 * @param pid 一级菜单ID
	 * @return
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public List<Programa> IndexMenu(@PathVariable("id") Long pid){
		List<Programa> listPrograma = programaService.selectListProgramasByPid(pid);
		return listPrograma;
	}

	/**
	 * 获取首页二级菜单
	 * @param pid 父级ID
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年4月27日 下午5:43:03
	 */
	public List<Programa> getTwoMenu(Long pid) {
		List<Programa> listPrograma = programaService.selectListProgramasByPid(pid);
		return listPrograma;
	}
	
	/**
	 * Ajax获取二级菜单
	 * @param pid 一级菜单ID
	 * @return  Programa对象集合
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年5月4日 下午4:53:29
	 */
	@RequestMapping(value = "/getMenu/{pid}")
	@ResponseBody
	public List<Programa> getMenu(@PathVariable("pid") Long pid, Model model, HttpServletRequest request) {
		List<Programa> listPrograma = setSessionMeun(pid);
//		List<Programa> listPrograma = programaService.selectListProgramasByPid(pid);
//		model.addAttribute("gpyhList", listPrograma);
		return listPrograma;
	}
	
	/**
	 *	首页获取二级菜单
	 * @param pid 
	 */
	public List<Programa> setSessionMeun(Long pid) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		List<Programa> listPrograma = new ArrayList<Programa>();
		//购票优惠
		Object gpyhObj = session.getAttribute("gpyhList");
		if(null == gpyhObj) {
			if (null != pid && pid == 1) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("gpyhList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> gpyhList = getTwoMenu(new Long(1));	//购票优惠
				session.setAttribute("gpyhList", gpyhList);
			}
		} else {
			if (null != pid && pid == 1) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("gpyhList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> gpyhList = getTwoMenu(new Long(1));	//购票优惠
				session.setAttribute("gpyhList", gpyhList);
			}
		}
		
		//精彩体验
		Object jctyObj = session.getAttribute("jctyList");
		if(null == jctyObj) {
			if (null != pid && pid == 6) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("jctyList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> jctyList = getTwoMenu(new Long(6));	//精彩体验
				session.setAttribute("jctyList", jctyList);
			}
		} else {
			if (null != pid && pid == 6) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("jctyList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> jctyList = getTwoMenu(new Long(6));	//精彩体验
				session.setAttribute("jctyList", jctyList);
			}
		}
		
		//美食住宿
		Object mszsObj = session.getAttribute("mszsList");
		if(null == mszsObj) {
			if (null != pid && pid == 15) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("mszsList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> mszsList = getTwoMenu(new Long(15));	//美食住宿
				session.setAttribute("mszsList", mszsList);
			}
		} else {
			if (null != pid && pid == 15) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("mszsList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> mszsList = getTwoMenu(new Long(15));	//美食住宿
				session.setAttribute("mszsList", mszsList);
			}
		}
		
		//景区导览
		Object jqdlObj = session.getAttribute("jqdlList");
		if(null == jqdlObj) {
			if (null != pid && pid == 23) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("jqdlList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> jqdlList = getTwoMenu(new Long(23));	//景区导览
				session.setAttribute("jqdlList", jqdlList);
			}
		} else {
			if (null != pid && pid == 23) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("jqdlList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> jqdlList = getTwoMenu(new Long(23));	//景区导览
				session.setAttribute("jqdlList", jqdlList);
			}
		}
		
		//帮助
		Object bzlObj = session.getAttribute("bzList");
		if(null == bzlObj) {
			if (null != pid && pid == 26) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("bzList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> bzList = getTwoMenu(new Long(26));	//帮助
				session.setAttribute("bzList", bzList);
			}
		} else {
			if (null != pid && pid == 26) {
				listPrograma = programaService.selectListProgramasByPid(pid);
				session.setAttribute("bzList", listPrograma);
			} else if (null != pid && pid == 0) {
				List<Programa> bzList = getTwoMenu(new Long(26));	//帮助
				session.setAttribute("bzList", bzList);
			}
		}
		
		return listPrograma;
	}
	
	public List<Programa> setModelMeun(Long pid, String list, Model model) {
		List<Programa> listPrograma = programaService.selectListProgramasByPid(pid);
		model.addAttribute(list, listPrograma);
		return listPrograma;
	}
	
	/**
	 * 景区介绍页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/introduction.html", method = RequestMethod.GET)
	public String introduction(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		return "/index/introduction";
	}
	
	/**
	 * 帮助
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/bangzhu.html", method = RequestMethod.GET)
	public String bangzhu(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		
		return "/index/bangzhu";
	}
	
	/**
	 * 游客服务
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/youkefuwu.html", method = RequestMethod.GET)
	public String youkefuwu(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		
		return "/index/bangzhu";
	}
	
	/**
	 * 联系电话
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/lianxidianhua.html", method = RequestMethod.GET)
	public String lianxidianhua(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		
		return "/index/lianxidianhua";
	}
	
	/**
	 * 精彩体验
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/experience.html", method = RequestMethod.GET)
	public String experience(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		//1、根据当前id获取下面的子模块ID
		List<Programa> list = articleService.selectArticleByParentProgramaId(programaId);
		List<Article> listArticle = new ArrayList<Article>();
		//2、根据子模块ID到article根据programa_id获取内容
		if(null != list && !list.isEmpty()){
			int l = list.size();
			for (int i = 0; i < l; i++) {
				MyPage<Article> page = new MyPage<Article>();
				page.setSize(50);
				page.getParams().put("programaId", list.get(i).getId());
				List<Article> listar = articleService.selectArticleLimit(page);
				if(null != listar && !listar.isEmpty()){
					for (int j = 0; j < listar.size(); j++) {
						Article ar = listar.get(j);
						listArticle.add(ar);
					}
				}
			}
		}
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("aspTitle", "精彩体验");
		getSEO(programaId,model);
		//把子模块组成list放到页面中
		return "/index/experience";
	}
	
	/**
	 * 交通信息
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/jiaotongxinxi.html", method = RequestMethod.GET)
	public String jiaotongxinxi(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		
		return "/index/jiaotongxinxi";
	}
	
	/**
	 * 导览图
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/daolantu.html", method = RequestMethod.GET)
	public String daolantu(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		getSEO(programaId,model);
		
		return "/index/daolantu";
	}
	
	/**
	 * 美食与住宿
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/meishizhusu.html", method = RequestMethod.GET)
	public String meishizhusu(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		//1、根据当前id获取下面的子模块ID
		List<Programa> list = articleService.selectArticleByParentProgramaId(programaId);
		List<Article> listArticle = new ArrayList<Article>();
		//2、根据子模块ID到article根据programa_id获取内容
		if(null != list && !list.isEmpty()){
			int l = list.size();
			for (int i = 0; i < l; i++) {
				MyPage<Article> page = new MyPage<Article>();
				page.getParams().put("programaId", list.get(i).getId());
				List<Article> listar = articleService.selectArticleLimit(page);
				if(null != listar && !listar.isEmpty()){
					for (int j = 0; j < listar.size(); j++) {
						Article ar = listar.get(j);
						listArticle.add(ar);
					}
				}
			}
		}
		getSEO(programaId,model);
		model.addAttribute("listArticle", listArticle);
		model.addAttribute("aspTitle", "美食&住宿");
		
//		return "/index/experience";
		return "/index/meishizhusu";	//原来美食&住宿和精彩体验2个菜单是2个不同的页面，现在改成一个页面
	}
	
	/**
	 * 优惠活动
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/yhhuodong.html", method = RequestMethod.GET)
	public String yhhuodongv(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber, Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		
		return "/index/yhhuodong";
	}
	
	/**
	 * 活动详情
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/huodong.html", method = RequestMethod.GET)
	public String huodong(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		Article article = articleService.selectByPrimaryKey(programaId);
		model.addAttribute("article", article);
		return "/index/huodong";
	}
	
	/**
	 * 根据二级栏目ID获取当前栏目同级所有的栏目和栏目下的图片，放到页面中
	 * @param programaId
	 * @param model
	 */
	private void getProArtValue(Long programaId, Model model) {
		//根据栏目ID获取父级ID，并根据父级ID获取同级的栏目id
		List<Programa> list = articleService.seletProgramaBrotherById(programaId);
		List<Article> articleList = new ArrayList<Article>();
		if(null != list && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				Programa pr = list.get(i);
				model.addAttribute("pid", pr.getParentId());
				//1、根据栏目ID获取文章ID
				MyPage<Article> page = new MyPage<Article>();
				page.setSize(50);
				page.getParams().put("programaId", pr.getId());
				List<Article> listar = articleService.selectArticleLimit(page);
				List<ArticleResource> arrlist  = new ArrayList<ArticleResource>();
				if(null != listar && !listar.isEmpty()){
					for (int j = 0; j < listar.size(); j++) {
						Article ar = listar.get(j);
						//2、根据文章ID获取资源内容
						arrlist = articleResourceService.selectListByProgramaId(ar.getId());
						ar.setArticleResourceList(arrlist);
						articleList.add(ar);
						
						if(pr.getId()==programaId){
							model.addAttribute("idoid", ar.getProgramaId());
							model.addAttribute("titleName", pr.getName());
							model.addAttribute("arrlist", arrlist);
						}
					}
				}
			}
			model.addAttribute("articleList", articleList);
		}
	}

	/**
	 * 猛兽喂养
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/mengshou.html", method = RequestMethod.GET)
	public String mengshou(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		
		return "/index/mengshou";
	}

	/**
	 * 明星熊猫
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/xiongmao.html", method = RequestMethod.GET)
	public String xiongmao(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		
		getSEO(programaId,model);
		
		return "/index/xiongmao";
//		return "/index/mengshou";
	}
	
	/**
	 * 低空飞行
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/fly.html", method = RequestMethod.GET)
	public String fly(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/fly";
//		return "/index/mengshou";
	}
	
	/**
	 * 美景赏析
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/shangxi.html", method = RequestMethod.GET)
	public String shangxi(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/shangxi";
//		return "/index/mengshou";
	}
	
	/**
	 * 欢乐剧场
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/juchang.html", method = RequestMethod.GET)
	public String juchang(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/juchang";
//		return "/index/mengshou";
	}
	
	/**
	 * 欢乐家园
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/jiayuan.html", method = RequestMethod.GET)
	public String canyin(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/jiayuan";
//		return "/index/mengshou";
	}
	
	/**
	 * 森林探险
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/tanxian.html", method = RequestMethod.GET)
	public String tanxian(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/tanxian";
//		return "/index/mengshou";
	}
	
	/**
	 * 悬崖别墅
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/bieshu.html", method = RequestMethod.GET)
	public String bieshu(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/bieshu";
//		return "/index/mengshou";
	}
	
	/**
	 * 度假酒店
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/jiudian.html", method = RequestMethod.GET)
	public String jiudian(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/jiudian";
//		return "/index/mengshou";
	}
	
	/**
	 * 碧峰餐厅
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/canting.html", method = RequestMethod.GET)
	public String canting(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/canting";
//		return "/index/mengshou";
	}
	
	/**
	 * 云海茶楼
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/chalou.html", method = RequestMethod.GET)
	public String chalou(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		getProArtValue(programaId,model);
		getSEO(programaId,model);
		return "/index/chalou";
//		return "/index/mengshou";
	}
	
	/**
	 * 微信
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/weixin.html", method = RequestMethod.GET)
	public String weixin(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		
		return "/index/weixin";
	}
	
	/**
	 * 微博
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/weibo.html", method = RequestMethod.GET)
	public String weibo(@PathVariable("programaId") Long programaId, Model model,HttpServletRequest request) throws IOException {
		
		return "/index/weibo";
	}
	
	/**
	 * 英文页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/english.html", method = RequestMethod.GET)
	public String englist(Model model,HttpServletRequest request) throws IOException {
		model.addAttribute("isEnglish", "1");
		return "/index/English";
	}
	
	/**
	 * 日文页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/japanese.html", method = RequestMethod.GET)
	public String japanese(Model model,HttpServletRequest request) throws IOException {
		model.addAttribute("isEnglish", "2");
		return "/index/japanese";
	}
	
	/**
	 * 韩文页面
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/korean.html", method = RequestMethod.GET)
	public String korean(Model model,HttpServletRequest request) throws IOException {
		model.addAttribute("isEnglish", "3");
		return "/index/korean";
	}
	
	/**
	 * 大事记
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/dashiji.html", method = RequestMethod.GET)
	public String dashiji(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		/*
		MyPage<Dashiji> page = new MyPage<Dashiji>();
		page.setNumber(pageNumber);
		//查询条件
		String cName = request.getParameter("cName");
		if(!StringUtils.isEmpty(cName)) {
			model.addAttribute("cName", cName);
			page.getParams().put("cName", "%" + cName + "%");
			String searchParams = "&cName=" + cName;
			model.addAttribute("searchParams", searchParams);
		}
		List<Dashiji> dashijiList = dashijiService.selectDashijiLimit(page);
		model.addAttribute("dashijiList", dashijiList);
		model.addAttribute("pageData", page);
		*/
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		
		return "/index/dashiji";
	}
	
	/**
	 * 大事记详情
	 * @param programaId
	 * @param model
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年5月15日 下午12:36:33
	 */
	@RequestMapping(value="/{programaId}/dashijiView.html", method = RequestMethod.GET)
	public String dashijiView(@PathVariable("programaId") Long programaId,Model model) {
		Article article = articleService.selectByPrimaryKey(programaId);
		model.addAttribute("article", article);
		
//		Dashiji dashiji = dashijiService.selectByPrimaryKey(programaId);
//		model.addAttribute("dashiji", dashiji);
		return "/index/dashijiView";
	}
	
	/**
	 * 新闻中心
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/news.html", method = RequestMethod.GET)
	public String news(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", programaId);
		getArticeByProgramaId(model,page);
		return "/index/news";
	}
	
	/**
	 * 新闻中心里面的新闻
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/news/{id}/newDetail.html", method = RequestMethod.GET)
	public String newDetail(@PathVariable("id") Long id,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", 36);
		List<Article> xinwenList = getArticeByProgramaId(model,page);
		model.addAttribute("xinwenList", xinwenList);
		
		page.getParams().put("programaId", 37);
		List<Article> list2 = getArticeByProgramaId(model,page);
		model.addAttribute("meitiList", list2);
		
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		
		String voteChoose = "";
		VoteDetail voteDetail = new VoteDetail();
		voteDetail.setVoteIp(IpTool.getClientIPAddress(request));
		List<VoteDetail> list = voteDetailServer.selectByVote(voteDetail);	//根据当前用户登录IP获取是否有投票记录
		if(null != list && !list.isEmpty()){
			VoteDetail vd = list.get(0);
			voteChoose = vd.getVoteChoose();
		}
		model.addAttribute("voteChoose", voteChoose);
		
		ArticleVote articleVote = articleService.selectArticleVoteByarId(id);
		model.addAttribute("articleVote", articleVote);
		
		return "/index/news/newDetail";
	}

	/**
	 * 媒体报道
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/{programaId}/meiti.html", method = RequestMethod.GET)
	public String meiti(@PathVariable("programaId") Long programaId,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.setSize(10);
		page.getParams().put("programaId", 36);	//新闻中心
		List list1 = getArticeByProgramaId(model,page);
		model.addAttribute("xinwenList", list1);
		
		page.getParams().put("programaId", 37);	//媒体报道
		List list2 = getArticeByProgramaId(model,page);
		model.addAttribute("meitiList", list2);
		return "/index/meiti";
	}
	
	/**
	 * 媒体报道正文
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/meiti/{id}/meitiDetail.html", method = RequestMethod.GET)
	public String meiti1(@PathVariable("id") Long id,@RequestParam(value = "page", defaultValue = "1") int pageNumber,Model model,HttpServletRequest request) throws IOException {
		MyPage<Article> page = new MyPage<Article>();
		page.setNumber(pageNumber);
		page.getParams().put("programaId", 36);
		List xinwenList = getArticeByProgramaId(model,page);
		model.addAttribute("xinwenList", xinwenList);
		
		page.getParams().put("programaId", 37);
		List list2 = getArticeByProgramaId(model,page);
		model.addAttribute("meitiList", list2);
		Article article = articleService.selectByPrimaryKey(id);
		model.addAttribute("article", article);
		
		String voteChoose = "";
		VoteDetail voteDetail = new VoteDetail();
		voteDetail.setVoteIp(IpTool.getClientIPAddress(request));
		List<VoteDetail> list = voteDetailServer.selectByVote(voteDetail);	//根据当前用户登录IP获取是否有投票记录
		if(null != list && !list.isEmpty()){
			VoteDetail vd = list.get(0);
			voteChoose = vd.getVoteChoose();
		}
		model.addAttribute("voteChoose", voteChoose);
		ArticleVote articleVote = articleService.selectArticleVoteByarId(id);	//投票
		model.addAttribute("articleVote", articleVote);
		
		return "/index/meiti/meitiDetail";
	}

	/**
	 * 媒体报道正文
	 * @param model
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/meiti/zhengwen.html", method = RequestMethod.GET)
	public String zhengwen(Model model,HttpServletRequest request) throws IOException {
		return "/index/meiti/zhengwen";
	}
	
	/**
	 * ajax登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public String ajaxLogin(HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String url = request.getHeader("Referer");
		String value = url.substring(url.length()-1,url.length());
		String ret="";
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {  
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
//            token.setRememberMe(rememberMe);  
            try {  
                currentUser.login(token);  
                ret = "{success:true,message:'登陆成功'}";  
            } catch (UnknownAccountException ex) {  
                ret = "{success:false,message:'账号错误'}";  
            } catch (IncorrectCredentialsException ex) {  
                ret = "{success:false,message:'密码错误'}";  
            } catch (LockedAccountException ex) {  
                ret = "{success:false,message:'账号已被锁定，请与管理员联系'}";  
            } catch (AuthenticationException ex) {  
                ret = "{success:false,message:'您没有授权'}";  
            }  
        }
		return "redirect:/commodity/list/"+value;
//		return ret;
	}
	
	/**
	 * 根据programaId分页获取文章信息
	 * @param model 
	 * @param page
	 * @return
	 */
	public List<Article> getArticeByProgramaId(Model model, MyPage<Article> page){
		List<Article> list = articleService.selectArticleLimit(page);
		if(!list.isEmpty() && list.size()>0){
			int l = list.size();
			if(l>1){
				model.addAttribute("articleList", list);
			}else{
				Article article = list.get(0);
				model.addAttribute("article", article);
			}
		}
		model.addAttribute("pageData", page);
		return list;
	}
	
	/**
	 * 赋值给前台keyword和Description
	 * @param programaId
	 * @param model
	 */
	private void getSEO(Long programaId, Model model) {
		MyPage<Dictionary> page = new MyPage<Dictionary>();
		page.setNumber(1);
		page.getParams().put("code", "seo");
		page.getParams().put("programaId", programaId);
		List<Dictionary> dictionaryList = dictionaryService.selectDictionaryLimit(page);
		if(!dictionaryList.isEmpty() && dictionaryList.size()>0){
			int ds = dictionaryList.size();
			for (int i = 0; i < ds; i++) {
				Dictionary dic = dictionaryList.get(i);
				model.addAttribute(dic.getName(), dic.getContent());
			}
		}
	}
	
	/**
	 * 加载首页友情链接
	 * @return
	 */
	@RequestMapping("/getFriendly")
	@ResponseBody
	public List<Dictionary> getFriendly(){
		List<Dictionary> list = new ArrayList<Dictionary>();
	    Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<Dictionary> list1 = (List<Dictionary>) session.getAttribute("listDictionary");
		if(null == list1 || list1.isEmpty()){
			MyPage<Dictionary> page = new MyPage<Dictionary>();
			page.setSize(50);
		    page.getParams().put("parentCode", "friendManager");
		    list = dictionaryService.selectDictionaryLimit(page);
		    session.setAttribute("listDictionary", list);
		}else{
			list = list1;
		}
	    return list;
	}
	
	/**
	 * 检测是否为移动端设备访问
	 * @param userAgent
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pujianhua  
	 * @createtime 2016年5月3日 上午10:25:25
	 */
    public boolean checkMobile(String userAgent){
    	// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),    
        // 字符串在编译时会被转码一次,所以是 "\\b"    
        // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)    
        String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
                +"|windows (phone|ce)|blackberry"    
                +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
                +"|laystation portable)|nokia|fennec|htc[-_]"    
                +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
        String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
          
        //移动设备正则匹配：手机端、平板  
        Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
        Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE); 
    	
        if(null == userAgent){    
            userAgent = "";    
        }    
        // 匹配    
        Matcher matcherPhone = phonePat.matcher(userAgent);    
        Matcher matcherTable = tablePat.matcher(userAgent);    
        if(matcherPhone.find() || matcherTable.find()){    
            return true;    
        } else {    
            return false;    
        }    
    }
	
	
	/**
	 * 关闭
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "shutdown/{id}", method = RequestMethod.GET)
	public String shutdown(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		
		keyWordsService.shutdownUserInfoById(id);
		return null;
	}
	
	/**
	 * 启动
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "start/{id}", method = RequestMethod.GET)
	public String startUserInfo(@PathVariable("id") Long id, Model model,HttpServletRequest request) {
		
		keyWordsService.startUserInfoById(id);
		return "index";
	}
	
	@RequestMapping(value = "testMsg/{phone}", method = RequestMethod.GET)
	@ResponseBody
	public String testMsg(@PathVariable("phone") String phone) {
		String sn="SDK-BBX-010-25168";
		String pwd="9-ca8f-[";
		String result = null;
		MessageClient client;
		try {
			logger.info("发送短信准备， 手机号：" + phone);
			client = new MessageClient(sn,pwd);
			String content = URLEncoder.encode("这是一个测试短信"+"【碧峰峡】", "utf-8");
			result = client.mdsmssend(phone, content, "", "", "", "");
			if (!StringUtils.isEmpty(result)) {
				long res = Long.valueOf(result);
				if(res > 0){	//发送成功
					logger.info("测试发送短信成功====" + result + "  手机号：" + phone);
				} else {
					logger.info("测试发送短信失败====" + result + "  手机号：" + phone);
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error("测试发送短信失败====  手机号：" + phone + "   错误信息：" + e.getLocalizedMessage());
		}
		return result;
	}
}
