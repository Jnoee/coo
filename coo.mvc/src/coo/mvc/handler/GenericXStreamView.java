package coo.mvc.handler;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentCollectionConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedSetConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernateProxyConverter;
import com.thoughtworks.xstream.hibernate.mapper.HibernateMapper;

/**
 * 自定义基于XStream的xml视图。
 */
public class GenericXStreamView extends MarshallingView {
	/**
	 * 构造方法。
	 */
	public GenericXStreamView() {
		XStreamMarshaller xstreamMarshaller = new XStreamMarshaller() {
			@Override
			protected void customizeXStream(XStream xstream) {
				xstream.registerConverter(new HibernateProxyConverter());
				xstream.registerConverter(new HibernatePersistentCollectionConverter(
						xstream.getMapper()));
				xstream.registerConverter(new HibernatePersistentMapConverter(
						xstream.getMapper()));
				xstream.registerConverter(new HibernatePersistentSortedMapConverter(
						xstream.getMapper()));
				xstream.registerConverter(new HibernatePersistentSortedSetConverter(
						xstream.getMapper()));
			}
		};
		xstreamMarshaller.setAutodetectAnnotations(true);
		xstreamMarshaller.setMapperWrappers(HibernateMapper.class);
		setMarshaller(xstreamMarshaller);
	}
}
