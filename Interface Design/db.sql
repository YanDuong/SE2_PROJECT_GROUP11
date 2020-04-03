ALTER DATABASE covid CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE countries (
    id int(11) PRIMARY KEY auto_increment,
    country_name varchar(255),
    total_cases varchar(255),
    new_cases varchar(255),
    total_death varchar(255),
    new_death varchar(255),
    total_recovered varchar(255),
    active_cases varchar(255),
    critical_cases varchar(255)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE admin (
    admin_id integer(11) PRIMARY KEY not null auto_increment,
    name varchar(255) ,
    email varchar(255) ,
    password varchar(255) 
)ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- --------------------------------------------------------

--
-- Dumping data for table `users`
--

INSERT INTO `admin` (`admin_id`, `name`, `email`, `password`) VALUES
(1, 'Tran Van Khang', 'vankhang@gmail.com', '123456'),
(2, 'Nguyen Van A', 'a@gmail.com', '123456'),
(3, 'Nguyen Van B', 'b@gmail.com', '123456'),
(4, 'Nguyen Van C', 'c@gmail.com', '123456'),
(5, 'Nguyen Van D', 'd@gmail.com', '123456'),
(6, 'Nguyen Van E', 'e@gmail.com', '123456');


-- --------------------------------------------------------

--
-- Dumping data for table `users`
--

INSERT INTO `countries` (`id`, `country_name`, `total_cases`, `new_cases`,`total_death`,`new_death`,`total_recovered`,`active_cases`,`critical_cases`) VALUES
(1,'USA', "190,000", "5,000", "5,000", "424", "20,442", "17,0000", "612");
