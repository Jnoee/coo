/** 扩展jQuery，实现页面元素可直接获取上级标识为page的div容器 */
$.fn.extend({
    getPageDiv: function(){
        return $(this).closest("div.page");
    }
});

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

/** 覆盖DWZ的翻页参数设置 */
$.extend(DWZ.pageInfo, {pageNum:"pageNo", numPerPage:"pageSize", orderField:"orderBy", orderDirection:"s"});

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