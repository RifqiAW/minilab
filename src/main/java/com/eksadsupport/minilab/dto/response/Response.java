package com.eksadsupport.minilab.dto.response;

import com.eksadsupport.minilab.domain.Sales;

public class Response {
    private String code;
    private String message;
    private String status;
    private Object data;

    public void responseBadRequest(){
        this.code = "400";
        this.message = "Process Failed";
        this.status = "F";
    }

    public void responseNoContent(){
        this.code = "204";
        this.message = "Process Failed";
        this.status = "F";
    }

    public void responseSuccess(Object obj){
        this.code = "201";
        this.message = "Process Succeed";
        this.status = "S";
        this.data = obj;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
