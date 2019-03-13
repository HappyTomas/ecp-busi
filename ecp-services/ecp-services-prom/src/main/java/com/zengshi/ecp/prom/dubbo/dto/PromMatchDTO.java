package com.zengshi.ecp.prom.dubbo.dto;


import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class PromMatchDTO extends BaseInfo {

    private static final long serialVersionUID = 1L;

    private PromInfoDTO promInfoDTO;
    
    private List<PromMatchSkuDTO> promMatchSkuDTOList;

    public List<PromMatchSkuDTO> getPromMatchSkuDTOList() {
        return promMatchSkuDTOList;
    }

    public void setPromMatchSkuDTOList(List<PromMatchSkuDTO> promMatchSkuDTOList) {
        this.promMatchSkuDTOList = promMatchSkuDTOList;
    }

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }
}