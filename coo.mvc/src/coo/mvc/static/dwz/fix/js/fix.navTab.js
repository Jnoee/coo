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
						var serializeArray = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {};
						op.data = serializeArrayToJson(serializeArray);
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