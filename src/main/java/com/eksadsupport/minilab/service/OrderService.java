package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Order;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.dto.order.GetOrder;
import com.eksadsupport.minilab.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository or;

    public GetOrder saveOrder(String orderId, String unitCode, String dealerCode, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus){
        or.save(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
        return new GetOrder(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
    }

    public GetOrder updateOrder(String orderId, String unitCode, String dealerCode, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus){
        or.update(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
        return new GetOrder(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
    }

    public Optional<Order> findByOrderId(String orderId){
        return or.findByOrderId(orderId);
    }
}
