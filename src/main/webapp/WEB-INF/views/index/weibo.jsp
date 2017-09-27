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
<!--banner-starts-->
<div class="bangzhu_banner">

</div>
<div class="bangzhu_border"></div>
<div class="bangzhu_box">
    <div class="bangzhu_text clearfix">
        <ul class="f-l lianxifangshi clearfix">
            <li class="tab active"><a href="javascript:;"><span>游客服务</span></a></li>
            <li class="tab"><a href="javascript:;"><span>联系方式</span></a></li>
        </ul>
        
        <div class="haoma f-r tabitem active">
            <h3>联系方式</h3>
            <div class="weibo clearfix">
                <h3 class="guanfang">官方微博</h3>
                <a class="f-l a1" href="http://weibo.com/p/1006062612957525/manage?iframe_url=http%3A%2F%2Fe.weibo.com%2Fv1%2Feps%2Flinks%2Ffriendchain#place" target="_blank"><img src="${ctx}/static/img/xinlangweibo.png" alt=""/></a>
                <a class="f-l" href="http://e.t.qq.com/yaanbifengxia" target="_blank"><img src="${ctx}/static/img/tengxun.png" alt=""/></a>
            </div>
            <%-- <div class="weixin">
                <h3 class="guanfang">官方微信</h3>
                <a href=""><img src="${ctx}/static/img/guanfangweixin.png" alt=""/></a>
            </div> --%>
        </div>
    </div>
</div>
</body>
</html>