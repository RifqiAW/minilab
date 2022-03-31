package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.Constants;
import com.eksadsupport.minilab.domain.Order;
import com.eksadsupport.minilab.dto.order.GetOrder;
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

import java.util.HashMap;
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
//            String orderId = valueToStringOrEmpty(inputPayload, "orderId");
//            String unitCode = inputPayload.get("unitCode").toString();
//            String dealerCode = inputPayload.get("dealerCode").toString();
//            String salesId   = inputPayload.get("salesId").toString();
//            String customerId = inputPayload.get("customerId").toString();
//            String minimumPayment = inputPayload.get("minimumPayment").toString();
//            String totalValue = inputPayload.get("totalValue").toString();
//            String orderValue = inputPayload.get("orderValue").toString();
//            String offtheroadValue = inputPayload.get("offtheroadValue").toString();
//            String orderDiscount = inputPayload.get("orderDiscount").toString();
//            String ppn = inputPayload.get("ppn").toString();
//            String platNomor = inputPayload.get("platNomor").toString();
//            String nomorMesin = inputPayload.get("nomorMesin").toString();
//            String nomorRangka = inputPayload.get("nomorRangka").toString();
//            String isLeasing = inputPayload.get("isLeasing").toString();
//            String paymentStatus = inputPayload.get("paymentStatus").toString();
//            String unitStatus = inputPayload.get("unitStatus").toString();

            Map<String, Object> args = new HashMap<>();

            for(String s: Constants.ORDER_ARGS){
                args.put(s, valueToStringOrEmpty(inputPayload, s));
            }

            if(checkStringIfNulllOrEmpty(args.get("orderId").toString())){
                GetOrder getOrder = os.saveOrder(args.get("orderId").toString(),
                        args.get("unitCode").toString(),
                        args.get("dealerCode").toString(),
                        args.get("salesId").toString(),
                        args.get("customerId").toString(),
                        Double.parseDouble(args.get("minimumPayment").toString()),
                        Double.parseDouble(args.get("totalValue").toString()),
                        Double.parseDouble(args.get("orderValue").toString()),
                        Double.parseDouble(args.get("offtheroadValue").toString()),
                        Double.parseDouble(args.get("orderDiscount").toString()),
                        Double.parseDouble(args.get("ppn").toString()),
                        args.get("platNomor").toString(),
                        args.get("nomorMesin").toString(),
                        args.get("nomorRangka").toString(),
                        args.get("isLeasing").toString(),
                        args.get("paymentStatus").toString(),
                        args.get("unitStatus").toString()
                );
            }

            Optional<Order> opt = os.findByOrderId(args.get("orderId").toString());

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }else{
                for(String s: Constants.ORDER_ARGS){
                    if(args.get(s).toString().isEmpty()){

                    }
                    args.put(s, valueToStringOrEmpty(inputPayload, s));
                }
            }

            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}
