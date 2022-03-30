package com.eksadsupport.minilab.dto.response;

public class ResponseSuccess extends Response{

    public ResponseSuccess(Object obj) {
        super();
        setCode("201");
        setMessage("Process Succeed");
        setStatus("S");
        setData(obj);
    }
}
