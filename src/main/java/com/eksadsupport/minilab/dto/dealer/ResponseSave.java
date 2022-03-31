package com.eksadsupport.minilab.dto.dealer;

public class ResponseSave {

    private String code;
    private Object data;
    private String message;
    private String status;


    public void responseBadRequest(){
        this.code = "400";
        this.message = "ERROR_BAD_REQUEST";
        this.status = "F";
    }

    public void responseNoContent(){
        this.code = "204";
        this.message = "DATA_NOT_FOUND";
        this.status = "F";
    }

    public void responseSuccess(Object obj){
        this.code = "201";
        this.message = "PROCESS_SUCCESSED";
        this.status = "S";
        this.data = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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


}
