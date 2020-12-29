SELECT setval(pg_get_serial_sequence('categories', 'id'),
    coalesce(max(id)+1, 1), false) FROM categories;