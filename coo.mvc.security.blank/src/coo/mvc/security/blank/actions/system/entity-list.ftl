<div class="page">
    <div class="pageContent">
    	<div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a href="javascript:void(0);" onclick="$('#fullTextIndexForm').submit()">
						<span class="a34">重建全文索引</span>
					</a>
                </li>
            </ul>
        </div>
        <@s.form  id="fullTextIndexForm" action="/system/full-text-index-build" class="pageForm" onsubmit="return validateCallback(this,navTabAjaxDone)">
        <table class="table" width="100%" layoutH="75">
            <thead>
                <tr>
                    <th width="80" align="center">
                        <input type="checkbox" class="checkboxCtrl" group="entityClasses" value="true" />
                    </th>
                    <th>实体类名</th>
                </tr>
            </thead>
            <tbody>
                <#list indexedEntityClasses as indexedEntityClass>
                <tr>
                    <td>
                        <input type="checkbox" name="entityClasses" value="${indexedEntityClass.name}" />
                    </td>
                    <td>${indexedEntityClass.name}</td>
                </tr>
                </#list>
            </tbody>
        </table>
        </@s.form>
		<div class="panelBar"></div>
    </div>
</div>