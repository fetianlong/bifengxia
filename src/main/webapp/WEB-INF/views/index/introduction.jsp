<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
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
    <!---- start-smoth-scrolling---->
</head>
<body>
<div class="introduce_box">
    <img src="${ctx}/static/img/bifengxiajianjie.png" alt="jianjie"/>
</div>
<div class="introduce_text_wrap">
    <div class="introduce_text">
		<h3 class="h3 clearfix"><div class="center"><span>碧峰峡简介</span></div></h3>
			        ${article.content}
			<!-- <p>
			雅安碧峰峡景区，位于四川雅安北部12公里处，距成都128公里，景区面积20平方公里，森林面积20000余亩，森林覆盖率达95%以上，素有“天府之肺”的美称，2001年被评为国家首批AAAA级旅游景区。景区由野生动物世界、生态峡谷景区和大熊猫基地三个部分组成，1998年由成都万贯（集团）置业股份有限公司投资修建，1999年12月28日正式向游人开放，开业当天迎来成雅高速正式开通，为景区带来了大量的人流和发展机遇。2015年12月，雅安市雨城区政府成功引进北京东方园林产业集团作为万贯集团战略合作伙伴投资控股碧峰峡景区，将采用“投资+实业”运作模式与“1+X”业态组合模式为碧峰峡注入发展的新活力。加之近年来，雅安市政府对交通投入力度加大，设施改善加快，伴随雅安作为川西南交通枢纽的确立，为碧峰峡景区的发展提供了强大动力。
			</p>
			<p>
			峡谷景区为两条峡谷组成，左峡长7公里，右峡长2.5公里，呈“Ｖ”字形，宽30-70米，海拔700-1971米，峡壁相对高度100-200米。植被、峡景和瀑布是碧峰峡景区的鲜明特色。峡内林木葱郁，苍翠欲滴，峰峦叠嶂，崖壑峥嵘。时而奇峰耸峙，高插蓝天，时而两山并合天光一线，多类型的瀑布景观，更使双峡增添无限景色，令人陶醉。鉴于碧峰峡极其优良的自然生态环境，中国保护大熊猫研究中心把基地设置在碧峰峡风景区内，2003年12月28日建成并正式对外开放，占地面积1074亩，集科研、繁殖、旅游多功能为一体，分为白熊坪、幼儿园和海归大熊猫乐园三个展示参观区。基地内现共有大熊猫60多只，是中国大熊猫的国家公园，也是明星熊猫的故乡。从赠台湾的“团团”、“圆圆”到旅美归来的“泰山”，无论是北京“奥运熊猫”宝宝还是上海“世博熊猫”，都成为了全世界的焦点。
			</p>
			<p>
			碧峰峡野生动物世界是目前四川唯一的野生动物乐园，也是全国第一家生态型野生动物园。园区规划面积10000亩，由车行猛兽观光区和温驯动物步行游览区和动物行为展示区组成，园内共放养各类野生动物近300种约10000余头（只、尾）。其中有东北虎、金丝猴、美洲豹等国家一级重点保护动物30余种，还有白狮、白虎等极品珍稀动物。
			</p> -->
    	<!-- <h3 class="h3 clearfix"><div class="center"><span>浏览路线</span></div></h3>
	    <h4 class="h4">动物园全程一般路线（游览参观+动物行为展示，约4小时）</h4>
	    <div class="luxian">
	    	<p>入口乘坐观光车（投食车）观看猛兽（非州狮、黑熊、东北虎）→动物幼儿园→和谐家园→花果山水帘洞→松鼠猴园→科普长廊→百鸟乐园→动物行为展示场→游乐中心→亚洲象行为展示场→动物纪念园→小观光车乘坐点→动物乐园→豹园→白狮白虎馆→科教馆→海狮行为展示场→生态蛇馆→游客中心</p>
	    </div>
	    <h4 class="h4">动物园半程精品路线（仅游览参观，约2小时）</h4>
	    <div class="luxian">
	    	<p>入口乘坐观光车（投食车）观看猛兽（非州狮、黑熊、东北虎）→和谐家园→花果山水帘洞→松鼠猴园→科普长廊→百鸟乐园→动物行为展示场→游乐中心→亚洲象行为展示场→动物纪念园→小观光车乘坐点→动物乐园→豹园→游客中心</p>
	    </div>
	    <h4 class="h4">风景区一般路线（熊猫园+左峡谷，约3至4小时）</h4>
	    <div class="luxian">
	    	<p>景区入口乘坐观光车至熊猫园→小西天→田翁桥→淘金滩→女娲池→天龙凌云→红灯笼→悬棺→十指补天峰→千层岩瀑布→青云梯→返回游客中心</p>
	    </div>
    	<h4 class="h4">风景区精品路线（熊猫园+右峡谷，约2至3小时）</h4>
	    <div class="luxian">
	    	<p>景区入口乘坐观光车至熊猫园→返回至福善桥→雅女园→观龙台→青龙潭瀑布→屏翠小瀑→鸳鸯瀑布→青云梯→返回游客中心</p>
	    </div>
	    <h4 class="h4">风景区全程路线（右峡谷+熊猫园+左峡谷，约4至5小时）</h4>
	    <div class="luxian">
	    	<p style="margin-bottom:70px;">青云梯→鸳鸯瀑布→屏翠小瀑→青龙潭瀑布→观龙台→雅女园→福善桥（乘坐观光车）→熊猫基地→小西天→田翁桥→淘金滩→女娲池→天龙凌云→红灯笼→悬棺→十指补天峰→千层岩瀑布→青云梯→返回游客中心</p>
	    </div> -->
    </div>
</div>
<!----end-introduce---->
<!--footer-->
<script>


</script>
<!--footer-ends-->
</body>
</html>