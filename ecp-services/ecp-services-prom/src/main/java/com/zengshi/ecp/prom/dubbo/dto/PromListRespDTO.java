package com.zengshi.ecp.prom.dubbo.dto;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-3 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromListRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private PromInfoDTO promInfoDTO;
    
    private PromSkuPriceRespDTO promSkuPriceRespDTO;

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }

    public PromSkuPriceRespDTO getPromSkuPriceRespDTO() {
        return promSkuPriceRespDTO;
    }

    public void setPromSkuPriceRespDTO(PromSkuPriceRespDTO promSkuPriceRespDTO) {
        this.promSkuPriceRespDTO = promSkuPriceRespDTO;
    }
    
}
