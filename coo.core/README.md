#核心库
核心库基于spring对hibernate进行了扩展和封装，实现了DAO层组件的自动装配。
此外自定义了一些常用的hibernate数据类型，以便于这些数据类型的存储。

对hibernate search进行了封装，屏蔽掉涉及lucene的接口，降低了开发人员应用全文搜索的门槛。

此外还定义了一些常用组件，例如：验证码组件、提示信息组件、spring工具组件等。

##泛型DAO组件自动装配
泛型DAO封装了常用的数据存储操作，通过继承泛型DAO来实现具体的DAO组件可以节省大量代码，泛型DAO的应用已非常普遍。

有了泛型DAO以后，我们依然面临一件繁琐的事情：针对每个业务实体来创建相应的DAO组件类。

核心模块通过自动扫描@Entity标注的业务实体类来自动生成对应的DAO组件，例如：

	User -> userDao
	Role -> roleDao
	Site -> siteDao
	SiteDic -> siteDicDao
	
在Service层可以直接通过DAO组件名称进行注入引用：

	public class UserService {
		@Resource
		private Dao<User> userDao;
		@Resource
		private Dao<Role> roleDao;
	}
	
	public class SiteService {
		@Resource
		private Dao<Site> siteDao;
		@Resource
		private Dao<SiteDic> siteDicDao;
	}

这意味着我们通过**一个类就实现了整个DAO层**，因为在spring容器启动过程中核心模块自动为我们装配了所需要的DAO组件。

##枚举类型
Java的枚举类的设计不能很好的满足中文应用的需求，因为它只提供了2个属性：name（String）和ordinal（Integer）。
而我们在应用枚举时通常需要3个属性：

1. name（String）：英文名称，编码时用，可以沿用Java枚举的name。
2. text（String）：枚举文本，显示时用。
3. value（String）：枚举值，存储时用。

以最常见的性别枚举类来举例：

1. name：MALE/FEMALE
2. text：男/女
3. value：001/002

从上面的例子可以看到，text在Java枚举类中没有对应属性，而value用ordinal又不合适。

核心模块针对中文应用的需求提出一个枚举类的接口IEnum，实现了该接口的枚举类将会得到存储和展示上的便利：

1. 通过简单的标注即可对枚举属性进行透明存储，无需开发人员处理。
		
		public class Person {
			@Type(type="IEnum")
			private Sex sex = Sex.MALE;
		}
			
2. 在页面可以直接显示枚举的文本。

		性别：${person.sex.text}
		
当然对于编码而言它有一个更重要的意义：**将对枚举的处理和转换代码集中在一个地方，而不是散乱在应用的各个角落。**

##自定义数据类型
hibernate可以扩展自定义数据类型的机制相当强大，这使得开发者可以通过自定义类型来实现无缝存储。

在核心模块中定义了以下常用的自定义数据类型：

1. ArrayUserType：字符串数组自定义类型，对应String[]类型的属性。
2. ArrayListUserType：字符串列表自定义类型，对应List<String>类型的属性。
3. JsonUserType：Json格式自定义类型，对应复合结构对象Object属性。
4. JsonListUserType：Json格式自定义列表类型，对应复合结构对象列表List<Object>属性。
5. IEnumUserType：自定义枚举类型，对应IEnum类型的属性。
6. IEnumListUserType：自定义枚举列表类型，对应List<IEnum>类型的属性。
7. BitCodeUserType：位编码自定义类型，对应BitCode类型的属性。
8. ParamUserType：参数型自定义类型，对应Param类型的属性。

##全文搜索
hibernate search整合了hibernate和lucene，实现了数据存储与全文索引的同步维护，而这一切对于开发者是透明的。
这使得全文搜索的使用门槛降低了很多，并可以被方便的应用到大多数的查询需求中去。

开发人员学习hibernate search需要付出一定的成本，包括hibernate search的资料以及lucene的相关资料。
核心模块对全文搜索的封装就是希望降低这一块的学习成本，让开发人员可以快速的应用全文搜索到项目中。

参照hibernate的查询构造器（Criteria），核心模块构建了一个FullTextCriteria全文索引查询构造器来帮助开发
人员快速构建基于全文索引的查询条件，结合hibernate search提供的相关Annotation注解，开发人员可以在不熟悉
hibernate search和lucene的情况下轻松应用全文搜索。

全文搜索能给项目带来的好处：

1. 替代like提升查询效率。
2. 替代连接查询提升查询效率。
3. 处理大文本字段的内容搜索。

##提示信息组件
用于集中管理系统中各层的提示信息，使开发人员采用统一的配置文件和接口来定义和使用这些提示信息。