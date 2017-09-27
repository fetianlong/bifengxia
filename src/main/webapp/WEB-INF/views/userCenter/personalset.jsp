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
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <script type="text/javascript">
    $(document).ready(function () {
		$("#userInfo").validate({
			rules: {
				phone: {
// 					required: true,
					isMobile: true,
// 					remote : {
// 						url : "${ctx}/registUser/checkPhoneExist/0",
// 						type : "get",
// 						cache:false,
// 						dataType : "json",
// 						data : {
// 							userName: function() {
// 								return $("#phone").val();
// 							}
// 						}
// 					}
				},
			},
// 			messages: {
// 				phone: {
// // 					required: "手机号不能为空",
// 					remote: "该手机号已经注册"
// 	            },
// 			}
		});
		$.validator.addMethod("isMobile", function(value, element) { 
		  var length = value.length; 
		  var mobile = /^1[3|4|5|7|8]\d{9}$/; 
		  return this.optional(element) || (length == 11 && mobile.test(value)); 
		}, "请正确填写您的手机号码");
		
		$("#usave").click(function () {
			$("#userInfo").submit();
		});
    });
    </script>
</head>
<body>
<!----start-introduce---->
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="border_zhongxin">
    <div class="zhongxin_bor">
        <div class="bor_gr">
            <span>个人中心</span>
        </div>
    </div>
</div>
<div class="bangzhu_box pad">
    <div class="bangzhu_text clearfix">
        <ul class="f-l lianxifangshi clearfix">
            <li class="tab active"><a href="${ctx}/userCenter/gotoUserCenter"><span>个人信息</span></a></li>
            <li class="tab"><a href="${ctx}/order/list"><span>历史订单</span></a></li>
        </ul>
    	<form id="userInfo" action="${ctx}/userCenter/upRegistUser" method="post">
        <ul class="f-r gerenxinxi tabitem active">
            <li class="geren_1">用户名 <input type="text" value="${registUser.userName}"  readonly="readonly"/></li>
            <li class="geren_1">手机号 <input type="text" id="phone" name="phone" value="${registUser.phone}"/></li>
            <li class="geren_3">性&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;
            <input type="radio" name="sex" value="1" <c:if test="${registUser.sex==1}"> checked="checked"</c:if>/><span>男</span><input type="radio" name="sex" value="0" <c:if test="${registUser.sex==0}"> checked="checked"</c:if>/><span>女</span></li>
            <li class="geren_1">居住地<input type="text" name="address" value="${registUser.address}"></li>
            <li class="geren_4"><input id="usave" type="button" value="保&nbsp;存" style="border:none;font-size:18px;border-radius:3px;"/></li>
        </ul>
        <input type="hidden" name="id" value="${registUser.id}">
    	</form>
    </div>
</div>
<!----end-introduce---->
</body>
</html>