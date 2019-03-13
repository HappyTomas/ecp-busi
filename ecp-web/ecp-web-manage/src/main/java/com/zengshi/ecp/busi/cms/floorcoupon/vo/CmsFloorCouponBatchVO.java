package com.zengshi.ecp.busi.cms.floorcoupon.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;

public class CmsFloorCouponBatchVO extends EcpBasePageReqVO implements Serializable {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 5314940248225545122L;
    
    private List<CmsFloorCouponReqDTO> floorCoupList;

    public List<CmsFloorCouponReqDTO> getFloorCoupList() {
        return floorCoupList;
    }

    public void setFloorCoupList(List<CmsFloorCouponReqDTO> floorCoupList) {
        this.floorCoupList = floorCoupList;
    }
    
    
}