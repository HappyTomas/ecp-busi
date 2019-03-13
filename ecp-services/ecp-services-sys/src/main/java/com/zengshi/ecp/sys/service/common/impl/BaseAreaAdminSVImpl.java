/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseAreaAdminSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-8-24下午9:06:50 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.common.BaseAreaAdminMapper;
import com.zengshi.ecp.sys.dao.model.BaseAreaAdmin;
import com.zengshi.ecp.sys.dao.model.BaseAreaAdminCriteria;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseAreaAdminSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-24下午9:06:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseAreaAdminSV")
public class BaseAreaAdminSVImpl extends GeneralSQLSVImpl implements IBaseAreaAdminSV {
    
    private static final String MODULE = BaseAreaAdminSVImpl.class.getName();
    
    @Resource
    private BaseAreaAdminMapper baseAreaAdminMapper;

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseAreaAdminSV#fetchAreaAdminInfoByCode(java.lang.String) 
     */
    @Override
    public BaseAreaAdminRespDTO fetchAreaAdminInfoByCode(String areaCode) {
        
        if(StringUtil.isEmpty(areaCode)){
            LogUtil.info(MODULE, "从数据库中，根据编码："+areaCode+"获取区域信息，入参为空");
            return null;
        }
        
        BaseAreaAdmin info = this.baseAreaAdminMapper.selectByPrimaryKey(areaCode);
        if(info == null){
            LogUtil.info(MODULE, "从数据库中，根据编码："+areaCode+"未获取到匹配的区域信息");
            return null;
        }
        
        BaseAreaAdminRespDTO dto = new BaseAreaAdminRespDTO();
        ObjectCopyUtil.copyObjValue(info, dto, "", true);
        return dto;
        
    }

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseAreaAdminSV#fetchChildAreaAdminInfoList(java.lang.String) 
     */
    @Override
    public List<BaseAreaAdminRespDTO> fetchChildAreaAdminInfoList(String areaCode) {
        if(StringUtil.isEmpty(areaCode)){
            LogUtil.info(MODULE, "从数据库中，根据编码："+areaCode+"获取子区域信息，入参为空");
            return null;
        }
        
        BaseAreaAdminCriteria criteria = new BaseAreaAdminCriteria();
        criteria.createCriteria().andParentAreaCodeEqualTo(areaCode);
        criteria.setOrderByClause(" area_order asc");
        
        List<BaseAreaAdmin> infos = this.baseAreaAdminMapper.selectByExample(criteria);
        
        if(infos == null || infos.size() ==0){
            LogUtil.info(MODULE, "从数据库中，根据编码："+areaCode+"未获取到匹配的子区域信息");
            return null;
        }
        
        List<BaseAreaAdminRespDTO> dtos = new ArrayList<BaseAreaAdminRespDTO>();
        for(BaseAreaAdmin vo : infos){
            BaseAreaAdminRespDTO dto = new BaseAreaAdminRespDTO();
            ObjectCopyUtil.copyObjValue(vo, dto, "", true);
            dtos.add(dto);
        }
        
        return dtos;
    }
    
    

}

