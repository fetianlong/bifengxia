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

<div class="bangzhu_banner" style="width:100%;background: url('${ctx}/static/img/bangzhu_banner.png') center center no-repeat;height: 300px;cursor: pointer;">

</div>
<div class="bangzhu_border"></div>
<div class="bangzhu_box">
    <div class="bangzhu_text clearfix">
        <ul class="f-l lianxifangshi clearfix">
            <li class="tab"><a href="${ctx}/index/27/youkefuwu.html"><span>游客服务</span></a></li>
            <li class="tab active"><a href="${ctx}/index/28/lianxidianhua.html"><span>联系方式</span></a></li>
        </ul>
        <div class="haoma f-r tabitem active">
        	${article.content}
        </div>
    </div>
</div>
</body>
</html>