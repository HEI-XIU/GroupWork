/*
Navicat MySQL Data Transfer

Source Server         : booklib
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2023-03-16 14:06:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases` (
  `caseId` int(11) NOT NULL AUTO_INCREMENT,
  `knowledgeId` int(11) DEFAULT NULL,
  `caseInfo` longtext NOT NULL,
  `isCode` tinyint(1) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`caseId`),
  KEY `FK_knowledgeId` (`knowledgeId`),
  CONSTRAINT `FK_knowledgeId` FOREIGN KEY (`knowledgeId`) REFERENCES `knowledge` (`knowledgeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cases
-- ----------------------------
INSERT INTO `cases` VALUES ('1', '1', '(function () {\r\n  var msg = \"hello\";\r\n  print(msg);\r\n})();', '1', 'JS');
INSERT INTO `cases` VALUES ('2', '1', 'function myFunction(p1, p2) {\r\n    return p1 * p2;              // 该函数返回 p1 和 p2 的乘积\r\n}', '1', 'JS');
INSERT INTO `cases` VALUES ('4', '2', 'var txt = \"\";\r\nvar numbers = [45, 4, 9, 16, 25];\r\nnumbers.forEach(myFunction);\r\n\r\nfunction myFunction(value, index, array) {\r\n  txt = txt + value + \"<br>\"; \r\nvar txt = \"\";\r\nvar numbers = [45, 4, 9, 16, 25];\r\nnumbers.forEach(myFunction);\r\n\r\nfunction myFunction(value, index, array) {\r\n  txt = txt + value + \"<br>\"; \r\n}', '1', 'JS');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(255) DEFAULT NULL,
  `courseId` varchar(11) DEFAULT NULL,
  `introduction` varchar(255) DEFAULT NULL,
  `textbook` varchar(255) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('6', 'zk', '1', null, null, null, null);
INSERT INTO `course` VALUES ('9', '软件架构', '3', '一门好课', 'SpringBoot', null, '彭彬');

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `knowledgeId` int(11) NOT NULL AUTO_INCREMENT,
  `knowledgeName` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `emphasis` varchar(255) DEFAULT NULL,
  `preKnowledges` longtext,
  `labels` longtext,
  PRIMARY KEY (`knowledgeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of knowledge
-- ----------------------------
INSERT INTO `knowledge` VALUES ('1', 'test', 'test', 'test', '{}', '{0: \'MySql\', 1: \'计算机\'}');
INSERT INTO `knowledge` VALUES ('2', 'test', 'test', 'test', '{0: 1}', '{0: \'MySql\', 1: \'计算机\'}');
INSERT INTO `knowledge` VALUES ('3', 't', 't', 't', '{}', '{}');

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagId` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(255) NOT NULL,
  `tagGroupName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tagId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '计算机', '课程');
INSERT INTO `tag` VALUES ('2', '软件架构', null);

-- ----------------------------
-- Table structure for taggroup
-- ----------------------------
DROP TABLE IF EXISTS `taggroup`;
CREATE TABLE `taggroup` (
  `tagGroupId` int(11) NOT NULL AUTO_INCREMENT,
  `tagGroupName` varchar(255) NOT NULL,
  PRIMARY KEY (`tagGroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of taggroup
-- ----------------------------
INSERT INTO `taggroup` VALUES ('1', '课程');
