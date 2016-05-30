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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.IndexedEmbedded;

import coo.core.enums.EnabledStatus;
import coo.core.hibernate.search.IEnumValueBridge;
import coo.core.security.annotations.LogBean;
import coo.core.security.annotations.LogField;
import coo.core.security.constants.AdminIds;

/**
 * 机构实体基类。
 * 
 * @param <O> 机构类型
 * @param <U> 用户类型
 * @param <A> 职务类型
 */
@MappedSuperclass
public abstract class OrganEntity<O extends OrganEntity<O, U, A>, U extends UserEntity<U, A>, A extends ActorEntity<O, U, ?>>
    extends ResourceEntity<U> {
  private static final long serialVersionUID = -6322476250996786432L;
  /** 上级机构 */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parentId")
  @IndexedEmbedded(includeEmbeddedObjectId = true, includePaths = "name")
  @LogBean(@LogField(text = "上级机构", property = "name"))
  private O parent;
  /** 名称 */
  @Field(analyze = Analyze.NO)
  @LogField(text = "名称")
  private String name;
  /** 启用状态 */
  @Type(type = "IEnum")
  @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = IEnumValueBridge.class))
  @LogField(text = "启用状态")
  private EnabledStatus enabled = EnabledStatus.ENABLED;
  /** 排序 */
  @Field(analyze = Analyze.NO)
  @LogField(text = "排序")
  private Integer ordinal = 999;
  /** 下级机构 */
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
      orphanRemoval = true)
  @OrderBy("ordinal,name")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  private List<O> childs = new ArrayList<O>();
  /** 关联职务 */
  @OneToMany(mappedBy = "organ", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
      orphanRemoval = true)
  private List<A> actors = new ArrayList<A>();

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
   * 判断是否根机构。
   * 
   * @return 返回是否根机构。
   */
  public Boolean isRoot() {
    return AdminIds.ORGAN_ID.equals(getId());
  }

  /**
   * 获取机构所在层次，根机构层次为0。
   * 
   * @return 返回机构所在层次。
   */
  public Integer getOrganLevel() {
    if (isRoot()) {
      return 0;
    } else {
      return parent.getOrganLevel() + 1;
    }
  }

  /**
   * 获取本机构树状路线上的所有机构，包括本机构。
   * 
   * @return 返回本机构树状路线上的所有机构，包括本机构。
   */
  public List<O> getFullTree() {
    List<O> organTree = getParents();
    organTree.addAll(getChildTree());
    return organTree;
  }

  /**
   * 获取本机构树下的所有机构，包括本机构。
   * 
   * @return 返回本机构树下的所有机构，包括本机构。
   */
  @SuppressWarnings("unchecked")
  public List<O> getChildTree() {
    List<O> organTree = new ArrayList<O>();
    organTree.add((O) this);
    for (O child : getChilds()) {
      if (child.getEnabled() == EnabledStatus.ENABLED) {
        organTree.addAll(child.getChildTree());
      }
    }
    return organTree;
  }

  /**
   * 获取本机构的所有上级机构，包括本机构。
   * 
   * @return 返回本机构的所有上级机构，包括本机构。
   */
  @SuppressWarnings("unchecked")
  public List<O> getParentTree() {
    List<O> organTree = new ArrayList<O>();
    organTree.add((O) this);
    if (getParent() != null) {
      organTree.addAll(getParents());
    }
    return organTree;
  }

  /**
   * 获取本机构的所有上级机构。
   * 
   * @return 返回本机构的所有上级机构。
   */
  public List<O> getParents() {
    List<O> parents = new ArrayList<>();
    if (getParent() != null) {
      parents.addAll(getParent().getParents());
      parents.add(getParent());
    }
    return parents;
  }

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

  public EnabledStatus getEnabled() {
    return enabled;
  }

  public void setEnabled(EnabledStatus enabled) {
    this.enabled = enabled;
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
}
