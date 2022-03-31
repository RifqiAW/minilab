package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.Common.Util;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.dto.response.Response;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.sales.GetListSales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.model.SalesSpecs;
import com.eksadsupport.minilab.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("ddms/v1/cmd/master/sales")
public class SalesController {

    @Autowired
    private SalesService ss;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload){
        try{
            Util util = new Util();

            String salesId = util.valueToStringOrEmpty(inputPayload, "salesId");
            String salesName = inputPayload.get("salesName").toString();
            String dealerId = inputPayload.get("dealerId").toString();
            String supervisorId = inputPayload.get("supervisorId").toString();
            String salesGender = inputPayload.get("salesGender").toString();
            String salesEmail = inputPayload.get("salesEmail").toString();
            String salesStatus = inputPayload.get("salesStatus").toString();

            if(!util.checkStringIfAlphabets(salesName) || !util.checkIfValidEmail(salesEmail)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(util.checkStringIfNulllOrEmpty(salesId)){
                GetSales sales = ss.saveSales(util.generateId(), salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }

            Optional<Sales> opt = ss.findBySalesId(salesId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            if(!util.isValidId(salesId)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            GetSales sales = ss.updateSales(salesId, salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);

            return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/listAll")
    public ResponseEntity<Object> listAll(@RequestBody Map<String, Object> inputPayload){
        Util util = new Util();
        try{
            String salesName = util.valueToStringOrEmpty(inputPayload, "salesName");
            String dealerId = util.valueToStringOrEmpty(inputPayload, "dealerId");
            String salesStatus = util.valueToStringOrEmpty(inputPayload, "salesStatus");
            int offset = Integer.parseInt(inputPayload.get("offset").toString());
            int limit = Integer.parseInt(inputPayload.get("limit").toString());

            if(salesName.isEmpty() && dealerId.isEmpty() && salesStatus.isEmpty()){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            Pageable paging = PageRequest.of(offset, limit);

            Page<Sales> pages = ss.listBy(dealerId, salesStatus, salesName, paging);

            List<Sales> sales = new ArrayList<Sales>();
            sales = pages.getContent();

            List<GetSales> getSalesList = new ArrayList<>();

            for(Sales sale:sales){
                GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                        sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                        sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                getSalesList.add(getSales);
            }

            GetListSales getListSales = new GetListSales();
            getListSales.setListSales(getSalesList);
            getListSales.setDataOfRecord(sales.size());

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

