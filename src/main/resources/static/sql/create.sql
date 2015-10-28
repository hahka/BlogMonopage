CREATE SEQUENCE IF NOT EXISTS categories_id_seq;

CREATE SEQUENCE IF NOT EXISTS posts_id_seq;

CREATE SEQUENCE IF NOT EXISTS roles_id_seq;

CREATE SEQUENCE IF NOT EXISTS users_id_seq;

CREATE SEQUENCE IF NOT EXISTS comments_id_seq;

CREATE TABLE IF NOT EXISTS categories (
   name        character      varying,
   category_id integer        REFERENCES categories(id),
   id          integer        default categories_id_seq.nextval NOT NULL PRIMARY KEY);

CREATE TABLE IF NOT EXISTS roles (
   id          integer        default roles_id_seq.nextval NOT NULL PRIMARY KEY,
   name        character      varying);

CREATE TABLE IF NOT EXISTS users (
   user_name   character      varying(32) NOT NULL,
   id          integer        default users_id_seq.nextval NOT NULL PRIMARY KEY,
   email       character      varying(64),
   role_id     integer        NOT NULL REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS posts (
   content     text,
   date        date,
   category_id integer        REFERENCES categories(id),
   user_id     integer        REFERENCES users(id),
   title       character      varying(64),
   id          integer        default posts_id_seq.nextval NOT NULL PRIMARY KEY,
   image       bytea
);


CREATE TABLE IF NOT EXISTS comments (
   id          integer        default comments_id_seq.nextval NOT NULL PRIMARY KEY,
   content     text,
   post_id     integer        REFERENCES posts(id),
   user_id     integer        REFERENCES users(id),
   date        date,
   validated   boolean
);

