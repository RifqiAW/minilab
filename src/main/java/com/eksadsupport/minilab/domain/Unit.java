package com.eksadsupport.minilab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mst_unit")
public class Unit {

    @Id
    @Size (max = 50)
    private String unitId;

    @Size (max = 255)
    @Column(name = "unit_series_name")
    private String unitSeriesName;

    @Size (max = 50)
    @Column(name = "dealer_id")
    private String dealerId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="dealer_id")
//    @JsonIgnore
//    private Dealer dealer;

    @Size (max = 4)
    @Column(name = "unit_quantity")
    private int unitQuantity;

    @Size (max = 512)
    @Column(name = "unit_color")
    private String unitColor;

    @Size (max = 10)
    @Column(name = "unit_status")
    private String unitStatus;

    @Column(name = "average_cost")
    private float averageCost;

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

//    public Dealer getDealer() {
//        return dealer;
//    }
//
//    public void setDealer(Dealer dealer) {
//        this.dealer = dealer;
//    }

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

