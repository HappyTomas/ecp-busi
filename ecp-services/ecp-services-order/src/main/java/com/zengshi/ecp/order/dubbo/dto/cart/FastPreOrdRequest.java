package com.zengshi.ecp.order.dubbo.dto.cart;

import com.zengshi.ecp.server.front.dto.BaseInfo;

import java.util.List;

/**
 * Created by wang on 16/5/13.
 */
public class FastPreOrdRequest extends BaseInfo {
    /**
     * serialVersionUID:.
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 7901536631132019766L;

    //买家id
    private Long staffId;

    //来源
    private String source;

    private List<FastOrdRequest> carList;


    public Long getStaffId() {
        return staffId;
    }


    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public List<FastOrdRequest> getCarList() {
        return carList;
    }


    public void setCarList(List<FastOrdRequest> carList) {
        this.carList = carList;
    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) {
        this.source = source;
    }
}
