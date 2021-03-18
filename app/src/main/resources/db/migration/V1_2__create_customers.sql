CREATE TABLE IF NOT EXISTS customers(
 created_on    timestamp   default current_timestamp,
 id             SERIAL      PRIMARY KEY,
 name           varchar(255) not null,
 phone_number   varchar(255) not null,
 address        varchar(255) not null,
 user_id        int8        references users(id)
);