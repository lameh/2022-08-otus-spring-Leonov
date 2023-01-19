insert into genre (id, `name`) values (2, 'Fantasy');
insert into genre (id, `name`) values (3, 'Science Fiction');

insert into author (id, `name`) values (2, 'H.L. Oldi');
insert into author (id, `name`) values (3, 'Isaac Azimov');

insert into book (id, `name`, author_id, genre_id) values (3, 'Foundation', 3, 3);

insert into commentary (id, text, book_id) values (2, 'Good!', 3);
insert into commentary (id, text, book_id) values (3, 'Very good!', 3);