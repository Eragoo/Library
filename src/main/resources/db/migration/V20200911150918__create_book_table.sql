create table genre
(
    id   bigint generated always as identity,
    name text not null,
    primary key (id)
);

create table book (
    id bigint generated always as identity,
    name text not null,
    author text not null,
    publication_date date not null,
    amount int not null,
    primary key (id)
);

create table book_genres (
    genre_id bigint not null,
    book_id bigint not null,
    foreign key (genre_id) references "genre",
    foreign key (book_id) references "book"
);

