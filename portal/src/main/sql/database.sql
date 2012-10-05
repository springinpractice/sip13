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

create table ticket_category (
    id tinyint unsigned not null auto_increment primary key,
    name varchar(40) unique not null
) engine = InnoDB;

create table ticket (
    id mediumint(8) unsigned not null auto_increment primary key,
    user_id mediumint(8) unsigned not null,
    date_created timestamp not null,
    category_id tinyint unsigned not null,
    description text(4000) not null,
    foreign key (user_id) references user (id),
    foreign key (category_id) references ticket_category (id)
) engine = InnoDB;


-- =====================================================================================================================
-- Sample data
-- =====================================================================================================================

insert into user (username, password, first_name, last_name, email) values
    ('paul', 'paul', 'Paul', 'Jenson', 'paul@example.com'),
    ('aimee', 'aimee', 'Aimee', 'Henshaw', 'aimee@example.com');

insert into ticket_category (name) values
    ('Billing issue'),
    ('Product issue'),
    ('General issue'),
    ('General question'),
    ('General suggestion');
