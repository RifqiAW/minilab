package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.domain.ViewAllSales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.model.SalesSpecs;
import com.eksadsupport.minilab.model.ViewSalesSpecs;
import com.eksadsupport.minilab.repository.SalesRepository;
import com.eksadsupport.minilab.repository.ViewSalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    SalesRepository sr;

    @Autowired
    ViewSalesRepository vsr;

    public GetSales saveSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        sr.save(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
        Sales sale = sr.getBySalesId(salesId);
        try{
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }catch (Exception e){
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), "",
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }
    }

    public GetSales updateSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        sr.update(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);

        Sales sale = sr.getBySalesId(salesId);
        try{
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }catch (Exception e){
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), "",
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }
    }

    public Page<Sales> listBy(String dealerId, String salesStatus, String salesName, Pageable pageable){
        Specification spec1 = SalesSpecs.dealerIdContains(dealerId);
        Specification spec2 = SalesSpecs.salesNameContains(salesName);
        Specification spec3 = SalesSpecs.statusIs(salesStatus);
        Specification spec = Specification.where(spec1).and(spec2).and(spec3);
        return sr.findAll(spec, pageable);
    }

    public Page<ViewAllSales> listViewBy(String dealerId, String salesStatus, String salesName, Pageable pageable){
        Specification spec1 = ViewSalesSpecs.dealerIdContains(dealerId);
        Specification spec2 = ViewSalesSpecs.salesNameContains(salesName);
        Specification spec3 = ViewSalesSpecs.statusIs(salesStatus);
        Specification spec = Specification.where(spec1).and(spec2).and(spec3);
        return vsr.findAll(spec, pageable);
    }

    public GetSales get(String salesId){
        Sales sale = sr.getBySalesId(salesId);
        try{
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }catch (Exception e){
            return new GetSales(sale.getSalesId(), sale.getSalesName(),
                    sale.getDealer().getDealerId(), "",
                    sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
        }
    }

    public Optional<Sales> findBySalesId(String salesId){
        return sr.findBySalesId(salesId);
    }
}
