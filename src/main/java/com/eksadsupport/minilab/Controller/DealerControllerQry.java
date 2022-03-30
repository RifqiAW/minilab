package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Dealer;
import com.eksadsupport.minilab.dto.dealer.DealerById;
import com.eksadsupport.minilab.dto.dealer.DealerListAll;
import com.eksadsupport.minilab.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("ddms/v1/qry/master/dealer")
public class DealerControllerQry {

    @Autowired
    DealerService ds;

    List respon;


    @PostMapping("listAll")
    public ResponseEntity<List>getAll(
            @RequestBody Map<String,Object> request
    ) {
        Map<String, Object> ret = new LinkedHashMap<>();

        String dealerId = request.get("dealerId").toString();
        String dealerStatus = request.get("dealerStatus").toString();
        String dealerName = request.get("dealerName").toString();
        int limit = Integer.parseInt(request.get("limit").toString());
        int offset = Integer.parseInt(request.get("offset").toString());

        DealerListAll dealerListAll = new DealerListAll();
        dealerListAll = ds.dealerListAll(dealerId,dealerStatus,dealerName,limit,offset);

        respon = new ArrayList<>();
        respon.add(dealerListAll);

        return new ResponseEntity<>(respon, HttpStatus.OK);
    }

    @GetMapping("get")
    public ResponseEntity<List>getDealerbyId(
            @RequestParam(required = false,value = "dealerId")String dealerId
    ){
        respon = new ArrayList<>();
        Optional<Dealer>cek = ds.findbyID(dealerId);
        String check = cek.get().getDealerId();
        try {
            if(check.isEmpty()){
                respon.add("204. DATA NOT FOUND");
                return new ResponseEntity<>(respon, HttpStatus.NOT_FOUND);

            }else {
                DealerById getDealer = new DealerById();
                getDealer = ds.dealerById(dealerId);

                respon.add(getDealer);

                return new ResponseEntity<>(respon, HttpStatus.OK);
            }
        }catch (NoSuchElementException e){
            respon.add("204. DATA NOT FOUND");
            return new ResponseEntity<>(respon, HttpStatus.NOT_FOUND);
        }

    }

}
