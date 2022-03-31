package com.eksadsupport.minilab.Controller;



import com.eksadsupport.minilab.domain.Customer;
import com.eksadsupport.minilab.dto.customer.GetCustomerById;
import com.eksadsupport.minilab.dto.customer.ListAllDTO;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

import static com.eksadsupport.minilab.Common.Util.*;


@RestController
@RequestMapping("ddms/v1/cmd/master/customer")
public class CustomerController {
    @Autowired
    CustomerService cs;

    @PostMapping("/save")
    public  ResponseEntity<Object> save(
            @RequestBody final Map<String, Object> request
    ) {
        try{
        String customerId = request.get("customerId").toString();
        String customerName = request.get("customerName").toString();
        String dealerId = request.get("dealerId").toString();
        String customerGender = request.get("customerGender").toString();
        String customerNik = request.get("customerNik").toString();
        String customerKk = request.get("customerKk").toString();
        String customerEmail = request.get("customerEmail").toString();
        String customerAddress = request.get("customerAddress").toString();
        String customerTelp = request.get("customerTelp").toString();
        String customerHp = request.get("customerHp").toString();
        String customerStatus = request.get("customerStatus").toString();
        String salesId = request.get("salesId").toString();

        if(!checkStringIfAlphabets(customerName) || !checkIfValidEmail(customerEmail)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }

        if(checkStringIfNulllOrEmpty(customerId)){
            Customer customer= cs.addCus(generateId(), customerName, dealerId, customerGender, customerNik, customerKk,
                    customerEmail, customerAddress, customerTelp, customerHp, customerStatus, salesId);
            return new ResponseEntity<>(new ResponseSuccess(customer), HttpStatus.OK);
        }

        Optional<Customer> opt2 = cs.findByCustomerId(customerId);

        if(!opt2.isPresent()){
            return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
        }
        if(!isValidId(customerId)){
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        Customer customer=cs.updateCus(customerName,dealerId,customerGender,customerNik,customerKk,
                customerEmail,customerAddress,customerTelp,customerHp,customerStatus,salesId,customerId);

            return new ResponseEntity<>(new ResponseSuccess(customer), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @Value("${property.max_limit}")
    private int CONSTANTS_MAX_LIMIT;

    @PostMapping("/tes2")
    public ResponseEntity<Object> listAll(
            @RequestBody final Map<String,Object>request
    ){
        String customerName = valueToStringOrEmpty(request, "customerName");
        String dealerId = valueToStringOrEmpty(request, "dealerId");
        String offset_s =valueToStringOrEmpty(request, "offset");
        String limit_s =valueToStringOrEmpty(request, "limit");

        int offset = 0;
        int limit = CONSTANTS_MAX_LIMIT;

        LinkedHashMap<String,Object>ret=new LinkedHashMap<>();


        if(dealerId.isEmpty()){
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        if(!offset_s.isEmpty()){
            offset = Integer.parseInt(offset_s);
        }

        if(!limit_s.isEmpty()){
            limit = Integer.parseInt(limit_s);
            List<ListAllDTO>index = cs.listAll(dealerId,customerName,limit,offset);
            ret.put("status","S");
            ret.put("code","201");
            ret.put("message","Process Success");
            ret.put("data",cs.listAll(dealerId,customerName,limit,offset));
            ret.put("dataOfRecord",index.size());
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }

        List<ListAllDTO> index = cs.listAll(dealerId,customerName,limit,offset);
        ret.put("status","S");
        ret.put("code","201");
        ret.put("message","Process Success");
        ret.put("data",cs.listAll(dealerId,customerName,limit,offset));
        ret.put("dataOfRecord",index.size());
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }



    @GetMapping("/tes3/{customerId}")
    public ResponseEntity<Object> getCustomerById(
            @PathVariable("customerId") String customerId
    ){
        LinkedHashMap<String,Object>ret=new LinkedHashMap<>();
        Optional<Customer>opt=cs.findByCustomerId(customerId);
        if(customerId.isEmpty()){
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
        try {
            GetCustomerById getCustomerById = cs.getCustomerById(customerId);
            return new ResponseEntity<>(new ResponseSuccess(getCustomerById), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
        }
    }

}
