CREATE TABLE transactions(
    id SERIAL PRIMARY KEY NOT NULL,
    account_from INT NOT NULL,
    account_to INT NOT NULL,
    currency_shortname INT NOT NULL,
    sum NUMERIC(6, 2),
    expense_category TEXT NOT NULL,
    datetime TIMESTAMP WITHOUT TIME ZONE,
    limit_exceeded BOOLEAN NOT NULL
)