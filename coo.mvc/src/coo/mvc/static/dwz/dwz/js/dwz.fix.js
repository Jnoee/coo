/** 覆盖DWZ的翻页参数设置 */
$.extend(DWZ.pageInfo, {
	pageNum: "pageNo",
	numPerPage: "pageSize",
	orderField: "orderBy",
	orderDirection: "s"
});

/** 点击导航菜单项 */
var clickNavMenu = function(index) {
	$("a", $("#navMenu")).eq(index).click();
}

/** 隐藏左边菜单栏 */
var hideMenuBar = function() {
	var sidebar = $("#sidebar");
	var sidebarBtn = $("#sidebar > .toggleCollapse > div");
	if(sidebar.css("display") != "none") {
		sidebarBtn.click();
	}
}

/** 显示左边菜单栏 */
var showMenuBar = function() {
	var sidebar = $("#sidebar_s");
	var sidebarBtn = $("#sidebar_s > .collapse > .toggleCollapse > div");
	if(sidebar.css("display") != "none") {
		sidebarBtn.click();
	}
}