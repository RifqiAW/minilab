package com.eksadsupport.minilab.dto.order;

import java.util.List;

public class GetListOrder {
    private List<GetOrder> listOrder;
    private long dataOfRecord;

    public List<GetOrder> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<GetOrder> listOrder) {
        this.listOrder = listOrder;
    }

    public long getDataOfRecord() {
        return dataOfRecord;
    }

    public void setDataOfRecord(long dataOfRecord) {
        this.dataOfRecord = dataOfRecord;
    }
}
