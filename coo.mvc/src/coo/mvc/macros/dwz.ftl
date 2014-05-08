<#--
 * 头部引用。
 -->
<#macro head>
    <@std.head />
    <link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="${ctx}/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
    <!--[if IE]>
    <link href="${ctx}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <!--[if lte IE 9]>
    <script src="${ctx}/dwz/js/speedup.js" type="text/javascript"></script>
    <![endif]-->
    <script src="${ctx}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/js/dwz.min.js" type="text/javascript"></script>
    <script src="${ctx}/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
    <link href="${ctx}/fix/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/fix/themes/css/core.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/fix/js/fix.js" type="text/javascript"></script>
    <#nested>
</#macro>

<#--
 * 左边菜单。
 -->
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

<#--
 * 主页navTab容器。
 -->
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

<#--
 * 分页表单。
 *
 * action：表单提交的相对路径
 * searchModel：搜索条件对象
 * showKeyword：是否显示全文搜索文本框
 * buttonText：检索按钮的文本
 * method：表单提交的方式
 * onsubmit：表单提交时的回调函数
 -->
<#macro pageForm action onsubmit targetType="navTab" rel="" searchModel=searchModel showKeyword=true buttonText="检索" method="post">
    <#if !onsubmit??>
        <#if targetType == "navTab">
            <#assign onsubmit="return navTabSearch(this, rel);" />
        </#if>
        <#if targetType == "dialog">
            <#assign onsubmit="return dialogSearch(this);" />
        </#if>
        <#if targetType == "div">
            <#assign onsubmit="return divSearch(this, rel);" />
        </#if>
    </#if>
    <@s.form id="pagerForm" method=method action=action onsubmit=onsubmit>
        <input type="hidden" name="pageNo" value="${searchModel.pageNo}" />
        <input type="hidden" name="pageSize" value="${searchModel.pageSize}" />
        <input type="hidden" name="orderBy" value="${searchModel.orderBy}" />
        <input type="hidden" name="sort" value="${searchModel.sort}" />
        <div class="searchBar">
            <div class="subBar">
                <ul>
                    <#nested>
                    <#if showKeyword>
                    <li><input type="text" name="keyword" value="${searchModel.keyword}" /></li>
                    </#if>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">${buttonText}</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </@s.form>
</#macro>

<#--
 * 分页导航条。
 *
 * pageModel：分页对象
 * onchange：每页条数选择框的onchange事件。不设置时根据targetType类型自动设置适合的事件函数，设置时将覆盖默认设置。
 * targetType：导航类型（navTab/dialog）
 -->
<#macro pageNav pageModel onchange targetType="navTab" rel="">
    <#if !onchange??>
        <#if targetType == "navTab">
            <#assign onchange="navTabPageBreak({numPerPage:this.value}, rel);" />
        </#if>
        <#if targetType == "dialog">
            <#assign onchange="dialogPageBreak({numPerPage:this.value}, rel);" />
        </#if>
    </#if>
    <div class="pages">
        <span>显示</span>
    	<#assign options = {"20":20, "30":30, "50":50, "80":80, "100":100}>
        <select name="pageSize" class="combox" onchange="${onchange}">
            <@s.options items=options values=pageModel.size />
        </select>
        <span>条，共${pageModel.count}条</span>
    </div>
    <div class="pagination" targetType="${targetType}" totalCount="${pageModel.count}" numPerPage="${pageModel.size}" pageNumShown="10" currentPage="${pageModel.number}"></div>
</#macro>

<#--
 * 局部刷新时需要用到的表单宏。
 *
 * action：局部刷新需要提交地址
 * params：局部刷新需要的参数
 -->
<#macro reload action params...>
    <@s.form id="pagerForm" method="get" action=action>
        <#if params??>
            <#list params?keys as paramName>
            	<input type="hidden" name="${paramName}" value="${params[paramName]}" />
            </#list>
        </#if>
    </@s.form>
</#macro>