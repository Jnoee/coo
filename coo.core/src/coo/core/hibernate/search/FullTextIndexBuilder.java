package coo.core.hibernate.search;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 全文索引构建组件。
 */
@Component
@Lazy(false)
public class FullTextIndexBuilder {
	@Resource
	private FullTextIndexer fullTextIndexer;
	@Value("${search.build:false}")
	private Boolean build = false;

	/**
	 * 根据配置在启动时构建全文索引。
	 */
	@PostConstruct
	protected void build() {
		if (build) {
			fullTextIndexer.startAndWait();
		}
	}
}