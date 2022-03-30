package com.eksadsupport.minilab.repository;

import com.eksadsupport.minilab.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
