<#macro head>
<link href="${R.std}/models/icon/icon.css" rel="stylesheet" type="text/css" />
<script src="${R.std}/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${R.std}/js/std.js" type="text/javascript"></script>
<#nested>
</#macro>
<#macro errorHead>
<@head />
<link href="${R.std}/models/error/error.css" rel="stylesheet" type="text/css" />
<script>
	$(document).ready(function(){
		$(".std-error-panel").center();
	});
</script>
<#nested>
</#macro>