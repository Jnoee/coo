<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/user-pwd-reset-save" targetType="dialog">
			<@s.hidden path="user.id" />
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
	                    <input type="password" name="managePassword" size="30" maxlength="20" class="required" alt="请输入您的密码" />
	                </dd>
	            </dl>
				<div class="divider" />
	            <div>输入您的管理员密码点击确定后，该用户的密码将被重置为：<span style="color:red">${defaultPassword}</span></div>
	        </div>
	        <@dwz.formBar submitBtnText="确定" />
        </@dwz.form>
    </div>
</div>