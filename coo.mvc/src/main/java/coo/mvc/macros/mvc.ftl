<#--
 * 将配置信息编码转换为配置信息文本。
 * 
 * code：配置信息编码
 * args：配置信息参数
 *
 * 示例：
 * <@s.message "user.delete.confirm" />
 -->
<#macro message code args>
    <@compress>
    <#if args?? && args?size != 0>
    	${springMacroRequestContext.getMessage(code, args)}
    <#else>
    	${springMacroRequestContext.getMessage(code)}
    </#if>
    </@compress>
</#macro>

<#--
 * 将相对路径的url转换为绝对路径的url。
 *
 * url：url的相对路径
 * params：用于填充url中变量的参数
 *
 * 示例：
 * <@s.url "/system/user-add" />
 * <@s.url "/system/user-edit?userId=${user.id}" />
 * <@s.url url="/system/user-edit?userId={userId}" userId="${user.id}" /> 
 -->
<#macro url url params...>
    <@compress>
    <#if params?? && params?size!=0>
        ${springMacroRequestContext.getContextUrl(url, params)}
    <#else>
        ${springMacroRequestContext.getContextUrl(url)}
    </#if>
    </@compress>
</#macro>

<#--
 * 链接。
 *
 * href：链接的相对路径
 * attributes：链接的其它属性
 -->
<#macro a href attributes...>
	<@compress>
    <a href="<@url href />" ${getAttributes(attributes)}><#nested></a>
    </@compress>
</#macro>

<#--
 * 绑定变量。
 *
 * path：变量路径
 -->
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

<#--
 * 表单。
 *
 * action：表单提交的相对路径
 * method：表单提交的方式
 * attributes：form的其它属性
 -->
<#macro form action method="post" attributes...>
    <form method="${method}" action="<@url "${action}" />" ${getAttributes(attributes)}>
        <#nested>
    </form>
</#macro>

<#--
 * 文本框。
 *
 * path：文本框绑定的属性路径
 * attributes：文本框的其它属性
 -->
<#macro input path attributes...>
    <@bind path />
    <@replaceAttributes attributes />
    <@compress>
    <input type="text" id="${id}" name="${name}" value="${stringStatusValue}" ${getAttributes(attributes)} />
    </@compress>
</#macro>

<#--
 * 密码框。
 *
 * path：密码框绑定的属性路径
 * attributes：密码框的其它属性
 -->
<#macro password path attributes...>
    <@bind path />
    <@replaceAttributes attributes />
    <@compress>
    <input type="password" id="${id}" name="${name}" ${getAttributes(attributes)} />
    </@compress>
</#macro>

<#--
 * 隐藏域。
 *
 * path：隐藏域绑定的属性路径
 * attributes：隐藏域的其它属性
 -->
<#macro hidden path attributes...>
    <@bind path />
    <@replaceAttributes attributes />
    <input type="hidden" id="${id}" name="${name}" value="${stringStatusValue}" ${getAttributes(attributes)} />
</#macro>

<#--
 * 大文本框。
 *
 * path：大文本框绑定的属性路径
 * attributes：大文本框的其它属性
 -->
<#macro textarea path attributes...>
    <@bind path />
    <@replaceAttributes attributes />
    <@compress>
    <textarea id="${id}" name="${name}" ${getAttributes(attributes)}>${stringStatusValue}</textarea>
    </@compress>
</#macro>

<#--
 * 选择框。
 *
 * path：选择框绑定的属性路径
 * items：选择框选项集合对象
 * itemLabel：选择项文本的属性名
 * itemValue：选择项值的属性名
 * attributes：选择框的其它属性
 -->
<#macro select path items itemLabel itemValue headerLabel headerValue="" attributes...>
    <@bind path />
    <@replaceAttributes attributes />
    <select id="${id}" name="${name}" ${getAttributes(attributes)}>
    	<#if headerLabel?? && headerLabel != "">
    	<option value="${headerValue}">${headerLabel}</option>
    	</#if>
        <@options items itemValue itemLabel status.value />
    </select>
</#macro>

<#--
 * 布尔选择框。
 *
 * path：选择框绑定的属性路径
 * headerLabel：选择框首个选项的文本
 * headerValue：选择框首个选项的值
 * trueLabel：true选项的文本
 * trueValue：true选项的值
 * falseLabel：false选项的文本
 * falseValue：false选项的值
 * attributes：选择框的其它属性
 -->
<#macro boolSelect path headerLabel headerValue="" trueLabel="是" trueValue="1" falseLabel="否" falseValue="0" attributes...>
	<@bind path />
    <@replaceAttributes attributes />
    <select id="${id}" name="${name}" ${getAttributes(attributes)}>
    	<#if headerLabel??>
    	<option value="${headerValue}">${headerLabel}</option>
    	</#if>
    	<#local isTrueSelected = status.value?? && status.value?string=="true">
    	<#local isFalseSelected = status.value?? && status.value?string=="false">
        <option value="${trueValue}" <#if isTrueSelected> selected="selected"</#if>>${trueLabel}</option>
        <option value="${falseValue}" <#if isFalseSelected> selected="selected"</#if>>${falseLabel}</option>
    </select>
</#macro>

<#--
 * 选择项列表。
 *
 * path：选择框绑定的属性路径
 * items：选择框选项集合对象
 * itemValue：选择项值的属性名
 * itemLabel：选择项文本的属性名
 * values：选中的值列表
 -->
<#macro options items itemValue itemLabel values>
    <@bindOptions items itemValue itemLabel values />
    <#list opts?keys as optKey>
        <#local optVal = opts[optKey]>
        <#local isSelected = contains(vals, optKey)>
        <option value="${optKey?html}"<#if isSelected> selected="selected"</#if>>${optVal?html}</option>
    </#list>
</#macro>

<#--
 * 单选组。
 *
 * path：单选组绑定的属性路径
 * items：单选组选项集合对象
 * itemValue：单选组值的属性名
 * itemLabel：单选组文本的属性名
 * prefix：单选项前置内容
 * suffix：单选项后置内容
 * attributes：单选组的其它属性
 -->
<#macro radios path items itemValue itemLabel prefix suffix attributes...>
    <@bind path />
    <@bindOptions items itemValue itemLabel status.value />
    <#list opts?keys as optKey>
    	<@replaceAttributes attributes />
        <#local optVal = opts[optKey]>
        <#local isChecked = contains(vals, optKey)>
        <@compress single_line=true>
        ${prefix}
        <input type="radio" id="${id}" name="${name}" value="${optKey?html}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
        ${optVal?html}
        ${suffix}
        </@compress>
    </#list>
</#macro>

<#--
 * 单选按钮。
 *
 * path：单选组绑定的属性路径
 * prefix：单选项前置内容
 * suffix：单选项后置内容
 * trueText：绑定的属性为true时显示的文本
 * falseText：绑定的属性为false时显示的文本
 * attributes：单选按钮的其它属性
 -->
<#macro radio path prefix suffix trueText="是" falseText="否" attributes...>
    <#if path??>
        <@bind path />
        <@replaceAttributes attributes />
        <#local isChecked = status.value?? && status.value?string=="true">
        ${prefix}<input type="radio" id="${id}" name="${name}" value="1"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />${trueText}${suffix}
        ${prefix}<input type="radio" id="${id}" name="${name}" value="0"<#if !isChecked> checked="checked"</#if> ${getAttributes(attributes)} />${falseText}${suffix}
    <#else>
        ${prefix}<input type="radio" ${getAttributes(attributes)} />${suffix}
    </#if>
</#macro>

<#--
 * 多选组。
 *
 * path：多选组绑定的属性路径
 * items：多选组选项集合对象
 * itemValue：多选组值的属性名
 * itemLabel：多选组文本的属性名
 * prefix：多选项前置内容
 * suffix：多选项后置内容
 * attributes：多选组的其它属性
 -->
<#macro checkboxs path items itemValue itemLabel prefix suffix attributes...>
    <@bind path />
    <#local values = status.value?split(",")>
    <@bindOptions items itemValue itemLabel values />
    <#list opts?keys as optKey>
    	<@replaceAttributes attributes />
        <#local optVal = opts[optKey]>
        <#local isChecked = contains(vals, optKey)>
        <@compress single_line=true>
        ${prefix}
        <input type="checkbox" id="${id}" name="${name}" value="${optKey?html}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
        ${optVal?html}
        ${suffix}
        </@compress>
    </#list>
    <input type="hidden" name="_${status.expression}" value="on"/>
</#macro>

<#--
 * 多选按钮。
 *
 * path：多选按钮绑定的属性路径
 * attributes：多选按钮的其它属性
 -->
<#macro checkbox path attributes...>
    <#if path??>
        <@bind path />
        <@replaceAttributes attributes />
        <#local isChecked = status.value?? && status.value?string=="true">
        <input type="hidden" name="_${status.expression}" value="on"/>
        <input type="checkbox" id="${id}" name="${name}"<#if isChecked> checked="checked"</#if> ${getAttributes(attributes)} />
    <#else>
        <input type="checkbox" ${getAttributes(attributes)} />
    </#if>
</#macro>

<#--
 * 错误信息。
 *
 * path：错误信息绑定的属性路径
 * separator：错误信息之间的分割符
 * attributes：错误信息的其它属性
 -->
<#macro errors path separator attributes...>
    <#if path??>
        <@bind path />
    </#if>
    <#list status.errorMessages as error>
        <span ${getAttributes(attributes)}>${error}</span>
        <#if error_has_next>${separator}</#if>
    </#list>
</#macro>

<#--
 * 用于重置表单组件的id和name属性，用于支持多表单对象绑定时覆盖默认的id和name。
 *
 * attributes：属性集合
 -->
<#macro replaceAttributes attributes>
    <#if attributes?? && attributes?size gt 0>
        <#if contains(attributes?keys, "id")>
            <#assign id = attributes["id"]>
        </#if>
        <#if contains(attributes?keys, "name")>
            <#assign name = attributes["name"]>
        </#if>
        <#if contains(attributes?keys, "value")>
            <#assign stringStatusValue = attributes["value"]>
        </#if>
    </#if>
</#macro>

<#--
 * 绑定选项值。
 *
 * items：选项集合对象
 * itemValue：值的属性名
 * itemLabel：文本的属性名
 * values：选中的值列表
 -->
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

<#--
 * 判断集合中是否包含某个元素。
 *
 * items：元素集合
 * target：目标元素
 -->
<#function contains items target>
    <#list items as item>
        <#if item == target><#return true></#if>
    </#list>
    <#return false>
</#function>

<#--
 * 获取属性值拼装的字符串。
 *
 * attributes：属性值集合
 -->
<#function getAttributes attributes>
    <#local attrs>
        <@compress single_line=true>
        <#if attributes?? && attributes?size gt 0>
            <#list attributes?keys as attributeName>
                ${attributeName}="${attributes[attributeName]}"
            </#list>
        </#if>
        </@compress>
    </#local>
    <#return attrs>
</#function>

<#--
 * 获取选项Map值。
 *
 * items：选项集合对象
 * itemValue：值的属性名
 * itemLabel：文本的属性名
 -->
<#function getOptions items itemValue itemLabel>
    <#local opts>
        <@compress single_line=true>
        {
        <#if items?? && items?size gt 0>
            <#list items as item>
                "${("item." + itemValue)?eval}":"${("item." + itemLabel)?eval}"<#if item_has_next>,</#if>
            </#list>
        </#if>
        }
        </@compress>
    </#local>
    <#return opts?eval>
</#function>