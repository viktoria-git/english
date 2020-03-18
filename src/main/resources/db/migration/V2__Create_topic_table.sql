create table if not exists `topic`(
    `id` int not null auto_increment primary key,
    `topic_name` varchar(50) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;