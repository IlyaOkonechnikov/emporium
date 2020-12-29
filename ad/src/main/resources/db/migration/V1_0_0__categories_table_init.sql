create table if not exists public.categories
(
    id        serial  not null primary key,
    name      varchar not null,
    parent_id integer default 0
);

create unique index categories_id_uindex
    on public.categories (id);