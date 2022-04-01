package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Order;
import com.eksadsupport.minilab.domain.ViewAllOrder;
import com.eksadsupport.minilab.dto.order.GetListOrder;
import com.eksadsupport.minilab.dto.order.GetOrder;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
            String unitCode = valueToStringOrEmpty(inputPayload, "unitCode");
            String dealerCode = valueToStringOrEmpty(inputPayload, "dealerCode");
            String salesId = valueToStringOrEmpty(inputPayload, "salesId");
            String customerId = valueToStringOrEmpty(inputPayload, "customerId");
            String minimumPayment = valueToStringOrEmpty(inputPayload, "minimumPayment");
            String totalValue = valueToStringOrEmpty(inputPayload, "totalValue");
            String orderValue = valueToStringOrEmpty(inputPayload, "orderValue");
            String offtheroadValue = valueToStringOrEmpty(inputPayload, "offtheroadValue");
            String orderDiscount = valueToStringOrEmpty(inputPayload, "orderDiscount");
            String ppn = valueToStringOrEmpty(inputPayload, "ppn");
            String platNomor = valueToStringOrEmpty(inputPayload, "platNomor");
            String nomorMesin = valueToStringOrEmpty(inputPayload, "nomorMesin");
            String nomorRangka = valueToStringOrEmpty(inputPayload, "nomorRangka");
            String isLeasing = valueToStringOrEmpty(inputPayload, "isLeasing");
            String paymentStatus = valueToStringOrEmpty(inputPayload, "paymentStatus");
            String unitStatus = valueToStringOrEmpty(inputPayload, "unitStatus");

            if(orderId.isEmpty() && (unitCode.isEmpty() || dealerCode.isEmpty() || salesId.isEmpty() ||
                    customerId.isEmpty() || minimumPayment.isEmpty() || totalValue.isEmpty() || orderValue.isEmpty() ||
                    offtheroadValue.isEmpty() || orderDiscount.isEmpty() || ppn.isEmpty() || paymentStatus.isEmpty() ||
                    unitStatus.isEmpty() || Integer.parseInt(ppn) < 0 ||
                    Integer.parseInt(totalValue) != Integer.parseInt(orderValue) - Integer.parseInt(orderDiscount) ||
                    (isLeasing.equalsIgnoreCase("NO") && !paymentStatus.equalsIgnoreCase("FULLY_PAID")) ||
                    (isLeasing.equalsIgnoreCase("YES") && !paymentStatus.equalsIgnoreCase("PARTIAL_PAID"))
                    )){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(checkStringIfNulllOrEmpty(orderId)){
                if(isLeasing.isEmpty()){
                    GetOrder getOrder =
                            os.saveOrder(generateId(),
                            unitCode,
                            dealerCode,
                            salesId,
                            customerId,
                            Double.parseDouble(minimumPayment),
                            Double.parseDouble(totalValue),
                            Double.parseDouble(orderValue),
                            Double.parseDouble(offtheroadValue),
                            Double.parseDouble(orderDiscount),
                            Double.parseDouble(ppn),
                            platNomor,
                            nomorMesin,
                            nomorRangka,
                            null,
                            paymentStatus,
                            unitStatus
                    );
                    return new ResponseEntity<>(new ResponseSuccess(getOrder), HttpStatus.OK);
                }
                else{
                    GetOrder getOrder = os.saveOrder(generateId(),
                            unitCode,
                            dealerCode,
                            salesId,
                            customerId,
                            Double.parseDouble(minimumPayment),
                            Double.parseDouble(totalValue),
                            Double.parseDouble(orderValue),
                            Double.parseDouble(offtheroadValue),
                            Double.parseDouble(orderDiscount),
                            Double.parseDouble(ppn),
                            platNomor,
                            nomorMesin,
                            nomorRangka,
                            isLeasing,
                            paymentStatus,
                            unitStatus
                    );
                    return new ResponseEntity<>(new ResponseSuccess(getOrder), HttpStatus.OK);
                }
            }

            Optional<Order> opt = os.findByOrderId(orderId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }else{
                if(unitCode.isEmpty()){
                    unitCode = opt.get().getUnit().getUnitId();
                }
                if(dealerCode.isEmpty()){
                    dealerCode = opt.get().getDealer().getDealerId();
                }
                if(customerId.isEmpty()){
                    customerId = opt.get().getCustomer().getCustomerId();
                }
                if(minimumPayment.isEmpty()){
                    minimumPayment = opt.get().getMinimumPayment()+"";
                }
                if(totalValue.isEmpty()){
                    totalValue = opt.get().getTotalValue()+"";
                }
                if(orderValue.isEmpty()){
                    orderValue = opt.get().getOrderValue()+"";
                }
                if(offtheroadValue.isEmpty()){
                    offtheroadValue = opt.get().getOfftheroadValue()+"";
                }
                if(ppn.isEmpty()){
                    ppn = opt.get().getPpn()+"";
                }
                if(platNomor.isEmpty()){
                    platNomor = opt.get().getPlatNomor();
                }
                if(nomorMesin.isEmpty()){
                    nomorMesin = opt.get().getNomorMesin();
                }
                if(nomorRangka.isEmpty()){
                    nomorRangka = opt.get().getNomorRangka();
                }
                if(isLeasing.isEmpty()){
                    isLeasing = opt.get().getIsLeasing();
                }
                if(paymentStatus.isEmpty()){
                    paymentStatus = opt.get().getPaymentStatus();
                }
                if(unitStatus.isEmpty()){
                    unitStatus = opt.get().getUnitStatus();
                }
                if(orderDiscount.isEmpty()){
                    orderDiscount = opt.get().getOrderTotalDiscount()+"";
                }
            }

            if (Integer.parseInt(ppn) < 0 ||
                    Integer.parseInt(totalValue) != Integer.parseInt(orderValue) - Integer.parseInt(orderDiscount) ||
                    (isLeasing.equalsIgnoreCase("NO") && !paymentStatus.equalsIgnoreCase("FULLY_PAID")) ||
                    (isLeasing.equalsIgnoreCase("YES") && !paymentStatus.equalsIgnoreCase("PARTIAL_PAID"))
            ){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(isLeasing.isEmpty()){
                GetOrder getOrder = os.updateOrder(
                        orderId,
                        unitCode,
                        dealerCode,
                        salesId,
                        customerId,
                        Double.parseDouble(minimumPayment),
                        Double.parseDouble(totalValue),
                        Double.parseDouble(orderValue),
                        Double.parseDouble(offtheroadValue),
                        Double.parseDouble(orderDiscount),
                        Double.parseDouble(ppn),
                        platNomor,
                        nomorMesin,
                        nomorRangka,
                        null,
                        paymentStatus,
                        unitStatus
                );
                return new ResponseEntity<>(new ResponseSuccess(getOrder), HttpStatus.OK);
            }
            else{
                GetOrder getOrder = os.updateOrder(
                        orderId,
                        unitCode,
                        dealerCode,
                        salesId,
                        customerId,
                        Double.parseDouble(minimumPayment),
                        Double.parseDouble(totalValue),
                        Double.parseDouble(orderValue),
                        Double.parseDouble(offtheroadValue),
                        Double.parseDouble(orderDiscount),
                        Double.parseDouble(ppn),
                        platNomor,
                        nomorMesin,
                        nomorRangka,
                        isLeasing,
                        paymentStatus,
                        unitStatus
                );
                return new ResponseEntity<>(new ResponseSuccess(getOrder), HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
    @Value("${property.max_limit}")
    public int CONSTANTS_MAX_LIMIT;

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(@RequestBody Map<String, Object> inputPayload){
        try{
            String platNomor = valueToStringOrEmpty(inputPayload, "platNomor");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String nomorMesin = valueToStringOrEmpty(inputPayload, "nomorMesin");
            String nomorRangka = valueToStringOrEmpty(inputPayload, "nomorRangka");
            String paymentStatus = valueToStringOrEmpty(inputPayload, "paymentStatus");
            String offset_s = valueToStringOrEmpty(inputPayload, "offset");
            String limit_s = valueToStringOrEmpty(inputPayload, "limit");

            int offset = 0;
            int limit = CONSTANTS_MAX_LIMIT;

            if(platNomor.isEmpty() && dealerId.isEmpty() && nomorMesin.isEmpty() && nomorRangka.isEmpty()
                && paymentStatus.isEmpty()){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(!limit_s.isEmpty()){
                limit = Integer.parseInt(limit_s);
            }
            if(!offset_s.isEmpty()){
                offset = Integer.parseInt(offset_s);
            }

            Pageable paging = PageRequest.of(offset, limit);

            Page<ViewAllOrder> pages = os.listViewBy(platNomor, dealerId, nomorMesin, nomorRangka, paymentStatus, paging);

            List<ViewAllOrder> orders = pages.getContent();

            List<GetOrder> getOrders = new ArrayList<>();

            for(ViewAllOrder order:orders){
                try {
                    GetOrder getOrder = new GetOrder(
                            order.getOrderId(),
                            order.getUnitId(),
                            order.getDealerCode(),
                            order.getSalesId(),
                            order.getCustomerId(),
                            order.getMinimumPayment(),
                            order.getTotalValue(),
                            order.getOrderValue(),
                            order.getOfftheroadValue(),
                            order.getOrderTotalDiscount(),
                            order.getPpn(),
                            order.getPlatNomor(),
                            order.getNomorMesin(),
                            order.getNomorRangka(),
                            order.getIsLeasing(),
                            order.getPaymentStatus(),
                            order.getUnitStatus()
                    );

                    getOrders.add(getOrder);
                }catch (Exception e){
                    GetOrder getOrder = new GetOrder(
                            order.getOrderId(),
                            order.getUnitId(),
                            order.getDealerCode(),
                            order.getSalesId(),
                            order.getCustomerId(),
                            order.getMinimumPayment(),
                            order.getTotalValue(),
                            order.getOrderValue(),
                            order.getOfftheroadValue(),
                            order.getOrderTotalDiscount(),
                            order.getPpn(),
                            order.getPlatNomor(),
                            order.getNomorMesin(),
                            order.getNomorRangka(),
                            "",
                            order.getPaymentStatus(),
                            order.getUnitStatus()
                    );

                    getOrders.add(getOrder);
                }
            }

            GetListOrder getListOrder = new GetListOrder();
            getListOrder.setListOrder(getOrders);
            getListOrder.setDataOfRecord(pages.getTotalPages());

            return new ResponseEntity<>(new ResponseSuccess(getListOrder), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<Object> get(@PathVariable String orderId){
        try{
            Optional<Order> opt = os.findByOrderId(orderId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            try {
                GetOrder order = new GetOrder(
                        opt.get().getOrderId(),
                        opt.get().getUnit().getUnitId(),
                        opt.get().getDealer().getDealerId(),
                        opt.get().getSales().getSalesId(),
                        opt.get().getCustomer().getCustomerId(),
                        opt.get().getMinimumPayment(),
                        opt.get().getTotalValue(),
                        opt.get().getOrderValue(),
                        opt.get().getOfftheroadValue(),
                        opt.get().getOrderTotalDiscount(),
                        opt.get().getPpn(),
                        opt.get().getPlatNomor(),
                        opt.get().getNomorMesin(),
                        opt.get().getNomorRangka(),
                        opt.get().getIsLeasing(),
                        opt.get().getPaymentStatus(),
                        opt.get().getUnitStatus()
                );
                return new ResponseEntity<>(new ResponseSuccess(order), HttpStatus.OK);
            }catch (Exception e){
                GetOrder order = new GetOrder(
                        opt.get().getOrderId(),
                        opt.get().getUnit().getUnitId(),
                        opt.get().getDealer().getDealerId(),
                        opt.get().getSales().getSalesId(),
                        opt.get().getCustomer().getCustomerId(),
                        opt.get().getMinimumPayment(),
                        opt.get().getTotalValue(),
                        opt.get().getOrderValue(),
                        opt.get().getOfftheroadValue(),
                        opt.get().getOrderTotalDiscount(),
                        opt.get().getPpn(),
                        opt.get().getPlatNomor(),
                        opt.get().getNomorMesin(),
                        opt.get().getNomorRangka(),
                        "",
                        opt.get().getPaymentStatus(),
                        opt.get().getUnitStatus()
                );
                return new ResponseEntity<>(new ResponseSuccess(order), HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}
