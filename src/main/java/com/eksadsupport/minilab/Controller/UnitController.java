package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Unit;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.unit.GetUnit;
import com.eksadsupport.minilab.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

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
