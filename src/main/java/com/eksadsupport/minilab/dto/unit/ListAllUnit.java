package com.eksadsupport.minilab.dto.unit;

public class ListAllUnit {
    private String unitId;
    private String unitSeriesName;
    private String dealerId;
    private String unitQuantity;
    private String unitColor;
    private String unitStatus;
    private String averageCost;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitSeriesName() {
        return unitSeriesName;
    }

    public void setUnitSeriesName(String unitSeriesName) {
        this.unitSeriesName = unitSeriesName;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public String getUnitColor() {
        return unitColor;
    }

    public void setUnitColor(String unitColor) {
        this.unitColor = unitColor;
    }

    public String getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(String unitStatus) {
        this.unitStatus = unitStatus;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }
}
