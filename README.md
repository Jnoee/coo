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

#模块组成
coo 由以下子模块项目组成：
* coo.base: 基础模块。基础工具类、异常定义、常量定义、基础模型等。
* coo.core: 核心模块。整合封装Hibernate/Hibernate Search/Spring的核心模块。
* coo.core.support：支撑模块。邮件、报表等通用支撑功能模块，整合封装ichartjs/jxls。
* coo.core.security: 安全模块。整合Shiro作为权限控制模块。
* coo.struts：整合封装Struts2/Freemarker/DWZ/BootStrap作为表现层基础模块。
* coo.struts.security：基于Struts2 Plugin机制的权限控制模块。

#脚手架子项目
coo 提供两个脚手架项目用于快速搭建开发项目：
* coo.struts.blank：不带权限控制的脚手架项目。
* coo.struts.security.blank：带有权限控制的脚手架项目。
