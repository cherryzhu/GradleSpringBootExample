CREATE TABLE `users` (
  `id` INT( 11 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  `name` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users`(name,email) VALUES ('aaa', 'a@a');
INSERT INTO `users`(name,email) VALUES ('bbb', 'b@b');
INSERT INTO `users`(name,email) VALUES ('ccc', 'c@c');
INSERT INTO `users`(name,email) VALUES ('ddd', 'd@d');
INSERT INTO `users`(name,email) VALUES ('eee', 'e@e');
INSERT INTO `users`(name,email) VALUES ('fff', 'f@f');
INSERT INTO `users`(name,email) VALUES ('KKK', 'K@K');