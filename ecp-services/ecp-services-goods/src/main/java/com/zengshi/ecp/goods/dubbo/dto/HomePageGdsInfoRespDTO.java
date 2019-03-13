package com.zengshi.ecp.goods.dubbo.dto;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class HomePageGdsInfoRespDTO extends BaseResponseDTO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5995552351653371000L;

    private GdsInfoRespDTO  gdsInfoRespDTO;
    
    private GdsSkuInfoRespDTO gdsSkuInfoRespDTO;
    
    private String url;

    public GdsInfoRespDTO getGdsInfoRespDTO() {
        return gdsInfoRespDTO;
    }

    public void setGdsInfoRespDTO(GdsInfoRespDTO gdsInfoRespDTO) {
        this.gdsInfoRespDTO = gdsInfoRespDTO;
    }

    public GdsSkuInfoRespDTO getGdsSkuInfoRespDTO() {
        return gdsSkuInfoRespDTO;
    }

    public void setGdsSkuInfoRespDTO(GdsSkuInfoRespDTO gdsSkuInfoRespDTO) {
        this.gdsSkuInfoRespDTO = gdsSkuInfoRespDTO;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

