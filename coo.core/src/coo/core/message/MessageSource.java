package coo.core.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import coo.base.constants.Encoding;
import coo.base.exception.BusinessException;
import coo.base.exception.UncheckedException;
import coo.base.util.StringUtils;

/**
 * 该类定义了信息配置组件。<br/>
 * 信息配置文件指定放置在/META-INF/coo目录下，名称以messages结尾，采用标准的Properties XML文件格式。
 */
@Component
public class MessageSource extends ReloadableResourceBundleMessageSource {
	private static final String MESSAGE_DIR = "/META-INF/coo/";

	/**
	 * 构造方法。
	 */
	public MessageSource() {
		setDefaultEncoding(Encoding.UTF_8);
		setUseCodeAsDefaultMessage(true);
		setBasenames("classpath*:" + MESSAGE_DIR + "*messages.xml");
	}

	/**
	 * 获取指定编码的信息。
	 * 
	 * @param code
	 *            信息编码
	 * @param vars
	 *            信息变量
	 * @return 返回指定编码的信息。
	 */
	public String get(String code, Object... vars) {
		return getMessage(code, vars, null);
	}

	/**
	 * 抛出业务异常。
	 * 
	 * @param code
	 *            信息编码
	 * @param vars
	 *            信息变量
	 */
	public void thrown(String code, Object... vars) {
		throw new BusinessException(get(code, vars));
	}

	/**
	 * 抛出业务异常。
	 * 
	 * @param ex
	 *            异常对象
	 * @param code
	 *            信息编码
	 * @param vars
	 *            信息变量
	 */
	public void thrown(Throwable ex, String code, Object... vars) {
		throw new BusinessException(get(code, vars));
	}

	@Override
	public void setBasenames(String... basenames) {
		try {
			List<String> baseNames = new ArrayList<String>();
			for (Resource resource : getResources(basenames)) {
				String uri = resource.getURI().toString();
				if (resource instanceof FileSystemResource
						|| resource instanceof UrlResource) {
					String baseName = "classpath:"
							+ MESSAGE_DIR
							+ StringUtils.substringBetween(uri, MESSAGE_DIR,
									".xml");
					baseNames.add(baseName);
					logger.debug("加载配置信息文件[" + baseName + "]...");
				}
			}
			super.setBasenames(baseNames.toArray(new String[] {}));
			logger.debug("加载配置信息文件成功。");
		} catch (IOException e) {
			throw new UncheckedException("加载配置信息文件时发生异常。", e);
		}
	}

	/**
	 * 根据配置文件路径获取资源列表。
	 * 
	 * @param basenames
	 *            配置文件路径
	 * @return 返回配置文件路径获取资源列表。
	 * @throws IOException
	 *             获取资源列表失败时抛出异常。
	 */
	private List<Resource> getResources(String... basenames) throws IOException {
		List<Resource> resources = new ArrayList<Resource>();
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		for (String basename : basenames) {
			for (Resource resource : resourcePatternResolver
					.getResources(basename)) {
				resources.add(resource);
			}
		}
		return resources;
	}
}
