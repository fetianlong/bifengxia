package com.chartTmSearch.quickstart;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.chatm.search.weixinpay.HttpUtil;
import com.chatm.search.weixinpay.PayCommonUtil;
import com.chatm.search.weixinpay.PayConfigUtil;
import com.chatm.search.weixinpay.XMLUtil;

public class WeiXinPay {
	public static void main(String ags[]){
		getweixinPay();
	}
	
	public static String getweixinPay(){
		try {
			
			String orderPayId = "20161023102349232";
			if(StringUtils.isEmpty(orderPayId)){
				orderPayId = "20161023102349232";
			}
	        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
	        packageParams.put("appid", PayConfigUtil.APP_ID);  
	        packageParams.put("nonce_str", PayCommonUtil.getCurrTime().substring(8, PayCommonUtil.getCurrTime().length()) + PayCommonUtil.buildRandom(4));  
	        packageParams.put("out_trade_no", orderPayId);
	        packageParams.put("mch_id", PayConfigUtil.MCH_ID);
	        
	        String sign = PayCommonUtil.createSign("UTF-8", packageParams,PayConfigUtil.API_KEY);  
	        packageParams.put("sign", sign);  
	         
	        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
	        System.out.println("请求xml：：：："+requestXML);  
	   
	        String resXml = HttpUtil.postData(PayConfigUtil.PAYSEARCH_URL, requestXML);  
	        System.out.println("返回xml：：：："+resXml);  
	        Map map = XMLUtil.doXMLParse(resXml);
	        String returncode = (String) map.get("return_code");
	        String returnmsg = (String) map.get("return_msg");
	        String trade_state = (String) map.get("trade_state");
	        System.out.println(trade_state);
	        System.out.println(returncode);
	        System.out.println(returnmsg);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
