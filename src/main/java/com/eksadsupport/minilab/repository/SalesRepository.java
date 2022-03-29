package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Modifying
    @Query(value = "insert into mst_sales(sales_id, sales_name, dealer_code, supervisor_id, sales_gender, sales_email, sales_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7) ", nativeQuery = true)
    @Transactional
    void save(String salesId, String salesName, String dealerCode, String supervisorId, String salesGender, String salesEmail, String salesStatus);

    @Modifying
    @Query(value = "update mst_sales set sales_name = ?2, dealer_code = ?3, supervisor_id = ?4, sales_gender = ?5, sales_email = ?6, sales_status = ?7 where sales_id = ?1", nativeQuery = true)
    @Transactional
    void update(String salesId, String salesName, String dealerCode, String supervisorId, String salesGender, String salesEmail, String salesStatus);

    @Query(value = "select * from mst_sales where dealer_code = ?1 and lower(sales_status) = ?2 and lower(sales_name) like '%?3%' limit ?4 offset ?5", nativeQuery = true)
    List<Sales> listAll(String dealerCode, String salesStatus, String salesName, int limit, int offset);

    @Query(value = "select * from from mst_sales where sales_id = ?1 LIMIT 1")
    Sales getBySalesId(String salesId);
}
