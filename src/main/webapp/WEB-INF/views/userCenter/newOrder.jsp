<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title> 我的订单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/layer.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/bootstrap-datetimepicker.min.css">
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
    <!---- start-smoth-scrolling---->
    
</head>
<body>
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="introduce_text" >
        <h3 class="h3 clearfix">
            <div class="center"><span>我的订单</span></div>
        </h3>
<%-- 		<c:if test="${isflag == 1}"> --%>
			<form id="orderForm" action="${ctx}/order/addOrder" method="post">
	        <ul class="dingdan">
<%-- 	            <h3>订单号：${newOld.payOrderId}</h3> --%>
	            <li class="clearfix">
	                <div class="xiangmu f-l">商标名称</div>
	                <div class="jiage f-l">
	                    <p class="f-l danjia">单价</p>
						 <p class="f-l shuliang">${dictionary.remark}</p>
	                    <%-- <c:if test="${ctype != 2}">
	                        <p class="f-l shuliang">数量</p>
                        </c:if>
                        <c:if test="${ctype == 2}">
	                        <p class="f-l shuliang">天数</p>
                        </c:if> --%>
	                    <p class="f-l zongjia">金额</p>
	                </div>
<!-- 	                <div class="caozuo f-l">操作</div> -->
	            </li>
	            <li class="clearfix">
	                <div class="xiangmu f-l xiaop">${comName}</div>
	                <div class="jiage f-l clearfix xiaop">
	                    <p class="f-l danjia">¥ <span>${price}</span></p>
	                    <p class="f-l shuliang ">
	                        <a class="jian" href="javascript:;">-</a><input type="text" name="pCount" class="count" value="${priceCount}" readonly="readonly"/><a class="jia" href="javascript:;">+</a>
	                    </p>
	                    <p class="f-l zongjia">¥ <span>${price * priceCount}</span></p>
	                </div>
<%-- 	                <div class="caozuo f-l xiaop"><a id="delOrder" href="javascript:delOrder('${newOrder.id}');">删除</a></div> --%>
	            </li>
	            <li class="clearfix">
	                <label for=""><span>姓名</span><input type="text" id="payUserName" name="payUserName" value="${newOrder.payUserName}"/><strong style="color: red;">*</strong></label>
	                <label for=""><span>手机</span><input type="text" id="userPhone" name="userPhone" value="${newOrder.userPhone}"/><strong style="color: red;">*</strong></label>
	                <label for=""><span>身份证</span><input type="text" id="payCardNo" name="payCardNo" value="${newOrder.payCardNo}"/><strong style="color: red;">*</strong></label>
<%-- 	                <label for=""><span>取票人姓名</span><input type="text" id="ticketsName" name="ticketsName" value="${newOrder.ticketsName}"/></label> --%>
<%-- 	                <label for=""><span>取票人手机</span><input type="text" id="ticketsPhone" name="ticketsPhone" value="${newOrder.ticketsPhone}"/></label> --%>
	                <label for="">
	                	<span>${dictionary.parentCode}</span>
	                <%-- <c:if test="${ctype == 0}">
                     <span>游玩时间 </span>
                    </c:if>
                    <c:if test="${ctype == 2}">
                     <span>入店日期 </span>
                    </c:if>
                    <c:if test="${ctype == 1}">
                     <span>用餐时间 </span>
                    </c:if> --%>
<!-- 	                <input type="text" id="toDate" name="playTime" class="select_date" value=""/> -->
	                <input type="text" id="toDate" name="playTime" class="select_date" value="${tdate}"/><strong style="color: red;">*</strong>
	                </label>
<%-- 	                <label for=""><span>游玩时间</span><input style="border:none;" readonly type="text" value="<fmt:formatDate value='${newOrder.playTime}' type='date'/>"/></label> --%>
	            </li>
	        </ul>
	        <div class="jiesuan">
	            总金额 <span style="font-size: 14px">¥</span><span class="sum">${allPrice}</span><a href="javascript:;" onclick="payChange();">结算</a>
	        </div>
	        <input type="hidden" name="sPrice" value="${price}" />
	        <input type="hidden" name="payCommodityId" value="${commodityId}" />
	        <input type="hidden" name="comPid" value="${commodityPriceId}" />
	        <input type="hidden" name="commodityName" value="${comName}" />
	        
	        </form>
<%--         </c:if> --%>
<%--         <c:if test="${isflag == 0}"> --%>
<!--         	<p style="height:40px;line-height:40px;font-size:24px;text-align:center;border-top:1px solid #ddd;">没有订单</p> -->
<%--         </c:if> --%>
    </div>
</div>
<!----end-introduce---->
<!--footer-->

<!--footer-ends-->
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<%-- <script src="${ctx}/static/js/moment.js"></script> --%>
<%-- <script src="${ctx}/static/js/daterangepicker.js"></script> --%>
<script src="${ctx}/static/js/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/static/js/bootstrap-datetimepicker.js"></script>
<script src="${ctx}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">

	$('.select_date').datetimepicker({
		language: 'zh-CN',
		format:'yyyy-mm-dd',
		startDate:new Date(),
		todayHighlight: true,	//如果为true, 高亮当前日期。
		autoclose: true,
		minView:2
	});
    
	function delOrder(oid){
		if(confirm("确定删除该订单吗？")){
			var url = '${ctx}/order/deleteOrder/'+oid;
			window.location.href = url;
		}4 
	}
	function payChange(){
		var playTime = $("#toDate").val();
		var dname=$('#payUserName').val();
		var qname=$('#ticketsName').val();
		var dphone=$('#userPhone').val();
		var qphone=$('#ticketsPhone').val();
		var zj=parseInt($('#payCardNo').val());
		var mobile = /^1[3|4|5|7|8]\d{9}$/; 
		var regIdCard=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		
// 		alert(playTime);
		if(null != playTime && playTime != ''){
			var d = new Date(Date.parse(playTime.replace(/-/g,"/")));
			var curDate = new Date();
	// 		alert(d + "====" + curDate);
			if(d<curDate){
				layer.msg("日期必须大于今天！");
				return false;
			}
		}else{
			layer.msg("请选择时间");
			return false;
		}
		
	    if(!dname){
	    	layer.msg('姓名不能为空');
	    	return false;
	    }
	    if(!dphone||!mobile.test(dphone)){
	    	layer.msg('手机号错误');
	    	return false;
	    }
	    if(!zj || !regIdCard.test(zj)){
	    	layer.msg('身份证号码错误');
	    	return false;
	    }
	    /* if(!qname){
	    	layer.msg('取票人姓名不能为空');
	    	return false;
	    }
	    if(!qphone||!mobile.test(qphone)){
	    	layer.msg('取票人电话错误');
	    	return false;
	    } */
		
		$("#orderForm").submit();
	}
</script>
</body>
</html>