package com.chatm.search.weixinpay;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class XMLUtil {
    /** 
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。 
     * @param strxml 
     * @return 
     * @throws JDOMException 
     * @throws IOException 
     */  
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {  
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  

        if(null == strxml || "".equals(strxml)) {  
            return null;  
        }  
          
        Map m = new HashMap();  
          
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));  
        SAXBuilder builder = new SAXBuilder();  
        Document doc = builder.build(in);  
        Element root = doc.getRootElement();  
        List list = root.getChildren();  
        Iterator it = list.iterator();  
        while(it.hasNext()) {  
            Element e = (Element) it.next();  
            String k = e.getName();  
            String v = "";  
            List children = e.getChildren();  
            if(children.isEmpty()) {  
                v = e.getTextNormalize();  
            } else {  
                v = XMLUtil.getChildrenText(children);  
            }  
            System.out.println("key: " + k + "   value: " + v);  
            m.put(k, v);  
        }  
          
        //关闭流  
        in.close();  
          
        return m;  
    }  
      
    /** 
     * 获取子结点的xml 
     * @param children 
     * @return String 
     */  
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if(!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while(it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if(!list.isEmpty()) {  
                    sb.append(XMLUtil.getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
          
        return sb.toString();  
    }
    
    public static void main(String[] args) throws JDOMException, IOException {
		String xml = "<?xml version='1.0' encoding='UTF-8'?><PWBResponse>  <transactionName>SEND_CODE_RES</transactionName>  <code>0</code>  <description>成功</description>  <orderResponse>    <order>      <linkName>ceshi</linkName>      <linkMobile>18811355087</linkMobile>      <orderCode>201709180058406636</orderCode>      <orderPrice>0.01</orderPrice>      <payMethod>vm</payMethod>      <groupNo></groupNo>      <assistCheckNo>63061242</assistCheckNo>      <ticketOrders>        <ticketOrder>          <orderCode>s20170918115840596752</orderCode>          <totalPrice>0.01</totalPrice>          <price>0.01</price>          <quantity>1</quantity>          <occDate>2017-09-20 00:00:00</occDate>          <goodsCode>PST20170307097873</goodsCode>          <goodsName>测试门票</goodsName>        </ticketOrder>      </ticketOrders>      <src>interface</src>    </order>  </orderResponse></PWBResponse>";
		Map map = doXMLParse(xml);
		String rcode = (String) map.get("code");
		String rdescription = (String) map.get("description");
		String orderResponse = (String) map.get("orderResponse");
		
		Map map1 = doXMLParse(orderResponse);
		String assistCheckNo = (String) map1.get("assistCheckNo");
		
		System.out.println(("下单返回结果：code=" + rcode + "   ,description=" + rdescription + "，取票码：" + assistCheckNo));

	}
}
