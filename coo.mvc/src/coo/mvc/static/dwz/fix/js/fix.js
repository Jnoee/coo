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

/**
 * 扩展String方法
 */
$(function(){
	$.extend(String.prototype, {
		escape: function() { // 处理jquery选择表达式中的特殊字符
			return this.replace(/[#;&,\.\+\*~':"!\^\$\[\]\(\)=>|\/\\]/g, "\\$&");
		},
		cleanParams: function() { // 清除参数
			var index = this.indexOf("?");
			if(index == -1) {
				return this;
			}
			return this.substring(0, index);
		},
		getParams: function() { // 获取参数
			var index = this.indexOf("?");
			if(index == -1) {
				return {};
			}
			var params = this.substring(index + 1);
			return $.parseJSON('{"' + decodeURI(params.replace(/&/g, "\",\"").replace(/=/g,"\":\"")) + '"}');
		}
	});
});

/** 处理jquery选择表达式中的特殊字符 */
var escapeSelector = function(selector) {
	return selector.replace(/[#;&,\.\+\*~':"!\^\$\[\]\(\)=>|\/\\]/g, "\\$&");
}

/** 自定义jqeury validator错误信息显示方法 */
$(function(){
	if ($.validator) {
		$.validator.setDefaults({
			errorPlacement: function(error, element) {
				if(element.hasClass("hideError")) {
					element.attr("title",error.text());
				} else {
					element.parents("dd:first").append(error);
					error.attr("title", error.text());
				}
			}
		});
	}
});

/** 扩展DWZ自定义验证函数，日期区间验证结束日期必须大于开始日期 */
var gtDate = function(element, gtToDate) {
	var endDate = $(element);
	var startDate = $(gtToDate, $(element).closest("form"));
	var pattern = startDate.attr("dateFmt") || "yyyy-MM-dd";
	
	if(endDate.val() && startDate.val()) {
		return endDate.val().parseDate("yyyy-MM-dd") > startDate.val().parseDate(pattern);
	}

	return true;
}

/** 扩展DWZ自定义验证函数，日期区间验证结束日期必须大于等于开始日期 */
var geDate = function(element, geToDate) {
	var endDate = $(element);
	var startDate = $(geToDate, $(element).closest("form"));
	var pattern = startDate.attr("dateFmt") || "yyyy-MM-dd";
	
	if(endDate.val() && startDate.val()) {
		return endDate.val().parseDate("yyyy-MM-dd") >= startDate.val().parseDate(pattern);
	}

	return true;
}

/** 扩展DWZ自定义验证函数，验证正则表达式 */
var regex = function(element, pattern, attrs) {
	var $element = $(element);
	if($element.val()) {
		if(pattern instanceof RegExp) {
			return pattern.test($element.val());
		} else {
			attrs = attrs || "g";
			var ex = new RegExp(pattern, attrs);
			return ex.test($element.val());
		}
	}
	return true;
}

/** 扩展jQuery，实现页面元素可直接获取上级标识为page的div容器 */
$.fn.extend({
    getPageDiv: function(){
        return $(this).closest("div.page");
    }
});

/** 覆盖DWZ的翻页参数设置 */
$.extend(DWZ.pageInfo, {pageNum:"pageNo", numPerPage:"pageSize", orderField:"orderBy", orderDirection:"s"});

/** 覆盖DWZ的Ajax请求错误处理函数 */
$.extend(DWZ, {
	ajaxError: function(xhr, ajaxOptions, thrownError){
		if (alertMsg) {
			if(xhr.status == "404") {
				alertMsg.error("<div>您访问的页面未找到。</div>");
			} else if(xhr.status == "500") {
				alertMsg.error("<div>服务器暂时繁忙，请稍候重试或与管理员联系。</div>");
			} else if(xhr.status == "403") {
				alertMsg.error("<div>您没有执行该操作的权限，请与管理员联系。</div>");
			} else {
				alertMsg.error("<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" 
						+ "<div>ajaxOptions: "+ajaxOptions + "</div>"
						+ "<div>thrownError: "+thrownError + "</div>"
						+ "<div>"+xhr.responseText+"</div>");
			}
		} else {
			if(xhr.status == "404") {
				alert("您访问的页面未找到。");
			} else if(xhr.status == "500") {
				alert("服务器暂时繁忙，请稍候重试或与管理员联系。");
			} else if(xhr.status == "403") {
				alert("您没有执行该操作的权限，请与管理员联系。");
			} else {
				alert("<div>Http status: " + xhr.status + " " + xhr.statusText + "</div>" 
						+ "<div>ajaxOptions: "+ajaxOptions + "</div>"
						+ "<div>thrownError: "+thrownError + "</div>"
						+ "<div>"+xhr.responseText+"</div>");
			}
		}
	}
});

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
				// 屏蔽批量增加输入框
				// var $rowNum = $('<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>').insertBefore($table);
				
				var trTm = "";
				$addBut.click(function(){
					if (! trTm) trTm = trHtml(fields);
					var rowNum = 1;
					// 屏蔽批量增加输入框
					// try{rowNum = parseInt($rowNum.val())} catch(e){}
	
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
				// 增加span文本支持
				case 'span':
					html = '<span class="' + field.fieldClass + '">' + field.defaultVal + '</span>';
					break;
				case 'del':
					html = '<a href="javascript:void(0)" class="btnDel '+ field.fieldClass + '" style="float:none;">删除</a>';
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
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" class="date '+field.fieldClass+'" dateFmt="'+field.patternDate+'" size="'+field.size+'" '+attrFrag+'/>';
					break;
				default:
					html = '<input type="text" name="'+field.name+'" value="'+field.defaultVal+'" size="'+field.size+'" class="'+field.fieldClass+'" '+attrFrag+'/>';
					break;
			}
			return '<td align="center">'+html+'</td>';
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

/** 覆盖dwz的输入框提示信息实现函数 */
(function($){
	$.fn.extend({
		inputAlert: function(){
			return this.each(function(){
				
				var $this = $(this);
				
				function getAltBox(){
					return $this.parent().find("label.alt");
				}
				function altBoxCss(opacity){
					var position = $this.position();
					return {
						width:$this.width(),
						// 增加2像素调整位置。
						top:position.top+2+'px',
						left:position.left +'px',
						opacity:opacity || 0.5
					};
				}
				if (getAltBox().size() < 1) {
					if (!$this.attr("id")) $this.attr("id", $this.attr("name") + "_" +Math.round(Math.random()*10000));
					// 先设置为隐藏，避免占位影响input的位置。
					var $label = $('<label class="alt" style="display:none;" for="'+$this.attr("id")+'">'+$this.attr("alt")+'</label>').appendTo($this.parent());
					// 然后再显示出来，这样定位准确。
					$label.css(altBoxCss(0.5)).show();
					if ($this.val()) $label.hide();
				}
				
				$this.focus(function(){
					getAltBox().css(altBoxCss(0.3));
				}).blur(function(){
					if (!$(this).val()) getAltBox().show().css("opacity",0.5);
				}).keydown(function(){
					getAltBox().hide();
				});
			});
		}
	});
})(jQuery);

/** 覆盖dwz的navTab的reload方法 */
$.extend(navTab, {
	reload: function(url, options){
		var op = $.extend({data:{}, navTabId:"", callback:null}, options);
		var $tab = op.navTabId ? this._getTab(op.navTabId) : this._getTabs().eq(this._currentIndex);
		var $panel =  op.navTabId ? this.getPanel(op.navTabId) : this._getPanels().eq(this._currentIndex);
		
		if ($panel){
			if (!url) {
				url = $tab.attr("url");
			}
			if (url) {
				if ($tab.hasClass("external")) {
					navTab.openExternal(url, $panel);
				} else {
					if ($.isEmptyObject(op.data)) { //获取pagerForm参数
						var $pagerForm = $("#pagerForm", $panel);
						//op.data = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
						// 把pagerForm中带的参数从数组形式转换成op.data上的属性，方便后面对参数进行覆盖。
						var params = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {};
						if(!$.isEmptyObject(params)) {
							jQuery.each(params, function(i, param){
								op.data[param.name]=param.value;
							});
						}
					}
					
					// 如果url上带有参数，则将url参数覆盖掉op.data上的参数。
					$.extend(op.data, url.getParams());
					
					$panel.ajaxUrl({
						type:"POST", url:url.cleanParams(), data:op.data, callback:function(response){
							navTab._loadUrlCallback($panel);
							if ($.isFunction(op.callback)) op.callback(response);
						}
					});
				}
			}
		}
	}
});

/** 重新加载指定的dialog */
var reloadDialog = function(dialog) {
	if(typeof dialog == 'string') {
		dialog = $("body").data(dialog);
	}
    if(dialog){
        $.pdialog.reload(dialog.data("url"),{dialogId:dialog.data("id")});
    }
}

/** 覆盖原dialogAjaxDone函数 */
function dialogAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if (json.navTabId && json.forwardUrl){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else if(!json.navTabId && json.forwardUrl) {
			navTab.reload(json.forwardUrl);
		} else if(json.navTabId && !json.forwardUrl) {
			navTab.reloadFlag(json.navTabId);
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			$.pdialog.closeCurrent();
		}
	}
}

/** 表单提交后刷新指定dialog的回调函数 */
var dialogReloadDone = function(json){
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok) {
        if (json.rel) {
            reloadDialog(json.rel);
        } else {
        	reloadDialog($.pdialog._current);
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
		if (json.navTabId && json.forwardUrl){
			navTab.reload(json.forwardUrl, {navTabId: json.navTabId});
		} else if(!json.navTabId && json.forwardUrl) {
			navTab.reload(json.forwardUrl);
		} else if(json.navTabId && !json.forwardUrl) {
			navTab.reloadFlag(json.navTabId);
		} else {
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args);
		}
    }
}

/** 覆盖DWZ的initUI函数 */
function initUI(_box){
	var $p = $(_box || document);

	$("div.panel", $p).jPanel();

	//tables
	$("table.table", $p).jTable();
	
	// css tables
	$('table.list', $p).cssTable();

	//auto bind tabs
	$("div.tabs", $p).each(function(){
		var $this = $(this);
		var options = {};
		options.currentIndex = $this.attr("currentIndex") || 0;
		options.eventType = $this.attr("eventType") || "click";
		$this.tabs(options);
	});

	$("ul.tree", $p).jTree();
	$('div.accordion', $p).each(function(){
		var $this = $(this);
		$this.accordion({fillSpace:$this.attr("fillSpace"),alwaysOpen:true,active:0});
	});

	$(":button.checkboxCtrl, :checkbox.checkboxCtrl", $p).checkboxCtrl($p);
	
	if ($.fn.combox) $("select.combox",$p).combox();
	
	if ($.fn.xheditor) {
		$("textarea.editor", $p).each(function(){
			var $this = $(this);
			var op = {html5Upload:false, skin: 'vista',tools: $this.attr("tools") || 'full'};
			var upAttrs = [
				["upLinkUrl","upLinkExt","zip,rar,txt"],
				["upImgUrl","upImgExt","jpg,jpeg,gif,png"],
				["upFlashUrl","upFlashExt","swf"],
				["upMediaUrl","upMediaExt","avi"]
			];
			
			$(upAttrs).each(function(i){
				var urlAttr = upAttrs[i][0];
				var extAttr = upAttrs[i][1];
				
				if ($this.attr(urlAttr)) {
					op[urlAttr] = $this.attr(urlAttr);
					op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
				}
			});
			
			$this.xheditor(op);
		});
	}
	
	if ($.fn.uploadify) {
		$(":file[uploaderOption]", $p).each(function(){
			var $this = $(this);
			var options = {
				fileObjName: $this.attr("name") || "file",
				auto: true,
				multi: true,
				onUploadError: uploadifyError
			};
			
			var uploaderOption = DWZ.jsonEval($this.attr("uploaderOption"));
			$.extend(options, uploaderOption);

			DWZ.debug("uploaderOption: "+DWZ.obj2str(uploaderOption));
			
			$this.uploadify(options);
		});
	}
	
	// init styles
	$("input[type=text], input[type=password], textarea", $p).addClass("textInput").focusClass("focus");

	$("input[readonly], textarea[readonly]", $p).addClass("readonly");
	$("input[disabled=true], textarea[disabled=true]", $p).addClass("disabled");

	$("input[type=text]", $p).not("div.tabs input[type=text]", $p).filter("[alt]").inputAlert();

	//Grid ToolBar
	$("div.panelBar li, div.panelBar", $p).hoverClass("hover");

	//Button
	$("div.button", $p).hoverClass("buttonHover");
	$("div.buttonActive", $p).hoverClass("buttonActiveHover");
	
	//tabsPageHeader
	$("div.tabsHeader li, div.tabsPageHeader li, div.accordionHeader, div.accordion", $p).hoverClass("hover");

	//validate form
	$("form.required-validate", $p).each(function(){
		var $form = $(this);
		$form.validate({
			onsubmit: false,
			focusInvalid: false,
			focusCleanup: true,
			errorElement: "span",
			ignore:".ignore",
			invalidHandler: function(form, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					var message = DWZ.msg("validateFormError",[errors]);
					alertMsg.error(message);
				} 
			}
		});
		
		$form.find('input[customvalid]').each(function(){
			var $input = $(this);
			$input.rules("add", {
				customvalid: $input.attr("customvalid")
			})
		});
	});

	if ($.fn.datepicker){
		$('input.date', $p).each(function(){
			var $this = $(this);
			var opts = {};
			if ($this.attr("dateFmt")) opts.pattern = $this.attr("dateFmt");
			if ($this.attr("minDate")) opts.minDate = $this.attr("minDate");
			if ($this.attr("maxDate")) opts.maxDate = $this.attr("maxDate");
			if ($this.attr("mmStep")) opts.mmStep = $this.attr("mmStep");
			if ($this.attr("ssStep")) opts.ssStep = $this.attr("ssStep");
			$this.datepicker(opts);
		});
	}

	// navTab
	$("a[target=navTab]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			navTab.openTab(tabid, url,{title:title, fresh:fresh, external:external});

			event.preventDefault();
		});
	});
	
	//dialogs
	$("a[target=dialog]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var rel = $this.attr("rel") || "_blank";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w) options.width = w;
			if (h) options.height = h;
			options.max = eval($this.attr("max") || "false");
			options.mask = eval($this.attr("mask") || "false");
			options.maxable = eval($this.attr("maxable") || "true");
			options.minable = eval($this.attr("minable") || "true");
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");
			options.drawable = eval($this.attr("drawable") || "true");
			options.close = eval($this.attr("close") || "");
			options.param = $this.attr("param") || "";

			var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			$.pdialog.open(url, rel, title, options);
			
			return false;
		});
	});
	
	// 修改ajax链接从当前页面获取rel标识的div，避免ID冲突问题。
	$("a[target=ajax]", $p).each(function(){
		$(this).click(function(event){
			var $this = $(this);
			var rel = $this.attr("rel");
			if (rel) {
				var $rel = $("#"+rel, $this.getPageDiv());
				$rel.loadUrl($this.attr("href"), {}, function(){
					$rel.find("[layoutH]").layoutH();
				});
			}

			event.preventDefault();
		});
	});
	
	$("div.pagination", $p).each(function(){
		var $this = $(this);
		$this.pagination({
			targetType:$this.attr("targetType"),
			rel:$this.attr("rel"),
			totalCount:$this.attr("totalCount"),
			numPerPage:$this.attr("numPerPage"),
			pageNumShown:$this.attr("pageNumShown"),
			currentPage:$this.attr("currentPage")
		});
	});

	if ($.fn.sortDrag) $("div.sortDrag", $p).sortDrag();

	// dwz.ajax.js
	if ($.fn.ajaxTodo) $("a[target=ajaxTodo]", $p).ajaxTodo();
	if ($.fn.dwzExport) $("a[target=dwzExport]", $p).dwzExport();

	if ($.fn.lookup) $("a[lookupGroup]", $p).lookup();
	if ($.fn.multLookup) $("[multLookup]:button", $p).multLookup();
	if ($.fn.suggest) $("input[suggestFields]", $p).suggest();
	if ($.fn.itemDetail) $("table.itemDetail", $p).itemDetail();
	if ($.fn.selectedTodo) $("a[target=selectedTodo]", $p).selectedTodo();
	if ($.fn.pagerForm) $("form[rel=pagerForm]", $p).pagerForm({parentBox:$p});

	// 这里放其他第三方jQuery插件...
}