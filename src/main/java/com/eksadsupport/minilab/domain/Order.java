package com.eksadsupport.minilab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trx_order")
public class Order {

    @Id
    @Size(max = 50)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    @JsonIgnore
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dealer_code")
    @JsonIgnore
    private Dealer dealer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="sales_id")
    @JsonIgnore
    private Sales sales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonIgnore
    private Customer customer;

    @Column(name = "minimum_payment")
    private double minimumPayment;

    @Column(name = "total_value")
    private double totalValue;

    @Column(name = "order_value")
    private double orderValue;

    @Column(name = "offtheroad_value")
    private double offtheroadValue;

    @Column(name = "order_total_discount")
    private double orderTotalDiscount;

    @Column(name = "ppn")
    private double ppn;

    @Column(name = "plat_nomor")
    private String platNomor;

    @Column(name = "nomor_mesin")
    private String nomorMesin;

    @Column(name = "nomor_rangka")
    private String nomorRangka;

    @Column(name = "is_leasing")
    private String isLeasing;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "unit_status")
    private String unitStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<Object> getAll(){
        List<Object> list = new ArrayList<>();
        list.add(orderId);
        list.add(unit);
        list.add(dealer);
        list.add(sales);
        list.add(customer);
        list.add(minimumPayment);
        list.add(totalValue);
        list.add(orderValue);
        list.add(offtheroadValue);
        list.add(orderTotalDiscount);
        list.add(ppn);
        list.add(platNomor);
        list.add(nomorMesin);
        list.add(nomorRangka);
        list.add(isLeasing);
        list.add(paymentStatus);
        list.add(unitStatus);
        return list;
    }

    public String getNomorRangka() {
        return nomorRangka;
    }

    public void setNomorRangka(String nomorRangka) {
        this.nomorRangka = nomorRangka;
    }
}
