create table if not exists word(
    `id` int not null auto_increment primary key,
    `word` varchar(50) null,
    `translate` varchar(50) null,
    `user_id` int not null,
    `topic_id` int not null,
    `level_id` int not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

create table if not exists user(
    `id` int not null auto_increment primary key,
    `username` varchar(50) null,
    `email` varchar(50) null,
    `password` varchar(6) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

create table if not exists `topic`(
    `id` int not null auto_increment primary key,
    `topic_name` varchar(50) null,
    `color` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

create table if not exists level(
    `id` int not null auto_increment primary key,
    `level_name` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

alter table word add foreign key (user_id) references user(id);
alter table word add foreign key(topic_id) references topic(id);
alter table word add foreign key(level_id) references level(id);

insert into user(username, email, password)
values ('viktoria','vicoolyastr@gmail.com','root'),
       ('radik','kolrlrs@gmail.com','root'),
       ('test','test@gmail.com','root');

insert into topic(topic_name,color)
values ('Travel','travel'),
       ('Other','other'),
       ('Work','work');

insert into level(level_name)
values ('Elementary'),
       ('Pre-Intermediate'),
       ('Intermediate');

insert into word(word, translate, user_id, topic_id, level_id)
values ('journey','путешествие',1,1,1),
       ('flight','полет',1,1,1),
       ('ticket','билет',1,2,1),
       ('cat','кот',1,1,2),
       ('declare','объявить',1,2,3);

insert into word(id, word, translate, user_id, topic_id, level_id)
values (125, 'dog', 'собака', 1, 1, 2);