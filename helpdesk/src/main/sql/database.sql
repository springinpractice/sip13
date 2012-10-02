drop database if exists sip13_help_desk;
create database sip13_help_desk;
use sip13_help_desk;


-- =====================================================================================================================
-- Tables
-- =====================================================================================================================

create table ticket_status (
    id tinyint unsigned not null auto_increment primary key,
    ukey varchar(80),
    name varchar(80)
) engine = InnoDB;

create table ticket (
    id mediumint(8) unsigned not null auto_increment primary key,
    ticket_status_id tinyint unsigned not null,
    user_name varchar(80) not null,
    user_email varchar(80) not null,
    description text(4000) not null,
    date_created timestamp not null,
    foreign key (ticket_status_id) references ticket_status (id)
) engine = InnoDB;


-- =====================================================================================================================
-- Procedures
-- =====================================================================================================================

delimiter //

create procedure createTicket(
    $status_key varchar(80),
    $user_name varchar(80),
    $user_email varchar(80),
    $description varchar(4000))
begin
    select id from ticket_status where ukey = $status_key into @ticket_status_id;
    insert into ticket (ticket_status_id, user_name, user_email, description) values (@ticket_status_id, $user_name, $user_email, $description);
end //

delimiter ;


-- =====================================================================================================================
-- Reference data
-- =====================================================================================================================

insert into ticket_status (ukey, name) values
    ('open', 'Open'),
    ('ack', 'Acknowledged'),
    ('in_progress', 'In progress'),
    ('rejected', 'Rejected'),
    ('closed', 'Closed');


-- =====================================================================================================================
-- Sample data
-- =====================================================================================================================

call createTicket('open', 'Chris Mahurin', 'chris@example.com', 'The login doesn''t seem to work.');
call createTicket('ack', 'Phil Turner', 'phil@example.com', 'I received an A but it should have been a B.');
call createTicket('open', 'Dardy Chen', 'dardy@example.com', 'This class costs too much.');
