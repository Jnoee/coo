<div class="page">
    <div class="pageHeader">
        <@dwz.pageForm action="/system/log-list" />
    </div>
    <div class="pageContent">
        <table class="table" width="100%" layoutH="85">
            <thead>
                <tr>
                    <th width="140px" align="center">操作时间</th>
                    <th width="100px" align="center">操作用户</th>
                    <th align="center">操作内容</th>
                    <th width="140px" align="center">详细日志</th>
                </tr>
            </thead>
            <tbody>
                <#list logPage.contents as log>
	                <tr target="sid_user" rel="1">
	                    <td>${log.createDate}</td>
	                    <td>${log.creator}</td>
	                    <td style="text-align:left">${log.message}</td>
	                    <td>
	                    	<#if log.hasData()>
	                    		<@dwz.a href="/system/log-view?logId=${log.id}" target="dialog" title="查看详细日志">查看</@dwz.a>
	                    	</#if>
	                    </td>
	                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pageNav pageModel=logPage />
        </div>
    </div>
</div>
