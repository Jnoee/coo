<div class="page">
    <div class="pageContent">
        <@s.form action="/system/user-save" class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
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