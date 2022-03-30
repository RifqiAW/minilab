package com.eksadsupport.minilab.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mst_ppn")
public class Ppn {
    @Id
    @Column(name = "ppn_id", nullable = false, length = 50)
    private String ppnId;

    @Column(name = "Description", nullable = false, length = 255)
    private String Description;

    @ManyToOne
    @JoinColumn(name = "dealer_code", nullable = false)
    private Dealer dealer;

    @Column(name = "effective_start_date")
    private Date effectiveStartDate;

    @Column(name = "effective_end_date")
    private Date effectiveEndDate;

    @Column(name = "ppn_rate", nullable = false, length = 8)
    private float ppnRate;

    @Column(name = "ppn_rate_previous", length = 8)
    private float ppnRatePrevious;

    @Column(name = "ppn_status", length = 10)
    private String ppnStatus;

    public String getPpnId() {
        return ppnId;
    }

    public void setPpnId(String ppnId) {
        this.ppnId = ppnId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
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

    public String getPpnStatus() {
        return ppnStatus;
    }

    public void setPpnStatus(String ppnStatus) {
        this.ppnStatus = ppnStatus;
    }
}