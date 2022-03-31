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
            String salesName = inputPayload.get("salesName").toString();
            String dealerId = inputPayload.get("dealerId").toString();
            String supervisorId = inputPayload.get("supervisorId").toString();
            String salesGender = inputPayload.get("salesGender").toString();
            String salesEmail = inputPayload.get("salesEmail").toString();
            String salesStatus = inputPayload.get("salesStatus").toString();

            if(!checkStringIfAlphabets(salesName) || !checkIfValidEmail(salesEmail)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(checkStringIfNulllOrEmpty(salesId)){
                GetSales sales = ss.saveSales(generateId(), salesName, dealerId, supervisorId, salesGender, salesEmail, salesStatus);
                return new ResponseEntity<>(new ResponseSuccess(sales), HttpStatus.OK);
            }

            Optional<Sales> opt = ss.findBySalesId(salesId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            if(!isValidId(salesId)){
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

//            Page<Sales> pages = ss.listBy(dealerId, salesStatus, salesName, paging);
            Page<Sales> pages = ss.listViewBy(dealerId, salesStatus, salesName, paging);
//            Page<ViewAllSales> pages = ss.listViewBy(dealerId, salesStatus, salesName, paging);

            List<Sales> sales = pages.getContent();
//            List<ViewAllSales> sales = pages.getContent();

            List<GetSales> getSalesList = new ArrayList<>();

            for(Sales sale:sales){
                GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
                        sale.getDealer().getDealerId(), sale.getSupervisor().getSalesId(),
                        sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());

                getSalesList.add(getSales);
            }


//            ArrayList<ViewAllSales> arr = new ArrayList<ViewAllSales>(sales.size());
//            for(int i=0; i < sales.size(); i++){
//                ViewAllSales sale = sales.get(i);
//                new ViewAllSales(sale.getId(), sale.getSalesId(), sale.getSalesName(), "222", "aaa", "bbb", "ccc", "ddd")
//                System.out.println(sale);
//                GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
//                        sale.getDealer(), sale.getSupervisor(),
//                        sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
//
//                getSalesList.add(getSales);
//            }

//            for(ViewAllSales sale:sales){
//                System.out.println(sale.getSalesId());
//                System.out.println(sale.getDealer());
//                System.out.println(sale.getSupervisor());
//                GetSales getSales = new GetSales(sale.getSalesId(), sale.getSalesName(),
//                        sale.getDealer(), sale.getSupervisor(),
//                        sale.getSalesGender(), sale.getSalesEmail(), sale.getSalesStatus());
//
//                getSalesList.add(getSales);
//            }

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

