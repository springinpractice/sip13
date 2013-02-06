drop database if exists sip13_help_desk;
create database sip13_help_desk;
use sip13_help_desk;


-- =====================================================================================================================
-- Tables
-- =====================================================================================================================

-- For dynamic navigation
create table site_node (
    id smallint unsigned not null auto_increment primary key,
    ukey varchar(80) unique not null,
    name varchar(200) not null,
    path varchar(200) not null,
    parent_id smallint unsigned,
    foreign key (parent_id) references site_node (id)
) engine = InnoDB;

create table ticket_status (
    id tinyint unsigned not null auto_increment primary key,
    ukey varchar(80) unique not null,
    name varchar(80) unique not null
) engine = InnoDB;

create table ticket_category (
    id tinyint unsigned not null auto_increment primary key,
    ukey varchar(80) unique not null,
    name varchar(40) unique not null
) engine = InnoDB;

create table ticket (
    id mediumint(8) unsigned not null auto_increment primary key,
    ticket_status_id tinyint unsigned not null,
    customer_username varchar(20),
    customer_email varchar(80),
    customer_full_name varchar(80),
    ticket_category_id tinyint unsigned not null,
    description text(4000) not null,
    date_created timestamp not null,
    foreign key (ticket_status_id) references ticket_status (id),
    foreign key (ticket_category_id) references ticket_category (id)
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

create procedure createSiteNode(
    $parent_key varchar(80),
    $ukey varchar(80),
    $name varchar(200),
    $path varchar(200))
begin
    declare pid smallint;
    if ($parent_key is not null) then
        select id from site_node where ukey = $parent_key into pid;
    end if;
    insert into site_node (ukey, name, path, parent_id) values ($ukey, $name, $path, pid);
end //

create procedure createTicket(
    $status_key varchar(80),
    $customer_username varchar(20),
    $category_key varchar(80),
    $description varchar(4000))
begin
    declare ticket_status_id tinyint;
    declare ticket_category_id tinyint;
    select id from ticket_status where ukey = $status_key into ticket_status_id;
    select id from ticket_category where ukey = $category_key into ticket_category_id;
    insert into ticket (ticket_status_id, customer_username, ticket_category_id, description) values
        (ticket_status_id, $customer_username, ticket_category_id, $description);
end //

delimiter ;


-- =====================================================================================================================
-- Reference data
-- =====================================================================================================================

call createSiteNode(null, 'home', 'Home', '/');
call createSiteNode('home', 'login', 'Log In', '/login');
call createSiteNode('home', 'logout', 'Log Out', '/logout');
call createSiteNode('home', 'accessDenied', 'Access Denied', '/accessdenied');
call createSiteNode('home', 'tickets', 'Tickets', '/tickets');
call createSiteNode('tickets', 'newTicket', 'New Ticket', '/tickets/new');
call createSiteNode('tickets', 'ticketDetails', 'Ticket {id}', '/tickets/{id}');
call createSiteNode('home', 'knowledgeBase', 'Knowledge Base', '/knowledgebase');
call createSiteNode('home', 'approvals', 'Approvals', '/approvals');
call createSiteNode('home', 'tasks', 'Tasks', '/tasks');
call createSiteNode('home', 'surveys', 'Surveys', '/surveys');
call createSiteNode('home', 'reporting', 'Reporting', '/reporting');

insert into ticket_status (ukey, name) values
    ('open', 'Open'),
    ('ack', 'Acknowledged'),
    ('in_progress', 'In progress'),
    ('rejected', 'Rejected'),
    ('closed', 'Closed');
    
insert into ticket_category (ukey, name) values
    ('billing', 'Billing'),
    ('general', 'General'),
    ('password', 'Password'),
    ('product', 'Product');


-- =====================================================================================================================
-- Sample data
-- =====================================================================================================================

insert into user (username, password, first_name, last_name, email) values
    ('willie', 'willie', 'Willie', 'Wheeler', 'willie@example.com'),
    ('john', 'john', 'John', 'Wheeler', 'john@example.com'),
    ('josh', 'josh', 'Josh', 'White', 'josh@example.com');

call createTicket('open', 'paul', 'billing', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vestibulum tempus congue. Aenean rhoncus pellentesque nisi fringilla mollis. Morbi a turpis posuere tellus dictum vehicula. Phasellus lacinia pellentesque felis, non pellentesque sem eleifend vitae. Phasellus sollicitudin commodo massa id dignissim. Aliquam auctor leo eget erat rhoncus sit amet blandit nisi luctus. Phasellus id nunc a augue lobortis mattis. Aenean commodo lectus in elit fringilla at tincidunt eros dapibus. Aliquam erat volutpat. In hac habitasse platea dictumst. In hac habitasse platea dictumst. Sed eu dolor nulla. Aliquam dictum, sapien at posuere egestas, orci magna aliquam ipsum, sit amet pellentesque mauris eros sit amet nibh.');
call createTicket('ack', 'aimee', 'password', 'Can''t change my password.');
call createTicket('open', 'paul', 'general', 'Mauris sagittis, arcu quis molestie ultricies, neque erat vulputate tortor, dapibus venenatis dolor sapien eget sem. Nulla quis neque quis erat iaculis accumsan ac eget nunc. Proin eu lorem sem. Donec condimentum tincidunt blandit. Proin lacinia orci a libero suscipit ut porttitor metus porttitor. Aliquam mattis nibh et nisl vulputate semper. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean vulputate eros nec libero tristique commodo dictum nibh aliquet. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.');
