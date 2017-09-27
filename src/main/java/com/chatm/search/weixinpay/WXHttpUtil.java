package com.chatm.search.weixinpay;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class WXHttpUtil {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(WXHttpUtil.class);
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds  
    private final static String DEFAULT_ENCODING = "UTF-8";  
      
    public static String postData(String urlStr, String data){  
        return postData(urlStr, data, null);  
    }  
      
    public static String postData(String urlStr, String data, String contentType){  
        BufferedReader reader = null;  
        try {  
            URL url = new URL(urlStr);  
            URLConnection conn = url.openConnection();  
            conn.setDoOutput(true);  
            conn.setConnectTimeout(CONNECT_TIMEOUT);  
            conn.setReadTimeout(CONNECT_TIMEOUT);  
            if(contentType != null)  
                conn.setRequestProperty("content-type", contentType);  
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);  
            if(data == null)  
                data = "";  
            writer.write(data);   
            writer.flush();  
            writer.close();    
  
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
                sb.append("\r\n");  
            }  
            return sb.toString();  
        } catch (IOException e) {  
            logger.error("Error connecting to " + urlStr + ": " + e.getMessage());  
        } finally {  
            try {  
                if (reader != null)  
                    reader.close();  
            } catch (IOException e) {  
            }  
        }  
        return null;  
    }
    
	public static String httpGet(String url) {
		String result = "";
		
		try {
			String inline_temp = null;
			URL firstURL = new URL(url);
			InputStream ins = firstURL.openStream();
			
			for (DataInputStream instream = new DataInputStream(ins); (inline_temp = instream.readLine()) != null; result = result
					+ inline_temp) {
				;
			}
			result = new String(result.getBytes("iso8859-1"), "GBK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	* 发送https请求
	* 
	* @param requestUrl 请求地址
	* @param requestMethod 请求方式（GET、POST）
	* @param outputStr 提交的数据
	* @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	*/
	public static Map httpsRequestMap(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		Map map = new HashMap();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { 
				new MyX509TrustManager() 
			};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = bufferedReader.readLine();
			StringBuffer buffer = new StringBuffer();
			System.out.println("发起请求返回的数据：" + str);
			
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
//			jsonObject = JSON.parseObject(buffer.toString());
//			jsonObject = JSON.parseObject(str);
			String[] listStr = str.split("&");
			
			for (int i = 0; i < listStr.length; i++) {
				String[] s1 = listStr[i].split("=");
				map.put(s1[0], s1[1]);
			}
//			jsonObject = JSONObject.parseObject(map.toString().replace("=", ":"));
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 发送https请求
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequestJson(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { 
					new MyX509TrustManager() 
			};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = bufferedReader.readLine();
//			StringBuffer buffer = new StringBuffer();
			System.out.println("发起请求返回的数据：" + str);
//			while ((str = bufferedReader.readLine()) != null) {
//				buffer.append(str);
//			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			jsonObject = JSON.parseObject(str);
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
