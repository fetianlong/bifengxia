package com.chatm.search.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.alipay.util.UtilDate;
import com.chatm.search.model.Commodity;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.Order;
import com.chatm.search.service.CommodityPriceService;
import com.chatm.search.service.CommodityService;
import com.chatm.search.service.OrderService;
import com.chatm.search.service.RegistUserService;
import com.chatm.search.shiro.ShiroDbRealm.ShiroUser;
import com.chatm.search.util.CheckMobile;
import com.chatm.search.util.UserUtils;

@Controller
@RequestMapping("/order")
public class OrderController  extends SupperController{
	protected static final Log log = LogFactory.getLog(OrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	CommodityService commodityService;
	@Autowired
	CommodityPriceService commodityPriceService;
	@Autowired
	RegistUserService registUserService;
	
	
	@RequestMapping("/list")
	public String orderList(Model model){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) subject.getPrincipal();
		List<Order> orderList = orderService.getOrderByUser(user.id);
		
		model.addAttribute("orderList", orderList);
		return "userCenter/orderList";
	}
	
	/**
	 * 取消订单，未支付的订单才可以取消
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateStatus/{id}")
	@ResponseBody
	public String updateStatus(@PathVariable("id")Long id,Model model){
		String flag = "0";
		try {
			Order o = orderService.selectByPrimaryKey(id);
			if(null != o && o.getStatus()==1){
				Order order = new Order();
				order.setId(id);
				order.setStatus(4);
				orderService.updateByPrimaryKeySelective(order);
				flag = "1";
			}
		} catch (Exception e) {
			
		}
		return flag;
	}
	
	/**
	 * 申请退款，已支付但是未取票的
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/refundFastpay/{id}")
	@ResponseBody
	public String refundFastpay(@PathVariable("id")Long id,Model model){
		String flag = "0";
		try {
			Order o = orderService.selectByPrimaryKey(id);
			if(null != o && o.getStatus() == 2){
				Order order = new Order();
				order.setId(id);
				order.setStatus(7);
				orderService.updateByPrimaryKeySelective(order);
				flag = "1";
			}
		} catch (Exception e) {
			
		}
		return flag;
	}
	
	/**
	 * 确认订单
	 * @param request
	 * @param model
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年5月16日 上午11:11:44
	 */
	@RequestMapping("/newOrder")
	public String newOrder(HttpServletRequest request, Model model){
		try {
			String commodityId = request.getParameter("commodityId");	//商品ID
			String commodityPriceId = request.getParameter("commodityPriceId");	//商品票种ID
			String ctype = request.getParameter("ctype");	//商品类型，0：门票，1：餐饮，2：酒店，3：商品预定
			model.addAttribute("ctype", ctype);
			if(!StringUtils.isEmpty(commodityId) && !StringUtils.isEmpty(commodityPriceId)){
				model.addAttribute("commodityId", commodityId);
				model.addAttribute("commodityPriceId", commodityPriceId);
				
				Commodity commodity = commodityService.selectByPrimaryKey(Long.parseLong(commodityId));
				String comName = commodity.getName();
				model.addAttribute("comName", comName);
				CommodityPrice commodityPrice = commodityPriceService.selectByPrimaryKey(Long.parseLong(commodityPriceId)); //根据票种ID获取票种价格
				BigDecimal price = commodityPrice.getNewPrice();
				model.addAttribute("price", price);	//单价

				String priceCount = request.getParameter("priceCount");
				model.addAttribute("priceCount", priceCount);	//数量
				
				int s = Integer.valueOf(priceCount);
				BigDecimal ss = BigDecimal.valueOf(s);
				BigDecimal allPrice = ss.multiply(price);
				model.addAttribute("allPrice", allPrice);	//总价
				
				String toDate = request.getParameter("toDate");
				model.addAttribute("tdate", toDate);
				
				this.getDictionary("sptype", ctype.toString(),model);
			}else{
				return "redirect:/index";
			}
			
		} catch (Exception e) {
			log.error("确认订单错误：" + e.getLocalizedMessage());
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return "userCenter/newOrder";
	}
	
	/**
	 * 新加入商品产生的订单
	 * @param request
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public String addOrder(HttpServletRequest request, Model model) throws ParseException{
		Order order = new Order();
		String payUserName = request.getParameter("payUserName");
		String userPhone = request.getParameter("userPhone");
		String payCardNo = request.getParameter("payCardNo");
		String ticketsName = request.getParameter("ticketsName");
		String ticketsPhone = request.getParameter("ticketsPhone");
		String playTime = request.getParameter("playTime");
		String sPrice = request.getParameter("sPrice");
		String pCount = request.getParameter("pCount");
		String payCommodityId = request.getParameter("payCommodityId");
		String comPid = request.getParameter("comPid");
		String commodityName = request.getParameter("commodityName");
		
		order.setPayUserName(payUserName);
		order.setUserPhone(userPhone);
		order.setPayCardNo(payCardNo);
		order.setTicketsName(ticketsName);
		order.setTicketsPhone(ticketsPhone);
		
//		Integer sp = Integer.valueOf(sPrice);
		BigDecimal ssp = new BigDecimal(sPrice);
//		BigDecimal sbPrice = ssp.multiply(order.getsPrice());
		order.setsPrice(ssp);
		
		order.setpCount(Long.parseLong(pCount));
		order.setPayCommodityId(Long.parseLong(payCommodityId));
		order.setComPid(Long.parseLong(comPid));
		order.setCommodityName(commodityName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(playTime);
		String sd = sdf.format(date);
		order.setPlayTime(sdf.parse(playTime));
		
		String isflag = request.getParameter("isflag");	//订单状态，防止刷新重新下单
		if(StringUtils.isEmpty(isflag)){
			isflag = "0";
		}
		order.setPayUser(getRegistUser().id);
		order.setStatus(1);
		order.setPayOrderId(UtilDate.getOrderNum()+UtilDate.getFive());	//订单ID
		order.setTicketsFlag(0);
		order.setMessage_flag(0);
		order.setSend_ticket_flag(0);
		
		int s = Integer.valueOf(order.getpCount().toString());
		BigDecimal ss = BigDecimal.valueOf(s);
		BigDecimal allPrice = ss.multiply(order.getsPrice());
		order.setPrice(allPrice);
		int id = orderService.insertSelective(order);
		model.addAttribute("orderId", order.getId());
		
		//判断是手机访问还是PC端访问
		String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
		if(CheckMobile.check(userAgent)){
			ShiroUser user = UserUtils.getRegistUser();
			model.addAttribute("registType", user.registType);
		}
		
		return "redirect:/order/payment";
//		return "userCenter/payment";
	}
	
	/**
	 * 跳转到选择支付页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/payment")
	public String payment(Model model, HttpServletRequest request){
		String registType = request.getParameter("registType");
		String orderId = request.getParameter("orderId");
		
		if(!StringUtils.isEmpty(orderId)) {
			model.addAttribute("orderId", orderId);
			model.addAttribute("registType", registType);
			return "userCenter/payment";
		} else {
			return "";
		}
	}
	
	@RequestMapping("/updateOrder")
	public String updateOrder(@ModelAttribute("orderForm") Order order, Model model, HttpServletRequest request) throws ParseException, IOException{
		BigDecimal allPrice = BigDecimal.valueOf(order.getpCount()).multiply(order.getsPrice());
		order.setPrice(allPrice);
		
		String pt = request.getParameter("toDate");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = sdf.parse(pt);
		order.setPlayTime(date);
		
		orderService.updateByPrimaryKeySelective(order);
		model.addAttribute("orderId", order.getId());
		
		//判断是手机访问还是PC端访问
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		if(CheckMobile.check(userAgent)){
			ShiroUser user = UserUtils.getRegistUser();
			model.addAttribute("registType", user.registType);
		}
		
		return "userCenter/payment";
	}
	
	private ShiroUser getRegistUser(){
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) currentUser.getPrincipal();
		return user;
	}
	
	/**
	 * 删除订单
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteOrder/{id}")
	public String deleteOrder(@PathVariable("id") Long id){
		orderService.deleteByPrimaryKey(id);
		return "redirect:/order/addOrder";
	}
	
	/**
	 * 选择支付方式
	 * @param orderId
	 * @param model
	 * @return
	 */
/*	@RequestMapping("/payment/{orderId}")
	public String payment(@PathVariable("orderId") Long orderId, Model model){
		model.addAttribute("orderId", orderId);
		return "commodity/payment";
	}*/
	
	/**
	 * 支付宝支付成功，自动跳转
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping(value="/paySuccess/{payOrderId}")
	public String paySuccuss(@PathVariable("payOrderId") String payOrderId, Model model) throws InterruptedException{
		Order order = orderService.selectByOrderByPayorderId(payOrderId);
		Commodity comm = commodityService.selectByPrimaryKey(order.getPayCommodityId());
		String ctype = comm.getCommodityType().toString();
		order.setCtype(ctype);
		model.addAttribute("order", order);
		return "/index/zhifuchenggong";
	}
	
	@RequestMapping(value="/weiXpaySuccess/{payOrderId}")
	public String weiXpaySuccess(@PathVariable("payOrderId") String payOrderId, Model model) throws InterruptedException{
		Order order = orderService.selectByOrderByPayorderId(payOrderId);
		Commodity comm = commodityService.selectByPrimaryKey(order.getPayCommodityId());
		String ctype = comm.getCommodityType().toString();
		order.setCtype(ctype);
		order.setStatus(2);
		model.addAttribute("order", order);
		return "/index/zhifuchenggong";
	}
	
	/**
	 * 点击支付成功后跳转
	 * @param orderId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/payCheckSuccess/{orderId}")
	public String payCheckSuccess(@PathVariable("orderId") String orderId, Model model){
		
		Order order = orderService.selectByPrimaryKey(Long.parseLong(orderId));
		Commodity comm = commodityService.selectByPrimaryKey(order.getPayCommodityId());
		String ctype = comm.getCommodityType().toString();
		order.setCtype(ctype);
		model.addAttribute("order", order);
		return "/index/zhifuchenggong";
	}
}
