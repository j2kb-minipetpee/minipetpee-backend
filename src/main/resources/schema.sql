create table comment (
     id bigint AUTO_INCREMENT PRIMARY KEY,
     content varchar(255) not null,
     member_id bigint,
     post_id bigint,
     created_at timestamp not null,
     updated_at timestamp
);

create table fan_comment (
     id bigint AUTO_INCREMENT PRIMARY KEY,
     content varchar(255) not null,
     homepee_id bigint not null,
     member_id bigint not null,
     created_at timestamp not null,
     updated_at timestamp
);

create table guest_note (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    content varchar(255) not null,
    visible boolean default 1 not null,
    member_id bigint,
    tab_id bigint,
    created_at timestamp not null,
    updated_at timestamp
);

create table homepee (
     id bigint AUTO_INCREMENT PRIMARY KEY,
     member_id bigint not null,
     gate_image_url varchar(255),
     title varchar(255) not null,
     visit_count integer default 0 not null
);

create table image (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    url varchar(255) not null,
    post_id bigint
);

create table member (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    birthday timestamp,
    email varchar(255) not null,
    gender varchar(255),
    name varchar(255) not null,
    password varchar(255) not null,
    personality varchar(255),
    profile_image_url varchar(255),
    species varchar(255),
    created_at timestamp not null,
    updated_at timestamp,
    deleted boolean default 0 not null
);

create table post (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    title varchar(255) not null,
    view_count integer default 0,
    content varchar(255) null,
    tab_id bigint,
    dtype varchar(31) not null,
    created_at timestamp not null,
    updated_at timestamp
);

create table star (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    fan_member_id bigint not null,
    star_member_id bigint not null,
    created_at timestamp not null
);

create table tab (
     id bigint AUTO_INCREMENT PRIMARY KEY,
     type varchar(255) not null,
     visible boolean default 1 not null,
     homepee_id bigint not null
);
