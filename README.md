#项目简介
**把复杂的事情变简单，把繁琐的事情变简洁。**

coo 是一个 Java Web Application 开发整合框架，用于支撑快速开发。
它基于市面流行的开源框架进行封装整合，屏蔽这些开源框架本身的复杂性。
以降低开发人员的学习成本并提升开发效率和质量。

coo 全部利用开源框架本身的接口和特性进行封装和扩展，对于开源框架本身没有任何侵入性的修改。
所有开源框架本身具备的功能和特性依然可以毫无阻碍的继续使用。

从分层的角度来看COO整合的主要的开源框架包括：
* 数据层：[Hibernate][]/[Hibernate Search][]
* 业务层：[Spring][]/[Shiro][]
* 表现层：[Struts2][]/[FreeMarker][]
* UI层：[DWZ][]/[BootStrap][]

#框架构成
![框架构成](框架构成.png)

COO由以下子模块项目组成：
* [coo.base](coo.base): 基础模块。基础工具类、异常定义、常量定义、基础模型等。
* [coo.core](coo.core): 核心模块。整合封装[Hibernate][]/[Hibernate Search][]/[Spring][]的核心模块。
* [coo.core.support](coo.core.support)：支撑模块。邮件、报表等通用支撑功能模块，整合封装[ichartjs][]/[jxls][]。
* [coo.core.security](coo.core.security): 安全模块。整合[Shiro][]作为权限控制模块。
* [coo.struts](coo.struts)：整合封装[Struts2][]/[FreeMarker][]/[DWZ][]/[BootStrap][]作为表现层基础模块。
* [coo.struts.security](coo.struts.security)：基于Struts2 Plugin机制的权限控制模块。
* [coo.struts.blank](coo.struts.blank)：不带权限控制的脚手架项目。
* [coo.struts.security.blank](coo.struts.security.blank)：带有权限控制的脚手架项目。
* [coo.db](coo.db)：数据库设计。

#依赖关系
![依赖关系](依赖关系.png)

#快速开始
*	[无安全模块的脚手架](coo.struts.blank)
*	[有安全模块的脚手架](coo.struts.security.blank)
	
[Hibernate]: http://hibernate.org/
[Hibernate Search]: http://hibernate.org/search/
[Spring]: http://projects.spring.io/spring-framework/
[Shiro]: http://shiro.apache.org/
[Struts2]: http://struts.apache.org/development/2.x/
[FreeMarker]: http://freemarker.org/
[DWZ]: http://j-ui.com/
[BootStrap]: http://getbootstrap.com/
[ichartjs]: http://www.ichartjs.com/
[jxls]: http://jxls.sourceforge.net/
