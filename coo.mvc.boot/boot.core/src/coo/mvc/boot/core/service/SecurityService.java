package coo.mvc.boot.core.service;

import org.springframework.stereotype.Service;

import coo.core.security.service.AbstractSecurityService;
import coo.mvc.boot.core.entity.Actor;
import coo.mvc.boot.core.entity.Organ;
import coo.mvc.boot.core.entity.Role;
import coo.mvc.boot.core.entity.User;
import coo.mvc.boot.core.entity.UserSettings;

/**
 * 安全服务。
 */
@Service
public class SecurityService extends
		AbstractSecurityService<Organ, User, Role, Actor, UserSettings> {
}