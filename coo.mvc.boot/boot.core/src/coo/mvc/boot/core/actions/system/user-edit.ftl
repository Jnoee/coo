<@dwz.reload action="/system/user-edit" userId=user.id /> 
<div class="page">
    <div class="pageContent">
    	<@s.form action="/system/user-update" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
        <@s.hidden path="user.id" />
    	<div class="pageFormContent" layoutH="58">
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
                <dd><@s.input path="user.name" size="30" maxlength="20" class="required" /></dd>
            </dl>
            <dl>
                <dt>序号：</dt>
                <dd><@s.input path="user.ordinal" size="30" min="0" max="99999" class="digits" /></dd>
            </dl>
            <div class="divider"></div>
            <dl class="nowrap">
                <h3>职务信息</h3>
            </dl>
            <div class="divider"></div>
            <div class="panel">
                <h1>职务列表[<a href="<@s.url "/system/actor-add?userId=${user.id}" />" target="dialog" rel="actor-add" width="900" height="480" mask="true" title="新增职务">新增职务</a>]</h1>
                <div>
                    <table class="list" width="98%">
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
                                <td align="center">${actor.name}</td>
                                <td align="center">${actor.organ.name}</td>
                                <td align="center">${actor.role.name}</td>
                                <td align="center">
                                    <a href="<@s.url "/system/actor-edit?actorId=${actor.id}" />" target="dialog" rel="actor-edit" width="900" height="480" mask="true" title="编辑职务">编辑</a>
                                    <#if !actor.isDefaultActor()>
                                    |
                                    <a href="<@s.url "/system/actor-delete?actorId=${actor.id}" />" target="ajaxTodo" callback="dialogReloadDone" title="您确定要删除该职务吗？">删除</a></#if>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
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