<#macro init>
	<script src="${ctx}/ecs/echarts-all.js" type="text/javascript"></script>
</#macro>

<#macro chart id action attributes...>
	<form id="${id}Form" action="<@s.url "${action}" />">
	    <#nested>
	</form>
	<div id="${id}" ${s.getAttributes(attributes)}></div>
	<script type="text/javascript">
		$(function () {
			var form = $("#${id}Form");
			var chart = echarts.init(document.getElementById("${id}"));
			$.ajax({
				type: "POST",
				url: form.attr("action"),
				data: form.serializeArray(),
				dataType: "json",
				cache: false,
				success: function(data) {
					try {
						chart.setOption(data);
					} catch(e) {
						chart.html(e);
					}
				}
			});
		});
	</script>
</#macro>