create table if not exists `word`(
    `id` int not null auto_increment primary key,
    `word` varchar(30) null,
    `translate` varchar(30) null,
    `color` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


