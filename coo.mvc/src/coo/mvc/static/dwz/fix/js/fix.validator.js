/** 自定义jqeury validator错误信息显示方法 */
$(function() {
	if($.validator) {
		$.validator.setDefaults({
			errorPlacement: function(error, element) {
				if(element.hasClass("hideError")) {
					element.attr("title", error.text());
				} else {
					element.parents("dd:first").append(error);
					error.attr("title", error.text());
				}
			}
		});
		$.validator.prototype.showLabel = function(element, message) {
			var label = this.errorsFor(element);
			if(label.length) {
				label.removeClass(this.settings.validClass).addClass(this.settings.errorClass);
				label.html(message).attr("title", message);
			} else {
				label = $("<" + this.settings.errorElement + ">");
				label.attr("for", this.idOrName(element));
				label.addClass(this.settings.errorClass);
				label.html(message || "").attr("title", message || "");
				if(this.settings.wrapper) {
					label = label.hide().show().wrap("<" + this.settings.wrapper + "/>").parent();
				}
				if(!this.labelContainer.append(label).length) {
					if(this.settings.errorPlacement) {
						this.settings.errorPlacement(label, $(element));
					} else {
						label.insertAfter(element);
					}
				}
			}
			if(!message && this.settings.success) {
				label.text("").attr("title", "");
				if(typeof this.settings.success === "string") {
					label.addClass(this.settings.success);
				} else {
					this.settings.success(label, element);
				}
			}
			this.toShow = this.toShow.add(label);
		}
	}
});

/** 扩展DWZ自定义验证函数，日期区间验证结束日期必须大于开始日期 */
var gtDate = function(element, gtToDate) {
	var endDate = $(element);
	var startDate = $(gtToDate, $(element).closest("form"));
	var pattern = startDate.attr("dateFmt") || "yyyy-MM-dd";

	if(endDate.val() && startDate.val()) {
		return endDate.val().parseDate("yyyy-MM-dd") > startDate.val().parseDate(pattern);
	}

	return true;
}

/** 扩展DWZ自定义验证函数，日期区间验证结束日期必须大于等于开始日期 */
var geDate = function(element, geToDate) {
	var endDate = $(element);
	var startDate = $(geToDate, $(element).closest("form"));
	var pattern = startDate.attr("dateFmt") || "yyyy-MM-dd";

	if(endDate.val() && startDate.val()) {
		return endDate.val().parseDate("yyyy-MM-dd") >= startDate.val().parseDate(pattern);
	}

	return true;
}

/** 扩展DWZ自定义验证函数，验证正则表达式 */
var regex = function(element, pattern, attrs) {
	var $element = $(element);
	if($element.val()) {
		if(pattern instanceof RegExp) {
			return pattern.test($element.val());
		} else {
			attrs = attrs || "g";
			var ex = new RegExp(pattern, attrs);
			return ex.test($element.val());
		}
	}
	return true;
}