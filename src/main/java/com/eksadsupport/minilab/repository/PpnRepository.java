package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Ppn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PpnRepository extends JpaRepository<Ppn, String> {
    @Modifying
    @Query(value = "insert into mst_ppn (ppn_id, dealer_code, description, ppn_rate, ppn_rate_previous, " +
            "effective_start_date, effective_end_date, ppn_status)" +
            "values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    @Transactional
    void savePpn(String ppnId, String dealerId, String ppnDescription, Float ppnRate, Float ppnRatePrevious,
                 Date effectiveStartDate, Date effectiveEndDate, String ppnStatus);

    @Modifying
    @Query(value = "update mst_ppn set dealer_code = ?2, description = ?3, " +
            "ppn_rate = ?4, ppn_rate_previous = ?5, effective_start_date = ?6, effective_end_date= ?7, " +
            "ppn_status = ?8 where ppn_id = ?1", nativeQuery = true)
    @Transactional
    void updatePpn(String ppnId, String dealerId, String ppnDescription, Float ppnRate, Float ppnRatePrevious,
                   Date effectiveStartDate, Date effectiveEndDate, String ppnStatus);

    @Query(value = "select * from mst_ppn where ppn_id = ?1", nativeQuery = true)
    Ppn getByPpnId(String ppnId);

    @Transactional
    @Query(value = "SELECT * FROM mst_ppn where  dealer_code =?1 and lower(ppn_status) like %?2% LIMIT ?3 OFFSET ?4",nativeQuery = true)
    List<Ppn> listAll(String dealerId, String ppnStatus, int limit, int offset);

    @Query(value = "select ppn_id from mst_ppn where ppn_id = ?1", nativeQuery = true)
    String cekPPnId (String ppnId);

    @Query(value = "select dealer_code from mst_ppn where ppn_id= ?1",nativeQuery = true)
    String cekDealerId(String ppnId);

    @Query(value = "select description from mst_ppn where ppn_id= ?1",nativeQuery = true)
    String cekPpnDescription(String ppnId);

    @Query(value = "select ppn_rate from mst_ppn where ppn_id= ?1",nativeQuery = true)
    String cekPpnRate(String ppnId);

    @Query(value = "select ppn_rate_previous from mst_ppn where ppn_id= ?1",nativeQuery = true)
    String cekPpnRatePrevious(String ppnId);

    @Query(value = "select effective_start_date from mst_ppn where ppn_id= ?1",nativeQuery = true)
    Date cekeffectiveStartDate(String ppnId);

    @Query(value = "select effective_end_date from mst_ppn where ppn_id= ?1",nativeQuery = true)
    Date cekeffectiveEndDate(String ppnId);

    @Query(value = "select ppn_status from mst_ppn where ppn_id= ?1",nativeQuery = true)
    String cekPpnStatus(String ppnId);

    @Transactional
    @Query(value = "select dealer_code from mst_ppn where dealer_code = ?1 " +
            "and effective_start_date = ?2 " +
            "and ppn_status='ACTIVE' LIMIT 1", nativeQuery = true)
    Optional<Ppn> findActivePpn(String dealerId, Date queryDate);

    @Query(value = "select ppn_id from mst_ppn where dealer_code = ?1", nativeQuery = true)
    String cekPPnIdBydealer (String dealerId);

    @Transactional
    @Query(value = "SELECT * FROM mst_ppn where dealer_code =?1",nativeQuery = true)
    List<Ppn> listAllByDealer(String dealerId);

    Optional<Ppn> findByPpnId(String ppnId);

//    Page<Ppn> findAll(Specification<Ppn> spec, Pageable pageable);
}
