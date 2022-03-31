package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.sales.GetListSales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.service.SalesService;
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
public class SalesController {

    @Autowired
    private SalesService ss;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload){
        try{
            String salesId = valueToStringOrEmpty(inputPayload, "salesId");
            String salesName = valueToStringOrEmpty(inputPayload, "salesName");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String supervisorId = valueToStringOrEmpty(inputPayload, "supervisorId");
            String salesGender = valueToStringOrEmpty(inputPayload, "salesGender");
            String salesEmail = valueToStringOrEmpty(inputPayload, "salesEmail");
            String salesStatus = valueToStringOrEmpty(inputPayload, "salesStatus");

//            if(!checkStringIfNulllOrEmpty(salesId) && (!checkStringIfAlphabets(salesName) || !checkIfValidEmail(salesEmail) || checkStringIfNulllOrEmpty(salesName)
//                || checkStringIfNulllOrEmpty(dealerId) || checkStringIfNulllOrEmpty(salesGender) || checkStringIfNulllOrEmpty(salesEmail)
//                || checkStringIfNulllOrEmpty(salesStatus))){
//                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
//            }

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
//                if(supervisorId.isEmpty()){
//                    try{
//                        supervisorId = opt.get().getSupervisor().getSalesId();
//                    }catch (Exception e){
//                        supervisorId = null;
//                    }
//                }
                if(salesGender.isEmpty()){
                    salesGender = opt.get().getSalesGender();
                }
                if(salesEmail.isEmpty()){
                    salesEmail = opt.get().getSalesEmail();
                }
                if(salesStatus.isEmpty()){
                    salesStatus = opt.get().getSalesStatus();
                }
            }

//            if(!isValidId(salesId)){
//                System.out.println(salesId);
//                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
//            }

            if(supervisorId.isEmpty()){
                GetSales sales = ss.updateSales(salesId, salesName, dealerId, null, salesGender, salesEmail, salesStatus);

                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }else{
                GetSales sales = ss.updateSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @Value("${property.max_limit}")
    public int CONSTANTS_MAX_LIMIT;

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(@RequestBody Map<String, Object> inputPayload){
        try{
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

            if(!limit_s.isEmpty()){
                limit = Integer.parseInt(limit_s);
            }
            if(!offset_s.isEmpty()){
                offset = Integer.parseInt(offset_s);
            }

            Pageable paging = PageRequest.of(offset, limit);

            Page<Sales> pages = ss.listViewBy(dealerId, salesStatus, salesName, paging);

            List<Sales> sales = pages.getContent();

            List<GetSales> getSalesList = new ArrayList<>();

            for(Sales sale:sales){
                try{
                    GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                            sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                            sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                    getSalesList.add(getSales);
                }catch (Exception e){
                    GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                            sale.getDealer().getDealerId(), null,
                            sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                    getSalesList.add(getSales);
                }

            }

            GetListSales getListSales = new GetListSales();
            getListSales.setListSales(getSalesList);
            getListSales.setDataOfRecord(pages.getTotalPages());

            return new ResponseEntity<>(new ResponseSuccess(getListSales), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{salesId}")
    public ResponseEntity<Object> get(@PathVariable String salesId){
        try{
            Optional<Sales> opt = ss.findBySalesId(salesId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            GetSales sales = ss.get(salesId);
            return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}

