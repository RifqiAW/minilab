package com.eksadsupport.minilab.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mst_dealer")
public class Dealer {

    @Id
    @Size(max = 50)
    @Column(name = "dealer_code")
    private String dealerId;

    @Column(name = "dealerName")
    private String dealerName;

    @Column(name = "dealerClass")
    @Size(max = 10)
    private String dealerClass;

    @Column(name = "telpNumber")
    private String telpNumber;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "dealerStatus")
    @Size(max = 10)
    private String dealerStatus;

    @Column(name = "dealerExtCode")
    @Size(max = 50)
    private String dealerExtCode;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerClass() {
        return dealerClass;
    }

    public void setDealerClass(String dealerClass) {
        this.dealerClass = dealerClass;
    }

    public String getTelpNumber() {
        return telpNumber;
    }

    public void setTelpNumber(String telpNumber) {
        this.telpNumber = telpNumber;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDealerStatus() {
        return dealerStatus;
    }

    public void setDealerStatus(String dealerStatus) {
        this.dealerStatus = dealerStatus;
    }

    public String getDealerExtCode() {
        return dealerExtCode;
    }

    public void setDealerExtCode(String dealerExtCode) {
        this.dealerExtCode = dealerExtCode;
    }
}
