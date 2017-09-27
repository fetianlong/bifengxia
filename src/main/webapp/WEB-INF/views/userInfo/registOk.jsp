<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title> 注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="renderer" content="webkit">
    <!---- start-smoth-scrolling---->
    
</head>
<body>
<!----start-introduce---->
<div class="header" id="home" style="height:auto;padding:10px 0;text-align:left;">
    <div class="login_head">
        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="" class="login_logo"></a>
        <span>碧峰峡</span> <span>|</span> <span>注册</span>
    </div>
</div>
<!----end-header---->
<div class="findpwd_wrap">
  
        <div class="regi_ok">
        	<img src="${ctx}/static/img/ok.png" alt=""/>恭喜您，注册成功
        </div>
       <button class="lijilogin">立即登录</button>
  
</div>
<!----end-introduce---->
<script type="text/javascript">
$(document).ready(function () {
	$(".lijilogin").click(function () {
		window.location.href = "${ctx}/login";
	});
})
</script>
</body>
</html>