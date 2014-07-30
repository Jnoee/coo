package coo.core.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentCollectionConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedSetConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernateProxyConverter;
import com.thoughtworks.xstream.hibernate.mapper.HibernateMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import coo.core.model.IEnum;

/**
 * 对XStream的封装。支持Hibernate懒加载。
 */
public class GenericXStream extends XStream {
	/**
	 * 构造方法。
	 */
	public GenericXStream() {
		// 自动读取Annotations配置，不过该功能并不可靠，需注意。
		autodetectAnnotations(true);
		// 注册Hibernate懒加载converter。
		registerConverter(new HibernateProxyConverter());
		registerConverter(new HibernatePersistentCollectionConverter(
				getMapper()));
		registerConverter(new HibernatePersistentMapConverter(getMapper()));
		registerConverter(new HibernatePersistentSortedMapConverter(getMapper()));
		registerConverter(new HibernatePersistentSortedSetConverter(getMapper()));
		registerConverter(new IEnumConverter(IEnum.class));
		// 不输出class属性
		aliasSystemAttribute(null, "class");
	}

	@Override
	protected MapperWrapper wrapMapper(final MapperWrapper next) {
		return new HibernateMapper(next);
	}
}
