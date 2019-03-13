/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseSysCfgRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2015-8-18 14:13:32 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.BaseSysCfgReqDTO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseSysCfgRSV;
import com.zengshi.ecp.sys.dao.model.BaseSysCfg;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-18 4:13:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseSysCfgRSVImpl implements IBaseSysCfgRSV {
    
    private static final String MODULE = BaseSysCfgRSVImpl.class.getName();
    
    @Resource
    private IBaseSysCfgSV baseSysCfgSV;

    /** 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseSysCfgRSV#saveCfg(com.zengshi.ecp.sys.dubbo.dto.BaseSysCfgReqDTO) 
     */
    @Override
    public void saveCfg(BaseSysCfgReqDTO sysCfgDto) throws BusinessException {
        if(sysCfgDto == null || StringUtil.isEmpty(sysCfgDto.getParaCode())){
            LogUtil.error(MODULE, "保存系统配置信息，入参为空" );
            throw new BusinessException(EcpSysCodeConstants.SYS_INVALID_PARAM_NULL);
        }
        
        BaseSysCfg sysCfg = new BaseSysCfg();
        ObjectCopyUtil.copyObjValue(sysCfgDto, sysCfg, null, true);
        sysCfg.setCreateStaff(sysCfgDto.getStaff().getId());
        
        this.baseSysCfgSV.saveCfg(sysCfg);
    }

    @Override
    public BaseSysCfgRespDTO queryCfgByCode(String paramCode) throws BusinessException {
        if(StringUtil.isEmpty(paramCode)){
            LogUtil.error(MODULE, "查询系统参数信息，入参为空" );
            throw new BusinessException(EcpSysCodeConstants.SYS_INVALID_PARAM_NULL);
        }
        
        BaseSysCfg sysCfg = this.baseSysCfgSV.querySysCfgByCode(paramCode);
        if(sysCfg ==null){
            return null;
        } else {
            BaseSysCfgRespDTO dto = new BaseSysCfgRespDTO();
            ObjectCopyUtil.copyObjValue(sysCfg, dto, null, true);
            return dto;
        }
        
    }
    
    

}

