<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
    <title> 找回密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
    <script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
    <!---- start-smoth-scrolling---->
    <script type="text/javascript">
	$(document).ready(function () {
		$("#userInfo").validate({
			rules: {
				userName: {
					required: true,
					isMobile: true,
					remote : {
						url : "${ctx}/registUser/checkPhoneExist/1",
						type : "get",
						cache:false,
						dataType : "json",
						data : {
							userName: function() {
								return $("#userName").val();
							}
						}
					}
				},
				password: {
					required: true,
					minlength: 6
				},
				plainPassword: {
					required: true,
					minlength: 6,
					equalTo: "#password"
				}
			},
			messages: {
				userName: {
					required: "手机号不能为空",
					remote: "该手机号未注册"
	            },
	            password: {
	                required: "没有填写密码",
	                minlength: "密码不能小于{0}个字符"
	            },
	            plainPassword: {
	                required: "没有确认密码",
	                minlength: "确认密码不能小于{0}个字符",
	                equalTo: "两次输入密码不一致"
	            }
			}
		});
		$.validator.addMethod("isMobile", function(value, element) { 
		  var length = value.length; 
		  var mobile = /^1[3|4|5|7|8]\d{9}$/; 
		  return this.optional(element) || (length == 11 && mobile.test(value)); 
		}, "请正确填写您的手机号码");
		
		//发送验证码
		$(".send").click(function () {
			var phone = $("#userName").val();
			if(null != phone && phone !=''){
				countSecond(60,'.send_detailsend');
				$('.send_detail').css('display','block');
				$.ajax({
					url: "${ctx}/registUser/sendMeseege/1",
			        type: "get",
			        dataType: "json",
			        data: {
	                	mobilephone:phone
			        },
			        success: function (data) {
			        	
			        }
				})
			}
		});
		//验证码判断
		$("#regs").click(function () {
			var phone = $("#userName").val();
			var invCode = $("#invCode").val();
			if(null != invCode && invCode != ''){
				$.ajax({
					url: "${ctx}/registUser/isValitaCode",
			        type: "get",
			        dataType: "json",
			        data: {
		           		mobilephone:phone,
		           		vCode:invCode
			        },
			        success: function (data) {
		        		if(data){
		        			$("#userInfo").submit();
		        		}else{
		        			alert("验证码不匹配");
		        		}
			        }
				})
			}
		});
	});
	//计时器，n秒后消失
	function countSecond(second,obj){

		$(obj).html(second);

		if(--second>-1){

			 setTimeout("countSecond("+second+",'"+obj+"')",1000);

		}else{

			$(obj).parent().css('display','none');

		}

	}
	
	</script>
</head>
<body>
<!----start-introduce---->

<div class="top">
    <div class="container clearfix">
        <div class="login">
            <a href="${ctx}/registUser">注册</a> |
            <a href="${ctx}/login">登录</a>
        </div>
        <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/> 0835 - 231 8091 </div>
    </div>
</div>
<div class="header" id="home" style="height:auto;padding:10px 0;text-align:left;">
    <div class="login_head">
        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="" class="login_logo"></a>
        <span>碧峰峡</span> <span>|</span> <span>忘记密码</span>
    </div>
</div>
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="findpwd_wrap">
    <div class="findpwd_content">
        <form id="userInfo" action="${ctx}/registUser/findPwd" class="findpwd_form clearfix" method="post">
            <label for="">
                <span>手机号</span>
                <input type="text" id="userName" name="userName" placeholder="手机号">
            </label>
            <label for="">
                <span>验证码</span>
                <input type="text" id="invCode" name="invCode" placeholder="验证码">
                <a href="javascript:;" class="send">发送验证码</a>
                <a href="javascript:;" class="send_detail">发送中<span class="send_detailsend">60</span>s</a>
            </label>
            <label for="">
                <span>新密码</span>
                <input type="password" id="password" name="password" placeholder="新密码">
            </label>
            <label for="" style="margin-bottom:4px">
                <span>再输一次</span>
                <input type="password" id="plainPassword" name="plainPassword" placeholder="再输一次">
            </label>
            <label for="" class="accept_label">
                <input type="checkbox" checked="checked" disabled="disabled" style="width: auto;vertical-align: middle;margin-right:4px;"><a href="javascript:;" class="accept">我已阅读并同意《碧峰峡网站用户须知》</a>
            </label>
            <button id="regs" type="button">找回</button>

        </form>
    </div>
</div>
<!----end-introduce---->
</body>
</html>