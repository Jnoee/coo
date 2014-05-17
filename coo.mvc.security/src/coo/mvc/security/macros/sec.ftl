<#--
 * 对应shiro的hasRole标签。
 -->
<#macro has name>
	<@shiro.hasRole name="${name}">
	<#nested>
	</@shiro.hasRole>
</#macro>

<#--
 * 对应shiro的lacksRole标签。
 -->
<#macro not name>
	<@shiro.lacksRole name="${name}">
	<#nested>
	</@shiro.lacksRole>
</#macro>

<#--
 * 对应shiro的hasAnyRoles标签。
 -->
<#macro any name>
	<@shiro.hasAnyRoles name="${name}">
	<#nested>
	</@shiro.hasAnyRoles>
</#macro>