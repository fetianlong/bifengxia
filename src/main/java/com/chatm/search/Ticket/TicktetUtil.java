package com.chatm.search.Ticket;

import com.chatm.search.model.Order;
import com.chatm.search.util.Constants;
import com.chatm.search.util.HttpClientTemplate;
import com.chatm.search.weixinpay.XMLUtil;
import org.apache.commons.lang.StringUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TicktetUtil {

	private static Logger logger = LoggerFactory.getLogger(TicktetUtil.class);
	
	private static String corpCode = "TESTFX";	//企业码
	private static String userName = "yadfbfx";	//用户名
	private static String URL = "http://boss.zhiyoubao.com/boss/service/code.htm";
	private static String TESTURL = "http://ds-zff.sendinfo.com.cn/boss/service/code.htm";
	private static String SECRETKEY = "TESTFX";
	private String nowDate = "";
	private String headXml = "";
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public TicktetUtil(String methodName, String corpCode, String userName) {
		this.nowDate = sdf.format(new Date());
		if (StringUtils.isNotEmpty(corpCode)) {
			this.headXml = "<PWBRequest>"
					+ "<transactionName>" + methodName + "</transactionName>"
					+ "<header>"
					+ "<application>SendCode</application>"
					+ "<requestTime>" + nowDate + "</requestTime>"
					+ "</header>"
					+ "<identityInfo>"
					+ "<corpCode>" + corpCode + "</corpCode>"
					+ "<userName>" + userName + "</userName>"
					+ "</identityInfo>";
		} else {
			this.headXml = "<PWBRequest>"
					+ "<transactionName>SEND_CODE_REQ</transactionName>"
					+ "<header>"
					+ "<application>SendCode</application>"
					+ "<requestTime>" + nowDate + "</requestTime>"
					+ "</header>"
					+ "<identityInfo>"
					+ "<corpCode>TESTFX</corpCode>"
					+ "<userName>yadfbfx</userName>"
					+ "</identityInfo>";
		}
	}

	//◆	xmlMsg：请求接口的内容，是数据格式的规范的XML格式字符串。
	//◆	sign：MD5(“xmlMsg=”+ xmlMsg +私钥)，即对xmlMsg 和 私钥 进行字符串拼接后的字符串进行MD5加密
	public String sendTicket(Order order) {
		String result = "";
		
		String xmlo = headXml
				+ "<orderRequest>"
				+ "<order>"
//				+ "<certificateNo>" + order.getPayCardNo() + "</certificateNo>"
				+ "<linkName>" + order.getPayUserName() + "</linkName>"
				+ "<linkMobile>" + order.getUserPhone() + "</linkMobile>"
				+ "<orderCode>" + order.getPayOrderId() + "</orderCode>"
				+ "<orderPrice>" + order.getPrice() + "</orderPrice>"
				+ "<payMethod>vm</payMethod>"
				+ "<groupNo></groupNo>"
				+ "<ticketOrders>"
				;
		logger.info("进入下单方法。。。。");
		if (StringUtils.isNotEmpty(order.getTicketsNo())) {
			String[] tinos = order.getTicketsNo().split(",") ;
			for (int i = 0; i < tinos.length; i++) {
				xmlo += "<ticketOrder>"
						+ "<orderCode>"+ tinos[i] + "</orderCode>"	//必填你们的子订单编码
						+ "<price>" + order.getsPrice() + "</price>"
						+ "<quantity>1</quantity>"
//						+ "<quantity>" + order.getpCount() + "</quantity>"
						+ "<totalPrice>" + order.getPrice() + "</totalPrice>"
						+ "<occDate>" + sdf.format(order.getPlayTime()) + "</occDate>"
//						+ "<goodsCode>" + order.getTicketId() + "</goodsCode>"	// 必填商品编码，同票型编码
						+ "<goodsCode>PST20170307097873</goodsCode>"	// 必填商品编码，同票型编码
						+ "<goodsName>" + order.getCommodityName() + "</goodsName>"
						+ "</ticketOrder>"
						;
			}
		}
		String xml = xmlo
				+ "</ticketOrders>"
				+ "</order>"
				+ "</orderRequest>"
				+ "</PWBRequest>"
				;
		try {
//			xml = "<PWBRequest><transactionName>SEND_CODE_REQ</transactionName><header><application>SendCode</application><requestTime>2017-09-11 09:58:48</requestTime></header><identityInfo><corpCode>TESTFX</corpCode><userName>yadfbfx</userName></identityInfo><orderRequest><order><certificateNo>330521199301043810</certificateNo><linkName>测试票</linkName><linkMobile>18900000000</linkMobile><orderCode>625c98a1c1d645358d08730442541d6a</orderCode><orderPrice>1</orderPrice><payMethod>vm</payMethod><scenicOrders><scenicOrder><orderCode>625c98a1c1d645358d08730442541d6a</orderCode><totalPrice>10</totalPrice><price>10</price><quantity>5</quantity><occDate>2017-09-18 00:00:00</occDate><goodsCode>PST20170803015684</goodsCode><goodsName>测试票</goodsName></scenicOrder></scenicOrders></order></orderRequest></PWBRequest>";
			logger.info("拼装XML：" + xml);
			String signPase = "xmlMsg=" + xml + Constants.secretkey + "";
			String sign = MD5.MD5Encode(signPase);	//MD5(“xmlMsg=”+ xmlMsg +私钥)后的值    -----加密后的内容要是小写的
			String xmlData = "xmlMsg=" + xml + "&sign=" + sign.toLowerCase() + "";
			System.out.println("xmlData: " + xmlData);
			HttpClientTemplate httpClientTemplate = new HttpClientTemplate(Constants.zhiyouURL);
//			result = httpClientTemplate.sendPost(xmlData);  //post请求，使用java自带的URL
            result = httpClientTemplate.doGet(xmlData);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("智游宝下单接口调用失败-mdgetSninfo：" + e.getLocalizedMessage());
			return "";
		}
	}

	/**
	 * @Description：用订单号去智游宝获取二维码地址
	 * @param：[order]
	 * @author：pjh
	 * @createtime：2017/9/11-17:51
	 * @updateuser：
	 * @updatetime：
	 * @updateDescription：
	 * @return：java.lang.String
	 */
	public String getTwoCodeURL(Order order) throws JDOMException, IOException {
		String codeurl = "";
		String xml = headXml
				+ "<orderRequest>"
				+ "<order>"
				+ "<orderCode>"+ order.getPayOrderId() + "</orderCode>"
				+ "</order>"
				+ "</orderRequest>"
				+ "</PWBRequest>"
				;
		String signPase = "xmlMsg=" + xml + Constants.secretkey + "";
		String sign = MD5.MD5Encode(signPase);	//MD5(“xmlMsg=”+ xmlMsg +私钥)后的值    -----加密后的内容要是小写的
		String xmlData = "xmlMsg=" + xml + "&sign=" + sign.toLowerCase() + "";

		HttpClientTemplate httpClientTemplate = new HttpClientTemplate(Constants.zhiyouURL);
		String result = httpClientTemplate.doGet(xmlData);

		Map map = XMLUtil.doXMLParse(result);
		String rcode = (String) map.get("code");
		String rdescription = (String) map.get("description");

		if (StringUtils.isNotEmpty(rcode) && rcode.equals("0")) {
//				&& StringUtils.isNotEmpty(rdescription) && rdescription.equals("成功")
			codeurl = (String) map.get("img");
		}

		return codeurl;
	}
	
	public static void main(String[] args) throws JDOMException, IOException {

		/*String xmlMsg = "xmlMsg666666666";
		String sign = "sign66666666";
		Map map = new LinkedHashMap();
		map.put("xmlMsg",xmlMsg);
		map.put("sign",sign);
		String url = "http://localhost:8081/bifengxia/api/order/test";
		HttpClientTemplate httpClientTemplate = new HttpClientTemplate(url);
		String result = httpClientTemplate.doPostObject(map,true);
		System.out.println(result);*/
		Order order = new Order();
		order.setCommodityName("商品名称测试测试嗷嗷嗷");
		order.setPayCardNo("123312111111111111");
		order.setPayUserName("张三");
		order.setUserPhone("18811355087");
		order.setpCount(new Long(1));
		BigDecimal bg = new BigDecimal("0.01");
		order.setPrice(bg);
		order.setsPrice(bg);
		order.setPlayTime(getNextDay(new Date()));
        order.setPayOrderId("12345611");
        order.setTicketsNo("12212122");
        order.setTicketId("PST20160918013085");

		TicktetUtil t = new TicktetUtil("SEND_CODE_REQ",Constants.corpCode, Constants.userName);
		String result = t.sendTicket(order);
		System.out.println("下单后返回结果：" + result);
		Map map = XMLUtil.doXMLParse(result);

		String rcode = (String) map.get("code");
		String rdescription = (String) map.get("description");

		System.out.println("下单后返回结果参数code：" + rcode);
		System.out.println("下单后返回结果参数description：" + rdescription);
		if (StringUtils.isNotEmpty(rcode) && rcode.equals("0")) {
//				&& StringUtils.isNotEmpty(rdescription) && rdescription.equals("成功")
			TicktetUtil t1 = new TicktetUtil("QUERY_SHORT_IMG_URL_REQ", Constants.corpCode, Constants.userName);
			String twoCode = t1.getTwoCodeURL(order);
			System.out.println("获取二维码地址：" + twoCode);
		}
	}

	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		date = calendar.getTime();
		return date;
	}

}
