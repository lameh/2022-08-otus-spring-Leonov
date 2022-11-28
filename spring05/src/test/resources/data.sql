insert into genre (id, `name`) values (2, 'Fantasy');
insert into genre (id, `name`) values (3, 'Science Fiction');

insert into author (id, `name`) values (2, 'Roger Zelazny');
insert into author (id, `name`) values (3, 'Michael Moorcock');

insert into book (id, `name`, author_id, genre_id) values (3, 'Nine princes in Amber', 2, 2);