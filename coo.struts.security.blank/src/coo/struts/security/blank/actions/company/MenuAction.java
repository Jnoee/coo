package coo.struts.security.blank.actions.company;

import coo.core.security.annotations.Auth;
import coo.struts.actions.GenericAction;

/**
 * 公司管理菜单。
 */
@Auth({ "COMPANY_MANAGE", "EMPLOYEE_MANAGE" })
public class MenuAction extends GenericAction {
}
