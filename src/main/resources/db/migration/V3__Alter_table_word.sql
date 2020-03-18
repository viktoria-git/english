alter table word add `topic_id` int;
alter table word add foreign key(topic_id) references topic(id);