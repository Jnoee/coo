<div class="page">
    <div class="pageContent">
        <@s.form action="organ-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
            <dl class="nowrap">
                <dt>上级机构：</dt>
                <dd><@s.select name="organ.parent" list="rootOrgan.organTree" listKey="id" listValue="selectText" cssClass="combox" value="organ.parent.id" /></dd>
            </dl>
			<div class="divider"></div>
            <dl>
                <dt>机构名称：</dt>
                <dd><@s.textfield name="organ.name" size="30" maxlength="60" cssClass="required" /></dd>
            </dl>
            <dl>
                <dt>序号：</dt>
                <dd><@s.textfield name="organ.ordinal" size="30" min="0" max="99999" cssClass="digits" /></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">
                                保存
                            </button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">
                                取消
                            </button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        </@s.form>
    </div>
</div>
