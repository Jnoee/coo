/*==============================================================*/
/* Table: Syst_Actor                                            */
/*==============================================================*/
create table Syst_Actor  (
   id                   CHAR(36)                        not null,
   organId              CHAR(36)                        not null,
   userId               CHAR(36)                        not null,
   roleId               CHAR(36)                        not null,
   name                 VARCHAR(60)                     not null,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_ACTOR primary key (id)
);

/*==============================================================*/
/* Index: IDX_Actor_organId                                     */
/*==============================================================*/
create index IDX_Actor_organId on Syst_Actor (
   organId ASC
);

/*==============================================================*/
/* Index: IDX_Actor_userId                                      */
/*==============================================================*/
create index IDX_Actor_userId on Syst_Actor (
   userId ASC
);

/*==============================================================*/
/* Index: IDX_Actor_roleId                                      */
/*==============================================================*/
create index IDX_Actor_roleId on Syst_Actor (
   roleId ASC
);

/*==============================================================*/
/* Table: Syst_BnLog                                            */
/*==============================================================*/
create table Syst_BnLog  (
   id                   CHAR(36)                        not null,
   creator              VARCHAR(20)                     not null,
   createDate           DATETIME                        not null,
   entityId				CHAR(36),
   message              VARCHAR(800)                    not null,
   origData             VARCHAR(2000),
   newData              VARCHAR(2000),
   constraint PK_SYST_BNLOG primary key (id)
);

/*==============================================================*/
/* Table: Syst_Organ                                            */
/*==============================================================*/
create table Syst_Organ  (
   id                   CHAR(36)                        not null,
   parentId             CHAR(36),
   name                 VARCHAR(60)                     not null,
   ordinal              INTEGER,
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_SYST_ORGAN primary key (id)
);

/*==============================================================*/
/* Index: IDX_Organ_parentId                                    */
/*==============================================================*/
create index IDX_Organ_parentId on Syst_Organ (
   parentId ASC
);

/*==============================================================*/
/* Table: Syst_Role                                             */
/*==============================================================*/
create table Syst_Role  (
   id                   CHAR(36)                        not null,
   name                 VARCHAR(60)                     not null,
   permissions          VARCHAR(800)                    not null,
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
   name                 VARCHAR(20)                     not null,
   username             VARCHAR(20)                     not null,
   password             VARCHAR(120)                    not null,
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

/*==============================================================*/
/* Table: Tmp_Company                                           */
/*==============================================================*/
create table Tmp_Company  (
   id                   CHAR(36)                        not null,
   name                 VARCHAR(60)                     not null,
   foundDate            DATETIME                        not null,
   enabled              SMALLINT                        not null,
   extendInfo           VARCHAR(800),
   constraint PK_TMP_COMPANY primary key (id)
);

comment on table Tmp_Company is
'公司';

comment on column Tmp_Company.foundDate is
'成立时间';

comment on column Tmp_Company.extendInfo is
'扩展信息';

/*==============================================================*/
/* Table: Tmp_Employee                                          */
/*==============================================================*/
create table Tmp_Employee  (
   id                   CHAR(36)                        not null,
   companyId            CHAR(36)                        not null,
   name                 VARCHAR(20)                     not null,
   age                  INTEGER                         not null,
   sex                  VARCHAR(3)                      not null,
   interests            VARCHAR(120),
   creatorId            CHAR(36)                        not null,
   createDate           DATETIME                        not null,
   modifierId           CHAR(36)                        not null,
   modifyDate           DATETIME                        not null,
   constraint PK_TMP_EMPLOYEE primary key (id)
);

comment on table Tmp_Employee is
'职员';

comment on column Tmp_Employee.companyId is
'关联公司ID';

comment on column Tmp_Employee.age is
'年龄';

comment on column Tmp_Employee.sex is
'性别';

comment on column Tmp_Employee.interests is
'兴趣爱好';

/*==============================================================*/
/* Index: IDX_Employee_companyId                                */
/*==============================================================*/
create index IDX_Employee_companyId on Tmp_Employee (
   companyId ASC
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
      
alter table Tmp_Employee
   add constraint FK_Employee_companyId foreign key (companyId)
      references Tmp_Company (id)
      on delete cascade;
      
/** 创建系统根机构 */
insert into Syst_Organ (id, parentId, name, creatorId, createDate, modifierId, modifyDate)
values ('ADMINOID-0000-0000-0000-000000000000', null, '系统根机构', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());
/** 创建系统管理员用户 */
insert into Syst_User (id, username, password, name, enabled, creatorId, createDate, modifierId, modifyDate)
values ('ADMINUID-0000-0000-0000-000000000000', 'admin', 'j6Xdj208bRekjd8a9acwQJL5IbHiws3ncNkgO6Gh9HY=', '系统管理员', 1, 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());
/** 创建系统管理员角色 */
insert into Syst_Role (id, name, permissions, creatorId, createDate, modifierId, modifyDate)
values('ADMINRID-0000-0000-0000-000000000000', '系统管理员', '111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());
/** 创建系统根机构管理员职务 */
insert into Syst_Actor (id, organId, userId, roleId, name, creatorId, createDate, modifierId, modifyDate)
values ('ADMINAID-0000-0000-0000-000000000000', 'ADMINOID-0000-0000-0000-000000000000', 'ADMINUID-0000-0000-0000-000000000000', 'ADMINRID-0000-0000-0000-000000000000', '系统根机构管理员', 'ADMINUID-0000-0000-0000-000000000000', NOW(), 'ADMINUID-0000-0000-0000-000000000000', NOW());
/** 创建系统管理员用户设置 */
insert into Syst_UserSettings (id, defaultActorId) values ('ADMINUID-0000-0000-0000-000000000000', 'ADMINAID-0000-0000-0000-000000000000');