/*==============================================================*/
/* Table: Syst_Actor                                            */
/*==============================================================*/
create table Syst_Actor
(
   id                   char(36) not null,
   organId              char(36) not null,
   userId               char(36) not null,
   roleId               char(36) not null,
   name                 varchar(60) not null,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Syst_BnLog                                            */
/*==============================================================*/
create table Syst_BnLog
(
   id                   char(36) not null,
   creator              varchar(20) not null,
   createDate           datetime not null,
   message              varchar(800) not null,
   origData             varchar(2000),
   newData              varchar(2000),
   primary key (id)
);

/*==============================================================*/
/* Table: Syst_Organ                                            */
/*==============================================================*/
create table Syst_Organ
(
   id                   char(36) not null,
   parentId             char(36),
   name                 varchar(60) not null,
   ordinal              int,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Syst_Role                                             */
/*==============================================================*/
create table Syst_Role
(
   id                   char(36) not null,
   name                 varchar(60) not null,
   permissions          varchar(800) not null,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Syst_User                                             */
/*==============================================================*/
create table Syst_User
(
   id                   char(36) not null,
   name                 varchar(20) not null,
   username             varchar(20) not null,
   password             varchar(120) not null,
   enabled              smallint not null,
   ordinal              int,
   creatorId            char(36) not null,
   createDate           datetime not null,
   modifierId           char(36) not null,
   modifyDate           datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Syst_UserSettings                                     */
/*==============================================================*/
create table Syst_UserSettings
(
   id                   char(36) not null,
   defaultActorId       char(36) not null,
   primary key (id)
);

alter table Syst_Actor add constraint FK_Actor_organId foreign key (organId)
      references Syst_Organ (id) on delete cascade on update restrict;

alter table Syst_Actor add constraint FK_Actor_roleId foreign key (roleId)
      references Syst_Role (id) on delete cascade on update restrict;

alter table Syst_Actor add constraint FK_Actor_userId foreign key (userId)
      references Syst_User (id) on delete cascade on update restrict;

alter table Syst_Organ add constraint FK_Organ_parentId foreign key (parentId)
      references Syst_Organ (id) on delete cascade on update restrict;


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