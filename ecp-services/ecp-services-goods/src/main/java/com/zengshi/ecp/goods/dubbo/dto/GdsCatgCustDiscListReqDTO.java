package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

@SuppressWarnings("rawtypes")
public class GdsCatgCustDiscListReqDTO extends BaseInfo{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private List<GdsCatgCustDiscReqDTO> catgCustDiscReqDTOs = new ArrayList<GdsCatgCustDiscReqDTO>();

    public List<GdsCatgCustDiscReqDTO> getCatgCustDiscReqDTOs() {
        return catgCustDiscReqDTOs;
    }

    public void setCatgCustDiscReqDTOs(List<GdsCatgCustDiscReqDTO> catgCustDiscReqDTOs) {
        this.catgCustDiscReqDTOs = catgCustDiscReqDTOs;
    } 
}

