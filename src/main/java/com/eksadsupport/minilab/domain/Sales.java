package com.eksadsupport.minilab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mst_sales")
public class Sales {

    @Id
    @Size(max = 50)
    private String salesId;

    @Size(max = 255)
    @Column(name = "sales_name")
    private String salesName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dealer_code")
    @JsonIgnore
    private Dealer dealer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supervisor_id")
    @JsonIgnore
    private Sales supervisor;

    @Size(max=4)
    @Column(name = "sales_email")
    private String salesEmail;

    @Size(max=10)
    @Column(name = "sales_status")
    private String salesStatus;

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

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Sales getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Sales supervisor) {
        this.supervisor = supervisor;
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
