package com.zengshi.ecp.general.solr.interfaces;

import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowRespDTO;

public interface ISearchDataSupport {
    
    /**
     * 查询分页返回索引数据
     * @param searchDataPageReqDTO
     * @return
     */
    public SearchDataPageRespDTO getDataPage(SearchDataPageReqDTO searchDataPageReqDTO);
    
    /**
     * 查询返回单条索引数据
     * @param searchDataRowReqDTO
     * @return
     */
    public SearchDataRowRespDTO getDataRow(SearchDataRowReqDTO searchDataRowReqDTO);
    
}

