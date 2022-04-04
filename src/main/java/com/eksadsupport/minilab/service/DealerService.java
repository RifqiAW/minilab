package com.eksadsupport.minilab.service;


import com.eksadsupport.minilab.Common.Constants;
import com.eksadsupport.minilab.domain.Dealer;

import com.eksadsupport.minilab.dto.dealer.DealerById;
import com.eksadsupport.minilab.dto.dealer.DealerListAll;
import com.eksadsupport.minilab.dto.dealer.GetDealer;
import com.eksadsupport.minilab.repository.DealerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DealerService {

    @Autowired
    DealerRepository dr;

    //Service Save
    public int getCreate (String dealerId,String dealerName,String dealerClass,String telpNumber,String alamat,String dealerExtCode, String dealerStatus){
        return dr.CreateDealer(dealerId,dealerName,dealerClass,telpNumber,alamat,dealerExtCode,dealerStatus);
    }

    public int getUpdateAll(String dealerId,String dealerName,String dealerClass,String telpNumber,String alamat,String dealerExtCode, String dealerStatus){
        return dr.UpdateDealerAll(dealerId,dealerName,dealerClass,telpNumber,alamat,dealerExtCode,dealerStatus);
    }

    public Optional<Dealer> findbyID(String dealerId){
        return dr.findById(dealerId);
    }

    public String cekIdDealer(String dealerId){
        return dr.cekId(dealerId);
    }
    public String cekClassDealer(String dealerId){
        return dr.cekClass(dealerId);
    }
    public String cekTelpDealer(String dealerId){
        return dr.cekTelp(dealerId);
    }
    public String cekAlamatDealer(String dealerId){
        return dr.cekAlamat(dealerId);
    }
    public String cekExtCode(String dealerId){
        return dr.cekExt(dealerId);
    }
    public String cekStatusDealer(String dealerId){
        return dr.cekStatus(dealerId);
    }

    public int codeGenerate(){
        return dr.GenerateId();
    }

    public Page<Dealer> findwithPagination(String dealerId, String dealerStatus, String dealerName, int limit, int offset){
        Page<Dealer> dealer = dr.ListAllDealer(dealerId,dealerStatus,dealerName,PageRequest.of(limit,offset));
        return dealer;
    }


    //Service Search By Id
    public DealerById dealerById(String dealerId){
        DealerById getdealer = new DealerById();
        Optional<Dealer> opt = dr.findById(dealerId);
        getdealer.setStatus(Constants.STATUS);
        getdealer.setCode(Constants.CODE);
        getdealer.setMessage(Constants.MESSAGE);
        getdealer.setData(opt);
        return getdealer;
    }

    //Service List ALL
    public DealerListAll dealerListAll(String dealerId,String dealerStatus,String dealerName,int limit, int offset){
        DealerListAll getDealerList = new DealerListAll();

        List<Dealer>
                opt = dr.ViewDealer(dealerId,dealerStatus,dealerName,limit,offset);

        //opt = dr.SelectDealer(dealerId,dealerStatus,dealerName,limit,offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealerList.setData(opt);
        //getDealerList.setDataOfRecord(limit);
        return getDealerList;
    }
    public DealerListAll dealerListAllOr(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        //List<Dealer> opt = dr.ViewDealer(dealerId, dealerStatus, dealerName, limit, offset);
        List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());

        return getDealerList;
    }

    public DealerListAll dealerListAllAnd(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {


        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer(dealerId, dealerStatus, dealerName, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;


    }

    public DealerListAll dealerListOrId(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer3(dealerId, dealerStatus, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;
    }

    public DealerListAll dealerListAndNameId(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer4(dealerId, dealerName, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;
    }

    public DealerListAll dealerListAndStatusId(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer5(dealerId, dealerStatus, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;
    }
    public DealerListAll dealerListAndStatusName(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer6(dealerStatus,dealerName, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;
    }

    public DealerListAll dealerListName(String dealerId,String dealerStatus,String dealerName,int limit, int offset) {
        DealerListAll getDealerList = new DealerListAll();
        GetDealer getDealer = new GetDealer();
        List<Dealer> opt = dr.ViewDealer7(dealerName, limit, offset);
        //List<Dealer> opt = dr.ViewDealer2(dealerId, dealerStatus, dealerName, limit, offset);
        getDealerList.setStatus(Constants.STATUS);
        getDealerList.setCode(Constants.CODE);
        getDealerList.setMessage(Constants.MESSAGE);
        getDealer.setListdealer(opt);
        getDealerList.setData(getDealer);
        getDealerList.setDataOfRecord(opt.size());
        return getDealerList;
    }

}
