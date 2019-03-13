package com.zengshi.ecp.search.dubbo.search;

import java.util.List;

public class UncertainField extends Field<List<String>>{

    private static final long serialVersionUID = 1L;
    
    public UncertainField(){}

    public UncertainField(String name, List<String> value) {
        super(name, value);
    }
    
}

