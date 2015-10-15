package coo.core.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import coo.core.enums.EnabledStatus;
import coo.core.hibernate.search.IEnumValueBridge;
import coo.core.security.annotations.LogField;

/**
 * 用户实体基类。
 * 
 * @param <U>
 *            用户类型
 * @param <A>
 *            职务类型
 * @param <S>
 *            用户设置类型
 */
@MappedSuperclass
public abstract class UserEntity<U extends UserEntity<U, A>, A extends ActorEntity<?, ?, ?>>
		extends ResourceEntity<U> {
	private static final long serialVersionUID = 285209873729434603L;
	/** 姓名 */
	@Field(analyze = Analyze.NO)
	@LogField(text = "姓名")
	private String name;
	/** 用户名 */
	@Field(analyze = Analyze.NO)
	@LogField(text = "用户名")
	private String username;
	/** 密码 */
	private String password;
	/** 启用状态 */
	@Type(type = "IEnum")
	@Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = IEnumValueBridge.class))
	@LogField(text = "启用状态")
	private EnabledStatus enabled = EnabledStatus.ENABLED;
	/** 排序 */
	@Field(analyze = Analyze.NO)
	@LogField(text = "排序")
	private Integer ordinal = 999;
	/** 默认职务 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defaultActorId")
	private A defaultActor;
	/** 用户职务 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@OrderBy("createDate")
	private List<A> actors = new ArrayList<A>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EnabledStatus getEnabled() {
		return enabled;
	}

	public void setEnabled(EnabledStatus disabled) {
		this.enabled = disabled;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public A getDefaultActor() {
		return defaultActor;
	}

	public void setDefaultActor(A defaultActor) {
		this.defaultActor = defaultActor;
	}

	public List<A> getActors() {
		return actors;
	}

	public void setActors(List<A> actors) {
		this.actors = actors;
	}
}
