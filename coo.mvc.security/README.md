#表现层安全整合模块
表现层安全整合模块是为了将安全模块扩展到表现层来，主要做了以下的封装：

1.	利用spring拦截器机制集成权限验证拦截器，使得Action（Controller）可以受到权限管控。
2.	引入shiro的taglib库，使得在freemarker页面元素可以受到权限管控。
3.	创建用户名/密码/验证码登录的Action（Controller）基类。

#Action（Controller）权限控制
可以在Action类或其方法上标识`@Auth`来指定访问该Action类或某方法所需的权限。

* 无`@Auth`标识的Action表示无权限限制。
* 无指定权限编码的`@Auth`标识表示用户需要登录才可以访问。
* 指定权限编码的`@Auth("XXX")`标识表示用户需要“XXX”权限才可以访问。
* 指定多个权限编码的`@Auth({"AAA","BBB","CCC"})`标识表示用户需要有“AAA”或“BBB”或“CCC”的权限才可以访问。

#页面元素权限控制
在FreeMarker页面，可以使用Shiro的Taglib来限制页面元素的可见/不可见。

	<div id="navMenu">
        <ul>
			<@shiro.hasAnyRoles name="COMPANY_MANAGE,EMPLOYEE_MANAGE">
        	<li>
        		<a href="company/menu"><span>公司管理</span> </a>
        	</li>
			</@shiro.hasAnyRoles>
			<@shiro.hasAnyRoles name="ADMIN">
            <li>
                <a href="system/menu"><span>系统管理</span> </a>
            </li>
			</@shiro.hasAnyRoles>
        </ul>
    </div>

有关shiro的taglib使用请查阅相关文档。