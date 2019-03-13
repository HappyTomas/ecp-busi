package com.zengshi.ecp.search.dubbo.search;

import java.util.Date;

public class DateFacetField extends FacetField<Date> {

    private static final long serialVersionUID = 1L;

    private String gap;
    
    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }

}
