<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title> ${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="${keywords}" />
    <meta name="Description" content="${Description}" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
</head>
<body>
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="introduce_text">
        <h3 class="h3 clearfix" style="text-align:center;">
	        <div class="center" style="display:inline-block;width:auto;padding:0 20px;">
	        	<span style="position:static;margin-top:66px;padding:0 20px;width:auto;">${article.title}</span>
	        </div>
	        <p style="color:#999;font-size:14px;margin-top:10px;">
	        	<fmt:formatDate value="${article.createDate}" type="date"/>
	        </p>
        </h3>
        ${article.content}
        <%-- 
        <p>雅安碧峰峡景区是国家AAAA级旅游区，包括风景区和野生动物园，由成都万贯（集团）置业股份有限公司投资修建。碧峰峡于1999年12月28日正式开园，开业当天迎来成雅高速正式开通，为景区带来了大量的人流和发展机遇。特别近年来，市政府交通投入力度加大，设施改善加快，伴随雅安作为川西南交通枢纽的确立，为碧峰峡景区的发展提供了强大动力。</p>
        <p>碧峰峡景区位于雅安市北16公里，景区面积20平方公里，森林面积20000余亩。景区为两条峡谷，左峡长7公里，右峡长2.5公里，呈“Ｖ”字形，宽30-70米，海拔700-1971米，峡壁相对高度100-200米。植被、峡景和瀑布是碧峰峡景区的鲜明特色。峡内林木葱郁，苍翠欲滴，峰峦叠嶂，崖壑峥嵘。时而奇峰耸峙，高插蓝天，时而两山并合天光一线，多类型的瀑布景观，更使双峡增添无限景色，令人陶醉。鉴于碧峰峡极其优良的自然生态环境，中国保护大熊猫研究中心把基地设置在碧峰峡风景区内，2003年12月28日建成并正式对外开放，基地占地1000余亩。5.12大地震后，卧龙的部分大熊猫搬迁到碧峰峡，目前基地圈养大熊猫80余只，成为全球圈养大熊猫最多的地方，吸引了众多国际友人来雅旅游。</p>
        <img src="${ctx}/static/img/houzi.png" alt="houzi"/>
        <p>碧峰峡野生动物园是西南第一家野生动物园，也是全国第一家生态型的野生动物园。该园规划面积10000亩，一期建设占地3000亩，其中野生动物观赏区1600亩，牧草基地1400亩，一期总投资达2个亿。它由猛兽车行观赏区和温驯动物步行观光区组成，其中猛兽车行观赏区分为散放狮区、散放熊区、散放虎区；温驯动物步行观光区又分为和谐家园、花果山水帘洞、百鸟极乐园、鸵鸟散放区、松鼠猴园、动物纪念园、动物乐园和极品动物区。</p>
         --%>
    </div>
</div>
</body>
</html>