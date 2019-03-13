package com.zengshi.ecp.busi.search;

import com.zengshi.ecp.search.dubbo.search.SortField;

import java.util.List;

/**
 * 默认排序规则接口
 * Created by HDF on 2016/8/18.
 */
public interface ISortStrategy {

    /**
     * 评分前的排序字段
     * @return
     */
    List<SortField> preScoreSort();

    /**
     * 评分后的排序字段
     * @return
     */
    List<SortField> afterScoreSort();
}
