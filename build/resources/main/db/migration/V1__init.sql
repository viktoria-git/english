# Creating table Word
create table if not exists word(
    `id` int not null auto_increment primary key,
    `word` varchar(50) null,
    `translate` varchar(50) null,
    `user_id` int not null,
    `topic_id` int not null,
    `level_id` int not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

# Creating table User
create table if not exists user(
    `id` int not null auto_increment primary key,
    `username` varchar(50) null,
    `email` varchar(50) null,
    `password` varchar(6) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

# Creating table Topic
create table if not exists `topic`(
    `id` int not null auto_increment primary key,
    `topic_name` varchar(50) null,
    `color` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

# Creating table Level
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
       ('Work','work'),
       ('Movie','movie'),
       ('Social','social'),
       ('Medicine','medicine'),
       ('Sport','sport'),
       ('Technology','technology'),
       ('Clothes','clothes'),
       ('Hobby','hobby'),
       ('Other','other');

insert into level(level_name)
values ('Elementary'),
       ('Pre-Intermediate'),
       ('Intermediate'),
       ('Upper_Intermediate'),
       ('Advanced');

insert into word(word, translate, user_id, topic_id, level_id)
values ('journey','путешествие',1,1,1),
       ('flight','полет',1,1,1),
       ('ticket','билет',1,1,1),
       ('get in','прибыть',1,1,2),
       ('declare','объявить',1,1,3),
       ('customs','таможня',2,1,2),
       ('station','станции',2,1,1),
       ('train','поезд',2,1,1),
       ('ferry','паром',2,1,1),
       ('trade','сделка',2,2,2),
       ('employee','сотрудник',2,2,1),
       ('employer','работодатель',1,2,1),
       ('commuter','пригородный',1,2,2),
       ('job','работа',1,2,1),
       ('occupation','род занятий',1,2,2),
       ('actor','актер',1,3,1),
       ('role','роль',1,3,1),
       ('story','история',1,3,1),
       ('script','скрипт',2,3,2),
       ('shot','кадр',2,3,2),
       ('western','вестерн',2,3,2),
       ('thriller','триллер',2,3,2),
       ('horror','фильм ужасов',2,3,2),
       ('brave','храбрый',2,4,1),
       ('careful','осторожный',2,4,1),
       ('cautious','осмотрительный',2,4,3),
       ('ecstatic','экстатический',2,4,3),
       ('excited','в восторге',2,4,2);
