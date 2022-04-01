package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, String>, JpaSpecificationExecutor<Unit> {

    @Modifying
    @Query(value = "insert into mst_unit(unit_id, unit_series_name, dealer_code, unit_quantity, unit_color, unit_status, average_cost) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    @Transactional
    void save(String unitId, String unitSeriesName, String dealerId, String unitQuantity, String unitColor, String unitStatus, String averageCost);


    @Modifying
    @Query(value = "update mst_unit set unit_series_name = ?2, dealer_id = ?3, unit_quantity = ?4, unit_color = ?5, unit_status = ?6, average_cost = ?7 where unit_id = ?1", nativeQuery = true)
    @Transactional
    void update(String unitId, String unitSeriesName, String dealerId, String unitQuantity, String unitColor, String unitStatus, double averageCost);


    @Query(value = "select * from mst_unit where unit_id = ?1", nativeQuery = true)
    Unit getByUnitId(String unitId);

    Optional<Unit> findByUnitId(String id);

    Page<Unit> findAll(Specification<Unit> spec, Pageable pageable);
}
