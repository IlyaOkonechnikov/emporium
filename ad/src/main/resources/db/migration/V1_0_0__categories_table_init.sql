create table if not exists public.category
(
    id        serial  not null primary key,
    name      varchar not null,
    parent_id integer default 0
);

create unique index category_id_uindex
    on public.category (id);