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
@RequestMapping("ddms/v1/qry/master/sales")
public class SalesQryController {

    @Autowired
    private SalesService ss;

    @Value("${property.max_limit}")
    public int CONSTANTS_MAX_LIMIT;

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(@RequestBody Map<String, Object> inputPayload,
                                          @RequestHeader(name = "token", required = false) String token){
        try{
            Claims claims = GenerateJWT.validateToken(token);
            String salesName = valueToStringOrEmpty(inputPayload, "salesName");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String salesStatus = valueToStringOrEmpty(inputPayload, "salesStatus");
            String offset_s = valueToStringOrEmpty(inputPayload, "offset");
            String limit_s = valueToStringOrEmpty(inputPayload, "limit");

            int offset = 0;
            int limit = CONSTANTS_MAX_LIMIT;

            if(salesName.isEmpty() && dealerId.isEmpty() && salesStatus.isEmpty()){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(!limit_s.isEmpty() || Integer.parseInt(limit_s) < 0){
                limit = Integer.parseInt(limit_s);
            }
            if(!offset_s.isEmpty() || Integer.parseInt(offset_s) < 1){
                offset = Integer.parseInt(offset_s);
            }

            Pageable paging = PageRequest.of(offset, limit);

            Page<ViewAllSales> pages = ss.listViewBy(dealerId, salesStatus, salesName, paging);

            List<ViewAllSales> sales = pages.getContent();

            List<GetSales> getSalesList = new ArrayList<>();

            for(ViewAllSales sale:sales){
                try{
                    GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                            sale.getDealerCode(), sale.getSupervisorId(),
                            sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                    getSalesList.add(getSales);
                }catch (Exception e){
                    GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                            sale.getDealerCode(), "",
                            sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                    getSalesList.add(getSales);
                }
            }

            GetListSales getListSales = new GetListSales();
            getListSales.setListSales(getSalesList);
            getListSales.setDataOfRecord(pages.getTotalPages());

            return new ResponseEntity<>(new ResponseSuccess(getListSales), HttpStatus.OK);
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

    @GetMapping("/get/{salesId}")
    public ResponseEntity<Object> get(@PathVariable String salesId,
                                      @RequestHeader(name = "token", required = false) String token){
        try{
            Claims claims = GenerateJWT.validateToken(token);
            Optional<Sales> opt = ss.findBySalesId(salesId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            try {
                GetSales sales = new GetSales(
                        opt.get().getSalesId(),
                        opt.get().getSalesName(),
                        opt.get().getDealer().getDealerId(),
                        opt.get().getSupervisor().getSalesId(),
                        opt.get().getSalesGender(),
                        opt.get().getSalesEmail(),
                        opt.get().getSalesStatus()
                );
                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }catch (Exception e){
                GetSales sales = new GetSales(
                        opt.get().getSalesId(),
                        opt.get().getSalesName(),
                        opt.get().getDealer().getDealerId(),
                        "",
                        opt.get().getSalesGender(),
                        opt.get().getSalesEmail(),
                        opt.get().getSalesStatus()
                );
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

    @GetMapping("/test")
    public ResponseEntity<Object> test(@RequestBody Map<String, Object> inputPayload){
        String salesId = valueToStringOrEmpty(inputPayload, "salesId");
        String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
        return new ResponseEntity<>(ss.test(salesId, dealerId), HttpStatus.OK);
    }
}

