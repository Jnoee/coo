<div class="page">
    <div class="pageHeader">
        <@dwz.pagerForm action="employee-list" />
    </div>
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
					<a href="<@s.url action="employee-add" />" target="dialog" rel="employee-add" mask="true" title="新增职员" width="800" height="700">
						<span class="a09">新增职员</span>
					</a>
				</li>
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
					<td>${employee.interestTexts}</td>
                    <td>
						<a href="<@s.url action="employee-edit" employeeId="${employee.id}" />" target="dialog" mask="true" rel="employee-edit" title="编辑职员" width="800" height="700">编辑</a>
						&nbsp;|&nbsp;
						<a href="<@s.url action="employee-delete" employeeId="${employee.id}" />" target="ajaxTodo" title="您确定要删除该职员吗？">删除</a>
					</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pagerNav pageModel=employeePage />
        </div>
    </div>
</div>