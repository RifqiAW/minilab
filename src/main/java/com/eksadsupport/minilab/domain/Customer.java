package com.eksadsupport.minilab.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "mst_customer")

public class Customer {

    @Id
    @Size(min = 0,max = 50)
    @Column(name = "customer_id",nullable = false)
    private String customerId;

    @Size(min = 0,max = 255)
    @Column(name = "customer_name",nullable = false)
    private String customerName;

    @ManyToOne
    @Size(min = 0,max = 50)
    @JoinColumn(name = "dealer_code",nullable = false)
    private Dealer dealer;

    @Size(min = 0,max = 4)
    @Column(name = "customer_gender")
    private String customerGender;

    @Size(min = 0,max = 50)
    @Column(name = "customer_nik",nullable = false)
    private String customerNik;

    @Size(min = 0,max = 50)
    @Column(name = "customer_kk",nullable = false)
    private String customerKk;

    @Size(min = 0,max = 255)
    @Email
    @Column(name = "customer_email")
    private String customerEmail;

    @Size(min = 0,max = 512)
    @Column(name = "customer_address",nullable = false)
    private String customerAddress;

    @Size(min = 0,max = 50)
    @Column(name = "customer_telp_number")
    private String customerTelp;

    @Size(min = 0,max = 50)
    @Column(name = "customer_hp_number")
    private String customerHp;

    @ManyToOne
    @Size(min = 0,max = 50)
    @JoinColumn(name = "sales_id",nullable = false)
    private Sales sales;

    @Size(min = 0,max = 10)
    @Column(name = "customer_status",nullable = false)
    private String customerStatus;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerNik() {
        return customerNik;
    }

    public void setCustomerNik(String customerNik) {
        this.customerNik = customerNik;
    }

    public String getCustomerKk() {
        return customerKk;
    }

    public void setCustomerKk(String customerKk) {
        this.customerKk = customerKk;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTelp() {
        return customerTelp;
    }

    public void setCustomerTelp(String customerTelp) {
        this.customerTelp = customerTelp;
    }

    public String getCustomerHp() {
        return customerHp;
    }

    public void setCustomerHp(String customerHp) {
        this.customerHp = customerHp;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
}