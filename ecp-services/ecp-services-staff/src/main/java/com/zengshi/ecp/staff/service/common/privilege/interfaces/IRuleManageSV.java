package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 规则管理功能服务接口<br>
 * Date:2015年9月1日下午3:43:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IRuleManageSV extends IGeneralSQLSV {
    
    public long saveAuthRule() throws BusinessException;

}

