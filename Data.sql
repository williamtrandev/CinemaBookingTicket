-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admins_roles`
--

DROP TABLE IF EXISTS `admins_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins_roles` (
  `admin_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKlgn5xtde3ysro9ydng18ym1yi` (`role_id`),
  KEY `FK20kja6hqqbe0t9gqgamyc68p9` (`admin_id`),
  CONSTRAINT `FK20kja6hqqbe0t9gqgamyc68p9` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`),
  CONSTRAINT `FKlgn5xtde3ysro9ydng18ym1yi` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins_roles`
--

LOCK TABLES `admins_roles` WRITE;
/*!40000 ALTER TABLE `admins_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `banner_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,'banner1.jpg'),(2,'banner2.jpg'),(3,'banner3.jpg'),(4,'banner4.jpg');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo`
--

DROP TABLE IF EXISTS `combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo` (
  `combo_id` bigint NOT NULL AUTO_INCREMENT,
  `deleted` tinyint DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name_combo` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`combo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_history`
--

DROP TABLE IF EXISTS `combo_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo_history` (
  `combo_history_id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `combo_id` bigint DEFAULT NULL,
  `history_id` bigint DEFAULT NULL,
  PRIMARY KEY (`combo_history_id`),
  KEY `FK84wuc46raioec8fd0vio1x518` (`combo_id`),
  KEY `FKlkh1i0quk1euyo4vkp5yxt1vs` (`history_id`),
  CONSTRAINT `FK84wuc46raioec8fd0vio1x518` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`combo_id`),
  CONSTRAINT `FKlkh1i0quk1euyo4vkp5yxt1vs` FOREIGN KEY (`history_id`) REFERENCES `history` (`history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_history`
--

LOCK TABLES `combo_history` WRITE;
/*!40000 ALTER TABLE `combo_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `combo_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `avatar_path` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `point` int NOT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `UKdwk6cx0afu8bs9o4t536v1j5v` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `genre_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Hoạt hình'),(2,'Kinh dị'),(3,'Hành động');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `history_id` bigint NOT NULL AUTO_INCREMENT,
  `date_booking` date DEFAULT NULL,
  `total` double NOT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`history_id`),
  KEY `FKjbek4jc1j9ngptsyoud1pbu2f` (`customer_id`),
  CONSTRAINT `FKjbek4jc1j9ngptsyoud1pbu2f` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_detail`
--

DROP TABLE IF EXISTS `history_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_detail` (
  `history_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `history_id` bigint DEFAULT NULL,
  `seat_id` bigint DEFAULT NULL,
  `showtime_id` bigint DEFAULT NULL,
  PRIMARY KEY (`history_detail_id`),
  KEY `FKhiai0oxnaoke4g6505ipnrhk2` (`history_id`),
  KEY `FKbhw5iqk73y6y7w5jgybdttf2e` (`seat_id`),
  KEY `FKgto6otdap5ma6shedlgyoukuw` (`showtime_id`),
  CONSTRAINT `FKbhw5iqk73y6y7w5jgybdttf2e` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`),
  CONSTRAINT `FKgto6otdap5ma6shedlgyoukuw` FOREIGN KEY (`showtime_id`) REFERENCES `show_time` (`showtime_id`),
  CONSTRAINT `FKhiai0oxnaoke4g6505ipnrhk2` FOREIGN KEY (`history_id`) REFERENCES `history` (`history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_detail`
--

LOCK TABLES `history_detail` WRITE;
/*!40000 ALTER TABLE `history_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `history_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` bigint NOT NULL AUTO_INCREMENT,
  `actors` varchar(255) DEFAULT NULL,
  `is_coming` tinyint DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `duration` int NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `trailer_path` varchar(255) DEFAULT NULL,
  `tag_id` bigint DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `FKo5fp4p468qy46f99qu32pxlr5` (`tag_id`),
  CONSTRAINT `FKo5fp4p468qy46f99qu32pxlr5` FOREIGN KEY (`tag_id`) REFERENCES `tag_movie` (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Lê Khánh, Hứa Vĩ Văn, Hoàng Oanh, Quang Tuấn, Võ Tấn Phát, Nguyên Thảo, Ngọc Phước, Ngọc Hoa, Lạc Hoàng Long',0,0,'BIỆT ĐỘI RẤT ỔN xoay quanh bộ đôi Quyên (Hoàng Oanh) và Phong (Hứa Vĩ Văn). Sau lần chạm trán tình cờ, bộ đôi lôi kéo Bảy Cục (Võ Tấn Phát), Bảy Súc (Nguyên Thảo), Quạu (Ngọc Phước), Quọ (Ngọc Hoa) tham gia vào phi vụ đặc biệt: Đánh tráo chiếc vòng đính hôn bằng kim cương quý giá và lật tẩy bộ mặt thật của Tuấn - chồng cũ của Quyên trong đám cưới giữa hắn và Tư Xoàn - nữ đại gia miền Tây sở hữu khối tài sản triệu đô. Màn kết hợp bất đắc dĩ của Biệt Đội Rất Ổn - Phong, Quyên và Gia Đình Cục Súc nhằm qua mắt “cô dâu, chú rể” tại khu resort sang chảnh hứa hẹn cực kỳ gay cấn, hồi hộp nhưng không kém phần hài hước, xúc động.','Tạ Nguyên Hiệp',104,'biet-doi-rat-on.jpg','BIỆT ĐỘI RẤT ỔN','2023-03-31','https://www.youtube.com/embed/2lD5pel1GsQ',2),(2,'Michael B. Jordan, Tessa Thompson, Jonathan Majors, Wood Harris, Phylicia Rashad, Mila Davis-Kent, Jose Benavidez, Selenis Leyva, Florian Munteanu',0,0,'Sau khi thống trị thế giới quyền anh, Adonis Creed đã phát triển mạnh mẽ trong cả sự nghiệp và cuộc sống gia đình. Khi một người bạn thời thơ ấu và cựu thần đồng quyền anh, Damian (Jonathan Majors), tái xuất sau khi thụ án tù dài hạn, anh ta háo hức chứng minh rằng mình xứng đáng được trở lại võ đài. Cuộc chạm trán giữa những người bạn cũ không chỉ là một cuộc chiến trên võ đài thông thường. Để có thể chiến thắng, Adonis phải đặt tương lai của mình lên trên hết để chiến đấu với Damian — một võ sĩ không còn gì để mất.','Michael B. Jordan',117,'creed-3-tay-dam-huyen-thoai.jpg','CREED III: TAY ĐẤM HUYỀN THOẠI','2023-03-24','https://www.youtube.com/embed/ir0lrq1IBtw',2),(3,'Kim Da-mi, Woo-Seok Byeon, Nam Yoon-Su, So-nee Jeon, Kim Soo Hyung',0,0,'Soulmate (tựa Việt: Tri Kỷ) là câu chuyện về Mi So (Kim Da Mi thủ vai) và Ha Eun (Jeon So Nee thủ vai) trong một mối quan hệ chồng chéo chất chứa những hạnh phúc, nỗi buồn, rung động và cả biệt ly. Từ giây phút đầu tiên gặp nhau dưới mái trường tiểu học, giữa hai cô gái đã hình thành một sợi dây liên kết đặc biệt. Và khi Ham Jin Woo (Byun Woo Seok thủ vai) bước vào giữa cả hai, đó cũng là lúc những vết nứt trong mối quan hệ của Mi So và Ha Eun xuất hiện.','Young-Keun Min',124,'tri-ky.jpg','TRI KỶ','2023-03-24','https://www.youtube.com/embed/tCC7hrheTR8',3),(4,'Dương Tử Quỳnh, Quan Kế Huy, Stephanie Hsu, James Hong, Jamie Lee Curtis,...',0,0,'Một phụ nữ trung niên nhập cư người Trung Quốc bị cuốn vào một cuộc phiêu lưu điên cuồng, nơi cô ấy một mình giải cứu thế giới bằng cách khám phá các vũ trụ khác và các bản thể khác của chính cô.','Daniel Kwan, Daniel Scheinert',139,'cuoc_chien_da_vu_tru.jpg','CUỘC CHIẾN ĐA VŨ TRỤ','2023-03-17','https://www.youtube.com/embed/4y5JUTzFlVs',4),(5,'',0,0,'\"Khóa Chặt Cửa Nào Suzume\" kể câu chuyện khi Suzume vô tình gặp một chàng trai trẻ đến thị trấn nơi cô sinh sống với mục đích tìm kiếm \"một cánh cửa\". Để bảo vệ Nhật Bản khỏi thảm họa, những cánh cửa rải rác khắp nơi phải được đóng lại, và bất ngờ thay Suzume cũng có khả năng đóng cửa đặc biệt này. Từ đó cả hai cùng nhau thực hiện sự mệnh \"khóa chặt cửa\"!','Makoto Shinkai',122,'KHÓA CHẶT CỬA NÀO SUZUME.jpg','KHÓA CHẶT CỬA NÀO SUZUME','2023-03-10','https://www.youtube.com/embed/w9HWe8zt64M',1),(6,'Anh Tú, Mạc Văn Khoa, Ngọc Phước, Nhất Trung, NSƯT Mỹ Duyên, Đại Nghĩa, Lâm Vỹ Dạ, BB Trần, Cát Tường, Hứa Minh Đạt…',0,0,'Thuộc phong cách hành động – hài hước với các “cú lừa” thông minh và lầy lội đến từ bộ đôi Tú (Anh Tú) và Khoa (Mạc Văn Khoa), Siêu Lừa Gặp Siêu Lầy của đạo diễn Võ Thanh Hòa theo chân của Khoa – tên lừa đảo tầm cỡ “quốc nội” đến đảo ngọc Phú Quốc với mong muốn đổi đời. Tại đây, Khoa gặp Tú – tay lừa đảo “hàng real” và cùng Tú thực hiện các phi vụ từ nhỏ đến lớn. Cứ ngỡ sự ranh mãnh của Tú và sự may mắn trời cho của Khoa sẽ giúp họ trở thành bộ đôi bất khả chiến bại, nào ngờ lại đối mặt với nhiều tình huống dở khóc – dở cười. Nhất là khi băng nhóm của bộ đôi nhanh chóng mở rộng vì sự góp mặt của ông Năm (Nhất Trung) và bé Mã Lai (Ngọc Phước).','Võ Thanh Hòa',112,'SIÊU LỪA GẶP SIÊU LẦY.jpg','SIÊU LỪA GẶP SIÊU LẦY','2023-03-03','https://www.youtube.com/embed/kdn0xrDf8tY',3),(7,'Thitiya Jirapornsilp, Anthony Buisseret,...',0,0,'Chuyện phim lấy bối cảnh năm 1999, khi thế giới đang chìm trong nỗi sợ hãi Y2K - sự cố khiến hệ thống máy tính sụp đổ và dự đoán có thể làm trái đất diệt vong khi bước sang năm 2000. Ngay lúc này, hai chị em sinh đôi giống hệt nhau là “You” và “Me” cũng đang lo lắng cho tương lai của họ về việc “làm sao sống mà có thể thiếu vắng nhau”. Cặp song sinh thân thiết với nhau đến mức có thể chia sẻ mọi khía cạnh cuộc sống cho nhau, kể cả nụ hôn đầu. Tình chị em tưởng chừng không thể rạn nứt cho đến khi chàng trai tên Mark chen vào giữa họ. Nếu ngay từ đầu, “lợi ích” của việc có ngoại hình giống hệt nhau được họ áp dụng để gian lận trong nhà hàng, rạp chiếu phim và trường học, thì sự xuất hiện của Mark khiến mọi thứ trở nên rối tung. Khi cặp song sinh phải đối mặt với “mối tình đầu không thể chia sẻ” như những thứ khác, cuộc xung đột nội tâm này sẽ dẫn họ bước sang giai đoạn mới của cuộc đời như thế nào?','Wanweaw Hongvivatana, Weawwan Hongvivatana',121,'TÌNH CHỊ DUYÊN EM.jpg','TÌNH CHỊ DUYÊN EM','2023-04-07','https://www.youtube.com/embed/CtKso4rn3r4',2),(8,'Michaela Longden, Rebecca Phillipson, Tom Millen, Victor Mellors',0,0,'Bí ẩn về cái chết của em gái Evie 20 năm trước còn bỏ ngỏ, vào lúc 09:09 hằng đêm, hàng loạt cuộc chạm trán kinh hoàng xảy ra. Liệu Margot có biết được sự thật ai là kẻ giết em gái mình?','Lawrence Fowler',102,'MẶT NẠ QUỶ.jpg','MẶT NẠ QUỶ','2023-04-14','https://www.youtube.com/embed/UJuqhPPEjhM',4),(9,'Matt Damon, Ben Affleck, Jason Bateman, Chris Messina, Matthew Maher, Marlon Wayans, Jay Mohr, Julius Tennon, Chris Tucker, Viola Davis',0,0,'Từ đạo diễn đã từng đoạt giải thưởng Ben Affleck, AIR hé lộ mối quan hệ đột phá giữa huyền thoại Michael Jordan khi mới bắt đầu sự nghiệp và bộ phận bóng rổ còn non trẻ của Nike, đã làm thay đổi thế giới thể thao và văn hóa đương đại với thương hiệu Air Jordan. Câu chuyện cảm động này kể về sự đánh cược khi đặt lên bàn cân tình hình kinh doanh của cả công ty, tầm nhìn vĩ đại của một người mẹ biết giá trị và tài năng của con trai mình, và một siêu sao bóng rổ đã trở thành huyền thoại.','Ben Affleck',112,'AIR - THEO ĐUỔI MỘT HUYỀN THOẠI.jpg','AIR - THEO ĐUỔI MỘT HUYỀN THOẠI','2023-04-14','https://www.youtube.com/embed/xcsb_YkTGHM',3),(10,'Riku Hagiwara, Yûsei Yagi, Akira Takano,…',0,0,'Hira, một nam sinh 17 tuổi có một cuộc sống cô độc ở trường, không bao giờ muốn phơi bày tật nói lắp của mình với các bạn cùng lớp. Anh ấy nhìn thế giới qua ống kính máy ảnh của mình, cho đến một ngày Kiyoi Sou bước qua cửa lớp.','Sakai Mai',132,'CHÀNG TRAI XINH ĐẸP CỦA TÔI.jpg','CHÀNG TRAI XINH ĐẸP CỦA TÔI','2023-05-18','https://www.youtube.com/embed/SdssSgST-fU',4),(11,'Nicolas Cage, Nicholas Hoult, Awkwafina',0,0,'Trong câu chuyện quái vật hiện đại về người hầu trung thành của Dracula, Renfield (do Nicholas Hoult thủ vai), kẻ bề tôi đáng thương của ông chủ tự ái nhất lịch sử, Dracula (do Nicolas Cage thủ vai). Renfield bị buộc phải bắt con mồi về cho chủ nhân và thực hiện mọi mệnh lệnh, kể cả việc đó nhục nhã như thế nào. Nhưng giờ đây, sau nhiều thế kỷ làm nô lệ, Renfield đã sẵn sàng để khám phá cuộc sống bên ngoài cái bóng của Hoàng Tử Bóng Đêm. Liệu anh ấy có thể tìm được cách để thoát khỏi mỗi quan hệ độc hại này?','Chris McKay',91,'RENFIELD TAY SAI CỦA QUỶ.jpg','RENFIELD TAY SAI CỦA QUỶ','2023-04-14','https://www.youtube.com/embed/vXkN2H3Ijyw',4),(12,' Masaya Fukunishi, Yoshiaki Hasegawa, Katsuhisa Hoki, Tetsu Inada, Ryota Iwasaki, Shinichiro Kamio, Mitsuaki Kanuka, Jun Kasama, Subaru Kimura,..',0,0,'Bộ phim hoạt hình chuyển thể từ loạt truyện tranh “Slam Dunk” nổi tiếng của Takehiko Inoue, khắc họa quá trình trưởng thành cá nhân của những học sinh trung học cống hiến tuổi trẻ cho bóng rổ. Phim theo chân Ryota Miyagi, hậu vệ của đội bóng rổ trường trung học Shohoku. Anh có một người anh trai, Sota, hơn anh ba tuổi và là người đã truyền cảm hứng cho tình yêu bóng rổ của anh. Ryota và các đồng đội của mình là Hanamichi Sakuragi, Takenori Akagi, Hisashi Mitsui và Kaede Rukawa thách đấu nhà vô địch bóng rổ liên trường, đội bóng rổ trường trung học Sannoh. Tác giả nguyên tác Inoue phụ trách chỉ đạo và viết kịch bản.','Takehiko Inoue, Yasuyuki Ebara',124,'PHIM CÚ ÚP RỔ ĐẦU TIÊN.jpg','PHIM CÚ ÚP RỔ ĐẦU TIÊN','2023-04-14','https://www.youtube.com/embed/NEa0J_Q-NIY',2),(13,'Greg Hsu, Austin Lin, Gingle Wang',0,0,'Ming-Han là một cảnh sát nhiệt huyết. Một ngày nọ, trong quá trình truy bắt tội phạm cùng người đồng nghiệp xinh đẹp Zi-Qing, Ming-Han vô tình nhặt được một phong bao màu đỏ, và một nhóm người bất ngờ nhảy ra gọi anh là \"con rể\". Họ yêu cầu anh ta kết hôn với đứa con đã chết của họ. Ming-Han không thể chấp nhận quyết định này. Tuy nhiên, anh ấy sau đó anh ta gặp phải vô số sự cố xui xẻo. Điều đáng sợ hơn nữa là anh ta bắt đầu nhìn thấy người chồng ma Mao-Mao của mình. Vì vậy, Ming-Han đã tìm đến thầy để tìm cách. Để thoát khỏi Mao-Mao, Ming-Han không còn cách nào khác ngoài việc giúp anh ta thực hiện mọi mong muốn của mình. Nếu không, Mao-Mao sẽ theo anh ta mãi mãi.Đồng thời, Mao-Mao cũng đóng vai trò là đối tác của Ming-Han để giúp anh ta điều tra vụ buôn lậu ma túy. Một cuộc hành trình giả tưởng đầy tiếng cười và nước mắt giữa một người đàn ông thẳng thắn và một con ma đồng tính đã bắt đầu.',' Cheng Wei-hao',130,'CHUYỆN TÔI VÀ MA QUỶ THÀNH NGƯỜI MỘT NHÀ.jpg','CHUYỆN TÔI VÀ MA QUỶ THÀNH NGƯỜI MỘT NHÀ','2023-04-07','https://www.youtube.com/embed/kB3aMANGbiw',4),(14,'Russell Crowe, Franco Nero…',0,0,'Lấy cảm hứng từ những hồ sơ có thật của Cha Gabriele Amorth, Trưởng Trừ Tà của Vatican (Russell Crowe, đoạt giải Oscar®), bộ phim \"The Pope\'s Exorcist\" theo chân Amorth trong cuộc điều tra về vụ quỷ ám kinh hoàng của một cậu bé và dần khám phá ra những bí mật hàng thế kỷ mà Vatican đã cố gắng giấu kín.','Julius Avery',104,'KHẮC TINH CỦA QUỶ.jpg','KHẮC TINH CỦA QUỶ','2003-09-09','https://www.youtube.com/embed/p4LAYNacgkI',4),(15,'Alessandro Antonaci, Stefano Mandalà, Daniel Lascar',0,0,'Sau cái chết của cha, Emma (Penelope Sangiorgi) vội vã bay từ New York về quê nhà ở Ý để lo hậu sự. Trong thời gian diễn ra tang lễ, Emma ở một mình trong căn nhà mà cha mẹ để lại. Lúc này, cô bị buộc phải đối mặt với một thực thể tà ác có khả năng kết nối thông qua một chiếc radio bị nguyền rủa. Để sống sót và bảo vệ những người mình yêu thương, Emma phải tìm ra bí mật đen tối ẩn sau chiếc radio ma quỷ kia…','Alessandro Antonaci, Stefano Mandalà, Daniel Lascar',93,'ÂM VỰC CHẾT.jpg','ÂM VỰC CHẾT','2023-04-21','https://www.youtube.com/embed/CmBuZXqkyLM',4),(16,'Jake Gyllenhaal, Alexander Ludwig, Antony Starr,…',0,0,'Trong nhiệm vụ cuối cùng ở Afghanistan, Trung sĩ John Kinley cùng đội với phiên dịch viên bản địa Ahmed. Khi đơn vị của họ bị phục kích, Kinley và Ahmed là 2 người sống sót duy nhất. Bị kẻ địch truy đuổi, Ahmed liều mạng đưa Kinley đang bị thương băng qua nhiều dặm đường địa hình khắc nghiệt đến nơi an toàn. Trở về Mỹ, Kinley biết rằng Ahmed và gia đình không dc cấp giấy thông hành tới Mỹ như đã hứa. Quyết tâm bảo vệ bạn mình và đền ơn cứu mạng, Kinley trở lại chiến trường để giúp Ahmed và gia đình trước khi lực lượng phiến quân phát hiện ra họ.','Guy Ritchie',123,'KHẾ ƯỚC.jpg','KHẾ ƯỚC','2023-04-21','https://www.youtube.com/embed/KHk2Dk-ZJnw',4),(17,'Park Sung Woong, Lee Yi Kyung, Yeom Hye Ran, Choi Min Su, Oh Dal Soo',0,0,'Phim Đầu Gấu Đụng Đầu Đất dựa trên câu chuyện thần thoại nổi tiếng tại Hàn Quốc về hai chú gấu sinh đôi hoá thành người sau khi ăn tỏi và ngải cứu trong 100 ngày. Chú gấu ăn tỏi trở thành Na Woong-nam, được một cặp vợ chồng nhà khoa học mang về nuôi nấng, tuy chỉ mới 25 tuổi nhưng lại sở hữu “giao diện” của một ông chú 52 với cái “đầu đất” ngây thơ, hiền lành. Trong khi đó, chú gấu ăn ngải cứu trở thành “đầu gấu” Lee Jeong-hak, được một tên trùm tổ chức tội phạm mang về nuôi và bị lợi dụng như một món “vũ khí” phòng vệ. Trong một tình huống bất đắc dĩ, Na Woong-nam đã trực tiếp đối đầu cùng anh em song sinh Lee Jeong-hak để ngăn chặn một vụ khủng bố virus có tầm lây lan mạnh. Sức mạnh của loài gấu bộc phát sẽ đẩy cuộc đụng độ của cặp gấu song sinh hoá người đi đến hồi kết nào?\r\n','Park Sung Kwang',98,'ĐẦU GẤU ĐỤNG ĐẦU ĐẤT.jpg','ĐẦU GẤU ĐỤNG ĐẦU ĐẤT','2023-04-21','https://www.youtube.com/embed/2IZLhCSApCk',3),(18,'Thái Hòa, Thu Trang, Tiến Luật, NSND Hồng Vân, Huỳnh Phương, Vinh Râu, Thái Vũ,...',1,0,'Lấy cảm hứng từ web drama Chuyện Xóm Tui, phiên bản điện ảnh sẽ mang một màu sắc hoàn toàn khác: hài hước hơn, gần gũi và nhiều cảm xúc hơn Bộ phim là câu chuyện của Nhót - người phụ nữ “chưa kịp già” đã sắp bị mãn kinh, vội vàng đi tìm chồng. Nhưng sâu thẳm trong cô, là khao khát muốn có một đứa con và luôn muốn hàn gắn với người cha suốt ngày say xỉn của mình.','Vũ Ngọc Đãng',112,'CON NHÓT MÓT CHỒNG.jpg','CON NHÓT MÓT CHỒNG','2023-04-28','https://www.youtube.com/embed/QDLyLlmex-Y',3),(19,'Lý Hải, Quốc Cường, Trung Dũng, Huy Khánh, Thanh Thức, Trần Kim Hải, Huỳnh Thi, Diệp Bảo Ngọc, Tú Tri, Quỳnh Như, Tạ Lâm, bé Thùy Linh…',1,0,'Lật mặt 6 sẽ thuộc thể loại giật gân, tâm lý pha hành động, hài hước.','Lý Hải',132,'LẬT MẶT 6 TẤM VÉ ĐỊNH MỆNH.jpg','LẬT MẶT 6: TẤM VÉ ĐỊNH MỆNH','2023-04-28','https://www.youtube.com/embed/o3FoowSoNr4',3),(20,'Anton Eldarov, Polina Gagarina, Aleksandr Gavrilin,…',1,0,'Câu chuyện xoay quanh tình bạn của chú mèo Vincent và chú chuột Maurice. Vincent vừa nhận được công việc bảo vệ kiệt tác tranh Mona Lisa trong một viện bảo tàng, còn Maurice lại có niềm đam mê gặm nhấm bức tranh này. Mọi chuyện phức tạp hơn khi có người cũng đang nung nấu ý định cướp lấy tuyệt tác Mona Lisa. Liệu Vincent và đồng đội của mình có thể cứu lấy những kiệt tác của Davinci và bảo vệ danh cho bảo tàng không?','Vasiliy Rovenskiy',80,'MÈO SIÊU QUẬY Ở VIỆN BẢO TÀNG.jpg','MÈO SIÊU QUẬY Ở VIỆN BẢO TÀNG','2023-04-28','https://www.youtube.com/embed/xj4cfU9SHIM',1),(21,'Nink Chanya McClory, Kritsanapoom Phibunsongkhram',1,0,'Truyền thuyết Ma Lai rút ruột vang danh Đông Nam Á một lần nữa ứng nghiệm. Khi Ma Lai rung động với chàng trai bị nguyền rủa, mối tình lãng mạn nhưng cũng vô cùng rùng rợn liệu có lối thoát? Cloud (Jayler Krissanapoom) sinh ra với chứng rối loạn di truyền dẫn tới bệnh bạch tạng. Sau nhiều năm sống cô độc vì bị xa lánh, Cloud gặp được một cô gái có tên là Sao (Nink Chanya McClory). Cloud nhanh chóng nảy sinh tình cảm với Sao mà không hề biết rằng, cô gái này mang trong mình một bí mật vô cùng khủng khiếp.','Paphangkorn Punchantarak',101,'MA LAI RÚT RUỘT.jpg','MA LAI RÚT RUỘT','2023-04-28','https://www.youtube.com/embed/EkJRvcytJPo',4),(22,'Kim Bo Ra, Kim Jae Hyun',1,0,'Lời đồn ma ám về nhà ga Oksu ngày càng nhiều khi những vụ án kinh hoàng liên tục xảy ra. Một đường ray cũ kỹ, một chiếc giếng bỏ hoang, những con số gây ám ảnh hay những vết thương kỳ dị trên thi thể người xấu số... Tất cả dẫn đến một bí mật đau lòng bị chôn vùi nhiều năm trước.','Jeong Yong Ki',80,'TRẠM TÀU MA.jpg','TRẠM TÀU MA','2023-04-28','https://www.youtube.com/embed/JZKNMSJ3edQ',3),(23,'Chris Pratt, Zoe Saldana, Dave Bautista',1,0,'Cho dù vũ trụ này có bao la đến đâu, các Vệ Binh của chúng ta cũng không thể trốn chạy mãi mãi. Vệ Binh Dải Ngân Hà 3 dự kiến khởi chiếu tại rạp từ 03.05.2023.','James Gunn',150,'VỆ BINH DẢI NGÂN HÀ 3.png','VỆ BINH DẢI NGÂN HÀ 3','2023-05-03','https://www.youtube.com/embed/89aYxQcGGA4',3),(24,'Artyom Bystrov, Ekaterina Cherednik, Fyodor Dobronravov',1,0,'Chú gấu Chebi – một sinh vật nhỏ, màu nâu, có đôi tai khổng lồ và đôi mắt to với sở thích ăn cam. Trên chuyến phiêu lưu đi khắp đó đây của mình, Chebi đáng yêu đã gặp được ông già Gena, người đang sống một mình và có mối quan hệ không tốt với cô con gái. Sau khi được ông Gena nhận nuôi, Chebi đã đồng hành cùng ông như một người bạn thân và giúp ông hàn gắn mối quan hệ với con gái.','Dmitriy Dyachenko',100,'CHEBI GẤU TAI TO ĐÁNG YÊU.jpg','CHEBI: GẤU TAI TO ĐÁNG YÊU','2023-05-05','https://www.youtube.com/embed/1a2Id_jKtLM',1),(25,'Halle Bailey, Jonah Hauer-King, Daveed Diggs, Awkwafina, Jacob Tremblay, Noma Dumezweni, Art Malik, with Javier Bardem and Melissa McCarthy',1,0,'“Nàng Tiên Cá” là câu chuyện được yêu thích về Ariel - một nàng tiên cá trẻ xinh đẹp và mạnh mẽ với khát khao phiêu lưu. Ariel là con gái út của Vua Triton và cũng là người ngang ngạnh nhất, nàng khao khát khám phá về thế giới bên kia đại dương. Trong một lần ghé thăm đất liền, nàng đã phải lòng Hoàng tử Eric bảnh bao. Trong khi tiên cá bị cấm tiếp xúc với con người, Ariel đã làm theo trái tim mình. Nàng đã thỏa thuận với phù thủy biển Ursula hung ác để cơ hội sống cuộc sống trên đất liền. Nhưng cuối cùng việc này lại đe dọa tới mạng sống của Ariel và vương miện của cha nàng.',' Rob Marshall',120,'NÀNG TIÊN CÁ.jpg','NÀNG TIÊN CÁ','2023-05-26','https://www.youtube.com/embed/R2cjgaopZcg',3),(26,'Vin Diesel, Jason Momoa, Brie Larson,…',1,0,'Dom Toretto và gia đình của anh ấy bị trở thành mục tiêu của người con trai đầy thù hận của ông trùm ma túy Hernan Reyes.','Louis Leterrier',121,'FAST AND FURIOUS X.jpg','FAST AND FURIOUS X','2023-05-19','https://www.youtube.com/embed/Jphd23nUCLs',3),(27,'Riku Hagiwara, Yusei Yagi, Akira Takano,…',1,0,'Chuyển thể từ nguyên tác cùng tên trong seri BL light novel của tác giả Nagira Yuu, Chàng Trai Xinh Đẹp Của Tôi nhận được nhiều sự mong đợi của người hâm mộ khi tái xuất với bản điện ảnh My Beautiful Man: Eternal (tên tiếng việt: Chàng Trai Xinh Đẹp Của Tôi: Đời Đời Kiếp Kiếp). Phim kể về mối tình tuyệt đẹp thời học sinh của hai chàng trai Hira và Kiyoi So. Hira vốn là một chàng trai hướng nội lại có tật nói lắp nên đã bị bạn bè bắt nạt. Ngay lúc ấy chàng trai Kiyoi So điển trai đã giúp Hira thoát khỏi cảnh bị bắt nạt. Dần dần cả hai nảy sinh tình cảm và đồng hành cùng nhau suốt những năm tháng thanh xuân. Với bản điện ảnh, người hâm mộ sẽ được theo dõi câu chuyện tiếp nối của bản truyền hình, hứa hẹn sẽ nhẹ nhàng, tươi sáng và đáng yêu. Phim sẽ khởi chiếu tại rạp vào 05.05.2023.','Sakai Mai',92,'CHÀNG TRAI XINH ĐẸP CỦA TÔI ĐỜI ĐỜI KIẾP KIẾP.jpg','CHÀNG TRAI XINH ĐẸP CỦA TÔI: ĐỜI ĐỜI KIẾP KIẾP','2023-05-26','https://www.youtube.com/embed/Wy0f83FHbYY',3),(28,'Priyanka Chopra Jonas, Sam Heughan, Russell Tovey',1,0,'Tình yêu sẽ đến với những ai tin tưởng. #LoveAgainMovie, một bộ phim healing cho những ai đã, đang và sẽ yêu. Với sự tham gia diễn viên Priyanka Chopra và Sam Heughan, cùng Céline Dion và bài hát mới của cô. Duy nhất tại rạp 12.05.2023','Jim Strouse',82,'YÊU NHƯ LẦN ĐẦU.jpg','YÊU NHƯ LẦN ĐẦU','2023-05-12','https://www.youtube.com/embed/UCkMlEh7qmg',3),(29,'Shameik Moore',1,0,'Vô số Spider-Man từ khắp các vũ trụ đang đối đầu nhau?! Xem ngay Official Trailer của SPIDER-MAN: ACROSS THE SPIDER-VERSE. Khởi chiếu tại rạp 02.06.2023','Joaquim Dos Santos, Justin K. Thompson, Kemp Powers',120,'NGƯỜI NHỆN DU HÀNH VŨ TRỤ NHỆN.jpg','NGƯỜI NHỆN: DU HÀNH VŨ TRỤ NHỆN','2023-06-02','https://www.youtube.com/embed/SUz8Aw28vrc',2),(30,'Michelle Yeoh, Dominique Fishback, Ron Perlman',1,0,'Bộ phim dựa trên sự kiện Beast Wars trong loạt phim hoạt hình \"Transformers\", nơi mà các robot có khả năng biến thành động vật khổng lồ cùng chiến đấu chống lại một mối đe dọa tiềm tàng.','Steven Caple Jr.',112,'TRANSFORMERS QUÁI THÚ TRỖI DẬY.jpg','TRANSFORMERS: QUÁI THÚ TRỖI DẬY','2023-06-09','https://www.youtube.com/embed/FH4-_8oVWkw',3),(31,'Ben Affleck, Michael Shannon, Michael Keaton',1,0,'Mùa hè này, đa thế giới sẽ va chạm khốc liệt với những bước chạy của FLASH','Andy Muschietti',116,'FLASH.jpg','FLASH','2023-06-16','https://www.youtube.com/embed/oa47IyEnvk4',3),(32,'Leah Lewis, Mamoudou Athie',1,0,'Xứ Sở Các Nguyên Tố từ Disney và Pixar lấy bối cảnh tại thành phố các nguyên tố, nơi lửa, nước, đất và không khí cùng chung sống với nhau. Câu chuyện xoay quanh nhân vật Ember, một cô nàng cá tính, thông minh, mạnh mẽ và đầy sức hút. Tuy nhiên, mối quan hệ của cô với Wade - một anh chàng hài hước, luôn thuận thế đẩy dòng - khiến Ember cảm thấy ngờ vực với thế giới này. Được đạo diễn bởi Peter Sohn, sản xuất bởi Denise Ream, và lồng tiếng bởi Leah Lewis (Ember) và Mamoudou Athie (Wade), phim dự kiến khởi chiếu tại rạp vào tháng 23.06.2023.','Peter Sohn',98,'XỨ SỞ CÁC NGUYÊN TỐ.jpg','XỨ SỞ CÁC NGUYÊN TỐ','2023-06-23','https://www.youtube.com/embed/8qTBWDKtyYU',1),(33,'Boyd Holbrook, Thomas Kretschmann, Mads Mikkelsen, Harrison Ford, Phoebe Waller-Bridge, Shaunette Renée Wilson',1,0,'Indiana Jones 5 sẽ pha trộn giữa thực tế, hư cấu khi lấy bối cảnh năm 1969, lần này Indiana Jones sẽ đối mặt với Đức quốc xã trong thời gian diễn ra cuộc chạy đua vào không gian.','James Mangold',102,'INDIANA JONES & VÒNG QUAY ĐỊNH MỆNH.jpg','INDIANA JONES & VÒNG QUAY ĐỊNH MỆNH','2023-06-29','https://www.youtube.com/embed/RjBcBt4ukCo',3),(34,'Jamie Lee Curtis, Owen Wilson, Rosario Dawson',1,0,'Nhà là nơi ma ám. Cùng xem đoạn trailer của bộ phim Dinh Thự Ma Ám sẽ được trình chiếu tại các cụm rạp vào ngày 28.07 này.','Justin Simien',96,'DINH THỰ MA ÁM.jpg','DINH THỰ MA ÁM','2023-07-28','https://www.youtube.com/embed/aFKlyxdD_5I',4),(35,'Margot Robbie, Ryan Gosling, Helen Mirren',1,0,'Bộ phim BARBIE sẽ được nhào nặn và chắp bút bởi nữ đạo diễn kiêm biên kịch từng nhận nhiều Đề cử Tượng vàng Oscar – Greta Grewig. Hai nhân vật chính Barbie và Ken sẽ được hóa thân bởi nữ diên viên Margot Robbie và nam thần Ryan Gosling, hứa hẹn sẽ tạo nên “chemistry” đáng yêu giữa hai nhân vật búp bê nổi tiếng thế giới.','Greta Gerwig',122,'BARBIE.jpg','BARBIE','2023-07-02','https://www.youtube.com/embed/_YZ4MISDAOk',2),(36,'Thanh Chan',0,0,'Phàm trần có một coder Thanh Chan','Thanh Chan',100,'avatar.jpg','Thanh Chan','2023-09-23','',2);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genre`
--

DROP TABLE IF EXISTS `movie_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genre` (
  `movie_id` bigint NOT NULL,
  `genre_id` bigint NOT NULL,
  KEY `FK86p3roa187k12avqfl28klp1q` (`genre_id`),
  KEY `FKp6vjabv2e2435at1hnuxg64yv` (`movie_id`),
  CONSTRAINT `FK86p3roa187k12avqfl28klp1q` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`genre_id`),
  CONSTRAINT `FKp6vjabv2e2435at1hnuxg64yv` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genre`
--

LOCK TABLES `movie_genre` WRITE;
/*!40000 ALTER TABLE `movie_genre` DISABLE KEYS */;
INSERT INTO `movie_genre` VALUES (36,1),(36,2);
/*!40000 ALTER TABLE `movie_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `room_id` bigint NOT NULL AUTO_INCREMENT,
  `num_row` int DEFAULT NULL,
  `theater_id` bigint DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  KEY `FKgsjwyr6yogc2ye1oungrndy6x` (`theater_id`),
  CONSTRAINT `FKgsjwyr6yogc2ye1oungrndy6x` FOREIGN KEY (`theater_id`) REFERENCES `theater` (`theater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` bigint NOT NULL AUTO_INCREMENT,
  `room_id` bigint DEFAULT NULL,
  PRIMARY KEY (`seat_id`),
  KEY `FKd7f42843rt05tt66t6vcb7s9u` (`room_id`),
  CONSTRAINT `FKd7f42843rt05tt66t6vcb7s9u` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_time`
--

DROP TABLE IF EXISTS `show_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `show_time` (
  `showtime_id` bigint NOT NULL AUTO_INCREMENT,
  `date_show` date DEFAULT NULL,
  `deleted` tinyint DEFAULT NULL,
  `time_show` time(6) DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  `ticket_id` bigint DEFAULT NULL,
  PRIMARY KEY (`showtime_id`),
  KEY `FK8e72rkmjwjag9nshwu5hvh6b4` (`movie_id`),
  KEY `FK9kvb60sp426s439jb9d6w4nm1` (`room_id`),
  KEY `FK5fdbu8uchktef4r0h2njqgptd` (`ticket_id`),
  CONSTRAINT `FK5fdbu8uchktef4r0h2njqgptd` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`),
  CONSTRAINT `FK8e72rkmjwjag9nshwu5hvh6b4` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  CONSTRAINT `FK9kvb60sp426s439jb9d6w4nm1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_time`
--

LOCK TABLES `show_time` WRITE;
/*!40000 ALTER TABLE `show_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `show_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag_movie`
--

DROP TABLE IF EXISTS `tag_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag_movie` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `name_tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `UKkqndsv7jo9ao91500ceavnwhm` (`name_tag`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag_movie`
--

LOCK TABLES `tag_movie` WRITE;
/*!40000 ALTER TABLE `tag_movie` DISABLE KEYS */;
INSERT INTO `tag_movie` VALUES (2,'C13'),(3,'C16'),(4,'C18'),(1,'P');
/*!40000 ALTER TABLE `tag_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theater` (
  `theater_id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`theater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-22  1:44:57
