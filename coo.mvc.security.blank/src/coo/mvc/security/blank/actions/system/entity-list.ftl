<div class="page">
    <div class="pageContent">
    	<div class="panelBar">
            <ul class="toolBar">
                <li>
                    <@dwz.a href="/system/full-text-index-build" target="selectedTodo" rel="entityClasses" title="您确定要重建这些全文索引吗？">
						<span class="a34">重建全文索引</span>
					</@dwz.a>
                </li>
            </ul>
        </div>
        <table class="table" width="100%" layoutH="75">
            <thead>
                <tr>
                    <th width="80" align="center">
                        <@s.checkbox class="checkboxCtrl" group="entityClasses" value="true" />
                    </th>
                    <th>实体类名</th>
                </tr>
            </thead>
            <tbody>
                <#list indexedEntityClasses as indexedEntityClass>
	                <tr>
	                    <td>
	                        <@s.checkbox name="entityClasses" value=indexedEntityClass.name />
	                    </td>
	                    <td>${indexedEntityClass.name}</td>
	                </tr>
                </#list>
            </tbody>
        </table>
		<div class="panelBar"></div>
    </div>
</div>