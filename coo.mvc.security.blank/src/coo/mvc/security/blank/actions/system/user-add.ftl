<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/user-save" targetType="dialog">
	        <div class="pageFormContent" layoutH="60">
	        	<dl class="nowrap">
	                <h3>用户信息</h3>
	            </dl>
	            <div class="divider"></div>
	            <dl>
	                <dt>用户名：</dt>
	                <dd><@s.input path="user.username" size="30" maxlength="20" class="required" /></dd>
				</dl>
				<dl>
	                <dt>姓名：</dt>
	                <dd>
	                    <@s.input path="user.name" name="user.name" size="30"  maxlength="20" class="required"/>
	                </dd>
	            </dl>
	            <dl>
	                <dt>序号：</dt>
	                <dd><@s.input path="user.ordinal" size="30" min="0" max="99999" class="digits" /></dd>
	            </dl>
				<div class="divider"></div>
	            <dl class="nowrap">
	                <h3>默认职务</h3>
	            </dl>
	            <div class="divider"></div>
	            <dl>
	                <dt>关联机构：</dt>
	                <dd><@s.select path="actor.organ" items=rootOrgan.organTree itemLabel="selectText" itemValue="id" class="required combox" /></dd>
				</dl>
				<dl>
	                <dt>关联角色：</dt>
	                <dd><@s.select path="actor.role" items=roles itemLabel="name" itemValue="id" class="required combox" /></dd>
	            </dl>
	            <dl>
	                <dt>职务名称：</dt>
	                <dd>
	                    <@s.input path="actor.name" name="actor.name" size="30" maxlength="20" class="required"  />
	                </dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>