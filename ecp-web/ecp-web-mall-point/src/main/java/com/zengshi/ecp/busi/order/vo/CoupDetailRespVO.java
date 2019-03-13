package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;

/**
 * Created by wang on 15/12/22.
 */
public class CoupDetailRespVO implements Serializable{
    private static final long serialVersionUID = -3254883795744591425L;
    private Long id;
    private String coupNo;
    private Long coupValue;
    private String coupName;
    private Long coupId;
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoupNo() {
        return coupNo;
    }

    public void setCoupNo(String coupNo) {
        this.coupNo = coupNo;
    }

    public Long getCoupValue() {
        return coupValue;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public void setCoupValue(Long coupValue) {
        this.coupValue = coupValue;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }
}
