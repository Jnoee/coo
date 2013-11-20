<?xml version="1.0" encoding="UTF-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<@std.errorHead><title>操作成功</title></@std.errorHead>
</head>
<body>
	<div class="std-error-panel">
		<div class="head">操作成功</div>
		<div class="body">${httpResult.message}</div>
		<div class="foot">
			<a href="${httpResult.returnUrl}">确定</a>
		</div>
	</div>
</body>
</html>
