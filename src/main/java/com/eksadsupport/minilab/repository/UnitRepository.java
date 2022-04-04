package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Customer;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.domain.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, String>, JpaSpecificationExecutor<Unit> {

    @Modifying
    @Query(value = "insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    @Transactional
    void save(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost);


    @Modifying
    @Query(value = "update mst_unit set unit_series_name = ?2, dealer_code = ?3, unit_quantity = ?4, unit_color = ?5, unit_status = ?6, average_cost = ?7 where unit_id = ?1", nativeQuery = true)
    @Transactional
    void update(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost);

    @Transactional
    @Query(value = "SELECT * FROM vw_mst_unit where  dealer_code =?1 and lower(customer_name) like %?2% LIMIT ?3 OFFSET ?4",nativeQuery = true)
    List<Unit> listAll(String dealerId, String unitSeriesName, int limit, int offset);

    @Query(value = "select * from mst_unit where unit_id = ?1", nativeQuery = true)
    Unit getByUnitId(String unitId);

    @Query(value = "select * from mst_sales where sales_id like ?1 or dealer_code like ?2", nativeQuery = true)
    List<Unit> testingParam(String unitId, String dealerId);

    Optional<Unit> findByUnitId(String id);

    Page<Unit> findAll(Specification<Unit> spec, Pageable pageable);
}
