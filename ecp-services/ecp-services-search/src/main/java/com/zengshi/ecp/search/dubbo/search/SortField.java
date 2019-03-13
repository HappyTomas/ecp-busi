package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.search.util.ESort;

public class SortField extends Field<ESort>{

    private static final long serialVersionUID = 1L;
    
    public SortField(String name,ESort sort){
        super(name, sort);
    }

}

