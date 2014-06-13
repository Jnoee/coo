<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/role-save">
	        <div class="pageFormContent" layoutH="60">
				<dl>
					<dt>名称：</dt>
					<dd><@s.input path="role.name" maxlength="60" class="required" /></dd>
				</dl>
				<div class="divider" />
	            <@system.permissions permissionGroups />
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>