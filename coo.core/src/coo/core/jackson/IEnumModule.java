package coo.core.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * IEnum枚举模块。
 */
@SuppressWarnings("serial")
public class IEnumModule extends SimpleModule {
	/**
	 * 构造方法。
	 */
	public IEnumModule() {
		super("jackson-datatype-ienum");
		addSerializer(new IEnumSerializer());
	}
}
