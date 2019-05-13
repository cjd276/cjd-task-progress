-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.24 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 rescue 的数据库结构
CREATE DATABASE IF NOT EXISTS `rescue` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rescue`;

-- 导出  表 rescue.module 结构
CREATE TABLE IF NOT EXISTS `module` (
  `id` varchar(50) DEFAULT NULL,
  `project_id` varchar(50) DEFAULT NULL COMMENT '工程id',
  `name` varchar(50) DEFAULT NULL COMMENT '模块名称',
  `code` varchar(50) DEFAULT NULL COMMENT 'war包名称',
  `operate` text COMMENT '操作步骤模板',
  `operate_return` text COMMENT '回滚模板',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 rescue.plan 结构
CREATE TABLE IF NOT EXISTS `plan` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '计划名称',
  `expect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '计划上线时间',
  `plan_status` varchar(50) NOT NULL DEFAULT '2' COMMENT '1 准备中 2进行中   11 已完成',
  `is_delete` varchar(50) NOT NULL COMMENT '0 未删除  1 已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `team_id` varchar(50) DEFAULT NULL COMMENT '战队id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上线计划';

-- 数据导出被取消选择。
-- 导出  表 rescue.pro_project 结构
CREATE TABLE IF NOT EXISTS `pro_project` (
  `id` varchar(50) NOT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `current_version` varchar(50) NOT NULL COMMENT '当前版本号',
  `current_version_prefix` varchar(50) NOT NULL COMMENT '版本号前缀',
  `name` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `team_id` varchar(50) DEFAULT NULL COMMENT '战队id',
  `team_name` varchar(50) DEFAULT NULL COMMENT '战队名称',
  `is_delete` varchar(50) DEFAULT '0' COMMENT '0 未删除 1 已删除',
  `code` varchar(50) DEFAULT NULL COMMENT '项目编码',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 rescue.task 结构
CREATE TABLE IF NOT EXISTS `task` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '任务名称',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `plan_id` varchar(50) NOT NULL COMMENT '计划id',
  `task_process_id` varchar(50) NOT NULL COMMENT '任务进度   0 开发前准备  1 开发中 2 自测中 3 sit 4 uat 5 pre 6 sandbox 7 已完成',
  `task_process_name` varchar(50) NOT NULL,
  `branch` varchar(50) NOT NULL COMMENT '分支',
  `personal_branch` varchar(50) DEFAULT NULL COMMENT '个人分支',
  `team_id` varchar(50) DEFAULT NULL COMMENT '战队id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- 数据导出被取消选择。
-- 导出  表 rescue.task_module 结构
CREATE TABLE IF NOT EXISTS `task_module` (
  `id` varchar(50) NOT NULL,
  `module_id` varchar(50) NOT NULL,
  `plan_id` varchar(50) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块 任务关系表';

-- 数据导出被取消选择。
-- 导出  表 rescue.task_process 结构
CREATE TABLE IF NOT EXISTS `task_process` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '阶段目标',
  `team_id` varchar(50) NOT NULL,
  `weight` int(11) NOT NULL COMMENT '进度权重（百分制）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `process_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务进度';

-- 数据导出被取消选择。
-- 导出  表 rescue.team 结构
CREATE TABLE IF NOT EXISTS `team` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '组名称',
  `code` varchar(50) DEFAULT NULL COMMENT '组编号',
  `is_delete` varchar(50) DEFAULT NULL COMMENT '0 未删除   1 已删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ding_url` varchar(200) NOT NULL COMMENT '钉钉url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据导出被取消选择。
-- 导出  表 rescue.user_task 结构
CREATE TABLE IF NOT EXISTS `user_task` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户 任务关系表';

-- 数据导出被取消选择。
-- 导出  表 rescue.user_team 结构
CREATE TABLE IF NOT EXISTS `user_team` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `team_id` varchar(50) NOT NULL COMMENT '组id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户战队关系表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
