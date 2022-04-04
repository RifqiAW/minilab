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

@RestController
@RequestMapping("ddms/v1/qry/transaction/order")
public class OrderQryController {

    @Autowired
    private OrderService os;

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
