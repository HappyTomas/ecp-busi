/** 
 * Project Name:ecp-services-goods-server 
 * File Name:Gds001RespDTO.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.app 
 * Date:2016年10月22日上午9:19:24 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.app;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: APP应用扩展字段请求<br>
 * Date:2016年10月22日上午9:19:24  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class AppExpandReqDTO extends BaseResponseDTO{

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 4320668765831941996L;
    
    
    private GdsSkuInfoRespDTO skuInfo;


    public GdsSkuInfoRespDTO getSkuInfo() {
        return skuInfo;
    }
    

    public AppExpandReqDTO(GdsSkuInfoRespDTO skuInfo) {
        super();
        this.skuInfo = skuInfo;
    }




    public void setSkuInfo(GdsSkuInfoRespDTO skuInfo) {
        this.skuInfo = skuInfo;
    }
    
    
}

