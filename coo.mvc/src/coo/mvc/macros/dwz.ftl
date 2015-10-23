<#--
 * 头部引用。
 -->
<#macro head>
    <link href="${ctx}/base/icons/icons.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/dwz/themes/default/style.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/dwz/themes/default/style.fix.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/dwz/themes/css/core.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/dwz/themes/css/core.fix.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
	<link href="${ctx}/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css"/>
	<link href="${ctx}/dwz/uploadify/css/uploadify.fix.css" rel="stylesheet" type="text/css"/>
	<!--[if IE]>
	<link href="${ctx}/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css"/>
	<![endif]-->
	<!--[if lte IE 9]>
	<script src="${ctx}/dwz/js/speedup.js" type="text/javascript"></script>
	<![endif]-->
	
	<script src="${ctx}/base/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/uploadify/scripts/jquery.uploadify.extends.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/dwz.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/js/dwz.fix.js" type="text/javascript"></script>
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
	            <div>收缩</div>
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
 * 表单组件。
 *
 * action：表单提交的相对路径
 * method：表单提交的方式
 * onsubmit：表单提交时的回调函数
 * targetType：回调方式，支持navTab、dialog、dialogReload、dialogClose四种
 * class：表单的样式
 * attributes：表单的其它属性
 -->
<#macro form action onsubmit method="post" targetType="dialog" class="pageForm required-validate" attributes...>
    <#if !onsubmit??>
        <#if targetType == "navTab">
            <#local onsubmit="return validateCallback(this, navTabAjaxDone);" />
        </#if>
        <#if targetType == "dialog">
            <#local onsubmit="return validateCallback(this, dialogAjaxDone);" />
        </#if>
        <#if targetType == "dialogReload">
            <#local onsubmit="return validateCallback(this, dialogReloadDone);" />
        </#if>
        <#if targetType == "dialogClose">
            <#local onsubmit="return validateCallback(this, dialogCloseDone);" />
        </#if>
    </#if>
	<form action="<@s.url "${action}" />" method="${method}" class="${class}"
	      onsubmit="${onsubmit}" ${s.getAttributes(attributes)}>
	    <#nested>
	</form>
</#macro>

<#--
 * 表单按钮栏。
 *
 * showSubmitBtn：是否显示提交按钮
 * submitBtnText：提交按钮上的文字
 * showCancelBtn：是否显示取消按钮
 * cancelBtnText：取消按钮上的文字
 -->
<#macro formBar showSubmitBtn=true submitBtnText="保存" showCancelBtn=true cancelBtnText="取消">
	<div class="formBar">
	    <ul>
	        <#nested>
			<#if showSubmitBtn>
        		<@button text="${submitBtnText}" type="submit" />
    		</#if>
			<#if showCancelBtn>
        		<@button text="${cancelBtnText}" class="close" />
    		</#if>
	    </ul>
	</div>
</#macro>

<#--
 * 按钮组件。
 *
 * text：按钮文本
 * type: 按钮类型
 * active：是否激活
 * attributes：按钮的其它属性
 -->
<#macro button text type="button" active=false attributes...>
	<#if active>
        <#local buttonClass="buttonActive" />
    <#else>
    	<#local buttonClass="button" />
    </#if>
	<li>
	    <div class="${buttonClass}">
	        <div class="buttonContent">
	            <button type="${type}" ${s.getAttributes(attributes)}>${text}</button>
	        </div>
	    </div>
	</li>
</#macro>

<#--
 * 链接组件。
 *
 * href：链接的相对路径
 * rel：navTab和dialog链接用到的rel属性
 * width：dialog链接用来指定打开窗口的宽度，可以指定为SS、S、M、L、XL或具体的宽度
 * height：dialog链接用来指定打开窗口的高度，可以指定为SS、S、M、L、XL或具体的宽度
 * target：链接类型，对应dwz使用的链接类型，如navTab、dialog、ajaxTodo、selectedTodo等
 * mask：dialog链接用来指定打开窗口是否为模态窗口
 * attributes：链接的其它属性
 -->
<#macro a href rel width="M" height="M" target="navTab" mask=true minable=false maxable=false resizable=false drawable=true attributes...>
    <#if !rel?? && (target == "navTab" || target == "dialog")>
        <#local relStartIndex = href?last_index_of("/") />
        <#local relEndIndex = href?last_index_of("?") />
        <#if relStartIndex == -1>
            <#local relStartIndex = 0 />
        </#if>
        <#if relEndIndex == -1>
            <#local rel = href?substring(relStartIndex + 1)  />
        <#else>
            <#local rel = href?substring(relStartIndex + 1, relEndIndex)  />
        </#if>
    </#if>
    <@compress single_line=true>
    <a href="<@s.url href />" target="${target}"
        <#if target == "dialog">
       rel="${rel}"
       mask="${mask?c}"
       minable="${minable?c}"
       maxable="${maxable?c}"
       resizable="${resizable?c}"
       drawable="${drawable?c}"
            <#if width == "SSS">
                <#local width="300" />
            </#if>
            <#if width == "SS">
                <#local width="400" />
            </#if>
            <#if width == "S">
                <#local width="500" />
            </#if>
            <#if width == "M">
                <#local width="700" />
            </#if>
            <#if width == "L">
                <#local width="900" />
            </#if>
            <#if width == "XL">
                <#local width="1100" />
            </#if>
       width="${width}"
            <#if height == "SSS">
                <#local height="250" />
            </#if>
            <#if height == "SS">
                <#local height="300" />
            </#if>
            <#if height == "S">
                <#local height="400" />
            </#if>
            <#if height == "M">
                <#local height="500" />
            </#if>
            <#if height == "L">
                <#local height="600" />
            </#if>
            <#if height == "XL">
                <#local height="700" />
            </#if>
       height="${height}"
        <#else>
            <#if rel> rel="${rel}"</#if>
        </#if>
    ${s.getAttributes(attributes)}><#nested></a>
    </@compress>
</#macro>

<#--
 * 多选组。
 *
 * path：多选组绑定的属性路径
 * items：多选组选项集合对象
 * itemValue：多选组值的属性名
 * itemLabel：多选组文本的属性名
 * required: 是否必填
 * showCheckAllBtn: 是否显示全选按钮
 -->
<#macro checkboxs path items itemValue itemLabel box required=true showCheckAllBtn=true>
    <@s.bind path />
    <#if items?size gt 0 && showCheckAllBtn>
    <label class="dd-span"><input type="checkbox" class="checkboxCtrl" group="${s.name}"<#if box??> box="${box}"</#if> />全选</label><br/>
    </#if>
    <#if required>
        <@s.checkboxs path=path items=items itemLabel=itemLabel itemValue=itemValue prefix="<label class='dd-span'>" suffix="</label>" id=path class="required" />
    <#else>
        <@s.checkboxs path=path items=items itemLabel=itemLabel itemValue=itemValue prefix="<label class='dd-span'>" suffix="</label>" id=path />
    </#if>
</#macro>

<#--
 * 图片组件。
 * 
 * path: 图片绑定的属性路径
 * width: 图片宽度
 * height: 图片高度
 * size: 图片大小限制
 * readonly: 是否只读
 -->
<#macro img path width height limit=0 size=1024 readonly=false fileObjName="attFile" buttonText="上传图片" buttonWidth=70 buttonHeight=18>
	<@s.bind path />
    <#local random = .now?datetime?string("yyyyMMddHHmmssSSS")>
    <#local inputId = "imgInput_" + random>
    <#local queueId = "imgQueue_" + random>
    <#local fileId = "imgFile_" + random>
    <#local multi = s.status.actualValue?? && s.status.actualValue?is_enumerable >
    <#if !readonly>
    	<input id="${inputId}" type="file" uploaderOption="{
			swf: '${ctx}/dwz/uploadify/scripts/uploadify.swf',
			uploader: 'assist/att-file-upload.json',
			fileObjName: '${fileObjName}',
			buttonText: '${buttonText}',
			width: ${buttonWidth},
			height: ${buttonHeight},
			imgWidth: ${width},
			imgHeight: ${height},
			multi: ${multi?c},
			removeCompleted: false,
			uploadLimit: <#if multi>${limit}<#else>1</#if>,
			queueSizeLimit: <#if multi>${limit}<#else>1</#if>,
			fileSizeLimit: '${size}KB',
			fileTypeDesc: '*.jpg;*.jpeg;*.gif;*.png;',
			fileTypeExts: '*.jpg;*.jpeg;*.gif;*.png;',
			inputName: '${s.name}',
			queueID: '${queueId}',
			onSelectError: uploadify_onSelectError,
			onInit: img_onInit,
			onUploadStart: img_onUploadStart,
			onUploadSuccess: img_onUploadSuccess
		}" />
    </#if>
    <div id="${queueId}" class="fileQueue">
	    <#if s.status.value??>
	    	<#if multi>
		    	<#list s.status.actualValue as image>
			    	<div id="${fileId}_${image_index}" class="uploadify-queue-item" style="width:${width}px;">
			            <#if !readonly>
			                <div class="cancel">
			                	<a href="javascript:uploadify_cancel('${inputId}','${fileId}_${image_index}');">X</a>
			                </div>
			            </#if>
			            <div class="uploadify-queue-image">
			            	<img src="${image.path}" width="${width}" height="${height}" />
			            	<#if !readonly><input type="hidden" name="${s.name}[${image_index}]" value="${image.id}"></#if>
			            </div>
			        </div>
		        </#list>
		    <#else>
		        <div id="${fileId}" class="uploadify-queue-item" style="width:${width}px;">
		            <#if !readonly>
		                <div class="cancel">
		                	<a href="javascript:uploadify_cancel('${inputId}','${fileId}');">X</a>
		                </div>
		            </#if>
		            <div class="uploadify-queue-image">
		            	<img src="${s.status.actualValue.path}" width="${width}" height="${height}" />
		            	<#if !readonly><@s.hidden path /></#if>
		            </div>
		        </div>
	        </#if>
	    </#if>
	</div>
</#macro>

<#--
 * 分页表单。
 *
 * action：表单提交的相对路径
 * onsubmit：表单提交时的回调函数
 * targetType：分页表单类型，支持navTab、dialog、div类型。
 * rel：局部刷新的div的id
 * alt：全文检索输入框上的提示信息
 * searchModel：搜索条件对象
 * showKeyword：是否显示全文搜索文本框
 * buttonText：检索按钮的文本
 * method：表单提交的方式
 -->
<#macro pageForm action onsubmit targetType="navTab" rel="" alt="" searchModel=searchModel showKeyword=true buttonText="检索" method="post">
    <#if !onsubmit??>
        <#if targetType == "navTab">
            <#local onsubmit="return navTabSearch(this, '${rel}');" />
        </#if>
        <#if targetType == "dialog">
            <#local onsubmit="return dialogSearch(this);" />
        </#if>
        <#if targetType == "div">
            <#local onsubmit="return divSearch(this, '${rel}');" />
        </#if>
    </#if>
    <@s.form id="pagerForm" method=method action=action onsubmit=onsubmit>
    <input type="hidden" name="pageNo" value="${searchModel.pageNo}"/>
    <input type="hidden" name="pageSize" value="${searchModel.pageSize}"/>
    <input type="hidden" name="orderBy" value="${searchModel.orderBy}"/>
    <input type="hidden" name="sort" value="${searchModel.sort}"/>
    <div class="searchBar">
        <div class="subBar">
            <ul>
                <#nested>
                <#if showKeyword>
                    <li><input type="text" name="keyword" value="${searchModel.keyword}" title="${alt}"/></li>
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
            <#local onchange="navTabPageBreak({numPerPage:this.value}, '${rel}');" />
        </#if>
        <#if targetType == "dialog">
            <#local onchange="dialogPageBreak({numPerPage:this.value}, '${rel}');" />
        </#if>
    </#if>
<div class="pages">
    <span>显示</span>
    <#local options = {"20":20, "30":30, "50":50, "80":80, "100":100}>
    <select name="pageSize" class="combox" onchange="${onchange}">
        <@s.options items=options values=pageModel.size />
    </select>
    <span>条，共${pageModel.count}条</span>
</div>
<div class="pagination" targetType="${targetType}" rel="${rel}" totalCount="${pageModel.count}"
     numPerPage="${pageModel.size}" pageNumShown="10" currentPage="${pageModel.number}"></div>
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
            <input type="hidden" name="${paramName}" value="${params[paramName]}"/>
            </#list>
        </#if>
    </@s.form>
</#macro>