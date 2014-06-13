<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/actor-save" targetType="dialogReload">
	        <@s.hidden path="actor.user" value="${actor.user.id}" />
	        <div class="pageFormContent" layoutH="60">
	            <dl>
	                <dt>关联机构：</dt>
	                <dd><@s.select path="actor.organ" items=rootOrgan.organTree itemValue="id" itemLabel="selectText" class="required combox" /></dd>
				</dl>
				<dl>
	                <dt>关联角色：</dt>
	                <dd><@s.select path="actor.role" items=roles itemValue="id" itemLabel="name" class="required combox" /></dd>
	            </dl>
	            <dl>
	                <dt>职务名称：</dt>
	                <dd><@s.input path="actor.name" maxlength="20" class="required" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>