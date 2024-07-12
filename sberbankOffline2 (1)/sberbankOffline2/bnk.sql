create database bank;
show databases;
use bank;

create table users(id int primary key, log varchar(100), pass varchar(100));

insert into users value ('2','new','new');
select * from users;
select log from users where log = '' and pass = '';
delete users from users where id = '2';
select count(*) from users; 
alter table users add balance double not null;
select balance from users where log = 'root';
SELECT balance FROM users WHERE log = 'root';
update users set balance = 10 where log = 'root';
