/** 自定义jqeury validator错误信息显示方法 */
$(function(){
	if ($.validator) {
		$.validator.setDefaults({
			errorPlacement: function(error, element) {
				if(element.hasClass("hideError")) {
					element.attr("title",error.text());
				} else {
					element.parents("dd:first").append(error);
					error.attr("title", error.text());
				}
			}
		});
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