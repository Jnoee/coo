<div class="page">
    <div class="pageContent">
        <@dwz.form action="/system/organ-update">
			<@s.hidden path="organ.id" />
	        <div class="pageFormContent" layoutH="56">
	        	<#if organ.parent??>
	            <dl class="nowrap">
	                <dt>上级机构：</dt>
	                <dd><@s.select path="organ.parent" items=parentOrgans  itemValue="id" itemLabel="selectText" class="combox" /></dd>
	            </dl>
				<div class="divider" />
				</#if>
	            <dl>
	                <dt>机构名称：</dt>
	                <dd><@s.input path="organ.name" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>序号：</dt>
	                <dd><@s.input path="organ.ordinal" min="0" max="99999" class="digits" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar showCancelBtn=false>
	       		<li>
	               	<@dwz.a class="button" href="/system/organ-add?organId=${organ.id}" target="dialog"><span>新增子机构</span></@dwz.a>
				</li>
	        </@dwz.formBar>
        </@dwz.form>
    </div>
</div>