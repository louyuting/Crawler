CREATE TABLE user(
    id int primary key auto_increment,
    username varchar(30) not null,
    sex varchar(10),
    birthday datetime,
    address varchar(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;