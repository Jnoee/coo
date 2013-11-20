<div class="page">
    <div class="pageContent">
        <@s.form action="actor-update" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <@s.hidden name="actor.id" />
        <div class="pageFormContent" layoutH="60">
        	<dl>
                <dt>关联机构：</dt>
                <dd><@s.select name="actor.organ" list="rootOrgan.organTree" listKey="id" listValue="selectText" value="actor.organ.id" cssClass="required" /></dd>
			</dl>
			<dl>
                <dt>关联角色：</dt>
                <dd><@s.select name="actor.role" list="roles" listKey="id" listValue="name" value="actor.role.id" cssClass="required" onchange="actorEdit_roleSelectorOnChange(this)" /></dd>
            </dl>
            <dl>
                <dt>职务名称：</dt>
                <dd><@s.textfield name="actor.name" size="30" maxlength="20" cssClass="required" /></dd>
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
	function actorEdit_roleSelectorOnChange(roleSelector) {
		var $roleSelector = $(roleSelector);
		var $roleName = $roleSelector.getParentUnitBox().find("input[name='actor.name']")
		$roleName.val($roleSelector.children("option:selected").text());
	}
</script>