package com.chartTmSearch.quickstart;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTemplate1 {

	private static Log Logger = LogFactory.getLog(HttpClientTemplate1.class);

	private String url;

	public HttpClientTemplate1() {
		super();
	}

	public HttpClientTemplate1(String url) {
		this.url = url;
	}

	/**
	 * 模拟get请求
	 * 
	 * @param url
	 * @param queryString
	 * @return
	 */
	public String doGet(String queryString) {
		String response = null;
		// 设置 Http 连接超时为5秒
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		HttpMethod method = new GetMethod(url);
		// 设置 get 请求超时为 5 秒
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		// 设置请求重试处理，用的是默认的重试处理：请求三次
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

		try {
			if (StringUtils.isNotBlank(queryString)) {
				method.setQueryString(URIUtil.encodeQuery(queryString));
			}

			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) {
				response = method.getResponseBodyAsString();
			} else {
				Logger.error("Method failed: " + method.getStatusLine());

			}
		} catch (URIException e) {
			Logger.error("执行HTTP Get请求时，编码查询字符串“" + queryString + "”发生异常！", e);
		} catch (IOException e) {
			Logger.error("执行HTTP Get请求" + url + "时，发生异常！", e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	

	public String doPost(Map<String, String> params, boolean pretty) {
		String response = null;

		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置 Http 连接超时为10秒
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
		
		PostMethod posmethod = new PostMethod(url);
		
		try {
			
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				list.add(new NameValuePair(key, params.get(key)));
			}

			NameValuePair[] data = new NameValuePair[list.size()];
			for (int i = 0; i < list.size(); i++) {
				data[i] = list.get(i);
			}
			
			// 将表单的值放入postMethod中
			posmethod.setRequestBody(data);

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
	
	
	public String httpPostWithJSON(Map params) {
		String respContent = null;
		try {
	        HttpPost httpPost = new HttpPost(url);
	        CloseableHttpClient client = HttpClients.createDefault();
	        JSONObject jsonObject = JSONObject.fromObject(params);
	        System.out.println("传值：" + params.toString());
	        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");//解决中文乱码问题    
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");   
	        httpPost.setEntity(entity);
	        
	        HttpResponse resp = client.execute(httpPost);
			
	        if(resp.getStatusLine().getStatusCode() == 200) {
	            HttpEntity he = resp.getEntity();
	            respContent = EntityUtils.toString(he,"UTF-8");
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
	
	public static void main(String[] args) {

		// http://www.yodao.com/smartresult-xml/search.s?type=mobile&q=手机号码
		//HttpClientTemplate t = new HttpClientTemplate("http://localhost:8080/looktm_test/getway.do?param=test");
//		String key="0b79739dbBEUK4XQYPD0lZCwkG&t=all&st=&ot=&lx=all&gt=all&fw=all&ll=all&dq=all&yx=all&zt=all&p=1&pn=20";
//		HttpClientTemplate1 t = new HttpClientTemplate1("http://vip3.hxlif.com/Api/Numberlist?Key="+key);
//		Map<String, String> params = new HashMap<String, String>();
/*		params.put("aa", "test");
		params.put("q", "13815261586");*/
		// String s = t.doPost(params,false);
           
//		String s = t.doGet("");
//		System.out.println(s);
		Random ran = new Random();
		String s1 = Integer.toHexString(ran.nextInt());
		System.out.println(s1);
		
		
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
