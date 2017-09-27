<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> 订单列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-introduce---->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="border_zhongxin">
    <div class="zhongxin_bor">
        <div class="bor_gr">
            <span>历史订单</span>
        </div>
    </div>
</div>
<div class="bangzhu_box pad">
    <div class="bangzhu_text clearfix">
        <ul class="f-l lianxifangshi clearfix">
            <li class="tab"><a href="${ctx}/userCenter/gotoUserCenter"><span>个人信息</span></a></li>
            <li class="tab active"><a href="${ctx}/order/list"><span>历史订单</span></a></li>
        </ul>
        <ul class="f-r bangzhu_p xx tabitem active">
        	<c:set var="nowDate">
        		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd" type="date"/>
        	</c:set>
        	<c:forEach items="${orderList}" var="order">
        		<li>
        			<h4>订单号：${order.payOrderId}</h4>
        			<c:if test="${order.status==1}">
        			<c:set var="useDate">
		        		<fmt:formatDate value="${order.playTime}" pattern="yyyy-MM-dd" type="date"/>
		        	</c:set>
        			<h4>
        			<a class="f-r" onclick="cencleOrder('${order.id}');" id="${order.id}" href="javascript:;">取消订单</a>
        			
        			<c:if test="${useDate > nowDate}">
        			<a class="" href="${ctx}/order/payment?orderId=${order.id}">继续支付</a>
        			</c:if>
        			</h4>
        			</c:if>
        			<c:if test="${order.status==2}">
        			
        			<h4><a class="f-r" onclick="refundFastpay('${order.id}','<fmt:formatDate value="${order.playTime}" pattern="yyyy-MM-dd" type="date"/>','${nowDate}');" id="${order.id}" href="javascript:;">退款</a></h4>
        			</c:if>
        			<ul>
                    <li><span>商品名称：${order.commodityName}</span><span>数量：${order.pCount}张</span><span>单价：${order.sPrice}元</span></li>
                    <li><span>订单总金额：${order.price}元</span>
                    	<span>支付方式：<c:if test="${order.payType == 1}">支付宝</c:if><c:if test="${order.payType == 0}">微信</c:if></span>
                    	<span>订购电话：${order.userPhone}</span>
                    	<span>状态：
                    		<c:if test="${order.status==1}">未支付</c:if>
                    		<c:if test="${order.status==2}">已支付</c:if>
                    		<c:if test="${order.status==3}">支付失败</c:if>
                    		<c:if test="${order.status==4}">订单已取消</c:if>
                    		<c:if test="${order.status==6}">完成订单</c:if>
                    		<c:if test="${order.status==7}">申请退款中</c:if>
                    		<c:if test="${order.status==8}">已退款</c:if>
                    	</span>
                    </li>
                    <li>
                    	<span>支付时间：<fmt:formatDate value="${order.payDate}" type="date" dateStyle="long"/></span>
                    	<span>使用时间：<fmt:formatDate value="${order.playTime}" type="date" dateStyle="long"/></span>
                    </li>
                    <ul class="shengming">
                        <li>★有效期：12月至2月/8：30--17：30</li>
                        <li>★费用说明：碧峰峡景区网络售票窗口，需带本人有效身份证件换票；</li>
                        <li>★优惠政策：<p>1、1.2米（不含）至1.4米（含）之间现场购买儿童特惠票；1.2米（含）以下儿童免费入馆；80岁（含）以上老人凭身份证免费进馆；</p></li>
                        <li>★注意：<p>1）此电子票需要专人专用，请勿直接持手抄电子码、拍照电子码、打印电子码等无效码到商家兑换；</p><p>2）凭身份证和手机短信到景区验证电子票，验证的证件信息需与客人提供的信息一致；</p><p>3）如有个别特殊项目，馆内商场、游艺、新项目等请按规定另行收费 ；</p></li>
                    </ul>
                    <li><a href="javascript:;" class="more">收起更多 -</a></li>
                </ul>
        		</li>
        	</c:forEach>
        </ul>
    </div>
</div>
<!----end-introduce---->
<!--footer-->
<script>
function cencleOrder(orderId){
	if(confirm("确定取消该订单吗")){
		var url = "${ctx}/order/updateStatus/"+orderId;
		$.ajax({
			url: url,
	        type: "get",
	        dataType: "json",
	        success: function (data) {
	        	if(data==1){
	        		window.location.reload();
	        	}else{
	        		alert("取消失败");
	        		return false;
	        	}
	        }
		})
	}
}

//退款
function refundFastpay(orderId, useDate, nowDate){
// 	alert(useDate > nowDate);
	if(confirm("确定申请退款吗")){
		if (useDate > nowDate) {
			var url = "${ctx}/order/refundFastpay/"+orderId;
			$.ajax({
				url: url,
		        type: "get",
		        dataType: "json",
		        success: function (data) {
		        	if(data==1){
		        		alert("申请退款成功，请耐心等待商家处理");
		        		window.location.reload();
		        	}else{
		        		alert("退款申请失败，请联系商家");
		        		return false;
		        	}
		        }
			})
		} else {
			alert("已过期的订单不能退款")
			return false;
		}
	}
}
</script>
<!--footer-ends-->
</body>
</html>