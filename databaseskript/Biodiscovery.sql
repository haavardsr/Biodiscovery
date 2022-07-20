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
VALUES ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'håvard', 'håvard', 'haavard@gmail.com',
        '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'erik', 'erik', 'erik@gmail.com', '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'shabnam', 'shabnam', 'shabnam@gmail.com',
        '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'aksel', 'aksel', 'aksel@gmail.com',
        '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'fredrik', 'fredrik', 'fredrik@gmail.com', '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'biodiscovery', 'biodiscovery', 'biodiscovery@gmail.com',
        '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL),
       ('8f688cf27ab833161995bf2399e97d019939eb642965efc01f364294c1172c1f', 'new', 'new', 'new@gmail.com',
        '12345678', 1, 'W3qALUg8uS/vlTccy9L8ow==', NULL);

