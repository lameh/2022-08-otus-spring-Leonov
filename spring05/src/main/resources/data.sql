insert into genre (id, `name`) values (1, 'Fantasy');
insert into genre (id, `name`) values (2, 'Science fiction');

insert into author (id, `name`) values (1, 'H.L. Oldi');
insert into author (id, `name`) values (2, 'Isaac Azimov');

insert into book (id, `name`, author_id, genre_id) values (1, 'Abyss of hungry eyes', 1, 1);
insert into book (id, `name`, author_id, genre_id) values (2, 'Foundation', 2, 2);