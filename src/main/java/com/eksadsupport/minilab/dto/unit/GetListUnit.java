package com.eksadsupport.minilab.dto.unit;

import java.util.List;

public class GetListUnit {
    private List<GetUnit> listUnit;
    private long dataOfRecord;

    public List<GetUnit> getListUnit() {
        return listUnit;
    }

    public void setListUnit(List<GetUnit> listUnit) {
        this.listUnit = listUnit;
    }

    public long getDataOfRecord() {
        return dataOfRecord;
    }

    public void setDataOfRecord(long dataOfRecord) {
        this.dataOfRecord = dataOfRecord;
    }
}
