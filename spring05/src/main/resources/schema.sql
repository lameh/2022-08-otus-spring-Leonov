drop table if exists author;
create table author
(
    id IDENTITY primary key,
    name varchar(255) not null
);

drop table if exists genre;
create table genre
(
    id IDENTITY primary key,
    name varchar(255) not null
);

drop table if exists book;
create table book
(
    id IDENTITY primary key,
    name      varchar(255) not null,
    author_id BIGINT,
    genre_id  BIGINT,
    foreign key (author_id) references author (id),
    foreign key (genre_id) references genre (id)
)