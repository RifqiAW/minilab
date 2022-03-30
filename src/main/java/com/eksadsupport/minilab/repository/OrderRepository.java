package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query(value = "insert into trx_order(order_id, unit_code, dealer_code, sales_id, customer_id, minimum_payment, total_value, order_value, offtheroad_value, order_total_discount, ppn, plat_nomor, nomor_mesin, nomor_rangka, is_leasing, payment_status, unit_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14, ?15, ?16, ?17) ", nativeQuery = true)
    @Transactional
    void save(String orderId, String unitCode, String dealerId, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderTotalDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus);

    @Modifying
    @Query(value = "update trx_order set unit_code = ?2, dealer_code = ?3, sales_id = ?4, customer_id = ?5, minimum_payment = ?6, total_value = ?7, order_value = ?8, offtheroad_value = ?9, order_total_discount = ?10, ppn = ?11, plat_nomor = ?12, nomor_mesin = ?13, nomor_rangka = ?14, is_leasing = ?15, payment_status = ?16, unit_status = ?17 where order_id = ?1", nativeQuery = true)
    @Transactional
    void update(String orderId, String unitCode, String dealerId, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderTotalDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus);

}
