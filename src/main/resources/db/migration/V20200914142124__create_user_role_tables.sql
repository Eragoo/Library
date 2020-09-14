create table "user"
(
    id bigint generated always as identity,
    login text not null unique,
    password text not null,
    role text not null,
    primary key (id)
);