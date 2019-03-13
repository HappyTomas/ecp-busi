package com.zengshi.ecp.staff.service.common.privilege.impl;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRuleManageSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 规则管理实现<br>
 * Date:2015年9月1日下午3:45:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class RuleManageSVImpl extends GeneralSQLSVImpl implements IRuleManageSV {

    @Override
    public long saveAuthRule() throws BusinessException {
        return 0;
    }


}

