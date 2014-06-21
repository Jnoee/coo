<@dwz.reload action="/company/company-list" />
<div class="page">
    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li><@dwz.a href="/company/company-add" target="dialog" title="新增公司" height="SS"><span class="a09">新增</span></@dwz.a></li>
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
                    	<@dwz.a href="/company/company-edit?companyId=${company.id}" target="dialog" title="编辑公司" height="SS">编辑</@dwz.a>
						|
						<@dwz.a href="/company/company-delete?companyId=${company.id}" target="ajaxTodo" title="确定要删除该公司吗？">删除</@dwz.a>
						|
						<@dwz.a href="/system/log-detail-list?entityId=${company.id}" target="dialog" title="查看操作记录">操作记录</@dwz.a>
					</td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar"></div>
    </div>
</div>