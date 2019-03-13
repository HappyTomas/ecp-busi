package com.zengshi.ecp.busi.search.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年9月14日下午5:05:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version @param <T>
 * @since JDK 1.6
 */
public class CategoryClickAjaxRespVO<T> extends EcpBasePageRespVO<T> implements Serializable {

    /**
     * serialVersionUID:
     */
    private static final long serialVersionUID = -2004460624500515441L;

    private List<GdsPropRespDTO> propList;

    public List<GdsPropRespDTO> getPropList() {
        return propList;
    }

    public void setPropList(List<GdsPropRespDTO> propList) {
        this.propList = propList;
    }

}
