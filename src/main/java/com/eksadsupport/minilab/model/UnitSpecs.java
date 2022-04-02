package com.eksadsupport.minilab.model;

import com.eksadsupport.minilab.domain.Unit;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class UnitSpecs {
    public static Specification<Unit> unitSeriesNameContains(String unitSeriesName){
        return ((root, query, criteriaBuilder) ->
                unitSeriesName.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("unitSeriesName")), "%"+unitSeriesName.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<Unit> dealerIdContains(String dealerId){
        return ((root, query, criteriaBuilder) ->
                dealerId.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("dealer").get("dealerId")), "%"+dealerId.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<Unit> statusIs(String status){
        return ((root, query, criteriaBuilder) ->
                status.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("unitStatus")), "%"+status.toUpperCase(Locale.ROOT)+"%"));
    }
}
