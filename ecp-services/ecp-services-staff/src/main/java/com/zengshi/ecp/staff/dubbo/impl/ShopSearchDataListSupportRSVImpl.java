package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.general.solr.dto.SearchDataPageReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataPageRespDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowReqDTO;
import com.zengshi.ecp.general.solr.dto.SearchDataRowRespDTO;
import com.zengshi.ecp.general.solr.interfaces.ISearchDataSupport;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 搜索店铺信息<br>
 * Date:2016-4-26上午11:43:48  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ShopSearchDataListSupportRSVImpl implements ISearchDataSupport {

    @Resource
    private IShopMgrSV shopMgrSV;

    @Override
    public SearchDataPageRespDTO getDataPage(SearchDataPageReqDTO searchDataPageReqDTO) {
        SearchDataPageRespDTO dataPageRespDTO = new SearchDataPageRespDTO();
        
        ShopSelectReqDTO shopReq = new ShopSelectReqDTO();
        shopReq.setPageNo(searchDataPageReqDTO.getPageNo());
        shopReq.setPageSize(searchDataPageReqDTO.getPageSize());
        shopReq.setStatus("1");
        shopReq.setTableIndex(searchDataPageReqDTO.getDbIndex());
        shopReq.setShowScoreShop(false);
        PageResponseDTO<ShopInfoResDTO> shopInfos = shopMgrSV.listShop(shopReq, null);

        if (shopInfos == null || CollectionUtils.isEmpty(shopInfos.getResult())) {
            dataPageRespDTO.setPagerOver(true);
        }
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());
        if (dataPageRespDTO.isPagerOver() && searchDataPageReqDTO.getDbIndex() == cnt) {
            dataPageRespDTO.setDbIndexOver(true);
        }
        
        List<Map<String, Object>> maps;
        if(shopInfos != null){
            maps = GdsUtils.convertListToMapList(shopInfos.getResult(),ShopInfoResDTO.class);
        }else{
            maps = new ArrayList<Map<String, Object>>();
        }
        dataPageRespDTO.setDataList(maps);
        return dataPageRespDTO;
    }

    @Override
    public SearchDataRowRespDTO getDataRow(SearchDataRowReqDTO arg0) {
        SearchDataRowRespDTO dataRowRespDTO = new SearchDataRowRespDTO();
    	BaseSysCfgRespDTO cfg=SysCfgUtil.fetchSysCfg("GDS_SCORE_SHOP_DEFAULT");
		if(cfg != null && cfg.getParaValue()!=null){
			try {
                if(arg0.getId().trim().equals(cfg.getParaValue().trim())){
                	return dataRowRespDTO;
                }
			} catch (Exception e) {
				LogUtil.error(this.getClass().getName(), "convert socre shop value to Long failed please check!");
			}
		}
        ShopSelectReqDTO shopReq = new ShopSelectReqDTO();
        List<Long> idList = new ArrayList<Long>();
        idList.add(Long.parseLong(arg0.getId()));
        PageResponseDTO<ShopInfoResDTO> shopInfos = shopMgrSV.listShop(shopReq, idList);
        if(shopInfos != null && CollectionUtils.isNotEmpty(shopInfos.getResult())){
            dataRowRespDTO.setData(GdsUtils.ConvertObjToMap(shopInfos.getResult().get(0), ShopInfoResDTO.class));
        }
        return dataRowRespDTO;
    }

}
