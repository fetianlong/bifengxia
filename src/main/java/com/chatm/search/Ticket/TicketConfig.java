package com.chatm.search.Ticket;

public class TicketConfig {
	public static final String account = "bfx_online";
	public static final String password = "0172588c04d54304b2dcd05bf6e2da69";
	// 签名算法
	public static String sign(long req_time) {
		String str = account + MD5.MD5Encode(password) + req_time;
		String sign = MD5.MD5Encode(str);
//		logger.info("access_token str:" + str);
		return sign;
	}
}
