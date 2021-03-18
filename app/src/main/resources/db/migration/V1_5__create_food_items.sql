create table if not exists food_items(
 created_on     timestamp      default current_timestamp,
 id             SERIAL         PRIMARY KEY,
 name           varchar(255)   not null,
 description    varchar(255)   not null,
 price          DECIMAL(10,2)  not null,
 weight         DECIMAL(10,2)  not null,
 food_category_id  int8          references food_categories(id)
);