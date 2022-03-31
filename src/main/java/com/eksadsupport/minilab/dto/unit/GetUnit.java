package com.eksadsupport.minilab.dto.unit;

public class GetUnit {

    private String unitId;
    private String unitSeriesName;
    private String dealerId;
    private int unitQuantity;
    private String unitColor;
    private String unitStatus;
    private float averageCost;

    public GetUnit(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost) {
        this.unitId = unitId;
        this.unitSeriesName = unitSeriesName;
        this.dealerId = dealerId;
        this.unitQuantity = unitQuantity;
        this.unitColor = unitColor;
        this.unitStatus = unitStatus;
        this.averageCost = (float) averageCost;
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

    public int getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(int unitQuantity) {
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

    public float getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(float averageCost) {
        this.averageCost = averageCost;
    }
}
