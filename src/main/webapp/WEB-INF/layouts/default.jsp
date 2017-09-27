<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ include file="/WEB-INF/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>碧峰峡<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/img/favicon.ico" rel="shortcut icon">
<link rel="stylesheet" href="${ctx}/static/css/swiper.min.css">
<link href="${ctx}/static/css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="${ctx}/static/css/style.css" rel='stylesheet' type='text/css' />
<link href="${ctx}/static/css/reset.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="${ctx}/static/css/content.css" type="text/css"/>

<script src="${ctx}/static/js/jquery-1.11.0.min.js"> </script>


<sitemesh:head/>
</head>

<body>
	<div id="tcbg"></div>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
	
	<div>
		<sitemesh:body/>
	</div>
	<%@ include file="/WEB-INF/layouts/footer.jsp"%>
<%-- 	<script type="text/javascript" src="http://pv.sohu.com/cityjson?ie=utf-8"></script>  
 <script src="${ctx}/static/js/BehavioralData/BehavicategoryData.js" type="text/javascript" charset="utf-8"></script> </body> --%>
</html>
<script>
	$.ajax({
		url: "${ctx}/index/getFriendly",
	    type: "get",
	    dataType: "json",
	    success: function (data) {
	    	var html = "友情链接："
	    	$.each(data, function(i, k) {
	    		html += "<a href='"+k.codeValue+"' target='_blank'>" + k.name + "</a>";
	    	});
	    	$(".yqlj").html(html);
	    }
	})

</script>