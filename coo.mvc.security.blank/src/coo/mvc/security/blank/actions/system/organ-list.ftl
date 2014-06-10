<div class="page">
	<div style="float:left; display:block;overflow:auto; width:22%; border-right:solid 1px #CCC;" layoutH="0">
	    <@system.organTree rootOrgan />
	</div>
	<div id="organBox" style="float:left; display:block;padding:3px; overflow:auto; width:77%;" layoutH="0"></div>
</div>
<script>
	$(function() {
		setTimeout(function() {
			$("a[organId=${selectedOrganId}]").click();
		}, 10);
	}); 
</script>