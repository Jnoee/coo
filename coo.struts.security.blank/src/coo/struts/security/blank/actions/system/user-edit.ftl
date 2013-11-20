<div class="page">
    <div class="pageContent">
    	<@s.form action="user-update" cssClass="pageForm required-validate" onsubmit="return validateCallback(this)">
        <@s.hidden name="user.id" />
    	<div class="panel collapse" defH="118">
			<h1>用户信息</h1>
			<div>
				<div class="pageFormContent">
					<dl>
		                <dt>用户名：</dt>
		                <dd><@s.textfield name="user.username" size="30" maxlength="20" cssClass="required" /></dd>
					</dl>
					<dl>
		                <dt>姓名：</dt>
		                <dd><@s.textfield name="user.name" size="30" maxlength="20" cssClass="required" /></dd>
		            </dl>
		            <dl>
		                <dt>序号：</dt>
		                <dd><@s.textfield name="user.ordinal" size="30" min="0" max="99999" cssClass="digits" /></dd>
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
	                </ul>
	            </div>
			</div>
		</div>
		</@s.form>
		<div class="panel collapse" defH="410">
			<h1>职务信息</h1>
			<div>
				<div class="panelBar">
		            <ul class="toolBar">
		                <li>
		                	<a href="<@s.url action="actor-add" userId="${user.id}" />" target="dialog" rel="actor-add" width="800" height="480" mask="true" title="新增职务" class="hitbtn"><span class="a09">新增职务</span></a>
		                </li>
		            </ul>
		        </div>
				<table class="table" width="100%">
                    <thead>
                        <tr>
                            <th align="center">职务名称</th>
                            <th align="center" width="200">关联机构</th>
                            <th align="center" width="200">关联角色</th>
                            <th align="center" width="200">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list user.actors as actor>
                        <tr>
                            <td>${actor.name}</td>
                            <td>${actor.organ.name}</td>
                            <td>${actor.role.name}</td>
                            <td><a href="<@s.url action="actor-edit" actorId="${actor.id}" />" target="dialog" rel="actor-edit" width="800" height="480" mask="true" title="编辑职务">编辑</a><#if !actor.isDefaultActor()>&nbsp;|&nbsp;<a href="<@s.url action="actor-delete" actorId="${actor.id}" />" target="ajaxTodo" title="您确定要删除该职务吗？">删除</a></#if></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
			</div>
		</div>
    </div>
</div>
