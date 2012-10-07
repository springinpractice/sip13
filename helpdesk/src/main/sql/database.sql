drop database if exists sip13_help_desk;
create database sip13_help_desk;
use sip13_help_desk;


-- =====================================================================================================================
-- Tables
-- =====================================================================================================================

-- For dynamic navigation
create table site_node_template (
    id smallint unsigned not null auto_increment primary key,
    ukey varchar(80) unique not null,
    name varchar(200) not null,
    path varchar(200) not null,
    parent_id smallint unsigned,
    foreign key (parent_id) references site_node_template (id)
) engine = InnoDB;

create table ticket_status (
    id tinyint unsigned not null auto_increment primary key,
    ukey varchar(80) unique not null,
    name varchar(80) unique not null
) engine = InnoDB;

create table ticket (
    id mediumint(8) unsigned not null auto_increment primary key,
    ticket_status_id tinyint unsigned not null,
    customer_username varchar(20) not null,
    description text(4000) not null,
    date_created timestamp not null,
    foreign key (ticket_status_id) references ticket_status (id)
) engine = InnoDB;

create table user (
    id mediumint(8) unsigned not null auto_increment primary key,
    username varchar(20) unique not null,
    password varchar(20) not null,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    email varchar(80) not null
) engine = InnoDB;


-- =====================================================================================================================
-- Procedures
-- =====================================================================================================================

delimiter //

create procedure createSiteNodeTemplate(
    $parent_key varchar(80),
    $ukey varchar(80),
    $name varchar(200),
    $path varchar(200))
begin
    declare pid smallint;
    if ($parent_key is not null) then
        select id from site_node_template where ukey = $parent_key into pid;
    end if;
    insert into site_node_template (ukey, name, path, parent_id) values ($ukey, $name, $path, pid);
end //

create procedure createTicket(
    $status_key varchar(80),
    $customer_username varchar(20),
    $description varchar(4000))
begin
    declare ticket_status_id tinyint;
    select id from ticket_status where ukey = $status_key into ticket_status_id;
    insert into ticket (ticket_status_id, customer_username, description) values (ticket_status_id, $customer_username, $description);
end //

delimiter ;


-- =====================================================================================================================
-- Reference data
-- =====================================================================================================================

call createSiteNodeTemplate(null, 'home', 'Home', '/');
call createSiteNodeTemplate('home', 'login', 'Log In', '/login');
call createSiteNodeTemplate('home', 'logout', 'Log Out', '/logout');
call createSiteNodeTemplate('home', 'accessDenied', 'Access Denied', '/accessdenied');
call createSiteNodeTemplate('home', 'tickets', 'Tickets', '/tickets');
call createSiteNodeTemplate('tickets', 'newTicket', 'New Ticket', '/tickets/new');
call createSiteNodeTemplate('tickets', 'ticketDetails', 'Ticket {id}', '/tickets/{id}');
call createSiteNodeTemplate('home', 'knowledgeBase', 'Knowledge Base', '/knowledgebase');
call createSiteNodeTemplate('home', 'approvals', 'Approvals', '/approvals');
call createSiteNodeTemplate('home', 'tasks', 'Tasks', '/tasks');
call createSiteNodeTemplate('home', 'surveys', 'Surveys', '/surveys');
call createSiteNodeTemplate('home', 'reporting', 'Reporting', '/reporting');

insert into ticket_status (ukey, name) values
    ('open', 'Open'),
    ('ack', 'Acknowledged'),
    ('in_progress', 'In progress'),
    ('rejected', 'Rejected'),
    ('closed', 'Closed');


-- =====================================================================================================================
-- Sample data
-- =====================================================================================================================

insert into user (username, password, first_name, last_name, email) values
    ('willie', 'willie', 'Willie', 'Wheeler', 'willie@example.com'),
    ('john', 'john', 'John', 'Wheeler', 'john@example.com'),
    ('josh', 'josh', 'Josh', 'White', 'josh@example.com');

call createTicket('open', 'paul', 'The login doesn''t seem to work.');
call createTicket('ack', 'aimee', 'I received an A but it should have been a B.');
call createTicket('open', 'paul', 'This class costs too much.');
