CREATE DATABASE acme_bank;

CREATE TABLE accounts(
    account_id                  varchar(30) not null,
    account_name                varchar(50),
    balance                     float,
    constraint                  accounts_pk primary key (account_id)
);

INSERT INTO accounts(account_id, account_name, balance) VALUES ('V9L3Jd1BBI', 'fred', 100);
INSERT INTO accounts(account_id, account_name, balance) VALUES ('fhRq46Y6vB', 'barney', 300);
INSERT INTO accounts(account_id, account_name, balance) VALUES ('uFSFRqUpJy', 'wilma', 1000);
INSERT INTO accounts(account_id, account_name, balance) VALUES ('ckTV56axff', 'betty', 1000);
INSERT INTO accounts(account_id, account_name, balance) VALUES ('Qgcnwbshbh', 'pebbles', 50);
INSERT INTO accounts(account_id, account_name, balance) VALUES ('if9l185l18', 'bambam', 50);