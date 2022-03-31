package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.ViewAllSales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViewSalesRepository extends PagingAndSortingRepository<ViewAllSales, String> {
    Page<ViewAllSales> findAll(Specification<ViewAllSales> spec, Pageable pageable);
}
