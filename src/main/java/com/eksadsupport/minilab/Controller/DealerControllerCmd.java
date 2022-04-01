package com.eksadsupport.minilab.Controller;


import com.eksadsupport.minilab.Common.CheckUtils;
import com.eksadsupport.minilab.domain.Dealer;
import com.eksadsupport.minilab.dto.dealer.ResponseSave;
import com.eksadsupport.minilab.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("ddms/v1/cmd/master/dealer")
public class DealerControllerCmd {

    @Autowired
    DealerService ds;

    @PostMapping("save")
    public ResponseEntity<Object> saveAll(
            @RequestBody Map<String,Object> request
    ){
        ResponseSave responseSave = new ResponseSave();

        String dealerId = request.get("dealerId").toString();
        String dealerName = request.get("dealerName").toString();
        String dealerClass = request.get("dealerClass").toString();
        String telpNumber = request.get("telpNumber").toString();
        String alamat = request.get("alamat").toString();
        String dealerExtCode = request.get("dealerExtCode").toString();
        String dealerStatus = request.get("dealerStatus").toString();
        try {
            String class1 = "H23"; String class2 = "H123";
            String status1 = "ACTIVE"; String status2="INACTIVE";
            try {

                //FITUR INSERT
                if (dealerId.isEmpty()) {
                    responseSave.responseNoContent();
                    return new ResponseEntity<>(responseSave, HttpStatus.BAD_REQUEST);
                } else {
                    if (dealerClass.equals(class1)||dealerClass.equals(class2)) {
                        if(dealerStatus.equals(status1)||dealerStatus.equals(status2)) {
                            int insertDealer = ds.getCreate(dealerId, dealerName, dealerClass, telpNumber, alamat, dealerExtCode, dealerStatus);
                            Optional<Dealer> dealerList = ds.findbyID(dealerId);
                            responseSave.responseSuccess(dealerList);
                            return new ResponseEntity<>(responseSave, HttpStatus.OK);
                        }
                    }
                }
            }catch (Exception es){

                // FITUR UPDATE
                String cek_id = ds.cekIdDealer(dealerId);

                if(cek_id.equals(dealerId)) {

                    if(CheckUtils.isNullOrEmpty(dealerClass)){
                        String deal_class = ds.cekClassDealer(dealerId);
                        dealerClass = deal_class;
                    }
                    if(CheckUtils.isNullOrEmpty(telpNumber)){
                        String telp = ds.cekTelpDealer(dealerId);
                        telpNumber = telp;

                    }if(CheckUtils.isNullOrEmpty(alamat)){
                        String al = ds.cekAlamatDealer(dealerId);
                        alamat = al;

                    } if(CheckUtils.isNullOrEmpty(dealerExtCode)){
                        String ext = ds.cekExtCode(dealerId);
                        dealerExtCode = ext;

                    } if(CheckUtils.isNullOrEmpty(dealerStatus)){
                        String stat = ds.cekStatusDealer(dealerId);
                        dealerStatus = stat;
                    }
                    int editDealer = ds.getUpdateAll(dealerId,dealerName,dealerClass,telpNumber,alamat,dealerExtCode,dealerStatus);
                    Optional<Dealer> dealerList = ds.findbyID(dealerId);
                    responseSave.responseSuccess(dealerList);
                    return new ResponseEntity<>(responseSave, HttpStatus.OK);
                }
            }

        } catch (Exception e){
            responseSave.responseBadRequest();
            return new ResponseEntity<>(responseSave, HttpStatus.BAD_REQUEST);
        }
        responseSave.responseBadRequest();
        return new ResponseEntity<>(responseSave, HttpStatus.BAD_REQUEST);
    }



}

