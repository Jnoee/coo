package coo.core.message;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * 该类定义了信息配置组件。信息配置文件名称为messages.xml，采用标准的Properties XML文件格式。
 */
@Component
public class MessageConfig implements ResourceLoaderAware {
	private Logger log = LoggerFactory.getLogger(getClass());
	private final static String CONFIG_FILE_PATH = "classpath:META-INF/coo/messages.xml";
	private Properties messageProperties;

	/**
	 * 获取指定编码的信息。
	 * 
	 * @param code
	 *            信息编码
	 * @param vars
	 *            信息变量
	 * @return 返回指定编码的信息。
	 */
	public String getString(String code, Object... vars) {
		String message = messageProperties.getProperty(code);
		for (Object var : vars) {
			message = message.replaceFirst("\\$\\{var\\}", var.toString());
		}
		return message;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		try {
			messageProperties = new Properties();
			messageProperties.loadFromXML(resourceLoader.getResource(
					CONFIG_FILE_PATH).getInputStream());
			log.info("加载配置信息文件成功。");
		} catch (Exception e) {
			log.warn("加载配置信息文件时发生异常。没有找到[" + CONFIG_FILE_PATH
					+ "]信息配置文件或该文件格式有错误。");
		}
	}
}
