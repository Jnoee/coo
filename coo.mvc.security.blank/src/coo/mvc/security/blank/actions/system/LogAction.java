package coo.mvc.security.blank.actions.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.core.security.entity.BnLog;
import coo.core.security.permission.AdminPermission;
import coo.core.security.service.BnLogger;

/**
 * @Description：日志管理
 * @author 李新文 创建日期：2014年3月3日
 */
@Controller
@RequestMapping("/system")
@Auth(AdminPermission.CODE)
public class LogAction {

	@Autowired
	private BnLogger bnLogger;

	/**
	 * 查询日志列表
	 * 
	 * @param model
	 *            model
	 * @param searchModel
	 *            SearchModel
	 */
	@RequestMapping(value = "log-list")
	public void list(Model model, SearchModel searchModel) {
		model.addAttribute("logPage", bnLogger.searchLog(searchModel));
	}

	/**
	 * 查询日志详情
	 * 
	 * @param logId
	 *            logId
	 * @param model
	 *            model
	 */
	@RequestMapping("log-data-view")
	public void logDataView(String logId, Model model) {
		BnLog log = bnLogger.getLog(logId);
		model.addAttribute("datas", log.toLogData());
	}
}
