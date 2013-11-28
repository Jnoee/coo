<div class="page">
    <div class="pageContent">
        <@s.form action="employee-save" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>姓名：</dt>
                <dd><@s.textfield name="employee.name" size="30" maxlength="60" cssClass="required" /></dd>
			</dl>
            <dl>
				<dt>所属公司：</dt>
                <dd><@s.select name="employee.company" list=companys headerKey="" headerValue="请选择..." listKey="id" listValue="name" cssClass="required combox" /></dd>
			</dl>
			<div class="divider" />
			<dl>
                <dt>年龄：</dt>
                <dd><@s.textfield name="employee.age" size="30" min="1" max="999" cssClass="required number" /></dd>
            </dl>
			<dl>
                <dt>性别：</dt>
                <dd><@s.select name="employee.sex" list=enums["coo.struts.blank.enums.Sex"]?values listKey="value" listValue="text" cssClass="required combox" /></dd>
            </dl>
			<dl>
                <dt>兴趣爱好：</dt>
                <dd><@s.checkboxlist name="employee.interests" list=enums["coo.struts.blank.enums.Interest"]?values listKey="value" listValue="text" /></dd>
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