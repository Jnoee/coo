<div class="page">
    <div class="pageHeader">
        <@dwz.pageForm action="/system/user-list" />
    </div>
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a href="<@s.url "/system/user-add" />" target="dialog" rel="user-add" mask="true" title="新增用户" width="800" height="480"><span class="a09">新增</span> </a>
                </li>
            </ul>
        </div>
        <table class="table" width="100%" layoutH="112">
            <thead>
                <tr>
                    <th align="center">用户名</th>
                    <th width="150px" align="center">姓名</th>
					<th width="150px" align="center">创建时间</th>
					<th width="80px" align="center">创建人</th>
					<th width="150px" align="center">修改时间</th>
					<th width="80px" align="center">修改人</th>
                    <th width="80px" align="center">状态</th>
                    <th width="200px" align="center">操作</th>
                </tr>
            </thead>
            <tbody>
                <#list userPage.contents as user>
                <tr target="sid_user" rel="1">
                    <td><a href="<@s.url "/system/user-edit?userId=${user.id}" />" target="navTab" rel="user-edit" title="编辑用户">${user.username}</a></td>
                    <td>${user.name}</td>
					<td>${user.createDate}</td>
					<td>${user.creator.name}</td>
					<td>${user.modifyDate}</td>
					<td>${user.modifier.name}</td>
                    <td><#if user.enabled>√<#else>×</#if></td>
                    <td><#if user.enabled><a href="<@s.url "/system/user-disable?userId=${user.id}" />" target="ajaxTodo" title="确定要禁用吗？">禁用</a><#else><a href="<@s.url "/system/user-enable?userId=${user.id}" />" target="ajaxTodo" title="确定要启用吗？">启用</a></#if>&nbsp;|&nbsp;<a href="<@s.url "/system/user-pwd-reset?userId=${user.id}"/>" target="dialog" rel="user-pwd-reset" mask="true" title="重置密码">重置密码</a></td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pageNav pageModel=userPage />
        </div>
    </div>
</div>