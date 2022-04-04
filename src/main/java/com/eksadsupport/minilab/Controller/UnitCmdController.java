package com.eksadsupport.minilab.Controller;

import com.eksadsupport.minilab.domain.Unit;
import com.eksadsupport.minilab.domain.ViewAllUnit;
import com.eksadsupport.minilab.dto.response.ResponseBadRequest;
import com.eksadsupport.minilab.dto.response.ResponseNoContent;
import com.eksadsupport.minilab.dto.response.ResponseSuccess;
import com.eksadsupport.minilab.dto.unit.GetListUnit;
import com.eksadsupport.minilab.dto.unit.GetUnit;
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

@RestController
@RequestMapping("ddms/v1/cmd/master/unit")
public class UnitCmdController {

    @Autowired
    private UnitService us;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Map<String, Object> inputPayload) {
        try {
            String unitCode = valueToStringOrEmpty(inputPayload, "unitCode");
            String unitSeriesName = valueToStringOrEmpty(inputPayload, "unitSeriesName");
            String dealerCode = valueToStringOrEmpty(inputPayload, "dealerId");
            String unitQuantity = valueToStringOrEmpty(inputPayload, "unitQuantity");
            String unitColor = valueToStringOrEmpty(inputPayload, "unitColor");
            String unitStatus = valueToStringOrEmpty(inputPayload, "unitStatus").toUpperCase(Locale.ROOT);
            String averageCost = valueToStringOrEmpty(inputPayload, "averageCost");

            if (unitCode.isEmpty() && ((unitSeriesName.isEmpty() || dealerCode.isEmpty() || unitQuantity.isEmpty() ||
                    unitColor.isEmpty() || unitStatus.isEmpty() || averageCost.isEmpty() ||
                    Double.parseDouble(averageCost) < 0))
            ){
                return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
            }

            if(checkStringIfNulllOrEmpty(unitCode)){
                if(unitStatus.isEmpty()){
                    GetUnit getUnit =
                            us.saveUnit(generateId(),
                            unitSeriesName,
                            dealerCode,
                            Integer.parseInt(unitQuantity),
                            unitColor,
                            unitStatus,
                            Double.parseDouble(averageCost)
                    );
                    return new ResponseEntity<>(new ResponseSuccess(getUnit), HttpStatus.OK);
                }
                else{
                    GetUnit getUnit = us.saveUnit(generateId(),
                            unitSeriesName,
                            dealerCode,
                            Integer.parseInt(unitQuantity),
                            unitColor,
                            unitStatus,
                            Double.parseDouble(averageCost)
                    );
                    return new ResponseEntity<>(new ResponseSuccess(getUnit), HttpStatus.OK);
                }
            }

            Optional<Unit> opt = us.findByUnitId(unitCode);

            if(!opt.isPresent()){
                return new ResponseEntity<>(new ResponseNoContent(), HttpStatus.NO_CONTENT);
            }else{
//                if(unitCode.isEmpty()){
//                    unitCode = opt.get().getUnitId();
//                }
                if(unitSeriesName.isEmpty()){
                    unitSeriesName = opt.get().getUnitSeriesName();
                }
                if(dealerCode.isEmpty()){
                    dealerCode = opt.get().getDealer().getDealerId();
                }
                if (unitQuantity.isEmpty()) {
                        unitQuantity = opt.get().getUnitQuantity() + "";
                    }
                if (unitColor.isEmpty()) {
                        unitColor = opt.get().getUnitColor();
                    }
                if (unitStatus.isEmpty()) {
                        unitStatus = opt.get().getUnitStatus();
                    }
                if (averageCost.isEmpty()) {
                        averageCost = opt.get().getAverageCost() + "";
                    }
                }
                if (Double.parseDouble(averageCost) < 0) {
                    return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
                }

            if(unitStatus.isEmpty()){
                GetUnit getUnit = us.updateUnit(
                                unitCode,
                                unitSeriesName,
                                dealerCode,
                                Integer.parseInt(unitQuantity),
                                unitColor,
                                unitStatus,
                                Double.parseDouble(averageCost)
                        );
                return new ResponseEntity<>(new ResponseSuccess(getUnit), HttpStatus.OK);
            }
            else{
                GetUnit getUnit = us.updateUnit(
                        unitCode,
                        unitSeriesName,
                        dealerCode,
                        Integer.parseInt(unitQuantity),
                        unitColor,
                        unitStatus,
                        Double.parseDouble(averageCost)
                );
                return new ResponseEntity<>(new ResponseSuccess(getUnit), HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ResponseBadRequest(), HttpStatus.BAD_REQUEST);
        }
    }
}
