package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Unit;
import com.eksadsupport.minilab.dto.unit.GetUnit;
import com.eksadsupport.minilab.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository ur;

    public GetUnit saveUnit(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost){
        ur.save(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
        return new GetUnit(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
    }

    public Optional<Unit> findByUnitId(String unitId){
        return ur.findByUnitId(unitId);
    }

    public GetUnit updateUnit(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost){
        ur.save(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
        return new GetUnit(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
    }

//    public List<Sales> listAll(String dealerId, String salesStatus, String salesName, int limit, int offset){
//        return sr.listAll(dealerId, salesStatus, salesName, limit, offset);
//    }

//    public Page<Sales> listAll(String dealerId, String salesStatus, String salesName, Pageable pageable){
//        return sr.listAll(dealerId, salesStatus, salesName, pageable);
//    }
//
//    public Page<Sales> listEverything(Pageable pageable){
//        return sr.listEverything(pageable);
//    }
}
