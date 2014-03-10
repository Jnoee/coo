<div class="page">
    <div class="pageContent">
        <@s.form action="/system/person-pwd-change-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <dl>
                <dt>原密码：</dt>
                <dd><@s.password path="pwdChangeModel.oldPwd" size="30" maxlength="20" class="required" /></dd>
            </dl>
            <dl>
                <dt>新密码：</dt>
                <dd><@s.password id="newPwd" path="pwdChangeModel.newPwd" size="30" maxlength="20" class="required" /></dd>
            </dl>
            <dl>
                <dt>确认新密码：</dt>
                <dd><@s.password path="pwdChangeModel.confirmPwd" size="30" maxlength="20" class="required" equalTo="#newPwd" /></dd>
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
