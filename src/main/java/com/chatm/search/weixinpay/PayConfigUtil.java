package com.chatm.search.weixinpay;


public class PayConfigUtil {
	//初始化
//	public static String APP_ID = "wx30255a20e41901b0";	//微信开发平台应用ID
//	public static String APP_SECRET = "be05e0f07af7ff42aeb486f72c636bba";	//应用对应的凭证
//	public static String API_KEY = "b4dbf4d2e80cb6542e3cf7af7d4185f7";	//API key
//	public static String MCH_ID = "1231029501";	//商户号	857754
	
	public static String APP_ID = "wx30255a20e41901b0";	//微信服务号开发者ID（开发平台应用ID）
	public static String APP_SECRET = "be05e0f07af7ff42aeb486f72c636bba";	//应用对应的凭证
	public static String API_KEY = "bifengxia2318077bifengxia2318077";	//API key
	public static String MCH_ID = "1231029501";	//商户号	857754
	
	//有关URL
	public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";	//微信的统一下单接口
	public static String PAYSEARCH_URL = "https://api.mch.weixin.qq.com/pay/orderquery";	//查询订单接口
	public static String NOTIFY_URL = "http://www.bifengxia.com/PayAsync/weixinNotify/";	//微信支付回调地址
	
	//微信公众号信息
	public static String APPID = "wx98dfa4f4df3122e7";
	public static String SECRET = "aa74652f6118197863a88b7c62b1b95b";
	public static String REDIRECTURI = "http://www.bifengxia.com/";	//微信登录回调地址
	
	//qq开发者信息
	public static String QQ_APPID = "101356885";	//appid
	public static String QQ_APPKEY = "d8b7900a4128e9d2026a2fde8066b8a0";	//appkey
//	public static String QQ_REDIRECTURI = "http://www.bifengxia.com:8080/webExample/qq";	//qq登录回调地址
	public static String QQ_REDIRECTURI = "http://www.bifengxia.com/qq";	//qq登录回调地址
	
}
