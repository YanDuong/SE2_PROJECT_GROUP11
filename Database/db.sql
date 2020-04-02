ALTER DATABASE covid CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE countries (
    country_id int(11) PRIMARY KEY auto_increment,
    total_cases int(11),
    new_cases int(11),
    total_death int(11),
    new_death int(11),
    total_recovered int(11),
    active_cases int(11),
    critical_cases int(11)
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