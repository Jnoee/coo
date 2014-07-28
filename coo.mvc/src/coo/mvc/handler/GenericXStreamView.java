package coo.mvc.handler;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.thoughtworks.xstream.XStream;

import coo.core.xstream.GenericXStream;

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
			protected XStream constructXStream() {
				return new GenericXStream();
			}
		};
		setMarshaller(xstreamMarshaller);
	}
}
