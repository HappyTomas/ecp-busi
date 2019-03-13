package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class RCrePreOrdsReqVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long staffId;
    private String source;
    private List<RCrePreOrdReqVO> carList;
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public List<RCrePreOrdReqVO> getCarList() {
        return carList;
    }
    public void setCarList(List<RCrePreOrdReqVO> carList) {
        this.carList = carList;
    }
    
}

