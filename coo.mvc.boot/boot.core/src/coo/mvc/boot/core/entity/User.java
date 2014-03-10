package coo.mvc.boot.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import coo.core.security.entity.UserEntity;

/**
 * 用户。
 */
@Entity
@Table(name = "Syst_User")
@Indexed
public class User extends UserEntity<User, Actor, UserSettings> {
}
