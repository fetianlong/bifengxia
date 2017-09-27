<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title> ${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
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
<div class="findpwd_wrap">
    <div class="regi_content">
        <p>选择支付方式</p>
        <form id="formId" action="${ctx}/pay/checkPay/" method="get" class="clearfix">
        	<input type="hidden" name="id" value="${orderId}" />
            <div style="border-bottom: 1px solid #ddd;">
                <label for="">
                    <input id="payType1" type="radio" name="payType" value="1" checked="checked"><img src="${ctx}/static/img/zhifubao.png" alt=""/>
                </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <label for="">
                    <input id="payType2" type="radio" name="payType" value="0"><img src="${ctx}/static/img/weixin.png" alt="" style="width: 130px;padding: 2px 10px;border: 2px solid #DBDCDC;"/>
                </label>
            </div>

            <button id="subId" type="button" class="f-r">立即支付</button>
        </form>
    </div>
</div>
<script src="${ctx}/static/js/layer.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function JSAPIsubmit(){
    $.ajax({
        type: 'POST',
        url: '${ctx}/pay/weixin_payJSAPI',
        data: {'id' : '${orderId}'},
        dataType: "json",
        success: function(data){
// 	        alert(data);
	        var appId = data.appId;
	        var timeStamp = data.time_expire;
	        var nonceStr = data.nonceStr;
	        var packages = data.packages;
	        var paySign = data.sign;
	        console.log("appId：" + appId);
	        console.log("timeStamp：" + timeStamp);
	        console.log("nonceStr：" + nonceStr);
	        console.log("packages：" + packages);
	        console.log("paySign：" + paySign);
	        WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":appId,     //公众号名称，由商户传入
                    "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数
                    "nonceStr":nonceStr, //随机串
                    "package":packages,
                    "signType":"MD5",         //微信签名方式：
                    "paySign":paySign //微信签名
                },
                function(res){
                    WeixinJSBridge.log(res.err_msg);
                    if(res.err_msg == "get_brand_wcpay_request:ok"){
                    	
                    	window.location.replace("${ctx}/order/paySuccess/");
                        <!--支付成功调用-->
                        <!--history.go(0);   -->
                        //alert("成功");
                    }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
                        <!--取消支付调用-->
                        //alert("取消");

                    }else{
                        <!--支付失败-->
                        //alert("失败");

                    }
                }
        	);
    	}
	});
}

$(function(){
	$("#subId").click(function(){
		var stype = '${registType}';
// 		var payType = $("#payType1").val();
		var payType = $("input[type='radio']:checked").val();
// 		alert(payType);
// 		alert($("#payType2").val());
		var url = "${ctx}/pay/checkPay?id=${orderId}&payType=" + payType;
		if(stype==2){
			JSAPIsubmit();
// 			$("#formId").attr("action", url);
// 			$("#formId").submit();
		}else{
			window.open(url, "_blank");
			layer.open({
		        type: 1
		        ,title: false //不显示标题栏
		        ,closeBtn: false
		        ,area: '300px;'
		        ,shade: 0.8
		        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
		        ,btn: ['支付成功', '支付失败']
		        ,moveType: 1 //拖拽模式，0或者1
		        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; ">是否支付成功</div>'
		        ,success: function(layero){
		          var btn = layero.find('.layui-layer-btn');
		          btn.css('text-align', 'center');
		          btn.find('.layui-layer-btn0').attr({
		            href: '${ctx}/order/list'
// 		            href: '${ctx}/order/payCheckSuccess/${orderId}'
// 		            ,target: '_blank'
		          });
		        }
		      });
// 			if (confirm("是否支付成功？")) {
// 				window.location.href = "${ctx}/paySuccess/${orderId}";
// 			}
// 			$("#formId").submit();
		}
	});
});
</script>
</body>
</html>