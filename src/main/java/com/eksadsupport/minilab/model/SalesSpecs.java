package com.eksadsupport.minilab.model;

import com.eksadsupport.minilab.domain.Sales;
import org.springframework.data.jpa.domain.Specification;
import com.eksadsupport.minilab.Common.Util;

import java.util.Locale;

public class SalesSpecs {

    public static Specification<Sales> salesNameContains(String salesName){
        return ((root, query, criteriaBuilder) ->
                salesName.isEmpty() ?
                    criteriaBuilder.conjunction() :
                    criteriaBuilder.like(criteriaBuilder.upper(root.get("salesName")), "%"+salesName.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<Sales> dealerIdContains(String dealerId){
        return ((root, query, criteriaBuilder) ->
                dealerId.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("dealer").get("dealerId")), "%"+dealerId.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<Sales> statusIs(String status){
        return ((root, query, criteriaBuilder) ->
                status.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("salesStatus")), "%"+status.toUpperCase(Locale.ROOT)+"%"));
    }
}
