desc role;

select * from role;

SELECT `users`.`id`,
    `users`.`first_name`,
    `users`.`last_name`,
    `users`.`password`,
    `users`.`user_name`
FROM `movie_db`.`users`;
select * from user;

select * from token;
select * from user_role;

drop table role;

delete from role where id = 1;

update role set id = 2 where id = 3;

alter table role auto_increment = 3;

insert into users (id, address, mobile_no, name, password, username) values(1, 'here', '123', 'clotzer', 'test123', 'clotzer');;

delete from `movie_db`.`user` where id = 1;
commit;

truncate table user;