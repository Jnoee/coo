package coo.struts.security.blank.actions.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ModelDriven;

import coo.base.model.Page;
import coo.core.model.SearchModel;
import coo.core.security.annotations.Auth;
import coo.core.security.entity.BnLog;
import coo.core.security.model.LogData;
import coo.core.security.permission.AdminPermission;
import coo.core.security.service.BnLogger;
import coo.struts.actions.GenericAction;

/**
 * 日志管理。
 */
@Auth(AdminPermission.CODE)
public class LogAction extends GenericAction implements
		ModelDriven<SearchModel> {
	@Resource
	private BnLogger bnLogger;
	private SearchModel searchModel = new SearchModel();
	private Page<BnLog> pageModel;
	private List<LogData> datas = new ArrayList<LogData>();

	/**
	 * 查看日志列表。
	 * 
	 * @return 返回查看日志列表页面。
	 */
	@Action("log-list")
	public String list() {
		pageModel = bnLogger.searchLog(searchModel);
		return SUCCESS;
	}

	/**
	 * 查看日志详细记录。
	 * 
	 * @return 返回查看日志详细记录页面。
	 */
	@Action("log-data-view")
	public String logDataView() {
		String logId = ServletActionContext.getRequest().getParameter("logId");
		BnLog log = bnLogger.getLog(logId);
		datas = log.toLogData();
		return SUCCESS;
	}

	public Page<BnLog> getPageModel() {
		return pageModel;
	}

	public void setPageModel(Page<BnLog> pageModel) {
		this.pageModel = pageModel;
	}

	public List<LogData> getDatas() {
		return datas;
	}

	public void setDatas(List<LogData> datas) {
		this.datas = datas;
	}

	@Override
	public SearchModel getModel() {
		return searchModel;
	}
}
