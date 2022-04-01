package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.ViewAllUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViewUnitRepository extends PagingAndSortingRepository<ViewAllUnit, String> {
    Page<ViewAllUnit> findAll(Specification<ViewAllUnit> spec, Pageable pageable);
}
