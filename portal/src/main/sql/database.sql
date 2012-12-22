drop database if exists sip13_portal;
create database sip13_portal;
use sip13_portal;


-- =====================================================================================================================
-- Tables
-- =====================================================================================================================

create table user (
    id mediumint(8) unsigned not null auto_increment primary key,
    username varchar(20) unique not null,
    password varchar(20) not null,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    email varchar(80) not null
) engine = InnoDB;


-- =====================================================================================================================
-- Sample data
-- =====================================================================================================================

insert into user (username, password, first_name, last_name, email) values
    ('paul', 'paul', 'Paul', 'Jenson', 'paul@example.com'),
    ('aimee', 'aimee', 'Aimee', 'Henshaw', 'aimee@example.com');
