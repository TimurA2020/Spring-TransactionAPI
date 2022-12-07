# Spring-TransactionAPI

Transaction API

How to use: 
You must have PgAdmin installed and have database called "transactions" created (can be changed to a different name)
Default username and password = "postgres"
Default server.port = 8000
These things can be changed in application.properties file

By default the API will have three accounts (ids: 1, 2, 3)

You can interact with the API within the project's html files (view) (send get and post requests)

Endpoints:
- Set account limit: "api/accounts/add" (properties: 'id', 'product_limit', 'service_limit') (METHOD POST) 
Example
  { 'id' : 1,
    'product_limit' : 200,
    'service_limit' : 100
    };
- Add transaction: "api/transactions/add" (properties: 'account_from', 'account_to', 'currency_shortname', 'sum', 'expense_category') (METHOD POST)
Example
          {
            "account_from": 1,
            "account_to": 2,
            "currency_shortname": 'KZT',
            "sum": 300,
            "expense_category": 'product'
          };
- Get Current exchange rate: 
USD/KZT -> api/exchangerate/fromusdtokzt
USD/RUB -> api/exchangerate/fromusdtorub
//It will give you today's exhange rate, if it's not available, then 'previous close'
- Get Exceeded transactions: "api/transactions/exceeded/{accountId}"

Technologies used{
  -Spring boot
  -Spring Data JPA/Hibernate
  -PostgreSQL
  -Junit
  -h2 database for testing
  -Liquibase
  -Lombok
}

