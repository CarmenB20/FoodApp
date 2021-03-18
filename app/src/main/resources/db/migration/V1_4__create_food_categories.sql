CREATE TABLE IF NOT EXISTS food_categories(
 created_on     timestamp   default current_timestamp,
 id             SERIAL      PRIMARY KEY,
 name           varchar(255) not null,
 restaurant_id   int8        references restaurants(id)
);