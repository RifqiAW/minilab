package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Modifying
    @Query(value = "INSERT INTO mst_customer (customer_id,customer_name,dealer_code,customer_gender," +
            "customer_nik,customer_kk,customer_email,customer_address,customer_status,sales_id) VALUES " +
            "(:customerId,:customerName,:dealerId,:customerGender," +
            ":customerNik,:customerKk,:customerEmail,:customerAddress,:customerStatus,:salesId)",nativeQuery = true)
    int addCustomer(String customerId,String customerName,String dealerId,String customerGender,
                    String customerNik,String customerKk,String customerEmail,String customerAddress,
                    String customerStatus,String salesId);
}
