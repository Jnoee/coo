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
        <@s.form id="fullTextIndexForm" action="/system/full-text-index-build" class="pageForm" onsubmit="return validateCallback(this,navTabAjaxDone)">
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
        </@s.form>
		<div class="panelBar"></div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#fullTextIndexForm").validate({
            rules: {
                "entityClasses": {
                    required: true
                }
            },
            messages: {
                "entityClasses": {
                    required: "您至少要选择一个需要重建索引的实体类。"
                }
            },
            errorPlacement: function(error, element) {
                alertMsg.error(error.text());
            }
        });
    });
</script>