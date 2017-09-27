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
    <div class="jtxx_text">
        <h3 class="h3 clearfix"><div class="center"><span>${article.title}</span></div></h3>
		${article.content}
    <%-- 
        <p>1、可到成都新南门旅游客运站乘坐“成都——碧峰峡”的旅游专线。</p>
        <img src="${ctx}/static/img/jtxx.png" alt="">
        <p>2、可到成都石羊高速公路客运站上乘坐“成都——雅安”的班车抵达雅安后在雅安旅游车站转乘“雅安——碧峰峡”客运班车或坐出租车转至碧峰峡。</p>
        <p>3、团体可通过自包旅游车直达碧峰峡</p>
        <p>4、自驾车：（1）走成雅高速，在雅安北出口下高速，过收费站后左转；（2）走成温邛高速</p>
        <p style="color:#f49c0b;">外地游客如何去碧峰峡？</p>
        <p style="font-size:14px;">外地游客可通过当地班车抵达雅安后，在雅安旅游车站转至碧峰峡。</p>
        <p style="font-size:14px;">碧峰峡联系电话： 028-82840005（成都） 0835-2318091 （雅安客户中心） 0835-2318017 （总台）</p>
    --%>
    </div> 
</div>
<!----end-introduce---->
<!--footer-->
<script>


</script>
<!--footer-ends-->
</body>
</html>