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
    private String dealer_code;

    @Column(name = "dealerName")
    private String dealer_name;

    @Column(name = "dealerClass")
    @Size(max = 10)
    private String dealer_class;

    @Column(name = "telpNumber")
    private String telp_number;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "dealerStatus")
    @Size(max = 10)
    private String dealer_status;

    @Column(name = "dealerExtCode")
    @Size(max = 50)
    private String dealer_ext_code;

    public String getDealer_code() {
        return dealer_code;
    }

    public void setDealer_code(String dealer_code) {
        this.dealer_code = dealer_code;
    }

    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }

    public String getDealer_class() {
        return dealer_class;
    }

    public void setDealer_class(String dealer_class) {
        this.dealer_class = dealer_class;
    }

    public String getTelp_number() {
        return telp_number;
    }

    public void setTelp_number(String telp_number) {
        this.telp_number = telp_number;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDealer_status() {
        return dealer_status;
    }

    public void setDealer_status(String dealer_status) {
        this.dealer_status = dealer_status;
    }

    public String getDealer_ext_code() {
        return dealer_ext_code;
    }

    public void setDealer_ext_code(String dealer_ext_code) {
        this.dealer_ext_code = dealer_ext_code;
    }
}
