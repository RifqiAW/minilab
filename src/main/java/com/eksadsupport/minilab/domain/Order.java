package com.eksadsupport.minilab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trx_order")
public class Order {

    @Id
    @Size(max = 50)
    private String orderId;

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

    @Column(name = "is_leasin")
    private String isLeasing;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "unit_status")
    private String unitStatus;
}
