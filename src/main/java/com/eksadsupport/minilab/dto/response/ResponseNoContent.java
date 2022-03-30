package com.eksadsupport.minilab.dto.response;

public class ResponseNoContent extends Response{

    public ResponseNoContent() {
        super();
        setCode("204");
        setMessage("Process Failed");
        setStatus("F");
    }
}
