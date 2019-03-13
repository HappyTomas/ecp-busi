/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseExpressRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2015-9-2上午10:21:55 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dao.model.BaseExpress;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseExpressSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-9-2上午10:21:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseExpressRSVImpl implements IBaseExpressRSV {
    
    @Resource
    private IBaseExpressSV baseExpressSV;

    /** 
     * TODO 根据Id，获取物流信息；
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV#fetchExpressInfo(com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO) 
     */
    @Override
    public BaseExpressRespDTO fetchExpressInfo(BaseExpressReqDTO reqDto) throws BusinessException{
        if(reqDto == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_FETCH_PARAM_NULL);
        }
        
        BaseExpress express = this.baseExpressSV.queryExpressById(reqDto.getId());
        
        BaseExpressRespDTO respDto = new BaseExpressRespDTO();
        ObjectCopyUtil.copyObjValue(express, respDto, null, true);
        return respDto;
    }

    @Override
    public List<BaseExpressRespDTO> fetchActiveExpressInfo(BaseExpressReqDTO reqDto)
            throws BusinessException {
        List<BaseExpressRespDTO> respDtos = new ArrayList<BaseExpressRespDTO>();
        ///获取有效配置的物流信息；
        List<BaseExpress> expressList = this.baseExpressSV.listActiveExpress();
        if(expressList == null || expressList.isEmpty()){
            return respDtos;
        } else {
            ///循环复制；加入；
            for(BaseExpress express : expressList){
                BaseExpressRespDTO respDto = new BaseExpressRespDTO();
                ObjectCopyUtil.copyObjValue(express, respDto, null, true);
                respDtos.add(respDto);
            }
        }
        return respDtos;
    }

    @Override
    public List<BaseExpressRespDTO> fetchAllExpressInfo(BaseExpressReqDTO reqDto)  throws BusinessException {
        
        List<BaseExpressRespDTO> respDtos = new ArrayList<BaseExpressRespDTO>();
        
        List<BaseExpress> expressList = this.baseExpressSV.listAllExpress();
        if(expressList == null || expressList.isEmpty()){
            return respDtos;
        } else {
            ///循环复制；加入；
            for(BaseExpress express : expressList){
                BaseExpressRespDTO respDto = new BaseExpressRespDTO();
                ObjectCopyUtil.copyObjValue(express, respDto, null, true);
                respDtos.add(respDto);
            }
        }
        return respDtos;
    }

    @Override
    public PageResponseDTO<BaseExpressRespDTO> fetchExpressInfoByPage(BaseExpressReqDTO reqDto)
            throws BusinessException {
        if(reqDto == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_FETCH_PARAM_NULL);
        }
        
        return this.baseExpressSV.listExpressByPage(reqDto);
    }

    @Override
    public long createExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null || StringUtil.isEmpty(reqDto.getExpressFullName()) || StringUtil.isEmpty(reqDto.getExpressName())){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        return this.baseExpressSV.createExpress(reqDto);
    }

    @Override
    public long updateExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null || StringUtil.isEmpty(reqDto.getExpressFullName()) || StringUtil.isEmpty(reqDto.getExpressName())){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        return this.baseExpressSV.updateExpress(reqDto);
    }

    @Override
    public long removeExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null ){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        return this.baseExpressSV.removeExpress(reqDto);
    }

    @Override
    public long activeExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null ){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        return this.baseExpressSV.registExpress(reqDto);
    }
    
    

}

