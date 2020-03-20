# Creating table Word
create table word(
    `id` int not null auto_increment primary key,
    `word` varchar(50) null,
    `translate` varchar(50) null,
    `topic_id` int not null,
    `level_id` int not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
#
# Creating table Topic
create table if not exists `topic`(
    `id` int not null auto_increment primary key,
    `topic_name` varchar(50) null,
    `color` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

# Creating table Level
create table level(
                      `id` int not null auto_increment primary key,
                      `level_name` varchar(20) null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

alter table word add foreign key(topic_id) references topic(id);
alter table word add foreign key(level_id) references level(id);

insert into topic(topic_name,color)
values ('Travel','primary'),
       ('Work','secondary'),
       ('Movie','success'),
       ('Social','danger'),
       ('Medicine','warning'),
       ('Sport','info'),
       ('Other','dark');

insert into level(level_name)
values ('Elementary'),
       ('Pre-Intermediate'),
       ('Intermediate'),
       ('Upper_Intermediate'),
       ('Advanced');

insert into word(word, translate, topic_id, level_id)
values ('journey','путешествие',1,1),
       ('flight','полет',1,1),
       ('ticket','билет',1,1),
       ('get in','прибыть',1,2),
       ('declare','объявить',1,3),
       ('customs','таможня',1,2),
       ('station','станции',1,1),
       ('train','поезд',1,1),
       ('ferry','паром',1,1),
       ('trade','сделка',2,2),
       ('employee','сотрудник',2,1),
       ('employer','работодатель',2,1),
       ('commuter','пригородный',2,2),
       ('job','работа',2,1),
       ('occupation','род занятий',2,2),
       ('actor','актер',3,1),
       ('role','роль',3,1),
       ('story','история',3,1),
       ('script','скрипт',3,2),
       ('shot','кадр',3,2),
       ('western','вестерн',3,2),
       ('thriller','триллер',3,2),
       ('horror','фильм ужасов',3,2),
       ('brave','храбрый',4,1),
       ('careful','осторожный',4,1),
       ('cautious','осмотрительный',4,3),
       ('ecstatic','экстатический',4,3),
       ('excited','в восторге',4,2);

