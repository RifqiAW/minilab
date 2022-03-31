package com.eksadsupport.minilab.model;

import com.eksadsupport.minilab.domain.ViewAllSales;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class ViewSalesSpecs {
    public static Specification<ViewAllSales> salesNameContains(String salesName){
        return ((root, query, criteriaBuilder) ->
                salesName.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("salesName")), "%"+salesName.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllSales> dealerIdContains(String dealerId){
        return ((root, query, criteriaBuilder) ->
                dealerId.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("dealerCode")), "%"+dealerId.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllSales> statusIs(String status){
        return ((root, query, criteriaBuilder) ->
                status.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("salesStatus")), "%"+status.toUpperCase(Locale.ROOT)+"%"));
    }
}
