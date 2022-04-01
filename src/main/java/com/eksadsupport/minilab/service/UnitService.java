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

    public GetUnit saveUnit(String unitId, String unitSeriesName, String dealerId, String unitQuantity, String unitColor, String unitStatus, String averageCost){
        ur.save(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);
        Unit unit = ur.getByUnitId(unitId);
        try{
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }catch (Exception e){
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }
    }

    public GetUnit updateUnit(String unitId, String unitSeriesName, String dealerId, String unitQuantity, String unitColor, String unitStatus, String averageCost){
        ur.save(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);

        Unit unit = ur.getByUnitId(unitId);
        try{
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }catch (Exception e){
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }
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

    public GetUnit get(String unitId){
        Unit unit = ur.getByUnitId(unitId);
        try{
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }catch (Exception e){
            return new GetUnit(unit.getUnitId(), unit.getUnitSeriesName(),
                    unit.getDealer().getDealerId(), unit.getUnitQuantity(),
                    unit.getUnitColor(), unit.getUnitStatus(), unit.getAverageCost());
        }
    }

    public Optional<Unit> findByUnitId(String unitId){
        return ur.findByUnitId(unitId);
    }

}
