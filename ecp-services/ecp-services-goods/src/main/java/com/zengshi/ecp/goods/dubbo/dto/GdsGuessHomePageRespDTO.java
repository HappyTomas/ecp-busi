package com.zengshi.ecp.goods.dubbo.dto;

import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

public class GdsGuessHomePageRespDTO extends BaseResponseDTO {
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1L;

    /**
     * 分类列表
     */
    private List<GdsCategoryRespDTO> categoryRespDTOs = new ArrayList<GdsCategoryRespDTO>();

    private List<GdsInfoDetailRespDTO> detailRespDTOs = new ArrayList<GdsInfoDetailRespDTO>();

    public List<GdsCategoryRespDTO> getCategoryRespDTOs() {
        return categoryRespDTOs;
    }

    public void setCategoryRespDTOs(List<GdsCategoryRespDTO> categoryRespDTOs) {
        this.categoryRespDTOs = categoryRespDTOs;
    }

    public List<GdsInfoDetailRespDTO> getDetailRespDTOs() {
        return detailRespDTOs;
    }

    public void setDetailRespDTOs(List<GdsInfoDetailRespDTO> detailRespDTOs) {
        this.detailRespDTOs = detailRespDTOs;
    }
}

