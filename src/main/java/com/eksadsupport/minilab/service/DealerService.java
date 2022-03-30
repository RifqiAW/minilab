package com.eksadsupport.minilab.service;


import com.eksadsupport.minilab.Common.Constants;
import com.eksadsupport.minilab.domain.Dealer;

import com.eksadsupport.minilab.dto.dealer.DealerById;
import com.eksadsupport.minilab.dto.dealer.DealerListAll;
import com.eksadsupport.minilab.repository.DealerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DealerService {

    @Autowired
    DealerRepository dr;

    public int getCreate (String dealerId,String dealerName,String dealerClass,String telpNumber,String alamat,String dealerExtCode, String dealerStatus){
        return dr.CreateDealer(dealerId,dealerName,dealerClass,telpNumber,alamat,dealerExtCode,dealerStatus);
    }

    public int getUpdate(String dealerName,String dealerId){
        return dr.UpdateDealer(dealerName,dealerId);
    }


    public Optional<Dealer> findbyID(String dealerId){
        return dr.findById(dealerId);
    }


    public DealerById dealerById(String dealerId){
        DealerById getdealer = new DealerById();
        Optional<Dealer> opt = dr.findById(dealerId);
        getdealer.setStatus(Constants.STATUS);
        getdealer.setCode(Constants.CODE);
        getdealer.setMessage(Constants.MESSAGE);
        getdealer.setData(opt);
        return getdealer;
    }

    public DealerListAll dealerListAll(String dealerId,String dealerStatus,String dealerName,int limit, int offset){
        DealerListAll getDealerList = new DealerListAll();

        List<Dealer> opt = dr.ViewDealer(dealerId,dealerStatus,dealerName,limit,offset);

        //opt = dr.SelectDealer(dealerId,dealerStatus,dealerName,limit,offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealerList.setData(opt);
        getDealerList.setDataOfRecord(limit);
        return getDealerList;
    }


}
