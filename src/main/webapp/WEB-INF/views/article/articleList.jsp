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
</head>
<body>
<!--banner-starts-->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="dashijian_text clearfix">
        <div class="mianbaoxie">
            <a href="${ctx}/">首页</a> > <span>搜索结果</span>
        </div>
        <div class="results">
            <h3 class="count_result">以下是您的搜索结果，总共 <span class="count_num">${pageData.total}</span> 条</h3>
            <c:forEach items="${articleList}" var="art">
            	<div class="result">
	                <h3>
	                <c:choose>
	                	<c:when test="${art.programaId == 35}">
	                		<a href="${ctx}/index/${art.id}/dashijiView.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 32}">
	                		<a href="${ctx}/index/${art.id}/huodong.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 8}">
	                		<a href="${ctx}/index/${art.programaId}/mengshou.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 9}">
	                		<a href="${ctx}/index/${art.programaId}/xiongmao.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 10}">
	                		<a href="${ctx}/index/${art.programaId}/fly.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 11}">
	                		<a href="${ctx}/index/${art.programaId}/shangxi.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 33}">
	                		<a href="${ctx}/index/${art.programaId}/juchang.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 34}">
	                		<a href="${ctx}/index/${art.programaId}/jiayuan.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 16}">
	                		<a href="${ctx}/index/${art.programaId}/bieshu.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 17}">
	                		<a href="${ctx}/index/${art.programaId}/jiudian.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 19}">
	                		<a href="${ctx}/index/${art.programaId}/canting.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 20}">
	                		<a href="${ctx}/index/${art.programaId}/chalou.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 2}">
	                		<a href="${ctx}/index/${art.programaId}/introduction.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 24}">
	                		<a href="${ctx}/index/${art.programaId}/daolantu.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 25}">
	                		<a href="${ctx}/index/${art.programaId}/jiaotongxinxi.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 26 || art.programaId == 27}">
	                		<a href="${ctx}/index/${art.programaId}/bangzhu.html">${art.title}</a>
	                	</c:when>
	                	<c:when test="${art.programaId == 28}">
	                		<a href="${ctx}/index/${art.programaId}/lianxidianhua.html">${art.title}</a>
	                	</c:when>
	                	<c:otherwise>
	                		<a href="${ctx}/index/meiti/${art.id}/meitiDetail.html">${art.title}</a>
	                	</c:otherwise>
	                </c:choose>
	                <%-- <c:if test="${art.programaId == 35}">
	                <a href="${ctx}/index/${art.id}/dashijiView.html">${art.title}</a>
	                </c:if>
	                <c:if test="${art.programaId != 35}">
	                <a href="${ctx}/index/meiti/${art.id}/meitiDetail.html">${art.title}</a>
	                </c:if> --%>
	                </h3>
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
            </c:forEach>
        </div>
        
        <tags:pagination page="${pageData}" paginationSize="10"/>
        
    </div>
</div>
</body>
</html>