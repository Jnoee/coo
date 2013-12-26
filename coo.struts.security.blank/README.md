#有安全模块的脚手架项目

##快速开始
以创建一个名为的demo项目为例：

1.	安装和配置好ANT工具。
2.	复制coo.struts.security.blank目录到D盘并修改目录名为demo。
3.	修改D:\demo\build.xml文件中的配置项:

		<property name="new.maven.groupId" value="none" />
		<property name="new.maven.artifactId" value="demo" />
		<property name="new.project.package" value="none.demo" />
		<property name="new.project.package.path" value="/none/demo" />
		<property name="new.project.src" value="src/none/demo" />
		
	将上面这段修改成：
		
		<property name="new.maven.groupId" value="com.xxx" />
		<property name="new.maven.artifactId" value="demo" />
		<property name="new.project.package" value="com.xxx.demo" />
		<property name="new.project.package.path" value="/com/xxx/demo" />
		<property name="new.project.src" value="src/com/xxx/demo" />
		
		
4.	在D:\demo目录下执行ant命令，即可生成一个全新的maven项目。

	导入Eclipse并配置为web application即可在应用服务器中运行。
	
	系统管理员的用户名/密码为admin/admin。