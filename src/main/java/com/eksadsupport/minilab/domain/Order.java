package com.eksadsupport.minilab.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "trx_order")
public class Order {

    @Id
    @Size(max = 50)
    private String orderId;
}
