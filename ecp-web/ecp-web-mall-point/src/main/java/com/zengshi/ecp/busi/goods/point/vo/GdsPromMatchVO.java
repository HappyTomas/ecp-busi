package com.zengshi.ecp.busi.goods.point.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsPromMatchVO extends BaseInfo implements Serializable{
    /** 
     * serialVersionUID:TODO(重新封装促销那边获取的数据). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -5760486412637892804L;
    
    private PromInfoDTO promInfoDTO;
    
    private List<GdsPromMatchSkuVO> gdsPromMatchSkuVOList;

    public PromInfoDTO getPromInfoDTO() {
        return promInfoDTO;
    }

    public void setPromInfoDTO(PromInfoDTO promInfoDTO) {
        this.promInfoDTO = promInfoDTO;
    }

    public List<GdsPromMatchSkuVO> getGdsPromMatchSkuVOList() {
        return gdsPromMatchSkuVOList;
    }

    public void setGdsPromMatchSkuVOList(List<GdsPromMatchSkuVO> gdsPromMatchSkuVOList) {
        this.gdsPromMatchSkuVOList = gdsPromMatchSkuVOList;
    }
    
}

