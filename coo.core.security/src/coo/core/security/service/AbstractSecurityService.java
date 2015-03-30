package coo.core.security.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import coo.base.model.BitCode;
import coo.base.model.Page;
import coo.base.util.BeanUtils;
import coo.base.util.StringUtils;
import coo.core.hibernate.dao.Dao;
import coo.core.hibernate.search.FullTextCriteria;
import coo.core.message.MessageSource;
import coo.core.model.SearchModel;
import coo.core.security.annotations.AutoFillIn;
import coo.core.security.annotations.DetailLog;
import coo.core.security.annotations.DetailLog.LogType;
import coo.core.security.annotations.SimpleLog;
import coo.core.security.constants.AdminIds;
import coo.core.security.entity.ActorEntity;
import coo.core.security.entity.BnLogEntity;
import coo.core.security.entity.OrganEntity;
import coo.core.security.entity.RoleEntity;
import coo.core.security.entity.UserEntity;
import coo.core.security.entity.UserSettingsEntity;
import coo.core.security.permission.AdminPermission;
import coo.core.security.permission.PermissionConfig;

/**
 * 安全模块抽象服务类。
 * 
 * @param <O>
 *            机构类型
 * @param <U>
 *            用户类型
 * @param <R>
 *            角色类型
 * @param <A>
 *            职务类型
 * @param <S>
 *            用户设置类型
 */
public abstract class AbstractSecurityService<O extends OrganEntity<O, U, A>, U extends UserEntity<U, A, S>, R extends RoleEntity<U, A>, A extends ActorEntity<O, U, R>, S extends UserSettingsEntity<A>> {
	@Resource
	protected Dao<O> organDao;
	@Resource
	protected Dao<U> userDao;
	@Resource
	protected Dao<R> roleDao;
	@Resource
	protected Dao<A> actorDao;
	@Resource
	protected Dao<S> userSettingsDao;
	@Resource
	protected LoginRealm loginRealm;
	@Resource
	protected PermissionConfig permissionConfig;
	@Resource
	protected MessageSource messageSource;
	@Autowired(required = false)
	@Qualifier("bnLogger")
	protected AbstractBnLogger<? extends BnLogEntity> bnLogger;

	/**
	 * 登录。
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param ip
	 *            IP地址
	 */
	@SimpleLog(code = "user.logon.log", vars = "ip")
	public void signIn(String username, String password, String ip) {
		try {
			AuthenticationToken token = new UsernamePasswordToken(username,
					password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			loginRealm.clearCache();
		} catch (DisabledAccountException de) {
			messageSource.thrown(de, "user.disabled");
		} catch (UnknownAccountException ue) {
			messageSource.thrown(ue, "user.not.exist");
		} catch (IncorrectCredentialsException ie) {
			messageSource.thrown(ie, "password.wrong");
		} catch (AuthenticationException ae) {
			messageSource.thrown(ae, "login.failed");
		}
	}

	/**
	 * 退出登录。
	 */
	public void signOut() {
		loginRealm.clearCache();
		SecurityUtils.getSubject().logout();
	}

	/**
	 * 获取指定ID的机构。
	 * 
	 * @param id
	 *            机构ID
	 * @return 返回指定ID的机构。
	 */
	@Transactional(readOnly = true)
	public O getOrgan(String id) {
		return organDao.get(id);
	}

	/**
	 * 新增机构。
	 * 
	 * @param organ
	 *            机构
	 */
	@Transactional
	@AutoFillIn
	@SimpleLog(code = "organ.add.log", vars = "organ.name")
	public void createOrgan(O organ) {
		if (organ.getParent() == null) {
			messageSource.thrown("organ.add.no.parent");
		}
		organDao.save(organ);
	}

	/**
	 * 更新机构。
	 * 
	 * @param organ
	 *            机构
	 */
	@Transactional
	@AutoFillIn
	@DetailLog(target = "organ", code = "organ.edit.log", vars = "organ.name", type = LogType.ALL)
	public void updateOrgan(O organ) {
		O origOrgan = getOrgan(organ.getId());
		BeanUtils.copyFields(organ, origOrgan, "ordinal", null);
	}

	/**
	 * 删除机构。
	 * 
	 * @param organ
	 *            机构
	 */
	@Transactional
	@DetailLog(target = "organ", code = "organ.delete.log", vars = "organ.name", type = LogType.ORIG)
	public void deleteOrgan(O organ) {
		organDao.remove(organ);
	}

	/**
	 * 获取指定ID的角色。
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 返回指定ID的角色。
	 */
	@Transactional(readOnly = true)
	public R getRole(String roleId) {
		return roleDao.get(roleId);
	}

	/**
	 * 获取所有角色列表。
	 * 
	 * @return 返回所有角色列表。
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<R> getAllRole() {
		Criteria criteria = roleDao.createCriteria();
		criteria.addOrder(Order.asc("createDate"));
		return criteria.list();
	}

	/**
	 * 新增角色。
	 * 
	 * @param role
	 *            角色
	 */
	@Transactional
	@AutoFillIn
	@SimpleLog(code = "role.add.log", vars = "role.name")
	public void createRole(R role) {
		if (!roleDao.isUnique(role, "name")) {
			messageSource.thrown("role.name.exist", role.getName());
		}
		roleDao.save(role);
	}

	/**
	 * 更新角色。
	 * 
	 * @param role
	 *            角色
	 */
	@Transactional
	@AutoFillIn
	@DetailLog(target = "role", code = "role.edit.log", vars = "role.name", type = LogType.ALL)
	public void updateRole(R role) {
		if (!roleDao.isUnique(role, "name")) {
			messageSource.thrown("role.name.exist", role.getName());
		}
		R origRole = getRole(role.getId());
		BeanUtils.copyFields(role, origRole);
	}

	/**
	 * 获取当前登录用户。
	 * 
	 * @return 返回当前登录用户。
	 */
	@Transactional(readOnly = true)
	public U getCurrentUser() {
		try {
			String userId = (String) SecurityUtils.getSubject().getPrincipal();
			return userDao.get(userId);
		} catch (Exception e) {
			throw new UnauthenticatedException("获取当前登录用户时发生异常。", e);
		}
	}

	/**
	 * 获取默认操作人。
	 * 
	 * @return 当存在当前登录用户时返回当前登录用户，否则返回超级管理员用户。
	 */
	@Transactional(readOnly = true)
	public U getDefaultOperator() {
		try {
			String userId = (String) SecurityUtils.getSubject().getPrincipal();
			return userDao.get(userId);
		} catch (Exception e) {
			return getAdminUser();
		}
	}

	/**
	 * 获取当前登录用户默认职务所属机构。
	 * 
	 * @return 返回当前登录用户默认职务所属机构。
	 */
	@Transactional(readOnly = true)
	public O getCurrentOrgan() {
		return getCurrentUser().getSettings().getDefaultActor().getOrgan();
	}

	/**
	 * 分页全文搜索用户。
	 * 
	 * @param searchModel
	 *            搜索条件
	 * @return 返回全文搜索用户分页对象。
	 */
	@Transactional(readOnly = true)
	public Page<U> searchUser(SearchModel searchModel) {
		FullTextCriteria criteria = userDao.createFullTextCriteria();
		criteria.setKeyword(searchModel.getKeyword());
		criteria.addSortDesc("createDate", SortField.Type.LONG);

		// 将系统管理员从搜索的用户结果中排除
		BooleanQuery bq = new BooleanQuery();
		bq.add(new TermQuery(new Term("id", AdminIds.USER_ID)), Occur.MUST_NOT);
		bq.add(new WildcardQuery(new Term("id", "*")), Occur.MUST);
		criteria.addLuceneQuery(bq, Occur.MUST);

		return userDao.searchPage(criteria, searchModel.getPageNo(),
				searchModel.getPageSize());
	}

	/**
	 * 获取指定ID的用户。
	 * 
	 * @param id
	 *            用户ID
	 * @return 返回指定ID的用户。
	 */
	@Transactional(readOnly = true)
	public U getUser(String id) {
		return userDao.get(id);
	}

	/**
	 * 获取指定用户名的用户。
	 * 
	 * @param username
	 *            用户名
	 * @return 返回指定用户名的用户。
	 */
	@Transactional(readOnly = true)
	public U getUserByUsername(String username) {
		return userDao.findUnique("username", username);
	}

	/**
	 * 新增用户。
	 * 
	 * @param user
	 *            用户
	 */
	@Transactional
	@AutoFillIn
	@SimpleLog(code = "user.add.log", vars = "user.name")
	public void createUser(U user) {
		if (!userDao.isUnique(user, "username")) {
			messageSource.thrown("username.exist", user.getUsername());
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			user.setPassword(loginRealm
					.encryptPassword(AdminPermission.DEFAULT_PASSWORD));
		}
		userDao.save(user);

		S settings = user.getSettings();

		A defaultActor = settings.getDefaultActor();
		defaultActor.setUser(user);
		defaultActor.autoFillIn();
		actorDao.save(defaultActor);

		settings.setId(user.getId());
		userSettingsDao.save(settings);
	}

	/**
	 * 更新用户。
	 * 
	 * @param user
	 *            用户
	 */
	@Transactional
	@AutoFillIn
	@DetailLog(target = "user", code = "user.edit.log", vars = "user.name", type = LogType.ALL)
	public void updateUser(U user) {
		if (!userDao.isUnique(user, "username")) {
			messageSource.thrown("username.exist", user.getUsername());
		}
		U origUser = getUser(user.getId());
		BeanUtils.copyFields(user, origUser, "enabled,settings");
		// 如果关联的用户设置不为空，则同时更新用户设置。
		if (user.getSettings() != null) {
			BeanUtils.copyFields(user.getSettings(), origUser.getSettings());
		}
	}

	/**
	 * 更新用户设置。
	 * 
	 * @param userSettings
	 *            用户设置
	 */
	@Transactional
	public void updateUserSettings(S userSettings) {
		S origUserSettings = userSettingsDao.get(userSettings.getId());
		BeanUtils.copyFields(userSettings, origUserSettings);
	}

	/**
	 * 删除指定的用户。
	 * 
	 * @param user
	 *            用户
	 */
	@Transactional
	@DetailLog(target = "user", code = "user.delete.log", vars = "user.name", type = LogType.ORIG)
	public void deleteUser(U user) {
		userDao.remove(user);
	}

	/**
	 * 启用用户。
	 * 
	 * @param user
	 *            用户
	 */
	@Transactional
	@SimpleLog(code = "user.enable.log", vars = "user.name")
	public void enableUser(U user) {
		user.setEnabled(true);
	}

	/**
	 * 禁用用户。
	 * 
	 * @param user
	 *            用户
	 */
	@Transactional
	@SimpleLog(code = "user.disable.log", vars = "user.name")
	public void disableUser(U user) {
		user.setEnabled(false);
	}

	/**
	 * 重置密码。
	 * 
	 * @param managePassword
	 *            管理员密码
	 * @param user
	 *            重置用户
	 */
	@Transactional
	@SimpleLog(code = "user.reset.password.log", vars = "user.name")
	public void resetPassword(String managePassword, U user) {
		if (!loginRealm.checkPassword(managePassword, getCurrentUser()
				.getPassword())) {
			messageSource.thrown("admin.password.wrong");
		}
		user.setPassword(loginRealm
				.encryptPassword(AdminPermission.DEFAULT_PASSWORD));
	}

	/**
	 * 修改密码。
	 * 
	 * @param oldPwd
	 *            原密码
	 * @param newPwd
	 *            新密码
	 */
	@Transactional
	@SimpleLog(code = "user.change.password.log")
	public void changePassword(String oldPwd, String newPwd) {
		U user = getCurrentUser();
		if (!loginRealm.checkPassword(oldPwd, user.getPassword())) {
			messageSource.thrown("old.password.wrong");
		}
		user.setPassword(loginRealm.encryptPassword(newPwd));
	}

	/**
	 * 获取指定ID的职务。
	 * 
	 * @param actorId
	 *            职务ID
	 * @return 返回指定ID的职务。
	 */
	@Transactional(readOnly = true)
	public A getActor(String actorId) {
		return actorDao.get(actorId);
	}

	/**
	 * 新增职务。
	 * 
	 * @param actor
	 *            职务
	 */
	@Transactional
	@AutoFillIn
	@SimpleLog(code = "actor.add.log", vars = { "actor.user.name", "actor.name" })
	public void createActor(A actor) {
		actorDao.save(actor);
	}

	/**
	 * 更新职务。
	 * 
	 * @param actor
	 *            职务
	 */
	@Transactional
	@AutoFillIn
	@DetailLog(target = "actor", code = "actor.edit.log", vars = {
			"actor.user.name", "actor.name" }, type = LogType.ALL)
	public void updateActor(A actor) {
		A origActor = getActor(actor.getId());
		BeanUtils.copyFields(actor, origActor);
	}

	/**
	 * 删除指定的职务。
	 * 
	 * @param actor
	 *            职务
	 */
	@Transactional
	@SimpleLog(code = "actor.delete.log", vars = { "actor.user.name",
			"actor.name" })
	public void deleteActor(A actor) {
		if (actor.isDefaultActor()) {
			messageSource.thrown("default.actor.not.allow.delete");
		}
		actorDao.remove(actor);
	}

	/**
	 * 当前登录用户切换到指定的职务。
	 * 
	 * @param actorId
	 *            职务ID
	 */
	@Transactional
	public void changeActor(String actorId) {
		U currentUser = getCurrentUser();
		A actor = getActor(actorId);
		if (!currentUser.getActors().contains(actor)) {
			messageSource.thrown("actor.change.not.allow");
		}
		currentUser.getSettings().setDefaultActor(actor);
		loginRealm.clearCache();
	}

	/**
	 * 查找具有指定权限的用户。
	 * 
	 * @param permissionCode
	 *            权限编码
	 * @return 返回具有指定权限的用户列表。
	 */
	@Transactional(readOnly = true)
	public List<U> findUserByPermission(String permissionCode) {
		return findUserByPermissions(new String[] { permissionCode },
				new String[] {});
	}

	/**
	 * 查找具有指定权限的用户。仅限于用户的某个职务同时满足权限条件，而非用户的所有职务组合满足权限条件。
	 * 
	 * @param includePermissionCodes
	 *            包含权限编码
	 * @return 返回满足权限条件的用户集合。
	 */
	@Transactional(readOnly = true)
	public List<U> findUserByPermissions(String[] includePermissionCodes) {
		return findUserByPermissions(includePermissionCodes, new String[] {});
	}

	/**
	 * 查找具有指定权限的用户。仅限于用户的某个职务同时满足权限条件，而非用户的所有职务组合满足权限条件。
	 * 
	 * @param includePermissionCodes
	 *            包含权限编码
	 * @param excludePermissionCodes
	 *            不包含权限编码
	 * @return 返回满足权限条件的用户集合。
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<U> findUserByPermissions(String[] includePermissionCodes,
			String[] excludePermissionCodes) {
		List<Integer> trueBits = permissionConfig
				.getPermissionIds(includePermissionCodes);
		List<Integer> falseBits = permissionConfig
				.getPermissionIds(excludePermissionCodes);
		BitCode permissionCode = new BitCode().getQueryBitCode(
				trueBits.toArray(new Integer[] {}),
				falseBits.toArray(new Integer[] {}));
		Criteria criteria = userDao.createCriteria();
		criteria.createAlias("actors", "actors");
		criteria.createAlias("actors.role", "role");
		criteria.add(Restrictions.like("role.permissions",
				permissionCode.toString()));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	/**
	 * 获取系统根机构。
	 * 
	 * @return 返回系统根机构。
	 */
	public O getRootOrgan() {
		return organDao.get(AdminIds.ORGAN_ID);
	}

	/**
	 * 获取系统管理员用户。
	 * 
	 * @return 返回系统管理员用户。
	 */
	public U getAdminUser() {
		return userDao.get(AdminIds.USER_ID);
	}

	/**
	 * 获取系统管理员角色。
	 * 
	 * @return 返回系统管理员角色。
	 */
	public R getAdminRole() {
		return roleDao.get(AdminIds.ROLE_ID);
	}
}