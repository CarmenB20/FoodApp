create table if not exists orders(
 created_on          timestamp      default current_timestamp,
 id                  SERIAL         PRIMARY KEY,
 order_no            int8           not null,
 order_description   varchar(255)   not null,
 info                varchar(255)    not null,
 restaurant_id       int8           references restaurants(id),
 customer_id         int8           references customers(id)
);