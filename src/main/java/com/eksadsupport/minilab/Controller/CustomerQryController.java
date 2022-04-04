package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Customer;
import com.eksadsupport.minilab.dto.customer.GetCustomerById;
import com.eksadsupport.minilab.dto.customer.ListAllDTO;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.response.ResponseUnauthorized;
import com.eksadsupport.minilab.service.CustomerService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eksadsupport.minilab.Common.Constants.*;
import static com.eksadsupport.minilab.Common.Constants.MESSAGE;
import static com.eksadsupport.minilab.Common.Util.checkStringIfNulllOrEmpty;
import static com.eksadsupport.minilab.Common.Util.valueToStringOrEmpty;

@RestController
@RequestMapping("/ddms/v1/qry/master/customer/")
public class CustomerQryController {
    @Autowired
    CustomerService cs;

    @Value("${property.max_limit}")
    private int CONSTANTS_MAX_LIMIT;

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(
            @RequestBody final Map<String,Object> request
//            @RequestHeader(name = "token", required = false) String token
    ) {
        try {
//            Claims claims = GenerateJWT.validateToken(token);
            String customerName = valueToStringOrEmpty(request, "customerName");
            String dealerId = valueToStringOrEmpty(request, "dealerId");
            String offset_s = valueToStringOrEmpty(request, "offset");
            String limit_s = valueToStringOrEmpty(request, "limit");
            int offset = 0;
            int limit = CONSTANTS_MAX_LIMIT;

            LinkedHashMap<String, Object> ret = new LinkedHashMap<>();
            LinkedHashMap<String, Object> ret2 = new LinkedHashMap<>();

            if (dealerId.isEmpty()) {
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }
            if (!offset_s.isEmpty()) {
                offset = Integer.parseInt(offset_s);
            }

            if (!limit_s.isEmpty()) {
                limit = Integer.parseInt(limit_s);
                List<ListAllDTO> index = cs.listAll(dealerId, customerName, limit, offset);
                if (index.size() == 0) {
                    return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
                } else {
                    ret.put("status", STATUS);
                    ret.put("code", CODE);
                    ret.put("message", MESSAGE);
                    ret2.put("List Customer", cs.listAll(dealerId, customerName, limit, offset));
                    ret.put("data", ret2);
                    ret.put("dataOfRecord", index.size());
                    return new ResponseEntity<>(ret, HttpStatus.OK);
                }
            }
            List<ListAllDTO> index = cs.listAll(dealerId, customerName, limit, offset);
            ret.put("status", STATUS);
            ret.put("code", CODE);
            ret.put("message", MESSAGE);
            ret2.put("List Customer", cs.listAll(dealerId, customerName, limit, offset));
            ret.put("data", ret2);
            ret.put("dataOfRecord", index.size());
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }catch (SignatureException ex) {
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException expired) {
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/get/{customerId}")
    public ResponseEntity<Object> getCustomerById(
            @PathVariable("customerId") String customerId
//            @RequestHeader(name = "token", required = false) String token
    ){
        try{
//            Claims claims = GenerateJWT.validateToken(token);
            Optional<Customer> opt=cs.findByCustomerId(customerId);
            if(customerId.isEmpty()||checkStringIfNulllOrEmpty(customerId)||!opt.isPresent() ){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }
            try {
                GetCustomerById getCustomerById = cs.getCustomerById(customerId);
                return new ResponseEntity<>(new ResponseSuccess(getCustomerById), HttpStatus.OK);
            }catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }
        } catch (SignatureException ex) {
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        } catch (ExpiredJwtException expired) {
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }


}
