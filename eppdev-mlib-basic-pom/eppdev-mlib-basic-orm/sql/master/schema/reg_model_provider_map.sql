create table reg_model_provider_map(
  id char(32) comment 'UUID，唯一标识',
  model_id varchar(64) comment '',
  provider_instance_name varchar(64) comment 'provider的实例名称',
  create_date datetime comment '数据创建时间（技术字段）',
  update_date datetime comment '数据最后修改时间（技术字段）',
  create_by char(32) comment '数据创建人ID（技术字段）',
  update_by char(32) comment '数据最后修改人ID（技术字段）',
  remarks varchar(1024) comment '备注（技术字段）',
  del_flag integer comment '删除标识（技术字段）',
  primary key(id)
);

create index idx__reg_model_provider_map__provider_instance_name on reg_model_provider_map(provider_instance_name);
create index idx__reg_model_provider_map__model_id on reg_model_provider_map(model_id);

