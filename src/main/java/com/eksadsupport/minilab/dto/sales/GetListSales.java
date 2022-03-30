package com.eksadsupport.minilab.dto.sales;

import com.eksadsupport.minilab.domain.Sales;

import java.util.List;

public class GetListSales {
    private List<GetSales> listSales;
    private long dataOfRecord;

    public List<GetSales> getListSales() {
        return listSales;
    }

    public void setListSales(List<GetSales> listSales) {
        this.listSales = listSales;
    }

    public long getDataOfRecord() {
        return dataOfRecord;
    }

    public void setDataOfRecord(long dataOfRecord) {
        this.dataOfRecord = dataOfRecord;
    }
}
