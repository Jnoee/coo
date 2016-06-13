<#--
 * 头部引用。
 -->
<#macro head>
	<link href="${ctx}/dwz/themes/base/style.css" rel="stylesheet" />
	<link href="${ctx}/dwz/themes/base/core.css" rel="stylesheet" />
	<link href="${ctx}/dwz/themes/base/print.css" rel="stylesheet" media="print"/>
	<link href="${ctx}/dwz/external/awesome/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${ctx}/dwz/external/uploadify/css/uploadify.css" rel="stylesheet" />
	<link href="${ctx}/dwz/external/slider/bjqs.css" rel="stylesheet" />
	<link href="${ctx}/dwz/uploadify.extends.css" rel="stylesheet" />
	<!--[if IE]>
	<link href="${ctx}/dwz/themes/base/ieHack.css" rel="stylesheet" />
	<![endif]-->
	
	<script src="${ctx}/dwz/external/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/jquery.bgiframe.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/external/slider/bjqs-1.3.min.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/jquery.uploadify.extends.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/dwz.js" type="text/javascript"></script>
	<script src="${ctx}/dwz/dwz.config.js" type="text/javascript"></script>
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
	            <h2 class="fa-desktop">主菜单</h2>
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
	                        <a href="javascript:;"><span class="fa-home">我的主页</span></a>
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
 * class：表单的样式
 * attributes：表单的其它属性
 -->
<#macro form action class="validateForm" attributes...>
	<form action="<@s.url "${action}" />" class="${class}" ${s.getAttributes(attributes)}>
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
			<#if showSubmitBtn>
        		<@button text="${submitBtnText}" type="submit" />
    		</#if>
	        <#nested>
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
 * target：链接类型，对应dwz使用的链接类型，如navTab、dialog、ajaxTodo、selectedTodo等
 * mask：dialog链接用来指定打开窗口是否为模态窗口
 * attributes：链接的其它属性
 -->
<#macro a href rel target="navTab" attributes...>
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
	    <a href="<@s.url href />" target="${target}" <#if rel> rel="${rel}"</#if> ${s.getAttributes(attributes)}>
	    	<#nested>
	    </a>
    </@compress>
</#macro>

<#--
 * 单选组。
 *
 * path：单选组绑定的属性路径
 * items：单选组选项集合对象
 * itemValue：单选组值的属性名
 * itemLabel：单选组文本的属性名
 -->
<#macro radios path items itemValue itemLabel>
	<@s.radios path=path items=items itemValue=itemValue itemLabel=itemLabel prefix="<label class='unit'>" suffix="</label>" />
</#macro>

<#--
 * 单选按钮。
 *
 * path：单选组绑定的属性路径
 * trueText：绑定的属性为true时显示的文本
 * falseText：绑定的属性为false时显示的文本
 -->
<#macro radio path trueText="是" falseText="否">
	<@s.radio path=path prefix="<label class='unit'>" suffix="</label>" trueText=trueText falseText=falseText />
</#macro>

<#--
 * 多选组。
 *
 * path：多选组绑定的属性路径
 * items：多选组选项集合对象
 * itemValue：多选组值的属性名
 * itemLabel：多选组文本的属性名
 * box: 分组容器标签名，控制全选按钮影响的范围
 * required: 是否必填
 * showCheckAllBtn: 是否显示全选按钮
 -->
<#macro checkboxs path items itemValue itemLabel box itemWidth="auto" required=true showCheckAllBtn=true>
    <@s.bind path />
    <#if items?size gt 0 && showCheckAllBtn>
    	<label class="unit"><input type="checkbox" class="checkboxCtrl" group="${s.name}"<#if box??> box="${box}"</#if> />全选</label><br style="clear:both" />
    </#if>
    <#if required>
        <@s.checkboxs path=path items=items itemLabel=itemLabel itemValue=itemValue prefix="<label class='unit' style='width:${itemWidth}'>" suffix="</label>" id=path class="required" />
    <#else>
        <@s.checkboxs path=path items=items itemLabel=itemLabel itemValue=itemValue prefix="<label class='unit' style='width:${itemWidth}'>" suffix="</label>" id=path />
    </#if>
</#macro>

<#--
 * 价格组件。
 *
 * path：文本框绑定的属性路径
 * attributes：文本框的其它属性
 -->
<#macro price path required=true attributes...>
    <@s.bind path />
    <@s.replaceAttributes attributes />
    <@compress>
    	<input type="text" id="${s.id}" name="${s.name}" value="${s.status.actualValue?string('0.00')}" class="price <#if required>required</#if>" ${s.getAttributes(attributes)} />
    </@compress>
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
<#macro img path width height uploadUrl="assist/att-file-upload.json" limit=0 size=1024 readonly=false fileObjName="attFile" buttonText="上传图片（.jpg .gif .png）" buttonWidth=150 buttonHeight=18>
	<@s.bind path />
    <#local random = s.name + "_" + .now?datetime?string("yyyyMMddHHmmssSSS")>
    <#local inputId = "imgInput_" + random>
    <#local queueId = "imgQueue_" + random>
    <#local fileId = "imgFile_" + random>
    <#local multi = s.status.actualValue?? && s.status.actualValue?is_enumerable >
    <#if !readonly>
    	<input id="${inputId}" type="file" uploaderOption="{
			swf: '${ctx}/dwz/external/uploadify/scripts/uploadify.swf',
			uploader: '${uploadUrl}',
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
			fileTypeDesc: '*.jpg;*.gif;*.png;',
			fileTypeExts: '*.jpg;*.gif;*.png;',
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
			            <div class="uploadify-queue-image">
			            	<img src="${image.path}" width="${width}" height="${height}" />
			            	<#if !readonly><input type="hidden" name="${s.name}" value="${image.id}"></#if>
			            </div>
			            <#if !readonly>
			            	<div class="uploadify-queue-bar">
								<div><a href="javascript:uploadify_cancel('${inputId}','${fileId}_${image_index}');" title="删除"><i class="fa fa-trash"></i></a></div>
							</div>
			            </#if>
			        </div>
		        </#list>
		    <#else>
		        <div id="${fileId}" class="uploadify-queue-item" style="width:${width}px;">
		            <div class="uploadify-queue-image">
		            	<img src="${s.status.actualValue.path}" width="${width}" height="${height}" />
		            	<#if !readonly><@s.hidden path /></#if>
		            </div>
		            <#if !readonly>
		            	<div class="uploadify-queue-bar">
							<div><a href="javascript:uploadify_cancel('${inputId}','${fileId}');" title="删除"><i class="fa fa-trash"></i></a></div>
						</div>
		            </#if>
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
 * rel：局部刷新的div的id
 * alt：全文检索输入框上的提示信息
 * searchModel：搜索条件对象
 * showKeyword：是否显示全文搜索文本框
 * buttonText：检索按钮的文本
 -->
<#macro pageForm action onsubmit rel="" alt="" searchModel=searchModel showKeyword=true buttonText="检索">
    <@s.form action=action class="pagerForm" onsubmit=onsubmit rel=rel>
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
 -->
<#macro pageNav pageModel>
	<div class="pages">
	    <span>显示</span>
	    <#local options = {"20":20, "30":30, "50":50, "80":80, "100":100}>
	    <select class="combox">
	        <@s.options items=options values=pageModel.size />
	    </select>
	    <span>条，共${pageModel.count}条</span>
	</div>
	<div class="pagination" totalCount="${pageModel.count}" numPerPage="${pageModel.size}" pageNumShown="10" currentPage="${pageModel.number}"></div>
</#macro>

<#--
 * 局部刷新时需要用到的表单宏。
 *
 * action：局部刷新需要提交地址
 * params：局部刷新需要的参数
 -->
<#macro reload action params...>
    <@s.form class="pagerForm" method="get" action=action>
        <#if params??>
            <#list params?keys as paramName>
            <input type="hidden" name="${paramName}" value="${params[paramName]}"/>
            </#list>
        </#if>
    </@s.form>
</#macro>