package coo.mvc.boot.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.base.util.BeanUtils;
import coo.core.security.annotations.AutoFillResourceEntity;
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
	@Override
	@Transactional
	@AutoFillResourceEntity
	public void updateOrgan(Organ organ) {
		Organ origOrgan = getOrgan(organ.getId());
		BeanUtils.copyFields(organ, origOrgan, "ordinal", null);
		super.updateOrgan(origOrgan);
	}

	@Override
	@Transactional
	@AutoFillResourceEntity
	public void updateRole(Role role) {
		Role origRole = getRole(role.getId());
		BeanUtils.copyFields(role, origRole);
		super.updateRole(origRole);
	}

	@Override
	@Transactional
	@AutoFillResourceEntity
	public void updateUser(User user) {
		User origUser = getUser(user.getId());
		BeanUtils.copyFields(user, origUser);
		super.updateUser(origUser);
	}

	@Override
	@Transactional
	@AutoFillResourceEntity
	public void updateActor(Actor actor) {
		Actor origActor = getActor(actor.getId());
		BeanUtils.copyFields(actor, origActor);
		super.updateActor(origActor);
	}
}