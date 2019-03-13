package com.zengshi.ecp.busi.cms.floorgds.vo;

import java.io.Serializable;
import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;

/**
 *   
 * Title: ECP <br>
 * Project Name:ecp-web-manage-core <br>
 * Description: <br>
 * Date:2016年7月5日下午2:20:53  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public class CmsFloorGdsBatchVO extends EcpBasePageReqVO implements Serializable{
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -1220726397216924080L;
    private List<CmsFloorGdsReqDTO> floorGdsList ;
    public List<CmsFloorGdsReqDTO> getFloorGdsList() {
        return floorGdsList;
    }
    public void setFloorGdsList(List<CmsFloorGdsReqDTO> floorGdsList) {
        this.floorGdsList = floorGdsList;
    }
}

