package com.chatm.search.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chatm.search.model.Commodity;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.Order;
import com.chatm.search.service.CommodityPriceService;
import com.chatm.search.service.CommodityService;
import com.chatm.search.service.OrderService;

/**
 * 
 * 
 * @description
 * @version currentVersion(1.0)
 * @author pujianhua
 * @createtime 2016年4月14日 下午2:50:45
 */
@Component()
public class SendTicketMessageTask {

	private static Log logger = LogFactory.getLog(SendTicketMessageTask.class);
	
	@Autowired
	OrderService orderService;
	@Autowired
	CommodityService commodityService;
	@Autowired
	CommodityPriceService commodityPriceService;
	
	/*
	 * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点 0 0/30 9-17 * * ? 朝九晚五工作时间内每半小时 0 0 12
	 * ? * WED 表示每个星期三中午12点 "0 0 12 * * ?" 每天中午12点触发 "0 15 10 ? * *" 每天上午10:15触发
	 * "0 15 10 * * ?" 每天上午10:15触发 "0 15 10 * * ? *" 每天上午10:15触发
	 * "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 "0 * 14 * * ?"
	 * 在每天下午2点到下午2:59期间的每1分钟触发 "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
	 * "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 "0 0-5 14 * * ?"
	 * 在每天下午2点到下午2:05期间的每1分钟触发 "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
	 * "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 "0 15 10 15 * ?" 每月15日上午10:15触发
	 * "0 15 10 L * ?" 每月最后一日的上午10:15触发 "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
	 * "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
	 * "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
	 */
	/**
	 * 每隔30秒执行一次
	 * 
	 * @throws IOException
	 * @description
	 * @version currentVersion
	 * @author pujianhua
	 * @createtime 2016年4月14日 下午2:53:48
	 */
//	@Scheduled(cron = "0/30 * * * * ? ")	// 每个30S执行一次
	public void taskCycle() throws IOException, Exception {
		Order ordero = new Order();
		ordero.setMessage_flag(0);
		logger.info("定时任务开启：每30S查询发送失败短信的订单，并重新发送");
		List<Order> orderList1 = orderService.selectListOrderByOrder(ordero);	//获取已支付,但是发送短信未成功的订单
		if(null != orderList1 && !orderList1.isEmpty()){
			int listsize1 = orderList1.size();
			logger.info("获取到发送失败短信的订单数量：" + listsize1);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = new Date();
			for (int i = 0; i < listsize1; i++) {
				Order or = orderList1.get(i);
				String dd = df.format(d);
				Date d3 = df.parse(dd);
				
				Date pd = or.getPlayTime();
				String pd1 = df.format(pd);
				Date pd2 = df.parse(pd1);
				
				if(d3.getTime() <= pd2.getTime()) {
					logger.info("获取游玩时间未过期的订单手机号：" + or.getUserPhone());
					Commodity comm = commodityService.selectByPrimaryKey(or.getComPid());
					String ctype = comm.getCommodityType().toString();
					or.setCtype(ctype);
					String comTypeName = null;
					String dat = null;
					String qpm = "";
					String cont = null;
					String tno = "";
					if(ctype.equals("0")){	//0:门票，1：餐饮，2：住宿',
						comTypeName = "门票";
	//					Random r = new Random();
	//					long num = Math.abs(r.nextLong() % 10000000000L);
						tno = or.getPayOrderId().substring(or.getPayOrderId().length()-8, or.getPayOrderId().length());
						or.setTicketsNo(tno);
						if(StringUtils.isEmpty(or.getTicketsNo())){
							or.setTicketsNo(tno);
						} else {
							tno = or.getTicketsNo();
						}
						qpm = "；取票码：" + tno;
						cont = "尊敬的用户您好，您预订的 " + dat+or.getCommodityName() + comTypeName + "，订单号："+or.getPayOrderId()+ qpm + "，二维码地址：http://www.bifengxia.com/" + tno + ", 请您于有效期内，持有效证件到景区窗口换取入园门票，如有疑问请拔打客服电话0835-2318091";
					}else if(ctype.equals("1")){
						comTypeName = "餐饮";
					}else if(ctype.equals("2")){
						comTypeName = "住宿";
					}else if(ctype.equals("3")){
						comTypeName = "商品";
					}
					
					SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
					dat = smd.format(or.getPlayTime());
					if (StringUtils.isEmpty(cont)) {
						cont = "尊敬的用户您好，您预订的"+dat+or.getCommodityName()+ comTypeName + "订单号："+or.getPayOrderId()+ qpm +",请您于有效期内，持有效证件到景区窗口换取入园门票，如有疑问请拔打客服电话0835-2318091";
					}
					String result = orderService.sendMsg(cont, or.getUserPhone());	//发送短信，更新发送状态
					if (!StringUtils.isEmpty(result)) {
						long res = Long.valueOf(result);
						if(res>0){	//发送成功
							or.setMessage_flag(1);
							orderService.updateByPrimaryKeySelective(or);
							logger.info("订票发送短信返回结果====" + result + "  手机号：" + or.getUserPhone());
						} else {
							logger.info("订票发送短信失败====" + result + "  手机号：" + or.getUserPhone());
						}
					}
				}
			}
		}
		/*
		Order order1 = new Order();
		order1.setSend_ticket_flag(0);
		logger.info("定时任务开启：每10S查询推送到票务系统失败的订单，并重新推送");
		List<Order> orderList2 = orderService.selectListOrderByOrder(order1);	//获取已支付,但是推送到票务系统失败的订单
		if(null != orderList2 && !orderList2.isEmpty()){
			int listsize1 = orderList2.size();
			logger.info("获取到推送到票务系统失败的订单数量：" + listsize1);
			for (int i = 0; i < listsize1; i++) {
				Order or = orderList2.get(i);
//				Random r = new Random();
//				long num = Math.abs(r.nextLong() % 10000000000L);
				String tno = "";
				if(StringUtils.isEmpty(or.getTicketsNo())){
					tno = or.getPayOrderId().substring(or.getPayOrderId().length()-9, or.getPayOrderId().length());
					or.setTicketsNo(String.valueOf(tno));
				} else {
					tno = or.getTicketsNo();
				}
				
				String ticketId = "";
				Commodity comm = commodityService.selectByPrimaryKey(or.getComPid());
				if (null != comm && comm.getId() > 0) {
					List<CommodityPrice> listCommodityPrice = commodityPriceService.selectPriceBycomId(comm.getId());
					if(null != listCommodityPrice && !listCommodityPrice.isEmpty()){
						CommodityPrice cp = listCommodityPrice.get(0);
						ticketId = cp.getTicketId();
					}
					orderService.sendDateTicket(or, tno, ticketId);	//推送到票务系统，并更新推送状态
				}
			}
		}
		*/
	}


}
