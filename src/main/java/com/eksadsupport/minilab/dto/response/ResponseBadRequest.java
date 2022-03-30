package com.eksadsupport.minilab.dto.response;

public class ResponseBadRequest extends Response{

    public ResponseBadRequest() {
        super();
        setCode("400");
        setMessage("Process Failed");
        setStatus("F");
    }
}
