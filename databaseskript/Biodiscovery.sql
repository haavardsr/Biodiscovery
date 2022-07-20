drop database if exists MytestDB;
create database MytestDB;
use MytestDB;

set time_zone ='+01:00';

CREATE TABLE user
(
    id              INT AUTO_INCREMENT
        PRIMARY KEY,

    password        VARCHAR(128) NULL,
    first_name      VARCHAR(100) NOT NULL,
    last_name       VARCHAR(100) NOT NULL,
    email           VARCHAR(254) NOT NULL,
    tlf             VARCHAR(25)  NOT NULL,
    is_superuser    TINYINT(1)   NOT NULL,
    salt            VARCHAR(200) NULL,
    verificationKey LONGTEXT     NULL,
    CONSTRAINT email
        UNIQUE (email)
)
    CHARSET = utf8mb3;


create table User_roles (
  LoginName         varchar(255) not null,
  role_name         varchar(255) not null,
  primary key (LoginName, role_name)
);

create table Categories (
  Id              integer UNIQUE auto_increment,
  Name              varchar(255) not null,
  ImagePath         varchar(255) not null,
  CONSTRAINT PRIMARY KEY (Id)
);

create table files(
    Id              integer UNIQUE auto_increment,
    Name            varchar(255) not null,
    Content         LONGBLOB not null,
    ContentType varchar(255) not null,
    CONSTRAINT PRIMARY KEY (Id)
);

#inserter en record av en bruker inn i databasen otra.
INSERT INTO user (password, first_name, last_name, email, tlf, is_superuser, salt, verificationKey)
VALUES ('c2696c1d1ee286e4c92cfb553bc147745fb740e8c22aa0df6ebde7461ea6b663', 'håvard', 'håvard', 'haavard@gmail.com',
        '12345678', 1, 'rS4zXqNn3314QA7ABivokQ==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'erik', 'erik', 'erik@gmail.com', '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'kevin', 'kevin', 'kevin@gmail.com',
        '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'john', 'john', 'john@gmail.com',
        '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'ole', 'ole', 'ole@gmail.com', '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'hans', 'hans', 'hans@gmail.com',
        '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL),
       ('e6a1f9acda8f7ca6eea57ba85f9c0620478827209c3316f77a3ebe9d140da9ba', 'new', 'new', 'amv.rental.test@gmail.com',
        '12345678', 1, '15KA8EmTj1Q9+9HtVRXueg==', NULL);

