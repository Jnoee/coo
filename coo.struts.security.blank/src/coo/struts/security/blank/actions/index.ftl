<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=7" />
        <@dwz.head />
        <link href="themes/default/style.css" rel="stylesheet" type="text/css" />
        <link href="themes/css/core.css" rel="stylesheet" type="text/css" />
        <script src="js/site.js" type="text/javascript"></script>
        <title>coo.struts.security.blank</title>
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
                                <li>
                                    <a href="<@s.url namespace="system" action="person-actor-change" actorId="${actor.id}" />">${actor.organ.name}-${actor.name}</a>
                                </li>
                                </#list>
                            </ul>
                        </li>
                        <li>
                            <a href="<@s.url namespace="system" action="person-pwd-change" />" target="dialog" rel="person-pwd-change" mask="true" title="修改密码">修改密码</a>
                        </li>
                        <li>
                            <a href="<@s.url action="logout" />">退出</a>
                        </li>
                    </ul>
                    <ul class="themeList" id="themeList">
                        <li theme="default">
                            <div class="selected">
                                蓝色
                            </div>
                        </li>
                        <li theme="green">
                            <div>
                                绿色
                            </div>
                        </li>
                        <li theme="purple">
                            <div>
                                紫色
                            </div>
                        </li>
                        <li theme="silver">
                            <div>
                                银色
                            </div>
                        </li>
                        <li theme="azure">
                            <div>
                                天蓝
                            </div>
                        </li>
                    </ul>
                </div>
                <div id="navMenu">
                    <ul>
						<@shiro.hasAnyRoles name="COMPANY_MANAGE,EMPLOYEE_MANAGE">
                    	<li>
                    		<a href="company/menu.do"><span>公司管理</span> </a>
                    	</li>
						</@shiro.hasAnyRoles>
						<@shiro.hasAnyRoles name="ADMIN">
                        <li>
                            <a href="system/menu.do"><span>系统管理</span> </a>
                        </li>
						</@shiro.hasAnyRoles>
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
					loginUrl : "login.do",
					debug : false,
					callback : function() {
						initEnv();
						$("#themeList").theme();
						navTab.openTab("main", "home.do", {
							title : "我的首页"
						});
						setTimeout("clickNavMenu(0)", 10);
					}
				});
			});
        </script>
    </body>
</html>
