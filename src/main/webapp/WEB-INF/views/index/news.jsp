<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
<%--     <link rel="stylesheet" href="${ctx}/static/css/content.css" type="text/css"/> --%>
    
    <style>
    	@media (max-width:640px){
    		.result>div.f-l{width:96% !important;}
    	}
    </style>
</head>
<body>
<!--banner-starts-->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="dashijian_text clearfix">
    	<h3 class="h3 clearfix">
            <div class="center"><span>新闻中心</span></div>
        </h3>
        
        <div class="results">
			<c:forEach items="${articleList}" var="art">
				<div class="result clearfix">
	            	<img class="f-l" src="${art.picUrl}" style="width:180px;">
	            	<div class="f-l" style="margin-left:20px;width:820px;">
	                <h3><a href="${ctx}/index/news/${art.id}/newDetail.html">${art.title}</a></h3>
	                <p>${art.abstractText}</p>
	                <p class="date">时间：
	                <c:if test="${art.releaseType==0}">
                    	<fmt:formatDate value="${art.createDate}" type="date"/>
                    </c:if>
                    <c:if test="${art.releaseType==1}">
                    	<fmt:formatDate value="${art.releaseTime}" type="date"/>
                    </c:if>
	                </p>
                </div>
            </div>
			</c:forEach>
            
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
</div>
<script>
	
</script>
</body>
</html>