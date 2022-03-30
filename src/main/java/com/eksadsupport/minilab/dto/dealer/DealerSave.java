package com.eksadsupport.minilab.dto.dealer;

import com.eksadsupport.minilab.domain.Dealer;

import java.util.List;

public class DealerSave {


import java.util.List;

    public class DealerSave {

        String code;
        List<Dealer> data;
        String message;
        String status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<Dealer> getData() {
            return data;
        }

        public void setData(List<Dealer> data) {
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
