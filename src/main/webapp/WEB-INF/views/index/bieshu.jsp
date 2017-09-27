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
    <div class="dashijian_text clearfix">
        <div class="mianbaoxie">
            <a href="${ctx}/">首页</a> > <a href="${ctx}/index/${pid}/meishizhusu.html">美食住宿</a> > <span>${titleName}</span>
        </div>
        <div class="jy_detail">
            <div class="tup clearfix">
                <div class="f-l tup_l">
                    <a class="syt" href="javascript:;"></a>
                    <a class="xyt" href="javascript:;"></a>
                    <div class="tup_l_wrap">
                    	<c:forEach items="${arrlist}" varStatus="status" var="art">
                    		<img src="${art.pathUrl}" <c:if test="${status.first}">class="active"</c:if> alt=""/>
                    	</c:forEach>
                    </div>
                </div>
                <div class="f-r tup_r">
                    <a href="javascript:;" class="syz">上一组</a>
                    <div class="tup_all">
                        <div class="tup_list_wrap">
                        	<c:forEach items="${articleList}" varStatus="status" var="allL">
                        		<div class="tup_list">
                        			<c:forEach items="${allL.articleResourceList}" varStatus="status" var="reso">
                        				<img src="${reso.pathUrl}" alt="" <c:if test="${status.first}">class="active"</c:if>/>
                        			</c:forEach>
                        		</div>
                        	</c:forEach>
                        </div>
                    </div>
                    <a href="javascript:;" class="xyz">下一组</a>
                </div>
            </div>
            <c:forEach items="${articleList}" varStatus="status" var="article">
            	<c:if test="${idoid==article.programaId}">
            	<div class="items active">
            	</c:if>
            	<c:if test="${idoid!=article.programaId}">
            	<div class="items"> 
            	</c:if>
		            <h3>${article.title} <span class="diji">1</span> / <span class="gongji">5</span></h3>
		            <p>${article.abstractText}</p>
        		</div>
            </c:forEach>
        </div>
    </div>
</div>
<!----end-introduce---->
</body>
</html>