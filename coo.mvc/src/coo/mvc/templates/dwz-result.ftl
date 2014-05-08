{
"statusCode":"${ajaxResult.statusCode}",
"message":"${ajaxResult.message}",
"navTabId":"${ajaxResult.navTabId}",
"rel":"${ajaxResult.rel}",
"callbackType":"${ajaxResult.callbackType}",
"forwardUrl":"<#if ajaxResult.forwardUrl?length gt 0>${ctx}${ajaxResult.forwardUrl}</#if>"
}
