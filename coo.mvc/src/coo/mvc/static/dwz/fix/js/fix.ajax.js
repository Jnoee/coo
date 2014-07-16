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