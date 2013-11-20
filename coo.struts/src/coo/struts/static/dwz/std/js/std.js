/**
 * 修正Flash在IE浏览器中修改页面title的BUG。 此BUG比较离谱，从2008年9.x版本被人发现到2011年10.x版本还未修复。
 * 参考资料：http://bugs.adobe.com/jira/browse/FP-240
 */
$(document).ready(function(){
    var oldtitle = document.title || "";
    document.onpropertychange = function(){
        var newtitle, doctitle = document.title || "";
        if (window.event.propertyName != 'title' ||
        doctitle == oldtitle) {
            return;
        }
        newtitle = doctitle.indexOf('#') != -1 ? doctitle.substring(0, doctitle.indexOf('#')) : doctitle;
        if (newtitle == '' && doctitle.indexOf('#') != -1) {
            newtitle = oldtitle;
        }
        oldtitle = newtitle;
        document.title = newtitle;
    }
});

$.fn.extend({
    center: function(){
        $(this).each(function(){
            $(this).css("position", "absolute");
            $(this).css("top", ($(window).height() - $(this).height()) / 2 + $(window).scrollTop() + "px");
            $(this).css("left", ($(window).width() - $(this).width()) / 2 + $(window).scrollLeft() + "px");
            return $(this);
        });
    }
});
