package com.chatm.search.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
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
import com.chatm.search.util.MessageClient;
import com.chatm.search.weixinpay.GenerateQrCodeUtil;
import com.chatm.search.weixinpay.HttpUtil;
import com.chatm.search.weixinpay.PayCommonUtil;
import com.chatm.search.weixinpay.PayConfigUtil;
import com.chatm.search.weixinpay.XMLUtil;

@Controller
@RequestMapping(value = "/PayAsync")
public class PayAsyncController {

	private static Logger logger = LoggerFactory.getLogger(PayAsyncController.class);
//	PayAsyncController
	@Autowired
	private OrderService orderService;
	
	/**
	 * 支付宝notify_url返回接口
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/alipayDirect/async", method = RequestMethod.POST)
	public String async(HttpServletRequest request) throws IOException {
		logger.debug("异步调用开始。。。。。。。。。。。");
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		logger.info("Asyn zhifubao : " + params);
		// String notifyId = request.getParameter("notify_id");
		if (AlipayNotify.verify(params)) {// 验证成功
			if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
				logger.debug("验证成功，开始获取用户订单信息，订单号：" + out_trade_no);
				logger.debug("验证成功，开始获取用户订单信息，支付宝交易号：" + trade_no);
				Order or = orderService.selectByOrderByPayorderId(out_trade_no);
				if(null != or && or.getStatus()!=2){
					or.setStatus(2);
					or.setPayDate(new Date());
					or.setTicketsFlag(0);
					or.setTrade_no(trade_no);
					or.setPayType(1);
	                orderService.updateByPrimaryKeySelective(or);
	                logger.info("支付宝异步>>>>>支付成功" + or.getUserPhone());
				}
			}
			return "success";
		} else {// 验证失败
			logger.info("支付宝异步验证失败,错误信息：" + trade_status);
			return "fail";
		}
	}

	/**
	 * 微信支付完成后回调
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/weixinNotify")
	public String weixinNotify(HttpServletRequest request,HttpServletResponse response) {  
		logger.info("微信支付异步返回调用地址。。。。");
        try {
	        //读取参数  
	        InputStream inputStream ;  
	        StringBuffer sb = new StringBuffer();  
	        inputStream = request.getInputStream();  
	        String s ;  
	        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	        while ((s = in.readLine()) != null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        inputStream.close();  
	  
	        //解析xml成map  
	        Map<String, String> m = new HashMap<String, String>();  
	        m = XMLUtil.doXMLParse(sb.toString());  
	          
	        //过滤空 设置 TreeMap  
	        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
	        Iterator it = m.keySet().iterator();  
	        while (it.hasNext()) {  
	            String parameter = (String) it.next();  
	            String parameterValue = m.get(parameter);  
	              
	            String v = "";  
	            if(null != parameterValue) {  
	                v = parameterValue.trim();  
	            }  
	            packageParams.put(parameter, v);  
	        }  
	          
	        // 账号信息  
	        String key = PayConfigUtil.API_KEY; // key  
	  
	        //判断签名是否正确  
	        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,key)) {  
	            //------------------------------  
	            //处理业务开始  
	            //------------------------------  
	            String resXml = "";  
	            if("SUCCESS".equals((String)packageParams.get("result_code"))){  
	                // 这里是支付成功  
	            	logger.info("微信异步输出：支付成功。。。。。。。。。。。。。。。。。");
	                //////////执行自己的业务逻辑////////////////  
	                String mch_id = (String)packageParams.get("mch_id");  
	                String openid = (String)packageParams.get("openid");  
	                String is_subscribe = (String)packageParams.get("is_subscribe");  
	                String out_trade_no = (String)packageParams.get("out_trade_no");  
	                String total_fee = (String)packageParams.get("total_fee");  
	                
	                //////////执行自己的业务逻辑////////////////  
	                //更新订单状态
	               /* Order or = orderService.selectByOrderByPayorderId(out_trade_no);
	                logger.info("微信异步输出：当前订单状态：" + or.getStatus());
		        	if(or.getStatus()!=2){
		        		or.setStatus(2);
		        		or.setPayDate(new Date());
		        		or.setTicketsFlag(0);
		        		or.setPayType(0);
		                orderService.updateByPrimaryKeySelective(or);
		                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
		        	}*/
		        	resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
		        			+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
	                  
	            } else {  
	                logger.info("微信支付失败,错误信息：" + packageParams.get("err_code"));  
	                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
	                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
	            }  
	            //------------------------------  
	            //处理业务完毕  
	            //------------------------------  
//	            System.out.println("业务逻辑处理完成后，返回微信信息：" + resXml);
	            BufferedOutputStream out = new BufferedOutputStream(  
	                    response.getOutputStream());  
	            out.write(resXml.getBytes());  
	            out.flush();  
	            out.close();
	            logger.info("微信异步通知：发送完成，关闭");
	        } else{  
	            logger.info("通知签名验证失败");  
	        }  
        } catch (Exception e) {
        	logger.error("微信回调错误" + e.getLocalizedMessage());
			e.printStackTrace();
		}
        
        return "redirect:/order/list";
    }
	
	/**
	 * 微信 根据商户订单号查询订单状态
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
	        System.out.println("当前订单状态："  + returncode + "    " + returnmsg + "   " + tradeState);
	        if(!StringUtils.isEmpty(tradeState) && tradeState.equals("SUCCESS")){
	        	Order order = orderService.selectByOrderByPayorderId(orderPayId);
	        	if(order.getStatus()!=2){
//		        	Order o = new Order();
//		        	o.setId(order.getId());
	        		order.setStatus(2);
	        		order.setPayDate(new Date());
	        		order.setTicketsFlag(0);
	        		
		        	orderService.updateByPrimaryKeySelective(order);
		        	
//		        	sendMsg(o,orderPayId);
	        	}
//	        	return "redirect:/order/list";
	        	return "1";
	        }else{
	        	return "0";
	        }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * 付款成功后发送短信通知
	 * @param or
	 */
	private void sendMsg(Order or,String orderPayId) {
		String sn="SDK-BBX-010-25168";
		String pwd="9-ca8f-[";
		MessageClient client;
		try {
			client = new MessageClient(sn,pwd);
			String comTypeName = null;
			
			or = orderService.selectByOrderByPayorderId(orderPayId);
			
			if(or.getCtype().equals("0")){	//0:门票，1：餐饮，2：住宿',
				comTypeName = "门票";
			}else if(or.getCtype().equals("1")){
				comTypeName = "餐饮";
			}else if(or.getCtype().equals("2")){
				comTypeName = "住宿";
			}
			SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
			String dat = smd.format(or.getPlayTime());
			
			String cont = "尊敬的用户您好，您预订的"+dat+or.getCommodityName()+ comTypeName + "订单号："+or.getPayOrderId()+",请您于有效期内，持有效证件到景区窗口换取入园门票，如有疑问请拔打客服电话028-66999653";
			String content = URLEncoder.encode(cont+"【碧峰峡】", "utf-8");
			client.mdsmssend(or.getUserPhone(), content, "", "", "", "");
			System.out.println("发送短信成功====" + or.getUserPhone());
			logger.info("发送短信成功====" + or.getUserPhone());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


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
