/** 点击导航菜单项 */
var clickNavMenu = function(index){
    $("a", $("#navMenu")).eq(index).click();
}
/** 隐藏左边菜单栏 */
var hideMenuBar = function(){
    var sidebar = $("#sidebar");
    var sidebarBtn = $("#sidebar > .toggleCollapse > div");
    if (sidebar.css("display") != "none") {
        sidebarBtn.click();
    }
}
/** 显示左边菜单栏 */
var showMenuBar = function(){
    var sidebar = $("#sidebar_s");
    var sidebarBtn = $("#sidebar_s > .collapse > .toggleCollapse > div");
    if (sidebar.css("display") != "none") {
        sidebarBtn.click();
    }
}
/** 扩展jQuery，实现页面元素可直接获取上级unit box容器 */
$.fn.extend({
    getParentUnitBox: function(){
        return $(this).parents(".unitBox:first");
    }
});

/** 覆盖DWZ的翻页参数设置 */
$.extend(DWZ.pageInfo, {pageNum:"pageNo", numPerPage:"pageSize", orderField:"orderBy", orderDirection:"s"});

/** 覆盖DWZ的switchEnv函数 */
(function($){
    $.fn.switchEnv = function(){
        return this.each(function(){
            var $this = $(this);
            $this.click(function(){
                if ($this.hasClass("selected")) {
                    _hide($this);
                }
                else {
                    _show($this);
                }
                return false;
            });
            // 由于父容器的click返回false，这里需要设置弹出层中的link点击时阻止事件冒泡，使链接可以被激活。
            $this.find(">ul>li>a").click(function(event){
                event.stopPropagation();
            });
        });
    }
    function _show($box){
        $box.addClass("selected");
        $(document).bind("click", {
            box: $box
        }, _handler);
    }
    
    function _hide($box){
        $box.removeClass("selected");
        $(document).unbind("click", _handler);
    }
    
    function _handler(event){
        _hide(event.data.box);
    }
})(jQuery);

/** 覆盖DWZ的theme函数，支持多个部分的样式替换。*/
$.fn.extend({
    theme: function(options){
        var op = $.extend({
            dwzTheme: "coo/mvc/static/dwz/dwz/themes",
            fixTheme: "coo/mvc/static/dwz/fix/themes",
            appTheme: "themes"
        }, options);
        var dwzThemeHref = op.dwzTheme + "/#theme#/style.css";
        var fixThemeHref = op.fixTheme + "/#theme#/style.css";
        var appThemeHref = op.appTheme + "/#theme#/style.css";
        return this.each(function(){
            var jThemeLi = $(this).find(">li[theme]");
            var setTheme = function(themeName){
                var dwzThemeLink = $("head").find("link[href*='" + op.dwzTheme + "'][href$='style.css']");
                var fixThemeLink = $("head").find("link[href*='" + op.fixTheme + "'][href$='style.css']");
                var appThemeLink = $("head").find("link[href^='" + op.appTheme + "'][href$='style.css']");
                if (dwzThemeLink.size() > 0) {
                    dwzThemeLink.attr("href", dwzThemeHref.replace("#theme#", themeName));
                }
                if (fixThemeLink.size() > 0) {
                    fixThemeLink.attr("href", fixThemeHref.replace("#theme#", themeName));
                }
                if (appThemeLink.size() > 0) {
                    appThemeLink.attr("href", appThemeHref.replace("#theme#", themeName));
                }
                jThemeLi.find(">div").removeClass("selected");
                jThemeLi.filter("[theme=" + themeName + "]").find(">div").addClass("selected");
                
                if ($.isFunction($.cookie)) 
                    $.cookie("dwz_theme", themeName);
            }
            
            jThemeLi.each(function(index){
                var $this = $(this);
                var themeName = $this.attr("theme");
                $this.addClass(themeName).click(function(){
                    setTheme(themeName);
                });
            });
            if ($.isFunction($.cookie)) {
                var themeName = $.cookie("dwz_theme");
                if (themeName) {
                    setTheme(themeName);
                }
            }
        });
    }
});

/** 覆盖DWZ的jPanel函数，修复Panel的头部不能放置链接内容的Bug。*/
$.fn.extend({
	jPanel : function(options) {
		var op = $.extend({
			header : "panelHeader",
			headerC : "panelHeaderContent",
			content : "panelContent",
			coll : "collapsable",
			exp : "expandable",
			footer : "panelFooter",
			footerC : "panelFooterContent"
		}, options);
		return this.each(function() {
			var $panel = $(this);
			var close = $panel.hasClass("close");
			var collapse = $panel.hasClass("collapse");

			var $content = $(">div", $panel).addClass(op.content);
			var title = $(">h1", $panel).wrap(
					'<div class="' + op.header + '"><div class="' + op.headerC
							+ '"></div></div>');
			if (collapse)
				// 指定收缩按钮的class名称，不要影响到其它链接。
				$("<a class=\"panelCollapseBtn\" href=\"\"></a>").addClass(close ? op.exp : op.coll)
						.insertAfter(title);

			var header = $(">div:first", $panel);
			var footer = $(
					'<div class="' + op.footer + '"><div class="' + op.footerC
							+ '"></div></div>').appendTo($panel);

			var defaultH = $panel.attr("defH") ? $panel.attr("defH") : 0;
			var minH = $panel.attr("minH") ? $panel.attr("minH") : 0;
			if (close)
				$content.css({
					height : "0px",
					display : "none"
				});
			else {
				if (defaultH > 0)
					$content.height(defaultH + "px");
				else if (minH > 0) {
					$content.css("minHeight", minH + "px");
				}
			}
			if (!collapse)
				return;
			// 获取收缩按钮时排除其它无关链接元素
			var $pucker = $("a.panelCollapseBtn", header);
			var inH = $content.innerHeight() - 6;
			if (minH > 0 && minH >= inH)
				defaultH = minH;
			else
				defaultH = inH;
			$pucker.click(function() {
				if ($pucker.hasClass(op.exp)) {
					$content.jBlindDown({
						to : defaultH,
						call : function() {
							$pucker.removeClass(op.exp).addClass(op.coll);
							if (minH > 0)
								$content.css("minHeight", minH + "px");
						}
					});
				} else {
					if (minH > 0)
						$content.css("minHeight", "");
					if (minH >= inH)
						$content.css("height", minH + "px");
					$content.jBlindUp({
						call : function() {
							$pucker.removeClass(op.coll).addClass(op.exp);
						}
					});
				}
				return false;
			});
		});
	}
});

/** 覆盖DWZ的主从结构组件，支持checkbox表单控件。*/
$.fn.extend({
	itemDetail: function(){
		return this.each(function(){
			var $table = $(this).css("clear","both"), $tbody = $table.find("tbody");
			var fields=[];
	
			$table.find("tr:first th[type]").each(function(i){
				var $th = $(this);
				var field = {
					type: $th.attr("type") || "text",
					patternDate: $th.attr("dateFmt") || "yyyy-MM-dd",
					name: $th.attr("name") || "",
					defaultVal: $th.attr("defaultVal") || "",
					size: $th.attr("size") || "12",
					enumUrl: $th.attr("enumUrl") || "",
					lookupGroup: $th.attr("lookupGroup") || "",
					lookupUrl: $th.attr("lookupUrl") || "",
					lookupPk: $th.attr("lookupPk") || "id",
					suggestUrl: $th.attr("suggestUrl"),
					suggestFields: $th.attr("suggestFields"),
					postField: $th.attr("postField") || "",
					fieldClass: $th.attr("fieldClass") || "",
					fieldAttrs: $th.attr("fieldAttrs") || ""
				};
				fields.push(field);
			});
			
			$tbody.find("a.btnDel").click(function(){
				var $btnDel = $(this);
				
				if ($btnDel.is("[href^=javascript:]")){
					$btnDel.parents("tr:first").remove();
					initSuffix($tbody);
					return false;
				}
				
				function delDbData(){
					$.ajax({
						type:'POST', dataType:"json", url:$btnDel.attr('href'), cache: false,
						success: function(){
							$btnDel.parents("tr:first").remove();
							initSuffix($tbody);
						},
						error: DWZ.ajaxError
					});
				}
				
				if ($btnDel.attr("title")){
					alertMsg.confirm($btnDel.attr("title"), {okCall: delDbData});
				} else {
					delDbData();
				}
				
				return false;
			});
	
			var addButTxt = $table.attr('addButton') || "Add New";
			if (addButTxt) {
				var $addBut = $('<div class="button"><div class="buttonContent"><button type="button">'+addButTxt+'</button></div></div>').insertBefore($table).find("button");
				var $rowNum = $('<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>').insertBefore($table);
				
				var trTm = "";
				$addBut.click(function(){
					if (! trTm) trTm = trHtml(fields);
					var rowNum = 1;
					try{rowNum = parseInt($rowNum.val())} catch(e){}
	
					for (var i=0; i<rowNum; i++){
						var $tr = $(trTm);
						$tr.appendTo($tbody).initUI().find("a.btnDel").click(function(){
							$(this).parents("tr:first").remove();
							initSuffix($tbody);
							return false;
						});
					}
					initSuffix($tbody);
				});
			}
		});
		
		/**
		 * 删除时重新初始化下标
		 */
		function initSuffix($tbody) {
			$tbody.find('>tr').each(function(i){
				$(':input, a.btnLook, a.btnAttach', this).each(function(){
					var $this = $(this), name = $this.attr('name'), val = $this.val();
	
					if (name) $this.attr('name', name.replaceSuffix(i));
					
					var lookupGroup = $this.attr('lookupGroup');
					if (lookupGroup) {$this.attr('lookupGroup', lookupGroup.replaceSuffix(i));}
					
					var suffix = $this.attr("suffix");
					if (suffix) {$this.attr('suffix', suffix.replaceSuffix(i));}
					
					if (val && val.indexOf("#index#") >= 0) $this.val(val.replace('#index#',i+1));
				});
			});
		}
		
		function tdHtml(field){
			var html = '', suffix = '';
			
			if (field.name.endsWith("[#index#]")) suffix = "[#index#]";
			else if (field.name.endsWith("[]")) suffix = "[]";
			
			var suffixFrag = suffix ? ' suffix="' + suffix + '" ' : '';
			
			var attrFrag = '';
			if (field.fieldAttrs){
				var attrs = DWZ.jsonEval(field.fieldAttrs);
				for (var key in attrs) {
					attrFrag += key+'="'+attrs[key]+'"';
				}
			}
			switch(field.type){
				// 增加checkbox支持
				case 'checkbox':
					html = '<input type="checkbox" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" '+attrFrag+'/>';
					break;
				case 'del':
					html = '<a href="javascript:void(0)" class="btnDel '+ field.fieldClass + '">删除</a>';
					break;
				case 'lookup':
					var suggestFrag = '';
					if (field.suggestFields) {
						suggestFrag = 'autocomplete="off" lookupGroup="'+field.lookupGroup+'"'+suffixFrag+' suggestUrl="'+field.suggestUrl+'" suggestFields="'+field.suggestFields+'"' + ' postField="'+field.postField+'"';
					}
	
					html = '<input type="hidden" name="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
						+ '<input type="text" name="'+field.name+'"'+suggestFrag+' lookupPk="'+field.lookupPk+'" size="'+field.size+'" class="'+field.fieldClass+'"/>'
						+ '<a class="btnLook" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" title="查找带回">查找带回</a>';
					break;
				case 'attach':
					html = '<input type="hidden" name="'+field.lookupGroup+'.'+field.lookupPk+suffix+'"/>'
						+ '<input type="text" name="'+field.name+'" size="'+field.size+'" readonly="readonly" class="'+field.fieldClass+'"/>'
						+ '<a class="btnAttach" href="'+field.lookupUrl+'" lookupGroup="'+field.lookupGroup+'" '+suggestFrag+' lookupPk="'+field.lookupPk+'" width="560" height="300" title="查找带回">查找带回</a>';
					break;
				case 'enum':
					$.ajax({
						type:"POST", dataType:"html", async: false,
						url:field.enumUrl, 
						data:{inputName:field.name}, 
						success:function(response){
							html = response;
						}
					});
					break;
				case 'date':
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" class="date '+field.fieldClass+'" dateFmt="'+field.patternDate+'" size="'+field.size+'"/>'
						+'<a class="inputDateButton" href="javascript:void(0)">选择</a>';
					break;
				default:
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" '+attrFrag+'/>';
					break;
			}
			return '<td>'+html+'</td>';
		}
		
		function trHtml(fields){
			var html = '';
			$(fields).each(function(){
				html += tdHtml(this);
			});
			return '<tr class="unitBox">'+html+'</tr>';
		}
	}
});

/** 重新加载指定的dialog */
var reloadDialog = function(rel) {
    var dialog = $("body").data(rel);
    if(dialog){
        $.pdialog.reload(dialog.data("url"),{dialogId:rel});
    }
}

/** 表单提交后刷新指定dialog的回调函数 */
var dialogReloadDone = function(json){
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok) {
        if (json.rel) {
            reloadDialog(json.rel);
        }
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
    }
}

/** 表单提交后关闭指定dialog的回调函数 */
var dialogCloseDone = function(json){
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok) {
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
		if (json.rel) {
        	$.pdialog.close(json.rel);
        }
		if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else { //重新载入当前navTab页面
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
    }
}
