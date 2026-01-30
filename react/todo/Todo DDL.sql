drop database if exists todo;
create database todo;

use todo;

create table todo(
	num int primary key auto_increment,
    date Date not null,
    text varchar(100) not null,
    `order` int not null
);
insert todo(date, text, `order`) values('2026-01-29', "점심", 1);