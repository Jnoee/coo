package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;

import coo.core.hibernate.search.FullTextIndexer;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.struts.actions.GenericAction;
import coo.struts.util.AjaxResultUtils;

/**
 * 全文索引管理。
 */
@Auth(AdminPermission.CODE)
public class FullTextIndexAction extends GenericAction {
	@Resource
	private FullTextIndexer fullTextIndexer;
	private List<Class<?>> indexedEntityClasses = new ArrayList<Class<?>>();

	/**
	 * 查看全文索引选择页面。
	 * 
	 * @return 返回查看全文索引选择页面。
	 */
	@Action("entity-list")
	public String select() {
		indexedEntityClasses = fullTextIndexer.getIndexedEntityClasses();
		return SUCCESS;
	}

	/**
	 * 重建选中的全文索引。
	 * 
	 * @return 返回操作成功信息。
	 */
	@Action("full-text-index-build")
	public String build() {
		fullTextIndexer.startAndWait(indexedEntityClasses
				.toArray(new Class<?>[] {}));
		return AjaxResultUtils.refresh(
				getMessage("full.text.index.build.success"), "entity-list");
	}

	public List<Class<?>> getIndexedEntityClasses() {
		return indexedEntityClasses;
	}

	public void setIndexedEntityClasses(List<Class<?>> indexedEntityClasses) {
		this.indexedEntityClasses = indexedEntityClasses;
	}
}
