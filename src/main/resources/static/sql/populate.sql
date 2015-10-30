INSERT INTO CATEGORIES(name) VALUES ('Voyage'),('Cuisine');


INSERT INTO POSTS(title, content, category_id) VALUES ('Voyage à New-York', 'Premier jour blablabla', 1);

insert into COMMENTS(post_id, content) values (1, 'Voici un commentaire bien utile');
insert into COMMENTS(post_id, content) values (1, 'Voici un deuxième commentaire bien utile');



insert into ROLES(name) values ('Admin'),('Contributor'),('User');

insert into USERS(email, user_name, role_id) values ('teaiaex2309@gmail.com','Toto', 1);
insert into USERS(email, user_name, role_id) values ('kldazen1324@gmail.com','Tata', 1);

