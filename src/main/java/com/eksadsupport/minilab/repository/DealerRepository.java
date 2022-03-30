package com.eksadsupport.minilab.repository;


import com.eksadsupport.minilab.domain.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<Dealer,String > {

    @Modifying
    @Query(value = "insert into mst_dealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_ext_code,dealer_status)values(:dealerId,:dealerName,:dealerClass,:telpNumber,:alamat,:dealerExtCode,:dealerStatus)", nativeQuery = true)
    int CreateDealer(String dealerId, String dealerName, String dealerClass, String telpNumber, String alamat, String dealerExtCode, String dealerStatus);

    @Modifying
    @Query(value = "update mst_dealer set dealer_name=:dealerName where dealer_code=:dealerId", nativeQuery = true)
    int UpdateDealer(String dealerName, String dealerId);

    @Query(value = "select*from mst_dealer", nativeQuery = true)
    List<Dealer> listdealer();

    @Query(value = "select * from mst_dealer where dealer_code=:dealerId", nativeQuery = true)
    Optional<Dealer> DealerById(String dealerId);

    @Query(value = "select * from mst_dealer where dealer_code like %:dealerId% and lower(dealer_status)=:dealerStatus and lower (dealer_name)like %:dealerName% limit=:limit offset=:offset", nativeQuery = true)
    Optional<Dealer> ViewDealer(String dealerId, String dealerName, String dealerStatus, int limit, int offset);

    Optional<Dealer> findById(String dealerId);

}