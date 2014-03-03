package coo.mvc.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.ui.freemarker.SpringTemplateLoader;

import coo.base.util.ClassUtils;
import coo.base.util.DateUtils;
import coo.base.util.StringUtils;
import coo.core.model.IEnum;
import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义FreeMarker配置管理。
 */
public class FreeMarkerConfigurer extends
		org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer {
	private ServletContext servletContext;
	private List<String> enumPackages = new ArrayList<String>();
	private List<Class<?>> staticClasses = new ArrayList<Class<?>>();
	private List<String> templatePaths = new ArrayList<String>();
	private List<String> autoIncludes = new ArrayList<String>();
	private Map<String, String> autoImports = new HashMap<String, String>();

	@Override
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
		this.servletContext = servletContext;
	}

	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		initEnums();
		initStatics();
		initSharedVariables();
		initAutoIncludes();
		initAutoImports();
	}

	@Override
	protected void postProcessTemplateLoaders(
			List<TemplateLoader> templateLoaders) {
		super.postProcessTemplateLoaders(templateLoaders);
		for (String templatePath : templatePaths) {
			templateLoaders.add(new SpringTemplateLoader(getResourceLoader(),
					templatePath));
		}
	}

	/**
	 * 初始化枚举变量。
	 * 
	 * @throws TemplateModelException
	 *             初始化枚举变量失败时抛出异常
	 */
	protected void initEnums() throws TemplateModelException {
		TemplateHashModel enums = BeansWrapper.getDefaultInstance()
				.getEnumModels();
		getConfiguration().setSharedVariable("enums", enums);
		for (Class<?> enumClass : ClassUtils.findClassesByParentClass(
				IEnum.class, enumPackages.toArray(new String[] {}))) {
			getConfiguration().setSharedVariable(enumClass.getSimpleName(),
					enums.get(enumClass.getName()));
		}
	}

	/**
	 * 初始化静态变量。
	 * 
	 * @throws TemplateModelException
	 *             初始化静态变量失败时抛出异常。
	 */
	protected void initStatics() throws TemplateModelException {
		TemplateHashModel statics = BeansWrapper.getDefaultInstance()
				.getStaticModels();
		getConfiguration().setSharedVariable("statics", statics);
		staticClasses.add(StringUtils.class);
		staticClasses.add(DateUtils.class);
		for (Class<?> staticClass : staticClasses) {
			getConfiguration().setSharedVariable(staticClass.getSimpleName(),
					statics.get(staticClass.getName()));
		}
	}

	/**
	 * 初始化公共变量。
	 * 
	 * @throws TemplateModelException
	 *             初始化公共变量失败时抛出异常。
	 */
	protected void initSharedVariables() throws TemplateModelException {
		getConfiguration().setSharedVariable("ctx",
				servletContext.getContextPath());
	}

	/**
	 * 初始化自动包含文件。
	 */
	protected void initAutoIncludes() {
		for (String autoInclude : autoIncludes) {
			getConfiguration().addAutoInclude(autoInclude);
		}
	}

	/**
	 * 初始化自动导入文件。
	 */
	protected void initAutoImports() {
		for (Entry<String, String> autoImport : autoImports.entrySet()) {
			getConfiguration().addAutoImport(autoImport.getKey(),
					autoImport.getValue());
		}
	}

	public List<String> getEnumPackages() {
		return enumPackages;
	}

	public void setEnumPackages(List<String> enumPackages) {
		this.enumPackages = enumPackages;
	}

	public List<Class<?>> getStaticClasses() {
		return staticClasses;
	}

	public void setStaticClasses(List<Class<?>> staticClasses) {
		this.staticClasses = staticClasses;
	}

	public List<String> getTemplatePaths() {
		return templatePaths;
	}

	public void setTemplatePaths(List<String> templatePaths) {
		this.templatePaths = templatePaths;
	}

	public List<String> getAutoIncludes() {
		return autoIncludes;
	}

	public void setAutoIncludes(List<String> autoIncludes) {
		this.autoIncludes = autoIncludes;
	}

	public Map<String, String> getAutoImports() {
		return autoImports;
	}

	public void setAutoImports(Map<String, String> autoImports) {
		this.autoImports = autoImports;
	}
}
