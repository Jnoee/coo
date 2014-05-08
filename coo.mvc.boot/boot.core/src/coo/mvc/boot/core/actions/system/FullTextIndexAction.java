package coo.mvc.boot.core.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.hibernate.search.FullTextIndexer;
import coo.core.message.MessageSource;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.util.NavTabResultUtils;

/**
 * 全文索引管理。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class FullTextIndexAction {
	@Resource
	private FullTextIndexer fullTextIndexer;
	@Resource
	private MessageSource messageSource;

	/**
	 * 查看全文索引选择页面。
	 * 
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("entity-list")
	public void select(Model model) {
		model.addAttribute("indexedEntityClasses",
				fullTextIndexer.getIndexedEntityClasses());
	}

	/**
	 * 重建选中的全文索引。
	 * 
	 * @param entityClasses
	 *            关联索引名称
	 * @return 返回操作成功信息。
	 */
	@RequestMapping("full-text-index-build")
	public ModelAndView build(Class<?>[] entityClasses) {
		fullTextIndexer.startAndWait(entityClasses);
		return NavTabResultUtils.reload(messageSource
				.get("full.text.index.build.success"));
	}
}
