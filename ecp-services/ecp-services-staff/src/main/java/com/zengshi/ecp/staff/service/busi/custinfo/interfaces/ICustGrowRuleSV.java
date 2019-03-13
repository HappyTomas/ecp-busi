package com.zengshi.ecp.staff.service.busi.custinfo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年10月13日下午4:46:24  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICustGrowRuleSV {

    
    /**
     * 
     * getGrowByParamRule:(根据行为模式获取用户成长值). <br/> 
     * 
     * @author wangbh
     * @param money
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public Long getGrowByParamRule(PayQuartzInfoRequest dto) throws BusinessException;
    
    
}

