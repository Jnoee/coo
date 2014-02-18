<div class="page">
    <div class="pageContent">
        <@s.form action="company-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>名称：</dt>
                <dd><@s.textfield name="company.name" size="30" maxlength="60" cssClass="required" /></dd>
            </dl>
            <dl>
                <dt>成立时间：</dt>
                <dd><@s.textfield name="company.foundDate" size="30" cssClass="required date" /></dd>
            </dl>
            <dl>
                <dt>地址：</dt>
                <dd><@s.textfield name="company.extendInfo.address" size="30" maxlength="60" /></dd>
            </dl>
            <dl>
                <dt>电话：</dt>
                <dd><@s.textfield name="company.extendInfo.tel" size="30" maxlength="20" /></dd>
            </dl>
			<dl>
                <dt>传真：</dt>
                <dd><@s.textfield name="company.extendInfo.fax" size="30" maxlength="20" /></dd>
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