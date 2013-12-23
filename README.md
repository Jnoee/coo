#项目简介
coo 是一个 Java Web Application 开发整合框架，用于支撑快速开发。
它基于市面流行的开源框架进行封装整合，屏蔽这些开源框架本身的复杂性。
以降低开发人员的学习成本并提升开发效率和质量。

coo 全部利用开源框架本身的接口和特性进行封装和扩展，对于开源框架本身没有任何侵入性的修改。
所有开源框架本身具备的功能和特性依然可以毫无阻碍的继续使用。

从分层的角度来看COO整合的主要的开源框架包括：
* 数据层：Hibernate/Hibernate Search
* 业务层：Spring/Shiro
* 表现层：Struts2/Freemarker
* UI层：DWZ/BootStrap

#框架构成
![框架构成](框架构成.png)

COO由以下子模块项目组成：
* [coo.base](coo.base): 基础模块。基础工具类、异常定义、常量定义、基础模型等。
* [coo.core](coo.core): 核心模块。整合封装Hibernate/Hibernate Search/Spring的核心模块。
* [coo.core.support](coo.core.support)：支撑模块。邮件、报表等通用支撑功能模块，整合封装ichartjs/jxls。
* [coo.core.security](coo.core.security): 安全模块。整合Shiro作为权限控制模块。
* [coo.struts](coo.struts)：整合封装Struts2/Freemarker/DWZ/BootStrap作为表现层基础模块。
* [coo.struts.security](coo.struts.security)：基于Struts2 Plugin机制的权限控制模块。
* [coo.struts.blank](coo.struts.blank)：不带权限控制的脚手架项目。
* [coo.struts.security.blank](coo.struts.security.blank)：带有权限控制的脚手架项目。
* [coo.db](coo.db)：数据库设计。

#依赖关系
![依赖关系](依赖关系.png)

#快速开始
1.	安装和配置好ANT工具。
2.	获取coo.struts.blank或coo.struts.security.blank脚手架项目。
3.	修改脚手架项目目录中的build.xml文件中的配置项。

		<!-- 新的配置变量定义 -->
		<property name="new.maven.groupId" value="none" />
		<property name="new.maven.artifactId" value="demo" />
		<property name="new.project.package" value="none.demo" />
		<property name="new.project.package.path" value="/none/demo" />
		<property name="new.project.src" value="src/none/demo" />
		
4.	执行ant命令，即可生成一个全新的maven项目。

	导入Eclipse并配置为web application即可在应用服务器中运行。
	
	系统管理员的用户名/密码为admin/admin。