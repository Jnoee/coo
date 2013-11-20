@TypeDefs(value = {
		@TypeDef(name = "BitCode", typeClass = BitCodeUserType.class),
		@TypeDef(name = "Params", typeClass = ParamsUserType.class),
		@TypeDef(name = "IEnum", typeClass = IEnumUserType.class),
		@TypeDef(name = "IEnumList", typeClass = IEnumListUserType.class),
		@TypeDef(name = "Json", typeClass = JsonUserType.class),
		@TypeDef(name = "JsonList", typeClass = JsonListUserType.class),
		@TypeDef(name = "Array", typeClass = ArrayUserType.class) })
package coo.core.hibernate.usertype;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

