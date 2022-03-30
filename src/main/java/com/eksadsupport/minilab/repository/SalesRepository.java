package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Modifying
    @Query(value = "insert into mst_sales(sales_id, sales_name, dealer_code, supervisor_id, sales_gender, sales_email, sales_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    @Transactional
    void save(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);

    @Modifying
    @Query(value = "update mst_sales set sales_name = ?2, dealer_code = ?3, supervisor_id = ?4, sales_gender = ?5, sales_email = ?6, sales_status = ?7 where sales_id = ?1", nativeQuery = true)
    @Transactional
    void update(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);


//    @Query(value = "insert into mst_sales(sales_id, sales_name, dealer_code, supervisor_id, sales_gender, sales_email, sales_status) values (?1, ?2, ?3, ?4, ?5, ?6, ?7) returning *", nativeQuery = true)
//    @Transactional
//    Sales save(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);

//    @Query(value = "update mst_sales set sales_name = ?2, dealer_code = ?3, supervisor_id = ?4, sales_gender = ?5, sales_email = ?6, sales_status = ?7 where sales_id = ?1 returning *", nativeQuery = true)
//    @Transactional
//    Sales update(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus);

//    @Query(value = "select * from vw_mst_sales where dealer_code = ?1 and sales_status = ?2 and sales_name = ?3 limit ?4 offset ?5", nativeQuery = true)
//    List<Sales> listAll(String dealerId, String salesStatus, String salesName, int limit, int offset);

    @Query(value = "select * from vw_mst_sales where dealer_code like %?1% and sales_status = ?2 and sales_name like %?3%", nativeQuery = true)
    Page<Sales> listAll(String dealerId, String salesStatus, String salesName, Pageable pageable);

    @Query(value = "select * from vw_mst_sales", nativeQuery = true)
    Page<Sales> listEverything(Pageable pageable);

    @Query(value = "select * from mst_sales where sales_id = ?1", nativeQuery = true)
    Sales getBySalesId(String salesId);

    Optional<Sales> findBySalesId(String id);
}
