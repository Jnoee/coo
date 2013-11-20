package coo.struts.security.blank.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import coo.core.security.entity.ActorEntity;

/**
 * 职务。
 */
@Entity
@Table(name = "Syst_Actor")
public class Actor extends ActorEntity<Organ, User, Role> {

}
