package com.eksadsupport.minilab.service;

import com.eksadsupport.minilab.domain.*;
import com.eksadsupport.minilab.dto.customer.ListAllDTO;
import com.eksadsupport.minilab.dto.unit.GetUnit;
import com.eksadsupport.minilab.dto.unit.ListAllUnit;
import com.eksadsupport.minilab.model.SalesSpecs;
import com.eksadsupport.minilab.model.UnitSpecs;
import com.eksadsupport.minilab.model.ViewSalesSpecs;
import com.eksadsupport.minilab.repository.UnitRepository;
import com.eksadsupport.minilab.repository.ViewSalesRepository;
import com.eksadsupport.minilab.repository.ViewUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    private UnitRepository ur;

    @Autowired
    ViewUnitRepository vur;

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

//    public List<ListAllUnit> listAll(String dealerId, String unitId, int limit, int offset) {
//        List<ListAllUnit> listAll = new ArrayList<ListAllUnit>();
//        List<Unit> opt = ur.listAll(dealerId, unitId, limit, offset);
//
//        for (int i = 0; i < opt.size(); i++) {
//            ListAllUnit listUnit = new ListAllUnit();
//            listUnit.setUnitId(opt.get(i).getUnitId());
//            listUnit.setUnitSeriesName(opt.get(i).getUnitSeriesName());
//            listUnit.setDealerId(opt.get(i).getDealer().getDealerId());
//            listUnit.setUnitQuantity(opt.get(i).getUnitQuantity());
//            listUnit.setUnitColor(opt.get(i).getUnitColor());
//            listUnit.setUnitStatus(opt.get(i).getUnitStatus());
//            listUnit.setAverageCost(opt.get(i).getAverageCost());
//            listAll.add(listUnit);
//        }
//        return listAll;
//    }

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
        Specification spec = Specification.where(spec1).or(spec2).or(spec3);
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

    public Optional<Unit> findByUnitId(String unitId){
        return ur.findByUnitId(unitId);
    }

}
