package coo.mvc.boot.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import coo.core.security.entity.RoleEntity;

/**
 * 角色。
 */
@Entity
@Table(name = "Syst_Role")
public class Role extends RoleEntity<User, Actor> {
}
