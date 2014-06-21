<div class="page">
    <div class="pageContent">
    	<div class="pageFormContent" layoutH="58">
    		<#list entityLogs as log>
    			<div class="d-line"></div>
				<@system.log_view log />
			</#list>
		</div>
		<@dwz.formBar showSubmitBtn=false />
    </div>
</div>