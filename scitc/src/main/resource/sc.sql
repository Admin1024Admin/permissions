/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50719
Source Host           : 127.0.0.1:3306
Source Database       : sc

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-11-24 13:38:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `accessName` varchar(15) NOT NULL COMMENT '权限名字',
  `accessUrl` varchar(100) NOT NULL COMMENT '权限的规则',
  `accessLevel` int(11) NOT NULL COMMENT '权限等级',
  `accessParentId` int(11) NOT NULL COMMENT '权限父级id',
  `isMenu` int(11) NOT NULL COMMENT '是否是菜单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of access
-- ----------------------------
INSERT INTO `access` VALUES ('2', '用户', '', '1', '0', '0');
INSERT INTO `access` VALUES ('3', '管理员管理', '', '1', '0', '1');
INSERT INTO `access` VALUES ('4', '管理员列表', '/admin/list.action', '2', '3', '1');
INSERT INTO `access` VALUES ('5', '角色列表', '/role/list.action', '2', '3', '1');
INSERT INTO `access` VALUES ('6', '权限列表', '/access/access.action', '2', '3', '1');
INSERT INTO `access` VALUES ('7', '班级管理', '', '1', '0', '1');
INSERT INTO `access` VALUES ('8', '教师管理', '', '1', '0', '1');
INSERT INTO `access` VALUES ('9', '学生管理', '', '1', '0', '1');
INSERT INTO `access` VALUES ('10', '后台', '/', '1', '0', '0');
INSERT INTO `access` VALUES ('11', '后台主页', '/index.action', '2', '10', '0');
INSERT INTO `access` VALUES ('12', '欢迎页', '/welcome.action', '2', '10', '0');
INSERT INTO `access` VALUES ('13', '菜单列表', '/access/menu.action', '2', '10', '0');
INSERT INTO `access` VALUES ('14', '退出登陆', '/user/logout.action', '2', '2', '0');
INSERT INTO `access` VALUES ('15', '获取父级菜单', '/access/parents.action', '2', '3', '0');
INSERT INTO `access` VALUES ('16', '添加权限', '/access/add.action', '2', '3', '0');
INSERT INTO `access` VALUES ('17', '添加角色页面', '/role/addPage.action', '2', '3', '0');
INSERT INTO `access` VALUES ('18', '添加角色', '/role/add.action', '2', '3', '0');
INSERT INTO `access` VALUES ('19', '分配权限页面', '/role/editAccessPage.action', '2', '3', '0');
INSERT INTO `access` VALUES ('20', '分配权限', '/role/editAccess.action', '2', '3', '0');
INSERT INTO `access` VALUES ('21', '角色权限', '/access/getRoleAccess.action', '2', '3', '0');
INSERT INTO `access` VALUES ('22', '所有权限', '/access/getAllAccess.action', '2', '3', '0');
INSERT INTO `access` VALUES ('23', '添加管理员页面', '/admin/addPage.action', '2', '3', '0');
INSERT INTO `access` VALUES ('24', '添加管理员', '/admin/add.action', '2', '3', '0');
INSERT INTO `access` VALUES ('25', '班级列表', '/class/list.action', '2', '7', '1');
INSERT INTO `access` VALUES ('26', '添加班级页面', '/class/addPage.action', '2', '7', '0');
INSERT INTO `access` VALUES ('27', '添加班级', '/class/add.action', '2', '7', '0');
INSERT INTO `access` VALUES ('28', '删除班级', '/class/delete.action', '2', '7', '0');
INSERT INTO `access` VALUES ('29', '更新班级页面', '/class/updatePage.action', '2', '7', '0');
INSERT INTO `access` VALUES ('30', '更新班级', '/class/update.action', '2', '7', '0');
INSERT INTO `access` VALUES ('31', '教师列表', '/teacher/list.action', '2', '8', '1');
INSERT INTO `access` VALUES ('32', '添加教师页面', '/teacher/addPage.action', '2', '8', '0');
INSERT INTO `access` VALUES ('33', '添加教师', '/teacher/add.action', '2', '8', '0');
INSERT INTO `access` VALUES ('34', '删除教师', '/teacher/delete.action', '2', '8', '0');
INSERT INTO `access` VALUES ('35', '更新教师页面', '/teacher/updatePage.action', '2', '8', '0');
INSERT INTO `access` VALUES ('36', '更新教师', '/teacher/update.action', '2', '8', '0');
INSERT INTO `access` VALUES ('37', '学生列表', '/stu/list.action', '2', '9', '1');
INSERT INTO `access` VALUES ('38', '添加学生页面', '/stu/addPage.action', '2', '9', '0');
INSERT INTO `access` VALUES ('39', '添加学生', '/stu/add.action', '2', '9', '0');
INSERT INTO `access` VALUES ('40', '删除学生', '/stu/delete.action', '2', '9', '0');
INSERT INTO `access` VALUES ('41', '更新学生页面', '/stu/updatePage.action', '2', '9', '0');
INSERT INTO `access` VALUES ('42', '更新学生', '/stu/update.action', '2', '9', '0');
INSERT INTO `access` VALUES ('43', '手机登陆', '/user/loginPhone.action', '2', '2', '0');
INSERT INTO `access` VALUES ('44', '发送短信', '/user/sendMsg.action', '2', '2', '0');
INSERT INTO `access` VALUES ('45', '日常管理', '', '1', '0', '1');
INSERT INTO `access` VALUES ('46', '请假申请列表', '/daily/leave.action', '2', '45', '1');
INSERT INTO `access` VALUES ('47', '家长会通知列表', '/daily/meeting.action', '2', '45', '1');

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(11) unsigned NOT NULL,
  `address` varchar(100) NOT NULL COMMENT '用户地址',
  PRIMARY KEY (`id`),
  KEY `FK_ik9bfkytk5lrm7yq2bhnf6fe6` (`userId`),
  CONSTRAINT `FK_ik9bfkytk5lrm7yq2bhnf6fe6` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户地址表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('3', '1', 'aaaa');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) NOT NULL,
  `roleDesc` varchar(100) NOT NULL,
  `isSuper` int(11) NOT NULL DEFAULT '0' COMMENT '是否超级管理员:1是,0不是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '上帝视角', '1');
INSERT INTO `role` VALUES ('2', '班主任', '班主任', '0');
INSERT INTO `role` VALUES ('3', '家长', '掌控自己孩子的信息', '0');

-- ----------------------------
-- Table structure for roleaccess
-- ----------------------------
DROP TABLE IF EXISTS `roleaccess`;
CREATE TABLE `roleaccess` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色id',
  `accessId` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8 COMMENT='角色权限关系表';

-- ----------------------------
-- Records of roleaccess
-- ----------------------------
INSERT INTO `roleaccess` VALUES ('95', '3', '13');
INSERT INTO `roleaccess` VALUES ('96', '3', '47');
INSERT INTO `roleaccess` VALUES ('97', '3', '12');
INSERT INTO `roleaccess` VALUES ('98', '3', '11');
INSERT INTO `roleaccess` VALUES ('99', '3', '43');
INSERT INTO `roleaccess` VALUES ('100', '3', '46');
INSERT INTO `roleaccess` VALUES ('101', '3', '10');
INSERT INTO `roleaccess` VALUES ('102', '3', '45');
INSERT INTO `roleaccess` VALUES ('103', '3', '14');
INSERT INTO `roleaccess` VALUES ('104', '3', '2');
INSERT INTO `roleaccess` VALUES ('105', '3', '44');
INSERT INTO `roleaccess` VALUES ('187', '2', '39');
INSERT INTO `roleaccess` VALUES ('188', '2', '36');
INSERT INTO `roleaccess` VALUES ('189', '2', '31');
INSERT INTO `roleaccess` VALUES ('190', '2', '43');
INSERT INTO `roleaccess` VALUES ('191', '2', '33');
INSERT INTO `roleaccess` VALUES ('192', '2', '40');
INSERT INTO `roleaccess` VALUES ('193', '2', '47');
INSERT INTO `roleaccess` VALUES ('194', '2', '34');
INSERT INTO `roleaccess` VALUES ('195', '2', '11');
INSERT INTO `roleaccess` VALUES ('196', '2', '14');
INSERT INTO `roleaccess` VALUES ('197', '2', '35');
INSERT INTO `roleaccess` VALUES ('198', '2', '9');
INSERT INTO `roleaccess` VALUES ('199', '2', '13');
INSERT INTO `roleaccess` VALUES ('200', '2', '41');
INSERT INTO `roleaccess` VALUES ('201', '2', '8');
INSERT INTO `roleaccess` VALUES ('202', '2', '42');
INSERT INTO `roleaccess` VALUES ('203', '2', '32');
INSERT INTO `roleaccess` VALUES ('204', '2', '12');
INSERT INTO `roleaccess` VALUES ('205', '2', '46');
INSERT INTO `roleaccess` VALUES ('207', '2', '38');
INSERT INTO `roleaccess` VALUES ('208', '2', '10');
INSERT INTO `roleaccess` VALUES ('209', '2', '45');
INSERT INTO `roleaccess` VALUES ('210', '2', '37');
INSERT INTO `roleaccess` VALUES ('211', '2', '2');
INSERT INTO `roleaccess` VALUES ('212', '2', '44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) NOT NULL,
  `userPhone` bigint(20) NOT NULL COMMENT '用户手机号',
  `userPwd` char(32) NOT NULL COMMENT '用户密码',
  `userAddTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户添加时间',
  `roleId` int(11) NOT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-12 22:23:08', '1');
INSERT INTO `user` VALUES ('4', 'admin789', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-13 13:42:11', '2');
INSERT INTO `user` VALUES ('7', 'root', '15528950203', '123123', '2018-11-22 20:52:41', '1');
INSERT INTO `user` VALUES ('11', 'admin2', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-13 13:42:11', '3');
INSERT INTO `user` VALUES ('12', 'admin3', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-13 13:42:11', '1');
INSERT INTO `user` VALUES ('24', 'admin4', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-13 13:42:11', '2');
INSERT INTO `user` VALUES ('25', 'admin5', '15808907890', '4297F44B1397399D7A93955235245B24', '2018-11-13 13:42:11', '2');
