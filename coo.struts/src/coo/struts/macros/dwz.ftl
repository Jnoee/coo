<#macro head>
<@std.head />
<link href="${R.dwz}/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${R.dwz}/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="${R.dwz}/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="${R.dwz}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
<link href="${R.dwz}/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if lte IE 9]>
<script src="${R.dwz}/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="${R.dwz}/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${R.dwz}/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${R.dwz}/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${R.dwz}/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${R.dwz}/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${R.dwz}/js/dwz.min.js" type="text/javascript"></script>
<script src="${R.dwz}/js/dwz.regional.zh.js" type="text/javascript"></script>
<link href="${R.fix}/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="${R.fix}/themes/css/core.css" rel="stylesheet" type="text/css" />
<script src="${R.fix}/js/fix.js" type="text/javascript"></script>
<#nested>
</#macro>
<#macro leftside>
<div id="leftside">
    <div id="sidebar_s">
        <div class="collapse">
            <div class="toggleCollapse">
                <div></div>
            </div>
        </div>
    </div>
    <div id="sidebar">
        <div class="toggleCollapse">
            <h2>主菜单</h2>
            <div>收缩 </div>
        </div>
        <div class="accordion" fillSpace="sideBar">
            <div class="accordionHeader">
                <h2><span class="a05">Folder</span>子菜单</h2>
            </div>
            <div class="accordionContent"></div>
        </div>
    </div>
</div>
</#macro>
<#macro container>
<div id="container">
    <div id="navTab" class="tabsPage">
        <div class="tabsPageHeader">
            <div class="tabsPageHeaderContent">
                <ul class="navTab-tab">
                    <li tabid="main" class="main">
                        <a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a>
                    </li>
                </ul>
            </div>
            <div class="tabsLeft">left</div>
            <div class="tabsRight">right</div>
            <div class="tabsMore">more</div>
        </div>
        <ul class="tabsMoreList">
            <li>
                <a href="javascript:;">我的主页</a>
            </li>
        </ul>
        <div class="navTab-panel tabsPageContent layoutBox">
            <div class="page unitBox">
                <div style="width:300px;"></div>
            </div>
        </div>
    </div>
</div>
</#macro>
<#macro pagerForm action method="post" onsubmit="return navTabSearch(this);">
<@s.form id="pagerForm" method="${method}" action="${action}" onsubmit="${onsubmit}">
<@s.hidden name="pageNum" />
<@s.hidden name="numPerPage" />
<@s.hidden name="orderField" />
<div class="searchBar">
    <div class="subBar">
        <ul>
            <#nested>
            <li><@s.textfield name="keyText" /></li>
            <li>
                <div class="buttonActive">
                    <div class="buttonContent">
                        <button type="submit">检索 </button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
</@s.form>
</#macro>
<#macro pagerNav pageModel onchange="navTabPageBreak({numPerPage:this.value});">
<div class="pages">
    <span>显示</span>
	<#assign options = {"20":20, "30":30, "50":50, "80":80, "100":100}>
    <@s.select name="numPerPage" list=options listKey="value" listValue="key" cssClass="combox" onchange="${onchange}" /><span>条，共${pageModel.count}条</span>
</div>
<div class="pagination" targetType="navTab" totalCount="${pageModel.count}" numPerPage="${pageModel.size}" pageNumShown="10" currentPage="${pageModel.number}"></div>
</#macro>
<#macro refresh action params...>
<@s.form id="pagerForm" method="get" action="${action}">
<#if params??>
<#list params?keys as paramName>
	<@s.hidden name="${paramName}" value="${params[paramName]}" />
</#list>
</#if>
</@s.form>
</#macro>