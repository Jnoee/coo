<div class="page">
    <div class="pageHeader">
        <@dwz.pageForm action="/company/employee-list" />
    </div>
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li><@dwz.a href="/company/employee-add" target="dialog" width="S" height="SS" title="新增职员"><span class="a09">新增</span></@dwz.a></li>
            </ul>
        </div>
        <table class="table" width="100%" layoutH="112">
            <thead>
                <tr>
                    <th width="150px" align="center">姓名</th>
					<th width="200px" align="center">所属公司</th>
                    <th width="100px" align="center">年龄</th>
					<th width="100px" align="center">性别</th>
					<th align="center">兴趣爱好</th>
                    <th width="150px" align="center">操作</th>
                </tr>
            </thead>
            <tbody>
                <#list employeePage.contents as employee>
                <tr target="sid_user" rel="1">
                    <td>${employee.name}</td>
                    <td>${employee.company.name}</td>
                    <td>${employee.age}</td>
					<td>${employee.sex.text}</td>
					<td>${employee.interests?join(",")}</td>
                    <td>
						<@dwz.a href="/company/employee-edit?employeeId=${employee.id}" target="dialog" width="S" height="SS" title="编辑职员">编辑</@dwz.a>
						|
						<@dwz.a href="/company/employee-delete?employeeId=${employee.id}" target="ajaxTodo" title="您确定要删除该职员吗？">删除</@dwz.a>
					</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pageNav pageModel=employeePage />
        </div>
    </div>
</div>