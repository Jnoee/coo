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