package com.zengshi.ecp.goods.dubbo.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
 * 
 * Title: 商品目录工具类. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-25下午3:39:10  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
@Service("gdsCatalogUtil")
public class GdsCatalogUtil {
	
	private static final String MODULE = GdsCatalogUtil.class.getName();
	private static IGdsCatalogRSV gdsCatalogRSV;
	
	
	@Resource(name = "gdsCatalogRSV")
    public void setGdsCatalogRSV(IGdsCatalogRSV gdsCatalogRSV) {
        GdsCatalogUtil.gdsCatalogRSV = gdsCatalogRSV;
    }
	
	public static List<GdsCatalogRespDTO> loadCatalogFromDB(){
		GdsCatalogReqDTO reqDTO = new GdsCatalogReqDTO();
    	reqDTO.setPageSize(Integer.MAX_VALUE);
    	//reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
    	PageResponseDTO<GdsCatalogRespDTO> page = gdsCatalogRSV.queryGdsCatalogRespDTOPaging(reqDTO);
    	return page.getResult();
	}
	

}

