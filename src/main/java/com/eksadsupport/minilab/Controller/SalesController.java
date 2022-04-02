package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.domain.ViewAllSales;
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


        }catch (Exception e){
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

