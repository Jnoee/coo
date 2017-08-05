/** 扩展String方法 */
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