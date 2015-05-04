/** 覆盖DWZ的checkbox全选/反选函数 */
(function($) {
	$.fn.extend({
		checkboxCtrl : function(parent) {
			return this.each(function() {
				var $parent = $(parent || document);
				var $box = $(this).attr("box");
				if($box) {
					$parent = $(this).closest($box);
				}
				var $checkAllBtn = $(this);
				var checkboxName = $(this).attr("group").escape();
				var $checkboxs = $parent.find(":checkbox[name=" + checkboxName + "]");
				
				$(this).bind("checkSelected", function() {
					var $checkedCheckboxs = $parent.find(":checkbox[name=" + checkboxName + "]:checked");
					if($checkboxs.size() > 0 && $checkedCheckboxs.size() == $checkboxs.size()) {
						$(this).attr("checked", true);
					} else {
						$(this).attr("checked", false);
					}
				});
				
				$(this).bind("selectAll", function() {
					$checkboxs.attr("checked", true);
				});

				$(this).bind("selectNone", function() {
					$checkboxs.attr("checked", false);
				});

				$(this).bind("selectInvert", function() {
					$checkboxs.each(function() {
						$(this).attr("checked", !$(this).is(":checked"));
					});
				});
				
				$(this).click(function() {
					if ($(this).is(":checkbox")) {
						if ($(this).is(":checked")) {
							$(this).trigger("selectAll");
						} else {
							$(this).trigger("selectNone");
						}
					} else {
						var selectType = $(this).attr("selectType");
						switch (type) {
						case "invert":
							$(this).trigger("selectInvert");
							break;
						case "none":
							$(this).trigger("selectAll");
							break;
						default:
							$(this).trigger("selectAll");
							break;
						}
					}
				});
				
				$checkboxs.each(function() {
					$(this).click(function() {
						$checkAllBtn.trigger("checkSelected");
					});
				});
				
				$(this).trigger("checkSelected");
			});
		}
	});
})(jQuery);