package coo.mvc.security.blank.actions.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import coo.core.hibernate.search.FullTextIndexer;
import coo.core.message.MessageConfig;
import coo.core.security.annotations.Auth;
import coo.core.security.permission.AdminPermission;
import coo.mvc.util.DwzResultUtils;

/**
 * @Description：全文索引
 * @author 李新文 创建日期：2014年3月3日
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class FullTextIndexAction {

	@Autowired
	private FullTextIndexer fullTextIndexer;

	@Autowired
	private MessageConfig messageConfig;

	@RequestMapping("entity-list")
	public void select(Model model) {
		model.addAttribute("indexedEntityClasses",
				fullTextIndexer.getIndexedEntityClasses());
	}

	@RequestMapping("full-text-index-build")
	public ModelAndView build(Class<?>[] entityClasses) {
		fullTextIndexer.startAndWait(entityClasses);
		return DwzResultUtils.refresh(
				messageConfig.getString("full.text.index.build.success"),
				"entity-list");
	}
}
