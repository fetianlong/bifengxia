package com.chartTmSearch.quickstart;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.chatm.search.util.HttpClientTemplate;


public class OrderTest {
	static String account = "bfx_online";
	static String password = "0172588c04d54304b2dcd05bf6e2da69";
	public static void main(String[] args) {
		String api_url = "http://61.157.226.178:8090/tdx/papi";
		
		String url = api_url + "/order.jhtml";
		long req_time = System.currentTimeMillis();
		List<POrder> orders = new ArrayList<POrder>();
		POrder order = new POrder();
		order.setTicket_id("GW20161213230961");
		order.setActive_time("2017-10-10 00:00:00");
		order.setPrice(80 + "");

		order.setSize("1");
		order.setAmount("80");
		order.setCust_name("测试数据1");
		order.setCust_no("210000198501231121");
		order.setCreate_time("2017-10-10 00:00:00");
		order.setOrder_id("" + System.currentTimeMillis());
		order.setStatus("1");
		
//		order.setVerify_code("4575735026");
		order.setVerify_code("1670455161");
		
//		order.setAgent_id("bfx");
//		order.setAgent_name("碧峰峡官网");
//		order.setThird_id("mt");
//		order.setThird_name("美团");
//		order.setExpire_time("2016-12-10 00:00:00");
		order.setOrder_no("xbz" + System.currentTimeMillis());
		order.setPay_type("3");
		orders.add(order);
		//
		String access_token = sign(req_time, account, password);
		Map params = new HashMap<>();
		params.put("account", "bfx_online");
		params.put("req_time", req_time + "");
		params.put("access_token", access_token);
//		params.put("orders", orders);
		
		String json = JSONArray.fromObject(orders).toString();
		
		params.put("orders", json.toString());
		System.out.println(params);
		
		HttpClientTemplate httpClientTemplate = new HttpClientTemplate(url);
//		String result = httpClientTemplate.doPost(params, true);
		String result = httpClientTemplate.httpPostWithJSON(params);
		System.out.println(result);
		
//		JSONObject jsonObject = JSONObject.fromObject(params);
//		System.out.println(jsonObject);
//		String json = jsonObject.toString();
//		String json1 = json.replace(":[{", "=[{");
//		HttpClientTemplate httpClientTemplate = new HttpClientTemplate(url);
//		String result = httpClientTemplate.httpPostWithJSON(params);
//		System.out.println(result);
		
//		String rspJson = AsyncHttpUtils.post(url, JacksonUtil.toJson(params));
//		System.out.println(rspJson);
		// String rspJson = HttpUtil.post(url, JacksonUtil.toJson(params), (Map<String, String>) null);
//		CommonRsp rsp = JacksonUtil.fromJson(rspJson, CommonRsp.class);
//		if (rsp != null && rsp.getData("orders") != null) {
//			orders = (ArrayList) rsp.getData("orders");
//			System.out.println(orders);
//		}
		
	}

	// 签名算法
	public static String sign(long req_time, String account, String password) {
		String str = account + MD5.MD5Encode(password) + req_time;
		String sign = MD5.MD5Encode(str);
		System.out.println("access_token:" + sign + "     req_time:" + req_time);
		return sign;
	}
	
	/**
	 * MD5编码
	 *
	 * @param origin 原始字符串
	 * @return 经过MD5加密之后的结果
	 */
	public static String MD5Encode(String origin) {
		String resultString = null;
		try {
			resultString = origin;
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes("UTF-8")));
			return resultString.toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 转换字节数组为16进制字串
	 *
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (byte aB : b) {
			resultSb.append(byteToHexString(aB));
		}
		return resultSb.toString();
	}
	

	/**
	 * 转换byte到16进制
	 *
	 * @param b 要转换的byte
	 * @return 16进制格式
	 */
	private static String byteToHexString(byte b) {
		String[] HEX_DIGITS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
				"e", "f"};
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}
}
