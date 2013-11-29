<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2><span class="a33">Folder</span>公司管理</h2>
    </div>
    <div class="accordionContent">
        <ul class="tree treeFolder">
            <@shiro.hasRole name="COMPANY_MANAGE">
            <li>
                <a href="<@s.url action="company-list"/>" target="navTab" rel="company-list">公司管理</a>
            </li>
            </@shiro.hasRole>
			<@shiro.hasRole name="EMPLOYEE_MANAGE">
            <li>
                <a href="<@s.url action="employee-list"/>" target="navTab" rel="employee-list">职员管理</a>
            </li>
            </@shiro.hasRole>
        </ul>
    </div>
</div>
