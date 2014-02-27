<#macro message code>
    ${springMacroRequestContext.getMessage(code)}
</#macro>

<#macro messageText code, text>
    ${springMacroRequestContext.getMessage(code, text)}
</#macro>

<#macro messageArgs code, args>
    ${springMacroRequestContext.getMessage(code, args)}
</#macro>

<#macro messageArgsText code, args, text>
    ${springMacroRequestContext.getMessage(code, args, text)}
</#macro>

<#macro theme code>
    ${springMacroRequestContext.getThemeMessage(code)}
</#macro>

<#macro themeText code, text>
    ${springMacroRequestContext.getThemeMessage(code, text)}
</#macro>

<#macro themeArgs code, args>
    ${springMacroRequestContext.getThemeMessage(code, args)}
</#macro>

<#macro themeArgsText code, args, text>
    ${springMacroRequestContext.getThemeMessage(code, args, text)}
</#macro>

<#macro url url params...>
    <#if params?? && params?size!=0>
        ${springMacroRequestContext.getContextUrl(url, params)}
    <#else>
        ${springMacroRequestContext.getContextUrl(url)}
    </#if>
</#macro>

<#macro bind path>
    <#if htmlEscape?exists>
        <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
    <#else>
        <#assign status = springMacroRequestContext.getBindStatus(path)>
    </#if>
    <#if status.value?exists && status.value?is_boolean>
        <#assign stringStatusValue = status.value?string>
    <#else>
        <#assign stringStatusValue = status.value?default("")>
    </#if>
    <#assign id = status.expression?replace("[", "")?replace("]", "")>
    <#assign name = status.expression>
</#macro>

<#macro bindEscaped path, htmlEscape>
    <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
    <#if status.value?exists && status.value?is_boolean>
        <#assign stringStatusValue = status.value?string>
    <#else>
        <#assign stringStatusValue = status.value?default("")>
    </#if>
    <#assign id = status.expression?replace("[", "")?replace("]", "")>
    <#assign name = status.expression>
</#macro>

<#macro form action method="post" attributes...>
    <form method="${method}" action="<@url "${action}" />" ${getAttributes(attributes)}>
        <#nested>
    </form>
</#macro>

<#macro input path attributes...>
    <@bind path/>
    <input type="text" id="${id}" name="${name}" value="${stringStatusValue}" ${getAttributes(attributes)} />
</#macro>

<#macro password path attributes...>
    <@bind path/>
    <input type="password" id="${id}" name="${name}" ${getAttributes(attributes)} />
</#macro>

<#macro hidden path attributes...>
    <@bind path/>
    <input type="hidden" id="${id}" name="${name}" value="${stringStatusValue}" ${getAttributes(attributes)} />
</#macro>

<#macro textarea path attributes...>
    <@bind path/>
    <textarea id="${id}" name="${name}" ${getAttributes(attributes)}>${stringStatusValue}</textarea>
</#macro>

<#macro select path items itemValue itemLabel attributes...>
    <@bind path/>
    <select id="${id}" name="${name}" ${getAttributes(attributes)}>
        <@options items itemValue itemLabel status.value />
    </select>
</#macro>

<#macro options items itemValue itemLabel values>
    <@bindOptions items itemValue itemLabel values />
    <#list opts?keys as optKey>
        <#assign optVal = opts[optKey]>
        <#assign isSelected = contains(vals, optVal)>
        <option value="${optVal?html}"<#if isSelected> selected="selected"</#if>>${optKey?html}</option>
    </#list>
</#macro>

<#macro radios path items itemValue itemLabel separator attributes...>
    <@bind path/>
    <@bindOptions items itemValue itemLabel status.value />
    <#list opts?keys as optKey>
        <#assign id="${id}${optKey_index}">
        <#assign optVal = opts[optKey]>
        <#assign isChecked = contains(vals, optVal)>
        <input type="radio" id="${id}" name="${name}" value="${optVal?html}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
        ${optKey?html}${separator}
    </#list>
</#macro>

<#macro checkboxs path items itemValue itemLabel separator attributes...>
    <@bind path/>
    <@bindOptions items itemValue itemLabel status.actualValue />
    <#list opts?keys as optKey>
        <#assign id="${id}${optKey_index}">
        <#assign optVal = opts[optKey]>
        <#assign isChecked = contains(vals, optVal)>
        <input type="checkbox" id="${id}" name="${name}" value="${optVal?html}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
        ${optKey?html}${separator}
    </#list>
</#macro>

<#macro checkbox path attributes...>
    <@bind path />
    <#assign isChecked = status.value?? && status.value?string=="true">
    <input type="checkbox" id="${id}" name="${name}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
</#macro>

<#macro errors separator attributes...>
    <#list status.errorMessages as error>
        <span ${getAttributes(attributes)}>${error}</span>
        <#if error_has_next>${separator}</#if>
    </#list>
</#macro>

<#macro bindOptions items itemValue itemLabel values>
    <#if itemValue?? && itemLabel??>
        <#assign opts = getOptions(items, itemValue, itemLabel)>
    <#else>
        <#assign opts = items>
    </#if>
    <#if values??>
        <#if !values?is_enumerable>
            <#assign vals = [values]>
        <#else>
            <#assign vals = values>
        </#if>
    <#else>
        <#assign vals = []>
    </#if>
</#macro>

<#function contains items target>
    <#list items as item>
        <#if item == target><#return true></#if>
    </#list>
    <#return false>
</#function>

<#function isIEnum value>
    <#if value?is_string && value?is_hash && value.text?? && value.value??>
        <#return true>
    </#if>
    <#return false>
</#function>

<#function isPrimitive value>
    <#if value?is_string || value?is_number || value?is_boolean || value?is_date>
        <#return true>
    </#if>
    <#return false>
</#function>

<#function getAttributes attributes>
    <#local attrs>
        <#if attributes?? && attributes?size gt 0>
            <#list attributes?keys as attributeName>
                ${attributeName}="${attributes[attributeName]}"
            </#list>
        </#if>
    </#local>
    <#return attrs>
</#function>

<#function getOptions items itemValue itemLabel>
    <#local opts>
        {
        <#if items?? && items?size gt 0>
            <#list items as item>
                "${item[itemLabel]}":"${item[itemValue]}"<#if item_has_next>,</#if>
            </#list>
        </#if>
        }
    </#local>
    <#return opts?eval>
</#function>