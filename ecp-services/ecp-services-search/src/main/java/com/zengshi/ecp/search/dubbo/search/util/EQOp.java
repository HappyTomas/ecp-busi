package com.zengshi.ecp.search.dubbo.search.util;

public enum EQOp {

    AND("AND"), OR("OR");

    private final String name;

    public String getName() {
        return this.name;
    }

    private EQOp(String name) {
        this.name = name;
    }

}
