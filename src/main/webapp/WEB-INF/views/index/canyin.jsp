<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
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
<div class="introduce_text_wrap" style="padding-bottom:20px;">
    <div class="introduce_text" >
        <h3 class="h3 clearfix" style="margin-bottom:10px;">
            <div class="center"><span>${title}</span></div>
        </h3>
        <c:forEach items="${commodityList}" var="commodity" varStatus="commoStatus">
        
        	<c:if test="${commoStatus.first==true}">
        <div class="clearfix yuding">
        	<a href="javascript:;" class="shensuo">收起 -</a>
        	</c:if>
        	<c:if test="${commoStatus.first==false}">
        <div class="clearfix yuding shou">
        	<a href="javascript:;" class="shensuo">详情 +</a>
        	</c:if>
            
            
            <div class="f-l yuding_l">
                <h3>${commodity.name}</h3>
                <img src="${commodity.picUrl}" alt=""/>
            </div>
            <div class="f-r yuding_r">
                <h3>市场价&nbsp;&nbsp;&nbsp;&nbsp;<span id="jiageyuanjia${commodity.id}" class="yuanjia">¥${commodity.oldPrice}</span>官网价&nbsp;&nbsp;&nbsp;&nbsp;<span id="jiagexianjia${commodity.id}" class="xianjia" style="font-size:20px;">¥${commodity.price}元</span></h3>
                <p class="jieshao">${commodity.remark}</p>
                <form id="yudingId" action="${ctx}/order/addOrder" method="post">
                    <label>
                        <span>票种</span>
                        <c:forEach items="${commodity.commodityPrice}" var="comp" varStatus="status">
                        	<a id="${commodity.id}-${comp.id}" href="javascript:changePiao(${commodity.id},${comp.id});" <c:if test="${status.first==true}">class="kind active"</c:if><c:if test="${status.first==false}">class="kind"</c:if>>${comp.name}</a>
                        </c:forEach>
                    </label>
                    <label>
                        <span>入园时间</span>
                        <input type="text" name="toDate" class="select_date" readonly/>
                    </label>
                    <label>
                        <span>数量</span>
                        <a class="jian" href="javascript:;">-</a><input type="text" name="priceCount" class="count" value="1"/><a class="jia" href="javascript:;">+</a>
                    </label>
                    <input type="hidden" id="commodityId" name="commodityId" />
                    <input type="hidden" id="commodityPriceId" name="commodityPriceId" />
                    <shiro:guest>
                    <button id="nowyuding" onclick="nowYuding(${commodity.id},0);" type="button">立即预定</button>
                    </shiro:guest>
                    <shiro:authenticated>
                    <button id="nowyuding" onclick="nowYuding(${commodity.id},1);" type="button">立即预定</button>
                    </shiro:authenticated>
                </form>
            </div>
            <div class="jieshao" style="margin-bottom:30px;">
                <h3 class="jieshao_h3">酒店简介</h3>
                <p>${commodity.introduction}</p>
            </div>
            <div class="sheshis clearfix">
                <div class="sheshi f-l">
                    <img src="${ctx}/static/img/tingchechang.png" alt=""/>
                    <p>停车场</p>
                </div>
                <div class="sheshi f-l">
                    <img src="${ctx}/static/img/wifi.png" alt=""/>
                    <p>wifi</p>
                </div>
                <div class="sheshi f-l">
                    <img src="${ctx}/static/img/canting.png" alt=""/>
                    <p>餐厅</p>
                </div>
                <div class="sheshi f-l">
                    <img src="${ctx}/static/img/wc.png" alt=""/>
                    <p>wc</p>
                </div>
                <div class="sheshi f-l">
                    <img src="${ctx}/static/img/xiuxishi.png" alt=""/>
                    <p>休息室</p>
                </div>
            </div>
        </div>
		</c:forEach>
    </div>
</div>
<!----end-introduce---->
<!--footer-->
<form id="loginForm" action="${ctx}/index/ajaxLogin" method="post" class="login_form clearfix" style="float:none;display:none;margin-top:0;">
            <h3>
                登录碧峰峡
                <a href="${ctx}/registUser" class="regi">立即注册</a>
            </h3>
            <%-- <div class="dlk">
			<%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if(error != null){
			%>
				<label style="font-size:9pt;color:#FF0000;text-align:center;">
						登录失败，请重试.&nbsp;
				</label>
			<%
			}
			%> --%>
            <label for=""><input type="text" id="username" name="username" class="required" placeholder="用户名"></label>
            <label for=""><input type="password" id="password" name="password" class="required" placeholder="密码"></label>
            <a href="" class="forget">忘记密码？</a>
            <button id="loginSubmitPost" type="button">登录
			<%-- <%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if(error != null){
			%>
				<span class="warn">登录失败</span>
			<%
			}
			%> --%>
			<span class="warn"></span>
			</button>
            <h4><span>其他登录</span></h4>
            <div class="qita_login">
                <a href="loginSubmit" style="margin-right:20px;"><img src="${ctx}/static/img/denglu_qq.png" alt=""></a>
                <a href=""><img src="${ctx}/static/img/denglu_weixin.png" alt=""></a>
            </div>

        </form>
<script src="${ctx}/static/js/moment.js"></script>
<script src="${ctx}/static/js/daterangepicker.js"></script>
<script src="${ctx}/static/js/layer.js" type="text/javascript" charset="utf-8"></script>
<script>
    $('.select_date').daterangepicker({
        singleDatePicker: true,
        format:'YYYY-MM-DD'
    });
    var comId = '${comId}';
    var compId = '${compId}';
	function changePiao(pid,id){
		comId = pid;
		compId = id;
		$.ajax({
			url: "${ctx}/commodity/changePrice/"+id,
	        type: "get",
	        dataType: "json",
	        success: function (data) {
	        	$("#jiageyuanjia"+pid).html(data.oldPrice);
	        	$("#jiagexianjia"+pid).html(data.newPrice);
	        }
		})
	}
	
	function nowYuding(pid,type){
		$("#commodityPriceId").val(compId);
		$("#commodityId").val(pid);
		if(type==1){
			$("#yudingId").submit();
		}else{
			layer.open({
	            type: 1,
	            title: false,
	            closeBtn: 1,
	            area: '440px',
	            skin: 'layui-layer-nobg', //没有背景色
	            /*shadeClose: true,*/
	            content: $('#loginForm')
	        });
		}
		
	}
	//登录
	$("#loginSubmitPost").click(function () {
		var uname = $("#username").val();
		var pwd = $("#password").val();
		if(null == uname || uname == ''){
			$(".warn").html("请输入用户名");
			return false;
		}
		if(null == pwd || pwd == ''){
			$(".warn").html("请输入用户名");
			return false;
		}
		$("#loginForm").submit();
// 		window.location.href = "${ctx}/index/ajaxLogin";
		/* $.ajax({
			url: "${ctx}/index/ajaxLogin",
	        type: "post",
	        dataType: "json",
	        data: {
	        	username:uname,
	        	password:pwd,
	        },
	        success: function (data) {
	        	if(data.success){
	        		window.location.reload();
	        	}else if(data.success == false){
	        		$(".warn").html(data.message);
	        	}
	        }
		}) */
	});
</script>
<!--footer-ends-->
</body>
</html>