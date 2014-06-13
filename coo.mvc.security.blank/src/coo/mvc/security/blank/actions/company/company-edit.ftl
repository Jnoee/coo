<div class="page">
    <div class="pageContent">
        <@dwz.form action="/company/company-update">
	        <@s.hidden path="company.id" />
	        <div class="pageFormContent" layoutH="60">
	            <dl>
	                <dt>名称：</dt>
	                <dd><@s.input path="company.name" maxlength="60" class="required" /></dd>
	            </dl>
	            <dl>
	                <dt>成立时间：</dt>
	                <dd><@s.input path="company.foundDate" class="required date" /></dd>
	            </dl>
	            <dl class="nowrap">
	                <dt>地址：</dt>
	                <dd><@s.input path="company.extendInfo.address" maxlength="60" class="l-input" /></dd>
	            </dl>
	            <dl>
	                <dt>电话：</dt>
	                <dd><@s.input path="company.extendInfo.tel" maxlength="20" /></dd>
	            </dl>
	            <dl>
	                <dt>传真：</dt>
	                <dd><@s.input path="company.extendInfo.fax" maxlength="20" /></dd>
	            </dl>
	        </div>
	        <@dwz.formBar />
        </@dwz.form>
    </div>
</div>