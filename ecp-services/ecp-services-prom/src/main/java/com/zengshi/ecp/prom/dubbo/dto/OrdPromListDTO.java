package com.zengshi.ecp.prom.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseInfo;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-11 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class OrdPromListDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;
    
    private List<OrdPromDTO> ordPromDTOList;

    public List<OrdPromDTO> getOrdPromDTOList() {
        return ordPromDTOList;
    }

    public void setOrdPromDTOList(List<OrdPromDTO> ordPromDTOList) {
        this.ordPromDTOList = ordPromDTOList;
    }
}
