package com.zengshi.ecp.busi.seller.prom.myprom.vo;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-5-3 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromCountVO extends EcpBaseResponseVO{


    private static final long serialVersionUID = 1L;
    
    private Long promCount;

    public Long getPromCount() {
        return promCount;
    }

    public void setPromCount(Long promCount) {
        this.promCount = promCount;
    }
    
}
