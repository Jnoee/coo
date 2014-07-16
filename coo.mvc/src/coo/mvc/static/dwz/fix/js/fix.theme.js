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