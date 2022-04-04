package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.GenerateJWT;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.domain.ViewAllSales;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.response.ResponseUnauthorized;
import com.eksadsupport.minilab.dto.sales.GetListSales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.service.SalesService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.eksadsupport.minilab.Common.Util.*;

@RestController
@RequestMapping("ddms/v1/cmd/master/sales")
public class SalesCmdController {

    @Autowired
    private SalesService ss;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload,
                                       @RequestHeader(name = "token", required = false) String token){
        try{
            Claims claims = GenerateJWT.validateToken(token);
            String salesId = valueToStringOrEmpty(inputPayload, "salesId");
            String salesName = valueToStringOrEmpty(inputPayload, "salesName");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String supervisorId = valueToStringOrEmpty(inputPayload, "supervisorId");
            String salesGender = valueToStringOrEmpty(inputPayload, "salesGender").toUpperCase(Locale.ROOT);
            String salesEmail = valueToStringOrEmpty(inputPayload, "salesEmail");
            String salesStatus = valueToStringOrEmpty(inputPayload, "salesStatus").toUpperCase(Locale.ROOT);

            if(checkStringIfNulllOrEmpty(salesId) && (!checkIfValidEmail(salesEmail) || checkStringIfNulllOrEmpty(salesName)
                    || checkStringIfNulllOrEmpty(dealerId) || checkStringIfNulllOrEmpty(salesGender) || checkStringIfNulllOrEmpty(salesEmail)
                    || checkStringIfNulllOrEmpty(salesStatus))){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(checkStringIfNulllOrEmpty(salesId)){
                if(supervisorId.isEmpty()){
                    GetSales sales = ss.saveSales(generateId(), salesName, dealerId, null, salesGender, salesEmail, salesStatus);

                    return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
                }else{
                    GetSales sales = ss.saveSales(generateId(), salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                    return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
                }
            }

            Optional<Sales> opt = ss.findBySalesId(salesId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }else{
                if(salesName.isEmpty()){
                    salesName = opt.get().getSalesName();
                }
                if(dealerId.isEmpty()){
                    dealerId = opt.get().getDealer().getDealerId();
                }
                if(salesGender.isEmpty()){
                    salesGender = opt.get().getSalesGender();
                }
                try {
                    if(supervisorId.isEmpty()){
                        supervisorId = opt.get().getSupervisor().getSalesId();
                    }
                }catch (Exception e){
                    supervisorId = null;
                }
                if(salesEmail.isEmpty()){
                    salesEmail = opt.get().getSalesEmail();
                }
                if(salesStatus.isEmpty()){
                    salesStatus = opt.get().getSalesStatus();
                }
            }

            if(!checkIfValidEmail(salesEmail)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(checkStringIfNulllOrEmpty(supervisorId)){
                GetSales sales = ss.updateSales(salesId, salesName, dealerId, null, salesGender, salesEmail, salesStatus);

                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }else{
                GetSales sales = ss.updateSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }
        }
        catch (ExpiredJwtException expired){
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        }
        catch (SignatureException e) {
            return new ResponseEntity<>(new ResponseUnauthorized(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}

