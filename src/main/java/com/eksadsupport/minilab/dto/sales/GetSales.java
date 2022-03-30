package com.eksadsupport.minilab.dto.sales;

public class GetSales {
    private String salesId;
    private String salesName;
    private String dealerId;
    private String supervisorId;
    private String salesGender;
    private String salesEmail;
    private String salesStatus;

    public GetSales(String salesId, String salesName, String dealerId, String supervisorId, String salesGender, String salesEmail, String salesStatus) {
        this.salesId = salesId;
        this.salesName = salesName;
        this.dealerId = dealerId;
        this.supervisorId = supervisorId;
        this.salesGender = salesGender;
        this.salesEmail = salesEmail;
        this.salesStatus = salesStatus;
    }

    public String getSalesId() {
        return salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSalesGender() {
        return salesGender;
    }

    public void setSalesGender(String salesGender) {
        this.salesGender = salesGender;
    }

    public String getSalesEmail() {
        return salesEmail;
    }

    public void setSalesEmail(String salesEmail) {
        this.salesEmail = salesEmail;
    }

    public String getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }
}
