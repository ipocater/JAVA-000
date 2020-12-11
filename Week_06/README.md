电商交易场景表结构：
create table if not EXISTS tb_jk_user(
	user_id int UNSIGNED auto_increment COMMENT '用户id',
  user_name VARCHAR(200) not null COMMENT '用户名称',
  password VARCHAR(200) not null comment '用户密码',
  phone  VARCHAR(20) COMMENT '用户手机号',
  create_date DATE COMMENT '创建日期',
  update_date DATE COMMENT '修改日期',
   PRIMARY KEY (user_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table if not EXISTS tb_jk_goods(
	goods_id int UNSIGNED auto_increment COMMENT '商品id',
  goods_name VARCHAR(100) not null COMMENT '商品名称',
  goods_des VARCHAR(200) COMMENT '商品描述',
  goods_price numeric(10,2) COMMENT '商品价格',
  create_date DATE COMMENT '商品创建时间',
  update_date DATE COMMENT '商品更改时间',
  PRIMARY KEY (goods_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table if not EXISTS tb_jk_order(
  order_id int UNSIGNED auto_increment COMMENT '订单id',
  goods_id int UNSIGNED not null COMMENT '订单商品id',
  user_id int UNSIGNED not null COMMENT '订单用户id',
  order_price numeric(10,2) COMMENT '订单金额',
  create_time DATE COMMENT '订单创建时间',
  update_time DATE COMMENT '订单更改时间',
  PRIMARY KEY (order_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
