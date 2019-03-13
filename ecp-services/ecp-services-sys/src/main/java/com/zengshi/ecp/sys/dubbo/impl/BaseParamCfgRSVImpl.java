/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamCfgRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2015-8-20上午9:29:28 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.BaseParamCfgReqDTO;
import com.zengshi.ecp.server.front.dto.BaseParamCfgRespDTO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.param.IBaseParamCfgRSV;
import com.zengshi.ecp.sys.dao.model.BaseParamConfig;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamCfgSV;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-20上午9:29:28  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseParamCfgRSVImpl implements IBaseParamCfgRSV {
    
    private static final String MODULE = BaseParamCfgRSVImpl.class.getName();
    
    @Resource
    private IBaseParamCfgSV baseParamCfgSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseParamCfgRSV#fetchParamsByKey(com.zengshi.ecp.sys.dubbo.dto.BaseParamCfgReqDTO) 
     */
    @Override
    public List<BaseParamDTO> fetchParamsByKey(BaseParamCfgReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || StringUtil.isEmpty(reqDTO.getParamKey())){
            LogUtil.error(MODULE, "根据参数编码获取参数信息列表的入参中，参数编码为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(reqDTO.getSpLang())){
            reqDTO.setSpLang(LocaleUtil.getLocalString());
        }
        
        return this.baseParamCfgSV.fetchParamListByKey(reqDTO.getParamKey(),reqDTO.getSpLang());
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseParamCfgRSV#fetchParamByKeyAndCode(com.zengshi.ecp.sys.dubbo.dto.BaseParamCfgReqDTO) 
     */
    @Override
    public BaseParamDTO fetchParamByKeyAndCode(BaseParamCfgReqDTO reqDTO) throws BusinessException {
        if(reqDTO == null || StringUtil.isEmpty(reqDTO.getParamKey()) || StringUtil.isEmpty(reqDTO.getSpCode())){
            LogUtil.error(MODULE, "根据参数Key、参数编码 获取参数信息的入参中，参数编码为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(reqDTO.getSpLang())){
            reqDTO.setSpLang(LocaleUtil.getLocalString());
        }
        
        return this.baseParamCfgSV.fetchParamByKeyCode(reqDTO.getParamKey(), reqDTO.getSpCode(), reqDTO.getSpLang());
    }

    @Override
    public List<BaseParamCfgRespDTO> fetchActiveParamConfig(BaseParamCfgReqDTO reqDTO)
            throws BusinessException {
        
        List<BaseParamCfgRespDTO> dtos = new ArrayList<BaseParamCfgRespDTO>();
        
        List<BaseParamConfig> params = this.baseParamCfgSV.fetchActiveParamConfig();
        if(params == null || params.size() == 0){
          //为空，不处理；
        } else {
            for(BaseParamConfig param : params){
                BaseParamCfgRespDTO dto = new BaseParamCfgRespDTO();
                ObjectCopyUtil.copyObjValue(param, dto, null, true);
                dtos.add(dto);
            }
        }
        
        return dtos;
    }
    
    

}

