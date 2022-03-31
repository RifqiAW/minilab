package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long>, JpaSpecificationExecutor<Sales> {

    @Modifying
    @Query(value = "insert into mst_sales(sales_id, sales_name, dealer_code, supervisor_id, sales_gender, sales_email, sales_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    @Transactional
    void save(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);

    @Modifying
    @Query(value = "update mst_sales set sales_name = ?2, dealer_code = ?3, supervisor_id = ?4, sales_gender = ?5, sales_email = ?6, sales_status = ?7 where sales_id = ?1", nativeQuery = true)
    @Transactional
    void update(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);

    @Query(value = "select * from mst_sales where sales_id = ?1", nativeQuery = true)
    Sales getBySalesId(String salesId);

    Optional<Sales> findBySalesId(String id);

    Page<Sales> findAll(Specification<Sales> spec, Pageable pageable);
}
