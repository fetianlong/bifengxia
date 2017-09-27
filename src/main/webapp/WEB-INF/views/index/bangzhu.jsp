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
</head>
<body>
<!--banner-starts-->
<div class="bangzhu_banner" style="width:100%;background: url('${ctx}/static/img/bangzhu_banner.png') center center no-repeat;height: 300px;cursor: pointer;">

</div>
<div class="bangzhu_border"></div>
<div class="bangzhu_box">
    <div class="bangzhu_text clearfix">
        <ul class="f-l lianxifangshi clearfix">
            <li class="tab active"><a href="${ctx}/index/27/youkefuwu.html"><span>游客服务</span></a></li>
            <li class="tab"><a href="${ctx}/index/28/lianxidianhua.html"><span>联系方式</span></a></li>
        </ul>
        <ul class="f-r bangzhu_p tabitem active">
        ${article.content}
        <%-- 
            <li>
                <h3>问：可到成都新南门旅游客运站乘坐“成都——碧峰峡”的旅游专线</h3>
                <p><span>答：</span>四川美联华邦通用航空有限公司董事会秘书长曹婧、市场部经理李静及资深飞行员等空勤、地勤人员一行7人，专程到雅碧峰峡实地考察低空旅游飞行环境。美联华邦公司与碧峰峡公司双方就低空旅游合作进行了洽谈，美联华邦公司对碧峰峡的生态环境和丰富产品的多样性十分认可，更认同万贯的经营理念，认同碧峰峡积极创建5A级旅游景区大胆尝试新项目的胆识，双方良好沟通，初步达成深度合作意向，且美联华邦公司向该空域主管部门申报飞行计划。</p>
            </li>
            <li>
                <h3>问：可到成都新南门旅游客运站乘坐“成都——碧峰峡”的旅游专线</h3>
                <p><span>答：</span>四川美联华邦通用航空有限公司董事会秘书长曹婧、市场部经理李静及资深飞行员等空勤、地勤人员一行7人，专程到雅碧峰峡实地考察低空旅游飞行环境。美联华邦公司与碧峰峡公司双方就低空旅游合作进行了洽谈，美联华邦公司对碧峰峡的生态环境和丰富产品的多样性十分认可，更认同万贯的经营理念。</p>
            </li>
            <li>
                <h3>问：可到成都新南门旅游客运站乘坐“成都——碧峰峡”的旅游专线</h3>
                <p><span>答：</span>四川美联华邦通用航空有限公司董事会秘书长曹婧、市场部经理李静及资深飞行员等空勤、地勤人员一行7人，专程到雅碧峰峡实地考察低空旅游飞行环境。美联华邦公司与碧峰峡公司双方就低空旅游合作进行了洽谈，美联华邦公司对碧峰峡的生态环境和丰富产品的多样性十分认可，更认同万贯的经营理念，认同碧峰峡积极创建5A级旅游景区大胆尝试新项目的胆识，双方良好沟通，初步达成深度合作意向，且美联华邦公司向该空域主管部门申报飞行计划。</p>
            </li>
            <li>
                <h3>问：可到成都新南门旅游客运站乘坐“成都——碧峰峡”的旅游专线</h3>
                <p><span>答：</span>四川美联华邦通用航空有限公司董事会秘书长曹婧、市场部经理李静及资深飞行员等空勤、地勤人员一行7人，专程到雅碧峰峡实地考察低空旅游飞行环境。美联华邦公司与碧峰峡公司双方就低空旅游合作进行了洽谈，美联华邦公司对碧峰峡的生态环境和丰富产品的多样性十分认可，更认同万贯的经营理念。</p>
            </li>
         --%>
        </ul>
    </div>
</div>
</body>
</html>