<div class="page">
    <div class="pageContent">
        <@s.form action="/system/organ-update" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<@s.hidden path="organ.id" />
        <div class="pageFormContent" layoutH="60">
        	<#if organ.parent??>
            <dl class="nowrap">
                <dt>上级机构：</dt>
                <dd><@s.select path="organ.parent" items=parentOrgans  itemValue="id" itemLabel="selectText" class="combox" /></dd>
            </dl>
			<div class="divider" />
			</#if>
            <dl>
                <dt>机构名称：</dt>
                <dd><@s.input path="organ.name" size="30" maxlength="60" class="required" /></dd>
            </dl>
            <dl>
                <dt>序号：</dt>
                <dd><@s.input path="organ.ordinal" size="30" min="0" max="99999" class="digits" /></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
            	<li>
                	<a class="button" href="<@s.url "/system/organ-add?organId=${organ.id}"  />" target="dialog" rel="organ-add" mask="true" width="800" height="480"><span>新增子机构</span></a>
				</li>
                <li>
                    <div class="buttonActive">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        </@s.form>
    </div>
</div>
