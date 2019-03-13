package com.zengshi.ecp.aip.third.dubbo.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IUserPermitRSV {

    /**
     * 
     * createUserPerimit:获得当前店铺的消息授权 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String createUserPerimit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
    
	// 获得第三方平台 允许调用消息列表
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
 
    // 取消用户授权
    public String cancelUserPerimit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
}

