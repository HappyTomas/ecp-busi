/** 
 * Project Name:ecp-services-goods-server 
 * File Name:Gds001RespDTO.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.dto.app 
 * Date:2016年10月22日上午9:19:24 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.dto.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSkuInfoGdsIdxRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: APP扩展字段响应.<br>
 * Date:2016年10月22日上午9:19:24  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class AppExpandRespDTO extends BaseResponseDTO{

    
    
    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = -795942078989217257L;
    private Map<String,Object> expand;
    
    public Map<String, Object> getExpand() {
        return expand;
    }


    public void setExpand(Map<String, Object> expand) {
        this.expand = expand;
    }
    
    public void putObject(String key, Object obj){
        if(StringUtil.isNotBlank(key) && null != obj){
            if(null == expand){
                expand = new HashMap<>();
            }
            expand.put(key, obj);
        }
    }

}

