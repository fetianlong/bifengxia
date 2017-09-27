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
<meta name="discription" content="${discription}" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
    <style>
    	ul.navig li{padding:0 2%;display:block;}
    	ul.navig li a{font-size:1.1em;}
    	.xiala_opacity{height:230px;}
    	.xiala{height:156px;}
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
<body>
	<div class="header" id="home">
        <div class="xiala_opacity"></div>
		<div class="container">
			<div class="navigation clearfix">
			 <span class="menu"></span> 
				<ul class="navig clearfix">
					<li class="f-l">
                        <a class="active" href="${ctx}/index">메 인 화면</a>
                    </li>
					<li class="f-l">
                        <a href="javascript:;">예정 된 서비스</a>
                        <ul class="xiala">
                            <li><a href="${ctx}/commodity/list/0">입장권예약</a></li>
                            <li><a href="${ctx}/commodity/list/2">호텔예약</a></li>
                            <li><a href="${ctx}/commodity/list/1">외식예정</a></li>
                            <li><a href="${ctx}/index/32/yhhuodong.html">시즌 할인</a></li>
                            <li><a href="${ctx}/commodity/list/3">상품을 예약하다</a></li>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/6/experience.html">재밌는 체험</a>
                        <ul class="xiala">
                            <li><a href="${ctx}/index/8/mengshou.html">맹수 먹이주기</a></li>
                            <li><a href="${ctx}/index/9/xiongmao.html">스타 팬다</a></li>
                            <li><a href="${ctx}/index/10/fly.html">저공비행</a></li>
                            <li><a href="${ctx}/index/11/shangxi.html">아름다움의감사</a></li>
                            <li><a href="${ctx}/index/33/juchang.html">즐거운 극장</a></li>
                            <li><a href="${ctx}/index/34/jiayuan.html">화목한 가정</a></li>
                        </ul>
                    </li>
                    
					<li class="f-l">
                        <a href="${ctx}/index/15/meishizhusu.html">식당 및 주숙</a>
                        <ul class="xiala" style="border-right:1px solid #ddd;">
                            <li><a href="${ctx}/index/16/bieshu.html">벼랑빌라</a></li>
                            <li><a href="${ctx}/index/17/jiudian.html">리조트</a></li>
                            <li><a href="${ctx}/index/19/canting.html">비봉레스토랑</a></li>
                            <li><a href="${ctx}/index/20/chalou.html">운해차루</a></li>
                        </ul>
                    </li>
                    <li class="logo f-l">
                        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="logo" class="logo_icon"></a>
                    </li>
					<li class="f-l">
                        <a href="">풍경구안내</a>
                        <ul class="xiala">
                        	<li><a href="${ctx}/index/2/introduction.html">풍경구소개 </a></li>
                            <li><a href="${ctx}/index/24/daolantu.html">안내도</a></li>
                            <li><a href="${ctx}/index/25/jiaotongxinxi.html">교통정보</a></li>
                            <li><a href="http://www.expoon.com/14467/panorama?from=groupmessage&isappinstalled=0" target="_blank">가상 导览</a></li>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/26/bangzhu.html">서비스</a>
                        <ul class="xiala" style="border-right:1px solid #ddd;">
                            <li><a href="${ctx}/index/27/youkefuwu.html">유람객서비스</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">전화번호</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">위챗</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">웨이보</a></li>
                        </ul>
                    </li>

				</ul>
				<p class="language">
					<a href="${ctx}/" class="f-l">中文</a>
					<a href="${ctx}/index/japanese.html" class="f-l">日本語</a>
					<a href="${ctx}/index/english.html" class="f-l">English</a>
				</p>
                <form action="" class="search_form">
                
                    <label>
	                    <input type="text" class="search" placeholder="동물원"/>
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
                        <p>식당</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/8/mengshou.html">
                        <img src="${ctx}/static/img/zoo.png" alt=""/>
                        <p>동물원</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/24/daolantu.html">
                        <img src="${ctx}/static/img/xingcheng.png" alt=""/>
                        <p>행정</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/16/bieshu.html">
                        <img src="${ctx}/static/img/zhusu.png" alt=""/>
                        <p>주숙</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/11/shangxi.html">
                        <img src="${ctx}/static/img/meijing.png" alt=""/>
                        <p>아름다운 경치</p>
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
                            <p class="jcty">추천항목</p>
                            <!-- <p class="jctye" style=""><a href="">查看全部</a></p> -->
                        </h4>
                    </div>
                    <div class="swiper-wrapper">
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/xiongmao.html"><img src="${ctx}/static/img/pangda.png" alt="" />
                                <h4>팬다원(팬다와 아름다운 풍경 )</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>팬다원은 비봉하풍경구의 제일 특색있는 관광명소중 하나이다. 면적은 1074모정도 되며 60여마리의 팬더들이 있으며 연구, 번식, 관광등 다 공능이 합체된 곳이다. 모두가 익히 잘 알고 있는 대만에 증여했던 “퇀퇀”,”왠왠”, 영국으로 갔던 “양광”, “탠탠”, 싱가포르로 간 “우제”, “후보” 및 유명한 유학파 팬다인 “화메이”, “타이산”, “푸룽”등 모두 여기서 생활한 적이 있다.</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/jiudian.html"><img src="${ctx}/static/img/pangda1.png" alt="" />
                                <h4>천용능운 - 백용담 폭포</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>“청용능운”은 또 “백용담폭포”라고도 불린다. 이는 비봉하 풍경구내의 제일 웅장한 폭포이며 높이가 30미터, 너비가 10미터로 폭포가 쏟아내리면서 피어나오는 물줄기가 흡사 백용이 날아오르는 모습 같고 폭포소리는 협곡을 뒤흔들먀 백용이 바다로 나가는듯 하다. 난간에서 아래로 내려다 보면 폭포가 아홉개 계단으로 나뉘에 떨어지는 모습이 흡사 백용이 하늘에서 내려온듯 하여 장관을 이룬다.</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/mengshou.html"><img src="${ctx}/static/img/mengshouweiyang.png" alt="" />
                                <h4>맹수 먹이주기(색다른 스릴체험)</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>맹수 먹이주기는 비봉하 야생동물세계의 제일 스릴감이 넘치는 체험중의 하나이다. 맹수 먹이주기 구역에는 많은 사자, 범, 흑곰 등 다양한 맹수들이 살고 있으며 원구를 들어서면 생태적이고 광야로운 느낌을 받을수 있다. 관광객은 조련사가 준비한 고기를 먹이투여 구역에르 들여보내 동북호랑이 아프리카 사자 흑곰 등이 먹이를 빼앗아 먹는 순간을 유리를 사이에 두고 볼 수 있으며 스릴감이 넘친다. 。</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/fly.html"><img src="${ctx}/static/img/dikongfeixing.png" alt="" />
                                	<h4>화목한 가원 (사람, 반려동물, 자연이 공생하는 세상)</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>화목한 가원은 비봉하 야생동물세계의 첫번재 동물들을 한곡에 방목하는 구역이다. 여기에는 신의 동물이라는 알파카(라마), 매화꽃 사슴, 백조, 당나귀, 얼룩 사슴등 온순한 동물들이 살고 있어 사람인파에서도 천천히 가로질러 가며 사람들과 놀며 사람,동물, 자연이 조화를 이루는 아름다운 경치를 만들어내 많은 즐거움을 가져다 준다. </p>
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
