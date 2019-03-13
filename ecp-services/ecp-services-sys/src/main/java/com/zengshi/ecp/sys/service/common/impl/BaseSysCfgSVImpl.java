/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseSysCfgSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-8-18 12:34:09 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.common.BaseSysCfgMapper;
import com.zengshi.ecp.sys.dao.model.BaseSysCfg;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-18 12:34:09  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseSysCfgSV")
public class BaseSysCfgSVImpl extends GeneralSQLSVImpl implements IBaseSysCfgSV {
    
    private static final String MODULE = BaseSysCfgSVImpl.class.getName();
    
    @Resource
    private BaseSysCfgMapper baseSysCfgMapper;

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV#saveCfg(com.zengshi.ecp.sys.dubbo.dto.BaseSysCfgReqDTO) 
     */
    @Override
    public void saveCfg(BaseSysCfg sysCfg) throws BusinessException {
        
        if(sysCfg == null || StringUtil.isEmpty(sysCfg.getParaCode()) ){
            LogUtil.error(MODULE, "保存系统参数信息，入参为空" );
            throw new BusinessException(EcpSysCodeConstants.SYS_INVALID_PARAM_NULL);
        }
        
        if(sysCfg.getCreateTime() == null){
            sysCfg.setCreateTime(DateUtil.getSysDate());
        }

        baseSysCfgMapper.insert(sysCfg);
    }

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV#listCfg() 
     */
    @Override
    public List<BaseSysCfg> listCfg() throws BusinessException {
        
        List<BaseSysCfg> sysCfgs = baseSysCfgMapper.selectByExample(null);
        
        return sysCfgs;
    }

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV#updateCfg(com.zengshi.ecp.sys.dubbo.dto.BaseSysCfgRespDTO) 
     */
    @Override
    public void updateCfg(BaseSysCfg sysCfg) throws BusinessException {
        
        if(sysCfg == null || StringUtil.isEmpty(sysCfg.getParaCode()) ){
            LogUtil.error(MODULE, "更新系统参数信息，入参为空" );
            throw new BusinessException(EcpSysCodeConstants.SYS_INVALID_PARAM_NULL);
        }
        
        //更新系统参数
        /////先根据系统参数编码，查询系统参数信息，如果为空，则调用写入方法；否则，则修改；
        BaseSysCfg dbSysCfg = this.querySysCfgByCode(sysCfg.getParaCode());
        if(dbSysCfg == null){
            this.saveCfg(sysCfg);
        } else {
            dbSysCfg.setParaDesc(sysCfg.getParaDesc());
            dbSysCfg.setParaValue(sysCfg.getParaValue());
            dbSysCfg.setUpdateStaff(sysCfg.getUpdateStaff());
            dbSysCfg.setUpdateTime(DateUtil.getSysDate());
            ///更新系统参数；
            this.baseSysCfgMapper.updateByPrimaryKey(dbSysCfg);
        }
    }

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV#querySysCfgByCode(java.lang.String) 
     */
    @Override
    public BaseSysCfg querySysCfgByCode(String paraCode) throws BusinessException {
        if(StringUtil.isEmpty(paraCode) ){
            LogUtil.error(MODULE, "根据系统编码获取系统配置信息入参为空" );
            throw new BusinessException(EcpSysCodeConstants.SYS_INVALID_PARAM_NULL);
        }
        
        return this.baseSysCfgMapper.selectByPrimaryKey(paraCode);
    }

}

