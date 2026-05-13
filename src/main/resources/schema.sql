drop table if exists tb_wallettransaction;
drop table if exists tb_transactiontype;
drop table if exists tb_wallet;
drop table if exists tb_user;

drop sequence if exists gen_user;
drop sequence if exists gen_wallet;
drop sequence if exists gen_wallettransaction;

CREATE TABLE tb_user (
    id_user numeric(18) not null PRIMARY KEY,
    tx_name VARCHAR(255) NOT NULL,
    tx_email VARCHAR(255) NOT NULL UNIQUE,
    dt_created TIMESTAMP
);

CREATE TABLE tb_wallet (
    id_wallet numeric(18) not null PRIMARY KEY,
    cd_user numeric(18) NOT NULL,
    vl_balance NUMERIC(19,2) DEFAULT 0,
    dt_created TIMESTAMP,

    CONSTRAINT fk_wallet_user
        FOREIGN KEY (cd_user)
        REFERENCES tb_user(id_user)
);

CREATE TABLE tb_transactiontype (
    id_transactiontype numeric(18) PRIMARY KEY,
    tx_name VARCHAR(100) NOT NULL
);

CREATE TABLE tb_wallettransaction (
    id_wallettransaction numeric(18) not null PRIMARY KEY,
    cd_wallet numeric(18) NOT NULL,
    cd_transactiontype numeric(18) NOT NULL,
    vl_amount NUMERIC(19,2) NOT NULL,
    dt_created_at TIMESTAMP,

    CONSTRAINT fk_transaction_wallet
        FOREIGN KEY (cd_wallet)
        REFERENCES tb_wallet(id_wallet),

    CONSTRAINT fk_transaction_transactiontype
        FOREIGN KEY (cd_transactiontype)
        REFERENCES tb_transactiontype(id_transactiontype)
);

CREATE SEQUENCE public.gen_user
	INCREMENT BY 1
	MINVALUE 1
	START 1;

CREATE SEQUENCE public.gen_wallet
	INCREMENT BY 1
	MINVALUE 1
	START 1;

CREATE SEQUENCE public.gen_wallettransaction
	INCREMENT BY 1
	MINVALUE 1
	START 1;
	

INSERT INTO tb_transactiontype (
    id_transactiontype,
    tx_name
) VALUES
(1, 'DEPOSIT'),
(2, 'WITHDRAW'),
(3, 'TRANSFER IN'),
(4, 'TRANSFER OUT'),
(5, 'REFUND');