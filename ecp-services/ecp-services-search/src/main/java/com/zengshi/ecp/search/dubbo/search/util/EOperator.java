package com.zengshi.ecp.search.dubbo.search.util;

public enum EOperator {

    AND("AND"), OR("OR"),NOT("NOT");

    private final String name;

    public String getName() {
        return this.name;
    }

    private EOperator(String name) {
        this.name = name;
    }

}
