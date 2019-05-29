create table banner
(
  id          integer       not null auto_increment primary key,
  name        varchar(255)  not null unique,
  price       decimal(8, 2) not null,
  category_id integer       not null,
  content     text          not null,
  deleted     bit(1)        not null
);

create table category
(
  id       integer      not null auto_increment primary key,
  name     varchar(255) not null unique,
  req_name varchar(255) not null unique,
  deleted  bit(1)       not null
);

create table request
(
  id         integer      not null auto_increment primary key,
  banner_id  integer      not null,
  user_agent text         not null,
  ip_address varchar(255) not null,
  date       datetime     not null
);

alter table banner
  add constraint banner_category_fk
    foreign key (category_id)
      references category (id);

alter table request
  add constraint request_category_fk
    foreign key (banner_id)
      references banner (id);