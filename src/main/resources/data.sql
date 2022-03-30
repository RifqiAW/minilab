insert into mst_sales(sales_id, sales_name, supervisor_id, sales_gender, sales_email, sales_status) values ('M1', 'Offline', NULL, 'GTLK', 'asd', 'INACTIVE');

insert into mst_ppn (ppn_id, description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status) values ('P1', 'Ppn ke 1', '100012', '14-02-2022 16:00', '18-02-2022 19:30', 11.5, 10, 'INACTIVE');
insert into trx_order(order_id, unit_code, dealer_code, sales_id, customer_id, minimum_payment, total_value, order_value, offtheroad_value, order_total_discount, ppn, plat_nomor, nomor_mesin, nomor_rangka, is_leasing, payment_status, unit_status) values ('M1', 'unitcode', 'dealercode', 'M1', 'customerid', 5.0, 3.0, 2.0, 1.0, 6.0, 'PL4T', '11', 'NO', 'FULLY_PAID', 'UNIT_RECEIVED');
