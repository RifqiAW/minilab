package com.eksadsupport.minilab.dto.order;

public class GetOrder {
    private String orderId;
    private String unitCode;
    private String dealerCode;
    private String salesId;
    private String customerId;
    private double minimumPayment;
    private double totalValue;
    private double orderValue;
    private double offtheroadValue;
    private double orderDiscount;
    private double ppn;
    private String platNomor;
    private String nomorMesin;
    private String nomorRangka;
    private String isLeasing;
    private String paymentStatus;
    private String unitStatus;

    public GetOrder(String orderId, String unitCode, String dealerCode, String salesId, String customerId, double minimumPayment, double totalValue, double orderValue, double offtheroadValue, double orderDiscount, double ppn, String platNomor, String nomorMesin, String nomorRangka, String isLeasing, String paymentStatus, String unitStatus) {
        this.orderId = orderId;
        this.unitCode = unitCode;
        this.dealerCode = dealerCode;
        this.salesId = salesId;
        this.customerId = customerId;
        this.minimumPayment = minimumPayment;
        this.totalValue = totalValue;
        this.orderValue = orderValue;
        this.offtheroadValue = offtheroadValue;
        this.orderDiscount = orderDiscount;
        this.ppn = ppn;
        this.platNomor = platNomor;
        this.nomorMesin = nomorMesin;
        this.nomorRangka = nomorRangka;
        this.isLeasing = isLeasing;
        this.paymentStatus = paymentStatus;
        this.unitStatus = unitStatus;
    }
}
