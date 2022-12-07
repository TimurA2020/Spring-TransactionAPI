CREATE TABLE accounts(
    id SERIAL PRIMARY KEY NOT NULL,
    product_limit NUMERIC(9, 2) NOT NULL,
    service_limit NUMERIC(9, 2) NOT NULL
);