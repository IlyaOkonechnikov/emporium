create table if not exists emporium.categories
(
    id        serial  not null primary key,
    name      varchar not null,
    parent_id integer default 0
);

create unique index categories_id_uindex
    on emporium.categories (id);

INSERT INTO emporium.categories (id, name, parent_id)
VALUES (1, 'MainParent', null);