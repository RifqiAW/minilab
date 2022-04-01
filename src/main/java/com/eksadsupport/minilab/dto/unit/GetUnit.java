package com.eksadsupport.minilab.dto.unit;

public class GetUnit {

    private String unitId;
    private String unitSeriesName;
    private String dealerId;
    private String unitQuantity;
    private String unitColor;
    private String unitStatus;
    private String averageCost;

    public GetUnit(String unitId, String unitSeriesName, String dealerId, String unitQuantity, String unitColor, String unitStatus, String averageCost) {
        this.unitId = unitId;
        this.unitSeriesName = unitSeriesName;
        this.dealerId = dealerId;
        this.unitQuantity = unitQuantity;
        this.unitColor = unitColor;
        this.unitStatus = unitStatus;
        this.averageCost = averageCost;
    }

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
