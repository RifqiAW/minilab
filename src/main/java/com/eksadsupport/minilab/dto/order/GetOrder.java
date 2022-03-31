package com.eksadsupport.minilab.dto.order;

public class GetOrder {
    private String orderId;
    private String unitCode;
    private String dealerCode;
    private String salesId;
    private String customerId;
    private int minimumPayment;
    private int totalValue;
    private int orderValue;
    private int offtheroadValue;
    private int orderDiscount;
    private int ppn;
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
        this.minimumPayment = (int)Math.ceil(minimumPayment);
        this.totalValue = (int)Math.ceil(totalValue);
        this.orderValue = (int)Math.ceil(orderValue);
        this.offtheroadValue = (int)Math.ceil(offtheroadValue);
        this.orderDiscount = (int)Math.ceil(orderDiscount);
        this.ppn = (int)Math.ceil(ppn);
        this.platNomor = platNomor;
        this.nomorMesin = nomorMesin;
        this.nomorRangka = nomorRangka;
        this.isLeasing = isLeasing;
        this.paymentStatus = paymentStatus;
        this.unitStatus = unitStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(int minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(int orderValue) {
        this.orderValue = orderValue;
    }

    public int getOfftheroadValue() {
        return offtheroadValue;
    }

    public void setOfftheroadValue(int offtheroadValue) {
        this.offtheroadValue = offtheroadValue;
    }

    public int getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(int orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public int getPpn() {
        return ppn;
    }

    public void setPpn(int ppn) {
        this.ppn = ppn;
    }

    public String getPlatNomor() {
        return platNomor;
    }

    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getNomorMesin() {
        return nomorMesin;
    }

    public void setNomorMesin(String nomorMesin) {
        this.nomorMesin = nomorMesin;
    }

    public String getNomorRangka() {
        return nomorRangka;
    }

    public void setNomorRangka(String nomorRangka) {
        this.nomorRangka = nomorRangka;
    }

    public String getIsLeasing() {
        return isLeasing;
    }

    public void setIsLeasing(String isLeasing) {
        this.isLeasing = isLeasing;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(String unitStatus) {
        this.unitStatus = unitStatus;
    }
}
