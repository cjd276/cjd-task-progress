/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost
 Source Database       : rescue

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : utf-8

 Date: 06/07/2019 11:29:08 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `apollo`
-- ----------------------------
DROP TABLE IF EXISTS `apollo`;
CREATE TABLE `apollo` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `key_str` varchar(50) NOT NULL COMMENT '操作的key',
  `value_str` text NOT NULL COMMENT '操作的值',
  `operate_type` varchar(50) NOT NULL COMMENT '操作类型',
  `module_id` varchar(50) NOT NULL,
  `plan_id` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records of `apollo`
-- ----------------------------
BEGIN;
INSERT INTO `apollo` VALUES ('5a128c72-0f3e-47b2-b0aa-0a9c49e78a72', '2019-06-07 10:19:06', '2019-06-07 10:19:06', '测试key1', '测试value1', '添加', '90a75b2e-b804-42d5-a14d-75ea51f9255d', 'e1137431-4b82-4977-8425-17bfff8cf2a4'), ('ae35e914-9095-43ec-8fd5-a35b13728db1', '2019-06-07 11:14:15', '2019-06-07 11:14:15', '测试key2', '测试value2', '修改', '90a75b2e-b804-42d5-a14d-75ea51f9255d', 'e1137431-4b82-4977-8425-17bfff8cf2a4');
COMMIT;

-- ----------------------------
--  Table structure for `kv_type`
-- ----------------------------
DROP TABLE IF EXISTS `kv_type`;
CREATE TABLE `kv_type` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `kv_type_name` varchar(8) NOT NULL COMMENT '动态属性类型名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `kv_sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `kv_type_name_uq` (`kv_type_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
--  Records of `kv_type`
-- ----------------------------
BEGIN;
INSERT INTO `kv_type` VALUES ('613a441e-c195-4a12-bd14-4d1c15df3a49', '阻碍项', '2019-06-04 10:52:29', '2019-06-04 10:52:29', '3'), ('a152d0fc-e84e-4d0d-8fe6-6e34533fc7d8', '部署信息', '2019-06-04 10:52:29', '2019-06-04 10:52:29', '2'), ('sys_1', '散列属性', '2019-06-01 18:21:59', '2019-06-01 19:07:14', '1');
COMMIT;

-- ----------------------------
--  Table structure for `module`
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `id` varchar(50) NOT NULL,
  `project_id` varchar(50) NOT NULL COMMENT '工程id',
  `name` varchar(50) NOT NULL COMMENT '模块名称',
  `code` varchar(50) NOT NULL COMMENT 'war包名称',
  `operate` text COMMENT '操作步骤模板',
  `operate_return` text COMMENT '回滚模板',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `service_monitor` text COMMENT '服务监控项',
  `is_delete` varchar(1) DEFAULT '0',
  `namespace` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `module`
-- ----------------------------
BEGIN;
INSERT INTO `module` VALUES ('90a75b2e-b804-42d5-a14d-75ea51f9255d', 'a9ad5ab4-1d25-46a8-9177-6e70169244a9', '合同模块', 'CONTRACT_ROOT', '富鑫宝对外（合同模块）(水土：10.1.11.130/131 新牌坊：10.6.34.37/38 )\na.登陆10.1.11.130服务\nb.确认上传的war包（/home/tomcat/apache-tomcat-8.0.47/webapps/ROOT.war）\nc.进入 cd /home/tomcat/apache-tomcat-8.0.47/bin，执行 sh startup.sh\nd.进入 cd /home/tomcat/apache-tomcat-8.0.47/logs ，执行tail –f catalina.out，如果没有报error代表启动成功', '富鑫宝对外（合同模块）(水土：10.1.11.130/131 新牌坊：10.6.34.37/38 )\na.登陆10.1.11.130服务\nb.回滚war包（/home/tomcat/apache-tomcat-8.0.47/webapps/ROOT.war）\nc.进入 cd /home/tomcat/apache-tomcat-8.0.47/bin，执行 sh startup.sh\nd.进入 cd /home/tomcat/apache-tomcat-8.0.47/logs ，执行tail –f catalina.out，如果没有报error代表启动成功\ne.如果启动成功，则按照上述顺序在10.1.11.131重复执行\nf.水土机房按照上述步骤登陆相应服务器重复以上步骤', '2019-06-05 12:40:49', '2019-06-05 12:45:03', '（水土）10.1.11.130/131 （新牌坊）10.6.34.37/38            监控 8080端口', '0', 'dis-contract'), ('be558f81-1239-4c67-a6b9-ff77a3f1064c', 'a9ad5ab4-1d25-46a8-9177-6e70169244a9', '富鑫宝编排服务', 'FXB_ROOT', '富鑫宝对外（编排模块）(水土：10.1.11.140/141 新牌坊：10.6.34.47/48 )\na.登陆10.1.11.140服务\nb.确认上传的war包（/home/tomcat/apache-tomcat-8.0.47/webapps/ROOT.war）\nc.进入 cd /home/tomcat/apache-tomcat-8.0.47/bin，执行 sh startup.sh\nd.进入 cd /home/tomcat/apache-tomcat-8.0.47/logs ，执行tail –f catalina.out，如果没有报error代表启动成功', '富鑫宝对外（编排模块）(水土：10.1.11.140/141 新牌坊：10.6.34.47/48 )\na.登陆10.1.11.140服务\nb.回滚war包（/home/tomcat/apache-tomcat-8.0.47/webapps/ROOT.war）\nc.进入 cd /home/tomcat/apache-tomcat-8.0.47/bin，执行 sh startup.sh\nd.进入 cd /home/tomcat/apache-tomcat-8.0.47/logs ，执行tail –f catalina.out，如果没有报error代表启动成功\ne.如果启动成功，则按照上述顺序在10.1.11.141重复执行\nf.水土机房按照上述步骤登陆相应服务器重复以上步骤', '2019-06-05 10:21:52', '2019-06-05 12:45:03', ' (水土）10.1.11.140/141 （新牌坊）10.6.34.47/48         监控 8080端口', '0', 'dis-fxb');
COMMIT;

-- ----------------------------
--  Table structure for `plan`
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '计划名称',
  `expect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '计划上线时间',
  `plan_status` varchar(50) NOT NULL DEFAULT '2' COMMENT '1 准备中 2进行中   11 已完成',
  `is_delete` varchar(50) NOT NULL COMMENT '0 未删除  1 已删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `team_id` varchar(50) DEFAULT NULL COMMENT '战队id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上线计划';

-- ----------------------------
--  Records of `plan`
-- ----------------------------
BEGIN;
INSERT INTO `plan` VALUES ('e1137431-4b82-4977-8425-17bfff8cf2a4', '上线计划示例', '2019-06-04 16:00:00', '1', '0', '2019-06-03 17:42:58', '2019-06-03 17:42:58', '293cb014-1ac8-46a7-a518-a702cac102e9');
COMMIT;

-- ----------------------------
--  Table structure for `pro_project`
-- ----------------------------
DROP TABLE IF EXISTS `pro_project`;
CREATE TABLE `pro_project` (
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

-- ----------------------------
--  Records of `pro_project`
-- ----------------------------
BEGIN;
INSERT INTO `pro_project` VALUES ('a9ad5ab4-1d25-46a8-9177-6e70169244a9', '2019-06-05 11:31:18', 'V5.0', 'Dis_', '理财cloud', '293cb014-1ac8-46a7-a518-a702cac102e9', '理财战队', '0', 'dis_cloud', '2019-06-05 10:21:52');
COMMIT;

-- ----------------------------
--  Table structure for `sys_key_value`
-- ----------------------------
DROP TABLE IF EXISTS `sys_key_value`;
CREATE TABLE `sys_key_value` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `bond` varchar(50) NOT NULL COMMENT '属性键',
  `associate` varchar(50) NOT NULL COMMENT '关联id',
  `val` varchar(2000) NOT NULL COMMENT '属性值',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `kv_type_name` varchar(8) NOT NULL COMMENT '动态属性类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
--  Records of `sys_key_value`
-- ----------------------------
BEGIN;
INSERT INTO `sys_key_value` VALUES ('34da416e-ad8d-4716-a8f1-571afba417a8', '口号', 'team293cb014-1ac8-46a7-a518-a702cac102e9', '齐心协力，露露第一', '2019-06-03 16:56:47', '2019-06-03 16:56:47', '散列属性');
COMMIT;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(50) NOT NULL COMMENT '自定id,主要供前端展示权限列表分类排序使用.',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `path` varchar(50) DEFAULT NULL COMMENT '菜单路由路径',
  `menue_type` varchar(2) NOT NULL COMMENT '菜单1 按钮2',
  `parent_id` varchar(255) DEFAULT '' COMMENT '父菜单menue code',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单的中文释义',
  `permission_code` varchar(255) NOT NULL COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(255) NOT NULL COMMENT '本权限的中文释义',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台权限表';

-- ----------------------------
--  Records of `sys_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1339b0a1-f2ea-4886-9a82-7590d2c228eb', 'el-icon-lx-time', '4', 'planList', '1', 'sys_1', '上线计划', 'plan:manage', '上线计划', '2019-04-28 14:23:14', '2019-04-28 14:59:19'), ('24ff0bfc-327f-48fb-95b4-ab6cf0223273', 'el-icon-lx-news', '2', 'scheduleSetting', '1', 'sys_1', '任务进度设置', 'task:scheduleSetting', '任务进度设置', '2019-04-28 17:08:06', '2019-04-28 17:08:06'), ('27385923-e4cf-401d-b123-42e68c30e405', 'el-icon-lx-emoji', '-1', 'icon', '1', 'sys_1', '自定义图标', 'icon:me', '自定义图标', '2019-04-28 14:17:52', '2019-04-28 14:58:14'), ('61641a8f-4952-4e37-a486-584be252c632', 'el-icon-lx-home', '-2', 'dashboard', '1', 'sys_1', '系统首页', 'sys:index', '系统首页', '2019-04-28 14:13:41', '2019-04-28 14:58:33'), ('924cf211-3fb7-48f4-af06-acf8f38d30ff', 'el-icon-lx-record', '5', 'myTasks', '1', 'sys_1', '我的任务', 'task:mytask', '我的任务', '2019-04-28 14:23:46', '2019-04-28 14:59:21'), ('9b1ec131-3cd5-4cac-a82f-54630f993b4e', 'el-icon-lx-friend', '2', 'teamManage', '1', 'sys_1', '战队管理', 'team:manage', '战队管理', '2019-04-28 14:18:38', '2019-04-28 14:58:56'), ('ae44f3ba-de87-4957-91fc-a36725711da2', 'el-icon-lx-file', '3', 'projectList', '1', 'sys_1', '项目管理', 'project:manage', '项目管理', '2019-04-28 14:22:41', '2019-04-28 14:59:11'), ('b0628ffa-562b-4ced-85af-3ae6ea08eb1d', 'el-icon-lx-tag', '2', 'kvTypeSettings', '1', 'sys_1', '动态属性类型设置', 'kvTypeSettings', '动态属性类型设置', '2019-06-01 21:28:11', '2019-06-01 21:28:11'), ('c511220d-c9cd-452b-82ef-84e2bc9f78e2', 'el-icon-lx-friendadd', '1', 'teamUserManage', '1', '9b1ec131-3cd5-4cac-a82f-54630f993b4e', '成员管理', 'team:membermanage', '成员管理', '2019-04-28 14:22:08', '2019-04-28 14:58:58'), ('eb9671b3-cdac-4302-b27e-51cb3df0472b', 'el-icon-lx-group', '2', 'teamList', '1', '9b1ec131-3cd5-4cac-a82f-54630f993b4e', '战队管理', 'teamLeaf:manage', '战队管理', '2019-04-28 14:21:15', '2019-04-28 14:59:00'), ('sys_1', '', null, null, '', '1', '虚拟根菜单', '', '', '2019-04-25 11:17:53', '2019-04-28 14:32:26'), ('sys_2', 'el-icon-lx-settings', '1', 'shiroManage', '1', 'sys_1', '权限管理', 'shiro:manage', '权限管理', '2019-04-25 11:17:53', '2019-04-28 14:57:51'), ('sys_3', 'el-icon-lx-people', '1', 'userManage', '1', 'sys_2', '用户管理', 'user:manage', '用户管理', '2019-04-25 11:17:53', '2019-04-28 14:57:53'), ('sys_4', 'el-icon-lx-female', '2', 'roleList', '1', 'sys_2', '角色管理', 'role:manage', '角色管理', '2019-04-25 11:17:53', '2019-04-28 14:57:54'), ('sys_5', 'el-icon-lx-read', '3', 'menuManage', '1', 'sys_2', '菜单管理', 'menu:manage', '菜单管理', '2019-04-28 14:02:25', '2019-04-28 14:57:56');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `code` varchar(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` varchar(1) DEFAULT '1' COMMENT '是否有效  1有效  0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='后台角色表';

-- ----------------------------
--  Records of `sys_role`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('f7eeb036-e070-4afa-9e3e-fabdc90aa758', '战队管理员', 'dis_manager', '2019-06-03 16:59:13', '2019-06-03 16:59:13', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL COMMENT '角色id',
  `menu_id` varchar(50) NOT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

-- ----------------------------
--  Records of `sys_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('15654add-ca03-4bee-96e6-33ae9dbdef78', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'ae44f3ba-de87-4957-91fc-a36725711da2', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('4eef2bb7-3d73-4088-b3ec-a2320c613de3', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'c511220d-c9cd-452b-82ef-84e2bc9f78e2', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('4fb737f8-815d-464e-bc1f-dd4b054a6a0d', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'sys_3', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('55f1cb04-cdc0-49cb-a39f-915fcd7a1ef8', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '924cf211-3fb7-48f4-af06-acf8f38d30ff', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('67e88ed6-5dda-44e0-bf00-56d76c0b0fc4', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'eb9671b3-cdac-4302-b27e-51cb3df0472b', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('69cf1007-230d-482b-86c0-d06d95d406f1', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'b0628ffa-562b-4ced-85af-3ae6ea08eb1d', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('6e8758e9-da6a-4b53-81d5-9fc2578dd552', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'sys_2', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('79a53bb7-4f09-437c-bee1-abc7fc838978', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '9b1ec131-3cd5-4cac-a82f-54630f993b4e', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('9187e01d-8f62-49dd-b3c1-ef5647d5ac87', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '27385923-e4cf-401d-b123-42e68c30e405', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('92fd17ba-dbf1-4766-9137-61c740343d96', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '1339b0a1-f2ea-4886-9a82-7590d2c228eb', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('9f990442-f236-4875-a1f5-d9afaafccabf', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'sys_4', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('a6a28848-fe79-40ac-ae0f-d34c655224c4', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '61641a8f-4952-4e37-a486-584be252c632', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('d902170b-fdec-4fc3-9039-aebe6fdb7f56', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', 'sys_5', '2019-06-03 17:02:55', '2019-06-03 17:02:55'), ('e16f7fec-1c2f-47fc-8eec-1c6530d4b3cc', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '24ff0bfc-327f-48fb-95b4-ab6cf0223273', '2019-06-03 17:02:55', '2019-06-03 17:02:55');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(50) NOT NULL COMMENT '手机号',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` varchar(1) DEFAULT '1' COMMENT '是否有效  0有效  1无效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营后台用户表';

-- ----------------------------
--  Records of `sys_user`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('2e9e3090-abb1-452d-8d2f-c099b25fee95', 'dis_admin', 'dis_admin', '理财战队管理员', '13051699675', '2019-06-03 16:51:39', '2019-06-03 16:51:39', '0'), ('a3609eaf-1fcf-45f4-a243-4be5e5149f27', 'dev_01', 'dev_01', '开发用户1号', '13051699675', '2019-06-03 17:49:06', '2019-06-03 17:49:06', '0'), ('sys_1', 'admin', 'admin', '系统管理员', '', '2019-04-25 08:01:03', '2019-06-03 16:50:20', '0');
COMMIT;

-- ----------------------------
--  Table structure for `sys_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sys_user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_roles` VALUES ('e2c59276-872e-4bc7-a3a4-168b08c4363a', '2e9e3090-abb1-452d-8d2f-c099b25fee95', 'f7eeb036-e070-4afa-9e3e-fabdc90aa758', '2019-06-03 17:19:50', '2019-06-03 17:19:50');
COMMIT;

-- ----------------------------
--  Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
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
  `plan_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
--  Records of `task`
-- ----------------------------
BEGIN;
INSERT INTO `task` VALUES ('1b60b3a6-8701-42ca-ab32-7fe43c6ff254', '测试任务', '2019-06-02 16:00:00', '2019-06-07 16:00:00', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '510b3e2d-3a75-4269-8b8f-a7acbca1660f', '业务梳理', 'dev_001', 'gl_002', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:47:32', '2019-06-03 20:04:19', '测试计划');
COMMIT;

-- ----------------------------
--  Table structure for `task_module`
-- ----------------------------
DROP TABLE IF EXISTS `task_module`;
CREATE TABLE `task_module` (
  `id` varchar(50) NOT NULL,
  `module_id` varchar(50) NOT NULL,
  `plan_id` varchar(50) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块 任务关系表';

-- ----------------------------
--  Records of `task_module`
-- ----------------------------
BEGIN;
INSERT INTO `task_module` VALUES ('49078ea2-54fe-46ab-a73e-13d690931edd', 'be558f81-1239-4c67-a6b9-ff77a3f1064c', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', '2019-06-06 07:44:38', '2019-06-06 07:44:38'), ('c132c475-6e89-4f42-ab36-d7651940b719', '90a75b2e-b804-42d5-a14d-75ea51f9255d', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', '2019-06-06 07:44:38', '2019-06-06 07:44:38');
COMMIT;

-- ----------------------------
--  Table structure for `task_process`
-- ----------------------------
DROP TABLE IF EXISTS `task_process`;
CREATE TABLE `task_process` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '阶段目标',
  `team_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `process_sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务进度';

-- ----------------------------
--  Records of `task_process`
-- ----------------------------
BEGIN;
INSERT INTO `task_process` VALUES ('01b44a09-b078-4a4d-b3fe-94d0f594d4c7', '开发自测', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:47:00', '2019-06-03 17:47:00', '2'), ('21275081-f928-43eb-8ce9-0d76a9113273', 'dev测试', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:47:00', '2019-06-03 17:47:00', '3'), ('3ac99685-fc39-4059-8734-e3e05e131659', 'uat测试', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:47:00', '2019-06-03 17:47:00', '4'), ('510b3e2d-3a75-4269-8b8f-a7acbca1660f', '业务梳理', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:47:00', '2019-06-03 17:47:00', '1');
COMMIT;

-- ----------------------------
--  Table structure for `task_process_settings`
-- ----------------------------
DROP TABLE IF EXISTS `task_process_settings`;
CREATE TABLE `task_process_settings` (
  `id` varchar(50) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `plan_id` varchar(50) NOT NULL,
  `plan_name` varchar(50) NOT NULL,
  `task_process_id` varchar(50) NOT NULL,
  `task_process_name` varchar(50) NOT NULL,
  `end_notice_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `need_notice` varchar(2) NOT NULL DEFAULT '1' COMMENT '1 需要提醒   0 不需要提醒',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT=' ';

-- ----------------------------
--  Records of `task_process_settings`
-- ----------------------------
BEGIN;
INSERT INTO `task_process_settings` VALUES ('598a529c-5a93-4b60-a3a8-c5e1d19ec18c', '测试任务', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '测试计划', '3ac99685-fc39-4059-8734-e3e05e131659', 'uat测试', '2019-06-03 13:08:09', '1', '2019-06-06 07:44:38', '2019-06-06 07:44:38'), ('8a3a9949-86e0-4825-b85f-57f5c5db2148', '测试任务', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '测试计划', '21275081-f928-43eb-8ce9-0d76a9113273', 'dev测试', '2019-06-03 16:00:00', '1', '2019-06-06 07:44:38', '2019-06-06 07:44:38'), ('ca198ba6-4765-4512-b6a2-8cfe2be3db92', '测试任务', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '测试计划', '01b44a09-b078-4a4d-b3fe-94d0f594d4c7', '开发自测', '2019-06-03 13:08:50', '1', '2019-06-06 07:44:38', '2019-06-06 07:44:38'), ('e7da28bb-7d2a-4782-9dee-a174ddc926d4', '测试任务', '1b60b3a6-8701-42ca-ab32-7fe43c6ff254', 'e1137431-4b82-4977-8425-17bfff8cf2a4', '测试计划', '510b3e2d-3a75-4269-8b8f-a7acbca1660f', '业务梳理', '2019-06-03 13:08:11', '1', '2019-06-06 07:44:38', '2019-06-06 07:44:38');
COMMIT;

-- ----------------------------
--  Table structure for `team`
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '组名称',
  `code` varchar(50) DEFAULT NULL COMMENT '组编号',
  `is_delete` varchar(50) DEFAULT NULL COMMENT '0 未删除   1 已删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ding_url` varchar(200) NOT NULL COMMENT '钉钉url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `team`
-- ----------------------------
BEGIN;
INSERT INTO `team` VALUES ('293cb014-1ac8-46a7-a518-a702cac102e9', '理财战队', 'dis', '0', '2019-06-03 16:56:47', '2019-06-03 17:40:30', 'https://oapi.dingtalk.com/robot/send?access_token=46a6e8062ad803f1bd2e275033e5ac1f6897672641db412b31471fb9c6bc11b1');
COMMIT;

-- ----------------------------
--  Table structure for `user_task`
-- ----------------------------
DROP TABLE IF EXISTS `user_task`;
CREATE TABLE `user_task` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `task_id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `username` varchar(255) DEFAULT NULL,
  `taskname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户 任务关系表';

-- ----------------------------
--  Table structure for `user_team`
-- ----------------------------
DROP TABLE IF EXISTS `user_team`;
CREATE TABLE `user_team` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `team_id` varchar(50) NOT NULL COMMENT '组id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户战队关系表';

-- ----------------------------
--  Records of `user_team`
-- ----------------------------
BEGIN;
INSERT INTO `user_team` VALUES ('0dfd9b32-886c-4c5f-9426-6c5f8174c99b', 'a3609eaf-1fcf-45f4-a243-4be5e5149f27', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:49:31', '2019-06-03 17:49:31'), ('9a04afe3-e0c8-45fb-bba3-00ddf148ae78', '2e9e3090-abb1-452d-8d2f-c099b25fee95', '293cb014-1ac8-46a7-a518-a702cac102e9', '2019-06-03 17:18:08', '2019-06-03 17:18:08');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
