#项目简介
coo是一个 Java Web Application 开发框架，用于支撑快速开发。它基于市面流行的开源框架进行封装整合，屏蔽这些开源框架本身的复杂性。以降低开发人员的学习成本并提升开发效率和质量。

coo全部利用开源框架本身的接口和特性进行封装和扩展，对于开源框架本身没有任何侵入性的修改。所有开源框架本身具备的功能和特性依然可以毫无阻碍的继续使用。

从分层的角度来看coo整合的主要的开源框架包括：
* 数据层：[hibernate][]/[hibernate search][]
* 业务层：[spring][]/[shiro][]
* 表现层：[spring mvc][]/[freemarker][]
* UI层：[dwz][]/[bootstrap][]

coo支持模块化开发，通过简单的配置即可实现对模块的插拔和升级（非热部署），这给产品的模块化定制及分发后聚拢升级带来极大的便利。

#框架构成
![框架构成](框架构成.png)

coo由以下子模块项目组成：
* coo.base：基础模块。基础工具类、异常定义、常量定义、基础模型等。
* coo.core：核心模块。整合封装[hibernate][]/[hibernate search][]/[spring][]的核心模块。
* coo.core.support：支撑模块。邮件、报表等通用支撑功能模块，整合封装[jxls][]。
* coo.core.security：安全模块。整合[shiro][]作为权限控制模块。
* coo.mvc：整合封装[spring mvc][]/[freemarker][]/[dwz][]/[bootstrap][]/[echarts][]作为表现层基础模块。
* coo.mvc.security：基于[spring mvc][]的权限控制模块。

#依赖关系
![依赖关系](依赖关系.png)

#快速开始
* 示例及脚手架项目 [coo.boot](https://github.com/Jnoee/coo.boot)
	
[hibernate]: http://hibernate.org/
[hibernate search]: http://hibernate.org/search/
[spring]: http://projects.spring.io/spring-framework/
[shiro]: http://shiro.apache.org/
[spring mvc]: http://projects.spring.io/spring-framework/
[freemarker]: http://freemarker.org/
[dwz]: http://j-ui.com/
[bootstrap]: http://getbootstrap.com/
[echarts]: http://echarts.baidu.com/
[jxls]: http://jxls.sourceforge.net/