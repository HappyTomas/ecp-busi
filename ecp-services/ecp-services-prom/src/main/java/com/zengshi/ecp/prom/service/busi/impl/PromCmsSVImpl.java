package com.zengshi.ecp.prom.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromCmsSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-4-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromCmsSVImpl implements IPromCmsSV {

    private static final String MODULE = PromCmsSVImpl.class.getName();
    
    @Resource 
    private ICmsFloorGdsRSV cmsFloorGdsRSV;

    /**
     * TODO失效cms模块（选择的促销数据）
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromCmsSV#invalidCmsGds(java.lang.Long, java.lang.Long, java.lang.Long, java.util.List)
     * @param promId
     * @param gdsId
     * @param skudId
     * @param gdsIds
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void invalidCmsGds(Long promId,Long gdsId,Long skudId,List<Long> gdsIds) throws BusinessException{
        try{
            CmsFloorGdsReqDTO reqDTO=new CmsFloorGdsReqDTO();
            reqDTO.setGdsId(gdsId);
            reqDTO.setPromId(promId);
            cmsFloorGdsRSV.deleteCmsFloorGdsForProm(reqDTO);
        }catch(BusinessException ex){
          LogUtil.error(MODULE, "删除cms模块 促销页配置的商品发生错误了"+ex.toString());  
        }
        catch(Exception ex){
            LogUtil.error(MODULE, "Exception删除cms模块 促销页配置的商品发生错误了"+ex.toString());  
          }
          
        
    }
 
}
