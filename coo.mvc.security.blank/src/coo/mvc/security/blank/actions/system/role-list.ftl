<div class="page">
	<div style="float:left; display:block;overflow:auto; width:25%; border-right:solid 1px #CCC;" layoutH="0">
	    <ul class="tree" layoutH="37">
	        <li>
	            <a>已定义角色</a>
	            <ul>
	                <#list roles as role>
	                	<li><@dwz.a href="/system/role-edit?roleId=${role.id}" target="ajax" rel="roleBox" roleId="${role.id}">${role.name}</@dwz.a></li>
	                </#list>
	            </ul>
	        </li>
	    </ul>
	    <@dwz.formBar showSubmitBtn=false showCancelBtn=false>
    		<li><@dwz.a href="/system/role-add" target="dialog" class="button"><span>新增角色</span></@dwz.a></li>
	    </@dwz.formBar>
	</div>
	<div id="roleBox" style="float:left; display:block;padding:3px; overflow:auto; width:74%;" layoutH="0"></div>
</div>
<script>
    $(function() {
        setTimeout(function() {
            $("a[roleId=${selectedRoleId}]").click();
        }, 10);
    }); 
</script>