# Wallet API 💳

A simple digital wallet API built with Spring Boot and PostgreSQL that allows users to create wallets, perform deposits, withdrawals, and transfers between wallets.

This project was developed to demonstrate backend development skills using REST APIs, transaction management, database relationships, and financial operation handling.

---

## 🚀 Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

---

# 📌 Features

- Create users
- Create wallets
- Deposit money into a wallet
- Withdraw money from a wallet
- Transfer money between wallets
- List all transactions
- Transaction history with sender and receiver information
- Transaction persistence using relational database
- Validation for invalid financial operations
- Transaction management using `@Transactional`

---

# 📂 Project Structure

```bash
src/main/java/com/walletapi
│
├── api
├── service
├── repository
├── dto
├── model
└── enums
```

---

# 🗄️ Database Structure

## Main Tables

### `tb_user`

| Column | Type |
|---|---|
| id_user | BIGINT |
| tx_name | VARCHAR |
| tx_email | VARCHAR |
| dt_created | TIMESTAMP |

### `tb_wallet`

| Column | Type |
|---|---|
| id_wallet | BIGINT |
| cd_user | BIGINT |
| vl_balance | NUMERIC(19,2) |
| dt_created | TIMESTAMP |

### `tb_transactiontype`

| Column | Type |
|---|---|
| id_transactiontype | BIGINT |
| tx_name | VARCHAR |

### `tb_wallettransaction`

| Column | Type |
|---|---|
| id_wallettransaction | BIGINT |
| cd_wallet | BIGINT |
| cd_walletdestination | BIGINT |
| cd_transactiontype | BIGINT |
| vl_amount | NUMERIC(19,2) |
| dt_created_at | TIMESTAMP |

---

# 🔥 API Endpoints

## 👤 Users

### Create User

```http
POST /users/createUser
```

### Request Body

```json
{
  "userName": "Fernando",
  "email": "fernando@email.com"
}
```

---

### List All Users

```http
GET /users/listAllUsers
```

---

### Delete User

```http
DELETE /users/deleteUser
```

### Request Body

```json
1
```

---

# 💰 Wallets

## Create Wallet

```http
POST /wallets/createWallet
```

### Request Body

```json
{
  "userId": 1
}
```

---

## Deposit

```http
POST /wallets/deposit
```

### Request Body

```json
{
  "walletId": 1,
  "amount": 150.00
}
```

---

## Withdraw

```http
POST /wallets/withdraw
```

### Request Body

```json
{
  "walletId": 1,
  "amount": 50.00
}
```

---

## Transfer Between Wallets

```http
POST /wallets/transfer
```

### Request Body

```json
{
  "walletId": 1,
  "walletDestinationId": 2,
  "amount": 100.00
}
```

---

# 📜 Wallet Transactions

## List All Transactions

Returns all deposits, withdrawals, and transfers performed in the system.

```http
GET /wallet-transactions/listAllTransactions
```

### Example Response

```json
[
  {
    "userFrom": "Fernando",
    "transaction": "TRANSFER",
    "userReceived": "John",
    "amount": 100.00
  }
]
```

---

# ⚙️ Business Rules

- Wallet balance cannot become negative
- Deposit amount must be greater than zero
- Withdraw amount must be greater than zero and the wallet must have sufficient balance
- Transfer amount must be greater than zero and the wallet from must have sufficient balance
- Transfers require both source and destination wallets
- All financial operations are transactional
- Transaction history is stored for auditing purposes

---

# 🔒 Transaction Management

The project uses Spring transaction management with:

```java
@Transactional
```

This ensures database consistency during deposits, withdrawals, and transfers.

---

# ▶️ Running the Project

## Clone Repository

```bash
git clone <repository-url>
```

---

## Configure Database

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/walletdb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

# 🧪 Possible Tests

- Create users and wallets
- Perform deposits
- Perform withdrawals
- Transfer balance between wallets
- Validate insufficient balance scenarios
- List transaction history

---

# 📌 Future Improvements (suggestions)

- Implement authentication and authorization
- Add login and user registration endpoints
- Add role-based access control (ADMIN / USER)
- Add balance statement endpoint
