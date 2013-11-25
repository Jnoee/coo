/*==============================================================*/
/* Table: Syst_Actor                                            */
/*==============================================================*/
create table Syst_Actor  (
   id                   CHAR(36)                        not null,
   organId              CHAR(36)                        not null,
   userId               CHAR(36)                        not null,
   roleId               CHAR(36)                        not null,
   name                 NVARCHAR2(60)                   not null,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_ACTOR primary key (id)
);

/*==============================================================*/
/* Table: Syst_BnLog                                            */
/*==============================================================*/
create table Syst_BnLog  (
   id                   CHAR(36)                        not null,
   creator              NVARCHAR2(20)                   not null,
   createDate           DATETIME                        not null,
   message              NVARCHAR2(800)                  not null,
   origData             NVARCHAR2(2000),
   newData              NVARCHAR2(2000),
   constraint PK_SYST_BNLOG primary key (id)
);

/*==============================================================*/
/* Table: Syst_Organ                                            */
/*==============================================================*/
create table Syst_Organ  (
   id                   CHAR(36)                        not null,
   parentId             CHAR(36),
   name                 NVARCHAR2(60)                   not null,
   ordinal              INTEGER,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_ORGAN primary key (id)
);

/*==============================================================*/
/* Table: Syst_Role                                             */
/*==============================================================*/
create table Syst_Role  (
   id                   CHAR(36)                        not null,
   name                 NVARCHAR2(60)                   not null,
   permissions          NVARCHAR2(800)                  not null,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_ROLE primary key (id)
);

/*==============================================================*/
/* Table: Syst_User                                             */
/*==============================================================*/
create table Syst_User  (
   id                   CHAR(36)                        not null,
   name                 NVARCHAR2(20)                   not null,
   username             NVARCHAR2(20)                   not null,
   password             NVARCHAR2(120)                  not null,
   enabled              SMALLINT                        not null,
   ordinal              INTEGER,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_USER primary key (id)
);

/*==============================================================*/
/* Table: Syst_UserSettings                                     */
/*==============================================================*/
create table Syst_UserSettings  (
   id                   CHAR(36)                        not null,
   defaultActorId       CHAR(36)                        not null,
   constraint PK_SYST_USERSETTINGS primary key (id)
);

alter table Syst_Actor
   add constraint FK_Actor_organId foreign key (organId)
      references Syst_Organ (id)
      on delete cascade;

alter table Syst_Actor
   add constraint FK_Actor_roleId foreign key (roleId)
      references Syst_Role (id)
      on delete cascade;

alter table Syst_Actor
   add constraint FK_Actor_userId foreign key (userId)
      references Syst_User (id)
      on delete cascade;

alter table Syst_Organ
   add constraint FK_Organ_parentId foreign key (parentId)
      references Syst_Organ (id)
      on delete cascade;
      
      
/** 创建系统根机构 */
insert into Syst_Organ (id, parentId, name, creatorId, createDate, modifierId, modifyDate)
values ('c9b68b8b-24a9-4669-b38d-5544f4643c58', null, '系统根机构', '70900052-bfed-4ef0-be2e-99df235ceddf', NOW(), '70900052-bfed-4ef0-be2e-99df235ceddf', NOW());
/** 创建系统管理员用户 */
insert into Syst_User (id, username, password, name, enabled, creatorId, createDate, modifierId, modifyDate)
values ('70900052-bfed-4ef0-be2e-99df235ceddf', 'admin', 'j6Xdj208bRekjd8a9acwQJL5IbHiws3ncNkgO6Gh9HY=', '系统管理员', 1, '70900052-bfed-4ef0-be2e-99df235ceddf', NOW(), '70900052-bfed-4ef0-be2e-99df235ceddf', NOW());
/** 创建系统管理员角色 */
insert into Syst_Role (id, name, permissions, creatorId, createDate, modifierId, modifyDate)
values('24a8e04b-11cb-48a2-97ce-ed921bdd7df1', '系统管理员', '100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000', '70900052-bfed-4ef0-be2e-99df235ceddf', NOW(), '70900052-bfed-4ef0-be2e-99df235ceddf', NOW());
/** 创建系统根机构管理员职务 */
insert into Syst_Actor (id, organId, userId, roleId, name, creatorId, createDate, modifierId, modifyDate)
values ('72a7a166-ee81-4be0-99bb-97907112056f', 'c9b68b8b-24a9-4669-b38d-5544f4643c58', '70900052-bfed-4ef0-be2e-99df235ceddf', '24a8e04b-11cb-48a2-97ce-ed921bdd7df1', '系统根机构管理员', '70900052-bfed-4ef0-be2e-99df235ceddf', NOW(), '70900052-bfed-4ef0-be2e-99df235ceddf', NOW());
/** 创建系统管理员用户设置 */
insert into Syst_UserSettings (id, defaultActorId) values ('70900052-bfed-4ef0-be2e-99df235ceddf', '72a7a166-ee81-4be0-99bb-97907112056f');