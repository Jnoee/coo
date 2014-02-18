package coo.mvc.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import org.springframework.beans.FatalBeanException;
import org.springframework.ui.freemarker.SpringTemplateLoader;

import freemarker.cache.TemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

public class FreeMarkerConfigurer extends
		org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer {
	private ServletContext servletContext;
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
		getConfiguration().setSharedVariable("enums",
				BeansWrapper.getDefaultInstance().getEnumModels());
		getConfiguration().setSharedVariable("statics",
				BeansWrapper.getDefaultInstance().getStaticModels());
		try {
			getConfiguration().setSharedVariable("ctx",
					servletContext.getContextPath());
		} catch (TemplateModelException e) {
			throw new FatalBeanException("设置FreeMarker全局变量时发生异常。", e);
		}

		for (String autoInclude : autoIncludes) {
			getConfiguration().addAutoInclude(autoInclude);
		}

		for (Entry<String, String> autoImport : autoImports.entrySet()) {
			getConfiguration().addAutoImport(autoImport.getKey(),
					autoImport.getValue());
		}
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
