package com.zengshi.ecp.prom.dubbo.dto;

import java.util.Map;

import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class OrdPromRespDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private OrdPromDTO ordPromDTO;
    
    private Map<Long,OrdPromDetailDTO> detailMap;

    public OrdPromDTO getOrdPromDTO() {
        return ordPromDTO;
    }

    public void setOrdPromDTO(OrdPromDTO ordPromDTO) {
        this.ordPromDTO = ordPromDTO;
    }

    public Map<Long,OrdPromDetailDTO> getDetailMap() {
        return detailMap;
    }

    public void setDetailMap(Map<Long,OrdPromDetailDTO> detailMap) {
        this.detailMap = detailMap;
    }
}
