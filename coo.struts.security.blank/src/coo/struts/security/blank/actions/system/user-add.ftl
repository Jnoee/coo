<div class="page">
    <div class="pageContent">
        <@s.form action="user-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
        	<h2 class="contentTitle">用户信息</h2>
			<div class="divider" />
            <dl>
                <dt>用户名：</dt>
                <dd><@s.textfield name="user.username" size="30" maxlength="20" cssClass="required" /></dd>
			</dl>
			<dl>
                <dt>姓名：</dt>
                <dd><@s.textfield name="user.name" size="30" maxlength="20" cssClass="required" /></dd>
            </dl>
            <dl>
                <dt>序号：</dt>
                <dd><@s.textfield name="user.ordinal" size="30" min="0" max="99999" cssClass="digits" /></dd>
            </dl>
			<div class="divider" />
			<h2 class="contentTitle">默认职务</h2>
			<div class="divider" />
            <dl>
                <dt>关联机构：</dt>
                <dd><@s.select name="defaultActor.organ" list="rootOrgan.organTree" listKey="id" listValue="selectText" cssClass="required combox" /></dd>
			</dl>
			<dl>
                <dt>关联角色：</dt>
                <dd><@s.select name="defaultActor.role" list="roles" listKey="id" listValue="name" cssClass="required combox" onchange="userAdd_roleSelectorOnChange(this)" /></dd>
            </dl>
            <dl>
                <dt>职务名称：</dt>
                <dd><@s.textfield name="defaultActor.name" size="30" maxlength="20" cssClass="required" /></dd>
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
	function userAdd_roleSelectorOnChange(roleSelector) {
		var $roleSelector = $(roleSelector);
		var $roleName = $roleSelector.getParentUnitBox().find("input[name='defaultActor.name']")
		$roleName.val($roleSelector.children("option:selected").text());
	}
</script>