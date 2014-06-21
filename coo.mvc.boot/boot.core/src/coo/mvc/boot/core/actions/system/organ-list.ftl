<div class="page">
	<div class="tree-l-box" layoutH="0">
	    <@system.organTree rootOrgan />
	</div>
	<div id="organBox" class="tree-r-box" layoutH="0"></div>
</div>
<script>
	$(function() {
		setTimeout(function() {
			$("a[organId=${selectedOrganId}]").click();
		}, 10);
	}); 
</script>