package com.zengshi.ecp.general.solr.interfaces;

import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataDupPagerRowRowRespDTO;

public interface ISearchDataDupPagerSupport{
    
    /**
     * 查询二级分页返回索引数据
     * @param searchDataPageReqDTO
     * @return
     */
    public SearchDataDupPagerPageRespDTO getDataPage(SearchDataDupPagerPageReqDTO searchDataDupPagerPageReqDTO);
    
    /**
     * 查询返回外层数据行的里层分页索引数据
     * @param searchDataRowReqDTO
     * @return
     */
    public SearchDataDupPagerRowPageRespDTO getDataRowPage(SearchDataDupPagerRowPageReqDTO searchDataDupPagerRowPageReqDTO);
    
    /**
     * 查询返回单条索引数据
     * @param searchDataRowReqDTO
     * @return
     */
    public SearchDataDupPagerRowRowRespDTO getDataRow(SearchDataDupPagerRowRowReqDTO searchDataDupPagerRowRowReqDTO);

}

