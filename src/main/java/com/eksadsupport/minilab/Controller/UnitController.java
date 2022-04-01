package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Unit;
import com.eksadsupport.minilab.domain.ViewAllSales;
import com.eksadsupport.minilab.domain.ViewAllUnit;
import com.eksadsupport.minilab.dto.customer.ListAllDTO;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.sales.GetListSales;
import com.eksadsupport.minilab.dto.sales.GetSales;
import com.eksadsupport.minilab.dto.unit.GetListUnit;
import com.eksadsupport.minilab.dto.unit.GetUnit;
import com.eksadsupport.minilab.dto.unit.ListAllUnit;
import com.eksadsupport.minilab.service.UnitService;
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
import static com.eksadsupport.minilab.Common.Util.isValidId;

@RestController
@RequestMapping("ddms/v1/cmd/master/unit")
public class UnitController {

    @Autowired
    private UnitService us;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload){
        try{
            String unitId = valueToStringOrEmpty(inputPayload, "unitId");
            String unitSeriesName = valueToStringOrEmpty(inputPayload, "unitSeriesName");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String unitQuantity = valueToStringOrEmpty(inputPayload, "unitQuantity");
            String unitColor = valueToStringOrEmpty(inputPayload, "unitColor");
            String unitStatus = valueToStringOrEmpty(inputPayload, "unitStatus");
            String averageCost = valueToStringOrEmpty(inputPayload, "averageCost");

            if(checkStringIfNulllOrEmpty(unitId)){
                if(unitId.isEmpty()){
                    GetUnit unit = us.saveUnit(generateId(), unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);

                    return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
                }else{
                    GetUnit unit = us.saveUnit(generateId(), unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
                    return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
                }
            }

            Optional<Unit> opt = us.findByUnitId(unitId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }else{
                if(unitSeriesName.isEmpty()){
                    unitSeriesName = opt.get().getUnitSeriesName();
                }
                if(dealerId.isEmpty()){
                    dealerId = opt.get().getDealer().getDealerId();
                }
                if(unitQuantity.isEmpty()){
                    unitQuantity = opt.get().getUnitQuantity();
                }
                if(unitColor.isEmpty()){
                    unitColor = opt.get().getUnitColor();
                }
                if(unitStatus.isEmpty()){
                    unitStatus = opt.get().getUnitStatus();
                }
                if(averageCost.isEmpty()){
                    averageCost = opt.get().getAverageCost();
                }
            }


            if(unitId.isEmpty()){
                GetUnit unit = us.updateUnit(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);

                return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
            }else{
                GetUnit unit = us.updateUnit(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
                return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
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
            String unitSeriesName = valueToStringOrEmpty(inputPayload, "unitSeriesName");
            String dealerId = valueToStringOrEmpty(inputPayload, "dealerId");
            String unitStatus = valueToStringOrEmpty(inputPayload, "unitStatus");
            String offset_s = valueToStringOrEmpty(inputPayload, "offset");
            String limit_s = valueToStringOrEmpty(inputPayload, "limit");

            int offset = 0;
            int limit = CONSTANTS_MAX_LIMIT;

            if(unitSeriesName.isEmpty() && dealerId.isEmpty() && unitStatus.isEmpty()){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(!limit_s.isEmpty() || Integer.parseInt(limit_s) < 0){
                limit = Integer.parseInt(limit_s);
            }
            if(!offset_s.isEmpty() || Integer.parseInt(offset_s) < 1){
                offset = Integer.parseInt(offset_s);
            }

            Pageable paging = PageRequest.of(offset, limit);

            Page<ViewAllUnit> pages = us.listViewBy(dealerId, unitStatus, unitSeriesName, paging);

            List<ViewAllUnit> Unit = pages.getContent();

            List<GetUnit> getUnitList = new ArrayList<>();

            for(ViewAllUnit unit:Unit){
                try{
                    GetUnit getUnit = new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                            unit.getDealerCode(), unit.getUnitQuantity(),
                            unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());

                    getUnitList.add(getUnit);
                }catch (Exception e){
                    GetUnit getUnit = new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                            unit.getDealerCode(), unit.getUnitQuantity(),
                            unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());

                    getUnitList.add(getUnit);
                }
            }

            GetListUnit getListUnit = new GetListUnit();
            getListUnit.setListUnit(getUnitList);
            getListUnit.setDataOfRecord(pages.getTotalPages());

            return new ResponseEntity<>(new ResponseSuccess(getListUnit), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{unitId}")
    public ResponseEntity<Object> get(@PathVariable String unitId){
        try{
            Optional<Unit> opt = us.findByUnitId(unitId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            GetUnit unit = us.get(unitId);
            return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

}
