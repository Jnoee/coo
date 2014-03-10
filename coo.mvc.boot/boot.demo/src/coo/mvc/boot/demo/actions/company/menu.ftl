<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2><span class="a33">Folder</span>公司管理</h2>
    </div>
    <div class="accordionContent">
        <ul class="tree treeFolder">
            <@shiro.hasAnyRoles name="COMPANY_MANAGE">
            <li>
                <a href="<@s.url "/company/company-list" />" target="navTab" rel="company-list">公司管理</a>
            </li>
            </@shiro.hasAnyRoles>
            <@shiro.hasAnyRoles name="EMPLOYEE_MANAGE">
            <li>
                <a href="<@s.url "/company/employee-list" />" target="navTab" rel="employee-list">职员管理</a>
            </li>
            </@shiro.hasAnyRoles>
        </ul>
    </div>
</div>
