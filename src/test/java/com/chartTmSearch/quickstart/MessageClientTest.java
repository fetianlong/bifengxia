package com.chartTmSearch.quickstart;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.chatm.search.util.MessageClient;

public class MessageClientTest {

	public static void main(String[] args) {
		String sn="SDK-BBX-010-25168";
		String pwd="9-ca8f-[";
		try {
			MessageClient client = new MessageClient(sn,pwd);
			//获取信息
			String result = client.mdgetSninfo();
			System.out.println(result);
			String content = URLEncoder.encode("您的验证码是1234【碧峰峡】", "utf-8");
			String msg = client.mdsmssend("18811355087", content, "", "", "", "");
			System.out.println("返回结果：" + msg);
			//短信发送	
//	        String content = URLEncoder.encode("测试788", "utf8");
//	        System.out.println(content);
//			String result_mt = client.mdsmssend("13800138", content, "", "", "", "");
//			System.out.print(result_mt);
			//个性短信发送
//			String result_gxmt = client.mdgxsend("13800138", "测试", "", "", "", "");
//			System.out.print(result_gxmt);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
