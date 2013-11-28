<div class="page">
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a href="<@s.url action="company-add" />" target="dialog" rel="ec-add" mask="true" title="新增公司">
						<span class="a09">新增</span>
					</a>
                </li>
            </ul>
        </div>
        <table class="table" width="100%" layoutH="75">
            <thead>
                <tr>
                    <th width="150px" align="center">名称</th>
                    <th width="120px" align="center">成立时间</th>
                    <th align="center">地址</th>
					<th width="120px" align="center">电话</th>
					<th width="120px" align="center">传真</th>
					<th width="80px" align="center">状态</th>
                    <th width="150px" align="center">操作</th>
                </tr>
            </thead>
            <tbody>
                <#list companys as company>
                <tr target="sid_user" rel="1">
                    <td>${company.name}</td>
					<td>${company.foundDate?date}</td>
					<td>${company.extendInfo.address}</td>
					<td>${company.extendInfo.tel}</td>
					<td>${company.extendInfo.fax}</td>
                    <td>${company.enabled?string("√","×")}</td>
                    <td>
                    	<a href="<@s.url action="company-edit" companyId="${company.id}" />" target="dialog" rel="company-edit" mask="true" title="编辑公司">编辑</a>
						&nbsp;|&nbsp;
						<a href="<@s.url action="company-delete" companyId="${company.id}" />" target="ajaxTodo" title="确定要删除该公司吗？">删除</a>
					</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar"></div>
    </div>
</div>