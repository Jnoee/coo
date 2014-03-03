<div class="page">
    <div class="pageContent">
        <@s.form action="/company/employee-save" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
        <div class="pageFormContent" layoutH="60">
            <dl>
                <dt>姓名：</dt>
                <dd><@s.input path="employee.name" size="30" maxlength="60" class="required" /></dd>
			</dl>
            <dl>
				<dt>所属公司：</dt>
                <dd>
                    <@s.select path="employee.company" items=companys itemValue="id" itemLabel="name" class="required combox" />
                </dd>
			</dl>
			<div class="divider" />
			<dl>
                <dt>年龄：</dt>
                <dd><@s.input path="employee.age" size="30" min="1" max="999" class="required number" /></dd>
            </dl>
			<dl>
                <dt>性别：</dt>
                <dd>
                    <@s.select path="employee.sex" items=Sex?values itemValue="value" itemLabel="text" class="required combox" />
                </dd>
            </dl>
			<dl>
                <dt>兴趣爱好：</dt>
                <dd>
                    <@s.checkboxs path="employee.interests" items=Interest?values itemValue="text" itemLabel="text" />
                </dd>
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