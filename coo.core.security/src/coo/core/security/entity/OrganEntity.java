package coo.core.security.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.validator.constraints.NotEmpty;

import coo.core.security.annotations.Log;

/**
 * 机构实体基类。
 * 
 * @param <O>
 *            机构类型
 * @param <U>
 *            用户类型
 * @param <A>
 *            职务类型
 */
@MappedSuperclass
public abstract class OrganEntity<O extends OrganEntity<O, U, A>, U extends UserEntity<U, A, ?>, A extends ActorEntity<O, U, ?>>
		extends ResourceEntity<U> {
	/** 上级机构 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId")
	@Log(text = "上级机构", property = "name")
	private O parent;
	/** 名称 */
	@NotEmpty
	@Field(analyze = Analyze.NO)
	@Log(text = "名称")
	private String name;
	/** 排序 */
	@Field(analyze = Analyze.NO)
	@Log(text = "排序")
	private Integer ordinal;
	/** 下级机构 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("ordinal,name")
	private List<O> childs = new ArrayList<O>();
	/** 关联职务 */
	@OneToMany(mappedBy = "organ", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<A> actors = new ArrayList<A>();

	public O getParent() {
		return parent;
	}

	public void setParent(O parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public List<O> getChilds() {
		return childs;
	}

	public void setChilds(List<O> childs) {
		this.childs = childs;
	}

	public List<A> getActors() {
		return actors;
	}

	public void setActors(List<A> actors) {
		this.actors = actors;
	}

	/**
	 * 获取机构完整名称(带上级机构名称)。
	 * 
	 * @return 返回机构完整名称。
	 */
	public String getFullName() {
		if (parent != null) {
			return parent.getName() + "-" + name;
		} else {
			return name;
		}
	}

	/**
	 * 获取用于下拉列表显示的机构名称。
	 * 
	 * @return 返回机构名称。
	 */
	public String getSelectText() {
		String text = name;
		for (int i = 0; i < getOrganLevel(); i++) {
			text = " > " + text;
		}
		return text;
	}

	/**
	 * 获取机构所在层次，根机构层次为0。
	 * 
	 * @return 返回机构所在层次。
	 */
	public Integer getOrganLevel() {
		if (parent != null) {
			return parent.getOrganLevel() + 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取本机构树下的所有机构，包括本机构。
	 * 
	 * @return 返回本机构树下的所有机构，包括本机构。
	 */
	@SuppressWarnings("unchecked")
	public List<O> getOrganTree() {
		List<O> organTree = new ArrayList<O>();
		organTree.add((O) this);
		for (O child : childs) {
			organTree.addAll(child.getOrganTree());
		}
		return organTree;
	}
}
