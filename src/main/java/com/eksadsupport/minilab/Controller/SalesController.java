package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.Util;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ddms/v1/cmd/master/sales")
public class SalesController {

    @Autowired
    private SalesService ss;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload){
        try{
            Util util = new Util();

            String salesId = util.valueToStringOrEmpty(inputPayload, "salesId");
            String salesName = inputPayload.get("salesName").toString();
            String dealerId = inputPayload.get("dealerId").toString();
            String supervisorId = inputPayload.get("supervisorId").toString();
            String salesGender = inputPayload.get("salesGender").toString();
            String salesEmail = inputPayload.get("salesEmail").toString();
            String salesStatus = inputPayload.get("salesStatus").toString();

            if(!util.checkStringIfAlphabets(salesName)){
                return new ResponseEntity<>("tidak valid", HttpStatus.BAD_REQUEST);
            }

            if(util.checkStringIfNulllOrEmpty(salesId)){
                Sales sales = ss.saveSales("tempSalesId", salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                return new ResponseEntity<>(sales, HttpStatus.OK);
            }

            Sales sales = ss.updateSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);

            return new ResponseEntity<>(sales, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("tidak valid", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(@RequestBody Map<String, Object> inputPayload){
        try{
            String salesName = inputPayload.get("salesName").toString();
            String dealerId = inputPayload.get("dealerId").toString();
            String salesStatus = inputPayload.get("salesStatus").toString();
            int offset = Integer.parseInt(inputPayload.get("offset").toString());
            int limit = Integer.parseInt(inputPayload.get("limit").toString());

            List<Sales> sales = ss.listAll(dealerId, salesStatus, salesName, limit, offset);

            return new ResponseEntity<>(sales, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("tidak valid", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{salesId}")
    public ResponseEntity<Object> get(@PathVariable String salesId){
        try{

            Sales sales = ss.get(salesId);

            return new ResponseEntity<>(sales, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("tidak valid", HttpStatus.BAD_REQUEST);
        }
    }
}

