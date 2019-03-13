package com.zengshi.ecp.aip.third.service.busi.permit.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IUserPermitSV {

    /**
     * 
     * createPermit:获得当前店铺的允许执行消息权限<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String createPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
    
    /**
     * 
     * userPermitList:获得当前店铺的允许执行消息权限列表<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
    
    /**
     * 
     * cancelPermit:取消授权<br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public String cancelPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException;
    
}

