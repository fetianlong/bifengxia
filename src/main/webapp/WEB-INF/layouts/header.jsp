<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<script type="text/javascript">
 	
	//购票优惠
	var html5 = "";
	$.ajax({
		url: "${ctx}/index/getMenu/1",
        type: "get",
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i,val){
        		if(val.remark.indexOf("http")>=0){
        			html5 += "<li><a href='"+val.remark+"'  target='_blank'>" + val.name + "</a></li>"
        		}else{
					html5 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
        		}
// 				html5 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
			});
        	$("#index1").html(html5);
        }
	});
	
	//精彩体验
	var html4 = "";
	$.ajax({
		url: "${ctx}/index/getMenu/6",
        type: "get",
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i,val){
        		if(val.remark.indexOf("http")>=0){
        			html4 += "<li><a href='"+val.remark+"'  target='_blank'>" + val.name + "</a></li>"
        		}else{
					html4 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
        		}
// 				html4 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
			});
        	$("#index6").html(html4);
        }
	});
	
	//美食住宿
	var html3 = "";
	$.ajax({
		url: "${ctx}/index/getMenu/15",
        type: "get",
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i,val){
        		if(val.remark.indexOf("http")>=0){
        			html3 += "<li><a href='"+val.remark+"'  target='_blank'>" + val.name + "</a></li>"
        		}else{
					html3 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
        		}
// 				html3 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
			});
        	$("#index15").html(html3);
        }
	});
	
	//景区导览
	var html2 = "";
	$.ajax({
		url: "${ctx}/index/getMenu/23",
        type: "get",
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i,val){
//          		if(val.name=='虚拟导览'){
        		if(val.remark.indexOf("http")>=0){
        			html2 += "<li><a href='"+val.remark+"'  target='_blank'>" + val.name + "</a></li>"
        		}else{
					html2 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
        		}
			});
        	$("#index23").html(html2);
        }
	});
	
	//帮助
	var html1 = "";
	$.ajax({
		url: "${ctx}/index/getMenu/26",
        type: "get",
        dataType: "json",
        success: function (data) {
        	$.each(data, function(i,val){
        		if(val.remark.indexOf("http")>=0){
        			html1 += "<li><a href='"+val.remark+"'  target='_blank'>" + val.name + "</a></li>"
        		}else{
					html1 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
        		}
// 				html1 += "<li><a href='${ctx}/"+val.remark+"'>" + val.name + "</a></li>"
			});
        	$("#index26").html(html1);
        }
	});

	//导航搜索表单提示下拉菜单
	$('.search').keyup(function(){
		$('.sousuo_xiala').css('display','block');
		//ajax
		var keyword = $(this).val();
		var url = "${ctx}/keywordSearch/getkeyword/"+keyword;
		$.ajax({
			url: url,
	        type: "get",
	        dataType: "json",
	        success: function (data) {
	        	
	        	if(data!=null){
					var html = "";
	        		$.each(data, function(i,val){      
	        		      html += "<p>" + val + "</p>"
					});
	        		$('.sousuo_xiala').html(html);
	        		$('.sousuo_xiala p').click(function(){
	        			$('.search').val($(this).html());
	        			$('.sousuo_xiala').css('display','none');
	        			$('.search_form').submit();
	        		})
					document.onkeydown=function(event){
	        			
	                    var e = event || window.event || arguments.callee.caller.arguments[0];
	                    if(e && e.keyCode==38){
	                    	$('.search').trigger('blur');
	                    	var index = $(".sousuo_xiala p.hover").index();
	                    	if(!index){
	                    		index=0;
	                    	}
	                    	if(index == 0){
	                    		var length = $(".sousuo_xiala p").length;
	                    		$(".sousuo_xiala p").removeClass("hover");
	                    		$(".sousuo_xiala p").eq(length-1).addClass("hover");
	                    	}else{
	                    		$(".sousuo_xiala p.hover").removeClass("hover");
	                    		$(".sousuo_xiala p").eq(index-1).addClass("hover");
	                    	}
	                    
	                    }
	                    if(e && e.keyCode==40){
	                    	$('.search').trigger('blur');
	                    	var index = $(".sousuo_xiala p.hover").index();
	                    	
	                    	if(!index){
	                    		index=0;
	                    	} 
	                    	
	                    	var length = $(".sousuo_xiala p").length;
	                    	if(index == length-1){
	                    		$(".sousuo_xiala p").removeClass("hover");
	                    		$(".sousuo_xiala p").eq(0).addClass("hover");
	                    	}else{
	                    		$(".sousuo_xiala p.hover").removeClass("hover");
	                    		$(".sousuo_xiala p").eq(index+1).addClass("hover");
	                    	}
	                    
	                    }
	                    if(e && e.keyCode==13){
	                    	$('.search').val($('.sousuo_xiala p.hover').html());
		        			$('.sousuo_xiala').css('display','none');
		        			$('.search_form').submit();
	                    }
	                }
	        	}
	        }
		})
		
	})
	</script>	
	<!----start-header---->
    <c:if test="${isEnglish==1}">
	<div class="top">
        <div class="container clearfix" style="padding:0;">
            <div class="login" style="width:160px;">
        	<shiro:guest>
                <a href="${ctx}/registUser">Registration</a> |
                <a href="${ctx}/login">Log-in</a>
            </shiro:guest>
            <shiro:user>
            	<a href="${ctx}/order/list"><shiro:principal property="realName"/></a>
            	<a href="${ctx}/logout">Logout</a>
            </shiro:user>
            </div>
            <div class="phone_icon" style="margin-right:40px;"><img src="${ctx}/static/img/phone_icon.png" alt=""/>服务咨询电话  0835 - 231 8091 </div>
           </div>
    </div>
		
	</c:if>
   <c:if test="${isEnglish==2}">
   	<div class="top">
        <div class="container clearfix" style="padding:0;">
            <div class="login">
        	<shiro:guest>
                <a href="${ctx}/registUser">ログオン</a> |
                <a href="${ctx}/login">登録</a>
            </shiro:guest>
            <shiro:user>
            	<a href="${ctx}/order/list"><shiro:principal property="realName"/></a>
            	<a href="${ctx}/logout">退出</a>
            </shiro:user>
            </div>
            <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/> 0835 - 231 8091</div>
         </div>
    </div>
   </c:if> 	
   <c:if test="${isEnglish==3}">
   	<div class="top">
        <div class="container clearfix" style="padding:0;">
            <div class="login">
        	<shiro:guest>
                <a href="${ctx}/registUser">등록</a> |
                <a href="${ctx}/login">로그인</a>
            </shiro:guest>
            <shiro:user>
            	<a href="${ctx}/order/list"><shiro:principal property="realName"/></a>
            	<a href="${ctx}/logout">退出</a>
            </shiro:user>
            </div>
            <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/> 0835 - 231 8091</div>
         </div>
    </div>
   </c:if>	
   <c:if test="${isEnglish==null}">
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
            <div class="phone_icon"><img src="${ctx}/static/img/phone_icon.png" alt=""/>服务咨询电话： 0835 - 231 8091</div>
         </div>
    </div>
    <div class="header" id="home">
        <div class="xiala_opacity"></div>
		<div class="container">
			<div class="navigation clearfix">
			 <span class="menu"></span> 
				<ul class="navig clearfix">
					<li class="f-l">
                        <a class="active" href="${ctx}/">首页</a>
                    </li>
					<li class="f-l">
                        <a href="javascript:;">预定服务</a> 
                        <ul id="index1" class="xiala">
                        	<%-- <c:forEach items="${gpyhList}" var="list">
                        		<c:choose>
                        			<c:when test="${fn:contains(list.remark,'http')}">
                        				<li><a href="${list.remark}" target='_blank'>${list.name}</a></li>
                        			</c:when>
                        			<c:otherwise>
                        				<li><a href="${ctx}/${list.remark}">${list.name}</a></li>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach> --%>
                            <%-- <li><a href="${ctx}/index/2/introduction.html">景区简介</a></li>
                            <li><a href="${ctx}/commodity/list/0">门票预订</a></li>
                            <li><a href="${ctx}/commodity/list/2">酒店预订</a></li>
                            <li><a href="${ctx}/commodity/list/1">餐饮预订</a></li>
                            <li><a href="${ctx}/commodity/list/3">商品预订</a></li>
                            <li><a href="${ctx}/index/32/yhhuodong.html">优惠活动</a></li> --%>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/6/experience.html">精彩体验</a>
                        <ul id="index6" class="xiala">
                        	<%-- <c:forEach items="${jctyList}" var="list">
                        		<c:choose>
                        			<c:when test="${fn:contains(list.remark,'http')}">
                        				<li><a href="${list.remark}" target='_blank'>${list.name}</a></li>
                        			</c:when>
                        			<c:otherwise>
                        				<li><a href="${ctx}/${list.remark}">${list.name}</a></li>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach> --%>
                            <%-- <li><a href="${ctx}/index/8/mengshou.html">猛兽喂养</a></li>
                            <li><a href="${ctx}/index/9/xiongmao.html">明星熊猫</a></li>
                            <li><a href="${ctx}/index/10/fly.html">低空飞行</a></li>
                            <li><a href="${ctx}/index/11/shangxi.html">美景赏析</a></li>
                            <li><a href="${ctx}/index/33/juchang.html">欢乐剧场</a></li>
                            <li><a href="${ctx}/index/34/jiayuan.html">和谐家园</a></li> --%>
                        </ul>
                    </li>
                    
					<li class="f-l">
                        <a href="${ctx}/index/15/meishizhusu.html">美食住宿</a>
                        <ul id="index15" class="xiala" style="border-right:1px solid #ddd;">
                        	<%-- <c:forEach items="${mszsList}" var="list">
                        		<c:choose>
                        			<c:when test="${fn:contains(list.remark,'http')}">
                        				<li><a href="${list.remark}" target='_blank'>${list.name}</a></li>
                        			</c:when>
                        			<c:otherwise>
                        				<li><a href="${ctx}/${list.remark}">${list.name}</a></li>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach> --%>
                            <%-- <li><a href="${ctx}/index/16/bieshu.html">悬崖别墅</a></li>
                            <li><a href="${ctx}/index/17/jiudian.html">度假酒店</a></li>
                            <li><a href="${ctx}/index/19/canting.html">碧峰餐厅</a></li>
                            <li><a href="${ctx}/index/20/chalou.html">云海茶楼</a></li> --%>
                        </ul>
                    </li>
                    <li class="logo f-l">
                        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="logo" class="logo_icon"></a>
                    </li>
					<li class="f-l">
                        <a href="javascript:;">景区导览</a>
                        <ul id="index23" class="xiala">
                        	<%-- <c:forEach items="${jqdlList}" var="list">
                        		<c:choose>
                        			<c:when test="${fn:contains(list.remark,'http')}">
                        				<li><a href="${list.remark}" target='_blank'>${list.name}</a></li>
                        			</c:when>
                        			<c:otherwise>
                        				<li><a href="${ctx}/${list.remark}">${list.name}</a></li>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach> --%>
                            <%-- <li><a href="${ctx}/index/24/daolantu.html">导览图</a></li>
                            <li><a href="${ctx}/index/25/jiaotongxinxi.html">交通信息</a></li>
                            <li><a href="http://www.expoon.com/14467/panorama?from=groupmessage&isappinstalled=0" target="_blank">虚拟导览</a></li> --%>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/26/bangzhu.html">帮助</a>
                        <ul id="index26" class="xiala" style="border-right:1px solid #ddd;">
                        	<%-- <c:forEach items="${bzList}" var="list">
                        		<c:choose>
                        			<c:when test="${fn:contains(list.remark,'http')}">
                        				<li><a href="${list.remark}" target='_blank'>${list.name}</a></li>
                        			</c:when>
                        			<c:otherwise>
                        				<li><a href="${ctx}/${list.remark}">${list.name}</a></li>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach> --%>
                            <%-- <li><a href="${ctx}/index/27/youkefuwu.html">游客服务</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">联系电话</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">微信</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">微博</a></li> --%>
                        </ul>
                    </li>

				</ul>
				<p class="language">
					<a href="${ctx}/index/korean.html" class="f-l">한국어</a>
					<a href="${ctx}/index/japanese.html" class="f-l">日本語</a>
					<a href="${ctx}/index/english.html" class="f-l">English</a>
				</p>
                <form action="${ctx}/keywordSearch/search.html" class="search_form" method="get">
                
                    <label>
	                    <input type="text" class="search" autocomplete="off" name="searchKey" value="${searchKey}" placeholder="请输入关键字"/>
	                    <div class="sousuo_xiala">
	                    	<!-- <P>111</P>
	                    	<P>111</P>
	                    	<P>111</P> -->
	                    </div>
                    </label>
                    <button type="submit"></button>
                </form>
			</div>

		</div>
	</div>
   </c:if>
	
	<!----end-header---->
	<script src="${ctx}/static/js/swiper.min.js"></script>
    <script src="${ctx}/static/js/style.js"> </script>