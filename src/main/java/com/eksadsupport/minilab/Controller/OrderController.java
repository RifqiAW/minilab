package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static com.eksadsupport.minilab.Common.Util.*;
import static com.eksadsupport.minilab.Common.Util.isValidId;

@RestController
@RequestMapping("ddms/v1/cmd/master/order")
public class OrderController {

    @Autowired
    private OrderService os;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload){
        try{
            String orderId = valueToStringOrEmpty(inputPayload, "orderId");
            String unitCode = inputPayload.get("unitCode").toString();
            String dealerCode = inputPayload.get("dealerCode").toString();
            String salesId   = inputPayload.get("salesId").toString();
            String customerId = inputPayload.get("customerId").toString();
            String minimumPayment = inputPayload.get("minimumPayment").toString();
            String totalValue = inputPayload.get("totalValue").toString();
            String orderValue = inputPayload.get("orderValue").toString();
            String offtheroadValue = inputPayload.get("offtheroadValue").toString();
            String orderDiscount = inputPayload.get("orderDiscount").toString();
            String ppn = inputPayload.get("ppn").toString();
            String platNomor = inputPayload.get("platNomor").toString();
            String nomorMesin = inputPayload.get("nomorMesin").toString();
            String nomorRangka = inputPayload.get("nomorRangka").toString();
            String isLeasing = inputPayload.get("isLeasing").toString();
            String paymentStatus = inputPayload.get("paymentStatus").toString();
            String unitStatus = inputPayload.get("unitStatus").toString();

//            if(!checkStringIfAlphabets(salesName) || !checkIfValidEmail(salesEmail)){
//                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
//            }
//
//            if(checkStringIfNulllOrEmpty(salesId)){
//                GetSales sales = ss.saveSales(generateId(), salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
//                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
//            }
//
//            Optional<Sales> opt = ss.findBySalesId(salesId);
//
//            if(!opt.isPresent()){
//                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
//            }
//
//            if(!isValidId(salesId)){
//                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
//            }
//
//            GetSales sales = ss.updateSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);

//            return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}
