/** 
 * Project Name:ecp-services-sys 
 * File Name:BusiDynamicSqlMapper.java 
 * Package Name:com.zengshi.ecp.sys.dao.mapper.busi 
 * Date:2015-8-27下午6:01:56 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dao.mapper.busi.manual;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;


/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午6:01:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface BusiDynamicSqlMapper {
    
    public List<BaseParamDTO> dynamicSqlSelect(@Param("sql") String sql) throws DataAccessException;
}

