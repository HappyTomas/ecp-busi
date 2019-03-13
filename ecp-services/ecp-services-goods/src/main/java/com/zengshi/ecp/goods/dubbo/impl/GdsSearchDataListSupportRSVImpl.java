package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.general.solr.dto.GdsParamDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.alibaba.fastjson.JSONObject;

public class GdsSearchDataListSupportRSVImpl implements ISearchDataSupport {

    @Resource
    IGdsInfoQuerySV gdsInfoQuerySV;

    @Override
    public SearchDataPageRespDTO getDataPage(SearchDataPageReqDTO searchDataPageReqDTO) {
        SearchDataPageRespDTO dataPageRespDTO = new SearchDataPageRespDTO();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setPageNo(searchDataPageReqDTO.getPageNo());
        gdsInfoReqDTO.setPageSize(searchDataPageReqDTO.getPageSize());
        gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        gdsInfoReqDTO.setTableIndex(searchDataPageReqDTO.getDbIndex());

        List<GdsParamDTO> arr = JSONObject.parseArray(searchDataPageReqDTO.getJsonParams(),
                GdsParamDTO.class);
        if (CollectionUtils.isNotEmpty(arr)) {
            List<Long> catalogIds = new ArrayList<Long>();
            for (GdsParamDTO searchQueryDTO : arr) {
                catalogIds.add(searchQueryDTO.getCatalogId());
            }
            gdsInfoReqDTO.setCatalogIds(catalogIds);
        }
        PageResponseDTO<GdsInfoRespDTO> gdsInfos = gdsInfoQuerySV
                .queryGdsInfoListPageALLDB(gdsInfoReqDTO);

        if (gdsInfos == null || CollectionUtils.isEmpty(gdsInfos.getResult())) {
            dataPageRespDTO.setPagerOver(true);
        }
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());
        if (dataPageRespDTO.isPagerOver() && searchDataPageReqDTO.getDbIndex() == cnt) {
            dataPageRespDTO.setDbIndexOver(true);
        }
        
        List<Map<String, Object>> maps;
        if(gdsInfos !=null){
            maps = GdsUtils.convertListToMapList(gdsInfos.getResult(),GdsInfoRespDTO.class);
        }else{
            maps = new ArrayList<Map<String, Object>>();
        }
        dataPageRespDTO.setDataList(maps);
        return dataPageRespDTO;
    }

    @Override
    public SearchDataRowRespDTO getDataRow(SearchDataRowReqDTO arg0) {
        SearchDataRowRespDTO dataRowRespDTO = new SearchDataRowRespDTO();
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(Long.parseLong(arg0.getId()));
        GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQuerySV.queryGdsInfoByOption(gdsInfoReqDTO);
        if(gdsInfoRespDTO!=null && gdsInfoRespDTO.getId()!=null){
            dataRowRespDTO.setData(GdsUtils.ConvertObjToMap(gdsInfoRespDTO, GdsInfoRespDTO.class));
        }
        return dataRowRespDTO;
    }

}
