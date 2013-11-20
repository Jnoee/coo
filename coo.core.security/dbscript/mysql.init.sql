/** 创建系统根机构 */
insert into Syst_Organ (id, parentId, name, creatorId, createDate, modifierId, modifyDate)
values ('c9b68b8b-24a9-4669-b38d-5544f4643c58', null, '系统根机构', '70900052-bfed-4ef0-be2e-99df235ceddf', now(), '70900052-bfed-4ef0-be2e-99df235ceddf', now());
/** 创建系统管理员用户 */
insert into Syst_User (id, username, password, name, enabled, creatorId, createDate, modifierId, modifyDate)
values ('70900052-bfed-4ef0-be2e-99df235ceddf', 'admin', '1Qj37n/mxycXUoO0AEGAFEOYNPLBwJpY', '系统管理员', 1, '70900052-bfed-4ef0-be2e-99df235ceddf', now(), '70900052-bfed-4ef0-be2e-99df235ceddf', now());
/** 创建系统管理员角色 */
insert into Syst_Role (id, name, permissions, creatorId, createDate, modifierId, modifyDate)
values('24a8e04b-11cb-48a2-97ce-ed921bdd7df1', '系统管理员', '100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '70900052-bfed-4ef0-be2e-99df235ceddf', now(), '70900052-bfed-4ef0-be2e-99df235ceddf', now());
/** 创建系统根机构管理员职务 */
insert into Syst_Actor (id, organId, userId, roleId, name, creatorId, createDate, modifierId, modifyDate)
values ('72a7a166-ee81-4be0-99bb-97907112056f', 'c9b68b8b-24a9-4669-b38d-5544f4643c58', '70900052-bfed-4ef0-be2e-99df235ceddf', '24a8e04b-11cb-48a2-97ce-ed921bdd7df1', '系统根机构管理员', '70900052-bfed-4ef0-be2e-99df235ceddf', now(), '70900052-bfed-4ef0-be2e-99df235ceddf', now());
/** 创建系统管理员用户设置 */
insert into Syst_UserSettings (id, defaultActorId) values ('70900052-bfed-4ef0-be2e-99df235ceddf', '72a7a166-ee81-4be0-99bb-97907112056f');