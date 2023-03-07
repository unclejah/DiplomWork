-- liquibase formatted sql

-- changeSet uj:1
create table users (
                    user_id  serial not null,
                    city varchar(255),
                    email varchar(255),
                    first_name varchar(255),
                    image varchar(255),
                    last_name varchar(255),
                    password varchar(255),
                    phone varchar(255),
                    reg_date varchar(255),
                    role varchar(255) CONSTRAINT role_check CHECK ( role IN ('USER', 'ADMIN')),
                    primary key (user_id)
                   );

-- changeSet uj:2
create table ads (
                    ads_id  serial not null,
                    description varchar(255),
                    image varchar(255),
                    price int4 not null,
                    title varchar(255),
                    user_id int4,
                    primary key (ads_id)
                 );


-- changeSet uj:3
create table comments (
                        id  serial not null,
                        created_at varchar(255),
                        text varchar(255),
                        user_id int4,
                        ads_id int4,
                        primary key (id)
                      );

-- changeSet uj:4
create table images (
                        id varchar(255) not null,
                        data oid,
                        file_size int4,
                        media_type varchar(255),
                        primary key (id)
                    );
