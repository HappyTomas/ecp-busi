package com.zengshi.ecp.busi.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang on 15/12/22.
 */
public class CoupCheckBeanRespVO implements Serializable{

    private static final long serialVersionUID = -4556058968048737791L;
    private Long coupId;
    private List<CoupDetailRespVO> coupDetails;
    private int coupSize;
    private String coupName;
    private String noExpress;
    private String varLimit = "0";
    private List<Long> coupVarBeans;
    private String checked;

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<Long> getCoupVarBeans() {
        return coupVarBeans;
    }

    public void setCoupVarBeans(List<Long> coupVarBeans) {
        this.coupVarBeans = coupVarBeans;
    }

    public String getVarLimit() {
        return varLimit;
    }

    public void setVarLimit(String varLimit) {
        this.varLimit = varLimit;
    }

    public Long getCoupId() {
        return coupId;
    }

    public void setCoupId(Long coupId) {
        this.coupId = coupId;
    }

    public List<CoupDetailRespVO> getCoupDetails() {
        return coupDetails;
    }

    public void setCoupDetails(List<CoupDetailRespVO> coupDetails) {
        this.coupDetails = coupDetails;
    }

    public int getCoupSize() {
        return coupSize;
    }

    public void setCoupSize(int coupSize) {
        this.coupSize = coupSize;
    }

    public String getCoupName() {
        return coupName;
    }

    public void setCoupName(String coupName) {
        this.coupName = coupName;
    }

    public String getNoExpress() {
        return noExpress;
    }

    public void setNoExpress(String noExpress) {
        this.noExpress = noExpress;
    }
}
