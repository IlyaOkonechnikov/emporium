create table public.roles
(
    id   integer not null
        constraint role_table_pkey primary key,
    name varchar(256)
);
insert into public.roles (id, name)
VALUES (1, 'ROLE_ADMIN');
insert into public.roles (id, name)
VALUES (2, 'ROLE_USER');

create table public.users
(
    id       bigint not null
        constraint user_table_pkey primary key,
    username varchar(256),
    password varchar(256),
    role_id  integer references roles,
    email    varchar(64),
    enabled  boolean
);

INSERT INTO public.users (id, username, password, role_id, email, enabled)
VALUES (1, 'baburik', '$2a$10$qYYRMkrdakXfWWOH86N.W.uan10LvTLdKhZLsDFcnU3UzCuouGQwi', 2, 'baburik@gmail.com', false);