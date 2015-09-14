<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Signin Template for Bootstrap</title>
		
		<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
		<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
		<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<form action="<@s.url "/login-auth" />" method="post" class="form-horizontal center-block" style="width:300px;padding:10px">
				<h2 class="form-signin-heading"><strong>登录系统</strong></h2>
	            <@s.errors path="loginModel" />
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
						<@s.input path="loginModel.username" class="form-control" placeholder="用户名" required="true" autofocus="true" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<span class="input-group-addon"><span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span>
						<@s.password path="loginModel.password" class="form-control" placeholder="密码" required="true" />
					</div>
				</div>
				<#if authCounter.isOver()>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-barcode" aria-hidden="true"></span></span>
							<@s.input path="loginModel.code" class="form-control" placeholder="验证码" required="true" />
							<span class="input-group-addon" style="padding:0px"><a href="javascript:reloadCaptchaCode()" title="点击换一张图片"><img id="captchaCodeImage" src="<@s.url "/captcha-code-image" />" /></a></span>
						</div>
					</div>
                </#if>
                <div class="form-group">
                	<button type="submit" class="btn btn-lg btn-primary btn-block">登 录</button>
                </div>
			</form>
		</div>
		<#if authCounter.isOver()>
	        <script>
				function reloadCaptchaCode() {
					var now = new Date().getTime();
					$("#captchaCodeImage").attr("src", "<@s.url "/captcha-code-image" />?timestamp=" + now);
				}
	        </script>
        </#if>
	</body>
</html>