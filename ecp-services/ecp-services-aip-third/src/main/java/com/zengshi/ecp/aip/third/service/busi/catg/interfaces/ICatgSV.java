package com.zengshi.ecp.aip.third.service.busi.catg.interfaces;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface ICatgSV {

    /**
     * 
     * fetchCatg:商品分类 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public CatgsRespDTO fetch(CatgReqDTO catgReqDTO)throws BusinessException;
    
    /**
     * 
     * fetchCatgAndProp:商品分类包含属性 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO)throws BusinessException;
}

