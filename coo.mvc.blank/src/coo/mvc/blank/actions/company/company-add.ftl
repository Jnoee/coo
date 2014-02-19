<div class="page">
    <div class="pageContent">
        <form action="<@s.url "/company/company-save"/>" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>名称：</dt>
                <dd><@s.formInput path="company.name" attributes="size=\"30\" maxlength=\"60\" class=\"required\"" /></dd>
            </dl>
            <dl>
                <dt>成立时间：</dt>
                <dd><@s.formInput path="company.foundDate" attributes="size=\"30\" class=\"required date\"" /></dd>
            </dl>
            <dl>
                <dt>地址：</dt>
                <dd><@s.formInput path="company.extendInfo.address" attributes="size=\"30\" maxlength=\"60\"" /></dd>
            </dl>
            <dl>
                <dt>电话：</dt>
                <dd><@s.formInput path="company.extendInfo.tel" attributes="size=\"30\" maxlength=\"20\"" /></dd>
            </dl>
			<dl>
                <dt>传真：</dt>
                <dd><@s.formInput path="company.extendInfo.fax" attributes="size=\"30\" maxlength=\"20\"" /></dd>
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
        </form>
    </div>
</div>