/*
 Navicat Premium Data Transfer

 Source Server         : 233
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 02/07/2024 15:45:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `author` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_book_id_title`(`id`, `title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (27, '百年孤独', '加西亚·马尔克斯', 1);
INSERT INTO `books` VALUES (28, '追风筝的人', '卡勒德·胡赛尼', 1);
INSERT INTO `books` VALUES (29, '三体', '刘慈欣', 1);
INSERT INTO `books` VALUES (30, '解忧杂货店', '东野圭吾', 1);
INSERT INTO `books` VALUES (31, '活着', '余华', 1);
INSERT INTO `books` VALUES (32, '小王子', '安托万·德·圣·埃克苏佩里', 1);
INSERT INTO `books` VALUES (33, '1984', '乔治·奥威尔', 1);
INSERT INTO `books` VALUES (34, '动物庄园', '乔治·奥威尔', 1);
INSERT INTO `books` VALUES (35, '了不起的盖茨比', '弗朗西斯·斯科特·基·菲茨杰拉德', 1);
INSERT INTO `books` VALUES (36, '麦田里的守望者', 'J.D.塞林格', 1);
INSERT INTO `books` VALUES (37, '杀死一只知更鸟', '哈珀·李', 1);
INSERT INTO `books` VALUES (38, '不能承受的生命之轻', '米兰·昆德拉', 1);
INSERT INTO `books` VALUES (39, '霍乱时期的爱情', '加西亚·马尔克斯', 1);
INSERT INTO `books` VALUES (40, '挪威的森林', '村上春树', 1);
INSERT INTO `books` VALUES (41, '海边的卡夫卡', '村上春树', 1);
INSERT INTO `books` VALUES (42, '了不起的盖茨比', '弗朗西斯·斯科特·菲茨杰拉德', 1);
INSERT INTO `books` VALUES (43, '时间简史', '斯蒂芬·霍金', 1);
INSERT INTO `books` VALUES (44, '人类简史', '尤瓦尔·赫拉利', 1);
INSERT INTO `books` VALUES (45, '未来简史', '尤瓦尔·赫拉利', 1);
INSERT INTO `books` VALUES (46, '枪炮、病菌与钢铁', '贾雷德·戴蒙德', 1);
INSERT INTO `books` VALUES (47, '自私的基因', '理查德·道金斯', 1);
INSERT INTO `books` VALUES (48, '思考，快与慢', '丹尼尔·卡尼曼', 1);
INSERT INTO `books` VALUES (49, '乌合之众', '古斯塔夫·勒庞', 1);
INSERT INTO `books` VALUES (50, '国富论', '亚当·斯密', 1);
INSERT INTO `books` VALUES (51, '梦的解析', '西格蒙德·弗洛伊德', 1);
INSERT INTO `books` VALUES (52, '第二性', '西蒙·德·波伏娃', 1);
INSERT INTO `books` VALUES (53, '东方快车谋杀案', '阿加莎·克里斯蒂', 1);
INSERT INTO `books` VALUES (54, '无人生还', '阿加莎·克里斯蒂', 1);
INSERT INTO `books` VALUES (55, '福尔摩斯探案全集', '阿瑟·柯南·道尔', 1);
INSERT INTO `books` VALUES (56, '基督山伯爵', '大仲马', 1);
INSERT INTO `books` VALUES (57, '悲惨世界', '维克多·雨果', 1);
INSERT INTO `books` VALUES (58, '巴黎圣母院', '维克多·雨果', 1);
INSERT INTO `books` VALUES (59, '红与黑', '司汤达', 1);
INSERT INTO `books` VALUES (60, '约翰·克里斯朵夫', '罗曼·罗兰', 1);
INSERT INTO `books` VALUES (61, '钢铁是怎样炼成的', '尼古拉·奥斯特洛夫斯基', 1);
INSERT INTO `books` VALUES (62, '静静的顿河', '米哈伊尔·肖洛霍夫', 1);
INSERT INTO `books` VALUES (63, '罪与罚', '费奥多尔·陀思妥耶夫斯基', 1);
INSERT INTO `books` VALUES (64, '卡拉马佐夫兄弟', '费奥多尔·陀思妥耶夫斯基', 1);
INSERT INTO `books` VALUES (65, '安娜·卡列尼娜', '列夫·托尔斯泰', 1);
INSERT INTO `books` VALUES (66, '战争与和平', '列夫·托尔斯泰', 1);
INSERT INTO `books` VALUES (67, '复活', '列夫·托尔斯泰', 1);
INSERT INTO `books` VALUES (68, '包法利夫人', '居斯塔夫·福楼拜', 1);
INSERT INTO `books` VALUES (69, '红楼梦', '曹雪芹', 1);
INSERT INTO `books` VALUES (70, '西游记', '吴承恩', 1);
INSERT INTO `books` VALUES (71, '水浒传', '施耐庵', 1);
INSERT INTO `books` VALUES (72, '三国演义', '罗贯中', 1);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `book_id` int(0) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `borrow_date` date NULL DEFAULT NULL,
  `return_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `book_id`(`book_id`) USING BTREE,
  INDEX `borrow_ibfk_3`(`book_id`, `title`) USING BTREE,
  CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `borrow_ibfk_3` FOREIGN KEY (`book_id`, `title`) REFERENCES `books` (`id`, `title`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES (13, 'admin', 67, '复活', '2031-12-09', '2031-12-23');
INSERT INTO `borrow` VALUES (14, '小王', 33, '1984', '2031-12-09', '2031-12-23');
INSERT INTO `borrow` VALUES (15, '小李', 35, '了不起的盖茨比', '2031-12-09', '2031-12-23');
INSERT INTO `borrow` VALUES (16, '小王', 27, '百年孤独', '2023-12-12', '2023-12-26');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '小王', 'admin');
INSERT INTO `users` VALUES (2, '小李', 'admin');
INSERT INTO `users` VALUES (3, 'admin', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
