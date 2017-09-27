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
                        <a class="active" href="${ctx}/index">トップページ</a>
                    </li>
					<li class="f-l">
                        <a href="javascript:;">予定のサービス</a>
                        <ul class="xiala">
                            <li><a href="${ctx}/commodity/list/0">チケットの予定</a></li>
                            <li><a href="${ctx}/commodity/list/2">ホテルの予定</a></li>
                            <li><a href="${ctx}/commodity/list/1">飲食予定</a></li>
                            <li><a href="${ctx}/index/32/yhhuodong.html">割引イベント</a></li>
                            <li><a href="${ctx}/commodity/list/3">商品の予約</a></li>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/6/experience.html">多彩体験</a>
                        <ul class="xiala">
                            <li><a href="${ctx}/index/8/mengshou.html">猛獣の授乳</a></li>
                            <li><a href="${ctx}/index/9/xiongmao.html">スターパンダ</a></li>
                            <li><a href="${ctx}/index/10/fly.html">低空飛行</a></li>
                            <li><a href="${ctx}/index/11/shangxi.html">美しさの鑑賞</a></li>
                            <li><a href="${ctx}/index/33/juchang.html">楽しい劇場</a></li>
                            <li><a href="${ctx}/index/34/jiayuan.html">调和の家</a></li>
                        </ul>
                    </li>
                    
					<li class="f-l">
                        <a href="${ctx}/index/15/meishizhusu.html">グルメと宿</a>
                        <ul class="xiala" style="border-right:1px solid #ddd;">
                            <li><a href="${ctx}/index/16/bieshu.html">崖の別荘</a></li>
                            <li><a href="${ctx}/index/17/jiudian.html">リゾートホテル</a></li>
                            <li><a href="${ctx}/index/19/canting.html">碧峰レストラン</a></li>
                            <li><a href="${ctx}/index/20/chalou.html">雲海茶楼</a></li>
                        </ul>
                    </li>
                    <li class="logo f-l">
                        <a href="${ctx}/"><img src="${ctx}/static/img/logo.png" alt="logo" class="logo_icon"></a>
                    </li>
					<li class="f-l">
                        <a href="">観光地案内</a>
                        <ul class="xiala">
                        	<li><a href="${ctx}/index/2/introduction.html">スポット概要</a></li>
                            <li><a href="${ctx}/index/24/daolantu.html">案内図</a></li>
                            <li><a href="${ctx}/index/25/jiaotongxinxi.html">交通情報</a></li>
                            <li><a href="http://www.expoon.com/14467/panorama?from=groupmessage&isappinstalled=0" target="_blank">仮想ガイド</a></li>
                        </ul>
                    </li>
					<li class="f-l">
                        <a href="${ctx}/index/26/bangzhu.html">ヘルプ</a>
                        <ul class="xiala" style="border-right:1px solid #ddd;width:130px;">
                            <li><a href="${ctx}/index/27/youkefuwu.html">観光客サービス</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">お問合せ電話</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">微信</a></li>
                            <li><a href="${ctx}/index/28/lianxidianhua.html">微博</a></li>
                        </ul>
                    </li>

				</ul>
				<p class="language">
					<a href="${ctx}/" class="f-l">中文</a>
					<a href="${ctx}/index/korean.html" class="f-l">한국어</a>
					<a href="${ctx}/index/english.html" class="f-l">English</a>
				</p>
                <form action="" class="search_form">
                
                    <label>
	                    <input type="text" class="search" placeholder="動物園"/>
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
                        <p>グルメ</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/8/mengshou.html">
                        <img src="${ctx}/static/img/zoo.png" alt=""/>
                        <p>動物園</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/24/daolantu.html">
                        <img src="${ctx}/static/img/xingcheng.png" alt=""/>
                        <p>コース</p>
                    </a>
                </li>
                <li>
                    <a href="${ctx}/index/16/bieshu.html">
                        <img src="${ctx}/static/img/zhusu.png" alt=""/>
                        <p>ホテル</p>
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
                            <p class="jcty">推薦項目</p>
                            <!-- <p class="jctye" style=""><a href="">查看全部</a></p> -->
                        </h4>
                    </div>
                    <div class="swiper-wrapper">
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/xiongmao.html"><img src="${ctx}/static/img/pangda.png" alt="" />
                                <h4>パンダ園（胖達があれば、美景があります）</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>パンダ園は碧峰峡に最も特徴のある観光地の一つです、敷地面積が1074亩を占めています。合計60匹以上のパンダはここにあります、科学研究、繁殖、旅行などの多機能を一体にしています、よく知られている台湾へのパンダ「団団」、「園園」、英国へのパンダ「陽光」、「甜甜」、シンガポールへのパンダ「武傑」、「沪宝」及び多くの有名パンダ「華美」、「泰山」、「福龍」などはここに生活したことがあります。</p>
                                </div>
                            </div>
                        </div>
                        
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/jiudian.html"><img src="${ctx}/static/img/pangda1.png" alt="" />
                                <h4>天龍凌雲—白龍潭滝（蛟龍の海出、虹のような勢い）</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>「天龍凌雲」は「白龍潭滝」とも言われています、碧峰峡観光地に最も素晴らしい滝です、高さが約30メートル、幅が約10メートルとなっています、滝の水が降り注ぐのように降り、満天の水花が立ち上がり、白龍のように飛んでいるようです。滝の音が峡谷に響き、白龍が海を出るようです。手すりにもたれて見下ろせば、滝が連綿で九段階を降り、白い巨龍のように天から降り、素晴らしい壮観です。</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/8/mengshou.html"><img src="${ctx}/static/img/mengshouweiyang.png" alt="" />
                                <h4>猛獣にエサやり（考えられないスリル体験）</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>猛獣にエサやりは碧峰峡野生動物世界に最も刺激のある体験の一つです、猛獣にエサやりエリアには多くの獅子、トラ、ツキノワグマなどの野獣が飼っています、入園される瞬間に、原生態、ワイルド感覚を体験できます、飼育員が作った肉串をエサやり窓口から差し込み飼育できます、窓を通じて近距離で東北虎、アフリカ獅子、ツキノワグマなどの猛獣がエサを奪い合う瞬間を観察できます、スリル、刺激さが心まで伝わります。</p>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide swiper-slide2">
                            <div class="offer-left-wrap offer-left">
                                <a href="${ctx}/index/fly.html"><img src="${ctx}/static/img/dikongfeixing.png" alt="" />
                                	<h4>ハーモニー家園（人、可愛いペット、自然とのハーモニー・共生）</h4>
                                </a>
                                <div class="offer-bottom-wrap">
                                    
                                    <p>ハーモニー家園は碧峰峡野生動物世界に最初の動物混合飼育エリアです、ここに神獣と呼ばれるアルパカ（草泥馬）、ニホンジカ、ハクチョウ、ロバアルガリ、白黇鹿などの多種類の優しい動物が生活しています、観光客はここをゆっくりと観覧しながら、動物らと遊んだりしています、人と動物、人と自然が親密、ハーモニーで共存している美景を形成しています、あなたに数えられないほど楽しさをもたらします。 </p>
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
