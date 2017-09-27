<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title> 二维码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-introduce---->

<div class="introduce_text_wrap" style="padding:100px 0;text-align:center;">
	<img src="${ctx}/qr_codeImg?code_url=${urlCode}"/>
	<p style="margin-top:14px;">二维码</p>
</div>
</body>
</html>