package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Order;
import com.eksadsupport.minilab.domain.ViewAllOrder;
import com.eksadsupport.minilab.dto.order.GetOrder;
import com.eksadsupport.minilab.model.ViewOrderSpecs;
import com.eksadsupport.minilab.repository.OrderRepository;
import com.eksadsupport.minilab.repository.ViewOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository or;

    @Autowired
    ViewOrderRepository vor;

    public GetOrder saveOrder(String orderId, String unitCode, String dealerCode, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus){
        or.save(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
        Order order = or.getByOrderId(orderId);
        try {
            return new GetOrder(
                    order.getOrderId(),
                    order.getUnit().getUnitId(),
                    order.getDealer().getDealerId(),
                    order.getSales().getSalesId(),
                    order.getCustomer().getCustomerId(),
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
        }catch (Exception e){
            return new GetOrder(
                    order.getOrderId(),
                    order.getUnit().getUnitId(),
                    order.getDealer().getDealerId(),
                    order.getSales().getSalesId(),
                    order.getCustomer().getCustomerId(),
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
        }
    }

    public GetOrder updateOrder(String orderId, String unitCode, String dealerCode, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus){
        or.update(orderId, unitCode, dealerCode, salesId, customerId, minimumPayment, totalValue, orderValue, offtheroadValue, orderDiscount, ppn, platNomor, nomorMesin, nomorRangka, isLeasing, paymentStatus, unitStatus);
        Order order = or.getByOrderId(orderId);
        try {
            return new GetOrder(
                    order.getOrderId(),
                    order.getUnit().getUnitId(),
                    order.getDealer().getDealerId(),
                    order.getSales().getSalesId(),
                    order.getCustomer().getCustomerId(),
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
        }catch (Exception e){
            return new GetOrder(
                    order.getOrderId(),
                    order.getUnit().getUnitId(),
                    order.getDealer().getDealerId(),
                    order.getSales().getSalesId(),
                    order.getCustomer().getCustomerId(),
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
        }
    }

    public Page<ViewAllOrder> listViewBy(String platNomor, String dealerId, String nomorMesin, String nomorRangka,
                                         String paymentStatus, Pageable paging){
        Specification spec1 = ViewOrderSpecs.dealerIdContains(dealerId);
        Specification spec2 = ViewOrderSpecs.platNomorContains(platNomor);
        Specification spec3 = ViewOrderSpecs.nomorMesinContains(nomorMesin);
        Specification spec4 = ViewOrderSpecs.nomorRangkaContains(nomorRangka);
        Specification spec5 = ViewOrderSpecs.paymentStatusIs(paymentStatus);
        Specification spec = Specification.where(spec1).or(spec2).or(spec3).or(spec4).or(spec5);
        return vor.findAll(spec, paging);
    }

    public Optional<Order> findByOrderId(String orderId){
        return or.findByOrderId(orderId);
    }
}
