package com.zengshi.ecp.prom.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.PromPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromShipRSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromShipSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromShipRSVImpl implements IPromShipRSV {
    
    @Resource
    private  IPromShipSV promShipSV;
    
    @Resource
    private  IPromSV promSV;
    /**
     * 验证是否 免邮
     * 1 免邮
     * 非1 不免邮 
     * @param promShipDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromShipRespDTO qryPromShip(PromShipDTO promShipDTO) throws BusinessException{
        //参数验证
        if(promShipDTO==null){
            //传入参数为空
            throw new BusinessException("prom.400165");
        }
        if(StringUtil.isEmpty(promShipDTO.getMoney())){
            //传入金额不能为空
            throw new BusinessException("prom.400166");
        }
        if(StringUtil.isEmpty(promShipDTO.getShopId())){
            //传入店铺编码不能为空
            throw new BusinessException("prom.400167");
        }
        return promShipSV.qryPromShip(promShipDTO);
    }
    /**
     * 验证是否 免邮
     * 1 免邮
     * 非1 不免邮 
     * @param promPostDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPostRespDTO checkIfFreePost(PromPostDTO promPostDTO) throws BusinessException{
    	  PromPostRespDTO respDTO=new PromPostRespDTO();
    	  respDTO.setIfFreePost("0");
    	   if(promPostDTO==null){
    		   
    	   }else{
    		   if(promSV.checkFreePost(promPostDTO.getPromIds())){
    			   respDTO.setIfFreePost("1");
    		   }
    	   }
    	   return respDTO;
    }
     
}
