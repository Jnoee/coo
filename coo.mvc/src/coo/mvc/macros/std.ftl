<#macro head>
<link href="${ctx}/std/models/icon/icon.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/std/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="${ctx}/std/js/std.js" type="text/javascript"></script>
<#nested>
</#macro>
<#macro errorHead>
<@head />
<link href="${ctx}/std/models/error/error.css" rel="stylesheet" type="text/css" />
<script>
	$(document).ready(function(){
		$(".std-error-panel").center();
	});
</script>
<#nested>
</#macro>