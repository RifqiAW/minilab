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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            String unitSeriesName = inputPayload.get("unitSeriesName").toString();
            String dealerId = inputPayload.get("dealerId").toString();
            int unitQuantity = Integer.parseInt(inputPayload.get("unitQuantity").toString());
            String unitColor = inputPayload.get("unitColor").toString();
            String unitStatus = inputPayload.get("unitStatus").toString();
            double averageCost = Double.parseDouble(inputPayload.get("averageCost").toString());

//            if(!checkStringIfAlphabets(unitSeriesName) || !checkIfValidEmail(salesEmail)){
//                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
//            }

            if(checkStringIfNulllOrEmpty(unitId)){
                GetUnit unit = us.saveUnit(generateId(), unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
                return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
            }

            Optional<Unit> opt = us.findByUnitId(unitId);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }

            if(!isValidId(unitId)){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            GetUnit unit = us.updateUnit(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);

            return new ResponseEntity<>(new ResponseSuccess(unit), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }

}
