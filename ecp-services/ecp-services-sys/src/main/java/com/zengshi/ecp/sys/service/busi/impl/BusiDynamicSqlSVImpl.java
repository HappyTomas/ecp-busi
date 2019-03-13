/** 
 * Project Name:ecp-services-sys 
 * File Name:BusiDynamicSqlSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2015-8-27下午8:38:44 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.busi.manual.BusiDynamicSqlMapper;
import com.zengshi.ecp.sys.service.busi.interfaces.IBusiDynamicSqlSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午8:38:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("busiDynamicSqlSV")
public class BusiDynamicSqlSVImpl extends GeneralSQLSVImpl implements IBusiDynamicSqlSV {
    
    private static final String MODULE = BusiDynamicSqlSVImpl.class.getName();
    
    @Resource(name="busiDynamicSqlMapper")
    private BusiDynamicSqlMapper busiDynamicSqlMapper;

    /**   
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IBusiDynamicSqlSV#fetchParamList(java.lang.String) 
     */
    @Override
    public List<BaseParamDTO> fetchParamList(String sql) {
        if(StringUtil.isEmpty(sql)){
            LogUtil.info(MODULE, "根据SQL查询参数信息，入参SQL为空");
        }
        
        List<BaseParamDTO>  results = busiDynamicSqlMapper.dynamicSqlSelect(sql);
        return results;
    }

}

