use bank

create table users(id int primary key, логин varchar(100), пароль varchar(100))

insert into users value
('0','Администратор','123'),
('1','Пользователь','123');


select * from users