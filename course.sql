/*
Navicat MySQL Data Transfer

Source Server         : booklib
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2023-03-25 20:47:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases` (
  `caseId` int(11) NOT NULL AUTO_INCREMENT,
  `caseName` varchar(255) NOT NULL,
  `knowledgeId` int(11) NOT NULL,
  `caseContent` longtext NOT NULL,
  `isCode` tinyint(1) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`caseId`),
  KEY `FK_knowledgeId` (`knowledgeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cases
-- ----------------------------
INSERT INTO `cases` VALUES ('1', 'js1', '1', '(function () {\r\n  var msg = \"hello\";\r\n  print(msg);\r\n})();', '1', 'JS');
INSERT INTO `cases` VALUES ('2', 'js2', '1', 'function myFunction(p1, p2) {\r\n    return p1 * p2;              // 该函数返回 p1 和 p2 的乘积\r\n}', '1', 'JS');
INSERT INTO `cases` VALUES ('4', 'js3', '2', 'var txt = \"\";\r\nvar numbers = [45, 4, 9, 16, 25];\r\nnumbers.forEach(myFunction);\r\n\r\nfunction myFunction(value, index, array) {\r\n  txt = txt + value + \"<br>\"; \r\nvar txt = \"\";\r\nvar numbers = [45, 4, 9, 16, 25];\r\nnumbers.forEach(myFunction);\r\n\r\nfunction myFunction(value, index, array) {\r\n  txt = txt + value + \"<br>\"; \r\n}', '1', 'JS');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `courseId` int(11) NOT NULL,
  `courseName` varchar(255) NOT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `textbook` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('1', 'zk', null, null, null, null);

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `kid` int(11) NOT NULL AUTO_INCREMENT,
  `kname` varchar(255) NOT NULL,
  `courseid` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `emphasis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kid`),
  KEY `FK_courseId342525` (`courseid`),
  CONSTRAINT `FK_courseId342525` FOREIGN KEY (`courseid`) REFERENCES `courses` (`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of knowledge
-- ----------------------------
INSERT INTO `knowledge` VALUES ('1', '计算机', '1', '计算机（computer）俗称电脑，是现代一种用于高速计算的电子计算机器，可以进行数值计算，又可以进行逻辑计算，还具有存储记忆功能。', 'C语言');
INSERT INTO `knowledge` VALUES ('2', '高等数学', '1', '高等数学是指相对于初等数学和中等数学而言，数学的对象及方法较为繁杂的一部分，中学的代数、几何以及简单的集合论初步、逻辑初步称为中等数学，将其作为中小学阶段的初等数学与大学阶段的高等数学的过渡。', '微积分');
INSERT INTO `knowledge` VALUES ('3', 'springboot', '1', 'Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。', 'spring');
INSERT INTO `knowledge` VALUES ('4', '前端', '1', '前端即网站前台部分，运行在PC端，移动端等浏览器上展现给用户浏览的网页。随着互联网技术的发展，HTML5，CSS3，前端框架的应用，跨平台响应式网页设计能够适应各种屏幕分辨率，合适的动效设计，给用户带来极高的用户体验。', 'html+css+javascript');
INSERT INTO `knowledge` VALUES ('5', '后端', '1', '后端的工作职责是负责后台端程序的相关开关，主要是涉及到了服务器以及数据库还有相关语言代码的编写工作。', 'java');
INSERT INTO `knowledge` VALUES ('6', 'C语言', '1', 'C语言是一门面向过程的、抽象化的通用程序设计语言，广泛应用于底层开发。C语言能以简易的方式编译、处理低级存储器。C语言是仅产生少量的机器语言以及不需要任何运行环境支持便能运行的高效率程序设计语言。尽管C语言提供了许多低级处理的功能，但仍然保持着跨平台的特性，以一个标准规格写出的C语言程序可在包括类似嵌入式处理器以及超级计算机等作业平台的许多计算机平台上进行编译。', '面向过程');
INSERT INTO `knowledge` VALUES ('8', 'test1', '1', 'test1', 'test1');
INSERT INTO `knowledge` VALUES ('9', 'test2', '1', 'test2', 'test2');

-- ----------------------------
-- Table structure for prerelation
-- ----------------------------
DROP TABLE IF EXISTS `prerelation`;
CREATE TABLE `prerelation` (
  `preid` int(11) NOT NULL AUTO_INCREMENT,
  `kid` int(11) NOT NULL,
  `prekid` int(11) NOT NULL,
  PRIMARY KEY (`preid`),
  KEY `FK_kid1` (`kid`),
  KEY `FK_kid2` (`prekid`),
  CONSTRAINT `FK_kid1` FOREIGN KEY (`kid`) REFERENCES `knowledge` (`kid`),
  CONSTRAINT `FK_kid2` FOREIGN KEY (`prekid`) REFERENCES `knowledge` (`kid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of prerelation
-- ----------------------------
INSERT INTO `prerelation` VALUES ('1', '6', '1');
INSERT INTO `prerelation` VALUES ('2', '5', '3');
INSERT INTO `prerelation` VALUES ('3', '5', '1');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagid` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(255) NOT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '计算机');
INSERT INTO `tag` VALUES ('2', '编程');
INSERT INTO `tag` VALUES ('3', '前端');
INSERT INTO `tag` VALUES ('5', '后端');
INSERT INTO `tag` VALUES ('6', '技术');
INSERT INTO `tag` VALUES ('7', '技术');
INSERT INTO `tag` VALUES ('8', '技术');

-- ----------------------------
-- Table structure for tag_course
-- ----------------------------
DROP TABLE IF EXISTS `tag_course`;
CREATE TABLE `tag_course` (
  `tcid` int(127) NOT NULL AUTO_INCREMENT,
  `tid` int(127) NOT NULL,
  `cid` int(127) NOT NULL,
  PRIMARY KEY (`tcid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag_course
-- ----------------------------
INSERT INTO `tag_course` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for tag_group
-- ----------------------------
DROP TABLE IF EXISTS `tag_group`;
CREATE TABLE `tag_group` (
  `tgid` int(127) NOT NULL AUTO_INCREMENT,
  `tid` int(127) NOT NULL,
  `gid` int(127) NOT NULL,
  PRIMARY KEY (`tgid`,`tid`,`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag_group
-- ----------------------------
INSERT INTO `tag_group` VALUES ('9', '2', '1');
INSERT INTO `tag_group` VALUES ('10', '5', '2');

-- ----------------------------
-- Table structure for tag_konwledge
-- ----------------------------
DROP TABLE IF EXISTS `tag_konwledge`;
CREATE TABLE `tag_konwledge` (
  `tkid` int(127) NOT NULL AUTO_INCREMENT,
  `tid` int(127) NOT NULL,
  `kid` int(127) NOT NULL,
  PRIMARY KEY (`tkid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag_konwledge
-- ----------------------------
INSERT INTO `tag_konwledge` VALUES ('1', '1', '1');
INSERT INTO `tag_konwledge` VALUES ('2', '1', '2');
INSERT INTO `tag_konwledge` VALUES ('3', '1', '3');

-- ----------------------------
-- Table structure for tgroup
-- ----------------------------
DROP TABLE IF EXISTS `tgroup`;
CREATE TABLE `tgroup` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(128) NOT NULL,
  PRIMARY KEY (`gid`),
  KEY `gname` (`gname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tgroup
-- ----------------------------
INSERT INTO `tgroup` VALUES ('2', '前端');
INSERT INTO `tgroup` VALUES ('1', '教学');
