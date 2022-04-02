package com.eksadsupport.minilab.dto.ppn;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GetPpn {
    private String ppnId;
    private String dealerId;
    private String ppnDescription;
    private Float ppnRate;
    private Float ppnRatePrevious;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT+7")
    private Date effectiveStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "GMT+7")
    private Date effectiveEndDate;
    private String ppnStatus;

    public GetPpn(String ppnId, String dealerId, String ppnDescription, Float ppnRate, Float ppnRatePrevious,
                  Date effectiveStartDate, Date effectiveEndDate, String ppnStatus) {
        System.out.println(ppnId);
        this.ppnId = ppnId;
        this.dealerId = dealerId;
        this.ppnDescription = ppnDescription;
        this.ppnRate = ppnRate;
        this.ppnRatePrevious = ppnRatePrevious;
        this.effectiveStartDate = effectiveStartDate;
        this.effectiveEndDate = effectiveEndDate;
        this.ppnStatus = ppnStatus;
    }

    public String getPpnId() {
        return ppnId;
    }

    public void setPpnId(String ppnId) {
        this.ppnId = ppnId;
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

    public Float getPpnRate() {
        return ppnRate;
    }

    public void setPpnRate(Float ppnRate) {
        this.ppnRate = ppnRate;
    }

    public Float getPpnRatePrevious() {
        return ppnRatePrevious;
    }

    public void setPpnRatePrevious(Float ppnRatePrevious) {
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

    public String getPpnStatus() {
        return ppnStatus;
    }

    public void setPpnStatus(String ppnStatus) {
        this.ppnStatus = ppnStatus;
    }
}
