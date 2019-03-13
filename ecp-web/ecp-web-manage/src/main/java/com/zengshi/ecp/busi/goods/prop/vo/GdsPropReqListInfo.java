package com.zengshi.ecp.busi.goods.prop.vo;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.server.front.dto.BaseInfo;

public class GdsPropReqListInfo extends EcpBasePageReqVO {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = 44216707173661360L;

    private String propName;

    private Long id;

    private String status;

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GdsPropReqListInfo [propName=" + propName + ", id=" + id + ", status=" + status
                + "]";
    }
}
