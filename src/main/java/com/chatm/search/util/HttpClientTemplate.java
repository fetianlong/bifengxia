	package com.chatm.search.util;

	import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

	import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;

	public class HttpClientTemplate {

	private static Log Logger = LogFactory.getLog(HttpClientTemplate.class);

	private String url;

	public HttpClientTemplate() {
		super();
	}

	public HttpClientTemplate(String url) {
		this.url = url;
	}

	/**
	 * 模拟get请求
	 * 
	 * @param queryString
	 * @return
	 * @throws IOException 
	 */
	public String doGet(String queryString) {
		String response = null;
		// 设置 Http 连接超时为5秒
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		HttpMethod method = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
//		中午乱码参考：http://llying.iteye.com/blog/333503
		try {
			if (StringUtils.isNotBlank(queryString)) {
				method.setQueryString(URIUtil.encodeQuery(queryString, "UTF-8"));
				method.getParams().setContentCharset("UTF-8");
//				method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			}
			/*
			HttpResponse resp = client.execute(httpGet);
            
            HttpEntity entity = resp.getEntity();
            String respContent = EntityUtils.toString(entity , "GBK").trim();
            httpGet.abort();
            */
			Logger.info("执行get请求");
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
//				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
				StringBuffer stringBuffer = new StringBuffer();
				String str = "";  
				while((str = reader.readLine())!=null){  
				    stringBuffer.append(str);  
				}
//				String response1 = new String(stringBuffer.toString().getBytes("ISO-8859-1"),"GB2312");
//				String response2 = new String(stringBuffer.toString().getBytes("ISO-8859-1"),"UTF-8");
				response = stringBuffer.toString();
				Logger.info("get请求返回成功，数据response：" + response);
//				Logger.info("get请求返回成功，转码GB2312后数据response1：" + response1);
//				Logger.info("get请求返回成功，转码UTF-8后数据response2：" + response2);
			} else {
				Logger.error("Method failed: " + method.getStatusLine());

			}
		} catch (URIException e) {
			Logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
			e.printStackTrace();
		} catch (IOException e) {
			Logger.error("执行HTTP Get请求" + url + "时，发生异常！", e);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	public String doPostObject(Map<String, Object> params, boolean pretty) {
		String response = null;

		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		String params1 = JSON.toJSONString(params);
		Logger.info("请求的路径：" + url);
		Logger.info("请求的参数：" + params1);
		// 设置 Http 连接超时为10秒
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
		
		PostMethod posmethod = new PostMethod(url);
		posmethod.addRequestHeader("Content-Type", "application/json; charset=UTF-8"); 
		try {
			// 将表单的值放入postMethod中
			posmethod.setRequestBody(params1);

			client.executeMethod(posmethod);
			if (posmethod.getStatusCode() == HttpStatus.SC_OK) {
				// 内容多的时候用getResponseBodyAsStream,内容少直接可以posmethod.getResponseBodyAsString()
				BufferedReader reader = new BufferedReader(new InputStreamReader(posmethod.getResponseBodyAsStream(), "utf-8"));
				String line;
				StringBuffer sb = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					if (pretty) {
						sb.append(line).append(System.getProperty("line.separator"));
					} else {
						sb.append(line);
					}
				}
				reader.close();
				response = sb.toString();
			} else {
				Logger.error("请求失败: " + posmethod.getStatusLine());
			}
		} catch (IOException e) {
			Logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			posmethod.releaseConnection();
		}
		return response;
	}

	public String doPostFrom(Map<String, String> params, boolean pretty) {
		String response = null;
		HttpClient client = new HttpClient();
//		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		Logger.info("请求的路径：" + url);
		Logger.info("请求的参数：" + params);
		System.out.println("请求的参数：" + params.toString());
		// 设置 Http 连接超时为10秒
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
		PostMethod posmethod = new PostMethod(url);
//		JSONObject jsonObject = JSONObject.fromObject(params);
		posmethod.addRequestHeader("Content-Type", "application/json;charset=UTF-8");

		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				list.add(new NameValuePair(key, params.get(key)));
			}
			NameValuePair[] data = new NameValuePair[list.size()];
			for (int i = 0; i < list.size(); i++) {
				data[i] = list.get(i);
			}
			/*
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("xmlMsg", "xmll");
			jsonObject.put("sign", "sign111");
			String js = jsonObject.toString();
			RequestEntity entity = new StringRequestEntity(js,"application/json","UTF-8");
			posmethod.setRequestEntity(entity);*/
			// 将表单的值放入postMethod中
			posmethod.setRequestBody(data);
//			posmethod.addParameters(data);
			client.executeMethod(posmethod);
			if (posmethod.getStatusCode() == HttpStatus.SC_OK) {
				// 内容多的时候用getResponseBodyAsStream,内容少直接可以posmethod.getResponseBodyAsString()
				BufferedReader reader = new BufferedReader(new InputStreamReader(posmethod.getResponseBodyAsStream(), "utf-8"));
				String line;
				StringBuffer sb = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					if (pretty) {
						sb.append(line).append(System.getProperty("line.separator"));
					} else {
						sb.append(line);
					}
					
				}
				reader.close();
				
				response = sb.toString();
			} else {
				Logger.error("请求失败: " + posmethod.getStatusLine());
			}
		} catch (IOException e) {
			Logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			posmethod.releaseConnection();
		}
		return response;
	}

		/**
		 * 模拟请求
		 *
		 * @param url       资源地址
		 * @param map   参数列表
		 * @param encoding  编码
		 * @return
		 * @throws ParseException
		 * @throws IOException
		 */
		public static String send(String url, Map<String,String> map,String encoding) throws ParseException, IOException{
			String body = "";

			//创建httpclient对象
			CloseableHttpClient client = HttpClients.createDefault();
			//创建post方式请求对象
			HttpPost httpPost = new HttpPost(url);

			//装填参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			if(map!=null){
				for (Map.Entry<String, String> entry : map.entrySet()) {
					nvps.add(new NameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			//设置参数到请求对象中
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

			System.out.println("请求地址："+url);
			System.out.println("请求参数："+nvps.toString());

			//设置header信息
			//指定报文头【Content-type】、【User-Agent】
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			//执行请求操作，并拿到结果（同步阻塞）
			CloseableHttpResponse response = client.execute(httpPost);
			//获取结果实体
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				//按指定编码转换结果实体为String类型
				body = EntityUtils.toString(entity, encoding);
			}
			EntityUtils.consume(entity);
			//释放链接
			response.close();
			return body;
		}

		public String httpPostWithJSON(Map params) {
		String respContent = null;
		try {
			JSONObject jsonObject = JSONObject.fromObject(params);
			Logger.info("请求 路径：" + url);
			Logger.info("请求 数据：" + jsonObject);
	        HttpPost httpPost = new HttpPost(url);
	        CloseableHttpClient client = HttpClients.createDefault();
	        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");   
	        httpPost.setEntity(entity);
	        
	        HttpResponse resp = client.execute(httpPost);
	        if(resp.getStatusLine().getStatusCode() == 200) {
	            HttpEntity he = resp.getEntity();
	            respContent = EntityUtils.toString(he,"UTF-8");
	        } else {
	        	Logger.error("请求 " + url +" 失败：" + resp.getStatusLine());
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return respContent;
    }

	public String httpPostWithXML(Map<String, String> params) {
		String respContent = null;
		JSONObject jsonObj = JSONObject.fromObject(params);
		try {
			CloseableHttpClient client = HttpClients.createDefault();

			// 设置超时时间
			HttpPost post = new HttpPost(url);
			// 构造消息头
			post.setHeader("Content-type", "application/json; charset=utf-8");
			post.setHeader("Connection", "Close");
			String json = jsonObj.toString();
			// 构建消息实体
			StringEntity entity = new StringEntity(json, "UTF-8");
			entity.setContentEncoding("UTF-8");
			// 发送Json格式的数据请求
			entity.setContentType("application/json");
			post.setEntity(entity);

			HttpResponse resp = client.execute(post);
//	        HttpResponse resp = client.execute(httpPost);
	        if(resp.getStatusLine().getStatusCode() == 200) {
	            HttpEntity he = resp.getEntity();
	            respContent = EntityUtils.toString(he,"UTF-8");
	        } else {
	        	Logger.error("请求 " + url +" 失败：" + resp.getStatusLine());
	        }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return respContent;
    }

	public String sendPost(String param)  throws Exception{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setConnectTimeout(20000);
			conn.setReadTimeout(20*1000);
			conn.setDoOutput(true); // 发送POST请求必须设置如下两行
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			Logger.error("HTTP请求路径时错误：{}" + url, e);
			throw e; // 异常外抛
		} finally{
			try{
				if(out!=null)out.close();
				if(in!=null) in.close();
			}
			catch(Exception ex){
			}
		}
		return result;
	}

	/**
	 * post发送xml
	 * @Title: doPost 
	 * @Description: TODO
	 * @param @param xml
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String doPost(String xml) {
		String response = null;

		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置 Http 连接超时为5秒
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		PostMethod posmethod = new PostMethod(url);
		
		try {
			posmethod.setRequestEntity(new StringRequestEntity(xml, "text/xml", "utf-8"));
			
			client.executeMethod(posmethod);
			if (posmethod.getStatusCode() == HttpStatus.SC_OK) {

				// 内容多的时候用getResponseBodyAsStream,内容少直接可以posmethod.getResponseBodyAsString()
				BufferedInputStream bis = new BufferedInputStream(posmethod.getResponseBodyAsStream());  
                byte[] bytes = new byte[1024];  
                ByteArrayOutputStream bos = new ByteArrayOutputStream();  
                int count = 0;  
                while((count = bis.read(bytes))!= -1){  
                    bos.write(bytes, 0, count);  
                }  
                byte[] strByte = bos.toByteArray();  
                response = new String(strByte,0,strByte.length,"utf-8");  
                
			} else {
				Logger.error("Method failed: " + posmethod.getStatusLine());
			}
		} catch (IOException e) {
			Logger.error("执行HTTP Post请求" + url + "时，发生异常！", e);
		} finally {
			posmethod.releaseConnection();
		}
		return response;
	}

	// 构建唯一会话Id
	public static String getSessionId(){
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
	}

	public static void main(String[] args) {
		Map map = new LinkedHashMap();
		map.put("xmlMsg", "xml");
		map.put("sign", "111");
		HttpClientTemplate httpClientTemplate = new HttpClientTemplate("http://localhost:8081/bifengxia/api/order/test");
		String result = httpClientTemplate.doPostFrom(map,true);
		System.out.println(result);
	}

	public static Log getLogger() {
		return Logger;
	}

	public static void setLogger(Log logger) {
		Logger = logger;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
