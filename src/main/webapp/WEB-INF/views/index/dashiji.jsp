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
            <a href="${ctx}/">首页</a> > <span>大事记</span>
        </div>
        <div class="results">
			<%-- <c:forEach items="${dashijiList}" var="art">
				<div class="result clearfix">
	            	<div class="f-l" style="margin-left:20px;width:820px;">
	                <h3><a href="${ctx}/index/${art.id}/dashijiView.html">${art.title}</a></h3>
	                <p>
	                	<c:choose>
	                		<c:when test="${fn:length(art.content) > 100}">
	                			${fn:substring(art.content, 0, 100)}......
	                		</c:when>
	                		<c:otherwise>
	                			${art.content}
	                		</c:otherwise>
	                	</c:choose>
	                </p>
                </div>
            </div>
			</c:forEach> --%>
			<c:forEach items="${articleList}" var="art">
				<div class="result clearfix">
	            	<img class="f-l" src="${art.picUrl}" style="width:180px;">
	            	<div class="f-l" style="margin-left:20px;width:820px;">
	                <h3><a href="${ctx}/index/${art.id}/dashijiView.html">${art.title}</a></h3>
	                <p>
		                <c:choose>
		               		<c:when test="${fn:length(art.abstractText) > 100}">
		               			${fn:substring(art.abstractText, 0, 100)}......
		               		</c:when>
		               		<c:otherwise>
		               			${art.abstractText}
		               		</c:otherwise>
		               	</c:choose>
	                </p>
                </div>
            </div>
			</c:forEach>
        </div>
        <tags:pagination page="${pageData}" paginationSize="10"/>
    </div>
</div>
</body>
</html>