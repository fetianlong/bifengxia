package com.chatm.search.controller.api;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.chatm.search.Ticket.MD5;
import com.chatm.search.model.Order;
import com.chatm.search.service.OrderService;
import com.chatm.search.util.Constants;

/**
 * 对外提供接口
 * @description   
 * @version currentVersion(1.0)  
 * @author pjh  
 * @createtime 2017年5月11日 上午11:41:46
 */
@RequestMapping("/api/order")
@Controller
public class OrderApiController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderApiController.class);
	
	@Autowired
	OrderService orderService;

	/**
	 * 游客扫码后，票务系统推送信息过来告诉已经使用票了
	 * 接口地址：http://www.bifengxia.com/api/order/updateOrderByqrCode
	 * @param request
	 * @return
	 * @description
	 * @version currentVersion
	 * @author pjh
	 * @createtime 2017年6月21日 下午2:29:25
	 */
	@RequestMapping(value = "/updateOrderByqrCode", method = RequestMethod.GET)
	@ResponseBody
//	public String updateOrderByqrCode(@RequestBody OrderApi orderApi, HttpServletRequest request){
	public String updateOrderByqrCode(HttpServletRequest request){
		logger.info("过闸入园回调接口。。。......  updateOrderByqrCode");
		ResponseInfo responseInfo = new ResponseInfo();
		String sign = request.getParameter("sign");
		String orderNo = request.getParameter("orderNo");
		String status = request.getParameter("status");
		logger.info("updateOrderByqrCode 回传参数：sign=" + sign + "  orderNo=" + orderNo + "  status=" + status);
		try {
			if(StringUtils.isNotEmpty(sign) && StringUtils.isNotEmpty(orderNo)) {
				String sigin = MD5.MD5Encode(orderNo+"="+orderNo+Constants.secretkey);
				logger.info("传过来的签名：" + sign + "   本地生成的签名：" + sigin);
				if(sigin.equals(sign)) {	//验签成功
					if(StringUtils.isNotBlank(status) && status.equals("check")){	//已检票
						Order order = orderService.selectByOrderByPayorderId(orderNo);
						if(null != order && order.getId() > 0){
							SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
							String dat = smd.format(order.getPlayTime());
							String con = null;
							if (order.getStatus() == 2) {	//已支付
								con = "尊敬的用户您好，您预订的" + dat + order.getCommodityName() + " 门票，订单号："+order.getPayOrderId()+ "，已经成功消费，如有疑问请拔打客服电话0835-2318091";
								order.setStatus(6);	//已完成订单
								order.setTicketsFlag(1);	//已使用票

								responseInfo.setSuccess(true);
								responseInfo.setMessage("用户取票成功，订单更新已使用票，成功！");
								logger.info("用户取票成功：" + order.getUserPhone() + ",订单更新为已使用票，成功");

								if (null != con) {
									String result = orderService.sendMsg(con, order.getUserPhone());	//发送短信，提示用户出票成功
									if (!StringUtils.isEmpty(result)) {	//
										long res = Long.valueOf(result);
										if(res > 0){	//发送成功
											order.setMessage_flag(1);
											orderService.updateByPrimaryKeySelective(order);
											responseInfo.setMessage("订单更新成功，发送短信成功！" );
											logger.info("票务系统推送接口调用成功 发送短信返回结果====" + result + "  手机号：" + order.getUserPhone());
										} else {
											responseInfo.setMessage("发送短信失败！" );
											logger.error("票务系统推送接口调用 发送短信失败====" + result + "  手机号：" + order.getUserPhone());
										}
									}
								}
								return "success";
							} else if (order.getStatus() == 6){
								logger.error("该订单已使用");
								return "success";
							} else {
								responseInfo.setSuccess(false);
								responseInfo.setMessage("参数错误！");
								logger.error("用户取票错误：参数错误");
							}
						} else {
							responseInfo.setSuccess(false);
							responseInfo.setMessage("订单不存在");
							logger.error("订单不存在");
						}
					} else {
						responseInfo.setSuccess(false);
						responseInfo.setMessage("取票码为空或者订单状态错误");
						logger.error("取票码为空或者订单状态错误");
					}
				} else {
					responseInfo.setSuccess(false);
					responseInfo.setMessage("签名错误");
					logger.error("签名错误");
				}
			} else {
				responseInfo.setSuccess(false);
				responseInfo.setMessage("toke为空或者不正确");
				logger.error("toke为空或者不正确");
			}
		} catch (Exception e) {
			responseInfo.setSuccess(false);
			responseInfo.setMessage("订单号修改失败：" + e.getMessage());
			logger.error("订单修改失败：[方法：" + new Throwable().getStackTrace()[1].getMethodName()+"] - 参数【"+orderNo+"】 -错误信息：" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		JSONObject json = (JSONObject) JSONObject.toJSON(responseInfo);
//		System.out.println(json);
		return json.toString();
	}


//	@RequestMapping(value = "/test")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo test(@RequestBody String requestBody, HttpServletRequest request){

        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        System.out.println(jsonObject.getString("xmlMsg"));
        System.out.println(jsonObject.getString("sign"));
		ResponseInfo responseInfo = new ResponseInfo();

		/*String xml = request.getParameter("xmlMsg");
		String sign = request.getParameter("sign");
		System.out.println("收到的xmlMsg：" + xml);
		System.out.println("收到的sign：" + sign);
		responseInfo.setMessage(xml);
		responseInfo.setData(sign);
		responseInfo.setSuccess(true);*/
		return responseInfo;
	}

	/**
	 * 根据取票码获取订单信息
	 * @param request
	 * @return

	@RequestMapping(value = "/getOrderByqrCode")
	@ResponseBody
	public ResponseInfo<Order> getOrderByqrCode(HttpServletRequest request){
		ResponseInfo<Order> responseInfo = new ResponseInfo<Order>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String token = request.getParameter("token");
		String ticketsNo = request.getParameter("ticketsNo");
		String orderStatus = request.getParameter("orderStatus");
		String reqTime = request.getParameter("reqTime");
		try {
			if(!StringUtils.isEmpty(token) && StringUtils.isNotEmpty(reqTime)) {
				String sigin = orderService.sign(Constants.orderUser, Constants.orderPW, Long.parseLong(reqTime));
				logger.info("传过来的签名：" + token + "   本地生成的签名：" + sigin);
				if(sigin.equals(token)) {
					if(!StringUtils.isBlank(ticketsNo) && !StringUtils.isBlank(orderStatus) && orderStatus.equals("1") && !StringUtils.isBlank(ticketsNo)){
						Order order = orderService.selectByOrderByTicketNo(ticketsNo);
						if(null != order && order.getId() > 0){
							String dd = df.format(d);
							Date d3 = df.parse(dd);

							Date pd = order.getPlayTime();
							String pd1 = df.format(pd);
							Date pd2 = df.parse(pd1);

							if(d3.getTime() <= pd2.getTime()) {
								responseInfo.setSuccess(true);
								responseInfo.setData(order);
								responseInfo.setMessage("订单有效，票数:" + order.getpCount());
							} else {
								responseInfo.setSuccess(false);
								responseInfo.setMessage("游玩时间已过期");
							}
						} else {
							responseInfo.setSuccess(false);
							responseInfo.setMessage("订单不存在");
							logger.error("订单不存在");
						}
					} else {
						responseInfo.setSuccess(false);
						responseInfo.setMessage("取票码为空或者订单状态错误");
						logger.error("取票码为空或者订单状态错误");
					}
				} else {
					responseInfo.setSuccess(false);
					responseInfo.setMessage("签名错误");
					logger.error("签名错误");
				}
			} else {
				responseInfo.setSuccess(false);
				responseInfo.setMessage("toke为空或者不正确");
				logger.error("toke为空或者不正确");
			}
		} catch (Exception e) {
			responseInfo.setSuccess(false);
			responseInfo.setMessage("订单号修改失败：" + e.getMessage());
			logger.error("订单修改失败：[方法：" + new Throwable().getStackTrace()[1].getMethodName()+"] - 参数【"+ticketsNo+"】 -错误信息：" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return responseInfo;
	}*/
	/**
	 * 取票完成后调用接口，把订单状态改为已取票，并发送短信提示用户
	 * @param
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author pjh  
	 * @createtime 2017年5月11日 上午10:53:37
	 * , produces = MediaTypes.JAVASCRIPT_UTF_8
	 */
	/*@RequestMapping(value = "/getTicketByOrderNo", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfo getTicketByOrderNo(@RequestBody OrderApi orderApi, HttpServletRequest request){
		ResponseInfo responseInfo = new ResponseInfo();
//		String orderNo = request.getParameter("orderNo");
//		String orderStatus = request.getParameter("orderStatus");	//1：已取票，2：出票，3：申请退款
//		String reqTime = request.getParameter("reqTime");
//		String token = request.getParameter("orderToken");	//
//		String tiketURL = request.getParameter("tiketURL");	//票据二维码地址
		
		try {
//			if(IpTool.isInner(ip)){	//判断是否为内网IP
			if(!StringUtils.isEmpty(sign) && null != orderApi.getReqTime()) {
				String sigin = orderService.sign(Constants.orderUser, Constants.orderPW, Long.parseLong(orderApi.getReqTime()));
				if(sigin.equals(sign)) {
					if(!StringUtils.isBlank(orderNo) && !StringUtils.isBlank(status)){
						Order order = orderService.selectByOrderByPayorderId(orderNo);
						if(null != order && order.getId()>0){
							
							SimpleDateFormat smd = new SimpleDateFormat("yyyy年MM月dd日");
							String dat = smd.format(order.getPlayTime());
							String con = null;
							if (status.equals("1") && order.getStatus() == 2) {	//已取票并且已支付
								con = "尊敬的用户您好，您预订的" + dat + order.getCommodityName() + " 门票，订单号："+order.getPayOrderId()+ "，已经被取出，如有疑问请拔打客服电话0835-2318091";
								order.setStatus(6);	//已完成订单
								order.setTicketsFlag(1);	//已取票
								
								responseInfo.setSuccess(true);
								responseInfo.setMessage("订单更新成功");
								logger.info("用户取票成功：" + order.getUserPhone() + ",订单更新为已取票，成功");
							} else if (status.equals("2") && order.getStatus() == 2) {	//出票后发送短信，订单状态必须是已支付
								if (!StringUtils.isEmpty(orderApi.getTiketURL())) {
									order.setTicketsFlag(3);	//已出票
									con = "尊敬的用户您好，您预订的" + dat + order.getCommodityName() + " 门票，订单号："+order.getPayOrderId()+ "，"
											+ "已经成功出票，二维码地址：" + orderApi.getTiketURL() + "，如有疑问请拔打客服电话0835-2318091";
									responseInfo.setSuccess(true);
								} else {
									responseInfo.setSuccess(false);
									responseInfo.setMessage("二维码地址不存在：" + orderApi.getTiketURL());
									logger.error("用户二维码地址不存在：" + order.getUserPhone() + ",tiketURL()：" + orderApi.getTiketURL());
								}
							} else if (status.equals("3") && order.getStatus() == 6 && order.getTicketsFlag() == 1) {	//申请退款，订单状态必须已完成，而且是已出票
								order.setStatus(7);	//申请退款
								order.setTicketsFlag(2);	//已退票
								con = "尊敬的用户您好，您预订的" + dat + order.getCommodityName() + " 门票，订单号："+order.getPayOrderId()+ "，退款申请正在处理，请耐心等待！如有疑问请拔打客服电话0835-2318091";
								responseInfo.setSuccess(true);
							} else {
								responseInfo.setSuccess(false);
								responseInfo.setMessage("参数错误！");
								return responseInfo;
							}
							if (null != con) {
								String result = orderService.sendMsg(con, order.getUserPhone());	//发送短信，提示用户出票成功
								if (!StringUtils.isEmpty(result)) {	//
									long res = Long.valueOf(result);
									if(res > 0){	//发送成功
										order.setMessage_flag(1);
										orderService.updateByPrimaryKeySelective(order);
										responseInfo.setMessage("发送短信成功！" );
										logger.info("取票接口调用 发送短信返回结果====" + result + "  手机号：" + order.getUserPhone());
									} else {
										responseInfo.setMessage("发送短信失败！" );
										logger.error("取票接口调用 发送短信失败====" + result + "  手机号：" + order.getUserPhone());
									}
								}
							}
						} else {
							responseInfo.setSuccess(false);
							responseInfo.setMessage("订单不存在");
							logger.error("订单不存在");
						}
					} else {
						responseInfo.setSuccess(false);
						responseInfo.setMessage("取票码为空或者订单状态错误");
						logger.error("取票码为空或者订单状态错误");
					}
				} else {
					responseInfo.setSuccess(false);
					responseInfo.setMessage("签名错误");
					logger.error("签名错误");
				}
			} else {
				responseInfo.setSuccess(false);
				responseInfo.setMessage("toke为空或者不正确");
				logger.error("toke为空或者不正确");
			}
		} catch (IOException e) {
			responseInfo.setSuccess(false);
			responseInfo.setMessage("订单号修改失败");
			logger.error("订单修改失败：[方法：" + new Throwable().getStackTrace()[1].getMethodName()+"] - 参数【"+orderNo+"】 -错误信息：" + e.getLocalizedMessage());
		}
		return responseInfo;
	}*/

	
	public static void main(String[] args) {
		long req_time = System.currentTimeMillis();
		System.out.println(req_time);
//		long req_time = 1498736118406L;
		String account = "bifengxia";
		String password = "a69366ff3b68762dd9cbf91e02d570d07ed550bb";
		String sign = sign(account,password,req_time);
		System.out.println(sign);
	}
	// 签名算法
	public static String sign(String account, String password, long req_time) {
		String str = account + MD5.MD5Encode(password) + req_time;
		String sign = MD5.MD5Encode(str);
		return sign;
	}
}
