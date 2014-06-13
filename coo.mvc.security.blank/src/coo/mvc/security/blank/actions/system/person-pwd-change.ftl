<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/person-pwd-change-save">
	        <div class="pageFormContent" layoutH="56">
	            <dl>
	                <dt>原密码：</dt>
	                <dd><@s.password path="pwdChangeModel.oldPwd" maxlength="20" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>新密码：</dt>
	                <dd><@s.password id="newPwd" path="pwdChangeModel.newPwd" maxlength="20" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>确认新密码：</dt>
	                <dd><@s.password path="pwdChangeModel.confirmPwd" maxlength="20" class="required" equalTo="#newPwd" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>
