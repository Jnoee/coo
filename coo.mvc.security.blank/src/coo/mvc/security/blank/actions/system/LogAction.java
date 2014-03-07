package coo.mvc.security.blank.actions.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.core.security.entity.BnLog;
import coo.core.security.permission.AdminPermission;
import coo.core.security.service.BnLogger;

/**
 * 日志管理。
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class LogAction {

	@Resource
	private BnLogger bnLogger;

	/**
	 * 查看日志列表。
	 * 
	 * @param model
	 *            数据模型
	 * @param searchModel
	 *            搜索条件
	 */
	@RequestMapping("log-list")
	public void list(Model model, SearchModel searchModel) {
		model.addAttribute("logPage", bnLogger.searchLog(searchModel));
	}

	/**
	 * 查看日志详细记录。
	 * 
	 * @param logId
	 *            日志ID
	 * @param model
	 *            数据模型
	 */
	@RequestMapping("log-data-view")
	public void logDataView(String logId, Model model) {
		BnLog log = bnLogger.getLog(logId);
		model.addAttribute("datas", log.toLogData());
	}
}
