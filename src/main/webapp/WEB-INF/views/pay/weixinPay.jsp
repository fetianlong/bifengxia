<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title> 美食与住宿</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-introduce---->

<div class="introduce_text_wrap" style="padding:100px 0;text-align:center;">
	<img src="${ctx}/pay/qr_codeImg?code_url=${urlCode}"/>
	<p style="margin-top:14px;">微信扫描图中二维码进行支付</p>
<!--     <img id="id_wxtwoCode" src="" style="display:block;width:105px;margin:0 auto;"/> -->
</div>
<!----end-introduce---->
<script type="text/javascript">
$(document).ready(function(){
	setInterval("getnums()", 1000);
});
var flag = 0;
function getnums() {
	$.ajax({
		url: "${ctx}/pay/queryOrder",
        type: "post",
        dataType: "json",
        data: {
        	orderPayId:'${payOrderId}',
        },
        success: function (data) {
        	if(data == 0 && flag == 0){
//         		var html = "<img src='${ctx}/pay/qr_codeImg?code_url=${urlCode}' style='display:block;width:105px;margin:0 auto;'/>";
//         		var html = "<img src='${ctx}/pay/qr_codeImg?code_url=${urlCode}'/><p style='margin-top:14px;'>微信扫描图中二维码进行支付</p>";
//         		$(".introduce_text_wrap").html(html);
        		flag = 1;
        	}else if(data == 1){
        		window.location.href = "${ctx}/order/paySuccess/${payOrderId}";
        	}
        }
	})

}
// $("#id_wxtwoCode").attr('src',"${ctx}/pay/qr_codeImg?code_url=${urlCode}");
</script>
</body>
</html>