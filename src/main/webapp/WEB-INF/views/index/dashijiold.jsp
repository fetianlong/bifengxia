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
            <a href="${ctx}/">首页</a> > <span>大事件</span>
        </div>
        <div class="f-l dsj_l">
        	<c:forEach items="${dashijiList}" var="art">
            <div class="item">
                <h3>${art.title}</h3>
            </div>
            </c:forEach>
            <!-- <div class="item">
                <h3>1999年碧峰峡野生动物园二期建设完成并投入使用</h3>
            </div>
            <div class="item">
                <h3>1999年12月28日 碧峰峡风景区和碧峰峡野生动物园正式开放</h3>
            </div>
            <div class="item">
                <h3>1999年12月28日 碧峰峡风景区和碧峰峡野生动物园正式开放</h3>
            </div>
            <div class="item">
                <h3>1999年碧峰峡野生动物园二期建设完成并投入使用</h3>
            </div>
            <div class="item">
                <h3>1999年12月28日 碧峰峡风景区和碧峰峡野生动物园正式开放</h3>
            </div> -->
            <tags:pagination page="${pageData}" paginationSize="10"/>
        </div>
        <%-- <div class="f-r dsj_r">
            <div class="news">
                <h3>新闻中心列表</h3>
                <ul>
                	<c:forEach items="${xinwenList}" var="art">
                		<li><a href="${ctx}/index/news/${art.id}/a1.html">${art.title}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="news">
                <h3>媒体报道列表</h3>
                <ul>
                	<c:forEach items="${meitiList}" var="art">
                		<li><a href="${ctx}/index/meiti/${art.id}/a1.html">${art.title}</a></li>
                	</c:forEach>
                </ul>
            </div>
        </div> --%>
    </div>
</div>
</body>
</html>