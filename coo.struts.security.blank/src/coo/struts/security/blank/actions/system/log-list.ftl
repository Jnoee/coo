<div class="page">
    <div class="pageHeader">
        <@dwz.pagerForm action="log-list" />
    </div>
    <div class="pageContent">
        <table class="table" width="100%" layoutH="85">
            <thead>
                <tr>
                    <th width="140px" align="center">操作时间</th>
                    <th width="100px" align="center">操作用户</th>
                    <th align="center">操作内容</th>
                    <th width="140px" align="center">业务数据</th>
                </tr>
            </thead>
            <tbody>
                <#list pageModel.contents as log>
                <tr target="sid_user" rel="1">
                    <td>${log.createDate}</td>
                    <td>${log.creator}</td>
                    <td style="text-align:left">${log.message}</td>
                    <td><#if log.hasData()><a href="<@s.url action="log-data-view" logId="${log.id}" />" target="dialog" rel="log-data-view" mask="true" title="查看业务数据" width="800" height="480">查看</a></#if></td>
                </tr>
                </#list>
            </tbody>
        </table>
        <div class="panelBar">
            <@dwz.pagerNav pageModel=pageModel />
        </div>
    </div>
</div>
