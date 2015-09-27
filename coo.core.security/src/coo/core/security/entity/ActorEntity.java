package coo.core.security.entity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;

import coo.core.security.annotations.LogBean;
import coo.core.security.annotations.LogField;

/**
 * 职务实体基类。
 * 
 * @param <O>
 *            机构类型
 * @param <U>
 *            用户类型
 * @param <R>
 *            角色类型
 */
@MappedSuperclass
public abstract class ActorEntity<O extends OrganEntity<O, U, ?>, U extends UserEntity<U, ?>, R extends RoleEntity<U, ?>>
		extends ResourceEntity<U> {
	/** 关联机构 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organId")
	@LogBean(@LogField(text = "关联机构", property = "name"))
	private O organ;
	/** 关联用户 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@LogBean(@LogField(text = "关联用户", property = "username"))
	private U user;
	/** 关联角色 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId")
	@LogBean(@LogField(text = "关联角色", property = "name"))
	private R role;
	/** 名称 */
	@Field(analyze = Analyze.NO)
	@LogField(text = "名称")
	private String name;

	/**
	 * 获取职务的完整名称。（机构名-职务名）
	 * 
	 * @return 返回职务的完整名称。
	 */
	public String getFullName() {
		return organ.getName() + "-" + name;
	}

	/**
	 * 是否为关联用户的默认职务。
	 * 
	 * @return 返回是否为关联用户的默认职务。
	 */
	public Boolean isDefaultActor() {
		return user.getDefaultActor().getId().equals(id);
	}

	public O getOrgan() {
		return organ;
	}

	public void setOrgan(O organ) {
		this.organ = organ;
	}

	public U getUser() {
		return user;
	}

	public void setUser(U user) {
		this.user = user;
	}

	public R getRole() {
		return role;
	}

	public void setRole(R role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
