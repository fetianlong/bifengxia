package com.chatm.search.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.alipay.config.AlipayConfig;
import com.chatm.search.alipay.util.AlipayNotify;
import com.chatm.search.alipay.util.AlipaySubmit;
import com.chatm.search.model.Order;
import com.chatm.search.service.OrderService;
import com.chatm.search.shiro.ShiroDbRealm.ShiroUser;
import com.chatm.search.util.UserUtils;
import com.chatm.search.weixinpay.GenerateQrCodeUtil;
import com.chatm.search.weixinpay.HttpUtil;
import com.chatm.search.weixinpay.PayCommonUtil;
import com.chatm.search.weixinpay.PayConfigUtil;
import com.chatm.search.weixinpay.XMLUtil;

@Controller
@RequestMapping(value = "/pay")
public class PayController {

	private static Logger logger = LoggerFactory.getLogger(PayController.class);
//	PayAsyncController
	@Autowired
	private OrderService orderService;
	
	/**
	 * 进入支付->跳转
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/checkPay", method=RequestMethod.GET)
	public String playType(HttpServletRequest request) throws IOException{
		String playType = request.getParameter("payType");
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(playType) && !StringUtils.isBlank(id)){
			Order order = new Order();
			order.setPayType(Integer.parseInt(playType));
			order.setId(Long.parseLong(id));
			orderService.updateByPrimaryKeySelective(order);
			if(playType.equals("1")){	//支付宝支付
				return "forward:/pay/alipayDirect/alipay";
			}else{	//微信支付
				String data = getweixinPay(request);	//获取微信支付状态
				//如果已经支付，跳转到支付成功页面，并更新订单状态
				System.out.println(data);

				//如果未支付，跳转到支付页面（获取二维码流展示在页面上）

				return "forward:/pay/weixinPay";
			}
			//用来测试订单成功后发送短信和推送票务，不用支付了 
			/*Order or = orderService.selectByPrimaryKey(new Long(id));	//用来测试订单成功后发送短信和推送票务，不用支付了
        	if(or.getStatus()!=2){
        		or.setStatus(2);
        		or.setPayDate(new Date());
        		or.setTicketsFlag(0);
        		or.setPayType(Integer.parseInt(playType));
        		orderService.updateByPrimaryKeySelective(or);
        	}*/
		}
		return "";
	}

	/**
	 * 手机WAP支付
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/wapAlipay/alipay", method = RequestMethod.POST)
	public String WapAlipay(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String orderId = request.getParameter("id");
		Order order = new Order();
		if(!StringUtils.isEmpty(orderId)){
			order = orderService.selectByPrimaryKey(Long.parseLong(orderId));
		}else{
			System.out.println("当前订单Id未获取到。。。。。。。。。。。。。。。。             " + orderId);
			logger.info("当前订单Id未获取到。。。。。。。。。。。。。。。。             " + orderId);
		}
		
		if(null != order && order.getId()>0){
			String rmak = "碧峰峡网上订票";	// 订单描述
			// 商户网站订单系统中唯一订单号，必填
			String out_trade_no = order.getPayOrderId();
			
			// 订单名称 必填
			String subject = order.getCommodityName();
			Map<String, String> sParaTemp = new HashMap<String, String>();
			
			sParaTemp.put("service", AlipayConfig.wapService);
	        sParaTemp.put("partner", AlipayConfig.partner);
	        sParaTemp.put("seller_id", AlipayConfig.seller_id);
	        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", AlipayConfig.payment_type);
			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			sParaTemp.put("return_url", AlipayConfig.return_url);
			sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("total_fee", order.getPrice().toString());
			sParaTemp.put("subject", subject);
			sParaTemp.put("body", rmak);
//			sParaTemp.put("subject", new String(subject.getBytes("ISO-8859-1"), "utf-8"));
//			sParaTemp.put("body", new String(rmak.getBytes("ISO-8859-1"), "utf-8"));
			
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
			
			model.addAttribute("alPlayHtml", sHtmlText);
			return "/pay/aliPay";
		}
		return "";
	}
	
	/**.
	 * 支付宝扫码支付
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/alipayDirect/alipay", method = RequestMethod.GET)
	public String deposit(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		String orderId = request.getParameter("id");
		Order order = new Order();
		if(!StringUtils.isEmpty(orderId)){
			order = orderService.selectByPrimaryKey(Long.parseLong(orderId));
		}else{
			logger.info("当前订单Id未获取到。。。。。。。。。。。。。。。             " + orderId);
		}
		
		if(null != order && order.getId()>0){
			String rmak = "碧峰峡网上订票";	// 订单描述
			// 商户网站订单系统中唯一订单号，必填
			String out_trade_no = order.getPayOrderId();
			
			// 订单名称 必填
			String subject = order.getCommodityName();
			Map<String, String> sParaTemp = new HashMap<String, String>();
			
			sParaTemp.put("service", AlipayConfig.directService);
			sParaTemp.put("partner", AlipayConfig.partner);
			sParaTemp.put("seller_id", AlipayConfig.seller_id);
			sParaTemp.put("_input_charset", AlipayConfig.input_charset);
			sParaTemp.put("payment_type", AlipayConfig.payment_type);
//			sParaTemp.put("notify_url", AlipayConfig.notify_url);
			sParaTemp.put("return_url", AlipayConfig.return_url);
			sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
			sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
			sParaTemp.put("out_trade_no", out_trade_no);
			sParaTemp.put("total_fee", order.getPrice().toString());	//总金额
			sParaTemp.put("subject", subject);
			sParaTemp.put("body", rmak);
//			sParaTemp.put("subject", new String(subject.getBytes("ISO-8859-1"), "utf-8"));
//			sParaTemp.put("body", new String(rmak.getBytes("ISO-8859-1"), "utf-8"));
//			sParaTemp.put("show_url", show_url);
			
			logger.info("支付宝支付请求参数：" + sParaTemp);
			String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "post", "确认");
			model.addAttribute("alPlayHtml", sHtmlText);
			return "/pay/aliPay";
		}
		return "";
	}

	/**
	 * 支付宝return_url同步返回页面
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/alipayDirect/return_url")
	public String Return_url(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String,String> params = new HashMap<String,String>();  
		    Map requestParams = request.getParameterMap();
		    for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {  
		        String name = (String) iter.next();  
		        
		        String[] values = (String[]) requestParams.get(name);  
		        String valueStr = "";  
		        for (int i = 0; i < values.length; i++) {  
		            valueStr = (i == values.length - 1) ? valueStr + values[i]  
		                    : valueStr + values[i] + ",";  
		        }  
		        //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化  
//		        valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");  
//		        logger.info("return_url返回的名字和值" + name + "------" + valueStr);
		        params.put(name, valueStr);  
		    }  
		    //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//  
		    String trade_no = request.getParameter("trade_no");             //支付宝交易号  
		    String order_no = request.getParameter("out_trade_no");         //获取订单号  
		    /*
		    String total_fee = request.getParameter("total_fee");           //获取总金额  
		    String subject = new String(request.getParameter("subject").getBytes("ISO-8859-1"),"utf-8");//商品名称、订单名称  
		    String body = "";  
		    if(request.getParameter("body") != null){  
		        body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "utf-8");//商品描述、订单备注、描述 
		        String body1 = new String(request.getParameter("body").getBytes("ISO-8859-1"), "utf-8");//商品描述、订单备注、描述
		    }  
		    String buyer_email = request.getParameter("buyer_email");       //买家支付宝账号 
		    */
		    String trade_status = request.getParameter("trade_status");     //交易状态  
		    //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//  
		      
		    //计算得出通知验证结果  
		    boolean verify_result = AlipayNotify.verify(params);  
		    if(verify_result){//验证成功  
		        if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
		        	logger.info("同步 验证成功，开始获取用户订单信息，订单号：" + order_no + "   支付宝交易号：" + trade_no);
		                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序  
		                //如果有做过处理，不执行商户的业务程序  
		        	Order or = orderService.selectByOrderByPayorderId(order_no);
		        	if(or.getStatus() != 2){
//		        		Order order = new Order();
//		        		order.setPayOrderId(order_no);
		        		or.setStatus(2);
		        		or.setPayDate(new Date());
		        		or.setTicketsFlag(0);
		        		or.setTrade_no(trade_no);
		        		or.setPayType(1);
		        		orderService.updateByPrimaryKeySelective(or);
		        		logger.info("支付宝支付成功，更新订单信息成功：" + or.getUserPhone());
		        	}
		        	return "redirect:/order/paySuccess/"+order_no;
		        }  
		    }else{  
		        //该页面可做页面美工编辑  
		    	logger.error("支付宝验证失败trade_status：---" + trade_status);
//		        System.out.println("支付宝验证失败1111");  
		    }
		    return "";
		} catch (Exception e) {
			logger.error("支付宝验证失败2222" + e.getLocalizedMessage());
			return "";
		}
	}
	
	/**
	 * 接入微信接口，获取微信支付二维码。
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/weixinPay", method = RequestMethod.GET)
	public String weixinPay(Model model, HttpServletRequest request) throws Exception {
		
		String orderId = request.getParameter("id");
		Order order = new Order();
		if(!StringUtils.isEmpty(orderId)){
			order = orderService.selectByPrimaryKey(Long.parseLong(orderId));
			if(null != order && order.getId()>0){
			    String codeUrl = weixin_payNATIVE(order);
			    logger.info("微信支付codeURL：" + codeUrl);
			    if(!StringUtils.isEmpty(codeUrl)){
			    	
			    }else{
			    	
			    }
			    model.addAttribute("urlCode", codeUrl);
			    model.addAttribute("payOrderId", order.getPayOrderId());
			}
		}else{
			System.out.println("当前订单Id未获取到。。。。。。。。。。。。。。。。             " + orderId);
			logger.info("当前订单Id未获取到。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。             " + orderId);
			return "";
		}
		
	    return "/pay/weixinPay";
	}
	
	/** 
	 * 生成二维码图片并直接以流的形式输出到页面 
	 * @param response
	 */ 
	@RequestMapping("/qr_codeImg")  
	@ResponseBody  
	public void getQRCode(HttpServletRequest request, HttpServletResponse response) {
		// code_url就是这个链接weixin://wxpay/bizpayurl?pr=N4dkmCO
		String content = request.getParameter("code_url");
        GenerateQrCodeUtil.encodeQrcode(content, response);
	}
	
	/**
	 * 统一下单接口生成二维码code_url，微信扫码支付
	 * @param order
	 * @return code_url
	 * @throws Exception
	 */
	public String weixin_payNATIVE(Order order) throws Exception {  
        // 账号信息  
        String appid = PayConfigUtil.APP_ID;  // appid  
        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
        // 商业号  
        String mch_id = PayConfigUtil.MCH_ID;  
        // key  
        String key = PayConfigUtil.API_KEY;   
        String currTime = PayCommonUtil.getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = PayCommonUtil.buildRandom(4) + "";  
        //随机字符串  
        String nonce_str = strTime + strRandom;  
        // 价格   注意：价格的单位是分  
  
        //查询订单数据表获取订单信息  
//        PayOrder payOrder = payOrderDao.get(PayOrder.class,out_trade_no);  
        // 获取发起电脑 ip  
        String spbill_create_ip = getLocalIp();  
        // 回调接口   
        String notify_url = PayConfigUtil.NOTIFY_URL;  
        String trade_type = "NATIVE";  
        String time_start =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
        Calendar ca = Calendar.getInstance();  
        ca.setTime(new Date());  
        ca.add(Calendar.DATE, 1);           
        String time_expire =  new SimpleDateFormat("yyyyMMddHHmmss").format(ca.getTime());  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", mch_id);  
        packageParams.put("nonce_str", nonce_str);  
        packageParams.put("body",order.getCommodityName());  
        packageParams.put("out_trade_no", order.getPayOrderId());
        
        Integer p = (int) (order.getPrice().doubleValue() * 100);	//订单价格转换
		String price = p.toString();
        packageParams.put("total_fee", price);  
//        packageParams.put("total_fee", "");  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  
        packageParams.put("time_start", time_start);  
        packageParams.put("time_expire", time_expire);          
        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  
         
        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
//        System.out.println("请求xml：：：："+requestXML);  
   
        String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);  
//        System.out.println("返回xml：：：："+resXml);  
          
        Map map = XMLUtil.doXMLParse(resXml);  
        //String return_code = (String) map.get("return_code");  
        //String prepay_id = (String) map.get("prepay_id");  
        String urlCode = (String) map.get("code_url");   
//        System.out.println("打印调用统一下单接口生成二维码url:::::"+urlCode);  
        return urlCode;  
	}
	
	/**
	 * 微信公众号支付
	 * @return	prepay_id
	 * @throws Exception  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2016年12月10日 下午2:45:21
	 */
	@RequestMapping("/weixin_payJSAPI")
	@ResponseBody
	public Map weixin_payJSAPI(HttpServletRequest request) throws Exception {

		//先授权登录，然后获取openId

		Map m = new HashMap();
		ShiroUser user = UserUtils.getRegistUser();	//当前登录用户信息
		
		if(user.registType==2){	//微信注册用户
			String openid = user.openId;	//微信用户登陆后的openid
			System.out.println("用户openid: " + openid);
			String id = request.getParameter("id");
			Order order = orderService.selectByPrimaryKey(Long.parseLong(id));
			// 账号信息  
			String appid = PayConfigUtil.APPID;  // appid  
			
			String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
			String mch_id = PayConfigUtil.MCH_ID;  // 商业号  
			String key = PayConfigUtil.API_KEY;   // key 
			
			String currTime = PayCommonUtil.getCurrTime();  
			String strTime = currTime.substring(8, currTime.length());  
			String strRandom = PayCommonUtil.buildRandom(4) + "";  
			String nonce_str = strTime + strRandom;  //随机字符串  
			String spbill_create_ip = getLocalIp();  // 获取发起电脑 ip  
	
			// 回调接口   
			String notify_url = PayConfigUtil.NOTIFY_URL;  
			String trade_type = "JSAPI";  
			String time_start =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());  
			Calendar ca = Calendar.getInstance();  
			ca.setTime(new Date());  
			ca.add(Calendar.DATE, 1);           
			String time_expire =  new SimpleDateFormat("yyyyMMddHHmmss").format(ca.getTime());  
			SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
			packageParams.put("appid", appid);  
			packageParams.put("mch_id", mch_id);  
			packageParams.put("openid", openid);  
			packageParams.put("nonce_str", nonce_str);  
			packageParams.put("body",order.getCommodityName());  
			packageParams.put("out_trade_no", order.getPayOrderId());
			
			Integer p = (int) (order.getPrice().doubleValue() * 100);	//订单价格转换
			String price = p.toString();
			packageParams.put("total_fee", price);  
	//        packageParams.put("total_fee", "");  
			packageParams.put("spbill_create_ip", spbill_create_ip);  
			packageParams.put("notify_url", notify_url);  
			packageParams.put("trade_type", trade_type);  
			packageParams.put("time_start", time_start);  
			packageParams.put("time_expire", time_expire);
			
			String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
			packageParams.put("sign", sign);  
			
			System.out.println("微信支付JSAPI生成的签名sign：" + sign);
			logger.info("微信支付JSAPI生成的签名sign：" + sign);
			String requestXML = PayCommonUtil.getRequestXml(packageParams);
			
			logger.info("微信支付JSAPI请求参数：" + requestXML);
//	        System.out.println("微信支付JSAPI请求参数："+requestXML);  
			
			String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);  
//	        System.out.println("返回xml：：：："+resXml);  
//	        logger.info("weixin_payJSAPI 返回xml：：：："+resXml);
			
			Map map = XMLUtil.doXMLParse(resXml);  
			String return_code = (String) map.get("return_code");
			String return_msg = (String) map.get("return_msg");
			String prepay_id = (String) map.get("prepay_id");
			
	//		String urlCode = (String) map.get("code_url");   
	//        System.out.println("打印调用统一下单接口生成二维码url:::::"+urlCode);  
			packageParams.put("prepay_id", prepay_id);
			packageParams.put("packages", prepay_id);
			logger.info("return_code： " + return_code + "  \n return_msg：" + return_msg + "  \n prepay_id："+prepay_id);
			
			m.putAll(packageParams);
		}
		
		return m;  
	}  

	/**
	 * 微信根据商户订单号查询订单状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryOrder", method = RequestMethod.POST)
	@ResponseBody
	public String getweixinPay(HttpServletRequest request){
		try {
			
			String orderPayId = request.getParameter("orderPayId");
			
	        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
	        packageParams.put("appid", PayConfigUtil.APP_ID);  
	        packageParams.put("nonce_str", PayCommonUtil.getCurrTime().substring(8, PayCommonUtil.getCurrTime().length()) + PayCommonUtil.buildRandom(4));  
	        packageParams.put("out_trade_no", orderPayId);
	        packageParams.put("mch_id", PayConfigUtil.MCH_ID);
	        
	        String sign = PayCommonUtil.createSign("UTF-8", packageParams,PayConfigUtil.API_KEY);  
	        packageParams.put("sign", sign);  
	         
	        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
//	        System.out.println("请求xml：：：："+requestXML);  
	   
	        String resXml = HttpUtil.postData(PayConfigUtil.PAYSEARCH_URL, requestXML);  
//	        System.out.println("返回xml：：：："+resXml);  
	        Map map = XMLUtil.doXMLParse(resXml);
	        String returncode = (String) map.get("return_code");
	        String returnmsg = (String) map.get("return_msg");
	        String tradeState = (String) map.get("trade_state");	//SUCCESS—支付成功  REFUND—转入退款 NOTPAY—未支付    CLOSED—已关闭  REVOKED—已撤销（刷卡支付） USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
	        if(!StringUtils.isEmpty(tradeState) && tradeState.equals("SUCCESS")) {
	        	logger.info("微信支付成功!");
	        	Order order = orderService.selectByOrderByPayorderId(orderPayId);
	        	if(order.getStatus() != 2){
	        		order.setStatus(2);
	        		order.setPayDate(new Date());
	        		order.setTicketsFlag(0);
	        		order.setPayType(0);
		        	orderService.updateByPrimaryKeySelective(order);
	        	}
	        	return "1";
	        }else{
	        	return "0";
	        }
		} catch (Exception e) {
			logger.error("微信支付失败：" + e.getLocalizedMessage());
		}
		return "1";
	}
	
	/**
	 * 微信退款
	 * @param trade
	 * @return
	 */
	/*public String Refund(TRADE_Model trade) {
        string nonceStr = Senparc.Weixin.MP.TenPayLibV3.TenPayV3Util.GetNoncestr();

        Senparc.Weixin.MP.TenPayLibV3.RequestHandler packageReqHandler = new Senparc.Weixin.MP.TenPayLibV3.RequestHandler(null);

        //设置package订单参数
        packageReqHandler.SetParameter("appid", AppId);          //公众账号ID
        packageReqHandler.SetParameter("mch_id", MchId);          //商户号
        packageReqHandler.SetParameter("out_trade_no", trade.TRADE_NO);                 //填入商家订单号
        packageReqHandler.SetParameter("out_refund_no", "");                //填入退款订单号
        packageReqHandler.SetParameter("total_fee", "");               //填入总金额
        packageReqHandler.SetParameter("refund_fee", "");               //填入退款金额
        packageReqHandler.SetParameter("op_user_id", MchId);   //操作员Id，默认就是商户号
        packageReqHandler.SetParameter("nonce_str", nonceStr);              //随机字符串

        string sign = packageReqHandler.CreateMd5Sign("key", Key);

        packageReqHandler.SetParameter("sign", sign);                        //签名

        //退款需要post的数据
        string data = packageReqHandler.ParseXML();

        //退款接口地址
        string url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

        //本地或者服务器的证书位置（证书在微信支付申请成功发来的通知邮件中）
        string cert = "D:/apiclient_cert.p12";

        //私钥（在安装证书时设置）
        string password = "";

        ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(CheckValidationResult);
        //调用证书
        X509Certificate2 cer = new X509Certificate2(cert, password, X509KeyStorageFlags.PersistKeySet | X509KeyStorageFlags.MachineKeySet);

        #region 发起post请求
        HttpWebRequest webrequest = (HttpWebRequest)HttpWebRequest.Create(url);
        webrequest.ClientCertificates.Add(cer);
        webrequest.Method = "post";

        byte[] postdatabyte = Encoding.UTF8.GetBytes(data);
        webrequest.ContentLength = postdatabyte.Length;
        Stream stream;
        stream = webrequest.GetRequestStream();
        stream.Write(postdatabyte, 0, postdatabyte.Length);
        stream.Close();

        HttpWebResponse httpWebResponse = (HttpWebResponse)webrequest.GetResponse();
        StreamReader streamReader = new StreamReader(httpWebResponse.GetResponseStream());
        string responseContent = streamReader.ReadToEnd();
        #endregion

        var res = System.Xml.Linq.XDocument.Parse(responseContent);
        string return_code = res.Element("xml").Element("return_code").Value;

        Hashtable hashtable = new Hashtable();

        hashtable.Add("return_code", return_code);

        return Json(hashtable);
    }
    
    private static bool CheckValidationResult(object sender, X509Certificate certificate, X509Chain chain, SslPolicyErrors errors)
        {
            if (errors == SslPolicyErrors.None)
                 return true;
            return false;
        }
	*/
	/**
	* 获取本机Ip 
	* 
	* 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。
	* 获得符合 <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
	* @return
	*/
	@SuppressWarnings("rawtypes")
	private String getLocalIp() {
		String ip = null;
		Enumeration allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
						.nextElement();
				List<InterfaceAddress> InterfaceAddress = netInterface
						.getInterfaceAddresses();
				for (InterfaceAddress add : InterfaceAddress) {
					InetAddress Ip = add.getAddress();
					if (Ip != null && Ip instanceof Inet4Address) {
						ip = Ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			logger.warn("获取本机Ip失败:异常信息:" + e.getMessage());
		}
		return ip;
	}
	
}
