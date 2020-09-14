insert into role (name)
values ('ADMIN');
insert into role (name)
values ('USER');

insert into "user" (username, password, role_id)
VALUES ('admin', '$2a$10$fi6OLSRrM7KPkdXrtCEiJOGwR0T4kjyVanKi/nC9/lkCBqV55iPqq',
        (select id from role where name = 'ADMIN'));
insert into "user" (username, password, role_id)
VALUES ('user', '$2a$10$BB9nwyoADO8GSgm8lYWTtOCuilq4DUlGYSohwStb2fgqUniwH3Kem',
        (select id from role where name = 'USER'));