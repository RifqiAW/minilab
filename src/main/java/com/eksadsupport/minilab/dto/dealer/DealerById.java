package com.eksadsupport.minilab.dto.dealer;


import com.eksadsupport.minilab.domain.Dealer;

import java.util.Optional;



    public class DealerById {

        String status;
        String code;
        String message;
        Optional<Dealer> data;

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

        public Optional<Dealer> getData() {
            return data;
        }

        public void setData(Optional<Dealer> data) {
            this.data = data;
        }

}
