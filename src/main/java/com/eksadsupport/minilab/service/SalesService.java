package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    @Autowired
    SalesRepository sr;

    public Sales saveSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        return sr.save(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
    }

    public Sales updateSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus){
        return sr.update(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
    }

    public List<Sales> listAll(String dealerId, String salesStatus, String salesName, int limit, int offset){
        return sr.listAll(dealerId, salesStatus, salesName, limit, offset);
    }

    public Sales get(String salesId){
        return sr.getBySalesId(salesId);
    }
}
