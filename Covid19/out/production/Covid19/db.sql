
CREATE DATABASE IF NOT EXISTS `covid` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `covid`;
-- --------------------------------------------------------

--
-- Table structure for table `continent`
--
DROP TABLE IF EXISTS `continent`;
CREATE TABLE continent (
    id int(11) PRIMARY KEY auto_increment NOT NULL,
    country_name varchar(255) NOT NULL,
    total_cases int(11) NOT NULL,
    new_cases int(11) NOT NULL,
    total_death int(11) NOT NULL,
    new_death int(11) NOT NULL,
    total_recovered int(11) NOT NULL,
    active_cases int(11) NOT NULL,
    critical_cases int(11) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




-- --------------------------------------------------------

--
-- Table structure for table `country`
--
DROP TABLE IF EXISTS `country`;
CREATE TABLE country (
    id int(11) PRIMARY KEY auto_increment NOT NULL,
    country_name varchar(255) NOT NULL,
    total_cases int(11) NOT NULL,
    new_cases int(11) NOT NULL,
    total_death int(11) NOT NULL,
    new_death int(11) NOT NULL,
    total_recovered int(11) NOT NULL,
    active_cases int(11) NOT NULL,
    critical_cases int(11) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------


--
-- Table structure for table `VietNam`
--
DROP TABLE IF EXISTS `city`;
CREATE TABLE city(
    id int(11) PRIMARY KEY auto_increment,
    country_name varchar(255) NOT NULL,
    total_cases int(11) NOT NULL,
    active_cases int(11) NOT NULL,
    total_recovered int(11) NOT NULL,
    total_death int(11) NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--
DROP TABLE IF EXISTS `admin`;
CREATE TABLE admin (
    id int(11) PRIMARY KEY NOT NULL  auto_increment,
    name varchar(255) ,
    email varchar(255) ,
    password varchar(255) 
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------

--
-- Table structure for table `admin`
--
DROP TABLE IF EXISTS `dailycase`;
CREATE TABLE dailycase (
    id int(11) PRIMARY KEY NOT NULL  auto_increment,
    name varchar(255) ,
    date varchar(255) ,
    year varchar(255),
    cases int(11)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `new`
--
DROP TABLE IF EXISTS `news`;
CREATE TABLE news (
    id int(11) PRIMARY KEY NOT NULL  auto_increment,
    title varchar(255),
    url varchar(255),
    imageUrl varchar(255),
    sourceMeta varchar (255),
    datePublic varchar(255)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Dumping data for table `users`
--

INSERT INTO `admin` (`id`, `name`, `email`, `password`) VALUES
(1, 'Tran Van Khang', 'vankhang@gmail.com', '123456'),
(2, 'Nguyen Van A', 'a@gmail.com', '123456'),
(3, 'Nguyen Van B', 'b@gmail.com', '123456'),
(4, 'Nguyen Van C', 'c@gmail.com', '123456'),
(5, 'Nguyen Van D', 'd@gmail.com', '123456'),
(6, 'Nguyen Van E', 'e@gmail.com', '123456');


-- --------------------------------------------------------

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`id`, `country_name`, `total_cases`, `new_cases`,`total_death`,`new_death`,`total_recovered`,`active_cases`,`critical_cases`) VALUES
(1,'China', 1900338, 521323, 123512, 124124, 512414, 134512, 3562),
(2,'USA', 12515112, 2312213, 15135, 51251, 123112, 5124223, 1412);



-- --------------------------------------------------------

--
-- Dumping data for table `continent`
--

INSERT INTO `continent` (`id`, `country_name`, `total_cases`, `new_cases`,`total_death`,`new_death`,`total_recovered`,`active_cases`,`critical_cases` ) VALUES
(1,'Africa', 12515112, 2312213, 15135, 51251, 123112, 5124223, 1412);

-- --------------------------------------------------------
--
-- Dumping data for table `vietnam`
--

INSERT INTO `city` (`id`, `country_name`, `total_cases`,`total_recovered`,`active_cases`,`total_death`) VALUES
(1,'Hanoi', 201, 43, 12, 0);
