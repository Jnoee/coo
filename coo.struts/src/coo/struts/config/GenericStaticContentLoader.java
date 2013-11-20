package coo.struts.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.DefaultStaticContentLoader;

import coo.base.exception.UncheckedException;
import coo.base.util.StringUtils;

/**
 * 静态资源加载器。覆盖struts2默认的静态资源加载器，增加/coo/ui路径下的静态资源加载支持。
 */
public class GenericStaticContentLoader extends DefaultStaticContentLoader {
	private String[] staticPaths = new String[] { "/struts", "/static",
			"/coo/struts/static" };
	private String[] staticPacks = new String[] { "coo.struts.static" };

	@Override
	public void findStaticResource(String path, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 用于解决调试重新加载静态资源文件时产生的pathPrefixed为null的NullPointException异常
		if (pathPrefixes == null) {
			pathPrefixes = parse(getAdditionalPackages());
		}
		super.findStaticResource(path, request, response);
	}

	@Override
	public boolean canHandle(String resourcePath) {
		return serveStatic && (startWithStaticPath(resourcePath));
	}

	@Override
	protected String cleanupPath(String path) {
		for (String staticPath : staticPaths) {
			if (path.startsWith(staticPath)) {
				return StringUtils.substringAfter(path, staticPath);
			}
		}
		throw new UncheckedException("非法的静态资源路径：" + path);
	}

	@Override
	protected String getAdditionalPackages() {
		return super.getAdditionalPackages() + " "
				+ StringUtils.join(staticPacks, " ");
	}

	/**
	 * 判断资源路径是否以静态内容路径开头。
	 * 
	 * @param resourcePath
	 *            资源路径
	 * @return 如果资源路径是以静态内容路径开头返回true，否则返回false。
	 */
	private Boolean startWithStaticPath(String resourcePath) {
		for (String staticPath : staticPaths) {
			if (resourcePath.startsWith(staticPath + "/")) {
				return true;
			}
		}
		return false;
	}
}
