package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.CheckUtils;
import com.eksadsupport.minilab.domain.Dealer;
import com.eksadsupport.minilab.dto.dealer.DealerById;
import com.eksadsupport.minilab.dto.dealer.DealerListAll;
import com.eksadsupport.minilab.dto.dealer.ResponseDealer;
import com.eksadsupport.minilab.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/qry/master/dealer")
public class DealerQryController {

    @Autowired
    DealerService ds;

    List respon;


    @Value("${property.max_limit}")
    private int CONSTANTS_MAX_LIMIT;

    @PostMapping("/listAll")
    public ResponseEntity<Object>getAll(
            @RequestBody Map<String,Object> request
    ) {
        respon = new ArrayList<>();
        ResponseDealer responseDealer = new ResponseDealer();
        try {
            String dealerId = request.get("dealerId").toString();
            String dealerStatus = request.get("dealerStatus").toString();
            String dealerName = request.get("dealerName").toString();
            String offset_s =(request.get("offset").toString());
            String limit_s = (request.get("limit").toString());

            int offset = 0;
            int limit = CONSTANTS_MAX_LIMIT;

            if(!offset_s.isEmpty()) {
                offset = Integer.parseInt(offset_s);
            }

            if(limit_s.isEmpty()) {
                //KOSONG
                if (CheckUtils.isNullOrEmpty(dealerId) && CheckUtils.isNullOrEmpty(dealerStatus) && CheckUtils.isNullOrEmpty(dealerName)) {
                    responseDealer.responseNoContent();
                    return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);
                    //TERISI SEMUA = PAKAI ALL AND
                } else if (!dealerId.isEmpty() && !dealerName.isEmpty() && !dealerStatus.isEmpty()) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAllAnd(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //   ID SAJA
                } else if (!dealerId.isEmpty() && CheckUtils.isNullOrEmpty(dealerName) && CheckUtils.isNullOrEmpty(dealerStatus)) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListOrId(dealerId, dealerStatus, dealerName, limit, offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //ID dan NAME TERISI , STATUS KOSONG
                } else if (!dealerId.isEmpty() && !dealerName.isEmpty() && CheckUtils.isNullOrEmpty(dealerStatus)) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndNameId(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //ID dan STATUS TERISI, NAME KOSONG
                } else if (!dealerId.isEmpty() && CheckUtils.isNullOrEmpty(dealerName) && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndStatusId(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //  STATUS DAN NAME TERISI , ID KOSONG
                } else if (CheckUtils.isNullOrEmpty(dealerId) && !dealerName.isEmpty() && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndStatusName(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                    //STATUS SAJA
                } else if (CheckUtils.isNullOrEmpty(dealerId) && CheckUtils.isNullOrEmpty(dealerName) && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAllAnd(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                    //NAME SAJA
                } else if (CheckUtils.isNullOrEmpty(dealerId) && !dealerName.isEmpty() && CheckUtils.isNullOrEmpty(dealerStatus)) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListName(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                } else {
                    DealerListAll dealerListAll = new DealerListAll();
                    if (dealerListAll.getDataOfRecord() == 0) {
                        responseDealer.responseNoContent();
                        return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);
                    }
                    responseDealer.responseNoContent();
                    return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);
                }
            }
            if (!limit_s.isEmpty()){
                limit = Integer.parseInt(limit_s);

                if (CheckUtils.isNullOrEmpty(dealerId) && CheckUtils.isNullOrEmpty(dealerStatus) && CheckUtils.isNullOrEmpty(dealerName)) {
                    responseDealer.responseNoContent();
                    return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);
                    //TERISI SEMUA = PAKAI ALL AND
                } else if (!dealerId.isEmpty() && !dealerName.isEmpty() && !dealerStatus.isEmpty()) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAllAnd(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //   ID SAJA
                } else if (!dealerId.isEmpty() && CheckUtils.isNullOrEmpty(dealerName) && CheckUtils.isNullOrEmpty(dealerStatus)) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListOrId(dealerId, dealerStatus, dealerName, limit, offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //ID dan NAME TERISI , STATUS KOSONG
                } else if (!dealerId.isEmpty() && !dealerName.isEmpty() && CheckUtils.isNullOrEmpty(dealerStatus)) {

                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndNameId(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //ID dan STATUS TERISI, NAME KOSONG
                } else if (!dealerId.isEmpty() && CheckUtils.isNullOrEmpty(dealerName) && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndStatusId(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);

                    //  STATUS DAN NAME TERISI , ID KOSONG
                } else if (CheckUtils.isNullOrEmpty(dealerId) && !dealerName.isEmpty() && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAndStatusName(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                    //STATUS SAJA
                } else if (CheckUtils.isNullOrEmpty(dealerId) && CheckUtils.isNullOrEmpty(dealerName) && !dealerStatus.isEmpty()) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListAllAnd(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                    //NAME SAJA
                } else if (CheckUtils.isNullOrEmpty(dealerId) && !dealerName.isEmpty() && CheckUtils.isNullOrEmpty(dealerStatus)) {
                    DealerListAll dealerListAll = new DealerListAll();
                    dealerListAll = ds.dealerListName(dealerId, dealerStatus, dealerName, limit, offset);
                    //dealerListAll = ds.dealerListAllOr(dealerId,dealerStatus,dealerName,limit,offset);
                    respon.add(dealerListAll);
                    return new ResponseEntity<>(respon, HttpStatus.OK);
                } else {
                    DealerListAll dealerListAll = new DealerListAll();
                    if (dealerListAll.getDataOfRecord() == 0) {
                        responseDealer.responseNoContent();
                        return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);
                    }
                }

            }
        }catch (Exception e) {
            responseDealer.responseBadRequest();
            return new ResponseEntity<>(responseDealer, HttpStatus.BAD_REQUEST);
        }responseDealer.responseBadRequest();
        return new ResponseEntity<>(responseDealer, HttpStatus.BAD_REQUEST);
    }



    @GetMapping("/get/{dealerId}")
    public ResponseEntity<Object>getDealerbyId(
            @PathVariable String dealerId
    ){
        ResponseDealer responseDealer = new ResponseDealer();
        respon = new ArrayList<>();
        try {
            Optional<Dealer>cek = ds.findbyID(dealerId);


            if(!cek.isPresent()){
                responseDealer.responseNoContent();
                return new ResponseEntity<>(responseDealer, HttpStatus.NOT_FOUND);

            }else {
                DealerById getDealer = new DealerById();
                getDealer = ds.dealerById(dealerId);

                respon.add(getDealer);

                return new ResponseEntity<>(respon, HttpStatus.OK);
            }
        }catch (Exception e){
            responseDealer.responseBadRequest();
            return new ResponseEntity<>(responseDealer, HttpStatus.BAD_REQUEST);
        }

    }


}
