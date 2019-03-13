package com.zengshi.ecp.search.dubbo.search;

import java.util.List;

public class ExtraORFieldQueryGroup {
    
    /**
     * 额外查询字段,OR。不受索引配置中的默认搜索字段约束
     */
    private List<QueryField> extraORFieldQueryList;
    
    public ExtraORFieldQueryGroup(List<QueryField> extraORFieldQueryList){
        this.extraORFieldQueryList=extraORFieldQueryList;
    }

    public List<QueryField> getExtraORFieldQueryList() {
        return extraORFieldQueryList;
    }

    public void setExtraORFieldQueryList(List<QueryField> extraORFieldQueryList) {
        this.extraORFieldQueryList = extraORFieldQueryList;
    }

}

