CREATE SEQUENCE IF NOT EXISTS categories_id_seq;

CREATE SEQUENCE IF NOT EXISTS posts_id_seq;

CREATE SEQUENCE IF NOT EXISTS roles_id_seq;

CREATE SEQUENCE IF NOT EXISTS users_id_seq;

CREATE TABLE IF NOT EXISTS categories (
   name        character      varying,
   category_id integer        REFERENCES categories(id),
   id          integer        default categories_id_seq.nextval NOT NULL PRIMARY KEY);

CREATE TABLE IF NOT EXISTS role (
   id          integer        default roles_id_seq.nextval NOT NULL PRIMARY KEY,
   name        character      varying);

CREATE TABLE IF NOT EXISTS users (
   user_name   character      varying(32) NOT NULL,
   id          integer        default users_id_seq.nextval NOT NULL PRIMARY KEY,
   email       character      varying(64),
   role_id     integer        NOT NULL REFERENCES users(id)
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

