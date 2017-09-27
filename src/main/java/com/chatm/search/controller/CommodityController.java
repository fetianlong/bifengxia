package com.chatm.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.chatm.search.model.Commodity;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.Dictionary;
import com.chatm.search.service.CommodityPriceService;
import com.chatm.search.service.CommodityService;
/**
 * 商品管理
 * @author pu
 *
 */
@Controller
@RequestMapping(value = "/commodity")
public class CommodityController extends SupperController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@Autowired
	CommodityService commodityService;
	@Autowired
	CommodityPriceService commodityPriceService;
//	@Autowired
//	DictionaryService dictionaryService;
	
	@RequestMapping(value = "/list/{ctype}", method = RequestMethod.GET)
	public String list(@PathVariable("ctype") Long ctype, Model model,@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			HttpServletRequest request){
	    List<Commodity> commodityList = commodityService.selectCommodityByType(ctype);
	    if(null != commodityList && !commodityList.isEmpty()){
	    	int commoditysize = commodityList.size();
	    	for (int i = 0; i < commoditysize; i++) {
		    	Commodity commodity = commodityList.get(i);
		    	List<CommodityPrice> commodityPricelist = commodityPriceService.selectPriceBycomId(commodity.getId());
		    	if(!commodityPricelist.isEmpty()){
	    			commodity.setCommodityPrice(commodityPricelist);
		    		CommodityPrice comp = commodityPricelist.get(0);
		    		
		    		commodity.setOldPrice(comp.getOldPrice());
		    		commodity.setPrice(comp.getNewPrice());
		    		commodity.setCommodityType(comp.getId());	//票种ID 
		    		if(i==0){
		    			model.addAttribute("compId", comp.getId());	//票种ID
		    			model.addAttribute("comId", commodity.getId());	//商品ID
		    		}
		    	}
//		    	model.addAttribute("commodityPricelist", commodityPricelist);
	    	}
	    	
	    }
	    model.addAttribute("commodityList", commodityList);
	    
	    Dictionary dictionary = new Dictionary();
	    dictionary.setCode("sptype");
//	    dictionary.setCode("sptype");
	    dictionary.setCodeValue(ctype.toString());
	    List<Dictionary> listDictionary = dictionaryService.getListDictionary(dictionary);
	    if (null != listDictionary && !listDictionary.isEmpty()) {
	    	dictionary = listDictionary.get(0);
	    } else {
	    	logger.info("获取商品模板信息失败");
	    }
	    model.addAttribute("dictionary", dictionary);
	    model.addAttribute("title", dictionary.getName());
	    model.addAttribute("ctype", ctype);
	    this.getDictionary("sptype", ctype.toString(), model);
//	    model.addAttribute("dictionary", dictionary);
	    
	    /*if(ctype==0){
	    	model.addAttribute("title", "门票");
	    }else if(ctype==2){
	    	model.addAttribute("title", "酒店");
	    }else if(ctype==1){
	    	model.addAttribute("title", "餐饮");
	    }else if(ctype==3){
	    	model.addAttribute("ctype", ctype);
	    }*/
	    model.addAttribute("commodityList", commodityList);
	    
		return "commodity/commodityList";
	}
	
	@RequestMapping("/changePrice/{id}")
	@ResponseBody
	public CommodityPrice changePrice(@PathVariable("id")Long id, HttpServletRequest request){
		CommodityPrice commodityPrice = commodityPriceService.selectByPrimaryKey(id);
		commodityPrice.setOldPriceString(commodityPrice.getOldPrice().toString());
		commodityPrice.setNewPriceString(commodityPrice.getNewPrice().toString());
		return commodityPrice;
	}
	
}
