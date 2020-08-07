/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : attendance_system

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/08/2020 17:53:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `employee_id` int NOT NULL COMMENT '员工id',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`accountnumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'whk123', 'wanghongkun', '2020-03-26 16:45:32');
INSERT INTO `account` VALUES (2, '123', 'whk', '2020-03-26 16:57:33');

-- ----------------------------
-- Table structure for diary
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '流水',
  `login_time` datetime(0) NOT NULL COMMENT '登录时间',
  `employee_id` int NOT NULL COMMENT '员工id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 413 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES (1, '2020-02-25 13:22:21', 1);
INSERT INTO `diary` VALUES (2, '2020-02-25 13:25:47', 2);
INSERT INTO `diary` VALUES (3, '2020-02-25 05:27:30', 1);
INSERT INTO `diary` VALUES (4, '2020-02-25 15:35:38', 1);
INSERT INTO `diary` VALUES (5, '2020-02-25 15:37:46', 1);
INSERT INTO `diary` VALUES (6, '2020-02-25 15:38:47', 1);
INSERT INTO `diary` VALUES (7, '2020-02-25 15:40:50', 1);
INSERT INTO `diary` VALUES (8, '2020-02-25 15:54:33', 1);
INSERT INTO `diary` VALUES (9, '2020-02-25 15:59:34', 1);
INSERT INTO `diary` VALUES (10, '2020-02-25 16:01:08', 1);
INSERT INTO `diary` VALUES (11, '2020-02-25 16:07:01', 1);
INSERT INTO `diary` VALUES (12, '2020-02-26 12:35:22', 1);
INSERT INTO `diary` VALUES (13, '2020-02-26 13:03:45', 1);
INSERT INTO `diary` VALUES (14, '2020-02-26 13:04:14', 1);
INSERT INTO `diary` VALUES (15, '2020-02-26 13:13:16', 1);
INSERT INTO `diary` VALUES (16, '2020-02-26 13:16:16', 1);
INSERT INTO `diary` VALUES (17, '2020-02-26 16:00:05', 1);
INSERT INTO `diary` VALUES (18, '2020-02-26 16:02:44', 1);
INSERT INTO `diary` VALUES (19, '2020-02-26 16:09:58', 1);
INSERT INTO `diary` VALUES (20, '2020-02-26 16:10:57', 1);
INSERT INTO `diary` VALUES (21, '2020-02-26 16:22:59', 1);
INSERT INTO `diary` VALUES (22, '2020-02-26 16:25:35', 1);
INSERT INTO `diary` VALUES (23, '2020-02-26 16:30:47', 1);
INSERT INTO `diary` VALUES (24, '2020-02-26 16:33:41', 1);
INSERT INTO `diary` VALUES (25, '2020-02-27 02:10:37', 1);
INSERT INTO `diary` VALUES (26, '2020-02-27 03:55:36', 1);
INSERT INTO `diary` VALUES (27, '2020-02-27 03:56:34', 1);
INSERT INTO `diary` VALUES (28, '2020-02-27 03:57:17', 1);
INSERT INTO `diary` VALUES (29, '2020-02-27 03:59:40', 1);
INSERT INTO `diary` VALUES (30, '2020-02-27 04:01:55', 1);
INSERT INTO `diary` VALUES (31, '2020-02-27 04:06:46', 1);
INSERT INTO `diary` VALUES (32, '2020-02-27 04:23:47', 1);
INSERT INTO `diary` VALUES (33, '2020-02-27 04:26:33', 1);
INSERT INTO `diary` VALUES (34, '2020-02-27 04:26:42', 1);
INSERT INTO `diary` VALUES (35, '2020-02-27 04:28:58', 1);
INSERT INTO `diary` VALUES (36, '2020-02-27 04:31:25', 1);
INSERT INTO `diary` VALUES (37, '2020-02-27 04:33:54', 1);
INSERT INTO `diary` VALUES (38, '2020-02-27 05:11:53', 1);
INSERT INTO `diary` VALUES (39, '2020-02-27 05:16:08', 1);
INSERT INTO `diary` VALUES (40, '2020-02-27 05:16:47', 1);
INSERT INTO `diary` VALUES (41, '2020-02-27 06:15:49', 1);
INSERT INTO `diary` VALUES (42, '2020-02-27 06:17:26', 1);
INSERT INTO `diary` VALUES (43, '2020-02-29 02:22:43', 1);
INSERT INTO `diary` VALUES (44, '2020-02-29 02:26:40', 1);
INSERT INTO `diary` VALUES (45, '2020-02-29 02:48:12', 1);
INSERT INTO `diary` VALUES (46, '2020-02-29 05:33:55', 1);
INSERT INTO `diary` VALUES (47, '2020-02-29 05:34:59', 1);
INSERT INTO `diary` VALUES (48, '2020-02-29 05:40:29', 1);
INSERT INTO `diary` VALUES (49, '2020-02-29 05:44:51', 1);
INSERT INTO `diary` VALUES (50, '2020-02-29 05:51:16', 1);
INSERT INTO `diary` VALUES (51, '2020-02-29 05:52:19', 1);
INSERT INTO `diary` VALUES (52, '2020-03-01 12:37:18', 1);
INSERT INTO `diary` VALUES (53, '2020-03-01 12:37:46', 1);
INSERT INTO `diary` VALUES (54, '2020-03-01 12:42:56', 1);
INSERT INTO `diary` VALUES (55, '2020-03-01 12:45:58', 1);
INSERT INTO `diary` VALUES (56, '2020-03-01 12:47:22', 1);
INSERT INTO `diary` VALUES (57, '2020-03-01 13:04:30', 1);
INSERT INTO `diary` VALUES (58, '2020-03-01 13:14:18', 1);
INSERT INTO `diary` VALUES (59, '2020-03-01 13:57:54', 1);
INSERT INTO `diary` VALUES (60, '2020-03-01 16:14:06', 1);
INSERT INTO `diary` VALUES (61, '2020-03-01 16:19:23', 1);
INSERT INTO `diary` VALUES (62, '2020-03-01 16:38:42', 1);
INSERT INTO `diary` VALUES (63, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (64, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (65, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (66, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (67, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (68, '2020-03-01 16:44:44', 1);
INSERT INTO `diary` VALUES (69, '2020-03-01 17:04:24', 1);
INSERT INTO `diary` VALUES (70, '2020-03-01 17:08:58', 1);
INSERT INTO `diary` VALUES (71, '2020-03-01 17:16:34', 1);
INSERT INTO `diary` VALUES (72, '2020-03-01 17:39:14', 1);
INSERT INTO `diary` VALUES (73, '2020-03-01 17:42:44', 1);
INSERT INTO `diary` VALUES (74, '2020-03-01 18:14:51', 1);
INSERT INTO `diary` VALUES (75, '2020-03-01 18:29:51', 1);
INSERT INTO `diary` VALUES (76, '2020-03-01 18:45:19', 1);
INSERT INTO `diary` VALUES (77, '2020-03-01 18:49:04', 1);
INSERT INTO `diary` VALUES (78, '2020-03-01 18:59:27', 1);
INSERT INTO `diary` VALUES (79, '2020-03-01 19:32:41', 1);
INSERT INTO `diary` VALUES (80, '2020-03-01 19:37:09', 1);
INSERT INTO `diary` VALUES (81, '2020-03-01 19:41:29', 1);
INSERT INTO `diary` VALUES (82, '2020-03-01 20:17:07', 1);
INSERT INTO `diary` VALUES (83, '2020-03-01 20:27:50', 1);
INSERT INTO `diary` VALUES (84, '2020-03-01 20:36:56', 1);
INSERT INTO `diary` VALUES (85, '2020-03-01 21:21:56', 1);
INSERT INTO `diary` VALUES (86, '2020-03-01 21:24:42', 1);
INSERT INTO `diary` VALUES (87, '2020-03-01 21:26:40', 1);
INSERT INTO `diary` VALUES (88, '2020-03-01 21:27:18', 1);
INSERT INTO `diary` VALUES (89, '2020-03-01 21:28:49', 1);
INSERT INTO `diary` VALUES (90, '2020-03-01 21:30:16', 1);
INSERT INTO `diary` VALUES (91, '2020-03-01 21:32:23', 1);
INSERT INTO `diary` VALUES (92, '2020-03-01 21:38:31', 1);
INSERT INTO `diary` VALUES (93, '2020-03-01 21:41:32', 1);
INSERT INTO `diary` VALUES (94, '2020-03-01 21:48:12', 1);
INSERT INTO `diary` VALUES (95, '2020-03-01 21:49:11', 1);
INSERT INTO `diary` VALUES (96, '2020-03-01 21:49:57', 1);
INSERT INTO `diary` VALUES (97, '2020-03-01 22:14:27', 1);
INSERT INTO `diary` VALUES (98, '2020-03-01 22:16:00', 1);
INSERT INTO `diary` VALUES (99, '2020-03-01 22:32:58', 1);
INSERT INTO `diary` VALUES (100, '2020-03-01 22:35:16', 1);
INSERT INTO `diary` VALUES (101, '2020-03-01 22:38:11', 1);
INSERT INTO `diary` VALUES (102, '2020-03-01 22:39:07', 1);
INSERT INTO `diary` VALUES (103, '2020-03-01 22:39:31', 1);
INSERT INTO `diary` VALUES (104, '2020-03-01 22:40:28', 1);
INSERT INTO `diary` VALUES (105, '2020-03-01 22:54:40', 1);
INSERT INTO `diary` VALUES (106, '2020-03-01 23:06:26', 1);
INSERT INTO `diary` VALUES (107, '2020-03-01 23:24:09', 1);
INSERT INTO `diary` VALUES (108, '2020-03-01 23:26:39', 1);
INSERT INTO `diary` VALUES (109, '2020-03-01 23:28:11', 1);
INSERT INTO `diary` VALUES (110, '2020-03-01 23:34:59', 1);
INSERT INTO `diary` VALUES (111, '2020-03-01 23:38:37', 1);
INSERT INTO `diary` VALUES (112, '2020-03-01 23:39:46', 1);
INSERT INTO `diary` VALUES (113, '2020-03-01 23:41:15', 1);
INSERT INTO `diary` VALUES (114, '2020-03-02 11:12:10', 1);
INSERT INTO `diary` VALUES (115, '2020-03-02 12:28:25', 1);
INSERT INTO `diary` VALUES (116, '2020-03-02 13:14:07', 1);
INSERT INTO `diary` VALUES (117, '2020-03-02 13:16:23', 1);
INSERT INTO `diary` VALUES (118, '2020-03-02 13:18:48', 1);
INSERT INTO `diary` VALUES (119, '2020-03-02 13:41:42', 1);
INSERT INTO `diary` VALUES (120, '2020-03-02 13:43:10', 1);
INSERT INTO `diary` VALUES (121, '2020-03-02 13:43:38', 1);
INSERT INTO `diary` VALUES (122, '2020-03-02 13:46:12', 1);
INSERT INTO `diary` VALUES (123, '2020-03-02 13:53:43', 1);
INSERT INTO `diary` VALUES (124, '2020-03-02 13:55:10', 1);
INSERT INTO `diary` VALUES (125, '2020-03-02 13:56:07', 1);
INSERT INTO `diary` VALUES (126, '2020-03-02 13:56:32', 1);
INSERT INTO `diary` VALUES (127, '2020-03-02 13:57:50', 1);
INSERT INTO `diary` VALUES (128, '2020-03-02 14:00:37', 1);
INSERT INTO `diary` VALUES (129, '2020-03-03 11:36:06', 1);
INSERT INTO `diary` VALUES (130, '2020-03-03 11:47:43', 1);
INSERT INTO `diary` VALUES (131, '2020-03-03 11:50:15', 1);
INSERT INTO `diary` VALUES (132, '2020-03-03 13:36:25', 1);
INSERT INTO `diary` VALUES (133, '2020-03-03 13:38:45', 1);
INSERT INTO `diary` VALUES (134, '2020-03-03 13:43:36', 1);
INSERT INTO `diary` VALUES (135, '2020-03-03 13:43:50', 1);
INSERT INTO `diary` VALUES (136, '2020-03-03 13:44:09', 1);
INSERT INTO `diary` VALUES (137, '2020-03-03 13:48:23', 1);
INSERT INTO `diary` VALUES (138, '2020-03-03 13:52:57', 1);
INSERT INTO `diary` VALUES (139, '2020-03-03 17:19:54', 1);
INSERT INTO `diary` VALUES (140, '2020-03-03 17:32:10', 1);
INSERT INTO `diary` VALUES (141, '2020-03-03 17:32:34', 1);
INSERT INTO `diary` VALUES (142, '2020-03-03 17:36:43', 1);
INSERT INTO `diary` VALUES (143, '2020-03-03 17:39:15', 1);
INSERT INTO `diary` VALUES (144, '2020-03-03 17:44:51', 1);
INSERT INTO `diary` VALUES (145, '2020-03-03 17:48:47', 1);
INSERT INTO `diary` VALUES (146, '2020-03-03 17:50:25', 1);
INSERT INTO `diary` VALUES (147, '2020-03-03 18:01:05', 1);
INSERT INTO `diary` VALUES (148, '2020-03-03 18:02:30', 1);
INSERT INTO `diary` VALUES (149, '2020-03-03 18:07:19', 1);
INSERT INTO `diary` VALUES (150, '2020-03-03 18:12:38', 1);
INSERT INTO `diary` VALUES (151, '2020-03-03 18:13:22', 1);
INSERT INTO `diary` VALUES (152, '2020-03-03 23:30:07', 1);
INSERT INTO `diary` VALUES (153, '2020-03-03 23:30:31', 1);
INSERT INTO `diary` VALUES (154, '2020-03-03 23:33:57', 1);
INSERT INTO `diary` VALUES (155, '2020-03-03 23:35:13', 1);
INSERT INTO `diary` VALUES (156, '2020-03-03 23:36:10', 1);
INSERT INTO `diary` VALUES (157, '2020-03-03 23:42:38', 1);
INSERT INTO `diary` VALUES (158, '2020-03-03 23:47:12', 1);
INSERT INTO `diary` VALUES (159, '2020-03-03 23:54:48', 1);
INSERT INTO `diary` VALUES (160, '2020-03-03 23:58:36', 1);
INSERT INTO `diary` VALUES (161, '2020-03-04 00:06:48', 1);
INSERT INTO `diary` VALUES (162, '2020-03-04 00:07:53', 1);
INSERT INTO `diary` VALUES (163, '2020-03-04 00:09:06', 1);
INSERT INTO `diary` VALUES (164, '2020-03-04 00:10:18', 1);
INSERT INTO `diary` VALUES (165, '2020-03-04 00:10:43', 1);
INSERT INTO `diary` VALUES (166, '2020-03-04 00:20:35', 1);
INSERT INTO `diary` VALUES (167, '2020-03-04 00:20:56', 1);
INSERT INTO `diary` VALUES (168, '2020-03-04 00:21:33', 1);
INSERT INTO `diary` VALUES (169, '2020-03-04 00:21:58', 1);
INSERT INTO `diary` VALUES (170, '2020-03-04 00:24:54', 1);
INSERT INTO `diary` VALUES (171, '2020-03-04 00:25:19', 1);
INSERT INTO `diary` VALUES (172, '2020-03-04 00:30:42', 1);
INSERT INTO `diary` VALUES (173, '2020-03-04 00:33:15', 1);
INSERT INTO `diary` VALUES (174, '2020-03-04 00:37:08', 1);
INSERT INTO `diary` VALUES (175, '2020-03-04 00:50:17', 1);
INSERT INTO `diary` VALUES (176, '2020-03-04 00:52:38', 1);
INSERT INTO `diary` VALUES (177, '2020-03-04 00:59:09', 1);
INSERT INTO `diary` VALUES (178, '2020-03-04 01:01:21', 1);
INSERT INTO `diary` VALUES (179, '2020-03-04 01:03:11', 1);
INSERT INTO `diary` VALUES (180, '2020-03-04 01:16:45', 1);
INSERT INTO `diary` VALUES (181, '2020-03-04 01:23:48', 1);
INSERT INTO `diary` VALUES (182, '2020-03-04 01:25:35', 1);
INSERT INTO `diary` VALUES (183, '2020-03-04 01:26:25', 1);
INSERT INTO `diary` VALUES (184, '2020-03-04 01:27:01', 1);
INSERT INTO `diary` VALUES (185, '2020-03-04 01:27:44', 1);
INSERT INTO `diary` VALUES (186, '2020-03-04 01:28:29', 1);
INSERT INTO `diary` VALUES (187, '2020-03-04 01:29:23', 1);
INSERT INTO `diary` VALUES (188, '2020-03-04 01:30:52', 1);
INSERT INTO `diary` VALUES (189, '2020-03-04 01:35:23', 1);
INSERT INTO `diary` VALUES (190, '2020-03-04 01:40:09', 1);
INSERT INTO `diary` VALUES (191, '2020-03-04 01:41:49', 1);
INSERT INTO `diary` VALUES (192, '2020-03-04 01:45:01', 1);
INSERT INTO `diary` VALUES (193, '2020-03-04 01:47:08', 1);
INSERT INTO `diary` VALUES (194, '2020-03-04 01:48:48', 1);
INSERT INTO `diary` VALUES (195, '2020-03-04 11:04:51', 1);
INSERT INTO `diary` VALUES (196, '2020-03-04 11:14:25', 1);
INSERT INTO `diary` VALUES (197, '2020-03-04 11:17:28', 1);
INSERT INTO `diary` VALUES (198, '2020-03-04 11:20:46', 1);
INSERT INTO `diary` VALUES (199, '2020-03-04 11:22:59', 1);
INSERT INTO `diary` VALUES (200, '2020-03-04 11:23:56', 1);
INSERT INTO `diary` VALUES (201, '2020-03-04 11:24:35', 1);
INSERT INTO `diary` VALUES (202, '2020-03-04 11:25:09', 1);
INSERT INTO `diary` VALUES (203, '2020-03-04 11:25:27', 1);
INSERT INTO `diary` VALUES (204, '2020-03-04 11:41:12', 1);
INSERT INTO `diary` VALUES (205, '2020-03-04 11:50:33', 1);
INSERT INTO `diary` VALUES (206, '2020-03-04 11:56:05', 1);
INSERT INTO `diary` VALUES (207, '2020-03-04 12:00:36', 1);
INSERT INTO `diary` VALUES (208, '2020-03-04 12:01:29', 1);
INSERT INTO `diary` VALUES (209, '2020-03-04 12:03:28', 1);
INSERT INTO `diary` VALUES (210, '2020-03-04 12:05:07', 1);
INSERT INTO `diary` VALUES (211, '2020-03-04 13:03:13', 1);
INSERT INTO `diary` VALUES (212, '2020-03-04 13:03:23', 1);
INSERT INTO `diary` VALUES (213, '2020-03-05 10:49:23', 1);
INSERT INTO `diary` VALUES (214, '2020-03-05 11:28:58', 1);
INSERT INTO `diary` VALUES (215, '2020-03-05 11:57:55', 1);
INSERT INTO `diary` VALUES (216, '2020-03-06 14:28:38', 1);
INSERT INTO `diary` VALUES (217, '2020-03-06 14:30:53', 1);
INSERT INTO `diary` VALUES (218, '2020-03-06 14:33:50', 1);
INSERT INTO `diary` VALUES (219, '2020-03-06 14:36:17', 1);
INSERT INTO `diary` VALUES (220, '2020-03-06 14:37:03', 1);
INSERT INTO `diary` VALUES (221, '2020-03-06 14:45:44', 1);
INSERT INTO `diary` VALUES (222, '2020-03-06 14:59:48', 1);
INSERT INTO `diary` VALUES (223, '2020-03-06 15:01:28', 1);
INSERT INTO `diary` VALUES (224, '2020-03-06 15:02:40', 1);
INSERT INTO `diary` VALUES (225, '2020-03-06 15:04:23', 1);
INSERT INTO `diary` VALUES (226, '2020-03-06 15:22:21', 1);
INSERT INTO `diary` VALUES (227, '2020-03-06 15:23:46', 1);
INSERT INTO `diary` VALUES (228, '2020-03-06 15:26:38', 1);
INSERT INTO `diary` VALUES (229, '2020-03-06 15:28:33', 1);
INSERT INTO `diary` VALUES (230, '2020-03-06 15:31:20', 1);
INSERT INTO `diary` VALUES (231, '2020-03-06 15:34:58', 1);
INSERT INTO `diary` VALUES (232, '2020-03-06 15:40:16', 1);
INSERT INTO `diary` VALUES (233, '2020-03-06 15:41:49', 1);
INSERT INTO `diary` VALUES (234, '2020-03-06 15:42:10', 1);
INSERT INTO `diary` VALUES (235, '2020-03-06 15:46:17', 1);
INSERT INTO `diary` VALUES (236, '2020-03-06 15:48:16', 1);
INSERT INTO `diary` VALUES (237, '2020-03-06 15:50:07', 1);
INSERT INTO `diary` VALUES (238, '2020-03-06 15:52:38', 1);
INSERT INTO `diary` VALUES (239, '2020-03-06 15:54:24', 1);
INSERT INTO `diary` VALUES (240, '2020-03-06 15:55:24', 1);
INSERT INTO `diary` VALUES (241, '2020-03-06 16:00:49', 1);
INSERT INTO `diary` VALUES (242, '2020-03-06 16:08:42', 1);
INSERT INTO `diary` VALUES (243, '2020-03-06 16:09:38', 1);
INSERT INTO `diary` VALUES (244, '2020-03-06 16:11:45', 1);
INSERT INTO `diary` VALUES (245, '2020-03-06 16:13:04', 1);
INSERT INTO `diary` VALUES (246, '2020-03-06 16:17:36', 1);
INSERT INTO `diary` VALUES (247, '2020-03-06 16:22:01', 1);
INSERT INTO `diary` VALUES (248, '2020-03-06 16:22:30', 1);
INSERT INTO `diary` VALUES (249, '2020-03-06 20:35:50', 1);
INSERT INTO `diary` VALUES (250, '2020-03-06 20:38:17', 1);
INSERT INTO `diary` VALUES (251, '2020-03-06 20:41:01', 1);
INSERT INTO `diary` VALUES (252, '2020-03-06 20:46:50', 1);
INSERT INTO `diary` VALUES (253, '2020-03-06 20:50:39', 1);
INSERT INTO `diary` VALUES (254, '2020-03-06 20:56:47', 1);
INSERT INTO `diary` VALUES (255, '2020-03-06 20:59:17', 1);
INSERT INTO `diary` VALUES (256, '2020-03-06 21:01:22', 1);
INSERT INTO `diary` VALUES (257, '2020-03-06 21:03:07', 1);
INSERT INTO `diary` VALUES (258, '2020-03-06 21:03:35', 1);
INSERT INTO `diary` VALUES (259, '2020-03-06 21:27:42', 1);
INSERT INTO `diary` VALUES (260, '2020-03-06 21:29:41', 1);
INSERT INTO `diary` VALUES (261, '2020-03-06 21:30:27', 1);
INSERT INTO `diary` VALUES (262, '2020-03-06 21:35:21', 1);
INSERT INTO `diary` VALUES (263, '2020-03-06 21:37:15', 1);
INSERT INTO `diary` VALUES (264, '2020-03-06 21:38:42', 1);
INSERT INTO `diary` VALUES (265, '2020-03-06 21:41:22', 1);
INSERT INTO `diary` VALUES (266, '2020-03-06 21:42:37', 1);
INSERT INTO `diary` VALUES (267, '2020-03-06 21:44:28', 1);
INSERT INTO `diary` VALUES (268, '2020-03-06 21:52:01', 1);
INSERT INTO `diary` VALUES (269, '2020-03-06 22:02:24', 1);
INSERT INTO `diary` VALUES (270, '2020-03-06 22:06:52', 1);
INSERT INTO `diary` VALUES (271, '2020-03-06 22:10:00', 1);
INSERT INTO `diary` VALUES (272, '2020-03-07 11:44:23', 1);
INSERT INTO `diary` VALUES (273, '2020-03-07 12:29:48', 1);
INSERT INTO `diary` VALUES (274, '2020-03-09 14:15:33', 1);
INSERT INTO `diary` VALUES (275, '2020-03-09 14:41:51', 1);
INSERT INTO `diary` VALUES (276, '2020-03-09 14:42:48', 1);
INSERT INTO `diary` VALUES (277, '2020-03-09 14:44:27', 1);
INSERT INTO `diary` VALUES (278, '2020-03-09 14:53:57', 1);
INSERT INTO `diary` VALUES (279, '2020-03-09 15:02:01', 1);
INSERT INTO `diary` VALUES (280, '2020-03-09 15:02:47', 1);
INSERT INTO `diary` VALUES (281, '2020-03-09 15:38:19', 1);
INSERT INTO `diary` VALUES (282, '2020-03-09 16:04:45', 1);
INSERT INTO `diary` VALUES (283, '2020-03-09 16:05:59', 1);
INSERT INTO `diary` VALUES (284, '2020-03-09 16:08:55', 1);
INSERT INTO `diary` VALUES (285, '2020-03-09 16:39:02', 1);
INSERT INTO `diary` VALUES (286, '2020-03-09 17:11:11', 1);
INSERT INTO `diary` VALUES (287, '2020-03-09 17:12:58', 1);
INSERT INTO `diary` VALUES (288, '2020-03-09 17:16:57', 1);
INSERT INTO `diary` VALUES (289, '2020-03-09 17:24:42', 1);
INSERT INTO `diary` VALUES (290, '2020-03-09 17:32:23', 1);
INSERT INTO `diary` VALUES (291, '2020-03-09 17:39:57', 1);
INSERT INTO `diary` VALUES (292, '2020-03-09 17:42:15', 1);
INSERT INTO `diary` VALUES (293, '2020-03-09 17:44:35', 1);
INSERT INTO `diary` VALUES (294, '2020-03-09 17:49:11', 1);
INSERT INTO `diary` VALUES (295, '2020-03-09 17:57:37', 1);
INSERT INTO `diary` VALUES (296, '2020-03-09 18:00:25', 1);
INSERT INTO `diary` VALUES (297, '2020-03-09 21:10:04', 1);
INSERT INTO `diary` VALUES (298, '2020-03-09 22:01:03', 1);
INSERT INTO `diary` VALUES (299, '2020-03-09 22:02:54', 1);
INSERT INTO `diary` VALUES (300, '2020-03-09 22:04:56', 1);
INSERT INTO `diary` VALUES (301, '2020-03-09 22:09:34', 1);
INSERT INTO `diary` VALUES (302, '2020-03-09 22:12:19', 1);
INSERT INTO `diary` VALUES (303, '2020-03-09 22:14:59', 1);
INSERT INTO `diary` VALUES (304, '2020-03-09 22:18:08', 1);
INSERT INTO `diary` VALUES (305, '2020-03-09 22:20:11', 1);
INSERT INTO `diary` VALUES (306, '2020-03-09 22:24:37', 1);
INSERT INTO `diary` VALUES (307, '2020-03-09 22:27:37', 1);
INSERT INTO `diary` VALUES (308, '2020-03-09 22:35:59', 1);
INSERT INTO `diary` VALUES (309, '2020-03-09 22:39:22', 1);
INSERT INTO `diary` VALUES (310, '2020-03-10 11:16:39', 1);
INSERT INTO `diary` VALUES (311, '2020-03-10 11:17:01', 1);
INSERT INTO `diary` VALUES (312, '2020-03-10 11:24:10', 1);
INSERT INTO `diary` VALUES (313, '2020-03-10 11:26:44', 1);
INSERT INTO `diary` VALUES (314, '2020-03-10 11:28:53', 1);
INSERT INTO `diary` VALUES (315, '2020-03-10 11:34:50', 1);
INSERT INTO `diary` VALUES (316, '2020-03-10 12:23:39', 1);
INSERT INTO `diary` VALUES (317, '2020-03-10 12:28:03', 1);
INSERT INTO `diary` VALUES (318, '2020-03-10 12:29:27', 1);
INSERT INTO `diary` VALUES (319, '2020-03-10 12:36:19', 1);
INSERT INTO `diary` VALUES (320, '2020-03-10 12:40:32', 1);
INSERT INTO `diary` VALUES (321, '2020-03-10 12:43:47', 1);
INSERT INTO `diary` VALUES (322, '2020-03-10 12:46:16', 1);
INSERT INTO `diary` VALUES (323, '2020-03-10 12:53:29', 1);
INSERT INTO `diary` VALUES (324, '2020-03-10 12:56:54', 1);
INSERT INTO `diary` VALUES (325, '2020-03-10 13:01:00', 1);
INSERT INTO `diary` VALUES (326, '2020-03-10 13:03:32', 1);
INSERT INTO `diary` VALUES (327, '2020-03-10 13:07:23', 1);
INSERT INTO `diary` VALUES (328, '2020-03-10 13:08:55', 1);
INSERT INTO `diary` VALUES (329, '2020-03-10 20:37:58', 1);
INSERT INTO `diary` VALUES (330, '2020-03-12 15:45:04', 1);
INSERT INTO `diary` VALUES (331, '2020-03-12 15:53:32', 1);
INSERT INTO `diary` VALUES (332, '2020-03-12 15:55:25', 1);
INSERT INTO `diary` VALUES (333, '2020-03-12 15:58:24', 1);
INSERT INTO `diary` VALUES (334, '2020-03-12 16:06:59', 1);
INSERT INTO `diary` VALUES (335, '2020-03-12 16:07:54', 1);
INSERT INTO `diary` VALUES (336, '2020-03-12 16:35:52', 1);
INSERT INTO `diary` VALUES (337, '2020-03-12 16:37:29', 1);
INSERT INTO `diary` VALUES (338, '2020-03-12 16:38:05', 1);
INSERT INTO `diary` VALUES (339, '2020-03-12 16:38:51', 1);
INSERT INTO `diary` VALUES (340, '2020-03-12 16:39:57', 1);
INSERT INTO `diary` VALUES (341, '2020-03-12 16:41:58', 1);
INSERT INTO `diary` VALUES (342, '2020-03-12 16:42:40', 1);
INSERT INTO `diary` VALUES (343, '2020-03-12 16:45:29', 1);
INSERT INTO `diary` VALUES (344, '2020-03-12 16:47:40', 1);
INSERT INTO `diary` VALUES (345, '2020-03-12 16:48:41', 1);
INSERT INTO `diary` VALUES (346, '2020-03-12 17:03:12', 1);
INSERT INTO `diary` VALUES (347, '2020-03-12 17:06:59', 1);
INSERT INTO `diary` VALUES (348, '2020-03-12 17:07:20', 1);
INSERT INTO `diary` VALUES (349, '2020-03-12 17:07:23', 1);
INSERT INTO `diary` VALUES (350, '2020-03-12 18:06:49', 1);
INSERT INTO `diary` VALUES (351, '2020-03-12 18:28:25', 1);
INSERT INTO `diary` VALUES (352, '2020-03-12 18:34:11', 1);
INSERT INTO `diary` VALUES (353, '2020-03-12 18:37:40', 1);
INSERT INTO `diary` VALUES (354, '2020-03-12 18:39:33', 1);
INSERT INTO `diary` VALUES (355, '2020-03-12 18:43:06', 1);
INSERT INTO `diary` VALUES (356, '2020-03-12 18:51:43', 1);
INSERT INTO `diary` VALUES (357, '2020-03-12 18:54:47', 1);
INSERT INTO `diary` VALUES (358, '2020-03-12 18:57:31', 1);
INSERT INTO `diary` VALUES (359, '2020-03-12 18:59:26', 1);
INSERT INTO `diary` VALUES (360, '2020-03-12 19:34:49', 1);
INSERT INTO `diary` VALUES (361, '2020-03-12 19:35:21', 1);
INSERT INTO `diary` VALUES (362, '2020-03-20 17:23:16', 1);
INSERT INTO `diary` VALUES (363, '2020-03-27 08:52:48', 1);
INSERT INTO `diary` VALUES (364, '2020-03-27 09:23:31', 1);
INSERT INTO `diary` VALUES (365, '2020-03-27 09:26:20', 1);
INSERT INTO `diary` VALUES (366, '2020-03-28 09:47:27', 1);
INSERT INTO `diary` VALUES (367, '2020-03-30 15:51:00', 1);
INSERT INTO `diary` VALUES (368, '2020-03-31 13:08:55', 1);
INSERT INTO `diary` VALUES (369, '2020-03-31 13:28:52', 1);
INSERT INTO `diary` VALUES (370, '2020-03-31 13:30:19', 1);
INSERT INTO `diary` VALUES (371, '2020-03-31 13:50:59', 1);
INSERT INTO `diary` VALUES (372, '2020-03-31 13:56:07', 1);
INSERT INTO `diary` VALUES (373, '2020-03-31 14:04:26', 1);
INSERT INTO `diary` VALUES (374, '2020-03-31 14:07:21', 1);
INSERT INTO `diary` VALUES (375, '2020-03-31 14:08:43', 1);
INSERT INTO `diary` VALUES (376, '2020-03-31 14:11:00', 1);
INSERT INTO `diary` VALUES (377, '2020-03-31 14:11:46', 1);
INSERT INTO `diary` VALUES (378, '2020-03-31 14:16:30', 1);
INSERT INTO `diary` VALUES (379, '2020-03-31 14:50:40', 1);
INSERT INTO `diary` VALUES (380, '2020-04-04 12:20:19', 1);
INSERT INTO `diary` VALUES (381, '2020-04-04 12:22:29', 1);
INSERT INTO `diary` VALUES (382, '2020-04-04 12:23:59', 1);
INSERT INTO `diary` VALUES (383, '2020-04-04 12:25:09', 1);
INSERT INTO `diary` VALUES (384, '2020-04-04 12:25:53', 1);
INSERT INTO `diary` VALUES (385, '2020-04-04 12:26:42', 1);
INSERT INTO `diary` VALUES (386, '2020-04-04 12:30:51', 1);
INSERT INTO `diary` VALUES (387, '2020-04-04 12:31:48', 1);
INSERT INTO `diary` VALUES (388, '2020-04-04 12:33:47', 1);
INSERT INTO `diary` VALUES (389, '2020-04-05 23:24:27', 1);
INSERT INTO `diary` VALUES (390, '2020-04-05 23:47:26', 1);
INSERT INTO `diary` VALUES (391, '2020-04-05 23:47:33', 1);
INSERT INTO `diary` VALUES (392, '2020-04-05 23:48:11', 1);
INSERT INTO `diary` VALUES (393, '2020-04-06 14:00:56', 1);
INSERT INTO `diary` VALUES (394, '2020-04-06 14:02:15', 1);
INSERT INTO `diary` VALUES (395, '2020-04-06 14:25:12', 1);
INSERT INTO `diary` VALUES (396, '2020-04-06 14:27:39', 1);
INSERT INTO `diary` VALUES (397, '2020-04-06 14:50:58', 1);
INSERT INTO `diary` VALUES (398, '2020-04-07 11:37:54', 1);
INSERT INTO `diary` VALUES (399, '2020-04-07 11:57:10', 1);
INSERT INTO `diary` VALUES (400, '2020-04-07 12:02:18', 1);
INSERT INTO `diary` VALUES (401, '2020-04-07 12:07:47', 1);
INSERT INTO `diary` VALUES (402, '2020-04-07 12:10:07', 1);
INSERT INTO `diary` VALUES (403, '2020-04-07 12:13:34', 1);
INSERT INTO `diary` VALUES (404, '2020-04-08 11:38:13', 1);
INSERT INTO `diary` VALUES (405, '2020-04-09 18:01:07', 1);
INSERT INTO `diary` VALUES (406, '2020-04-09 18:50:50', 1);
INSERT INTO `diary` VALUES (407, '2020-04-10 09:18:50', 1);
INSERT INTO `diary` VALUES (408, '2020-04-10 09:25:52', 1);
INSERT INTO `diary` VALUES (409, '2020-05-14 09:49:50', 1);
INSERT INTO `diary` VALUES (410, '2020-05-17 11:07:02', 1);
INSERT INTO `diary` VALUES (411, '2020-05-18 16:21:34', 1);
INSERT INTO `diary` VALUES (412, '2020-07-23 12:44:15', 1);
INSERT INTO `diary` VALUES (413, '2020-07-28 11:37:09', 1);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `employee_id` int NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `entry_time` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `quit_time` datetime(0) NULL DEFAULT NULL COMMENT '离职时间',
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `salary` decimal(60, 0) NULL DEFAULT NULL COMMENT '薪水',
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '汪宏坤', '男', 21, '2020-01-18 08:27:27', NULL, '职位', 1500);
INSERT INTO `employee` VALUES (2, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (3, '汪宏坤', '男', 21, '2020-01-18 08:27:27', NULL, '职位', 1500);
INSERT INTO `employee` VALUES (4, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (5, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2001);
INSERT INTO `employee` VALUES (7, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (8, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (9, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (10, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (11, 'whk', '男', 21, '2020-03-02 22:08:57', NULL, '实习', 2000);
INSERT INTO `employee` VALUES (12, '汪宏坤', '男', 21, '2020-01-18 08:27:27', NULL, '职位', 1500);
INSERT INTO `employee` VALUES (13, '汪宏坤', '男', 23, '2020-03-26 20:38:51', NULL, '初级工程师', 3000);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`accountnumber`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('Doe', 'Ja', '2020-07-18 13:05:24');
INSERT INTO `manager` VALUES ('Doe', 'Jane', '2020-07-18 11:52:35');
INSERT INTO `manager` VALUES ('whk123', 'manager', '2020-03-20 19:08:21');

-- ----------------------------
-- Table structure for memorandum
-- ----------------------------
DROP TABLE IF EXISTS `memorandum`;
CREATE TABLE `memorandum`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `belong_date` datetime(0) NULL DEFAULT NULL COMMENT '所属日期',
  `updata_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of memorandum
-- ----------------------------
INSERT INTO `memorandum` VALUES (1, 'wanghongkun', '主题', '内容', '2020-02-04 00:00:00', '2020-02-07 00:00:00');
INSERT INTO `memorandum` VALUES (6, 'wanghongkun', '标题', '内容', '2020-02-28 00:00:00', '2020-02-29 15:23:22');
INSERT INTO `memorandum` VALUES (11, 'wanghongkun', 'test', 'qwe', '2020-02-05 00:00:00', '2020-03-01 16:51:16');
INSERT INTO `memorandum` VALUES (12, 'wanghongkun', 'test', 'qwe', '2020-02-01 00:00:00', '2020-03-01 16:51:19');
INSERT INTO `memorandum` VALUES (13, 'wanghongkun', 'test', 'qwe', '2020-02-06 00:00:00', '2020-03-01 16:53:41');
INSERT INTO `memorandum` VALUES (15, 'wanghongkun', '', '企鹅', '2020-03-05 00:00:00', '2020-03-01 17:39:27');
INSERT INTO `memorandum` VALUES (16, 'wanghongkun', 'testfasdasfwe', 'se', '2020-03-07 00:00:00', '2020-03-01 17:19:02');
INSERT INTO `memorandum` VALUES (17, 'wanghongkun', '', 'eqasd', '2020-03-06 00:00:00', '2020-03-01 17:43:00');
INSERT INTO `memorandum` VALUES (18, 'wanghongkun', 'test', 'qa', '2020-05-12 00:00:00', '2020-05-17 11:08:05');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '备忘录id',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者账户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mission_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `accountnumber_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'manager', '2020-03-11 09:05:55', '你定义的字段，我这里就是topicId这个字段定义成了int类型，但是在这个参数定义不是非必须的（也就是你的某个方法定义了这个参数，但是这个参数是非必须的，系统会自动给这个字段赋值为null值，但是int类型不能接收null值）', 'whk');
INSERT INTO `notice` VALUES (3, 'manager', '2020-03-25 09:06:03', '再次修改', 'whk');
INSERT INTO `notice` VALUES (5, 'manager', '2020-03-28 09:35:11', ' 测试', '汪宏坤');

-- ----------------------------
-- Table structure for offworkapplication
-- ----------------------------
DROP TABLE IF EXISTS `offworkapplication`;
CREATE TABLE `offworkapplication`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `employee_id` int NULL DEFAULT NULL COMMENT '员工id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `application_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请类型（病假 事假 带薪假 补签）',
  `application_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请原因',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束世间',
  `review_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `review_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核结果(1:同意 2:不同意）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of offworkapplication
-- ----------------------------
INSERT INTO `offworkapplication` VALUES (1, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (2, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (3, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (4, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (5, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (6, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (7, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', '2020-03-28 16:57:23', '同意');
INSERT INTO `offworkapplication` VALUES (8, 1, '2020-03-06 21:26:04', '病假', 'string', '2020-03-07 12:26:17', '2020-03-05 12:26:17', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (9, 1, '2020-03-09 22:24:30', '病假', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `offworkapplication` VALUES (10, NULL, '2020-03-09 22:35:48', '事假', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `offworkapplication` VALUES (11, 1, '2020-03-09 22:39:54', '病假', 'test', '2020-03-08 22:39:29', '2020-03-09 22:39:32', NULL, NULL);
INSERT INTO `offworkapplication` VALUES (12, 1, '2020-04-04 12:44:56', '事假', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `offworkapplication` VALUES (13, 1, '2020-05-17 11:08:38', '事假', 'qwe', '2020-05-17 11:08:31', '2020-05-17 11:08:34', NULL, NULL);

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '签到id',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工账户',
  `signin_time` datetime(0) NULL DEFAULT NULL COMMENT '签到时间',
  `signout_time` datetime(0) NULL DEFAULT NULL COMMENT '签退时间',
  `sign_duration` time(0) NULL DEFAULT NULL COMMENT '签到时长',
  `extraduration` time(0) NULL DEFAULT NULL COMMENT '加班时间',
  `exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1:正常  2:异常 3:补签',
  `exception_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常详情',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES (1, 'wanghongkun', '2020-03-02 09:00:00', '2020-03-02 17:00:00', '09:00:00', '00:00:00', '1', '无异常');
INSERT INTO `sign` VALUES (2, 'whk', '2020-03-02 09:00:00', '2020-03-02 17:00:00', '09:00:00', '00:00:00', '1', '无异常');
INSERT INTO `sign` VALUES (4, 'whk', '2020-03-04 10:14:03', '2020-03-04 10:14:03', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (5, 'wanghongkun', '2020-03-05 10:47:02', '2020-03-05 10:47:02', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (50, 'wanghongkun', '2020-03-03 11:36:08', '2020-03-03 23:30:33', '11:54:25', '02:54:25', '1', '无异常');
INSERT INTO `sign` VALUES (51, 'wanghongkun', '2020-03-24 23:59:59', '2020-03-24 23:59:59', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (52, 'whk', '2020-03-24 23:59:59', '2020-03-24 23:59:59', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (53, 'wanghongkun', '2020-03-26 00:00:00', '2020-03-26 00:00:00', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (54, 'whk', '2020-03-26 00:00:00', '2020-03-26 00:00:00', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (55, 'wanghongkun', '2020-04-01 06:25:10', '2020-04-01 16:25:29', '09:00:00', '02:00:00', '1', '无异常');
INSERT INTO `sign` VALUES (58, 'wanghongkun', '2020-04-09 00:00:00', '2020-04-09 00:00:00', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');
INSERT INTO `sign` VALUES (59, 'whk', '2020-04-09 00:00:00', '2020-04-09 00:00:00', '00:00:00', '00:00:00', '2', '上班时长不够，还差09:00:00');

-- ----------------------------
-- Table structure for signdiary
-- ----------------------------
DROP TABLE IF EXISTS `signdiary`;
CREATE TABLE `signdiary`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '签到流水号',
  `accountnumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `sign_time` datetime(0) NOT NULL COMMENT '签到时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of signdiary
-- ----------------------------
INSERT INTO `signdiary` VALUES (1, 'wanghongkun', '2020-02-26 15:02:05');
INSERT INTO `signdiary` VALUES (2, 'wanghongkun', '2020-02-26 15:03:39');
INSERT INTO `signdiary` VALUES (3, 'wanghongkun', '2020-02-26 15:36:40');
INSERT INTO `signdiary` VALUES (4, 'wanghongkun', '2020-02-26 16:11:42');
INSERT INTO `signdiary` VALUES (5, 'wanghongkun', '2020-02-26 16:23:50');
INSERT INTO `signdiary` VALUES (6, 'wanghongkun', '2020-02-26 16:25:46');
INSERT INTO `signdiary` VALUES (7, 'wanghongkun', '2020-02-26 16:26:05');
INSERT INTO `signdiary` VALUES (8, 'wanghongkun', '2020-02-26 16:28:15');
INSERT INTO `signdiary` VALUES (9, 'wanghongkun', '2020-02-26 16:30:55');
INSERT INTO `signdiary` VALUES (10, 'wanghongkun', '2020-02-26 16:31:28');
INSERT INTO `signdiary` VALUES (11, 'whk', '2020-03-01 16:32:39');
INSERT INTO `signdiary` VALUES (12, 'wanghongkun', '2020-03-01 16:34:00');
INSERT INTO `signdiary` VALUES (13, 'wanghongkun', '2020-03-01 16:33:56');
INSERT INTO `signdiary` VALUES (14, 'whk', '2020-03-01 04:34:19');
INSERT INTO `signdiary` VALUES (15, 'wanghongkun', '2020-03-01 09:59:53');
INSERT INTO `signdiary` VALUES (16, 'wanghongkun', '2020-03-02 13:54:25');
INSERT INTO `signdiary` VALUES (17, 'wanghongkun', '2020-03-03 11:36:08');
INSERT INTO `signdiary` VALUES (18, 'wanghongkun', '2020-03-03 23:30:33');
INSERT INTO `signdiary` VALUES (19, 'wanghongkun', '2020-03-04 11:04:53');
INSERT INTO `signdiary` VALUES (20, 'wanghongkun', '2020-03-05 19:06:28');
INSERT INTO `signdiary` VALUES (21, 'wanghongkun', '2020-03-07 12:29:51');
INSERT INTO `signdiary` VALUES (22, 'wanghongkun', '2020-03-09 14:42:58');
INSERT INTO `signdiary` VALUES (23, 'wanghongkun', '2020-03-10 12:44:01');
INSERT INTO `signdiary` VALUES (24, 'wanghongkun', '2020-03-12 17:07:26');
INSERT INTO `signdiary` VALUES (25, 'wanghongkun', '2020-03-12 18:28:45');
INSERT INTO `signdiary` VALUES (26, 'wanghongkun', '2020-04-04 12:26:04');
INSERT INTO `signdiary` VALUES (27, 'wanghongkun', '2020-04-04 12:26:10');
INSERT INTO `signdiary` VALUES (28, 'wanghongkun', '2020-04-10 09:26:11');
INSERT INTO `signdiary` VALUES (29, 'wanghongkun', '2020-05-14 09:50:03');
INSERT INTO `signdiary` VALUES (30, 'wanghongkun', '2020-05-17 11:07:28');
INSERT INTO `signdiary` VALUES (31, 'wanghongkun', '2020-05-17 11:07:33');
INSERT INTO `signdiary` VALUES (32, 'wanghongkun', '2020-05-17 11:07:38');

SET FOREIGN_KEY_CHECKS = 1;
