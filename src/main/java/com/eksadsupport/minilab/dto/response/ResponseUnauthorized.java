package com.eksadsupport.minilab.dto.response;

public class ResponseUnauthorized extends Response{

    public ResponseUnauthorized() {
        super();
        setCode("401");
        setMessage("Process Fail");
        setStatus("F");
    }
}
