/** 覆盖DWZ的jPanel函数，修复Panel的头部不能放置链接内容的Bug。 */
$.fn.extend({
	jPanel: function(options) {
		var op = $.extend({
			header: "panelHeader",
			headerC: "panelHeaderContent",
			content: "panelContent",
			coll: "collapsable",
			exp: "expandable",
			footer: "panelFooter",
			footerC: "panelFooterContent"
		}, options);
		return this.each(function() {
			var $panel = $(this);
			var close = $panel.hasClass("close");
			var collapse = $panel.hasClass("collapse");

			var $content = $(">div", $panel).addClass(op.content);
			var title = $(">h1", $panel).wrap('<div class="' + op.header + '"><div class="' + op.headerC + '"></div></div>');
			if(collapse)
				// 指定收缩按钮的class名称，不要影响到其它链接。
				$("<a class=\"panelCollapseBtn\" href=\"\"></a>").addClass(close ? op.exp : op.coll).insertAfter(title);

			var header = $(">div:first", $panel);
			var footer = $('<div class="' + op.footer + '"><div class="' + op.footerC + '"></div></div>').appendTo($panel);

			var defaultH = $panel.attr("defH") ? $panel.attr("defH") : 0;
			var minH = $panel.attr("minH") ? $panel.attr("minH") : 0;
			if(close)
				$content.css({
					height: "0px",
					display: "none"
				});
			else {
				if(defaultH > 0)
					$content.height(defaultH + "px");
				else if(minH > 0) {
					$content.css("minHeight", minH + "px");
				}
			}
			if(!collapse)
				return;
			// 获取收缩按钮时排除其它无关链接元素
			var $pucker = $("a.panelCollapseBtn", header);
			var inH = $content.innerHeight() - 6;
			if(minH > 0 && minH >= inH)
				defaultH = minH;
			else
				defaultH = inH;
			$pucker.click(function() {
				if($pucker.hasClass(op.exp)) {
					$content.jBlindDown({
						to: defaultH,
						call: function() {
							$pucker.removeClass(op.exp).addClass(op.coll);
							if(minH > 0)
								$content.css("minHeight", minH + "px");
						}
					});
				} else {
					if(minH > 0)
						$content.css("minHeight", "");
					if(minH >= inH)
						$content.css("height", minH + "px");
					$content.jBlindUp({
						call: function() {
							$pucker.removeClass(op.coll).addClass(op.exp);
						}
					});
				}
				return false;
			});
		});
	}
});