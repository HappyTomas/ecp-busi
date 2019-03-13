package com.zengshi.ecp.busi.search;

import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.util.ESort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HDF on 2016/8/18.
 */
public class DefaultSortStrategy implements ISortStrategy{
    @Override
    public List<SortField> preScoreSort() {
        return null;
    }

    @Override
    public List<SortField> afterScoreSort() {
        return null;
    }
}
