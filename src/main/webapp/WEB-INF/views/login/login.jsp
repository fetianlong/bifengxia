<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title> 登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit">

    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-header---->
<div class="top">
    <div class="container clearfix">
        <div class="login">
            <a href="${ctx}/registUser">注册</a> |
            <a href="${ctx}/login">登录</a>
        </div>
        <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/> 0835 - 231 8091</div>
    </div>
</div>
<div class="header" id="home" style="height:auto;padding:10px 0;text-align:left;">
    <div class="login_head">
        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="" class="login_logo"></a>
        <span>碧峰峡</span> <span>|</span> <span>登录中心</span>
    </div>
</div>
<!----end-header---->
<div class="login_wrap">
    <div class="login_content clearfix">
        <form id="loginForm" action="${ctx}/login" method="post" class="login_form clearfix">
            <h3>
                登录碧峰峡
                <a href="${ctx}/registUser" class="regi">立即注册</a>
            </h3>
            <label for=""><input type="text" id="username" name="username" class="required" placeholder="用户名"></label>
            <label for=""><input type="password" id="password" name="password" class="required" placeholder="密码"></label>
            <a href="${ctx}/registUser/findpwd" class="forget">忘记密码？</a>
            <button type="submit">登录
            <%
			String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			if(error != null){
			%>
				<span class="warn">登录失败</span>
			<%
			}
			%>
            
            </button>
            <h4><span>其他登录</span></h4>
            <div class="qita_login">
                <a href="javascript:;" onclick="otherLogin('3')" style="margin-right:20px;"><img src="${ctx}/static/img/denglu_qq.png" alt=""></a>
                <a href="javascript:;" onclick="otherLogin('2')"><img src="${ctx}/static/img/denglu_weixin.png" alt=""></a>
            </div>

        </form>
    </div>
</div>
    <!-- <div class="copy">
        <p>Copyright 2016 三亚金棕榈度假酒店 备案号：琼ICP备10001469号-2 技术支持： 北京邦格科技有限公司</p>
    </div> -->
<!--/footer-->
<script>
	function otherLogin(type){
		$.ajax({
			url: "${ctx}/login/getLoginUrl/"+type,
	        type: "get",
	        success: function (data) {
	        	window.location.href = data;
	        }
		})
	}
</script>
<!--footer-ends-->
</body>
</html>
