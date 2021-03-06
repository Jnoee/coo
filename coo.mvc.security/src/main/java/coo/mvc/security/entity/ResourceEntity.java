package coo.mvc.security.entity;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.SortableField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import coo.base.util.StringUtils;
import coo.core.hibernate.search.DateBridge;
import coo.core.model.UuidEntity;
import coo.core.util.SpringUtils;
import coo.mvc.security.service.AbstractSecurityService;

/**
 * 资源实体基类。
 * 
 * @param <U> 用户类型
 */
@MappedSuperclass
@JsonIgnoreProperties({"creator", "createDate", "modifier", "modifyDate"})
public abstract class ResourceEntity<U extends UserEntity<U, ?>> extends UuidEntity {
  private static final long serialVersionUID = -3609060293286581097L;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creatorId")
  protected U creator;
  @Temporal(TemporalType.TIMESTAMP)
  @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
  @SortableField
  protected Date createDate;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modifierId")
  protected U modifier;
  @Temporal(TemporalType.TIMESTAMP)
  @Field(analyze = Analyze.NO, bridge = @FieldBridge(impl = DateBridge.class))
  @SortableField
  protected Date modifyDate;

  /**
   * 自动填充创建人、创建时间、修改人、修改时间。
   */
  public void autoFillIn() {
    AbstractSecurityService<?, U, ?, ?> securityService = SpringUtils.getBean("securityService");
    U operator = securityService.getDefaultOperator();
    Date now = new Date();
    if (StringUtils.isEmpty(getId())) {
      setCreator(operator);
      setCreateDate(now);
      setModifier(operator);
      setModifyDate(now);
    } else {
      setModifier(operator);
      setModifyDate(now);
    }
  }

  public U getCreator() {
    return creator;
  }

  public void setCreator(U creator) {
    this.creator = creator;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public U getModifier() {
    return modifier;
  }

  public void setModifier(U modifier) {
    this.modifier = modifier;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
}
