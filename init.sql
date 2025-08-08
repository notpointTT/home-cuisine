CREATE TABLE `user` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,  -- 内部唯一ID
  `phone_num` VARCHAR(20) NOT NULL UNIQUE,   -- 带国家码：+8613812345678
  `username` VARCHAR(50) UNIQUE,          -- 可修改的展示名
  `display_name` VARCHAR(50),             -- 非唯一昵称
  `password_hash` VARCHAR(100),           -- bcrypt加密存储
  `mobile_verified` BOOLEAN DEFAULT FALSE,-- 是否完成手机验证
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  KEY `idx_phone_num` (`phone_num`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
   `global_trace_id` varchar(64) NOT NULL COMMENT '全局追踪ID',
  `trace_id` varchar(64) NOT NULL COMMENT '本地追踪ID',
  `log_type` varchar(20) NOT NULL COMMENT '日志类型(OPERATION/ACCESS/ERROR)',

  -- 通用字段
  `module` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `operation` varchar(100) DEFAULT NULL COMMENT '操作类型',
  `method` varchar(200) DEFAULT NULL COMMENT '方法名/HTTP方法',

  -- 请求相关
  `request_uri` varchar(500) DEFAULT NULL COMMENT '请求URI',
  `params` text DEFAULT NULL COMMENT '请求参数/方法参数',

  -- 响应相关
  `status` int(11) DEFAULT NULL COMMENT '状态码/操作状态',
  `result` text DEFAULT NULL COMMENT '响应结果/返回结果',
  `error_msg` text DEFAULT NULL COMMENT '错误信息',

  -- 执行信息
  `execution_time` bigint(20) DEFAULT NULL COMMENT '执行时长(ms)',

  -- 上下文信息
  `operator_id` varchar(50) DEFAULT NULL COMMENT '操作人ID',
  `operator_name` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `client_ip` varchar(50) DEFAULT NULL COMMENT '客户端IP',
  `user_agent` varchar(500) DEFAULT NULL COMMENT '用户代理',

  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

  PRIMARY KEY (`id`),
  INDEX `idx_trace_id` (`trace_id`),
  INDEX `idx_global_trace_id` (`global_trace_id`),
  INDEX `idx_log_type` (`log_type`),
  INDEX `idx_create_time` (`create_time`),
  INDEX `idx_module` (`module`, `operation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';