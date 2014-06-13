<div class="page">
    <div class="pageContent">
        <@dwz.form action="/company/employee-save">
	        <div class="pageFormContent s-cols" layoutH="60">
	            <dl>
	                <dt>姓名：</dt>
	                <dd><@s.input path="employee.name" maxlength="60" class="required" /></dd>
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
	                <dd><@s.input path="employee.age" min="1" max="999" class="required number" /></dd>
	            </dl>
				<dl>
	                <dt>性别：</dt>
	                <dd>
	                    <@s.select path="employee.sex" items=Sex?values itemValue="value" itemLabel="text" class="required combox" />
	                </dd>
	            </dl>
				<dl class="nowrap">
	                <dt>兴趣爱好：</dt>
	                <dd>
	                    <@s.checkboxs path="employee.interests" items=Interest?values itemValue="text" itemLabel="text" prefix="<span class='dd-span'>" suffix="</span>" class="required" />
	                </dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>