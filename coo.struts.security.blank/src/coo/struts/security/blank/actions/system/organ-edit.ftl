<div class="page">
    <div class="pageContent">
        <@s.form action="organ-update" cssClass="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
		<@s.hidden name="organ.id" value="${organ.id}" />
        <div class="pageFormContent" layoutH="60">
        	<#if organ.parent??>
            <dl class="nowrap">
                <dt>上级机构：</dt>
                <dd><@s.select name="organ.parent" list="parentOrgans" listKey="id" listValue="selectText" cssClass="combox" value="organ.parent.id" /></dd>
            </dl>
			<div class="divider" />
			</#if>
            <dl>
                <dt>机构名称：</dt>
                <dd><@s.textfield name="organ.name" size="30" maxlength="60" cssClass="required" /></dd>
            </dl>
            <dl>
                <dt>序号：</dt>
                <dd><@s.textfield name="organ.ordinal" size="30" min="0" max="99999" cssClass="digits" /></dd>
            </dl>
        </div>
        <div class="formBar">
            <ul>
            	<li>
                	<a class="button" href="<@s.url action="organ-add" organId="${organ.id}" />" target="dialog" rel="organ-add" mask="true" width="800" height="480"><span>新增子机构</span></a>
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
