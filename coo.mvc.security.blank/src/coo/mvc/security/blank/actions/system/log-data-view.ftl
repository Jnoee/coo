<div class="page">
	<table class="list" width="98%">
		<thead>
			<tr>
				<th width="100">字段</th>
				<th width="120">原值</th>
				<th width="120">新值</th>
			</tr>
		</thead>
		<tbody>
			<#list datas as data>
			<tr>
				<td style="font-weight:bold">${data.text}</td>
				<td>${data.origData}</td>
				<td <#if data.isChanged()>style="color:red"</#if>>${data.newData}</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>
