/*==============================================================*/
/* Table: Tmp_Company                                           */
/*==============================================================*/
create table Tmp_Company  (
   id                   CHAR(36)                        not null,
   name                 NVARCHAR2(60)                   not null,
   foundDate            DATE                            not null,
   enabled              SMALLINT                        not null,
   extendInfo           NVARCHAR2(800),
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
   name                 NVARCHAR2(20)                   not null,
   age                  INTEGER                         not null,
   sex                  NVARCHAR2(3)                    not null,
   interests            NVARCHAR2(120),
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

alter table Tmp_Employee
   add constraint FK_Employee_companyId foreign key (companyId)
      references Tmp_Company (id)
      on delete cascade;