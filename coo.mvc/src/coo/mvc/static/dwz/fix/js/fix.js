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
