<div class="page">
    <div class="pageContent">
        <@s.form action="user-pwd-reset-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
		<@s.hidden name="user.id" />
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>待重置的用户名：</dt>
                <dd>${user.username}</dd>
            </dl>
            <dl>
                <dt>待重置用户姓名：</dt>
                <dd>${user.name}</dd>
            </dl>
            <dl>
                <dt>请输入您的密码：</dt>
                <dd>
                    <@s.password name="managePassword" size="30" maxlength="20" cssClass="required" alt="请输入您的密码" />
                </dd>
            </dl>
			<div class="divider" />
            <div>输入您的管理员密码点击确定后，该用户的密码将被重置为：<span style="color:red">${defaultPassword}</span></div>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">确定</button>
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
