<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/role-update">
	        <@s.hidden path="role.id" />
	        <div class="pageFormContent" layoutH="60">
				<dl>
					<dt>名称：</dt>
					<dd><@s.input path="role.name" size="30" maxlength="60" class="required" /></dd>
				</dl>
				<div class="divider" />
	            <@system.permissions permissionGroups />
	        </div>
	        <@dwz.formBar showCancelBtn=false />
        </@dwz.form>
    </div>
</div>