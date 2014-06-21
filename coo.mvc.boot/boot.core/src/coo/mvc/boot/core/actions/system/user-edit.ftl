<@dwz.reload action="/system/user-edit" userId=user.id /> 
<div class="page">
    <div class="pageContent">
    	<@dwz.form action="/system/user-update">
	        <@s.hidden path="user.id" />
	    	<div class="pageFormContent" layoutH="58">
	    	    <dl class="nowrap">
	    	        <h3>用户信息</h3>
				</dl>
				<div class="divider"></div>
				<dl>
	                <dt>用户名：</dt>
	                <dd><@s.input path="user.username" maxlength="20" class="required" /></dd>
				</dl>
				<dl>
	                <dt>姓名：</dt>
	                <dd><@s.input path="user.name" maxlength="20" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>序号：</dt>
	                <dd><@s.input path="user.ordinal" min="0" max="99999" class="digits" /></dd>
	            </dl>
	            <div class="divider"></div>
	            <div class="panel">
	                <h1>职务列表[<@dwz.a href="/system/actor-add?userId=${user.id}" target="dialog">新增职务</@dwz.a>]</h1>
	                <div>
	                    <table class="list" width="98%">
	                        <thead>
	                            <tr>
	                                <th align="center">职务名称</th>
	                                <th align="center" width="150">关联机构</th>
	                                <th align="center" width="150">关联角色</th>
	                                <th align="center" width="150">操作</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <#list user.actors as actor>
		                            <tr>
		                                <td align="center">${actor.name}</td>
		                                <td align="center">${actor.organ.name}</td>
		                                <td align="center">${actor.role.name}</td>
		                                <td align="center">
		                                    <@dwz.a href="/system/actor-edit?actorId=${actor.id}" target="dialog" title="编辑职务">编辑</@dwz.a>
		                                    <#if !actor.isDefaultActor()>
			                                    |&nbsp;<@dwz.a href="/system/actor-delete?actorId=${actor.id}" target="ajaxTodo" callback="dialogReloadDone" title="您确定要删除该职务吗？">删除</@dwz.a>
		                                    </#if>
		                                </td>
		                            </tr>
	                            </#list>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
			</div>
			<@dwz.formBar />
        </@dwz.form>
    </div>
</div>