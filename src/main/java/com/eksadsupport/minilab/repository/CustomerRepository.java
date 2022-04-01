package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Customer;
import com.eksadsupport.minilab.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    @Transactional
    @Query(value = "INSERT INTO vw_mst_customer (customer_id,customer_name,dealer_code,customer_gender," +
            "customer_nik,customer_kk,customer_email,customer_address,customer_telp_number,customer_hp_number,customer_status,sales_id) VALUES " +
            "(:customerId,:customerName,:dealerId,:customerGender," +
            ":customerNik,:customerKk,:customerEmail,:customerAddress,:customerTelp,:customerHp,:customerStatus,:salesId) returning *",nativeQuery = true)
    int addCustomer(String customerId,String customerName,String dealerId,String customerGender,
                         String customerNik,String customerKk,String customerEmail,String customerAddress,
                         String customerTelp,String customerHp, String customerStatus,String salesId);

    @Modifying
    @Transactional
    @Query(value = " UPDATE vw_mst_customer SET customer_name=?1,dealer_code=?2,customer_gender=?3, " +
            "customer_nik=?4,customer_kk=?5,customer_email=?6,customer_address=?7," +
            "customer_telp_number=?8,customer_hp_number=?9,customer_status=?10,sales_id=?11 " +
            "WHERE customer_id=?12",nativeQuery = true)
    int updateCustomer(String customerName,String dealerId,String customerGender,
                       String customerNik,String customerKk,String customerEmail,String customerAddress,
                       String customerTelp,String customerHp,String customerStatus,String salesId,
                       String customerId);

    @Transactional
    @Query(value = "SELECT * FROM vw_mst_customer where  dealer_code =?1 and lower(customer_name) like %?2% LIMIT ?3 OFFSET ?4",nativeQuery = true)
    List<Customer> listAll(String dealerId, String customerName, int limit, int offset);


    @Query(value = "SELECT * FROM vw_mst_customer WHERE customer_id= ?1",nativeQuery = true)
    Optional<Customer> getCustomerById(String customerId);

    @Query(value = "SELECT * FROM vw_mst_customer WHERE customer_id= ?1",nativeQuery = true)
    Optional<Customer> getCustomerByIdV2(String customerId);



}
