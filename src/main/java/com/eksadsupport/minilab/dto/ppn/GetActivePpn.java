package com.eksadsupport.minilab.dto.ppn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GetActivePpn {
    private String id;
    private String dealerId;
    private String ppnDescription;
    private float ppnRate;
    private float ppnRatePrevious;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private Date effectiveStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm",timezone = "GMT+7")
    private Date effectiveEndDate;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getPpnDescription() {
        return ppnDescription;
    }

    public void setPpnDescription(String ppnDescription) {
        this.ppnDescription = ppnDescription;
    }

    public float getPpnRate() {
        return ppnRate;
    }

    public void setPpnRate(float ppnRate) {
        this.ppnRate = ppnRate;
    }

    public float getPpnRatePrevious() {
        return ppnRatePrevious;
    }

    public void setPpnRatePrevious(float ppnRatePrevious) {
        this.ppnRatePrevious = ppnRatePrevious;
    }

    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Date effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Date effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
