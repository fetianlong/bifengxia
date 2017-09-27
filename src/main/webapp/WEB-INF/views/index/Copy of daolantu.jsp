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
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/layer.css">
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-introduce---->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
	<div class="daolantu_text" style="width:1270px;">
		<h3 class="h3 clearfix"><div class="center"><span>${article.title}</span></div></h3>
		${article.content}
		<a href="javascript:;" style="top:186px;right: 321px;"></a>
        <a href="javascript:;" style="top:165px;right: 437px;"></a>
        <a href="javascript:;" style="top:213px;right: 503px;"></a>
        <a href="javascript:;" style="top:186px;right: 520px;"></a>
        <a href="javascript:;" style="top:202px;right: 612px;"></a>
        <a href="javascript:;" style="top:214px;right: 700px;"></a>
        <a href="javascript:;" style="top:176px;right: 745px;"></a>
        <a href="javascript:;" style="top:276px;right: 770px;"></a>
        <a href="javascript:;" style="top:347px;right: 796px;"></a>
        <a href="javascript:;" style="top:376px;right: 739px;"></a>
        <a href="javascript:;" style="top:437px;right: 730px;"></a>
        <a href="javascript:;" style="top:483px;right: 684px;"></a>
        <a href="javascript:;" style="top:485px;right: 644px;"></a>
        <a href="javascript:;" style="top:538px;right: 624px;"></a>
        <a href="javascript:;" style="top:520px;right: 611px;"></a>
        <a href="javascript:;" style="top:554px;right: 568px;"></a>
        <a href="javascript:;" style="top:555px;right: 457px;"></a>
        <a href="javascript:;" style="top:625px;right: 506px;"></a>
        <a href="javascript:;" style="top:650px;right: 430px;"></a>
        <a href="javascript:;" style="top:560px;right: 210px;"></a>
        <a href="javascript:;" style="top:314px;right: 287px;"></a>
        <a href="javascript:;" style="top:624px;right: 697px;"></a>
        <a href="javascript:;" style="top:624px;right: 795px;"></a>
    </div>
</div>
<div class="daolantu_tc">
	<h3>鸳鸯瀑布</h3>
	<div class="p_wrap">
		<p>鸳鸯瀑布位于四川省雅安市北8公里，此瀑布一分为二，左边水流较大，为阴；右边水流较小，为阳。故称鸳鸯瀑布。无心树，此树中空，又称之无心树。意即薄情寡义。周围无同类，连别的树也羞于其共处共生，都远避之。其侧面即为鸳鸯瀑布，相依相偎，与无心树的孤独形成强烈对比千里冰封时。</p>
		<p>鸳鸯瀑布位于四川省雅安市北8公里，此瀑布一分为二，左边水流较大，为阴；右边水流较小，为阳。故称鸳鸯瀑布。无心树，此树中空，又称之无心树。意即薄情寡义。周围无同类，连别的树也羞于其共处共生，都远避之。其侧面即为鸳鸯瀑布，相依相偎，与无心树的孤独形成强烈对比千里冰封时。</p>
	</div>
</div>
<!----end-introduce---->
<script src="${ctx}/static/js/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/js/layer.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>