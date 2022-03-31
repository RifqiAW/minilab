package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DealerRepository extends JpaRepository<Dealer,String > {

    @Modifying
    @Query(value = "insert into mst_dealer(dealer_code,dealer_name,dealer_class,telp_number,alamat,dealer_ext_code,dealer_status)values(:dealerId,:dealerName,:dealerClass,:telpNumber,:alamat,:dealerExtCode,:dealerStatus)",nativeQuery = true)
    int CreateDealer(String dealerId,String dealerName,String dealerClass,String telpNumber,String alamat,String dealerExtCode, String dealerStatus);

    @Modifying
    @Query(value = "update mst_dealer set dealer_name=:dealerName where dealer_code=:dealerId",nativeQuery = true)
    int UpdateDealer(String dealerName,String dealerId);


    @Query(value = "select * from mst_dealer where dealer_code=:dealerId",nativeQuery = true)
    Optional<Dealer > DealerById(String dealerId);

    @Query(value = "select * from public.mst_dealer where mst_dealer.dealer_code like %:dealerId% and mst_dealer.dealer_status =:dealerStatus and mst_dealer.dealer_name like %:dealerName% limit :limit offset :offset" ,nativeQuery = true)
    List<Dealer> SelectDealer(String dealerId, String dealerStatus, String dealerName, int limit, int offset);

    @Query(value = "select * from vw_mst_dealer where dealer_code like %:dealerId% and dealer_status =:dealerStatus and dealer_name like %:dealerName% limit :limit offset :offset" ,nativeQuery = true)
    List<Dealer> ViewDealer(String dealerId,String dealerStatus,String dealerName,int limit, int offset);

    @Query(value = "select * from vw_mst_dealer where dealer_code like %:dealerId% and dealer_status =:dealerStatus and dealer_name like %:dealerName% limit :limit offset :offset" ,nativeQuery = true)
    Page<Dealer> ListAllDealer(String dealerId, String dealerStatus, String dealerName, Pageable pageable);

    Optional<Dealer> findById(String dealerId);


}
