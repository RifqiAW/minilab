package com.eksadsupport.minilab.dto.customer;

public class GetCustomerDTO {
    private String customerId;
    private String customerNama;
    private String dealerId;
    private String customerGender;
    private String customerNik;
    private String customerKk;
    private String customerEmail;
    private String customerAddress;
    private String customerTelp;
    private String customerHp;
    private String salesId;
    private String customerStatus;


    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNama() {
        return customerNama;
    }

    public void setCustomerNama(String customerNama) {
        this.customerNama = customerNama;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
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

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
}
