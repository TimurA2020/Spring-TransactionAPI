CREATE TABLE exchangekz(
    id SERIAL PRIMARY KEY NOT NULL,
    rate NUMERIC(5, 2),
    date TIMESTAMP WITHOUT TIME ZONE
);