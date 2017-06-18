# coo 框架

coo 是一个 Java Web Application 开发框架，用于支撑快速开发。它基于主流的Java开源框架进行封装整合，屏蔽这些开源框架本身的复杂性，以降低开发人员的学习成本并提升开发效率和质量。

coo 对开源框架的封装和扩展都是基于这些框架本身的接口和扩展机制，并没有任何侵入性的修改，所以开源框架本身具备的功能和特性依然可以毫无阻碍的继续使用。

从分层的角度来看coo整合的主要开源框架包括：
* 数据层：[hibernate][]/[hibernate search][]
* 业务层：[spring][]/[shiro][]
* 表现层：[spring mvc][]/[freemarker][]
* UI框架：[dwz][]

想了解更多的信息可以查看 [参考手册](https://jnoee.gitbooks.io/coo-doc/content) 。

[hibernate]: http://hibernate.org/
[hibernate search]: http://hibernate.org/search/
[spring]: http://projects.spring.io/spring-framework/
[shiro]: http://shiro.apache.org/
[spring mvc]: http://projects.spring.io/spring-framework/
[freemarker]: http://freemarker.org/
[dwz]: https://github.com/Jnoee/dwz