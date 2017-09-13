/*
Navicat MySQL Data Transfer

Source Server         : orcl
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : organicshoppingmall

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-09-13 12:54:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `addressinfo`
-- ----------------------------
DROP TABLE IF EXISTS `addressinfo`;
CREATE TABLE `addressinfo` (
  `addressId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) NOT NULL,
  `province` varchar(10) NOT NULL,
  `city` varchar(10) NOT NULL,
  `area` varchar(10) NOT NULL,
  `street` varchar(20) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FK2_UserInfo_userId` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of addressinfo
-- ----------------------------
INSERT INTO `addressinfo` VALUES ('3', '1', '北京', '北京市', '朝阳区', '朝阳区法院');
INSERT INTO `addressinfo` VALUES ('56', '1', '山东', '青岛市', '崂山区', '青岛大学-东院');
INSERT INTO `addressinfo` VALUES ('57', '44', '天津', '天津市', '和平区', '梅来梅去');

-- ----------------------------
-- Table structure for `cartinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cartinfo`;
CREATE TABLE `cartinfo` (
  `cartId` int(10) NOT NULL AUTO_INCREMENT,
  `productName` varchar(20) NOT NULL,
  `productImg` varchar(20) NOT NULL,
  `userId` int(10) NOT NULL,
  `productQuantity` varchar(20) NOT NULL,
  `productPrice` decimal(20,0) NOT NULL,
  PRIMARY KEY (`cartId`),
  KEY `userId` (`userId`),
  CONSTRAINT `FK1_UserInfo_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cartinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderId` int(10) NOT NULL AUTO_INCREMENT,
  `orderDate` varchar(20) NOT NULL,
  `orderPrice` float(10,0) NOT NULL,
  `orderStatusId` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `userId` int(10) NOT NULL,
  `addressId` int(10) NOT NULL,
  PRIMARY KEY (`orderId`),
  KEY `userId` (`userId`),
  KEY `FK_OrderStatusInfo_orderStatusId` (`orderStatusId`),
  KEY `FK_addressId_addressinfo` (`addressId`),
  CONSTRAINT `FK_OrderStatusInfo_orderStatusId` FOREIGN KEY (`orderStatusId`) REFERENCES `orderstatusinfo` (`orderStatusId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_addressId_addressinfo` FOREIGN KEY (`addressId`) REFERENCES `addressinfo` (`addressId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_userInfo_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=110056 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('110046', '2017.08.31', '499', '0', '1', '3');
INSERT INTO `orderinfo` VALUES ('110054', '2017.08.26', '345', '1', '30', '56');
INSERT INTO `orderinfo` VALUES ('110055', '2017.08.30', '244', '0', '33', '57');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderId` int(10) DEFAULT NULL,
  `productId` int(10) DEFAULT NULL,
  `productCount` int(10) DEFAULT NULL,
  KEY `FK_orderId_orderInfo` (`orderId`),
  KEY `FK_productId_productInfo` (`productId`),
  CONSTRAINT `FK_orderId_orderInfo` FOREIGN KEY (`orderId`) REFERENCES `orderinfo` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_productId_productInfo` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('110046', '13', '1');
INSERT INTO `orderitem` VALUES ('110046', '10', '5');
INSERT INTO `orderitem` VALUES ('110046', '22', '2');
INSERT INTO `orderitem` VALUES ('110054', '3', '5');
INSERT INTO `orderitem` VALUES ('110054', '20', '5');
INSERT INTO `orderitem` VALUES ('110055', '2', '4');
INSERT INTO `orderitem` VALUES ('110055', '17', '4');

-- ----------------------------
-- Table structure for `orderstatusinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderstatusinfo`;
CREATE TABLE `orderstatusinfo` (
  `orderStatusId` int(2) NOT NULL,
  `orderStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`orderStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderstatusinfo
-- ----------------------------
INSERT INTO `orderstatusinfo` VALUES ('0', '未发货');
INSERT INTO `orderstatusinfo` VALUES ('1', '已发货');
INSERT INTO `orderstatusinfo` VALUES ('2', '已签收');

-- ----------------------------
-- Table structure for `productimginfo`
-- ----------------------------
DROP TABLE IF EXISTS `productimginfo`;
CREATE TABLE `productimginfo` (
  `productId` int(10) NOT NULL,
  `productImg` varchar(20) DEFAULT NULL,
  KEY `FK_ProductImgInfo_productId` (`productId`),
  CONSTRAINT `FK_ProductImgInfo_productId` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productimginfo
-- ----------------------------
INSERT INTO `productimginfo` VALUES ('13', '4-1.jpg');
INSERT INTO `productimginfo` VALUES ('13', '4-2.jpg');
INSERT INTO `productimginfo` VALUES ('13', '4-3.jpg');
INSERT INTO `productimginfo` VALUES ('13', '4-4.jpg');
INSERT INTO `productimginfo` VALUES ('13', '4-5.jpg');
INSERT INTO `productimginfo` VALUES ('14', '25.jpg');
INSERT INTO `productimginfo` VALUES ('14', '25.jpg');
INSERT INTO `productimginfo` VALUES ('14', '25.jpg');
INSERT INTO `productimginfo` VALUES ('14', '25.jpg');
INSERT INTO `productimginfo` VALUES ('14', '25.jpg');
INSERT INTO `productimginfo` VALUES ('15', '22.jpg');
INSERT INTO `productimginfo` VALUES ('15', '22.jpg');
INSERT INTO `productimginfo` VALUES ('15', '22.jpg');
INSERT INTO `productimginfo` VALUES ('15', '22.jpg');
INSERT INTO `productimginfo` VALUES ('15', '22.jpg');
INSERT INTO `productimginfo` VALUES ('16', '19.jpg');
INSERT INTO `productimginfo` VALUES ('16', '19.jpg');
INSERT INTO `productimginfo` VALUES ('16', '19.jpg');
INSERT INTO `productimginfo` VALUES ('16', '19.jpg');
INSERT INTO `productimginfo` VALUES ('16', '19.jpg');
INSERT INTO `productimginfo` VALUES ('17', '26.jpg');
INSERT INTO `productimginfo` VALUES ('17', '26.jpg');
INSERT INTO `productimginfo` VALUES ('17', '26.jpg');
INSERT INTO `productimginfo` VALUES ('17', '26.jpg');
INSERT INTO `productimginfo` VALUES ('17', '26.jpg');
INSERT INTO `productimginfo` VALUES ('18', '27.jpg');
INSERT INTO `productimginfo` VALUES ('18', '27.jpg');
INSERT INTO `productimginfo` VALUES ('18', '27.jpg');
INSERT INTO `productimginfo` VALUES ('18', '27.jpg');
INSERT INTO `productimginfo` VALUES ('18', '27.jpg');
INSERT INTO `productimginfo` VALUES ('19', '28.jpg');
INSERT INTO `productimginfo` VALUES ('19', '28.jpg');
INSERT INTO `productimginfo` VALUES ('19', '28.jpg');
INSERT INTO `productimginfo` VALUES ('19', '28.jpg');
INSERT INTO `productimginfo` VALUES ('19', '28.jpg');
INSERT INTO `productimginfo` VALUES ('20', '29.jpg');
INSERT INTO `productimginfo` VALUES ('20', '29.jpg');
INSERT INTO `productimginfo` VALUES ('20', '29.jpg');
INSERT INTO `productimginfo` VALUES ('20', '29.jpg');
INSERT INTO `productimginfo` VALUES ('20', '29.jpg');
INSERT INTO `productimginfo` VALUES ('21', '30.jpg');
INSERT INTO `productimginfo` VALUES ('21', '30.jpg');
INSERT INTO `productimginfo` VALUES ('21', '30.jpg');
INSERT INTO `productimginfo` VALUES ('21', '30.jpg');
INSERT INTO `productimginfo` VALUES ('21', '30.jpg');
INSERT INTO `productimginfo` VALUES ('22', '31.jpg');
INSERT INTO `productimginfo` VALUES ('22', '31.jpg');
INSERT INTO `productimginfo` VALUES ('22', '31.jpg');
INSERT INTO `productimginfo` VALUES ('22', '31.jpg');
INSERT INTO `productimginfo` VALUES ('22', '31.jpg');
INSERT INTO `productimginfo` VALUES ('23', '32.jpg');
INSERT INTO `productimginfo` VALUES ('23', '32.jpg');
INSERT INTO `productimginfo` VALUES ('23', '32.jpg');
INSERT INTO `productimginfo` VALUES ('23', '32.jpg');
INSERT INTO `productimginfo` VALUES ('23', '32.jpg');
INSERT INTO `productimginfo` VALUES ('24', '33.jpg');
INSERT INTO `productimginfo` VALUES ('24', '33.jpg');
INSERT INTO `productimginfo` VALUES ('24', '33.jpg');
INSERT INTO `productimginfo` VALUES ('24', '33.jpg');
INSERT INTO `productimginfo` VALUES ('24', '33.jpg');
INSERT INTO `productimginfo` VALUES ('1', '1-2.jpg');
INSERT INTO `productimginfo` VALUES ('1', '1-3.jpg');
INSERT INTO `productimginfo` VALUES ('1', '1-4.jpg');
INSERT INTO `productimginfo` VALUES ('1', '1-5.jpg');
INSERT INTO `productimginfo` VALUES ('1', '1-1.jpg');
INSERT INTO `productimginfo` VALUES ('2', '2-2.jpg');
INSERT INTO `productimginfo` VALUES ('2', '2-3.jpg');
INSERT INTO `productimginfo` VALUES ('2', '2-4.jpg');
INSERT INTO `productimginfo` VALUES ('2', '2-5.jpg');
INSERT INTO `productimginfo` VALUES ('2', '2-1.jpg');
INSERT INTO `productimginfo` VALUES ('3', '3-2.jpg');
INSERT INTO `productimginfo` VALUES ('3', '3-3.jpg');
INSERT INTO `productimginfo` VALUES ('3', '3-4.jpg');
INSERT INTO `productimginfo` VALUES ('3', '3-5.jpg');
INSERT INTO `productimginfo` VALUES ('3', '3-1.jpg');
INSERT INTO `productimginfo` VALUES ('4', '9.jpg');
INSERT INTO `productimginfo` VALUES ('4', '9.jpg');
INSERT INTO `productimginfo` VALUES ('4', '9.jpg');
INSERT INTO `productimginfo` VALUES ('4', '9.jpg');
INSERT INTO `productimginfo` VALUES ('4', '9.jpg');
INSERT INTO `productimginfo` VALUES ('5', '12.jpg');
INSERT INTO `productimginfo` VALUES ('5', '12.jpg');
INSERT INTO `productimginfo` VALUES ('5', '12.jpg');
INSERT INTO `productimginfo` VALUES ('5', '12.jpg');
INSERT INTO `productimginfo` VALUES ('5', '12.jpg');
INSERT INTO `productimginfo` VALUES ('6', '7.jpg');
INSERT INTO `productimginfo` VALUES ('6', '7.jpg');
INSERT INTO `productimginfo` VALUES ('6', '7.jpg');
INSERT INTO `productimginfo` VALUES ('6', '7.jpg');
INSERT INTO `productimginfo` VALUES ('6', '7.jpg');
INSERT INTO `productimginfo` VALUES ('7', '11.jpg');
INSERT INTO `productimginfo` VALUES ('7', '11.jpg');
INSERT INTO `productimginfo` VALUES ('7', '11.jpg');
INSERT INTO `productimginfo` VALUES ('7', '11.jpg');
INSERT INTO `productimginfo` VALUES ('7', '11.jpg');
INSERT INTO `productimginfo` VALUES ('8', '20.jpg');
INSERT INTO `productimginfo` VALUES ('8', '20.jpg');
INSERT INTO `productimginfo` VALUES ('8', '20.jpg');
INSERT INTO `productimginfo` VALUES ('8', '20.jpg');
INSERT INTO `productimginfo` VALUES ('8', '20.jpg');
INSERT INTO `productimginfo` VALUES ('9', '10.jpg');
INSERT INTO `productimginfo` VALUES ('9', '10.jpg');
INSERT INTO `productimginfo` VALUES ('9', '10.jpg');
INSERT INTO `productimginfo` VALUES ('9', '10.jpg');
INSERT INTO `productimginfo` VALUES ('9', '10.jpg');
INSERT INTO `productimginfo` VALUES ('10', '18.jpg');
INSERT INTO `productimginfo` VALUES ('10', '18.jpg');
INSERT INTO `productimginfo` VALUES ('10', '18.jpg');
INSERT INTO `productimginfo` VALUES ('10', '18.jpg');
INSERT INTO `productimginfo` VALUES ('10', '18.jpg');
INSERT INTO `productimginfo` VALUES ('11', '16.jpg');
INSERT INTO `productimginfo` VALUES ('11', '16.jpg');
INSERT INTO `productimginfo` VALUES ('11', '16.jpg');
INSERT INTO `productimginfo` VALUES ('11', '16.jpg');
INSERT INTO `productimginfo` VALUES ('11', '16.jpg');
INSERT INTO `productimginfo` VALUES ('12', '17.jpg');
INSERT INTO `productimginfo` VALUES ('12', '17.jpg');
INSERT INTO `productimginfo` VALUES ('12', '17.jpg');
INSERT INTO `productimginfo` VALUES ('12', '17.jpg');
INSERT INTO `productimginfo` VALUES ('12', '17.jpg');

-- ----------------------------
-- Table structure for `productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo` (
  `productId` int(10) NOT NULL AUTO_INCREMENT,
  `productName` varchar(20) NOT NULL,
  `productPrice` float(10,2) NOT NULL,
  `productInventory` int(10) NOT NULL,
  `productDetail` varchar(200) NOT NULL,
  `productTypeId` int(10) NOT NULL,
  `defaultImg` varchar(20) NOT NULL,
  PRIMARY KEY (`productId`),
  KEY `FK_productTypeId` (`productTypeId`),
  CONSTRAINT `FK_productTypeId` FOREIGN KEY (`productTypeId`) REFERENCES `productypeinfo` (`productTypeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productinfo
-- ----------------------------
INSERT INTO `productinfo` VALUES ('1', '苹果', '1.00', '1', '苹果的形状是圆圆的，上下都凹下去一个个小口，看样子像一个气球。苹果的皮滑溜溜的，如果你在洗苹果的时候，它一不小心就会“逃跑”，并与你玩捉迷藏。苹果的颜色是红通通的，上面还有一颗两颗白点，那可是苹果成熟的标志。它既像弟弟那红扑扑的小脸蛋儿，又像一位害羞的小姑娘，那样子，看的你会忍不住咬它一口。苹果的味道是甜丝丝的，脆生生的，让人吃了还想吃，真是“吃在嘴里，甜在心里”。苹果的肉水灵灵，吃起来又香又甜，', '1', '5.jpg');
INSERT INTO `productinfo` VALUES ('2', '桃子', '10.00', '43', '桃子的形状是圆形的，上面还有一个小尖尖，全身长着细细的绒毛。没有熟透的桃子是青色的，你削开皮，就可以看见那白白的果肉，轻轻地咬上一口，就会感到又苦又酸;快熟了的桃子一半青，一半红，甜津津的;熟透了的桃子就别提了，你咬一口，一股清香又甜蜜的汁水一涌而出，那滋味我真是一辈子也忘不了。盛夏时节，吃一口桃子，你的疲劳就会一扫而光。', '1', '6.jpg');
INSERT INTO `productinfo` VALUES ('3', '菠萝', '32.00', '45', '菠萝含有一种叫“菠萝朊酶”的物质，它能分解蛋白质，帮助消化，溶解阻塞于组织中的纤维蛋白和血凝块，改善局部的血液循环，稀释血脂，消除炎症和水肿，能够促进血循环。尤其是过食肉类及油腻食物之后，吃些菠萝更为适宜，可以预防脂肪沉积。', '1', '14.jpg');
INSERT INTO `productinfo` VALUES ('4', '草莓', '45.00', '54', '草莓营养价值丰富，被誉为是“水果皇后”，含有丰富的维生素C、维生素A、维生素E、维生素PP、维生素B1、维生素B2、胡萝卜素、鞣酸、天冬氨酸、铜、草莓胺、果胶、纤维素、叶酸、铁、钙、鞣花酸与花青素等营养物质', '1', '9.jpg');
INSERT INTO `productinfo` VALUES ('5', '香梨', '22.00', '76', '花谢了,树上挂满了小青灯笼.整个夏季是梨子的生长时节.梨子先是青色的,小小的,圆圆的,挂在树枝上,在风雨中,一摇一摆的,怪诱人的.到四、五月份时,梨的个头渐渐的大了,差不有我小拳头大小了,这时颜色也变成淡青色.秋天刚到,梨的颜色就变成了黄红色,这时梨成熟了,最大的长到了一斤多,最小的也有半斤多了。', '1', '12.jpg');
INSERT INTO `productinfo` VALUES ('6', '柠檬', '20.00', '4', '又称香柠檬、美华柠檬，柠檬和橙或者柠檬和宽皮柑的杂种，1908年在北京近郊发现，带回美国继续选育而成。其酸低，略带苦味，内部结构柔软，抗性特佳，耐热耐寒，果皮薄多汁不耐运贮。', '1', '7.jpg');
INSERT INTO `productinfo` VALUES ('7', '橙子', '8.00', '34', '糖橙又称无酸甜橙，果形与普通甜橙相似。因含酸量极低，果汁含量达到适当程度时即可采收、上市，是极早熟的甜橙品种。在地中海沿岸和巴西等地有少量生产，供应地方市场。', '1', '11.jpg');
INSERT INTO `productinfo` VALUES ('8', '油桃', '22.00', '76', '平均单果重206克，大果297克，是果形最大的早熟黄肉油桃。果肉黄色，硬溶，风味甜，可溶性固形物12%，粘核。金硕为墙微花（大花型），需冷量550小时，自花结实。在重庆地区果实6月10日左右成熟，果实发育期85天，比中油4号晚熟8～10天。', '1', '20.jpg');
INSERT INTO `productinfo` VALUES ('9', '芹菜', '33.00', '46', '芹菜富含蛋白质、碳水化合物、胡萝卜素、B族维生素、钙、磷、铁、钠等，同时，具有有平肝清热，祛风利湿，除烦消肿，凉血止血，解毒宣肺，健胃利血、清肠利便、润肺止咳、降低血压、健脑镇静的功效。常吃芹菜，尤其是吃芹菜叶，对预防高血压、动脉硬化等都十分有益，并有辅助治疗作用。', '2', '10.jpg');
INSERT INTO `productinfo` VALUES ('10', '西蓝花', '44.00', '56', '西蓝花中的营养成分，不仅含量高，而且十分全面，主要包括蛋白质、碳水化合物、脂肪、矿物质、维生素C和胡萝卜素等。据分析，每100克新鲜西蓝花的花球中，含蛋白质3.5克—4.5克，是菜花的3倍、番茄的4倍。此外，西蓝花中矿物质成分比其他蔬菜更全面，钙、磷、铁、钾、锌、锰等含量都很丰富，比同属于十字花科的白菜花高出很多。', '2', '18.jpg');
INSERT INTO `productinfo` VALUES ('11', '卷心菜', '23.00', '45', '卷心菜因有许多药用功效而备受推崇，希腊人和罗马人将它视为万能药，卷心菜有绿色、白色、红色等不同颜色，卷心菜里面的叶子比外面的叶子略白些。卷心菜重量通常从0.9～3千克不等，直径在10~20厘米不等。', '2', '16.jpg');
INSERT INTO `productinfo` VALUES ('12', '红辣椒', '11.00', '67', '从南到北，随着川菜和麻辣火锅在各地的流行，吃辣椒的人越来越多。不少年轻人几乎顿顿离不开辣椒，这除了喜欢辣味的感官刺激外，还有人认为辣椒能阻止人长胖，理由是习惯吃辣的川妹子个个身材苗条。有关专家解答，从科学角度看，过度食用辣椒并非对人体有利。', '2', '17.jpg');
INSERT INTO `productinfo` VALUES ('13', '西红柿', '212.00', '54', '现在是西红柿的结果时期。树上的西红柿像大灯笼似的。有的西红柿呈绿色，躲在绿叶间，把头稍微地探出了些。有的西红柿还只是与我的大指姆一般大小，在风中翩翩起舞，美极了！', '2', '13.jpg');
INSERT INTO `productinfo` VALUES ('14', '玉米', '11.00', '56', '2131', '2', '25.jpg');
INSERT INTO `productinfo` VALUES ('15', '豆荚', '23.00', '85', '332', '2', '22.jpg');
INSERT INTO `productinfo` VALUES ('16', '胡萝卜', '23.00', '34', '232', '2', '19.jpg');
INSERT INTO `productinfo` VALUES ('17', '厄瓜多尔白虾', '221.00', '54', '212', '3', '26.jpg');
INSERT INTO `productinfo` VALUES ('18', '智利三文鱼柳', '333.00', '65', '332', '3', '27.jpg');
INSERT INTO `productinfo` VALUES ('19', '黑鳕切身', '223.00', '13', '323', '3', '28.jpg');
INSERT INTO `productinfo` VALUES ('20', '科尔沁牛排', '232.00', '12', '324', '3', '29.jpg');
INSERT INTO `productinfo` VALUES ('21', '猪肉月牙骨', '3.00', '9', '3232', '3', '30.jpg');
INSERT INTO `productinfo` VALUES ('22', '阿根廷鱿鱼', '243.00', '12', '234654', '3', '31.jpg');
INSERT INTO `productinfo` VALUES ('23', '野生海参', '2345.00', '45', '32343', '3', '32.jpg');
INSERT INTO `productinfo` VALUES ('24', '澳洲羊排', '343.00', '9', '2334', '3', '33.jpg');

-- ----------------------------
-- Table structure for `productypeinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productypeinfo`;
CREATE TABLE `productypeinfo` (
  `productTypeId` int(10) NOT NULL,
  `productType` varchar(20) NOT NULL,
  PRIMARY KEY (`productTypeId`),
  KEY `productId` (`productTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productypeinfo
-- ----------------------------
INSERT INTO `productypeinfo` VALUES ('1', '鲜果');
INSERT INTO `productypeinfo` VALUES ('2', '蔬菜');
INSERT INTO `productypeinfo` VALUES ('3', '生鲜');
INSERT INTO `productypeinfo` VALUES ('4', '好吃的');
INSERT INTO `productypeinfo` VALUES ('5', '屎');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `userPass` varchar(100) NOT NULL,
  `email` varchar(20) NOT NULL,
  `phoneNumber` varchar(20) NOT NULL,
  `userStatus` varchar(10) NOT NULL DEFAULT '未锁定',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'zhangsan', 'c4ca4238a0b923820dcc509a6f75849b', '61458789@qq.com', '15678945600', '未锁定');
INSERT INTO `userinfo` VALUES ('30', '打发士大夫', 'e3ceb5881a0a1fdaad01296d7554868d', '175715862@qq.com', '22589589933', '已锁定');
INSERT INTO `userinfo` VALUES ('31', 'cty', '7cbfaaca94f5c0f73eea3cb717ce6676', '666@qq.com', '17854238406', '未锁定');
INSERT INTO `userinfo` VALUES ('33', '赵四儿', 'c4ca4238a0b923820dcc509a6f75849b', '1362@qq.com', '23425678900', '未锁定');
INSERT INTO `userinfo` VALUES ('34', 'admin', '21232f297a57a5a743894a0e4a801fc3', '', '', '未锁定');
INSERT INTO `userinfo` VALUES ('36', 'chen ', 'a1a8887793acfc199182a649e905daab', '1234345@qq.com', '1234567890', '已锁定');
INSERT INTO `userinfo` VALUES ('37', 'dqw', '202cb962ac59075b964b07152d234b70', '547865442@qq.com', '15487889969', '未锁定');
INSERT INTO `userinfo` VALUES ('39', 'dqww', '25f9e794323b453885f5181f1b624d0b', '456@qq.com', '12345678912', '未锁定');
INSERT INTO `userinfo` VALUES ('40', '董棋武', 'c7e7bebd73a4eb2dd8ebaa018b141019', '4567@qq.com', '12345678917', '未锁定');
INSERT INTO `userinfo` VALUES ('60', 'slj', 'c4ca4238a0b923820dcc509a6f75849b', '1175715862@qq.com', '12345678900', '未锁定');
