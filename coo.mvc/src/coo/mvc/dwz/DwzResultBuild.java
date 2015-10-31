package coo.mvc.dwz;

import org.springframework.web.servlet.ModelAndView;

import coo.base.util.StringUtils;
import coo.core.message.MessageSource;
import coo.core.util.SpringUtils;
import coo.mvc.handler.GenericJacksonView;

public class DwzResultBuild {
	private DwzResult result = new DwzResult();

	public ModelAndView build() {
		ModelAndView mv = new ModelAndView();
		GenericJacksonView view = new GenericJacksonView();
		view.setModelKey("dwzResult");
		mv.setView(view);
		mv.addObject(result);
		return mv;
	}

	public DwzResultBuild success(String code, Object... vars) {
		result.setStatusCode(DwzStatusCode.OK);
		result.setMessage(getMessage(code, vars));
		return this;
	}

	public DwzResultBuild error(String code, Object... vars) {
		result.setStatusCode(DwzStatusCode.ERROR);
		result.setMessage(getMessage(code, vars));
		return this;
	}

	public DwzResultBuild timeout() {
		result.setStatusCode(DwzStatusCode.TIMEOUT);
		result.setMessage(DwzStatusCode.TIMEOUT_DEFAULT_MSG);
		return this;
	}

	public DwzResultBuild denied() {
		result.setStatusCode(DwzStatusCode.ERROR);
		result.setMessage(DwzStatusCode.DENIED_DEFAULT_MSG);
		return this;
	}

	public DwzResultBuild fail() {
		result.setStatusCode(DwzStatusCode.ERROR);
		result.setMessage(DwzStatusCode.FAIL_DEFAULT_MSG);
		return this;
	}

	public DwzResultBuild closeNavTab() {
		return closeNavTab("");
	}

	public DwzResultBuild closeNavTab(String navTabId) {
		result.getCloseNavTab().add(navTabId);
		return this;
	}

	public DwzResultBuild closeDialog() {
		return closeDialog("");
	}

	public DwzResultBuild closeDialog(String dialogId) {
		result.getCloseDialog().add(dialogId);
		return this;
	}

	public DwzResultBuild reloadNavTab() {
		return reloadNavTab("");
	}

	public DwzResultBuild reloadNavTab(String params) {
		return reloadNavTab(params, "");
	}

	public DwzResultBuild reloadNavTab(String params, String callback) {
		return reloadNavTab("", params, callback);
	}

	public DwzResultBuild reloadNavTab(String navTabId, String params,
			String callback) {
		String[] args = { navTabId, "", params, callback };
		result.getReloadNavTab().add(StringUtils.join(args, ","));
		return this;
	}

	public DwzResultBuild forwardNavTab(String url) {
		return forwardNavTab(url, "");
	}

	public DwzResultBuild forwardNavTab(String url, String params) {
		return forwardNavTab(url, params, "");
	}

	public DwzResultBuild forwardNavTab(String url, String params,
			String callback) {
		return forwardNavTab("", url, params, callback);
	}

	public DwzResultBuild forwardNavTab(String navTabId, String url,
			String params, String callback) {
		String[] args = { navTabId, url, params, callback };
		result.getReloadNavTab().add(StringUtils.join(args, ","));
		return this;
	}

	public DwzResultBuild reloadDialog() {
		return reloadDialog("");
	}

	public DwzResultBuild reloadDialog(String params) {
		return reloadDialog(params, "");
	}

	public DwzResultBuild reloadDialog(String params, String callback) {
		return reloadDialog("", params, callback);
	}

	public DwzResultBuild reloadDialog(String dialogId, String params,
			String callback) {
		String[] args = { dialogId, "", params, callback };
		result.getReloadDialog().add(StringUtils.join(args, ","));
		return this;
	}

	public DwzResultBuild forwardDialog(String url) {
		return forwardDialog(url, "");
	}

	public DwzResultBuild forwardDialog(String url, String params) {
		return forwardDialog(url, params, "");
	}

	public DwzResultBuild forwardDialog(String url, String params,
			String callback) {
		return forwardDialog("", url, params, callback);
	}

	public DwzResultBuild forwardDialog(String dialogId, String url,
			String params, String callback) {
		String[] args = { dialogId, url, params, callback };
		result.getReloadDialog().add(StringUtils.join(args, ","));
		return this;
	}

	public DwzResultBuild reloadDiv(String divId) {
		return reloadDiv(divId, "");
	}

	public DwzResultBuild reloadDiv(String divId, String params) {
		return reloadDiv(divId, params, "");
	}

	public DwzResultBuild reloadDiv(String divId, String params, String callback) {
		String[] args = { divId, "", params, callback };
		result.getReloadDiv().add(StringUtils.join(args, ","));
		return this;
	}

	public DwzResultBuild forwardDiv(String divId, String url) {
		return forwardDiv(divId, url, "");
	}

	public DwzResultBuild forwardDiv(String divId, String url, String params) {
		return forwardDiv(divId, url, params, "");
	}

	public DwzResultBuild forwardDiv(String divId, String url, String params,
			String callback) {
		String[] args = { divId, url, params, callback };
		result.getReloadDiv().add(StringUtils.join(args, ","));
		return this;
	}

	private String getMessage(String code, Object... vars) {
		MessageSource messageSource = SpringUtils.getBean("messageSource");
		return messageSource.get(code, vars);
	}
}
