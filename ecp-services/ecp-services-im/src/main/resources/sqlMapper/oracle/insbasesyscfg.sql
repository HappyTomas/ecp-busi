DELETE
  FROM T_BASE_SYS_CFG WHERE para_code='IM_STAFF_MAX_SERV_NUM';

INSERT INTO T_BASE_SYS_CFG
     VALUES ('IM_STAFF_MAX_SERV_NUM',6,'客服人员最大接入买家会员数',SYSDATE,0,SYSDATE,0);

drop table t_im_custserv_satisfy_info;
create table t_im_custserv_satisfy_info
(
   id                   number(12,0) not null        ,
   shop_id               number(12,0) not null        ,
   of_staff_code        varchar2(64) not null        ,
   csa_code             varchar2(64) not null        ,
   session_id           varchar2(64) not null        ,
   satisfy_type         varchar(1)   not null        ,
   not_satisfy_type     varchar(1)                   ,
   not_satisfy_reason   varchar(128)                 ,
   create_date          DATE  DEFAULT SYSDATE not null,
   primary key (id)
);

COMMENT ON TABLE t_im_custserv_satisfy_info IS '客服人员服务评价信息表';
COMMENT ON COLUMN t_im_custserv_satisfy_info.id IS '主健ID';
COMMENT ON COLUMN t_im_custserv_satisfy_info.shop_id is '店铺ID';
COMMENT ON COLUMN t_im_custserv_satisfy_info.of_staff_code is '客服ID';
COMMENT ON COLUMN t_im_custserv_satisfy_info.csa_code is '买家会员ID';
COMMENT ON COLUMN t_im_custserv_satisfy_info.session_id is '发起聊天记录ID';
COMMENT ON COLUMN t_im_custserv_satisfy_info.satisfy_type is '满意度类型';
COMMENT ON COLUMN t_im_custserv_satisfy_info.not_satisfy_type is '不满意类型';
COMMENT ON COLUMN t_im_custserv_satisfy_info.not_satisfy_reason is '不满意原因';
COMMENT ON COLUMN t_im_custserv_satisfy_info.create_date is '评价时间';

delete from t_sequence where name='SEQ_CUSTSERV_SATISFY_ID';
insert into t_sequence(name,value) values('SEQ_CUSTSERV_SATISFY_ID',1);

commit;