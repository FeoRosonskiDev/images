create database imagenes;
use imagenes;

create table person
(
	id int auto_increment primary key,
    pname varchar(50),
    photo longblob
);