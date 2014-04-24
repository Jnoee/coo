package coo.mvc.security.blank.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import coo.core.security.entity.BnLogEntity;

/**
 * 业务日志。
 */
@Entity
@Table(name = "Syst_BnLog")
@Indexed
public class BnLog extends BnLogEntity {

}
