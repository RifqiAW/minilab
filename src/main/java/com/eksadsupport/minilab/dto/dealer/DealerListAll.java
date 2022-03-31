package com.eksadsupport.minilab.dto.dealer;

import com.eksadsupport.minilab.domain.Dealer;


import java.util.List;

public class DealerListAll {

    String status;
    String code;
    String message;
    Object data;
    int dataOfRecord;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getDataOfRecord() {
        return dataOfRecord;
    }

    public void setDataOfRecord(int dataOfRecord) {
        this.dataOfRecord = dataOfRecord;
    }
}
