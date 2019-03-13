package com.zengshi.ecp.search.dubbo.search.util;

public enum ECompareStr {

    //比较符：包含、不包含；等于、不等于；大于、大于等于；小于、小于等于；
    CONTAIN("contain"),
    NOTCONTAIN("notcontain"),
    EQUAL("equal"),
    NOTEQUAL("notequal"),
    GREATER("greater"),
    GREATERTHAN("greaterthan"),
    LESS("less"),
    LESSTHAN("lessthan");

    private final String compareStr;

    public String getName() {
        return this.compareStr;
    }

    private ECompareStr(String name) {
        this.compareStr = name;
    }

}
