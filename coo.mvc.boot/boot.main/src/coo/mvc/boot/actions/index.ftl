<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=7" />
        <@dwz.head />
        <link href="${ctx}/themes/default/style.css" rel="stylesheet" type="text/css" />
        <link href="${ctx}/themes/css/core.css" rel="stylesheet" type="text/css" />
        <script src="${ctx}/js/site.js" type="text/javascript"></script>
        <title>boot.main</title>
    </head>
    <body scroll="no">
        <div id="layout">
            <div id="header">
                <div class="headerNav">
                    <a class="logo" href="javascript:;">标志</a>
                    <ul class="nav">
                        <li>
                            <a href="javascript:;">${currentUser.name}（${currentUser.settings.defaultActor.fullName}）</a>
                        </li>
                        <li id="switchEnvBox">
                            <a href="javascript:;">切换职务</a>
                            <ul>
                                <#list currentUser.actors as actor>
                                	<li><@s.a href="/system/person-actor-change?actorId=${actor.id}">${actor.organ.name}-${actor.name}</@s.a></li>
                                </#list>
                            </ul>
                        </li>
                        <li><@dwz.a href="/system/person-pwd-change" target="dialog" width="SS" height="SSS">修改密码</@dwz.a></li>
                        <li><@s.a href="/logout">退出</@s.a></li>
                    </ul>
                    <ul class="themeList" id="themeList">
                        <li theme="default"><div class="selected">蓝色</div></li>
                        <li theme="green"><div>绿色</div></li>
                        <li theme="purple"><div>紫色</div></li>
                        <li theme="silver"><div>银色</div></li>
                        <li theme="azure"><div>天蓝</div></li>
                    </ul>
                </div>
                <div id="navMenu">
                    <ul>
                        <@sec.any name="COMPANY_MANAGE,EMPLOYEE_MANAGE">
                        	<li><@s.a href="/company/menu"><span>公司管理</span></@s.a></li>
                        </@sec.any>
                        <@sec.any name="ADMIN">
	                        <li><@s.a href="/system/menu"><span>系统管理</span></@s.a></li>
                        </@sec.any>
                    </ul>
                </div>
            </div>
            <@dwz.leftside />
            <@dwz.container />
        </div>
        <div id="footer">
            &copy; Jnoee
        </div>
        <script type="text/javascript">
            $(function() {
                DWZ.init("dwz.frag.xml", {
                    loginUrl : "${ctx}/login",
                    debug : false,
                    callback : function() {
                        initEnv();
                        $("#themeList").theme({
                            dwzTheme: "${ctx}/dwz/themes",
                            fixTheme: "${ctx}/fix/themes"
                        });
                        navTab.openTab("main", "home", {
                            title : "我的首页"
                        });
                        setTimeout("clickNavMenu(0)", 10);
                    }
                });
            });
        </script>
    </body>
</html>