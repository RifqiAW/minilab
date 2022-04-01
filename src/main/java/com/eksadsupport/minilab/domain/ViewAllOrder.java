package com.eksadsupport.minilab.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "vw_trx_order")
public class ViewAllOrder {

    @Id
    private String orderId;
    private String unitId;
    private String dealerCode;
    private String salesId;
    private String customerId;
    private double minimumPayment;
    private double totalValue;
    private double orderValue;
    private double offtheroadValue;
    private double orderTotalDiscount;
    private double ppn;
    private String platNomor;
    private String nomorMesin;
    private String nomorRangka;
    private String isLeasing;
    private String paymentStatus;
    private String unitStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
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

    public double getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(double minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }

    public double getOfftheroadValue() {
        return offtheroadValue;
    }

    public void setOfftheroadValue(double offtheroadValue) {
        this.offtheroadValue = offtheroadValue;
    }

    public double getOrderTotalDiscount() {
        return orderTotalDiscount;
    }

    public void setOrderTotalDiscount(double orderTotalDiscount) {
        this.orderTotalDiscount = orderTotalDiscount;
    }

    public double getPpn() {
        return ppn;
    }

    public void setPpn(double ppn) {
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
