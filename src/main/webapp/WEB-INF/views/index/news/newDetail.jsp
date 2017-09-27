<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title> 新闻中心</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-details---->
<div class="details_box">
    <div class="details_inder">
        <span><a href="${ctx}/">首页</a></span> <span> > </span> <span><a href="${ctx}/index/36/news.html">新闻中心</a></span> <span> > </span> <span><a href="">正文详情</a></span>
    </div>
    <div class="details_bar clearfix">
        <div class="f-l f_t">
            <h1>${article.title}</h1>
            <div class="infor clearfix">
                <div class="f-l ma_l">
                    <span class="s_mr">发布日期：
                    <c:if test="${article.releaseType==0}">
                    	<fmt:formatDate value="${article.createDate}" type="date"/>
                    </c:if>
                    <c:if test="${article.releaseType==1}">
                    	<fmt:formatDate value="${article.releaseTime}" type="date"/>
                    </c:if>
                    </span>
                </div>
                <div class="f-r ma_r">
                    <span class="s_mrr">来源：${article.artiReource}</span>
                </div>
            </div>
            ${article.content}
        	<div class="toupiao">
        		<c:if test="${articleVote.id != null}">
	            	<p>【投票】${articleVote.chooseName}</p>
	            	
	            	<c:if test="${articleVote.chooseAName != ''}">
	            		<div class="tiaoxing">
            				<label>
		            		<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseAName}">checked="checked"</c:if>  value="chooseANum">${articleVote.chooseAName}
		            		</label>
		            		<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseANum}</span>
	            			</c:if>
		            	</div>
	            	</c:if>
	            	<c:if test="${articleVote.chooseBName != ''}">
	            		<div class="tiaoxing">
            				<label>
	            			<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseBName}">checked="checked"</c:if> value="chooseBName">${articleVote.chooseBName}
	            			</label>
	            			<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseBNum}</span>
	            			</c:if>
		            	</div>
            		</c:if>
	            	<c:if test="${articleVote.chooseCName != ''}">
	            		<div class="tiaoxing">
            				<label>
	            			<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseCName}">checked="checked"</c:if> value="chooseCName">${articleVote.chooseCName}
	            			</label>
	            			<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseCNum}</span>
	            			</c:if>
		            	</div>
	            	</c:if>
	            	<c:if test="${articleVote.chooseDName != ''}">
	            		<div class="tiaoxing">
            				<label>
	            			<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseDName}">checked="checked"</c:if> value="chooseDName">${articleVote.chooseDName}
	            			</label>
	            			<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseDNum}</span>
	            			</c:if>
		            	</div>
	            	</c:if>
	            	<c:if test="${articleVote.chooseEName != ''}">
	            		<div class="tiaoxing">
            				<label>
	            			<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseEName}">checked="checked"</c:if> value="chooseEName">${articleVote.chooseEName}
	            			</label>
	            			<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseENum}</span>
	            			</c:if>
		            	</div>
	            	</c:if>
	            	<c:if test="${articleVote.chooseFName != ''}">
	            		<div class="tiaoxing">
            				<label>
	            			<input type="radio" name="chooseNum" <c:if test="${voteChoose eq articleVote.chooseFName}">checked="checked"</c:if> value="chooseFName">${articleVote.chooseFName}
	            			</label>
	            			<c:if test="${voteChoose != ''}">
		            			<span class="tiaochang"></span>
	            				<span class="shu">${articleVote.chooseFNum}</span>
	            			</c:if>
		            	</div>
	            	</c:if>
	            	
	            	<input type="hidden" name="id" value="${articleVote.id}">
	            	<input type="hidden" name="articleId" value="${articleVote.articleId}">
	            	<c:if test="${voteChoose != ''}">
	            		<a href="javascript:;" class="yitou">投票</a>
	            	</c:if>
	            	<c:if test="${voteChoose == ''}">
	            		<a href="javascript:;" onclick="toupiao();" class="weitou">投票</a>
	            	</c:if>
	            </c:if>
			</div>
        </div>
        
        <div class="f-r r_t">
            <div class="text_box">
                <h5><a href="${ctx}/index/news.html">新闻中心</a></h5>
                <ul class="ul_t">
                	<c:forEach items="${xinwenList}" var="art">
                		<li><a href="${ctx}/index/news/${art.id}/newDetail.html">${art.title}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="text_box">
                <h5><a href="${ctx}/index/meiti.html">媒体报道</a></h5>
                <ul class="ul_t">
                	<c:forEach items="${meitiList}" var="art">
                		<li><a href="${ctx}/index/meiti/${art.id}/meitiDetail.html">${art.title}</a></li>
                	</c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
<!----end-details---->
<!--footer-->
<script>
$(function(){
	for(var i=0;i<$('.tiaochang').length;i++){
		var count=parseInt($('.tiaochang').eq(i).next().html());
		$('.tiaochang').eq(i).css('width',5*count+'px');
	}
})
function toupiao(){
	var val = $('input:radio[name="chooseNum"]:checked').val();
	if (val == null) {
		alert("请选择投票");
	}else{
		var url = "${ctx}/articleVote/update/${articleVote.id}";
		$.ajax({
			url: url,
	        type: "post",
	        data: {
	        	chooseNum : val
	        },
	        dataType: "json",
// 	        success: function (data) {
	        	
// 	        }
		});
// 		$("#formId").submit();
	}
}

</script>
<!--footer-ends-->
</body>
</html>