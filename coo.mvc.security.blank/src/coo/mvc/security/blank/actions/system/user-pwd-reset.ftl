<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/user-pwd-reset-save">
			<@s.hidden path="user.id" />
	        <div class="pageFormContent cols1" layoutH="60">
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
	                    <input type="password" name="managePassword" maxlength="20" class="required" alt="请输入您的密码" />
	                </dd>
	            </dl>
				<div class="divider" />
	            <div style="padding:10px 0px 0px 5px">请输入管理员密码并点击确定，用户的密码将被重置为：<span style="color:red">${defaultPassword}</span>。</div>
	        </div>
	        <@dwz.formBar submitBtnText="确定" />
        </@dwz.form>
    </div>
</div>