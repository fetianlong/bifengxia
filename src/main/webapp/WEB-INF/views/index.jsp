<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title> ${title}</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="${keywords}" />
<meta name="description" content="${Description}" />
    <style>
	    body.lock {
			background: url("${ctx}/static/bootstrip/image/u0.jpg") no-repeat top
				center fixed;
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
		}
        .swiper-container1 {
            width: 100%;
            height: 100%;

        }
        .swiper-slide1 {
            text-align: center;
            font-size: 18px;
            background: #fff;

            /* Center slide text vertically */
            display: -webkit-box;
            display: -ms-flexbox;
            display: -webkit-flex;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            -webkit-justify-content: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            -webkit-align-items: center;
            align-items: center;
        }
.swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{bottom:40px;}
        .swiper-pagination1{ background: rgba(0,0,0,0.6);height: 30px;line-height: 34px;display:inline-block;width: auto !important;margin-left: -48px;left: 50% !important;border-radius: 14px;}
        .swiper-pagination1 .swiper-pagination-bullet{width:16px;height:16px;background:#fff;opacity:1;margin:0 8px !important;}
        .swiper-pagination1 .swiper-pagination-bullet-active{background:#f39800;}

        .swiper-pagination2{}
        .swiper-pagination2 .swiper-pagination-bullet{width:16px;height:16px;background:#aaa;width:34px;height:10px;border-radius:0;}
        .swiper-pagination2 .swiper-pagination-bullet-active{background:#f39800;}
        .swiper-button-next2 {top:100px;background:url("${ctx}/static/img/next.png") no-repeat center center;width:60px;height:60px;}
        .swiper-button-prev2 {top:100px;background:url("${ctx}/static/img/prev.png") no-repeat center center;width:60px;height:60px;}
        .swiper-slide a{display:block;width:100%;margin-bottom:-2px;position:relative;}
        .swiper-slide a img{width:100%;}
        @media (max-width:1200px) {
            .swiper-pagination1{ background: rgba(0,0,0,0.6);height: 20px;line-height: 20px;border-radius: 10px;}
            .swiper-pagination1 .swiper-pagination-bullet{width:8px;height:8px;background:#fff;opacity:1;margin:0 5px;}
        }
        @media (max-width:480px) {
            .swiper-container-horizontal>.swiper-pagination-bullets, .swiper-pagination-custom, .swiper-pagination-fraction{bottom:0px;}
        }
        
    </style>

<!---- start-smoth-scrolling---->
</head>
<body class="lock">
	<!--[if lte IE 8]>
	<style type="text/css">
	.edition{
		position:fixed;
		bottom:0;
		left:0;
		width:100%;
		height:100px;
		background:#f5f6b4;
	}    
	.editionBox{
		width:1000px;
		height:56px;
		margin:22px auto;
	}    
	.editionTitle{
		float:left;
		width:430px;
		font:14px/28px "微软雅黑";
		color:#333;
	}
	.editionBrowser{
		float:right;
		width:auto;
	}
	.editionBrowser dl{
		float:left;
		width:60px;
		text-align:center;
		margin:0;
		margin-right:30px;
	}
	.editionBrowser dl:last-child{
		margin-right:0;
	}
	.editionBrowser dl dd{
		font:14px/28px "微软雅黑";
		color:#7f7f7f;
		margin-left:0;
		text-align:center;
	}
	</style>
	<div id="errorie">
		<div class="edition">
	    <div class="editionBox">
	    	<div class="editionTitle">
	    		温馨提示：您当前的浏览器版本过低，可能导致网站不能正常访问！<br />
				为了您能正常使用网站功能，请使用这些浏览器。
	    	</div>
	    	<div class="editionBrowser">
	    	  <dl>
	    	   <dt><img alt="chrome" src="${ctx}/static/bootstrip/image/chrome.png"></dt>
	    	   <dd>chrome</dd>
	    	  </dl>
	    	  <dl>
	    	   <dt><img alt="firefox" src="${ctx}/static/bootstrip/image/firefox.png"></dt>
	    	   <dd>firefox</dd>
	    	  </dl>
	    	  <dl>
	    	   <dt><img alt="360" src="${ctx}/static/bootstrip/image/sanliuling.png"></dt>
	    	   <dd>360</dd>
	    	  </dl>
	    	  <dl>
	    	   <dt><img alt="搜狗" src="${ctx}/static/bootstrip/image/sougou.png"></dt>
	    	   <dd>搜狗</dd>
	    	  </dl>
	    	  <dl>
	    	   <dt><img alt="edge" src="${ctx}/static/bootstrip/image/edge.png"></dt>
	    	   <dd>edge</dd>
	    	  </dl>
	    	  <dl>
	    	   <dt><img alt="IE" src="${ctx}/static/bootstrip/image/ie.png"></dt>
	    	   <dd>IE9+</dd>
	    	  </dl>
	    	</div>
	    </div>
	    </div>
	</div>
	<![endif]-->
	<!--banner-starts-->
	<div class="banner">
        <div class="swiper-container swiper-container1">
            <div class="swiper-wrapper">
            	<%-- 
            	<c:forEach items="${listBanner}" var="listB">
            		<div class="swiper-slide swiper-slide1"><a href=""><img src="${listB.pathUrl}" alt=""/></a></div>
            	</c:forEach> 
            	 --%>
                 <%----%>
                <div class="swiper-slide swiper-slide1"><a href=""><img src="${ctx}/static/img/sy_banner.png" alt=""/></a></div>
                <div class="swiper-slide swiper-slide1"><a href=""><img src="${ctx}/static/img/sy_banner1.png" alt=""/></a></div>
                <div class="swiper-slide swiper-slide1"><a href=""><img src="${ctx}/static/img/sy_banner2.png" alt=""/></a></div>
                <div class="swiper-slide swiper-slide1"><a href=""><img src="${ctx}/static/img/sy_banner3.png" alt=""/></a></div>
                <div class="swiper-slide swiper-slide1"><a href=""><img src="${ctx}/static/img/sy_banner4.png" alt=""/></a></div>
               
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination swiper-pagination1"></div>
            <!-- Add Arrows -->
        </div>
	</div>	
	<!--banner-ends-->
    <div class="tese_wrap">
        <div class="container">
            <ul class="teselist clearfix">
                <li>
                    <a href="${ctx}/index/15/meishizhusu.html">
                        <img src="${ctx}/static/img/meishi.png" alt=""/>
                        <p>美食</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/8/mengshou.html">
                        <img src="${ctx}/static/img/zoo.png" alt=""/>
                        <p>动物园</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/24/daolantu.html">
                        <img src="${ctx}/static/img/xingcheng.png" alt=""/>
                        <p>行程</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/16/bieshu.html">
                        <img src="${ctx}/static/img/zhusu.png" alt=""/>
                        <p>住宿</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/11/shangxi.html">
                        <img src="${ctx}/static/img/meijing.png" alt=""/>
                        <p>美景</p>
                    </a>
                </li>
            </ul>
        </div>

    </div>

	<!--offer-starts-->
	<div class="offer">
		<div class="container">
			<!--<div class="offer-top heading">-->
				<!--<h4>-->
                    <!--<p class="jcty">推荐项目</p>-->
                    <!--<p class="jctye" style=""><a href="">查看全部</a></p>-->
                <!--</h4>-->
			<!--</div>-->
			<div class="offer-bottom">
                <div class="swiper-container swiper-container2">
                    <div class="offer-top heading">
                        <h4>
                            <p class="jcty">推荐项目</p>
                            <!-- <p class="jctye" style=""><a href="">查看全部</a></p> -->
                        </h4>
                    </div>
                    <div class="swiper-wrapper swiper1">
                    	<c:forEach items="${listArticleTuijian}" var="art">
                    		<div class="swiper-slide swiper-slide2">
				        		<div class="offer-left-wrap offer-left">
					                <a href="${ctx}/${art.purl}"><img src="${art.picUrl}" style="width: 540px;height: 267.38px;" alt=""/>
					                <h4>${art.title}</h4>
					                </a>
					                <div class="offer-bottom-wrap">
					                	<p>${art.abstractText}</a></p>
					                </div>
					            </div>
				            </div>
			        	</c:forEach>
                    	
                    </div>
                    <!-- Add Pagination -->
                    <div class="swiper-pagination swiper-pagination2"></div>
                    <!-- Add Arrows -->
                    <div class="swiper-button-next swiper-button-next2"></div>
                    <div class="swiper-button-prev swiper-button-prev2"></div>
                </div>
			</div>
		</div>
	</div>
	<!--offer-ends-->

    <script>
	
        $(function(){

        	$('.teselist li a').hover(function(){
        		var img=$(this).find('img:first').attr('src').replace('.png','_s.png')
        		$(this).find('img:first').attr('src',img);
        	},function(){
        		var img=$(this).find('img:first').attr('src').replace('_s.png','.png')
        		$(this).find('img:first').attr('src',img);
        	})
        	/* $('.swiper-slide a').hover(function(){
        		$(this).find('h4:first').css('display','block');
        	},function(){
        		$(this).find('h4:first').css('display','none')
        	}) */
            var swiper1 = new Swiper('.swiper-container1', {
                pagination: '.swiper-pagination1',
                slidesPerView: 1,
                paginationClickable: true,
                spaceBetween:0,
                loop: true,
                speed:750,
                autoplay: 2500

            });
          //banner小按钮位置
        	var width=$('.swiper-pagination1').width();
        	$('.swiper-pagination1').css('margin-left',-width/2);
            winw=$(window).width();
            var swiper2 = new Swiper('.swiper-container2', {
                slidesPerView: winw<600 ? 1:2,
                slidesPerGroup: winw<600 ? 1:2,
                pagination: '.swiper-pagination2',
                nextButton: '.swiper-button-next2',
                prevButton: '.swiper-button-prev2',
                paginationClickable: true,
                spaceBetween: 60,
                loop: true
            });

        })
        
        if (!('classList' in document.documentElement)) {
    		HTMLElement = window.HTMLElement || Element;
    		String.prototype.trim=function(){
				return this.replace(/(^\s*)|(\s*$)/g, "");
			}
			String.prototype.ltrim=function(){
				return this.replace(/(^\s*)/g,"");
			}
			String.prototype.rtrim=function(){
				return this.replace(/(\s*$)/g,"");
			}
		    if(!window.getComputedStyle){
				window.getComputedStyle = function(el) {
			    	this.el = el;
			    	this.getPropertyValue = function(prop) {
			      		var re = /(\-([a-z]){1})/g;
			      		if (prop === "float") {
			        		prop = "styleFloat";
			      		}
			      		if (re.test(prop)) {
			        		prop = prop.replace(re, function () {
			          			return arguments[2].toUpperCase();
			        		});
			      		}
			      		return el.currentStyle[prop] ? el.currentStyle[prop] : null;
			    	};
			    	return this;
			  	};

		    }
			Object.addEventListener = Object.attachEvent;
			if (!Array.prototype.indexOf){
			  Array.prototype.indexOf = function(elt /*, from*/){
			    var len = this.length >>> 0;

			    var from = Number(arguments[1]) || 0;
			    from = (from < 0)
			         ? Math.ceil(from)
			         : Math.floor(from);
			    if (from < 0)
			      from += len;

			    for (; from < len; from++){
			      if (from in this && this[from] === elt)
			        return from;
			    }
			    return -1;
			  };
			}

	        Object.defineProperty(HTMLElement.prototype, 'classList', {
	            get: function() {
	                var self = this;
	                function update(fn) {
	                    return function(value) {
	                        var classes = self.className.split(/\s+/g),
	                            index = classes.indexOf(value);
	                        
	                        fn(classes, index, value);
	                        self.className = classes.join(" ");
	                    }
	                }
	                
	                return {                    
	                    add: update(function(classes, index, value) {
	                        if (!~index) classes.push(value);
	                    }),
	                    
	                    remove: update(function(classes, index) {
	                        if (~index) classes.splice(index, 1);
	                    }),
	                    
	                    toggle: update(function(classes, index, value) {
	                        if (~index)
	                            classes.splice(index, 1);
	                        else
	                            classes.push(value);
	                    }),
	                    
	                    contains: function(value) {
	                        return !!~self.className.split(/\s+/g).indexOf(value);
	                    },
	                    
	                    item: function(i) {
	                        return self.className.split(/\s+/g)[i] || null;
	                    }
	                };
	            }
	        });
	    }
    </script>
	<!--footer-ends--> 
</body>
</html>
