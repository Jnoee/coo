package coo.mvc.security.blank.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import coo.core.security.entity.OrganEntity;

/**
 * 机构。
 */
@Entity
@Table(name = "Syst_Organ")
public class Organ extends OrganEntity<Organ, User, Actor> {

}
