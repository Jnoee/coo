/** 校验Params对应的参数格式 */
function regexParams(element) {
	return regex(
			element,
			/^\s*[^\s=]+[ ]*=[ ]*[^\s=]+\s*$|^\s*[^\s=]+[ ]*=[ ]*[^\s=]+\s*\n+(\s*[^\s=]+[ ]*=[ ]*[^\s=]+\s*\n)*(\s*[^\s=]+[ ]*=[ ]*[^\s=]+\s*)$/g);
}

/** 校验空格 */
function regexSpace(element){
	return regex(element, /^\S+$/);
}

/** 校验价格格式 */
function regexPrice(element) {
	return regex(element, /^\d+[\.]?\d{0,2}$/g);
}

/** 校验时间格式 */
function regexTime(element) {
	return regex(element, "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])$");
}

/** 扩展DWZ自定义验证函数，日期区间验证结束时间必须大于等于开始时间 */
function gtDate(element,geToTime) {
    var endTime = $(element);
    var startTime = $(geToTime, $(element).closest("div"));
    if(endTime.val() && startTime.val()) {
        return new Date((endTime.val()).replace(/\-/g, "\/")) >= new Date((startTime.val()).replace(/\-/g, "\/"));
    }
    return false;
}

/** 扩展DWZ自定义验证函数，日期区间验证结束时间必须大于开始时间 */
function geTime(element, geToTime) {
	if(regex(element, "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])$")){
		var endTime = $(element);
		var startTime = $(geToTime, $(element).closest("tr"));
		if(endTime.val() && startTime.val()) {
			return endTime.val().parseDate("HH:mm") > startTime.val().parseDate("HH:mm");
		}
		return true;
	}
	return false;
}

/** 扩展DWZ自定义验证函数,数字只能小于等于另外一个 */
function leNumber(element, geToElement) {
	var begin = $(element).val();
	var end = $(geToElement).val();
	if (parseFloat(begin) <= parseFloat(end)) {
		return true;
	}
	return false;
}

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