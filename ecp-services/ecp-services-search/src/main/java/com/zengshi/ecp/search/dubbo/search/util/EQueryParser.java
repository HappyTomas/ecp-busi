package com.zengshi.ecp.search.dubbo.search.util;

public enum EQueryParser {

    DEFTYPE_LUCENE("lucene"), DEFTYPE_DISMAX("dismax"),DEFTYPE_EDISMAX("edismax");

    private final String name;

    public String getName() {
        return this.name;
    }

    private EQueryParser(String name) {
        this.name = name;
    }

}
