<#macro organTree organ>
	<ul class="tree">
		<@organTreeNode organ=organ />
	</ul>
</#macro>

<#macro organTreeNode organ>
	<li>
		<@dwz.a href="/system/organ-edit?organId=${organ.id}" target="ajax" rel="organBox" organId="${organ.id}">${organ.name}</@dwz.a>
		<#if organ.childs?size gt 0>
			<ul>
		        <#list organ.childs as childOrgan>
		        	<@organTreeNode organ=childOrgan />
		        </#list>
        	</ul>
        </#if>
    </li>
</#macro>

<#macro permissions permissionGroups>
    <#list permissionGroups as permissionGroup>
		<fieldset>
			<legend>${permissionGroup.name}</legend>
	        <#list permissionGroup.permissions as permission>
		        <label>
		            <input type="checkbox" name="permissionIds" value="${permission.id}"<#if permissionIds?seq_contains(permission.id)> checked="checked"</#if> />
		            ${permission.name}
		        </label>
	        </#list>
		</fieldset>
    </#list>
</#macro>

<#macro log_view log>
	<dl>
		<dt>操作时间：</dt>
		<dd>${log.createDate}</dd>
	</dl>
	<dl>
		<dt>操作用户：</dt>
		<dd>${log.creator}</dd>
	</dl>
	<dl>
		<dt>操作内容：</dt>
		<dd>${log.message}</dd>
	</dl>
	<table class="list" width="98%">
		<thead>
			<tr>
				<th width="100">字段</th>
				<th width="120">原值</th>
				<th width="120">新值</th>
			</tr>
		</thead>
		<tbody>
			<#list log.toLogData() as data>
				<tr>
					<td style="font-weight:bold">${data.text}</td>
					<td>${data.origData}</td>
					<td <#if data.isChanged()>style="color:red"</#if>>${data.newData}</td>
				</tr>
			</#list>
		</tbody>
	</table>
</#macro>