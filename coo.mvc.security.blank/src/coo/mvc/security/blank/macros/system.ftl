<#macro organTree organ isRootOrgan="true">
<ul<#if isRootOrgan> class="tree treeFolder"</#if>>
    <li>
		<a href="<@s.url "/system/organ-edit?organId=${organ.id}" />" target="ajax" rel="organBox">${organ.name}</a>
        <#list organ.childs as childOrgan>
        <@organTree childOrgan isRootOrgan="false" />
        </#list>
    </li>
</ul>
</#macro>

<#macro permissions permissionGroups>
    <#list permissionGroups as permissionGroup>
		<fieldset>
			<legend>${permissionGroup.name}</legend>
        <#list permissionGroup.permissions as permission>
        <label>
            <input type="checkbox" name="permissionIds" value="${permission.id}"<#if permissionIds.contains(permission.id)> checked="checked"</#if> />
            ${permission.name}
        </label>
        </#list>
		</fieldset>
    </#list>
</#macro>
