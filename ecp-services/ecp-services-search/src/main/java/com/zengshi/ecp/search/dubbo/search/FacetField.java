package com.zengshi.ecp.search.dubbo.search;

import java.io.Serializable;

@SuppressWarnings("rawtypes")
public class FacetField<T> implements Serializable, Comparable<FacetField> {

    private static final long serialVersionUID = 1L;

    private String name;
    
    private String nameCn;

    private T start;

    private T end;
    
    public FacetField(){}
    
    public FacetField(String name,T start,T end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public T getStart() {
        return start;
    }

    public void setStart(T start) {
        this.start = start;
    }

    public T getEnd() {
        return end;
    }

    public void setEnd(T end) {
        this.end = end;
    }

    @Override
    public int compareTo(FacetField o) {
        return this.name.compareTo(o.name);
    }

}
