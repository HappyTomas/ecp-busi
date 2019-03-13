package com.zengshi.ecp.aip.third.dubbo.impl;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.ICatgRSV;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;


public class CatgRSVImpl extends AipAbstractRSVImpl implements ICatgRSV {
	
	@Resource
	private ICatgSV defaultCatgSV;
	  /**
     * 
     * fetchCatg:商品分类 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public CatgsRespDTO fetch(CatgReqDTO catgReqDTO)throws BusinessException{
    	if(catgReqDTO==null){
    		throw new BusinessException("AIPTHIRD.100020");
    	}
    	return defaultCatgSV.fetch(catgReqDTO);
    }
    
    /**
     * 
     * fetchCatgAndProp:商品分类包含属性 <br/> 
     * @author huangjx
     * @param s
     * @return 
     * @since JDK 1.7
     */
    public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO)throws BusinessException{
    	if(catgReqDTO==null){
    		throw new BusinessException("AIPTHIRD.100020");
    	}
    	return defaultCatgSV.fetchCatgAndProp(catgReqDTO);
    }
}

