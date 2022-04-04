DROP TABLE IF EXISTS mst_sales CASCADE
DROP TABLE IF EXISTS trx_order CASCADE
DROP VIEW IF EXISTS vw_mst_sales CASCADE
DROP TABLE IF EXISTS mst_dealer CASCADE
DROP VIEW IF EXISTS vw_mst_dealer CASCADE
DROP VIEW IF EXISTS vw_trx_order CASCADE
DROP TABLE IF EXISTS mst_ppn CASCADE
DROP TABLE IF EXISTS vw_mst_ppn CASCADE
DROP TABLE IF EXISTS mst_customer CASCADE
DROP TABLE IF EXISTS mst_unit CASCADE
DROP TABLE IF EXISTS vw_mst_unit CASCADE
DROP TABLE IF EXISTS vw_mst_customer CASCADE

CREATE TABLE IF NOT EXISTS mst_dealer(dealer_code VARCHAR(50) NOT NULL, dealer_name VARCHAR(255) NOT NULL, dealer_class VARCHAR(10) NOT NULL CHECK (dealer_class IN ('H123','H23')),telp_number VARCHAR(255) NOT NULL,alamat VARCHAR(512) NOT NULL,dealer_status VARCHAR(10) NOT NULL CHECK(dealer_status IN('ACTIVE','INACTIVE')),dealer_ext_code VARCHAR(50),PRIMARY KEY (dealer_code))

CREATE TABLE IF NOT EXISTS mst_sales(sales_id VARCHAR(50) NOT NULL, sales_name VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, supervisor_id VARCHAR(50), sales_gender VARCHAR(4) NOT NULL CHECK(sales_gender IN ('GTLK', 'GTPR')), sales_email VARCHAR(255) NOT NULL, sales_status VARCHAR(10) NOT NULL CHECK(sales_status IN ('ACTIVE', 'INACTIVE')), PRIMARY KEY (sales_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (supervisor_id) REFERENCES mst_sales(sales_id))

CREATE VIEW vw_mst_dealer AS select * from mst_dealer

CREATE VIEW vw_mst_sales AS select * from mst_sales

--CREATE VIEW vw_mst_sales AS select sales_id as sales_id, sales_name as salesName, dealer_code as dealerCode, supervisor_id as supervisorId, sales_gender as salesGender, sales_email as salesEmail, sales_status as salesStatus from mst_sales

CREATE TABLE IF NOT EXISTS mst_ppn(ppn_id VARCHAR(50) NOT NULL, Description VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, effective_start_date TIMESTAMP NOT NULL, effective_end_date TIMESTAMP, ppn_rate DOUBLE PRECISION NOT NULL, ppn_rate_previous DOUBLE PRECISION, ppn_status VARCHAR(10) NOT NULL CHECK(ppn_status IN ('ACTIVE', 'INACTIVE')), PRIMARY KEY (ppn_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code))

CREATE VIEW vw_mst_ppn AS select * from mst_ppn

CREATE TABLE IF NOT EXISTS mst_customer(customer_id VARCHAR(50) NOT NULL, customer_address VARCHAR(512) NOT NULL, customer_email VARCHAR(255), customer_gender VARCHAR(4) NOT NULL CHECK(customer_gender IN ('GTLK', 'GTPR')), customer_hp_number VARCHAR(255), customer_kk VARCHAR(50) NOT NULL, customer_name VARCHAR(255) NOT NULL, customer_nik VARCHAR(50) NOT NULL, customer_status VARCHAR(10) NOT NULL CHECK(customer_status IN ('ACTIVE', 'INACTIVE')), customer_telp_number VARCHAR(50), sales_id VARCHAR(50) NOT NULL, dealer_code VARCHAR(50) NOT NULL, PRIMARY KEY (customer_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (sales_id) REFERENCES mst_sales(sales_id))

CREATE VIEW vw_mst_customer AS select * from mst_customer

CREATE TABLE IF NOT EXISTS mst_unit(unit_id VARCHAR(50) NOT NULL, unit_series_name VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, unit_quantity INTEGER, unit_color VARCHAR(512) NOT NULL, unit_status VARCHAR(10) NOT NULL CHECK(unit_status IN ('ACTIVE', 'INACTIVE')), average_cost DOUBLE PRECISION NOT NULL, PRIMARY KEY (unit_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code))

CREATE VIEW vw_mst_unit AS select * from mst_unit

CREATE TABLE IF NOT EXISTS trx_order(order_id VARCHAR(50) NOT NULL, unit_id VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, sales_id VARCHAR(50) NOT NULL, customer_id VARCHAR(50) NOT NULL, minimum_payment DOUBLE PRECISION NOT NULL, total_value DOUBLE PRECISION NOT NULL, order_value DOUBLE PRECISION NOT NULL, offtheroad_value DOUBLE PRECISION NOT NULL, order_total_discount DOUBLE PRECISION NOT NULL, ppn DOUBLE PRECISION NOT NULL, plat_nomor VARCHAR(50), nomor_mesin VARCHAR(50), nomor_rangka VARCHAR(50), is_leasing VARCHAR(10) CHECK(is_leasing IS NULL OR is_leasing IN ('YES', 'NO')), payment_status VARCHAR(50) NOT NULL CHECK(payment_status IN ('FULLY_PAID', 'NOT_PAID', 'PARTIAL_PAID')), unit_status VARCHAR(50) NOT NULL CHECK(unit_status IN ('IN_STOCK', 'INDENT_PROCESS', 'UNIT_RECEIVED', 'READY_FOR_DELIVERY', 'RECEIPT_BY_CUSTOMER')), PRIMARY KEY (order_id), FOREIGN KEY (unit_id) REFERENCES mst_unit(unit_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (sales_id) REFERENCES mst_sales(sales_id), FOREIGN KEY (customer_id) REFERENCES mst_customer(customer_id))

CREATE VIEW vw_trx_order AS select * from trx_order


alter table mst_ppn alter column effective_start_date set default now()