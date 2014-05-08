<div style="float:left; display:block;overflow:auto; width:25%; border-right:solid 1px #CCC;" layoutH="0">
    <ul class="tree treeFolder" layoutH="37">
        <li>
            <a>已定义角色</a>
            <ul>
                <#list roles as role>
                <li>
                    <a href="<@s.url "/system/role-edit?roleId=${role.id}" />" target="ajax" rel="roleBox" roleId="${role.id}">${role.name}</a>
                </li>
                </#list>
            </ul>
        </li>
    </ul>
    <div class="formBar">
        <ul>
            <li>
                <a class="button" href="<@s.url "/system/role-add" />" target="dialog" rel="role-add" mask="true" width="800" height="480"><span>新增角色</span></a>
            </li>
        </ul>
    </div>
</div>
<div id="roleBox" style="float:left; display:block;padding:3px; overflow:auto; width:74%;" layoutH="0"></div>
<script>
    $(function() {
        setTimeout(function() {
            $("a[roleId=${selectedRoleId}]").click();
        }, 10);
    }); 
</script>