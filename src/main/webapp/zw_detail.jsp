<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title> 正文详情页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <!-- <link href="../css/bootstrap.css" rel='stylesheet' type='text/css'/>
    <link href="../css/style.css" rel='stylesheet' type='text/css'/>
    <link href="../css/reset.css" rel='stylesheet' type='text/css'/>
    <link href="../css/content.css" rel='stylesheet' type='text/css'/> -->
    <!---- start-smoth-scrolling---->
</head>
<body>
<!----start-header---->

<!----start-details---->
<div class="details_box">
    <div class="details_inder">
        <span><a href="">首页</a></span> <span> > </span> <span><a href="">景区介绍</a></span> <span> > </span> <span><a href="">正文详情</a></span>
    </div>
    <div class="details_bar clearfix">
        <div class="f-l f_t">
            <h1>全世界都在关注大熊猫，碧峰峡机遇无限</h1>
            <div class="infor clearfix">
                <div class="f-l ma_l">
                    <span class="s_mr">发布日期：2015-06-16 13:14:17</span>
                    <span>浏览次数：356</span>
                </div>
                <div class="f-r ma_r">
                    <span>作者：张三</span>
                    <span class="s_mrr">来源：网络</span>
                </div>
            </div>
            <p>6月13日，四川日报发表了《行南丝绸之路 游大熊猫家乡》文章，受到网易、腾讯、新浪等主流网络媒体轮番转载，报道称：“伦敦时间6月11日，由来自英国、西班牙等国家的欧洲熊猫粉丝和四川大熊猫研究专家、大熊猫摄影师、南丝路文化专家等组成的熊猫粉丝团从英国和西班牙同时出发，两天后他们将在法国巴黎会合，驾驶10辆“成都造”越野车，穿越欧亚15个国家抵达四川，探秘大熊猫家乡。本次活动的首轮推介会分别在英国和西班牙举行。伦敦时间6月12日上午，一场主题为“四川，不仅仅有熊猫”的推介会在伦敦动物园举行。”</p>
            <div class="img_banner">
                <a href=""><img src="${ctx}/static/img/xiongmaozhuzi.png" alt="xiongmao"/></a>
                <h3>碧峰峡工作人员考察团介绍碧峰峡布局与生态环境情况</h3>
            </div>
            <h2>“明星熊猫”故乡</h2>
            <p class="m_b_0">为了国际友人更好地了解大熊猫故乡--雅安，由雅安市旅游局代表雅安市参加了本次推介会活动。同时，为了国际友人更好地了解目前最大的熊猫散养中心--中国保护大熊猫研究中心雅安碧峰峡基地，市旅游局特意把碧峰峡诚挚的邀请函、门票等礼品带到了活动现场。</p>
            <p>《行南丝绸之路 游大熊猫家乡》报道还称：“英国站推介会前一天，熊猫粉丝团来到爱丁堡动物园探望大熊猫“阳光”和“甜甜”，并向前往爱丁堡动物园观赏大熊猫的全球游客推广四川旅游。爱丁堡动物园熊猫项目负责人告诉记者，“阳光”和“甜甜”在爱丁堡已生活4年，爱丁堡动物园熊猫馆成为游客到爱丁堡必看景点之一。”</p>
            <div class="img_banner">
                <a href=""><img src="${ctx}/static/img/xiongmaozhuzi.png" alt="xiongmao"/></a>
                <p>2008年，“欢欢”、“福娃”、“翠翠”、“朗朗”、“朵朵”、“凤仪”、“美欣”、“淘淘”来自碧峰峡大熊猫基地的8只大熊猫从100余只熊猫中脱颖而出，评选为"奥运熊猫"，吸引了全世界的目光。</p>
            </div>
        </div>
        <div class="f-r r_t">
            <div class="text_box">
                <h5><a href="">大事记</a></h5>
                <ul class="ul_t">
                    <li><a href="">规范秩序，提升风貌，齐心协力共创5A景区</a></li>
                    <li><a href="">东方碧峰峡建设项目开工 酷炫冰世界即将来袭！！</a></li>
                    <li><a href="">区委书记带队 强力综合整治 推进碧峰峡5A创建</a></li>
                    <li><a href="">重磅福利：属猴游客来碧峰峡免票啦！</a></li>
                    <li><a href="">全家欢乐总动员 碧峰峡里过大年</a></li>
                    <li><a href="">“碧峰峡杯”雨城旅游形象大使首赛启幕</a></li>
                    <li><a href="">【碧峰峡一定给你一个不一样的国庆节】</a></li>
                    <li><a href="">1元游碧峰峡，天降福利赶快来抢！</a></li>
                </ul>
            </div>
            <div class="text_box">
                <h5><a href="">新闻中心</a></h5>
                <ul class="ul_t">
                    <li><a href="">副省长杨洪波莅临碧峰峡旅游安全检查并指导5A创建</a></li>
                    <li><a href="">碧峰峡精彩亮相旅博会 大熊猫主题受热捧</a></li>
                    <li><a href="">全员深度参与服务专项培训 创建5A碧峰峡软实力飞升</a></li>
                    <li><a href="">政府领导施工现场办公 持续指导碧峰峡5A创建</a></li>
                    <li><a href="">“学霸”碧峰峡 用科学精神搞一场厕所革命</a></li>
                    <li><a href="">多领域顶级人才合力打造高品质厕所 创新环保铸就景区典范</a></li>
                    <li><a href="">【5A创建动态】碧峰峡斥重资掀起“厕所革命” 中国最美云海厕所令人期待</a></li>
                    <li><a href="">“文明游天下 最美四川人 最美文明小天使”选拔活动在碧峰峡成功举行</a></li>
                </ul>
            </div>
            <div class="text_box">
                <h5><a href="">媒体报道</a></h5>
                <ul class="ul_t">
                    <li><a href="http://news.xinhuanet.com/newmedia/2016-10/15/c_135756072.htm">首届中国大熊猫关爱文化国际交流活动在川启动(新华网)</a></li>
                    <li><a href="http://www.sc.gov.cn/10462/10605/13743/13747/2016/10/14/10399204.shtml">杨洪波赴雅安调研城市规划建设、交通和旅游工作（四川省人民政府网）</a></li>
                    <li><a href="http://news.china.com.cn/rollnews/news/live/2015-06/27/content_33294632.htm">雅安碧峰峡基地大熊猫"芊芊"产下双胞胎兄弟（中国网）</a></li>
                    <li><a href="http://news.china.com.cn/live/2015-04/01/content_32104992.htm">碧峰峡打造千亩花海观光区免费迎客（中国网）</a></li>
                    <li><a href="http://sc.china.com.cn/2014/lvyou_zixun_0829/67507.html">雅安市碧峰峡景区持续火爆（四川旅游政务网）</a></li>
                    <!-- <li><a href="">“碧峰峡杯”雨城旅游形象大使首赛启幕</a></li>
                    <li><a href="">【碧峰峡一定给你一个不一样的国庆节】</a></li>
                    <li><a href="">1元游碧峰峡，天降福利赶快来抢！</a></li> -->
                </ul>
            </div>
        </div>
    </div>
</div>
<!----end-details---->
<!--footer-->

<!--/footer-->
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/style.js"></script>
<script>


</script>
<!--footer-ends-->
</body>
</html>