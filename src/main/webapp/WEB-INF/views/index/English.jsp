<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title> ${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="${keywords}" />
    <meta name="Description" content="${Description}" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <style>
    	ul.navig li{padding:0;display:block;}
    	ul.navig li a{font-size:1.1em;}
    	.swiper-container2 {
            padding-bottom:30px;

        }
        .offer-left .offer-bottom-wrap{height:auto;}
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
        .swiper-pagination1 .swiper-pagination-bullet-active{background:#fd9f3b;}

        .swiper-pagination2{}
        .swiper-pagination2 .swiper-pagination-bullet{width:16px;height:16px;background:#aaa;width:34px;height:10px;border-radius:0;}
        .swiper-pagination2 .swiper-pagination-bullet-active{background:#fd9f3b;}
        .swiper-button-next2 {top:100px;background:url("${ctx}/static/img/next.png") no-repeat center center;width:60px;height:60px;}
        .swiper-button-prev2 {top:100px;background:url("${ctx}/static/img/prev.png") no-repeat center center;width:60px;height:60px;}
        .swiper-slide a{display:block;width:100%;margin-bottom:-2px;position:relative;}
        .swiper-slide a img{width:100%;}
        @media (max-width:1200px) {
            .swiper-pagination1{ background: rgba(0,0,0,0.6);height: 20px;line-height: 20px;border-radius: 10px;}
            .swiper-pagination1 .swiper-pagination-bullet{width:8px;height:8px;background:#fff;opacity:1;margin:0 5px;}
        }
        @media (max-width:640px) {
            .jcty{margin-top:20px;}
        }
    </style>

<!---- start-smoth-scrolling---->
</head>
<body>
	<div class="header" id="home">
        <div class="xiala_opacity"></div>
		<div class="container">
			<div class="navigation clearfix">
			 <span class="menu"></span> 
				<ul class="navig clearfix">
					<li class="f-l">
                        <a class="active" href="${ctx}/index">index</a>
                    </li>
					<li class="f-l">
                        <a href="#">Tickets and Concessions</a>
                        <ul class="xiala" style="width:220px;right:0;left:auto;">
                            <li><a href="${ctx}/commodity/list/0">Ticket reservation</a></li>
                            <li><a href="${ctx}/commodity/list/2">hotel reservation</a></li>
                            <li><a href="${ctx}/commodity/list/1">Restaurant reservation</a></li>
                            <li><a href="${ctx}/index/32/yhhuodong.html">Preferential activities</a></li>
                            <li><a href="${ctx}/commodity/list/3">Commodity reservation</a></li>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/6/experience.html">Wonderful Experience</a>
                        <ul class="xiala">
                            <li><a href="${ctx}/index/8/mengshou.html">Beast Feeding </a></li>
                            <li><a href="${ctx}/index/9/xiongmao.html">Star Panda</a></li>
                            <li><a href="${ctx}/index/10/fly.html">Low-altitude Flight</a></li>
                            <li><a href="${ctx}/index/11/shangxi.html">Appreciate</a></li>
                            <li><a href="${ctx}/index/33/juchang.html">Gaiety Theatre</a></li>
                            <li><a href="${ctx}/index/34/jiayuan.html">Harmonious Family</a></li>
                            <%-- <li><a href="${ctx}/index/tanxian.html">森林探险</a></li> --%>
                        </ul>
                    </li>
                    
					<li class="f-l">
                        <a href="${ctx}/index/15/meishizhusu.html">Cate and Accommodation</a>
                        <ul class="xiala"  style="border-right:1px solid #ddd;">
                            <li><a href="${ctx}/index/16/bieshu.html">Cliff Villa</a></li>
                            <li><a href="${ctx}/index/17/jiudian.html">Resort Hotel</a></li>
                            <li><a href="${ctx}/index/19/canting.html">Bifeng Restaurant</a></li>
                            <li><a href="${ctx}/index/20/chalou.html">Cloud Sea Teahouse</a></li>
                        </ul>
                    </li>
                   <li class="logo f-l">
                        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="logo" class="logo_icon"></a>
                    </li>
					<li class="f-l">
                        <a href="">Guide of Scenic Spot </a>
                        <ul class="xiala" style="width:150px;">
                        	<li><a href="${ctx}/index/2/introduction.html">Introduction</a></li>
                            <li><a href="${ctx}/index/24/daolantu.html">Guide Map</a></li>
                            <li><a href="${ctx}/index/25/jiaotongxinxi.html">Traffic Information</a></li>
                            <li><a href="http://www.expoon.com/14467/panorama?from=groupmessage&isappinstalled=0" target="_blank">VIRTUAL DISPLAY</a></li>
                        </ul>
                    </li>
                
					<li class="f-l">
                        <a href="${ctx}/index/26/bangzhu.html">Help</a>
                        <ul class="xiala" style="border-right:1px solid #ddd;width:200px;">
                            <li><a href="${ctx}/index/27/youkefuwu.html">Visitors Service</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">Contact Phone</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">WeChat</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">Microblog</a></li>
                        </ul>
                    </li>
				</ul>
				<p class="language">
					<a href="${ctx}/index/korean.html" class="f-l">한국어</a>
					<a href="${ctx}/index/japanese.html" class="f-l">日本語</a>
					<a href="${ctx}/" class="f-l">中文</a>
				</p>
                <form action="" class="search_form">
                    
                    <label>
	                    <input type="text" class="search" placeholder="zoo"/>
	                    <div class="sousuo_xiala">
	                    	<P>111</P>
	                    	<P>111</P>
	                    </div>
                    </label>
                    <button type="button"></button>
                </form>
			</div>

		</div>
	</div>
	<!--banner-starts-->
	<div class="banner">
        <div class="swiper-container swiper-container1">
            <div class="swiper-wrapper">
               <c:forEach items="${listBanner}" var="listB">
            		<div class="swiper-slide swiper-slide1"><a href=""><img src="${listB.pathUrl}" alt=""/></a></div>
            	</c:forEach> 
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
                        <p>Cate</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/8/mengshou.html">
                        <img src="${ctx}/static/img/zoo.png" alt=""/>
                        <p>Zoo</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/24/daolantu.html">
                        <img src="${ctx}/static/img/xingcheng.png" alt=""/>
                        <p>Journey</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/16/bieshu.html">
                        <img src="${ctx}/static/img/zhusu.png" alt=""/>
                        <p>Accommodation</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/11/shangxi.html">
                        <img src="${ctx}/static/img/meijing.png" alt=""/>
                        <p>Landscape</p>
                    </a>
                </li>
            </ul>
        </div>

    </div>

	<!--offer-starts-->
	<div class="offer">
		<div class="container">
			<div class="offer-bottom">
                <div class="swiper-container swiper-container2">
                    <div class="offer-top heading">
                        <h4>
                            <p class="jcty">Recommended Items</p>
                            <!-- <p class="jctye" style=""><a href="">View All</a></p> -->
                        </h4>
                    </div>
                    <div class="swiper-wrapper">
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/xiongmao.html"><img src="${ctx}/static/img/pangda.png" alt="" />
                                <h4>Panda Garden (with panda and beautiful landscape)</h4></a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>Panda Garden is one of the most characteristic scenic spots in Bifeng Gorge Scenic Spot， covering an area of 1，074 mu. There are totally more than 60 giant pandas. It integrates multiple functions of scientific research， breading and tourism as a whole. The well-known pandas “Tuantuan and “Yuanyuan” presented to Taiwan ， Yangguang” and “Tiantian” presented to UK “， “Wujie” and “Hubao” presented to Singapore and many famous overseas pandas “Huamei”， “Taishan”， “Fulong” once lived here.</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/jiudian.html"><img src="${ctx}/static/img/pangda1.png" alt="" />
                                <h4>Tianlong Lingyun——White Dragon Pool Waterfall (dragon coming out of the sea， impressive landscape)</h4></a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>Tianlong Lingyun is also called “White Dragon Pool Waterfall”. It is the most spectacular waterfall in Bifeng Gorge Scenic Spot. It is about 30m high and 10m wide， which pours down and splashes all over the sky just like a white dragon flying; the sound of waterfall shakes the gorge， just like a white dragon coming out of the sea. Leaning against the railing to overlook， the waterfall pours down in 9 cascades successively， which looks like a white huge dragon dropping from the sky， presenting a spectacular landscape.</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/8/mengshou.html"><img src="${ctx}/static/img/mengshouweiyang.png" alt="" />
                                <h4>Beast Feeding (a different breathtaking experience)</h4></a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>Beast Feeding is one of the most breathtaking and exciting experiences in Bifeng Gorge Safari Park. There are many cage-free beasts such as lion， tiger and black bear in the beast feeding zone. At the moment of entering the zone， you can experience the primitive and wild feeling， you can stretch out the pork shashlik prepared by the breeder from the feeding mouth and you can be separated by window to take a close look at the instant of the beats robbing food such as Manchurian tiger， African lion and bear. What a breathtaking and exciting scene.</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/fly.html"><img src="${ctx}/static/img/dikongfeixing.png" alt="" />
                                <h4>Harmonious Homeland (Harmonious co-existence of human， cute pet and nature)</h4></a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>Harmonious Homeland is the first animal polyculture area in Bifeng Gorge Safari Park. There are multiple tame animals such as alpaca (grass mud horse) which is called mythical creature， sika deer， swan， donkey argali and fallow Deer， they shuttle back and forth leisurely in the crowd， play with and interact with the visitors， forming a beautiful scenery of close and harmonious co-existence of human， animal and nature. It brings you infinite joy.</p>
                                </div>
                            </div>
                        </div>
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
