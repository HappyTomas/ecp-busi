package com.zengshi.ecp.prom.dubbo.dto;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsPromListDTO extends BaseResponseDTO{

    private static final long serialVersionUID = 4457551961459771856L;

    private List<PromListRespDTO> promList;
    private SeckillDiscountDTO seckill;
    public List<PromListRespDTO> getPromList() {
        return promList;
    }
    public void setPromList(List<PromListRespDTO> promList) {
        this.promList = promList;
    }
    public SeckillDiscountDTO getSeckill() {
        if(seckill == null){
            return new SeckillDiscountDTO();
        }
        return seckill;
    }
    public void setSeckill(SeckillDiscountDTO seckill) {
        this.seckill = seckill;
    }
    
}

