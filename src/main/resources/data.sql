insert into mst_dealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_status,dealer_ext_code)values('100000','AHASS Jakarta','H123','021-87654321','Jl.Kuningan Raya','ACTIVE','103553');

insert into mst_sales(sales_id, sales_name, dealer_code, supervisor_id, sales_gender, sales_email, sales_status) values ('M1', 'Offline', '100000', NULL, 'GTLK', 'asd', 'INACTIVE');

insert into mst_ppn(ppn_id, description, dealer_code, effective_start_date, effective_end_date, ppn_rate, ppn_rate_previous, ppn_status) values ('1', 'Ppn tiga', '100000', '14-02-2022 16:00', '18-02-2022 19:30', 11.5, 10, 'ACTIVE');

insert into mst_customer(customer_id, customer_name, dealer_code, customer_gender, customer_nik, customer_kk, customer_email, customer_address, customer_telp_number, customer_hp_number, sales_id, customer_status) values ('c1', 'Stonk', '100000', 'GTLK', 'xx', 'xxx', 'somewhere@gmail.com', 'above the ground beneath the sky', '666', '6969', 'M1', 'INACTIVE');

insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost) values ('202203201010570000001', 'CBR 150', '100000', '10', 'MERAH', 'INACTIVE', 25000000);

insert into trx_order(order_id, unit_id, dealer_code, sales_id, customer_id, minimum_payment, total_value, order_value, offtheroad_value, order_total_discount, ppn, plat_nomor, nomor_mesin, nomor_rangka, is_leasing, payment_status, unit_status) values ('o1', '202203201010570000001', '100000', 'M1', 'c1', 5.0, 3.0, 2.0, 1.0, 6.0, 7.0, 'PL4T', '12', '11', null, 'FULLY_PAID', 'UNIT_RECEIVED');

