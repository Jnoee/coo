package coo.core.jackson;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import coo.base.util.DateUtils;

/**
 * 对Jackson的ObjectMapper封装。设置json的输出格式、时间格式及Hibernate懒加载支持。
 */
public class GenericObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 733096684965378016L;

	/**
	 * 构造方法。
	 */
	public GenericObjectMapper() {
		// 值为null的属性不输出
		setSerializationInclusion(Include.NON_NULL);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		setDateFormat(new SimpleDateFormat(DateUtils.SECOND));
		Hibernate4Module hibernateModule = new Hibernate4Module();
		hibernateModule.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING,
				true);
		registerModule(hibernateModule);
		registerModule(new JodaModule());
	}
}