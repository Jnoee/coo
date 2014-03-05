<div class="page">
    <div class="pageContent">
        <@s.form action="/system/actor-save" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <@s.hidden path="actor.user" value="${actor.user.id}" />
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>关联机构：</dt>
                <dd><@s.select path="actor.organ" items=rootOrgan.organTree itemValue="id" itemLabel="selectText" class="required" /></dd>
			</dl>
			<dl>
                <dt>关联角色：</dt>
                <dd><@s.select path="actor.role" items=roles itemValue="id" itemLabel="name" class="required" onchange="actorAdd_roleSelectorOnChange(this)" /></dd>
            </dl>
            <dl>
                <dt>职务名称：</dt>
                <dd><@s.input path="actor.name" size="30" maxlength="20" class="required" /></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">取消</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        </@s.form>
    </div>
</div>
<script type="text/javascript" charset="utf-8">
	function actorAdd_roleSelectorOnChange(roleSelector) {
		var $roleSelector = $(roleSelector);
		var $roleName = $roleSelector.getParentUnitBox().find("input[name='actor.name']")
		$roleName.val($roleSelector.children("option:selected").text());
	}
</script>
