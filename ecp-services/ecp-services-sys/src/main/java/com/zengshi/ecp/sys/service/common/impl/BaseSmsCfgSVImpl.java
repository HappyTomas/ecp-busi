/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseSmsCfgSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2016年3月17日上午11:01:18 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.sys.dao.mapper.common.BaseSmsCfgMapper;
import com.zengshi.ecp.sys.dao.model.BaseSmsCfg;
import com.zengshi.ecp.sys.dao.model.BaseSysCfg;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSmsCfgSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseSysCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月17日上午11:01:18  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseSmsCfgSV")
public class BaseSmsCfgSVImpl implements IBaseSmsCfgSV {
    
    private static final String MODULE = BaseSmsCfgSVImpl.class.getName();
    
    @Autowired
    private BaseSmsCfgMapper baseSmsCfgMapper;
    
    @Autowired
    private IBaseSysCfgSV baseSysCfgSV;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseSmsCfgSV#fetchSmsCfgByGateway(java.lang.String) 
     */
    @Override
    public BaseSmsCfg fetchSmsCfgByGateway(String gateway) {

        return this.baseSmsCfgMapper.selectByPrimaryKey(gateway);
        
    }

    @Override
    public void saveSmsCfg(BaseSmsCfg smsCfg, Long staffId) {
        
        //保存 smsCfg配置；
        BaseSmsCfg oldCfg = this.fetchSmsCfgByGateway(smsCfg.getSmsGateway());
        if(oldCfg == null){
            //不存在，则插入数据；
            this.insertSmsCfg(smsCfg);
        } else {
            //存在，则更新数据；
            this.updateSmsCfg(smsCfg);
        }
        
        //保存系统参数配置；
        BaseSysCfg sysCfg = new BaseSysCfg();
        sysCfg.setParaCode(BaseMsgConstants.SYS_MSG_SMS_CFG_KEY);
        sysCfg.setParaValue(smsCfg.getSmsGateway());
        sysCfg.setUpdateStaff(staffId);
        sysCfg.setUpdateTime(DateUtil.getSysDate());
        sysCfg.setParaDesc("更新短信发送网关信息");
        this.baseSysCfgSV.updateCfg(sysCfg);
        
        //强制刷新到缓存；
        SysCfgUtil.refreshSysCfgByCode(BaseMsgConstants.SYS_MSG_SMS_CFG_KEY);
    }
    
    
    private void insertSmsCfg(BaseSmsCfg smsCfg){
        
        this.baseSmsCfgMapper.insert(smsCfg);
        
    }
    
    
    private void updateSmsCfg(BaseSmsCfg smsCfg){
        this.baseSmsCfgMapper.updateByPrimaryKey(smsCfg);
    }

    @Override
    public BaseSmsCfg fetchDefaultSmsCfg() {
        ///获取系统网关参数；
        String gateway = "";
        ///从缓存中获取系统网关配置；
        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg(BaseMsgConstants.SYS_MSG_SMS_CFG_KEY);
        if(sysCfg == null || StringUtils.isEmpty(sysCfg.getParaValue())){
            LogUtil.error(MODULE, "系统未指定短信网关，无法发送短信");
            throw new BusinessException(EcpSysCodeConstants.SYS_MSG_SEND_SMS_PHONE_NULL);
        } else {
            gateway = sysCfg.getParaValue();
        }
        
        //后续这部分的SmsGatewayBean 建议写入缓存；
        BaseSmsCfg smsCfg = this.fetchSmsCfgByGateway(gateway);
        return smsCfg;
    }
    
    
    

}

