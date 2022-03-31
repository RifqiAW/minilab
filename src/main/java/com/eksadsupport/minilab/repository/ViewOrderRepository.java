package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.ViewAllOrder;
import com.eksadsupport.minilab.domain.ViewAllSales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViewOrderRepository extends PagingAndSortingRepository<ViewAllOrder, String> {
    Page<ViewAllOrder> findAll(Specification<ViewAllOrder> spec, Pageable pageable);
}