package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.Order;
import com.eksadsupport.minilab.domain.Sales;
import com.eksadsupport.minilab.domain.Unit;
import com.eksadsupport.minilab.domain.ViewAllUnit;
import com.eksadsupport.minilab.dto.unit.GetUnit;
import com.eksadsupport.minilab.model.UnitSpecs;
import com.eksadsupport.minilab.model.ViewSalesSpecs;
import com.eksadsupport.minilab.repository.UnitRepository;
import com.eksadsupport.minilab.repository.ViewUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository ur;

    @Autowired
    ViewUnitRepository vur;

    public GetUnit saveUnit(String unitId, String unitSeriesName, String dealerId, int unitQuantity, String unitColor, String unitStatus, double averageCost){
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

    public GetUnit updateUnit(String unitId, String unitSeriesName, String dealerId, Integer unitQuantity, String unitColor, String unitStatus, double averageCost){
        ur.update(unitId, unitSeriesName, dealerId, unitQuantity, unitColor, unitStatus, averageCost);

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

    public Page<Unit> listBy(String dealerId, String unitStatus, String unitSeriesName, Pageable pageable){
        Specification spec1 = UnitSpecs.dealerIdContains(dealerId);
        Specification spec2 = UnitSpecs.unitSeriesNameContains(unitSeriesName);
        Specification spec3 = UnitSpecs.statusIs(unitStatus);
        Specification spec = Specification.where(spec1).and(spec2).and(spec3);
        return ur.findAll(spec, pageable);
    }

    public Page<ViewAllUnit> listViewBy(String dealerId, String unitStatus, String unitSeriesName, Pageable pageable){
        Specification spec1 = ViewSalesSpecs.dealerIdContains(dealerId);
        Specification spec2 = UnitSpecs.unitSeriesNameContains(unitSeriesName);
        Specification spec3 = UnitSpecs.statusIs(unitStatus);
        Specification spec = Specification.where(spec1).and(spec2).and(spec3);
        return vur.findAll(spec, pageable);
    }

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

    public Optional<Unit> findByUnitId(String unitId) {
        return ur.findByUnitId(unitId);
    }
    public List<Unit> test(String unitId, String dealerId){
        unitId = "%" + unitId + "%";
        if(dealerId.isEmpty()){
            dealerId = "%'\'\'%";
        }else{
            dealerId = "%" + dealerId + "%";
        }
        return ur.testingParam(unitId, dealerId);
    }
}
