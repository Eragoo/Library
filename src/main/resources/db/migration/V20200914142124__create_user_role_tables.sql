create table role
(
    id   bigint generated always as identity,
    name text not null unique,
    primary key (id)
);

create table "user"
(
    id        bigint generated always as identity,
    login     text not null unique,
    password  text not null,
    role_id bigint not null,
    primary key (id),
    foreign key (role_id) references role
);