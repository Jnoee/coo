package coo.mvc.security.blank.service;

import org.springframework.stereotype.Service;

import coo.core.security.service.AbstractSecurityService;
import coo.mvc.security.blank.entity.Actor;
import coo.mvc.security.blank.entity.Organ;
import coo.mvc.security.blank.entity.Role;
import coo.mvc.security.blank.entity.User;
import coo.mvc.security.blank.entity.UserSettings;

/**
 * 安全服务。
 */
@Service
public class SecurityService extends
		AbstractSecurityService<Organ, User, Role, Actor, UserSettings> {
}