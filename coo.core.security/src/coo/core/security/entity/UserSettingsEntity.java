package coo.core.security.entity;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 * 用户设置基类。
 * 
 * @param <A>
 *            职务类型
 */
@MappedSuperclass
public abstract class UserSettingsEntity<A extends ActorEntity<?, ?, ?>> {
	@Id
	private String id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "defaultActorId")
	private A defaultActor;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public A getDefaultActor() {
		return defaultActor;
	}

	public void setDefaultActor(A defaultActor) {
		this.defaultActor = defaultActor;
	}
}
