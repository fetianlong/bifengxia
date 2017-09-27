package com.chatm.search.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.Ticket.MD5;
import com.chatm.search.Ticket.TicktOrder;
import com.chatm.search.Ticket.TicktetUtil;
import com.chatm.search.alipay.util.UtilDate;
import com.chatm.search.dao.CommodityDao;
import com.chatm.search.dao.CommodityPriceDao;
import com.chatm.search.dao.DictionaryDao;
import com.chatm.search.dao.OrderDao;
import com.chatm.search.dao.RegistUserDao;
import com.chatm.search.model.Commodity;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.Dictionary;
import com.chatm.search.model.Order;
import com.chatm.search.model.RegistUser;
import com.chatm.search.shiro.ShiroDbRealm.ShiroUser;
import com.chatm.search.util.Constants;
import com.chatm.search.util.HttpClientTemplate;
import com.chatm.search.util.MessageClient;
import com.chatm.search.weixinpay.XMLUtil;

@Transactional
@Component
public class OrderService {
	
	private static Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	OrderDao orderDao;
	@Autowired
	CommodityDao commodityDao;
	@Autowired
	CommodityPriceDao commodityPriceDao;
	@Autowired
	RegistUserDao registUserDao;
	@Autowired
	DictionaryDao dictionaryDao;
	
    public int deleteByPrimaryKey(Long id){
    	return orderDao.deleteByPrimaryKey(id);
    };

    public int insert(Order record){
    	return orderDao.insert(record);
    };

    public int insertSelective(Order record){
        int flag = 0;
        try {
            if (!StringUtils.isEmpty(record.getUserPhone())) {
                ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
                RegistUser registUser = new RegistUser();
                registUser.setId(user.id);
                registUser.setPhone(record.getUserPhone());
                registUserDao.updateByPrimaryKeySelective(registUser);
                logger.info("添加订单的时候判断手机号是否有值：" + record.getUserPhone() + "，有值更新用户手机号");
            }
            Long sorders = record.getpCount();
            String soIds = "";

            for (int i = 0; i < sorders.intValue(); i++) {
               /* String pid = record.getPayOrderId();
                Long id = Long.valueOf(pid);
                Long id1 = id + i;*/
                soIds += "s" + UtilDate.getOrderNum()+UtilDate.getThree() + ",";
            }
            soIds = soIds.substring(0, soIds.length()-1);


            record.setTicketsNo(soIds);
            flag = orderDao.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return flag;
    };

    public Order selectByPrimaryKey(Long id){
    	return orderDao.selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(Order order) throws IOException{
    	
		int i = 0;
		if (null != order.getStatus() && order.getStatus()==2) {	//支付成功后，发送短信和推送到票务系统
			logger.info("支付成功后，准备发送短信和推送到票务系统");
			if (null == order.getTicketsFlag()) {order.setTicketsFlag(0);} 
			if (null == order.getMessage_flag()) {order.setMessage_flag(0);}
			if (null == order.getSend_ticket_flag()){order.setSend_ticket_flag(0);}
			
			logger.info("TicketsFlag：" + order.getTicketsFlag());
			logger.info("Message_flag：" + order.getMessage_flag());
			logger.info("Send_ticket_flag：" + order.getSend_ticket_flag());
//			order.setMessage_flag(0);
//			order.setSend_ticket_flag(0);
			
			String ticketId = "";
			Commodity comm = commodityDao.selectByPrimaryKey(order.getPayCommodityId());
			List<CommodityPrice> listCommodityPrice = commodityPriceDao.selectPriceBycomId(comm.getId());
			if(null != listCommodityPrice && !listCommodityPrice.isEmpty()){
				CommodityPrice cp = listCommodityPrice.get(0);
//				ticketId = cp.getId().toString();
				ticketId = cp.getTicketId();
				if (StringUtils.isEmpty(ticketId)) {
					ticketId = "2017061801";
				}
			}
			logger.info("ticketId：" + ticketId);
			order.setTicketId(ticketId);
			String ctype = null;
			String comTypeName = "";
			if(null != comm.getCommodityType()) {
				Dictionary dictionary = new Dictionary();
				dictionary.setCode("sptype");
				dictionary.setCodeValue(comm.getCommodityType().toString());
				dictionary = dictionaryDao.getDictionary(dictionary);
				comTypeName = dictionary.getName();
				ctype = comm.getCommodityType().toString();
			}
			order.setCtype(ctype);
			
			SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
			String dat = smd.format(order.getPlayTime());
			
			String cont = null;
			
			if (ctype.equals("0")) {	//0:门票，1：餐饮，2：住宿',
				threadSendTicket(order, ticketId, dat, comTypeName);
			} else {
				logger.info("当前订单不是门票，直接发送短信，先判断是否已经发送过短信");
				if (null != order.getMessage_flag() && order.getMessage_flag() != 1) {	//如果短信没有发送就发送，已经发送就不发送
					if (StringUtils.isEmpty(cont)) {
						cont = "尊敬的用户您好，您预订的 " + dat+order.getCommodityName() + comTypeName + "，订单号："+order.getPayOrderId() + ", 请您于有效期内，持有效证件到景区窗口换取入园门票，如有疑问请拔打客服电话0835-2318091";
					}
					String result = sendMsg(cont, order.getUserPhone());	//发送短信通知
					if (!StringUtils.isEmpty(result)) {
						long res = Long.valueOf(result);
						if(res>0){	//发送成功
							order.setMessage_flag(1);
							logger.info("订票发送短信成功====" + result + "  手机号：" + order.getUserPhone());
						} else {
							logger.info("订票发送短信失败====" + result + "  手机号：" + order.getUserPhone());
						}
					}
				}
			}
			i = orderDao.updateByPrimaryKeySelective(order);
//			if(ctype.equals("0")){	//门票就推送到票务系统
//				threadSendTicket(order,ticketId);
				
//			}
			
		} else {
			logger.info("订单status != 2,直接更新");
			orderDao.updateByPrimaryKeySelective(order);
		}
		return i;
    }

    /**
     * 多线程的方式 推送票务系统
     * @param o
     * @param ti
     * @param d
     */
    private void threadSendTicket(Order o, String ti, String d, String c) {
    	final Order order = o;
		final String ticketId = ti;
		final String dat = d;
		final String comTypeName = c;
    	logger.info("---新开线程发送短信和推送票务方法中。。。。。。。。。。。。。。。。。。。。。。。。。");
		//多线程的方式 发送短信和
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String tno = "";
					String qpm = "";
//					System.out.println("3---进入多线程 run 方法中：");
					logger.info("当前订单为门票，先判断是否已经推送过，准备推送到票务系统");
					Order o1 = selectByPrimaryKey(order.getId());
					logger.info("先从库中查询是否已经推送：" + o1.getSend_ticket_flag() + "   当前订单推送状态：" + order.getSend_ticket_flag());
					if (null == o1.getSend_ticket_flag()) {
						o1.setSend_ticket_flag(0);
					}
					if (order.getSend_ticket_flag() == o1.getSend_ticket_flag() && order.getSend_ticket_flag() != 1) {
						/*logger.info("当前订单为门票未推送过，准备推送");
						logger.info("当前订单是否已经存在取票码：" + order.getTicketsNo());
						if (StringUtils.isEmpty(order.getTicketsNo())) {
							tno = order.getPayOrderId().substring(order.getPayOrderId().length()-8, order.getPayOrderId().length());
							order.setTicketsNo(tno);
							logger.info("获取取票码：" + order.getTicketsNo());
						}*/
//						boolean flag = sendDateTicket(order, order.getTicketsNo(),ticketId);
						String twoCodeurl = sendDateByZYTicket(order);
//						boolean flag = true;
						if(StringUtils.isNotEmpty(twoCodeurl)) {
//							logger.info("推送到票务系统成功，获取取票码：" + tno);
//							qpm = "；取票码：" + tno;
							String cont = "尊敬的用户您好，您预订的 " + dat+order.getCommodityName() + comTypeName + "，订单号："+order.getPayOrderId() + "，票数：" + order.getpCount() + "张，二维码地址：" + twoCodeurl + "，取票码：" + order.getAssistCheckNo() + ", 请您于有效期内，持有效证件到景区窗口换取入园门票，如有疑问请拔打客服电话0835-2318091";
							String result = sendMsg(cont, order.getUserPhone());	//发送短信通知
							if (!StringUtils.isEmpty(result)) {
								long res = Long.valueOf(result);
								if(res>0){	//发送成功
									order.setMessage_flag(1);
									logger.info("订票发送短信成功====" + result + "  手机号：" + order.getUserPhone());
								} else {
									logger.info("订票发送短信失败====" + result + "  手机号：" + order.getUserPhone());
								}
							}
							order.setSend_ticket_flag(1);
							order.setTicketsFlag(3);	//3：已出票
							logger.info("发送短信成功后更新数据：把TicketsFlag设置为3（已出票），Send_ticket_flag（0），Message_flag（1）");
							orderDao.updateByPrimaryKeySelective(order);
						}
					} else logger.info("当前已经推送过了");
//					boolean flag = sendDateTicket(o, String.valueOf(o.getTicketsNo()),ti);
				} catch (Exception e) {
					logger.error("推送到票务系统异常：" + e.getLocalizedMessage());
//					e.printStackTrace();
				}	//推送数据到票务系统
			}
		});
		thread.start();
	}

	/**
	 * @Description：调用智游宝接口订票
	 * @param：[order]
	 * @author：pjh
	 * @createtime：2017/9/11-16:19
	 * @updateuser：
	 * @updatetime：
	 * @updateDescription：
	 * @return：string twoCodeurl 二维码地址 
	 */
	private String sendDateByZYTicket(Order order) {
		String twoCodeurl = "";
		try {
			logger.info("调用智游宝下单请求：" + order.getPayOrderId());
			TicktetUtil ticktetUtil = new TicktetUtil("SEND_CODE_REQ", Constants.corpCode, Constants.userName);
			String result = ticktetUtil.sendTicket(order);
			logger.info("get请求返回：" + result);
			Map map = XMLUtil.doXMLParse(result);

			String rcode = (String) map.get("code");
			String rdescription = (String) map.get("description");
			String orderResponse = (String) map.get("orderResponse");
			Map map1 = XMLUtil.doXMLParse(orderResponse);
			String assistCheckNo = (String) map1.get("assistCheckNo");
			logger.info("下单返回结果：code=" + rcode + "   ,description=" + rdescription + "，取票码：" + assistCheckNo);

			if (StringUtils.isNotEmpty(rcode) && rcode.equals("0")) {
				logger.info("调用智游宝下单成功，订单号：" + order.getPayOrderId() + "，准备获取二维码");
//					&& StringUtils.isNotEmpty(rdescription) && rdescription.equals("成功")) 
				//获取二维码地址
				TicktetUtil t1 = new TicktetUtil("QUERY_SHORT_IMG_URL_REQ", Constants.corpCode, Constants.userName);
				twoCodeurl = t1.getTwoCodeURL(order);
				order.setAssistCheckNo(assistCheckNo);
				logger.info("获取智游宝二维码成功：" + twoCodeurl);
			} else {
				logger.info("判断   StringUtils.isNotEmpty(rcode)：{}", StringUtils.isNotEmpty(rcode));
				logger.info("判断   rcode.equals('0')：{}", rcode.equals("0"));
				logger.info("判断   StringUtils.isNotEmpty(rdescription)：{}", StringUtils.isNotEmpty(rdescription));
				logger.info("判断   rdescription.equals('成功')：{}", rdescription.equals("成功"));
			}

		} catch (JDOMException e) {
			logger.info("解析 XML异常：{}", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("调用智游宝接口失败，订单号：" + e);
			e.printStackTrace();
		}

		return twoCodeurl;
	}

	public int updateByPrimaryKey(Order record){
    	return orderDao.updateByPrimaryKey(record);
    };
    
    public List<Order> getOrderByUser(Long userId){
    	return orderDao.getOrderListByUser(userId);
    }

	public Order selectOrderByOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.selectOrderByOrder(order);
	}
	public List<Order> selectListOrderByOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.selectListOrderByOrder(order);
	}

	/**
	 * 根据订单ID更新订单状态
	 * @param order
	 */
	public int updateOrderByPayorderId(Order order) {
		return orderDao.updateOrderByPayorderId(order);
	}

	/**
	 * 根据订单ID获取订单信息
	 * @param out_trade_no
	 * @return
	 */
	public Order selectByOrderByPayorderId(String out_trade_no) {
		// TODO Auto-generated method stub
		return orderDao.selectByOrderByPayorderId(out_trade_no);
	}
	
	/**
	 * 送短信通知
	 * @param cont 短信内容
	 * @param phone	发送的手机号
	 * @return
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年5月11日 上午11:15:15
	 */
	public String sendMsg(String cont, String phone) {
		String sn="SDK-BBX-010-25168";
		String pwd="9-ca8f-[";
		String result = null;
		MessageClient client;
		try {
			logger.info("发送短信准备， 手机号：" + phone);
			client = new MessageClient(sn,pwd);
			String content = URLEncoder.encode(cont+"【碧峰峡】", "utf-8");
			result = client.mdsmssend(phone, content, "", "", "", "");
		} catch (UnsupportedEncodingException e) {
			logger.info("发送短信失败====  手机号：" + phone + "   错误信息：" + e.getLocalizedMessage());
		}
		return result;
	}
	
	/**
	 * 推送数据到票务系统
	 * @param or
	 * @param num 
	 * @param ticketId 
	 * @throws IOException
	 */
	public boolean sendDateTicket(Order or, String num, String ticketId) throws IOException {
//		System.out.println("4---进入推送方法中：");
		try {
//			Resource resource = new ClassPathResource("/application.properties");
//			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String ticketUrl = Constants.ticketUrl;
			String account = Constants.ticketAccount;
			String password = Constants.ticketPassword;
			
	//		or = orderDao.selectByOrderByPayorderId(orderPayId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
			String url = ticketUrl;
			long req_time = System.currentTimeMillis();
			List<TicktOrder> orders = new ArrayList<TicktOrder>();
			TicktOrder order = new TicktOrder();
			
			order.setItem_name(or.getCommodityName());
			order.setTicket_id(ticketId);
			order.setActive_time(sdf.format(or.getPlayTime()));
	//		order.setPrice(or.getsPrice().toString());
	//		order.setAmount(or.getPrice().toString());	//总价
			if(or.getsPrice().toString().equals("0.01")){
				order.setPrice("0");	//测试设置单个价格，价格用数字，不带小数点的
				order.setAmount("0");	//测试设置总价
			}else{
				String p = or.getsPrice().toString();
				String ap = or.getPrice().toString();
				if(p.lastIndexOf(".") > 0) {
					String p1 = p.substring(0, p.lastIndexOf("."));
					String p2 = ap.substring(0, ap.lastIndexOf("."));
					order.setPrice(p1);
					order.setAmount(p2);
				} else {
					order.setPrice(or.getsPrice().toString());
					order.setAmount(or.getPrice().toString());	//总价
				}
			}
			
			order.setSize(or.getpCount().intValue());
			order.setCust_name(or.getPayUserName());
			order.setCust_phone(or.getUserPhone());
			order.setCust_no(or.getPayCardNo());
			order.setCreate_time(sdf.format(or.getPayDate()));
			order.setOrder_id(or.getPayOrderId());
//			order.setOrder_no(or.getPayOrderId());
	//		long num = Math.abs(new Random().nextLong() % 10000000000L);
			
			order.setVerify_code(num);
//			order.setVerify_code("GW" + num);
			order.setStatus("1");
			if(or.getPayType()==0){
				order.setPay_type("4");
			}else if(or.getPayType()==1){
				order.setPay_type("3");
			}
			orders.add(order);
			//
			Map<String, Object> params = new HashMap<>();
			params.put("account", account);
			params.put("req_time", req_time + "");
			params.put("access_token", sign(account, password, req_time));
			params.put("orders", orders);
	//		String rspJson = AsyncHttpUtils.post(url, JacksonUtil.toJson(params));
			HttpClientTemplate httpClientTemplate = new HttpClientTemplate(url);
//			String result = httpClientTemplate.httpPostWithJSON(params);
			String result = httpClientTemplate.doPostObject(params, false);
			logger.info("票务返回：" + result);
			/*if (!StringUtils.isEmpty(result)) {
				if (result.equals("{}")) {
					return true;
				} else {
					CommonRsp rsp = JacksonUtil.fromJson(result, CommonRsp.class);
					if (rsp != null && rsp.getRsp_code() == 0) {
						logger.info("推送到票务数据成功，手机号：" + order.getCust_phone() + "，订单号：" + order.getOrder_id());
						return true;
					}else{
						logger.error("推送到票务数据失败，手机号：" + order.getCust_phone() + "，订单号：" + order.getOrder_id());
						return false;
					}
				}
			}*/
			
		} catch (Exception e) {
			logger.info("推送到票务系统失败：[方法：" + new Throwable().getStackTrace()[1].getMethodName()+"] - 参数【"+or+"】 -错误信息：" + e.getLocalizedMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// 签名算法
	public String sign(String account, String password, long req_time) {
		String str = account + MD5.MD5Encode(password) + req_time;
		String sign = MD5.MD5Encode(str);
		return sign;
	}

	public Order selectByOrderByTicketNo(String ticketsNo) {
		return orderDao.selectByOrderByTicketNo(ticketsNo);
	}
}