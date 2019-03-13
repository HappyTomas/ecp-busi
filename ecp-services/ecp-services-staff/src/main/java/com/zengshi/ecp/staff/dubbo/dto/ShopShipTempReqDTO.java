package com.zengshi.ecp.staff.dubbo.dto;


import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年10月12日下午7:52:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 店铺物流模版信息
 */
public class ShopShipTempReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String shipTemplateName;
    
    private String ifFree;
    
    private String shipTemplateType;//计价方式
    
    private String defaultFreeParam;//默认运费的配置参数
    
    private List<ShopShipTempPricesReqDTO> shiptempPriceReqDTOList;

    public ShopShipTempReqDTO()
    {
        shiptempPriceReqDTOList = new ArrayList<ShopShipTempPricesReqDTO>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipTemplateName() {
        return shipTemplateName;
    }

    public void setShipTemplateName(String shipTemplateName) {
        this.shipTemplateName = shipTemplateName;
    }

    public String getIfFree() {
        return ifFree;
    }

    public void setIfFree(String ifFree) {
        this.ifFree = ifFree;
    }

    public String getShipTemplateType() {
        return shipTemplateType;
    }

    public void setShipTemplateType(String shipTemplateType) {
        this.shipTemplateType = shipTemplateType;
    }

    public String getDefaultFreeParam() {
        return defaultFreeParam;
    }

    public void setDefaultFreeParam(String defaultFreeParam) {
        this.defaultFreeParam = defaultFreeParam;
    }

    public List<ShopShipTempPricesReqDTO> getShiptempPriceReqDTOList() {
        return shiptempPriceReqDTOList;
    }

    public void setShiptempPriceReqDTOList(List<ShopShipTempPricesReqDTO> shiptempPriceReqDTOList) {
        this.shiptempPriceReqDTOList = shiptempPriceReqDTOList;
    }
    
    
}

