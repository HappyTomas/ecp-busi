/** 
 * Project Name:ecp-services-sys 
 * File Name:IBusiDynamicSqlSV.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.interfaces 
 * Date:2015-8-27下午8:37:25 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午8:37:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBusiDynamicSqlSV extends IGeneralSQLSV {
    
    /**
     * 
     * fetchParamList: 根据SQL脚本从数据库中获取数据 <br/> 
     * 
     * @author yugn 
     * @param sql
     * @return 
     * @since JDK 1.6
     */
    public List<BaseParamDTO> fetchParamList(String sql);

}

