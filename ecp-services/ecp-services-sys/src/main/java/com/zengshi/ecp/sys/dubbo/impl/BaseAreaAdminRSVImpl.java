/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseAreaAdminRSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.impl 
 * Date:2015-8-24下午9:41:44 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminReqDTO;
import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.param.IBaseAreaAdminRSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseAreaAdminSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-24下午9:41:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class BaseAreaAdminRSVImpl implements IBaseAreaAdminRSV {
    
    private static final String MODULE = BaseAreaAdminRSVImpl.class.getName();
    
    @Resource
    private IBaseAreaAdminSV baseAreaAdminSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseAreaAdminRSV#fetchChildAreaAdmin(com.zengshi.ecp.sys.dubbo.dto.BaseAreaAdminReqDTO) 
     */
    @Override
    public List<BaseAreaAdminRespDTO> fetchChildAreaAdmin(BaseAreaAdminReqDTO dto) {
        if(dto == null || StringUtil.isEmpty(dto.getParenAreaCode())){
            LogUtil.info(MODULE, "根据编码获取子区域信息，入参为空");
            return null;
        }
        
        return this.baseAreaAdminSV.fetchChildAreaAdminInfoList(dto.getParenAreaCode());
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.dubbo.interfaces.IBaseAreaAdminRSV#fetchAreaAdmin(com.zengshi.ecp.sys.dubbo.dto.BaseAreaAdminReqDTO) 
     */
    @Override
    public BaseAreaAdminRespDTO fetchAreaAdmin(BaseAreaAdminReqDTO dto) {
        if(dto == null || StringUtil.isEmpty(dto.getAreaCode())){
            LogUtil.info(MODULE, "根据编码获取区域信息，入参为空");
            return null;
        }
        
        return this.baseAreaAdminSV.fetchAreaAdminInfoByCode(dto.getAreaCode());
    }

}

