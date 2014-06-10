<div class="accordion" fillSpace="sidebar">
    <div class="accordionHeader">
        <h2><span class="a33">Folder</span>公司管理</h2>
    </div>
    <div class="accordionContent">
        <ul class="tree">
            <@sec.any name="COMPANY_MANAGE">
	            <li><@dwz.a href="/company/company-list">公司管理</@dwz.a></li>
            </@sec.any>
            <@sec.any name="EMPLOYEE_MANAGE">
	            <li><@dwz.a href="/company/employee-list">职员管理</@dwz.a></li>
            </@sec.any>
        </ul>
    </div>
</div>
