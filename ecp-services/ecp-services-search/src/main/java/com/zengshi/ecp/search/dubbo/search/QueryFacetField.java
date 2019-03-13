package com.zengshi.ecp.search.dubbo.search;

import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;

public class QueryFacetField extends FacetField<String> {

    private static final long serialVersionUID = 1L;

    public QueryFacetField() {
        this.setStart(SearchConstants.STAR);
        this.setEnd(SearchConstants.STAR);
    }

}
