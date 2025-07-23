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