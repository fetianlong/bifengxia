<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title> 支付成功</title>
 </head>
<body>
<div class="top">
    <div class="container clearfix" style="padding:0;">
        <div class="login">
    	<shiro:guest>
            <a href="${ctx}/registUser">注册</a> |
            <a href="${ctx}/login">登录</a>
        </shiro:guest>
        <shiro:user>
        	<a href="${ctx}/order/list"><shiro:principal property="realName"/></a>
        	<a href="${ctx}/logout">退出</a>
        </shiro:user>
        </div>
        <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/> 0835 - 231 8091</div>
     </div>
</div>
<!----start-introduce---->
<div class="header" id="home" style="height:auto;padding:10px 0;text-align:left;">
    <div class="login_head">
        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="" class="login_logo"></a>
        <span>碧峰峡</span> <span>|</span> <span>支付完成</span>
    </div>
</div>
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>

<!----end-header---->
<div class="zhifumingxi">
    <div class="zhifumingxi_warp">
        <div class="zhifuchenggong clearfix">
            <a class="f-l zhifuwancheng" href=""><img src="${ctx}/static/img/zhifu_duihao.png" alt="zhifuchenggong"/></a>
            <ul class="f-l">
                <li class="li1">
                	<%-- <c:if test="${order.status == 1}">
                		未支付
                	</c:if>
                	<c:if test="${order.status == 2}">
                		支付成功
                	</c:if> --%>
                	支付成功
                </li>
                <li class="li2"><span class="dingdanhao">订单号：${order.payOrderId}</span><span>在线支付：${order.price}元</span></li>
            	<li class="li2"><a href="${ctx}/" style="font-size:14px;color:#00b465;">完成并返回</a></li>
            </ul>
        </div>
        <div class="zhifuxiangqing">
            <ul class="ul1">
                <li>重点提示：已将订单号、兑换码，发送到您的订购手机，${order.userPhone}上，请于<fmt:formatDate value="${order.playTime}" type="date"/>持有效证件，至景区办理。</li>
                <li>客服电话：0835-2318077</li>
            </ul>
            <div class="ul2 clearfix">
                <ul class="f-l">
                    <li>商品名称：${order.commodityName}</li>
                    <li>订单总金额：${order.price}元</li>
                    
                    <li>使用日期：<fmt:formatDate value="${order.playTime}" type="date"/></li>
                    
                    <li>支付时间：<fmt:formatDate value="${order.payDate}" type="both" pattern="yyyy年MM月dd日 HH:mm:ss"/></li>
                </ul>
                <ul class="f-l">
                    <li>数量：${order.pCount}张</li>
                    <li>支付方式：<c:if test="${order.payType==1}">支付宝</c:if><c:if test="${order.payType==0}">微信</c:if></li>
<!--                     <li>退房日期：2016年10月3日</li> -->
                    <li></li>
                </ul>
                <ul class="f-l ull">
                    <li>单价：${order.sPrice}元</li>
                    <li>订购电话：${order.userPhone}</li>
                    <li></li>
                    <li></li>
                </ul>
                <ul class="f-l ulll">
                    <li></li>
                    <li>状态：已支付</li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <ul class="shixiang">
                <li>★有效期：<span>12月至2月/8：30--17：30</span></li>
                <li>★费用说明：<span>碧峰峡景区网络售票窗口，需带本人有效身份证件换票；</span></li>
                <li>★优惠政策：<span>1.2米（不含）至1.4米（含）之间现场购买儿童特惠票；1.2米（含）以下儿童免费入馆；80岁（含）以上老人凭身份证免费进馆；</span></li>
                <li>★注意：<p>1）此电子票需要专人专用，请勿直接持手抄电子码、拍照电子码、打印电子码等无效码到商家兑换；</p><p>2）凭身份证和手机短信到景区验证电子票，验证的证件信息需与客人提供的信息一致；</p><p>3）如有个别特殊项目，馆内商场、游艺、新项目等请按规定另行收费 ；</p></li>
                <li>★<span>请提前购买，当日购买次日生效。</span></li>
                <li>★温馨提示：<span>24小时客服：400-991-2990及189-1178-5249，本产品为电子票，购买成功后，系统会自动发送。</span></li>
            </ul>
        </div>
    </div>
</div>
<!----end-introduce---->
</body>
</html>