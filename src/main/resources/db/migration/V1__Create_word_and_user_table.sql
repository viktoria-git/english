# Creating table Word
create table if not exists word(
    `id` int not null auto_increment primary key,
    `word` varchar(50) null,
    `translate` varchar(50) null,
    `color` varchar(10) null,
    `user_id` int not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

# Creating table User
create table if not exists user(
    `id` int not null auto_increment primary key,
    `username` varchar(50) null,
    `email` varchar(50) null,
    `password` varchar(6) not null
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

alter table word add foreign key (user_id) references user(id);

insert into user(username, email, password)
values ('viktoria','vicoolyastr@gmail.com','root'),
       ('radik','kolrlrs@gmail.com','root'),
       ('test','test@gmail.com','root');

insert into word(word, translate, color, user_id)
values ('journey','путешествие','danger',1),
       ('flight','полет','danger',1),
       ('ticket','билет','danger',1),
       ('get in','прибыть','danger',1),
       ('declare','объявить','danger',1),
       ('customs','таможня','danger',2),
       ('station','станции','danger',2),
       ('train','поезд','danger',2),
       ('ferry','паром','danger',2),
       ('trade','сделка','danger',2),
       ('employee','сотрудник','danger',2),
       ('employer','работодатель','danger',1),
       ('commuter','пригородный','danger',1),
       ('job','работа','danger',1),
       ('occupation','род занятий','danger',1),
       ('actor','актер','danger',1),
       ('role','роль','danger',1),
       ('story','история','danger',1),
       ('script','скрипт','danger',2),
       ('shot','кадр','danger',2),
       ('western','вестерн','danger',2),
       ('thriller','триллер','danger',2),
       ('horror','фильм ужасов','danger',2),
       ('brave','храбрый','danger',2),
       ('careful','осторожный','danger',2),
       ('cautious','осмотрительный','danger',2),
       ('ecstatic','экстатический','danger',2),
       ('excited','в восторге','danger',2);

