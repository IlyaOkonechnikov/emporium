SELECT setval(pg_get_serial_sequence('category', 'id'),
    coalesce(max(id)+1, 1), false) FROM category;