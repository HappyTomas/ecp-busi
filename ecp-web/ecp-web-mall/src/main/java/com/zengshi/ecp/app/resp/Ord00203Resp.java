package com.zengshi.ecp.app.resp;

/**
 * Created by wang on 16/3/14.
 */
public class Ord00203Resp {
    /** 
     * id:促销ID. 
     * @since JDK 1.6 
     */ 
    private Long id;
    /** 
     * nameShort:促销简称. 
     * @since JDK 1.6 
     */ 
    private String nameShort;
    /** 
     * promTheme:促销名称. 
     * @since JDK 1.6 
     */ 
    private String promTheme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getPromTheme() {
        return promTheme;
    }

    public void setPromTheme(String promTheme) {
        this.promTheme = promTheme;
    }
}
