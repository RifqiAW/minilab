package com.eksadsupport.minilab.domain;

import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "vw_mst_unit")
public class ViewAllUnit {

    @Id
    private String unitId;
    private String unitSeriesName;
    private String dealerCode;
    private int unitQuantity;
    private String unitColor;
    private String unitStatus;

    public void setUnitQuantity(int unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public void setAverageCost(double averageCost) {
        this.averageCost = averageCost;
    }

    private double averageCost;

    public int getUnitQuantity() {
        return unitQuantity;
    }

    public double getAverageCost() {
        return averageCost;
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

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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

}
