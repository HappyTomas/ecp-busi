package com.zengshi.ecp.aip.third.service.busi.prop.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IPropSV {

    /**
     * 
     * fetchProp:分类属性 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public PropsRespDTO fetch(PropReqDTO propReqDTO)throws BusinessException;
    
    /**
     * 
     * fetchPropValue:商品分类包含属性 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public PropsRespDTO fetchPropValue(PropReqDTO propReqDTO)throws BusinessException;
}

