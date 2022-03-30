package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    SalesRepository sr;

    public GetSales saveSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        sr.save(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
        return new GetSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
    }

    public GetSales updateSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        sr.update(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
        return new GetSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
    }

//    public List<Sales> listAll(String dealerId, String salesStatus, String salesName, int limit, int offset){
//        return sr.listAll(dealerId, salesStatus, salesName, limit, offset);
//    }

    public Page<Sales> listAll(String dealerId, String salesStatus, String salesName, Pageable pageable){
        return sr.listAll(dealerId, salesStatus, salesName, pageable);
    }

    public Page<Sales> listEverything(Pageable pageable){
        return sr.listEverything(pageable);
    }

    public GetSales get(String salesId){
        Sales sale = sr.getBySalesId(salesId);
        return new GetSales(sale.getSalesId(), sale.getSalesName(),
                sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
    }

    public Optional<Sales> findBySalesId(String salesId){
        return sr.findBySalesId(salesId);
    }
}
