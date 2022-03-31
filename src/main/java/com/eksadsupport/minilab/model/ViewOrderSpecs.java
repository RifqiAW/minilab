package com.eksadsupport.minilab.model;

import com.eksadsupport.minilab.domain.ViewAllOrder;
import com.eksadsupport.minilab.domain.ViewAllSales;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public class ViewOrderSpecs {

    public static Specification<ViewAllOrder> platNomorContains(String platNomor){
        return ((root, query, criteriaBuilder) ->
                platNomor.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("platNomor")), "%"+platNomor.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllOrder> dealerIdContains(String dealerId){
        return ((root, query, criteriaBuilder) ->
                dealerId.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("dealerCode")), "%"+dealerId.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllOrder> nomorMesinContains(String nomorMesin){
        return ((root, query, criteriaBuilder) ->
                nomorMesin.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("nomorMesin")), "%"+nomorMesin.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllOrder> nomorRangkaContains(String nomorRangka){
        return ((root, query, criteriaBuilder) ->
                nomorRangka.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("nomorRangka")), "%"+nomorRangka.toUpperCase(Locale.ROOT)+"%"));
    }

    public static Specification<ViewAllOrder> paymentStatusIs(String paymentStatus){
        return ((root, query, criteriaBuilder) ->
                paymentStatus.isEmpty() ?
                        criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.upper(root.get("paymentStatus")), "%"+paymentStatus.toUpperCase(Locale.ROOT)+"%"));
    }
}
