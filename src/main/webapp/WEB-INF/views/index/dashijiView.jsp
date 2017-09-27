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
        <p>
        ${article.abstractText}
        </p>
    </div>
</div>
</body>
</html>