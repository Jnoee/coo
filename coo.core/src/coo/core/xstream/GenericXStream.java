package coo.core.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentCollectionConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedSetConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernateProxyConverter;
import com.thoughtworks.xstream.hibernate.mapper.HibernateMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * 对XStream的封装。支持Hibernate懒加载。
 */
public class GenericXStream extends XStream {
	/**
	 * 构造方法。
	 */
	public GenericXStream() {
		registerConverter(new HibernateProxyConverter());
		registerConverter(new HibernatePersistentCollectionConverter(
				getMapper()));
		registerConverter(new HibernatePersistentMapConverter(getMapper()));
		registerConverter(new HibernatePersistentSortedMapConverter(getMapper()));
		registerConverter(new HibernatePersistentSortedSetConverter(getMapper()));
	}

	@Override
	protected MapperWrapper wrapMapper(final MapperWrapper next) {
		return new HibernateMapper(next);
	}
}
