DROP TABLE IF EXISTS mst_sales CASCADE
DROP TABLE IF EXISTS trx_order CASCADE
DROP VIEW IF EXISTS vw_mst_sales CASCADE
DROP TABLE IF EXISTS mst_dealer CASCADE
DROP VIEW IF EXISTS vw_mst_dealer CASCADE
DROP TABLE IF EXISTS mst_ppn CASCADE
DROP TABLE IF EXISTS mst_customer CASCADE
DROP TABLE IF EXISTS mst_unit CASCADE
DROP TABLE IF EXISTS vw_mst_unit CASCADE

CREATE TABLE IF NOT EXISTS mst_dealer(dealer_code VARCHAR(50) NOT NULL, dealer_name VARCHAR(255) NOT NULL, dealer_class VARCHAR(10) NOT NULL CHECK (dealer_class IN ('H123','H23')),telp_number VARCHAR(255) NOT NULL,alamat VARCHAR(512) NOT NULL,dealer_status VARCHAR(10) NOT NULL CHECK(dealer_status IN('ACTIVE','INACTIVE')),dealer_ext_code VARCHAR(50),PRIMARY KEY (dealer_code))

CREATE TABLE IF NOT EXISTS mst_sales(sales_id VARCHAR(50) NOT NULL, sales_name VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, supervisor_id VARCHAR(50), sales_gender VARCHAR(4) NOT NULL CHECK(sales_gender IN ('GTLK', 'GTPR')), sales_email VARCHAR(255) NOT NULL, sales_status VARCHAR(10) NOT NULL CHECK(sales_status IN ('ACTIVE', 'INACTIVE')), PRIMARY KEY (sales_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (supervisor_id) REFERENCES mst_sales(sales_id))

--CREATE TABLE IF NOT EXISTS trx_order(order_id VARCHAR(50) NOT NULL, unit_code VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, sales_id VARCHAR(50) NOT NULL, customer_id VARCHAR(50) NOT NULL, minimum_payment FLOAT(8) NOT NULL, total_value FLOAT(8) NOT NULL, order_value FLOAT(8) NOT NULL, offtheroad_value FLOAT(8) NOT NULL, order_total_discount FLOAT(8) NOT NULL, ppn FLOAT(8) NOT NULL, plat_nomor VARCHAR(50), nomor_mesin VARCHAR(50), nomor_rangka VARCHAR(50), is_leasing VARCHAR(10) CHECK(is_leasing IN ('YES', 'NO')), payment_status VARCHAR(50) NOT NULL CHECK(payment_status IN ('FULLY PAID', 'NOT PAID', 'PARTIAL PAID')), unit_status VARCHAR(50) NOT NULL CHECK(unit_status IN ('IN_STOCK', 'INDENT_PROCESS', 'UNIT_ RECEIVED', 'READY_FOR_DELIVERY', 'RECEIPT_BY_CUSTOMER')), PRIMARY KEY (order_id), FOREIGN KEY (unit_code) REFERENCES mst_unit(unit_code), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (sales_id) REFERENCES mst_sales(sales_id), FOREIGN KEY (customer_id) REFERENCES mst_customer(customer_id))

CREATE VIEW vw_mst_dealer AS select * from mst_dealer

CREATE VIEW vw_mst_sales AS select * from mst_sales

CREATE TABLE IF NOT EXISTS mst_ppn(ppn_id VARCHAR(50) NOT NULL, Description VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, effective_start_date Date NOT NULL, effective_end_date Date, ppn_rate float(8) NOT NULL, ppn_rate_previous float(8), ppn_status VARCHAR(10) NOT NULL CHECK(ppn_status IN ('ACTIVE', 'INACTIVE')), PRIMARY KEY (ppn_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code))

CREATE TABLE IF NOT EXISTS mst_customer(customer_id VARCHAR(50) NOT NULL, customer_address VARCHAR(512) NOT NULL, customer_email VARCHAR(255), customer_gender VARCHAR(4) NOT NULL CHECK(customer_gender IN ('GTLK', 'GTPR')), customer_hp_number VARCHAR(255), customer_kk VARCHAR(50) NOT NULL, customer_name VARCHAR(255) NOT NULL, customer_nik VARCHAR(50) NOT NULL, customer_status VARCHAR(10) NOT NULL CHECK(customer_status IN ('ACTIVE', 'INACTIVE')), customer_telp_number VARCHAR(50), sales_id VARCHAR(50) NOT NULL, dealer_code VARCHAR(50) NOT NULL, PRIMARY KEY (customer_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code), FOREIGN KEY (sales_id) REFERENCES mst_sales(sales_id))

CREATE VIEW vw_mst_customer AS select * from mst_customer

CREATE TABLE IF NOT EXISTS mst_unit(unit_id VARCHAR(50) NOT NULL, unit_series_name VARCHAR(255) NOT NULL, dealer_code VARCHAR(50) NOT NULL, unit_quantity INTEGER, unit_color VARCHAR(512) NOT NULL, unit_status VARCHAR(10) NOT NULL CHECK(unit_status IN ('ACTIVE', 'INACTIVE')), average_cost FLOAT(8) NOT NULL, PRIMARY KEY (unit_id), FOREIGN KEY (dealer_code) REFERENCES mst_dealer(dealer_code))

CREATE VIEW vw_mst_unit AS select * from mst_unit