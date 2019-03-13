/** 
 * Project Name:ecp-services-demo 
 * File Name:DemoInfoRSVImpl.java 
 * Package Name:com.zengshi.ecp.demo.dubbo.impl 
 * Date:2015-8-3下午3:57:26 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.demo.dubbo.impl;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dubbo.dto.DemoInfoDTO;
import com.zengshi.ecp.demo.dubbo.interfaces.IDemoInfoRSV;
import com.zengshi.ecp.demo.dubbo.util.DemoConstants;
import com.zengshi.ecp.demo.service.busi.interfaces.IDemoInfoSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.validator.PaasValid;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-demo <br>
 * Description: <br>
 * Date:2015-8-3下午3:57:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public class DemoInfoRSVImpl implements IDemoInfoRSV{
    
    @Resource
    private IDemoInfoSV sv;

    @PaasValid
    @Override
    public void saveInfo(@Valid DemoInfoDTO info) throws BusinessException {
        
        DemoInfo demoInfo = new DemoInfo();
       /* demoInfo.setCreateTime(info.getCreateTime());
        demoInfo.setName(info.getName());
        demoInfo.setStatus(DemoConstants.DEMO_STATUS_VALID);*/
        
        try {
            sv.saveInfo(demoInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public DemoInfoDTO fetchDemoInfoById(DemoInfoDTO info) throws BusinessException {
        
        DemoInfo demoInfo = sv.fetchDemoById(info.getId());
        DemoInfoDTO dto = new DemoInfoDTO();
        dto.setId(demoInfo.getId());
        dto.setName(demoInfo.getDemoInfo());
        return dto;
    }
    
    

}

