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
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-introduce---->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="introduce_text">
        <h3 class="h3 clearfix">
            <div class="center"><span>${aspTitle}</span></div>
        </h3>
        <ul class="jcty_list clearfix">
        	<c:forEach items="${listArticle}" var="art">
        		<li>
	                <a href="${ctx}/${art.purl}"><img src="${art.picUrl}" style="width: 405px;height: 243.64px;" alt="mswy"/></a>
	                <h3><a href="${ctx}/${art.purl}">${art.title}</a></h3>
	                <p><a href="${ctx}/${art.purl}">${art.abstractText}</a></p>
	            </li>
        	</c:forEach>
        </ul>
    </div>
</div>
<!----end-introduce---->
</body>
</html>